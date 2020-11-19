import Vue from 'vue'
import Vuex from 'vuex'
import createdPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

import imageStore from '@/store/modules/imageStore.js'
import colorStore from '@/store/modules/colorStore.js'
import userStore from '@/store/modules/userStore.js'
import landingStore from '@/store/modules/landingStore.js'
import magazineStore from '@/store/modules/magazineStore.js'
import rankStore from '@/store/modules/rankStore.js'
import myPageStore from '@/store/modules/myPageStore.js'
import detailStore from '@/store/modules/detailStore.js'
import headerStore from '@/store/modules/headerStore.js'
import themeStore from '@/store/modules/themeStore.js'

export default new Vuex.Store({
  modules: {
    imageStore,
    colorStore,
    userStore,
    landingStore,
    magazineStore,
    rankStore,
    myPageStore,
    detailStore,
    headerStore,
    themeStore,
  },
  plugins: [
    createdPersistedState({
      paths: ['userStore', 'imageStore', 'colorStore', 'landingStore', 'magazineStore', 'detailStore'],
    })  
  ],
  state: {
  },
  mutations: {
  },
  actions: {
  }
})
