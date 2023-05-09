import { createStore } from 'vuex'

export default createStore({
  state:{
    userID:"20170101",
    userPhoto:"http://49.4.115.48:8080/images/test.jpg"
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
