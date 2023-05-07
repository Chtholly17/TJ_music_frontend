import { createStore } from 'vuex'

export default createStore({
  state:{
    userID:"20170101",
    userPhoto:require("@/assets/ikun.jpg")
  },
  getters: {
    getUserID (state) {
      return state.userID
    },
    getUserPhoto(state){
      return state.userPhoto
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
