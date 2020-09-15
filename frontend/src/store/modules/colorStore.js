const colorStore = {
  namespaced: true,
  state: {
    selectedColor: '#EF5350',
    selectedTheme: null
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      state.selectedTheme = payload.selectedTheme
    }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      console.log('AC_SELECTED_COLOR', payload.selectedColor);
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      console.log('AC_SELECTED_THEME', payload.selectedTheme);
      commit('MU_SELECTED_THEME', payload)
    }
  }
}

export default colorStore