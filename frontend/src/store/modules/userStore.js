
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
    //로그인 창 안에 입력되는 정보
    showSigninVal: true, //signIn을 보여줄지, signup을 보여줄지
    // nickName: '',
    signupInfo: {
      email : '', 
      nickname: '',
      password: '',
      passwordConfirm: '',
      provider: ''
    },
    // email: '',
    // password: '',
    // provider: '', // 일반 : root, 카카오: kakao, 구글 : google
  },
  getters:{
    GE_USER_INFO: state => state.userInfo,
    GE_IS_LOGIN: state => state.isLogin,
    GE_IS_LOGIN_ERROR: state => state.isLoginError,
    GE_DISPLAY: state => state.modalDisplay,
    GE_ERROR: state => state.errorMsg,
    GE_SHOW_SINGIN: state => state.showSigninVal,
    GE_SIGNUP_INFO: state => state.signupInfo,
  },
  mutations: {
    MU_USER_INFO: (state, payload) => {
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
    }, 
    MU_SHOW_SIGNIN: (state, payload) => {
      state.showSigninVal = payload
    },
    MU_SIGNUP_INFO: (state, payload) => {
      state.signupInfo = payload
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
        // console.log(response);
        commit('MU_IS_LOGIN_ERROR', false);
        commit('MU_IS_LOGIN', true);
        commit('MU_ERROR', null);
        dispatch('AC_GET_USERINFO', {token: response.data.data});
      })
      .catch(function (error) {
        // console.log(error.response);
        if(error.response.data.success === false ){
          commit('MU_ERROR', error.response.data.msg);
        } 
        commit('MU_IS_LOGIN_ERROR', true);
      });
    },

    //카카오톡 로그인
    AC_KAKAO_SIGNIN: ({dispatch}) => {
      console.log('AC_KAKAO_SIGNIN')
      window.Kakao.Auth.login({
        success: function(authObj) {
          console.log(authObj)
          const payload = {
            accessToken: authObj.access_token,
            provider: 'kakao'
          }
          dispatch('AC_SOCIAL_LOGIN', payload);
        },
        fail: function(err) {
          alert("fail", JSON.stringify(err));
          console.log("kakao login - err : ", err);
        },
      });
    },

    //소셜로그인
    AC_SOCIAL_LOGIN: ({commit, dispatch}, payload) => {
      console.log('AC_SOCIAL_LOGIN', payload);
      commit;

      var info = {
        email : '', 
        nickname: '',
        password: '',
        provider: payload.provider
      }

      console.log('login - provider 확인 :', info.provider)

      axios.post(SERVER.ROUTES.socialSignin, payload)
      .then(res => {
        var token = res.data.data.token;
        console.log('social - res', res);
        if(token == null){ //token이 null이면 회원가입 유도
          // console.log('social - res.data.data.token', res.data.data.token);
          // console.log('social - res.data.data.userInfo', res.data.data.userInfo);
          var user = res.data.data.userInfo;
          info.email = user.email;
          info.nickname = res.data.data.nickname;
          commit('MU_SIGNUP_INFO', info);
          commit('MU_SHOW_SIGNIN', false); 
        } else {
          // console.log("token ->", res.data.data.token);
          //소셜 로그인 진행
          dispatch('AC_GET_USERINFO', {token: res.data.data.token});
        }
      })
      .catch(err => {
        console.log('social - err', err);
        console.log('social - err', err.response);
      })
    },

    //회원가입
    AC_SIGNUP: ({commit}, payload) => {
      axios.post(SERVER.ROUTES.signup, payload)
      .then(response => {
        console.log(response);
        alert('회원가입 성공!');
        var info = {
          email : '', 
          nickname: '',
          password: '',
          passwordConfirm: '',
          provider: ''
        }
        commit('MU_SIGNUP_INFO', info);
        commit('MU_SHOW_SIGNIN', true);
      })
      .catch(err =>{
        alert(err.response.data.msg);
      })
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
      // axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/user?lang=ko', { headers: header })
      axios.get(SERVER.ROUTES.getUserInfo, { headers: header })
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

    //로그인 상태 확인
    AC_IS_LOGIN: ({commit}, payload) => {
      commit('MU_IS_LOGIN', payload);
    },

    //show signin 값 변경
    AC_SHOW_SIGNIN: ({commit}, payload)=>{
      commit('MU_SHOW_SIGNIN', payload);  
    }, 

    //회원가입 정보
    AC_SIGNUP_INFO: ({commit}, payload) => {
      commit('MU_SIGNUP_INFO', payload);
    },
  }
}

export default userStore