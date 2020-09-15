import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import imageStore from '@/store/modules/imageStore.js'
import colorStore from '@/store/modules/colorStore.js'
import userStore from '@/store/modules/userStore.js'

export default new Vuex.Store({
  modules: {
    imageStore,
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
