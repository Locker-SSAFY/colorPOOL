const headerStore = {
  namespaced: true,
  state: {
    header: false
  },
  getters: {
    GE_HEADER: state => state.header
  },
  mutations: {
    MU_HEADER: (state, payload) => {
      state.header = payload
    }
  },
  actions: {
    AC_HEADER: ({commit}, payload) => {
      commit('MU_HEADER', payload)
      console.log(payload);
    }
  }
}

export default headerStore;