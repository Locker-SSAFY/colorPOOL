import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'
import materialColors from '../../assets/color/colorList.js'

const myPageStore = {
  namespaced: true,
  state: {
    myMagazine: [],
    scrapMagazine: [],
    recentMagazine: []
  },
  getters: {
    GE_MY_LIST: state => state.myMagazine,
    GE_SCRAP_LIST: state => state.scrapMagazine,
    GE_RECENT_LIST: state => state.recentMagazine
  },
  mutations: {
    MU_MY_LIST: (state, payload) => {
      state.myMagazine = payload
    },
    MU_SCRAP_LIST: (state, payload) => {
      state.scrapMagazine = payload
    },
    MU_RECENT_LIST: (state, payload) => {
      state.recentMagazine = payload
    }
  },
  actions: {
    AC_MY_LIST: ({commit}) => {
      const token = localStorage.getItem('access_token');
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      axios.get(SERVER.ROUTES.getMagazineMine, {headers: header})
      .then((res) => {
        res.data.data.forEach(ele => {
          let id = ele.selectedColorId;
          ele.color = materialColors[parseInt(id / 10)].variations[parseInt(id % 10)].hex;
        })
        commit('MU_MY_LIST', res.data.data);
      })
      .catch((err) => {
        console.log(err);
      })
    },
    AC_SCRAP_LIST: ({commit}) => {
      const token = localStorage.getItem('access_token');
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      axios.get(SERVER.ROUTES.getMagazineBookmark, {headers: header})
      .then((res) => {
        res.data.data.forEach(ele => {
          let id = ele.selectedColorId;
          ele.color = materialColors[parseInt(id / 10)].variations[parseInt(id % 10)].hex;
        })
        commit('MU_SCRAP_LIST', res.data.data);
      })
      .catch((err) => {
        console.log(err);
      })
    },
    AC_RECENT_LIST: ({commit}) => {
      const token = localStorage.getItem('access_token');
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      axios.get(SERVER.ROUTES.getMagazineLike, {headers: header})
      .then((res) => {
        res.data.data.forEach(ele => {
          let id = ele.selectedColorId;
          ele.color = materialColors[parseInt(id / 10)].variations[parseInt(id % 10)].hex;
        })
        commit('MU_RECENT_LIST', res.data.data)
      })
      .catch((err) => {
        console.log(err);
      })
    }
  }
}

export default myPageStore;