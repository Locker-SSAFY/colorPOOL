import Vue from 'vue'
import Vuex from 'vuex'
import createdPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

import imageStore from '@/store/modules/imageStore.js'
import colorStore from '@/store/modules/colorStore.js'
import userStore from '@/store/modules/userStore.js'
import landingStore from '@/store/modules/landingStore.js'
import magazineStore from '@/store/modules/magazineStore.js'

export default new Vuex.Store({
  modules: {
    imageStore,
    colorStore,
    userStore,
    landingStore,
    magazineStore
  },
  plugins: [
    createdPersistedState({
      paths: ['imgaeStore', 'colorStore', 'landingStore', 'magazineStore'],
    })
  ],
  state: {
  },
  mutations: {
  },
  actions: {
  }
})
