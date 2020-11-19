import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'

const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
    selectedTheme: null,
    themes: [],
    selectedThemeId: null,
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme,
    GE_THEMES: state => state.themes,
    GE_SELECTED_THEME_ID: state => state.selectedThemeId,
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      state.selectedTheme = payload.selectedTheme
    },
    MU_THEMES : (state, payload) => {
      state.themes = payload
    },
    MU_SELECTED_THEME_ID: (state, payload) => {
      state.selectedThemeId = payload.selectedThemeId
    }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      commit('MU_SELECTED_THEME', payload)
    },
    AC_THEMES: ({commit}, payload) => {
      // 배색 GET
      const token = localStorage.getItem('access_token');
      console.log(token);
      const header = {
        'accept': '*',
        'X-AUTH-TOKEN': token,
        // 'Access-Control-Allow-Origin': '*',
        // 'X-Requested-With' : 'XMLHttpRequest'
      }
      const color = payload.id;
      
      //  비정상적인 axios 통신
      // axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/themes/' + color, {headers: header})
      // .then((res) => {
      //   console.log(res)
      //   const themes = res.data.data;
      //   commit('MU_THEMES', themes);
      // })
      // .catch((err) => {
      //   console.err(err);
      // })
      // cors-anywhere통해서 하기 때문에 비정상

      // console.log(commit);
      // console.log(SERVER);
      
      // 정상적인 axios 통신
      axios.get(SERVER.ROUTES.getThemes + color, {headers: header})
      .then((res) => {
        console.log(res)
        const themes = res.data.data;
        commit('MU_THEMES', themes);
      })
      .catch((err) => {
        console.err(err);
      })
      // cors-anywhere통해서 하기 때문에 비정상
      // 정상적인 axios 통신은 추후 확인해 봐야 함

    },
    AC_SELECTED_THEME_ID: ({commit}, payload) => {
      commit('MU_SELECTED_THEME_ID', payload);
    }
  }
}

export default colorStore