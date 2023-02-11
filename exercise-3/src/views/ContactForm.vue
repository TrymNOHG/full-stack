<template>
  <form @submit.prevent="onSubmit">
    <fieldset class="contact-form">
      <legend><h1>We would love to hear your feedback on the calculator!</h1></legend>
      <BaseInput
          v-model="feedback.name"
          label="Name:"
          type="text"
          id="name-field"
          :error="nameError"
          required
      />

      <BaseInput
          v-model="feedback.email"
          label="Email:"
          type="text"
          class="email"
          :error="emailError"
          required
      />

      <BaseInput
          v-model="feedback.message"
          label="Feedback:"
          type="textarea"
          id="feedback"
          error="This input has an error!"
          required
      />
    </fieldset>


    <button type="submit" class="button" value="Submit" id="submit-button">Submit</button>
  </form>
</template>

<script>
import BaseInput from "@/components/Forms/BaseInput.vue";
import axios from 'axios';
import { useField, useForm } from 'vee-validate';

export default {
  name: "ContactForm",
  components: {BaseInput},
  data() {
    return {
      feedback: {
        name: this.$store.state.feedbackInfo.name,
        email: this.$store.state.feedbackInfo.email,
        message: ''
      }
    }
  },
  methods: {
    onSubmit() {
      if (this.feedback.name.trim() === '' || this.feedback.email.trim() === '' || this.feedback.message.trim() === '') {
        alert("There can be no blank fields. Please fill out every field.")
        return;
      }

      this.$store.commit('updateName', this.feedback.name);
      this.$store.commit('updateEmail', this.feedback.email);

      axios.post(
          'https://my-json-server.typicode.com/TrymNOHG/full-stack/feedback',
          this.feedback
      ).then(function(response) {
        console.log('Response', response)
      }).catch(function(err) {
        console.log('Error', err)
      })

      this.feedback.message = '';
    }
  },
  computed: {
    pastName() {
      return this.$store.state.feedbackInfo.name
    },
    pastEmail() {
      return this.$store.state.feedbackInfo.email
    }
  },
  setup() {

    const validations = {
      name: value => {
        if (!value) return 'This field is required'

        const nameRegex = /^[a-zA-Z]$/
        if(!nameRegex.text(String(value).toLowerCase())) {
          return 'Name is invalid. It cannot contain numbers or special characters.'
        }

        return true
      },

      email: value => {
        if (!value) return 'This field is required';

        const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (!emailRegex.test(String(value).toLowerCase())) {
          return 'Email address is invalid!'
        }

        return true
      },

      feedback: value => !value ? 'A feedback message has to be entered.' : true

    }

    useForm({
      validationSchema: validations
    })

    const { value: name, errorMessage: nameError} = useField('name')
    const { value: email, errorMessage: emailError} = useField('email')
    const { value: feedback, errorMessage: feedbackError} = useField('email')


    return {
      name,
      nameError,
      email,
      emailError,
      feedback,
      feedbackError
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
