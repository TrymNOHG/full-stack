<template>
  <div class="calculator">
    <div class="screen"> {{ display_input }} </div>
    <div class="buttons" v-on:click="print">
<!--      Maybe add clear or back button?-->
      <button>7</button>
      <button>8</button>
      <button>9</button>
      <button>/</button>
      <button>4</button>
      <button>5</button>
      <button>6</button>
      <button>x</button>
      <button>1</button>
      <button>2</button>
      <button>3</button>
      <button>-</button>
      <button>0</button>
      <button>.</button>
      <button>ans</button>
      <button>+</button>
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
    entry: {
      type: Number, //Previous answer (if needed). This is pulled from the last log entry.
      //Might not need to fetch data from log if I just store the result.
      required: true
    }
  },
  data() {
    return {
      //Stack of operators
      input: [],
      result: null
    } // An object holding the list of operations as well as the result.
  },
  methods: {
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
    calculate() {
      let operators = {'x' : this.multiply, '+' : this.add, '-': this.subtract, '/': this.divide}
      let initVal = 0
      // Calculation operations
      //If invalid calculation (ends with operation symbol), alert(...);
      for (let i = 0; i < this.input.length/3; i++) {
        let num1 = this.input[i];
        let num2 = this.input[i + 1];
        let operator = this.input[i + 2];
        initVal += operators[operator](num1, num2);
      }
      this.result = initVal;

      let entry = {
        input: this.calculation,
        result: this.result
      }

      this.$emit('', entry)

      this.input = []
      this.display_input = ''
    },
    print() {
      console.log("ok")
    }
  },
  computed: {
    calculation() {
      let calc = "";
      for (let i = 0; i < this.input.length/3; i++) {
        let num1 = this.input[i];
        let num2 = this.input[i + 1];
        let operator = this.input[i + 2];
        calc += [num1, operator, num2].join(" ");
      }
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
