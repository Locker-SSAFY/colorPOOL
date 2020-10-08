const detailStore = {
  namespaced: true,
  state: {
    detail: null
  },
  getters: {
    GE_DETAIL: state => state.detail
  },
  mutations: {
    MU_DETAIL: (state, payload) => {
      state.detail = payload
    }
  },
  actions: {
    AC_DETAIL: ({commit}, payload) => {
      commit('MU_DETAIL', payload)
      console.log(payload);
    }
  }
}

export default detailStore;