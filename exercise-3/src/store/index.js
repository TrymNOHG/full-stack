import { createStore } from 'vuex'
import axios from "axios";

export default createStore({
  state: {
    logs: {
      logEntries: []
    },
    feedbackInfo: {
      name: '',
      email: ''
    }
  },
  getters: {
    getLogEntries(state) {
      return state.logs.logEntries
    }
  },
  mutations: {
    ADD_ENTRY(state, entry) {
      state.logs.logEntries.unshift(entry)
    },
    CLEAR_ALL(state) {
      state.logs.logEntries = []
    },
    UPDATE_FEEDBACK_INFO(state, person) {
      state.feedbackInfo.name = person.name
      state.feedbackInfo.email = person.email
    },
  },
  actions: {
    createFeedback({ commit }, feedback) {
      axios.post(
          'https://my-json-server.typicode.com/TrymNOHG/full-stack/feedback',
          feedback
      ).then(function(response) {
        console.log('Response', response)
        const person = {
          name: feedback.name,
          email: feedback.email
        }
        commit('UPDATE_FEEDBACK_INFO', person)
        alert("Your feedback has successfully been submitted!")
      }).catch(function(err) {
        console.log('Error', err)
        alert("Oops! Something appears to have gone wrong while processing your feedback.")
      })
    }
  },
  modules: {
  }
})
