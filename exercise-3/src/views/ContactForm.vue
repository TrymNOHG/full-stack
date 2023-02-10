<template>
  <form class="contact-form" @submit.prevent="onSubmit">
    <h1>We would love to hear your feedback on the calculator!</h1>
    <BaseInput
        v-model="name"
        label="Name:"
        type="text"
        id="name-field"
        required
    />

    <BaseInput
        v-model="email"
        label="Email:"
        type="email"
        class="email"
        required
    />

    <BaseInput
        v-model="message"
        label="Feedback:"
        type="textarea"
        id="feedback"
        required
    />


    <button type="submit" class="button" value="Submit" id="submit-button">Submit</button>
  </form>
</template>

<script>
import BaseInput from "@/components/Forms/BaseInput.vue";
import axios from 'axios';

export default {
  name: "ContactForm",
  components: {BaseInput},
  data() {
    return {
      name: this.$store.state.feedbackInfo.name,
      email: this.$store.state.feedbackInfo.email,
      message: ''
    }
  },
  methods: {
    onSubmit() {
      if (this.name.trim() === '' || this.email.trim() === '' || this.message.trim() === '') {
        alert("There can be no blank fields. Please fill out every field.")
        return;
      }

      let feedback = {
        name: this.name,
        email: this.email,
        message: this.message
      }

      this.$store.commit('updateName', this.name);
      this.$store.commit('updateEmail', this.email);

      this.message = '';
    }
  },
  computed: {
    pastName() {
      return this.$store.state.feedbackInfo.name
    },
    pastEmail() {
      return this.$store.state.feedbackInfo.email
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
