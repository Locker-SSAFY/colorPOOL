const landingStore = {
  namespaced: true,
  state: {
    isGet: false,
    isPick: false
  },
  getters: {
    GE_IS_GET: state => state.isGet,
    GE_IS_PICK: state => state.isPick
  },
  mutations: {
    MU_IS_GET: (state, payload) => {
      state.isGet = payload.isGet
    },
    MU_IS_PICK: (state, payload) => {
      state.isPick = payload.isPick
    }
  },
  actions: {
    AC_IS_GET: ({commit}, payload) => {
      commit('MU_IS_GET', payload)
    },
    AC_IS_PICK: ({commit}, payload) => {
      commit('MU_IS_PICK', payload)
    }
  }
}

export default landingStore