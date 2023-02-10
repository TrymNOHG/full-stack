import { createStore } from 'vuex'

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
    addEntry(state, entry) {
      state.logs.logEntries.unshift(entry)
    },
    clearAll(state) {
      state.logs.logEntries = []
    },
    updateName(state, name) {
      state.feedbackInfo.name = name
    },
    updateEmail(state, email) {
      state.feedbackInfo.email = email
    }
  },
  actions: {
  },
  modules: {
  }
})
