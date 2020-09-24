const landingStore = {
  namespaced: true,
  state: {
    isGet: false,
    isPick: false,
    isLanding: true, 
  },
  getters: {
    GE_IS_GET: state => state.isGet,
    GE_IS_PICK: state => state.isPick,
    GE_IS_LANDING: state => state.isLanding
  },
  mutations: {
    MU_IS_GET: (state, payload) => {
      console.log('MU_IS_GET', payload)
      state.isGet = payload.isGet
    },
    MU_IS_PICK: (state, payload) => {
      console.log('MU_IS_Pick', payload)
      state.isPick = payload.isPick
    },
    MU_IS_LANDING: (state, payload) => {
      console.log('MU_IS_LANDING', payload)
      state.isLanding = payload.isLanding
    }
  },
  actions: {
    AC_IS_GET: ({commit}, payload) => {
      commit('MU_IS_GET', payload)
    },
    AC_IS_PICK: ({commit}, payload) => {
      commit('MU_IS_PICK', payload)
    },
    AC_IS_LANDING: ({commit}, payload) => {
      commit('MU_IS_LANDING', payload)
    }
  }
}

export default landingStore