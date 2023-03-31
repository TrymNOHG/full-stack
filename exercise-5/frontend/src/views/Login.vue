<template>
  <form @submit="submit">
    <fieldset class="login-form">
      <legend><h1>Login</h1></legend>

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
import UserService from "@/services/UserService";
import CalculationService from "@/services/CalculationService";

export default {
  name: "Login",
  components: {BaseInput},
  data() {
    return {
      userInfo: [
          "username",
          "password"
      ]
    }
  },
  setup () {
    const validations = {
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
        username: store.state.feedbackInfo.username,
        password: ''
      }
    })

    const { value: username} = useField('username')
    const { value: password} = useField('password')

    const submit = handleSubmit(async values => {

      try {
        await UserService.postSignIn(values).then(response => {
          store.commit("SET_SESSION_TOKEN", response.data.token)
        });

        const equations = await CalculationService.getLog(values);
        console.log(equations)
        for(let value of equations.data) {
          let entry = {
            input: value.expression,
            result: value.result
          }
          store.commit('ADD_ENTRY', entry)
        }

      } catch (e) {
        console.log(e);
      }

      values.password = '';

    })

    return {
      submit,
      username,
      password,
      errors
    }
  }
}
</script>

<style scoped>

</style>
