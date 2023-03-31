import { createStore } from 'vuex'
import FeedbackService from "@/services/FeedbackService";
import UserService from "@/services/UserService";
import router from "@/router";
import createPersistedState from 'vuex-persistedstate'

const persistedState = createPersistedState({
  key: 'my-app',
  storage: sessionStorage
})


export default createStore({
  plugins: [persistedState],
  state: {
    logs: {
      logEntries: []
    },
    feedbackInfo: {
      name: '',
      email: ''
    },
    user: {
      name: '',
      email: ''
    },
    sessionToken: null
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
    UPDATE_USER_INFO(state, person) {
      state.feedbackInfo.name = person.name
      state.feedbackInfo.email = person.email
    },
    SET_SESSION_TOKEN(state, sessionToken) {
      state.sessionToken = sessionToken
    },
    CLEAR_STORE(state) {
      state.sessionToken = null
      state.logs.logEntries = []
      state.user.email = ''
      state.user.name = ''
      state.feedbackInfo.name = ''
      state.feedbackInfo.email = ''
    }
  },
  actions: {
    createFeedback({ commit }, feedback) {

      // axios.post(
      //     'https://my-json-server.typicode.com/TrymNOHG/full-stack/feedback',
      //     feedback
      // )
      FeedbackService.postFeedback(feedback)
      .then(function(response) {
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
    },
    createUser({ commit }, user) {

      UserService.postNewUser(user)
          .then(function(response) {
            console.log('Response', response.data.token)
            const person = {
              name: user.username,
              email: user.email
            }
            commit('UPDATE_USER_INFO', person)
            commit('SET_SESSION_TOKEN', response.data.token)
            alert("User created!")
            router.push("/Calculator").then()
          }).catch(function(err) {
        console.log('Error', err)
        alert("Oops! Something appears to have gone wrong while processing your user information.")
      })
    }
  },

  modules: {
  }
})
