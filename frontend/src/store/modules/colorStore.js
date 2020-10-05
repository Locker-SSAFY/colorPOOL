import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'

const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
    selectedTheme: null,
    themes: [],
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme,
    GE_THEMES: state => state.themes,
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      state.selectedTheme = payload.selectedTheme
      console.log('selected theme', state.selectedTheme);
    },
    MU_THEMES : (state, payload) => {
      state.themes = payload
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
      axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/themes/' + color, {headers: header})
      .then((res) => {
        console.log(res)
        const themes = res.data.data;
        commit('MU_THEMES', themes);
      })
      .catch((err) => {
        console.err(err);
      })
      // cors-anywhere통해서 하기 때문에 비정상

      console.log(commit);
      console.log(SERVER);
      
      // 정상적인 axios 통신
      /* axios.get(SERVER.ROUTES.getThemes + color, {withCredentials: true},{headers: header})
      .then((res) => {
        console.log(res);
        commit('MU_THEMES', res.data)
      })
      .catch((err) => {
        console.log(err);
      }) */
      // 정상적인 axios 통신은 추후 확인해 봐야 함

    }
  }
}

export default colorStore