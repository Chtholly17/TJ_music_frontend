import { createStore } from 'vuex'

export default createStore({
  state:{
    userID:"20170101",
    userPhoto:"http://49.4.115.48:8888/20170101/images/2.jpg"
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
