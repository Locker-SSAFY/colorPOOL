
import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'
// import axios2 from 'axios'

const userStore = {
  namespaced: true,
  state: {
    //로그인 성공 시 정보 저장
    userInfo: null,
    //로그인 되어있는 상태인지 확인
    isLogin: false,
    //로그인을 실패한 경우 true
    isLoginError: false,
    //에러메시지
    errorMsg: null,
    //로그인 창 display
    modalDisplay: false,
  },
  getters:{
    GE_USER_INFO: state => state.userInfo,
    GE_IS_LOGIN: state => state.isLogin,
    GE_IS_LOGIN_ERROR: state => state.isLoginError,
    GE_DISPLAY: state => state.modalDisplay,
    GE_ERROR: state => state.errorMsg
  },
  mutations: {
    MU_USER_INFO: (state, payload) => {
      console.log('MU_USER_INFO', payload)
      state.userInfo = payload
    },
    MU_IS_LOGIN: (state, payload) => {
      state.isLogin = payload
    },
    MU_IS_LOGIN_ERROR: (state, payload) => {
      state.isLoginErrors = payload
    },
    MU_DISPLAY: (state, payload) => {
      state.modalDisplay = payload
    },
    MU_ERROR: (state, payload) => {
      state.errorMsg = payload
    }
  },
  actions:{
    //로그인,회원가입 모달창
    AC_DISPLAY: ({commit}, payload) => {
      commit('MU_DISPLAY', payload);
    },

    //회원 로그인
    AC_SIGNIN: ({commit, dispatch}, payload) => {
      // axios2.post('/api/signin', payload)
      // axios2.post('http://localhost:8080/api/signin', payload)
      axios.post(SERVER.ROUTES.signin, payload)
      .then(function (response) {
        console.log(response);
        commit('MU_IS_LOGIN_ERROR', false);
        dispatch('AC_GET_USERINFO', {token: response.data.data});
      })
      .catch(function (error) {
        console.log(error.response);
        if(error.response.data.success === false ){
          commit('MU_ERROR', error.response.data.msg);
        } 
        commit('MU_IS_LOGIN_ERROR', true);
      });
    },

    //카카오톡 로그인
    AC_KAKAO_SIGNIN: ({commit}) => {
      console.log('AC_KAKAO_SIGNIN')
      window.Kakao.Auth.login({
        success: function(authObj) {
          console.log(authObj)
          commit;
          const payload = {
            accessToken: authObj.access_token,
            provider: 'kakao'
          }
          console.log("kakao_token", payload);
            axios.post(SERVER.ROUTES.socialSignin, payload)
          // axios.post('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/signin/social', payload)
            .then((response) => {
              console.log("response", response.data);
              const token = response.data.data;
              localStorage.setItem('access_token', token);
              commit('MU_IS_LOGIN_ERROR', false);
              commit('MU_DISPLAY', false);
              commit('MU_IS_LOGIN', true);
            })
            .catch((error) => {
              console.log("kakao login-error: ", error);
            })

          // dispatch('AC_GET_USERINFO', {token: response.data.data});
          //임시로 userInfo 저장
          // commit('MU_USER_INFO', {provder: 'kakao'});
        },
        fail: function(err) {
          alert("fail", JSON.stringify(err));
          console.log("err : ", err);
        },
      });
    },

    //회원가입
    AC_SIGNUP: ({commit}, payload) => {
      console.log('AC_SIGNUP', payload);
      commit
      axios.post(SERVER.ROUTES.signup, payload)
      .then(function (response) {
        console.log(response);
        commit('MU_DISPLAY', false);
        alert('회원가입 성공!');
      })
      .catch(function (error) {
        console.log(error);
      });
    },

    //회원 정보 조회
    AC_GET_USERINFO: ({commit}, payload) => {
      const token = payload.token
      const header = {
        'accept': '*/*',
        'X-AUTH-TOKEN': token,
        // 'Access-Control-Allow-Origin': '*'/
      }
      // axios2.get(SERVER.ROUTES.getUserInfo, { headers: header })
      // axios.get('https://j3a303.p.ssafy.io/api/user?lang=ko', { headers: header })
      axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/user?lang=ko', { headers: header })
      // axios.get(SERVER.ROUTES.getUserInfo, { headers: header })
      .then( response =>{
        localStorage.setItem("access_token", token);
        commit('MU_USER_INFO', response.data.data);
        commit('MU_DISPLAY', false);
        commit('MU_IS_LOGIN', true);
      })
      .catch( error => {
        console.log(error);
      })
    },

    //로그아웃 처리
    AC_LOGOUT: ({commit},payload) => {
      console.log('AC_LOGOUT');
      commit('MU_USER_INFO', {userInfo: payload});
      commit('MU_IS_LOGIN', false);
    },
    //에러 메시지 수정
    AC_ERROR: ({commit}, payload) => {
      commit('MU_ERROR', payload);
    },

    AC_IS_LOGIN: ({commit}, payload) => {
      commit('MU_IS_LOGIN', payload);
    }
  }
}

export default userStore