<template>
  <form @submit="submit">
    <fieldset class="sign-up-form">
      <legend><h1>Sign up to use our calculator!</h1></legend>
      <BaseInput
          label="Email:"
          type="email"
          class="email"
          id="email"
          :model-value="email"
          :error="errors.email"
          :options="userInfo"
          data-testid="email-field"
          @change="handleChange"
      />

      <BaseInput
          v-model="username"
          label="Username:"
          type="text"
          id="username-field"
          :error="errors.username"
          :options="userInfo"
          data-testid="username-field"
      />

      <BaseInput
          v-model="password"
          label="Password:"
          type="password"
          id="password"
          :error="errors.password"
          :options="userInfo"
          data-testid="password-field"
      />
    </fieldset>


    <button type="submit" class="button" value="Submit" id="submit-button">Submit</button>
  </form>
</template>

<script>
import BaseInput from "@/components/Forms/BaseInput.vue";
import {useStore} from "vuex";
import {useField, useForm} from "vee-validate";

export default {
  name: "SignUp",
  components: {BaseInput},
  data() {
    return {
      userInfo: [
        'email',
        'username',
        'password'
      ]
    }
  },
  setup() {
    const validations = {
      email: value => {
        if (!value) return 'This field is required';

        const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (!emailRegex.test(String(value).toLowerCase())) {
          return 'Email address is invalid!'
        }

        return true
      },
      username: value => {
        if (!value) return 'This field is required'

        //TODO: fix this regex
        const usernameRegex = /[a-zA-Z0-9]/
        if(!usernameRegex.test(String(value).toLowerCase())) {
          return 'Username is invalid. It cannot contain numbers or special characters.'
        }

        return true
      },

      password: value => !value.trim() ? 'A password message has to be entered.' : true

    }

    const store = useStore()

    const { handleSubmit, errors } = useForm({
      validationSchema: validations,
      initialValues: {
        username: store.state.user.username,
        email: store.state.user.email,
        password: ''
      }
    })

    const { value: email, handleChange} = useField('email')
    const { value: username} = useField('username')
    const { value: password} = useField('password')

    const submit = handleSubmit(async values => {

      try {
        await store.dispatch('createUser', values)
      } catch (e) {
        console.log(e);
      }

      values.password = '';

    })

    return {
      submit,
      email,
      username,
      password,
      errors,
      handleChange
    }
  }
}
</script>

<style scoped>

</style>
