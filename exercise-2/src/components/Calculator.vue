<template>
  <div class="calculator">
    <div class="screen"> {{ this.calculation }} </div>
    <div class="buttons">
<!--      Maybe add clear or back button?-->
      <button v-for="button in buttons" @click="add_input(button)">{{ button }}</button>
      <button @click="calculate" class="enterButton">Enter</button>
    </div>

  </div>
</template>

<script>
export default {
  props: {
  },
  data() {
    return {
      buttons: [7, 8, 9, "/", 4, 5, 6, "x", 1, 2, 3, "-", 0, ".", "ans", "+", "del", "ac", "c"],
      //Stack of operators
      input: [],
      result: null,
      operators: {'x' : this.multiply, '+' : this.add, '-': this.subtract, '/': this.divide}
    } // An object holding the list of operations as well as the result.
  },
  methods: {
    add_input(value) {
      if (typeof value === 'number' &&
          (this.input.length === 0 || this.operators[this.input[this.input.length - 1]] !== undefined)) {
        this.input.push(value)
      }
      else if (this.operators[value] !== undefined) this.input.push(value)
      else if (typeof value === 'string') {
        switch (value) {
          case 'ans':
            if (this.result !== null &&
                (this.operators[this.input[this.input.length - 1]] !== undefined || this.input.length === 0)) {
              this.input.push(this.result)
            }
            else alert("Invalid use of ans button!")
            break;
          case '.':
            if (String(this.input[this.input.length - 1]).indexOf(value) !== -1) alert("Invalid operation")
            else this.input[this.input.length - 1] += '.'
            break;
          case 'del':
            let lastIndex = this.input.length - 1
            if (this.input[lastIndex].length === 1) this.input.pop()
            else this.input[lastIndex] = this.input[lastIndex].substring(0, this.input[lastIndex].length - 1)
            break;
          case 'c':
            this.input = []
            break;
          case 'ac':
            this.input = []
            this.result = null
            this.$store.commit('clearAll')
            break;
        }
      }
      else this.input[this.input.length - 1] += String(value)

    },
    multiply (number1, number2) {
      return number1 * number2
    },
    add(number1, number2) {
      return number1 + number2;
    },
    subtract(number1, number2) {
      return number1 - number2;
    },
    divide(number1, number2) {
      if (number2 === 0) {
        alert("You cannot divide by 0");
      }
      return number1/number2;
    },
    //This method could use the Reverse Polish Notation in order to perform the necessary operations.
    calculate() {
      if (this.operators[this.input[this.input.length - 1]] !== undefined || this.input.length < 3) {
        alert("Invalid Calculation")
        return
      }

      let initVal = 0
      // Calculation operations
      let num1 = Number(this.input[0]);
      for (let i = 0; i < this.input.length/2; i+=2) {
        let num2 = Number(this.input[i + 2]);
        let operator = this.input[i + 1];
        initVal = this.operators[operator](num1, num2);
        num1 = initVal
        console.log(initVal)
      }
      this.result = initVal;

      let entry = {
        input: this.calculation,
        result: this.result
      }

      // this.$emit('completed-entry', entry)
      // this.$store.dispatch('addEntry', entry)
      this.$store.commit('addEntry', entry)

      this.input = []
    }
  },
  computed: {
    calculation() {
      let calc = "";
      this.input.forEach(symbol => calc += symbol + " ");
      return calc
    }
  }
}

</script>

<style scoped>
.calculator {
  display: grid;
  grid-template-rows: 1fr 3fr;
  row-gap: 10px;
  background-color: gray;
  padding: 10px 10px;
  border-radius: 5%;
  max-width: 300px;
}
.buttons {
  display: grid;
  grid-template: 1fr 1fr 1fr 1fr 1fr/ 1fr 1fr 1fr 1fr;
  gap: 5px 5px;
}

button {
  text-align: center;
}

button:hover {
  cursor: pointer;
  box-shadow: 0 0.5em 0.5em -0.4em;
  transform: translateY(-0.10em);
}

.enterButton {
  color: black;
  background-color: rgba(139, 81, 10, 0.85);
}

.calculator .screen {
  resize: none;
  outline: none;
  background-color: white;
  color: black;
  border-radius: 10%;
  text-align: left;
  padding-top: 15px;
  text-indent: 25px;
}
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
}
</style>
