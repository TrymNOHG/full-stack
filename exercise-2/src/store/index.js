import { createStore } from 'vuex'

export default createStore({
  state: {
    logs: {
      logEntries: []
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
    }
  },
  actions: {
  },
  modules: {
  }
})
