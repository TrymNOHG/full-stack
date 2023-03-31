<template>
  <label :for="uuid" v-bind="$attrs" v-if="label">{{ label }}</label>
  <input
      v-bind="$attrs"
      :placeholder="label"
      class="field"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      :id="uuid"
      :aria-describedby="error ? `${uuid}-error}` : null"
      :aria-invalid="error ? true : null"
  >
  <BaseErrorMessage
      v-if="error"
      :id="`${uuid}-error`"
  >
    {{ error }}
  </BaseErrorMessage>
</template>

<script>
import UniqueID from '@/features/UniqueID'
import BaseErrorMessage from "@/components/Forms/BaseErrorMessage.vue";
export default {
  components: {BaseErrorMessage},
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: [String, Number],
      default: ''
    },
    error: {
      type: String,
      default: ''
    }
  },
  setup () {
    const uuid = UniqueID().getID()
    return {
      uuid
    }
  }

}
</script>

<style scoped>
input {
  border-radius: 5px;
  height: 50px;
  font-size: 24px;
}

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

.errorMessage {
  color: red;
}
</style>
