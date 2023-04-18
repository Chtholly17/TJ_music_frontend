import { createStore } from 'vuex'
import {ElMessage} from "element-plus";

export default createStore({
  state:{
    userID: ""
  },
  getters: {
    getUserID (state) {
      ElMessage.success(state.userID)
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
