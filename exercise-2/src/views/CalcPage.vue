<script>
import Calculator from '@/components/Calculator.vue'
import Log from '@/components/Log.vue'
export default {
  name: "CalcPage",
  components: {
    Calculator,
    Log
  }
}

</script>

<template>
  <div class="splitCalc">
    <Calculator/>
    <Log/>
  </div>
</template>

<style scoped>
.splitCalc {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
Calculator {
}

</style>