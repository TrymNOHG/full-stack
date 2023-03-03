<template>
  <div class="calculator">
    <div class="screen" data-testid="calc-screen"> {{ this.calculation }} </div>
    <div class="buttons">
      <button v-for="button in buttons" @click="add_input(button)">{{ button }}</button>
      <button @click="calculate" class="enterButton">Enter</button>
    </div>
  </div>
</template>

<script>
import CalculationService from "@/services/CalculationService";
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
      if (typeof value === 'number') this.handleNumber(value)
      else if (this.isOperator(value)) this.handleOperator(value)
      else if (typeof value === 'string') this.handleSpecialButton(value)
      else alert("Something went wrong")
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
    handleNumber(value) {
      if (this.input.length === 0 || this.isOperator(this.lastElement)) {
        if (this.lastElement === "-" && this.input.length === 1) this.input[this.lastIndex] += value
        else this.input.push(value)
      }
      else this.input[this.input.length - 1] += String(value)
    },
    handleOperator(value) {
      if (!this.isOperator(this.lastElement)){
        if (this.result !== null && this.input.length === 0) {
          this.input.push(this.result)
          this.input.push(value)
        } else if (this.input.length !== 0) {
          this.input.push(value)
        } else if (value === "-"){
          this.input.push(value)
        }
      }
    },
    handleSpecialButton(value) {
      switch (value) {
        case 'ans':
          this.ansButton()
          break;
        case '.':
          this.decimalButton(value)
          break;
        case 'del':
          let nextValue = this.lastElement !== undefined ? String(this.input.pop()) : "";
          if (nextValue.length > 1) this.input.push(nextValue.substring(0, nextValue.length - 1))
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
    },
    ansButton() {
      if (this.result !== null &&
          (this.isOperator(this.lastElement)|| this.input.length === 0)) {
        this.input.push(this.result)
      }
      else alert("Invalid use of ans button!")
    },
    decimalButton(value) {
      if (String(this.lastElement).indexOf(value) !== -1
          || this.isOperator(this.lastElement)) alert("Invalid operation")
      else this.input[this.input.length - 1] += '.'
    },
    //This method could use the Reverse Polish Notation in order to perform the necessary operations.
    async calculate() {
      if (this.isOperator(this.input[this.input.length - 1]) || this.input.length < 3) {
        alert("Invalid Calculation")
        return
      }

      const expression = {
        "elements": this.input
      }

      await CalculationService.postFeedback(expression).then(response => {
        console.log('Response', response)
        this.result = response.data;
      }).catch(function (err) {
        console.log('Error', err)
      })

      let entry = {
        input: this.calculation,
        result: this.result
      }

      this.$store.commit('ADD_ENTRY', entry)

      this.input = []
    },
    isOperator(operator) {
      return this.operators[operator] !== undefined
    }
  },
  computed: {
    calculation() {
      let calc = "";
      this.input.forEach(symbol => calc += symbol + " ");
      return calc
    },
    lastIndex() {
      return this.input.length - 1
    },
    lastElement() {
      return this.input[this.lastIndex]
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
  max-width: 500px;
  min-width: 300px;
  margin: 10px auto;
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
  background-color: #9be7ff;
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
