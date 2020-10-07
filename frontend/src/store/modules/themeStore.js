import axios from 'axios';

const themeStore = {
  namespaced: true,
  state: {
    themeColor: {}
  },
  getters: {
    GE_THEME_COLOR: state => state.theme, 
  },
  mutations: {
    MU_THEME_COLOR: (state, payload) => {
      state.theme = payload
    }
  },
  actions: {
    AC_THEME_COLOR: ({commit}, payload) => {
      const token = localStorage.getItem('access_token')
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      const themeId = payload.themeId;
      axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/colors/' + themeId, {headers: header})
      .then((res) => {
        console.log(res);
        commit('MU_THEME_COLOR', res.data.data)
      })
      .catch((err) => {
        console.log(err);
      })
    }
  }
}

export default themeStore;