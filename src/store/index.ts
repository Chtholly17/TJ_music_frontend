import { createStore } from 'vuex'

export default createStore({
  state:{
    userID: ""
  },
  getters: {
    getUserID (state) {
      return state.userID
    }
  },
  mutations: {
    setUserID (state, ID) {
      state.userID = ID
    }
  },
  actions: {
  },
  modules: {
  }
})
