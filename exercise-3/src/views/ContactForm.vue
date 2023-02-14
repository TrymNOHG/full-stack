<template>
  <form @submit="submit">
    <fieldset class="contact-form">
      <legend><h1>We would love to hear your feedback on the calculator!</h1></legend>
      <BaseInput
          v-model="name"
          label="Name:"
          type="text"
          id="name-field"
          :error="errors.name"
          :options="feedbackInfo"
      />

      <BaseInput
          label="Email:"
          type="email"
          class="email"
          :model-value="email"
          :error="errors.email"
          :options="feedbackInfo"
          @change="handleChange"
      />

      <BaseInput
          v-model="feedback"
          label="Feedback:"
          type="textarea"
          id="feedback"
          :error="errors.feedback"
          :options="feedbackInfo"
      />
    </fieldset>


    <button type="submit" class="button" value="Submit" id="submit-button">Submit</button>
  </form>
</template>

<script>
import BaseInput from "@/components/Forms/BaseInput.vue";
import { useField, useForm } from 'vee-validate';
import { useStore } from 'vuex';

export default {
  name: "ContactForm",
  components: {BaseInput},
  data() {
    return {
      feedbackInfo: [
          'name',
          'email',
          'message'
      ]
    }
  },
  setup() {

    const validations = {
      name: value => {
        if (!value) return 'This field is required'

        //TODO: fix this regex
        const nameRegex = /[a-zA-Z]/
        if(!nameRegex.test(String(value).toLowerCase())) {
          return 'Name is invalid. It cannot contain numbers or special characters.'
        }

        return true
      },

      email: value => {
        if (!value) return 'This field is required';

        const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (!emailRegex.test(String(value).toLowerCase())) {
          return 'Email address is invalid!'
        }

        return true
      },

      feedback: value => !value.trim() ? 'A feedback message has to be entered.' : true

    }

    const store = useStore()

    const { handleSubmit, errors } = useForm({
      validationSchema: validations,
      initialValues: {
        name: store.state.feedbackInfo.name,
        email: store.state.feedbackInfo.email,
        feedback: ''
      }
    })

    const { value: name} = useField('name')
    const { value: email, handleChange} = useField('email')
    const { value: feedback} = useField('feedback')

    const submit = handleSubmit(values => {

      store.dispatch('createFeedback', values)

      values.feedback = '';

    })

    return {
      submit,
      name,
      email,
      feedback,
      errors,
      handleChange
    }
  }
}
</script>

<style scoped>

.contact-form {
  display: grid;
  max-width: 500px;
  min-width: 300px;
  align-content: center;
  padding: 20px;
  margin: 10px auto;
  grid-row-gap: 20px;
  border: 4px solid #d8d8d8;

}

h1 {
  color: #9be7ff;
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

#submit-button {
  position: relative;
  width: 250px;
  height: 75px;
  border: 0;
  text-align: center;
  max-width: 300px;
  min-width: 200px;
  margin: 10px auto;
  cursor: pointer;
}

#submit-button:hover {
  background-color: gray;
}

</style>
