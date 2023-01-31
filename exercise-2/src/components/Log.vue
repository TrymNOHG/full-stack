<template>
<!--  Scrollable log where last operation goes to the bottom.-->
  <div>
    <p v-for="pastEntry in pastEntries">{{ pastEntry }}</p>
  </div>
</template>

<script>
export default {
  name: "Log",
  props: {
    entry: {
      type: Object, //Previous answer (if needed). This is pulled from the last log entry.
      //Might not need to fetch data from log if I just store the result.
      required: true
    }
  },
  data() {
    return {
      pastEntries: []
    }
  }
}
</script>

<style scoped>
  p {
    background-color: white;
    height: 300px;
    overflow: scroll;
  }
</style>