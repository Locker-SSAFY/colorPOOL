import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore.js'
import userStore from '@/store/modules/userStore.js'

export default new Vuex.Store({
  modules: {
    colorStore,
    userStore
  },
  state: {
  },
  mutations: {
  },
  actions: {
  }
})