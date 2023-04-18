import { createStore } from 'vuex'
import {ElMessage} from "element-plus";

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
      ElMessage.success(state.userID)
    }
  },
  actions: {
  },
  modules: {
  }
})
