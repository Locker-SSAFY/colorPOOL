
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
  },
  getters:{
    GE_USER_INFO: state => state.userInfo,
    GE_IS_LOGIN: state => state.isLogin,
    GE_IS_LOGIN_ERROR: state => state.isLoginError
  },
  mutations: {
    MU_USER_INFO: (state, payload) => {
      state.userInfo = payload.userInfo
    },
    MU_IS_LOGIN: (state, payload) => {
      state.isLogin = payload
    },
    MU_IS_LOGIN_ERROR: (state, payload) => {
      state.isLoginErrors = payload
    },
  },
  actions:{
    //회원 로그인
    AC_SINGIN: ({dispatch}, payload) => {
      console.log('AC_SINGIN', payload);
      axios.post(SERVER.ROUTES.signin, payload)
      .then(function (response) {
        console.log(response);
        dispatch.AC_GET_USERINFO({token : response.data});
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    //회원가입
    AC_SIGNUP: ({commit}, payload) => {
      console.log('AC_SIGNUP', payload);
      commit
      axios.post(SERVER.ROUTES.signup, payload)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    //회원 정보 조회
    AC_GET_USERINFO: ({commit}, payload) => {
      const token = payload.token
      const header = {
        Accept: '*/*',
        'X-AUTH-TOKEN': token
      }
      axios.get(SERVER.ROUTES.getUserInfo, header)
      .then( response =>{
        console.log(response);
        localStorage.setItem("access_token", token);
        commit.MU_USER_INFO(response.data);
      })
      .catch( error => {
        console.log(error);
        
      })
    }
  }
}

export default userStore