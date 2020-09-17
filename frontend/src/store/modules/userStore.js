
import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'

const userStore = {
  namespaced: true,
  state: {
    //로그인 성공 시 정보 저장
    userInfo: null,
    //로그인 되어있는 상태인지 확인
    isLogin: false,
    //로그인을 실패한 경우 true
    isLoginError: false,
    //로그인 창 display
    modalDisplay: false,
  },
  getters:{
    GE_USER_INFO: state => state.userInfo,
    GE_IS_LOGIN: state => state.isLogin,
    GE_IS_LOGIN_ERROR: state => state.isLoginError,
    GE_DISPLAY: state => state.modalDisplay
  },
  mutations: {
    MU_USER_INFO: (state, payload) => {
      state.userInfo = payload.userInfo
    },
    MU_IS_LOGIN: (state, payload) => {
      state.isLogin = payload
    },
    MU_IS_LOGIN_ERROR: (state, payload) => {
      console.log('MU_IS_LOGIN_ERROR', payload)
      state.isLoginErrors = payload
    },
    MU_DISPLAY: (state, payload) => {
      state.modalDisplay = payload
    }
  },
  actions:{
    //로그인,회원가입 모달창
    AC_DISPLAY: ({commit}, payload) => {
      commit('MU_DISPLAY', payload);
    },
    //회원 로그인
    AC_SIGNIN: ({commit}, payload) => {
      console.log('AC_SINGIN', payload);
      axios.post(SERVER.ROUTES.signin, payload)
      .then(function (response) {
        console.log(response);
        commit('MU_IS_LOGIN_ERROR', false);
        // dispatch.AC_GET_USERINFO({token : response.data});
        // dispatch('AC_GET_USERINFO', {token: response.data.data});
        commit('MU_DISPLAY', false);
        //임시로 userInfo 저장
        commit('MU_USER_INFO', payload);
      })
      .catch(function (error) {
        console.log(error);
        commit('MU_IS_LOGIN_ERROR', true);
      });
    },
    //카카오톡 로그인
    
    //회원가입
    AC_SIGNUP: ({commit}, payload) => {
      console.log('AC_SIGNUP', payload);
      commit
      axios.post(SERVER.ROUTES.signup, payload)
      // axios.post('http://localhost:8080/api/signup', payload)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    //회원 정보 조회
    AC_GET_USERINFO: ({commit}, payload) => {
      console.log('AC_GET_USERINFO', payload.token)
      const token = payload.token
      console.log("token", token)
      const header = {
        'accept': '*/*',
        'X-AUTH-TOKEN': token,
        'Access-Control-Allow-Origin': '*'
      }
      axios.get(SERVER.ROUTES.getUserInfo, { headers: header })
      .then( response =>{
        console.log(response);
        localStorage.setItem("access_token", token);
        commit('MU_USER_INFO', response.data);
        commit('MU_DISPLAY', false);
      })
      .catch( error => {
        console.log(error);
      })
    }
  }
}

export default userStore