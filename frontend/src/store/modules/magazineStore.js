const magazineStore = {
  namespaced: true,
  state: {
    magazineImages: []
  },
  getters: {
    GE_MAGAZINE_IMAGES: state => state.magazineImages
  },
  mutations: {
    MU_MAGAZINE_IMAGES: (state, payload) => {
      state.magazineImages = payload.magazineImages
    }
  },
  actions: {
    AC_MAGAZINE_IMAGES: ({commit}, payload) => {
      commit('MU_MAGAZINE_IMAGES', payload)
    }
  }
}

export default magazineStore;