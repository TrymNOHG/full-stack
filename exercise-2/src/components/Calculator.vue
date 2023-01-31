<template>
  <div class="calculator">
    <div class="screen"> {{ this.calculation }} </div>
    <div class="buttons">
<!--      Maybe add clear or back button?-->
      <button v-for="button in buttons" @click="add_input(button)">{{ button }}</button>
      <button @click="calculate">Enter</button>
    </div>

  </div>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <h3>

    </h3>
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
      currentNumber: null,
      result: null,
      float: 1
    } // An object holding the list of operations as well as the result.
  },
  methods: {
    add_input(value) {
      let operators = {'x' : this.multiply, '+' : this.add, '-': this.subtract, '/': this.divide}
      switch (value) {
        case 'ans':
          if (this.result !== null && typeof this.input[this.input.length - 1] !== "number") {
            this.input.push(this.result)
          } else {
            alert("Invalid use of ans button!")
          }
          break;
        case '.':
          let currentVal = this.input.pop()
          this.float *= 10;
          this.input.push(currentVal * 1.0)
          break;
        case 'del':
          this.input.pop();
          break;
        case 'c':
          break;
        case 'ac':
          this.input = []
          this.currentNumber = null
          break;
        default:
          if (operators[value] !== undefined){
            if (this.input.length === 0) {
              if (this.result === null) {
                alert("Invalid")
              } else {
                this.input.push(this.result);
              }
            }
            this.input.push(value)
            this.currentNumber = null
            this.float = 1
          } else if (typeof value === "number"){
            if(this.currentNumber !== null) this.input.pop()
            if(this.float === 1) {
              this.currentNumber = this.currentNumber * 10 + value;
            } else {
              this.currentNumber += value/this.float;
              this.float *= 10;
            }
            this.input.push(this.currentNumber)
          } else {
            alert("Something went wrong")
          }
      }
      console.log(this.input)
      console.log(this.currentNumber)

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
    //This method uses the Reverse Polish Notation in order to perform the necessary operations.
    calculate() {
      let operators = {'x' : this.multiply, '+' : this.add, '-': this.subtract, '/': this.divide}
      let initVal = 0
      // Calculation operations
      //If invalid calculation (ends with operation symbol), alert(...);
      let num1 = this.input[0];
      for (let i = 0; i < this.input.length/2; i+=2) {
        let num2 = this.input[i + 2];
        let operator = this.input[i + 1];
        console.log(operator)
        initVal += operators[operator](num1, num2);
        num1 = initVal
      }
      this.result = initVal;

      let entry = {
        input: this.calculation,
        result: this.result
      }

      console.log(this.result);

      this.$emit('completed-entry', entry)

      this.input = []
      this.currentNumber = null
    }
  },
  computed: {
    calculation() { //TODO: This could be refactored so that a variable calc is saved in data and changed in methods.
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
}
.buttons {
  display: grid;
  grid-template: 1fr 1fr 1fr 1fr / 1fr 1fr 1fr 1fr;
  gap: 10px 10px;
}

button {
  text-align: center;
}

button:hover {
  cursor: pointer;
  box-shadow: 0 0.5em 0.5em -0.4em;
  transform: translateY(-0.10em);
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
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>
