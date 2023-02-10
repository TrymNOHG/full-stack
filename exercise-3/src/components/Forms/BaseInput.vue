<template>
  <label  v-bind="$attrs">{{ label }}</label>
  <input
      v-bind="$attrs"
      :placeholder="label"
      class="field"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
  >
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: [String, Number],
      default: ''
    }
  }
}
</script>

<style scoped>
label {
  color: white;
}

textarea {
  min-height: 200px;
  overflow: scroll;
}

#name-field {
  max-width: 300px;
  min-width: 300px;
  alignment: center;
  margin: 10px auto;
}
</style>
