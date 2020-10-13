import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'
import materialColors from '../../assets/color/colorList.js'

const rankStore = {
  namespaced: true,
  state: {
    topRank: [],
    restRank: [],
    magazineList: [],
  },
  getters: {
    GE_TOP_RANK: state => state.topRank,
    GE_REST_RANK: state => state.restRank,
    GE_MAGAZINE_LIST: state => state.magazineList,
  },
  mutations: {
    MU_TOP_RANK: (state, payload) => {
      state.topRank = payload
    },
    MU_REST_RANK: (state, payload) => {
      state.restRank = payload
    },
    MU_MAGAZINE_LIST: (state, payload) => {
      state.magazineList = payload
    }
  },
  actions: {
    AC_MAGAZINE_LIST: ({commit}) => {
      const token = localStorage.getItem('access_token');
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      console.log(token)

      // axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/magazines', {headers: header})
      // .then((res) => {
      //   console.log(res)
      //   res.data.data.forEach(ele => {
      //     let id = ele.selectedColorId;
      //     ele.color = materialColors[parseInt(id / 10)].variations[parseInt(id % 10)].hex
      //   });
      //   console.log(res.data.data.slice(0, 3))
      //   // commit('MU_TOP_RANK', res.data.data.slice(0, 3));
      //   // commit('MU_REST_RANK', res.data.data.slice(3, res.data.data.length))
      //   commit('MU_MAGAZINE_LIST', res.data.data);
      // })
      // .catch((err) => {
      //   console.err(err);
      // })
      // console.log(SERVER);

      //////////////////////////////////////////////
      // 정상적인 axios 통신
      axios.get(SERVER.ROUTES.getMagazineList, {headers: header})
      .then((res) => {
        res.data.data.forEach(ele => {
          let id = ele.selectedColorId;
          ele.color = materialColors[parseInt(id / 10)].variations[parseInt(id % 10)].hex
        });
        commit('MU_MAGAZINE_LIST', res.data.data);
      })
      .catch((err) => {
        console.log(err);
      })
      ///////////////////////////////////////////////
    },
    AC_TOP_RANK: ({commit}, payload) => {
      const topList = [
        {
          id: 1,
          name: 'MOVIEMAG',
          color: '#7E38E0',
          theme: ['#4B1694', '#E0A14F', '#7E38E0','#22E050','#1E943A'],
          userName: 'BEAVER',
          userEmail: 'beaver95@naver.com',
          coverImg: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/oyyUcGwLX7LTFS1pQbLrQpyzIyt.jpg',
          date: '2020-09-28 MON',
          likes: [1,2,3,4,5,6,7,8,9,10],
          heart: true,
          bookmark: true,
        },
        {
          id: 2,
          name: 'ACCMAG',
          color: '#38C0E0',
          theme: ['#1A44AD','#505C7A','#38C0E0','#E49872','#AD6A5B'],
          userName: 'YOON',
          userEmail: 'gaechunjae@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1558368204-cc52a4a4e519?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1052&q=80',
          date: '2020-09-26 SAT',
          likes: [1,2,3,4,5,6,7,8,9],
          heart: true,
          bookmark: true,
        },
        {
          id: 3,
          name: 'FASHIONMAG',
          color: '#1AADA4',
          theme: ['#1AADA4','#507A78','#38E076','#E4729A','#AD0B94'],
          userName: 'JUNHO',
          userEmail: 'runro94@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1554141220-83411835a60b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=678&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4,5,6,7,8],
          heart: false,
          bookmark: false
        }
      ];
      payload = topList;
      commit('MU_TOP_RANK', payload)
    },
    AC_REST_RANK: ({commit}, payload) => {
      const restList = [
        {
          id: 4,
          name: 'DIGIMON',
          color: '#66AB5E',
          theme: ['#66AB5E','#27781E','#CEDE91','#8D5AE2','#584FAB'],
          userName: 'YUNJIN',
          userEmail: 'yunjin95@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1575201647632-45fae95c9ce4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2666&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4,5,6,7],
          heart: false,
          bookmark: true
        },
        {
          id: 5,
          name: 'POKEMON',
          color: '#89ACAC',
          theme: ['#89ACAC','#1E7878','#90DEB2','#E25A7A','#AB4F92'],
          userName: 'KANGSE',
          userEmail: 'seeung@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1513694203232-719a280e022f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2698&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4,5,6],
          heart: true,
          bookmark: false
        },
        {
          id: 6,
          name: 'BONJOUR',
          color: '#AD945A',
          theme: ['#AD945A','#7A725E','#E09354','#00E6C9','#1FAD73'],
          userName: 'EUNGA',
          userEmail: 'eunga94@naver.com',
          coverImg: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/8UUZ2oV1OjYd55BMZWaQrcRPTGN.jpg',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4,5],
          heart: false,
          bookmark: false,
        },
        {
          id: 7,
          name: 'HIHELLO',
          color: '#286A94',
          theme: ['#286A94','#E06161','#53AAE0','#E0DE3D','#94932F'],
          userName: 'RUNRO',
          userEmail: 'runroh94@naver.com',
          coverImg: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/qZARQ9ztjIBRfwmg8q3SdOVI6H1.jpg',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4],
          heart: false,
          bookmark: true
        },
        {
          id: 8,
          name: 'WHATTHE',
          color: '#289439',
          theme: ['#289439','#8B60E0','#53E069','#E0913D','#957A5E'],
          userName: 'baejaewon',
          userEmail: 'jaewon9494@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1513161455079-7dc1de15ef3e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2,3],
          heart: true,
          bookmark: false
        },
        {
          id: 9,
          name: 'HEADERFOOTER',
          color: '#599162',
          theme: ['#599162','#BCABDE','#9EDFA9','#DEB587','#916B41'],
          userName: 'BAEGEIN',
          userEmail: 'baegain95@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2],
          heart: true,
          bookmark: true
        },
        {
          id: 7,
          name: 'HIHELLO',
          color: '#286A94',
          theme: ['#286A94','#E06161','#53AAE0','#E0DE3D','#94932F'],
          userName: 'RUNRO',
          userEmail: 'runroh94@naver.com',
          coverImg: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/qZARQ9ztjIBRfwmg8q3SdOVI6H1.jpg',
          date: '2020-09-25 FRI',
          likes: [1,2,3,4],
          heart: false,
          bookmark: true
        },
        {
          id: 8,
          name: 'WHATTHE',
          color: '#289439',
          theme: ['#289439','#8B60E0','#53E069','#E0913D','#957A5E'],
          userName: 'baejaewon',
          userEmail: 'jaewon9494@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1513161455079-7dc1de15ef3e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2,3],
          heart: true,
          bookmark: false
        },
        {
          id: 9,
          name: 'HEADERFOOTER',
          color: '#599162',
          theme: ['#599162','#BCABDE','#9EDFA9','#DEB587','#916B41'],
          userName: 'BAEGEIN',
          userEmail: 'baegain95@naver.com',
          coverImg: 'https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
          date: '2020-09-25 FRI',
          likes: [1,2],
          heart: true,
          bookmark: true
        }
      ];
      payload = restList;
      commit('MU_REST_RANK', payload)
    }
  }
}

export default rankStore;