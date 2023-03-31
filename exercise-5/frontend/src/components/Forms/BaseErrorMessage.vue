<template>
<p
    class="errorMessage"
    :id="id"
    aria-live="assertive"
>
  <slot />
</p>
</template>

<script>
export default {
  name: "BaseErrorMessage",
  props: {
    id: {
      type: [String, Number],
      required: true
    }
  }
}
</script>

<style scoped>

</style>
