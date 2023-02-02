<template>
<!--  Scrollable log where last operation goes to the bottom.-->
  <div class="log">
    <p>Log</p>
    <textarea disabled>{{ this.updateEntries }}</textarea>
  </div>
</template>

<script>
export default {
  name: "Log",
  data() {
    return {
      pastEntries: []
    }
  },
  methods: {

  },
  computed: {
    updateEntries(){
      let calc = "";
      let pastEntries = this.$store.state.logs.logEntries
      pastEntries.forEach(symbol => calc += symbol.input + " = " + symbol.result + "\n");
      return calc
    }
  }
}
</script>

<style scoped>
  textarea {
    background-color: white;
    height: 300px;
    overflow: scroll;
    resize: none;
  }
</style>