import { createStore } from 'vuex'

export default createStore({
  state:{
    userID: "",
    userPhoto: ""

  },
  getters: {
    getUserID (state) {
      return state.userID
    },
    getUserPhoto(state){
      return state.userPhoto
    },
  },
  mutations: {
    setUserID (state, ID) {
      state.userID = ID
    },
    setUserPhoto (state, photo) {
      state.userPhoto = photo
    }
  },
  actions: {
  },
  modules: {
  }
})
