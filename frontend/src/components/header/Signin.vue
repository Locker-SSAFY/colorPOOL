<template>
<div>
  <v-dialog v-model="dialog" persistent max-width="650px">
    <template v-slot:activator="{ attrs }">
      <v-btn
        icon
        text
        v-bind="attrs"
        @click="AC_DISPLAY(true)"
        class="mr-16"
      >
        <!-- v-on="on" -->
        <div style="line-height: 0.4; margin-top: 2rem; font-weight: 300;">
          <p>Getting</p>
          <p>Started</p>
        </div>
      </v-btn>
    </template>
    <v-card class="signin-modal wrap" :style="{'background-color': backColor  }">
      <v-row>
        <v-col cols="11" id="modal-title">
          <img class="logo_txt" src="../../assets/images/logo/logo_text.png">
        </v-col>
        <v-col cols="1" id="modal-title">
          <v-btn icon text large @click="close">
            <v-icon>mdi-window-close</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row class="underline"></v-row>
      <v-row>
        <v-col cols="2">
          <v-row v-if="showSigninVal">
            <v-btn dark style="width: 14%;" block>
              SIGNIN
            </v-btn>
          </v-row>
          <v-row v-else>
            <v-btn text @click="showSignin" style="width: 14%;" block>
              SIGNIN
            </v-btn>
          </v-row>
          <v-row v-if="showSigninVal">
            <v-btn text @click="showSignup" style="width: 14%;" block>
              SIGNUP
            </v-btn>
          </v-row>
          <v-row v-else>
            <v-btn dark style="width: 14%;" block>
              SIGNUP
            </v-btn>
          </v-row>
        </v-col>
        <v-col cols="10">
          <v-row class="signin-back">
            <!-- signin -->
            <v-col v-if="showSigninVal">
              <v-row id="show-error">{{errorMsg}}</v-row>
              <v-row>
                <div class="textfield-tape">
                  <v-text-field dense v-model="userEmail" placeholder="email" v-on:keyup.enter="signin" required></v-text-field>
                </div>
                <div class="textfield-tape">
                  <v-text-field dense v-model="userPassword" placeholder="password" type="password" v-on:keyup.enter="signin" required></v-text-field>
                </div>
                <v-btn
                  color="black"
                  dark
                  class="mb-2"
                  @click="signin"
                  text
                  style="position: absolute; top: 62%; right: 8%; font-size: 1.3rem;"
                >
                  <p style="font-family: 'PermanentMarker-Regular'; margin-bottom: 0;">> SIGININ</p>
                </v-btn>
              </v-row>
              <v-row>
                <v-btn
                  color="black"
                  icon
                  class="mb-2"
                  @click="handleClickSignIn"
                >
                  <v-icon>mdi-google</v-icon>
                </v-btn>
                <v-btn
                  color="black"
                  icon
                  class="mb-2"
                  @click="AC_KAKAO_SIGNIN"
                >
                  <v-icon>mdi-chat</v-icon>
                </v-btn>
              </v-row>
            </v-col>

            <!-- sign up-->
            <v-col v-else>
              <v-row>
                <div class="provider-desc" v-if="isSocialSignup">
                  <p>Signup with {{provider}}</p>
                </div>
                <div class="textfield-tape">
                  <v-text-field dense v-model="nickname" :rules="nicknameRules" placeholder="nickname" required></v-text-field>
                </div>
                <div class="textfield-tape" v-if="isSocialSignup">
                  <v-text-field dense v-model="email" placeholder="email" disabled></v-text-field>
                </div>
                <div class="textfield-tape" v-if="!isSocialSignup">
                  <v-text-field dense v-model="email" :rules="emailRules" placeholder="email" required></v-text-field>
                </div>
                <div class="textfield-tape">
                  <v-text-field dense v-model="password" :rules="passwordRules" placeholder="password" type="password" required></v-text-field>
                </div>
                <div class="textfield-tape">
                  <v-text-field dense v-model="passwordConfirm" :rules="passwordConfirmRules" placeholder="check password" type="password" required></v-text-field>
                </div>
                <v-btn
                  color="black"
                  dark
                  class="mb-2"
                  @click="signup"
                  text
                  style="position: absolute; top: 87%; right: 7%; font-size: 1.3rem;"
                >
                  <p style="font-family: 'PermanentMarker-Regular'; margin-bottom: 0;">> SIGINUP</p>
                </v-btn>
              </v-row>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-card>
  </v-dialog>
</div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
const colorStore = 'colorStore'
const userStore = 'userStore'

export default {
  computed: {
    ...mapGetters(userStore, { storeIsLogin: 'GE_IS_LOGIN',
                              storeUserInfo: 'GE_USER_INFO',
                              storeIsLoginError: 'GE_IS_LOGIN_ERROR',
                              storeDisplay: 'GE_DISPLAY',
                              storeErrorMsg: 'GE_ERROR',
                              storeShowSingin: 'GE_SHOW_SINGIN',
                              storeSignupInfo: 'GE_SIGNUP_INFO'}),
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR' }),
    //비밀 번호 확인 체크
    passwordConfirmRules() {
      if (this.passwordConfirm != this.passwordConfirm) {
        return "비밀번호가 일치하지 않습니다";
      }
      return [
        () =>
          this.password === this.passwordConfirm ||
          "비밀번호가 일치하지 않습니다",
        v => !!v || "비밀번호 확인을 입력해주세요",
        v =>
          (v && v.length >= 4 && v.length <= 50) ||
          "비밀번호 확인은 최소 4자 최대 50자 입니다"
      ];
    }
  },
  props: {
    name: void 0
  },
  created(){
    const bc = this.storeSelectedColor;
    if(bc === null){
      this.backColor = '#FFFFFF';
    } else {
      this.backColor = this.storeSelectedColor.hex;
    }
    this.dialog = this.storeDisplay;
    this.isLoginError = this.storeIsLoginError;
    this.userInfo = this.storeUserInfo;
    this.errorMsg = this.storeErrorMsg;

    //로그인 처리
    if(localStorage.getItem('access_token') != null){
      this.AC_IS_LOGIN(true);
    } else {
      this.AC_IS_LOGIN(false);
    }
    this.AC_SHOW_SIGNIN(true);
    this.showSigninVal = this.storeShowSingin;
    
    this.email = '';
    this.nickname = '';
    this.provider = '';
    this.password = '';
    this.passwordConfirm = '';
  },
  data(){
    return{
      dialog: false,
      isLogin: false,
      isLoginError: false,
      errorMsg: null,
      userInfo: null,
      userEmail: '',
      userPassword: '',
      showSigninVal: true,
      isSocialSignup: false,
      backColor: '',
      email: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
      provider: "",
      emailRules: [
        v => !!v || "이메일을 입력해주세요",
        v =>
          /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9]+/.test(v) ||
          "이메일 형식에 맞게 입력해주세요"
      ],
      nicknameRules: [
        v => !!v || "닉네임을 입력해주세요",
        v =>
          (v && v.length >= 1 && v.length <= 30) ||
          "닉네임은 최소 1자 최대 30자 입니다"
      ],
      passwordRules: [
        v => !!v || "비밀번호를 입력해주세요",
        v =>
          (v && v.length >= 4 && v.length <= 50) ||
          "비밀번호는 최소 4자 최대 50자 입니다"
      ],
    }
  },
  watch: {
    storeSelectedColor(val){
      if(val != null){
        this.backColor = val.hex;
      }
    },
    storeIsLoginError(val){
      this.isLoginError = val
    },
    storeUserInfo(val){
      this.userInfo = val
    },
    storeDisplay(val){
      this.dialog = val
    },
    storeErrorMsg(val){
      this.errorMsg = val;
    },
    storeShowSingin(val){
      this.showSigninVal = val;
    },
    storeSignupInfo(val){
      this.email = val.email;
      this.nickname = val.nickname;
      this.password = val.password;
      this.passwordConfirm = val.passwordConfirm;
      this.provider = val.provider;
      if(val.provider !== "kakao" && val.provider !== "google"){
        this.isSocialSignup = false;
      } else {
        this.isSocialSignup = true;
      }
    }
  },
  methods: {
    ...mapActions(userStore, ['AC_SIGNIN', 'AC_SIGNUP', 'AC_DISPLAY','AC_KAKAO_SIGNIN','AC_ERROR',
                              'AC_IS_LOGIN', 'AC_SHOW_SIGNIN', 'AC_SIGNUP_INFO', 'AC_SOCIAL_LOGIN']),
    showSignin(){
      this.AC_SHOW_SIGNIN(true);
      var info = {
        email : '', 
        nickname: '',
        password: '',
        provider: '',
      }
      this.AC_SIGNUP_INFO(info);
    },
    showSignup(){
      this.AC_SHOW_SIGNIN(false);
      this.userEmail = '';
      this.userPassword = '';
    },
    signin(){
      const payload = {
        userInfo: {
          email: this.userEmail, 
          password: this.userPassword,
          provider: 'root'
        }
      }
      this.AC_SIGNIN(payload);
    },
    signup(){
      if(this.nickname.length > 0 && this.email.lenght > 0, this.password.length > 0, this.password.length > 0){
        if (!( /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9]+/.test(this.email))){
          alert('이메일 형식을 맞춰주세요!');
        } else if( this.nickname.length < 1 || this.nickname.length > 30){
          alert('닉네임은 최소 1자 최대 30자 입니다');
        } else if( this.password.length < 4 || this.password.length > 50){
          alert('비밀번호는 최소 4자 최대 50자 입니다');
        } else if(this.password !== this.passwordConfirm){
          alert('비밀번호가 일치하지 않습니다');
        }
        else {
          var prov = '';
          if(!this.isSocialSignup){
            prov = 'root'
          } else {
            if(this.provider == 'kakao'){
              prov = 'kakao'
            } else {
              prov = 'google'
            }
          }
          const payload = {
            nickname: this.nickname,
            userInfo: {
              email: this.email, 
              password: this.password,
              provider: prov
            }
          }
          this.AC_SIGNUP(payload);
        }
      } else {
        alert('모든 내용을 다 적어주세요!');
      }
    },
    close(){
      this.AC_ERROR(null);
      this.showSignin;
      this.AC_DISPLAY(false);
    },

    //구글 로그인
    async handleClickSignIn() {
      try {
        const googleUser = await this.$gAuth.signIn();
        let token = googleUser.getAuthResponse().access_token;
        console.log("google_token : ", token);
        localStorage.setItem("google_token",token);
        this.isSignIn = this.$gAuth.isAuthorized;

        const payload = {
          accessToken: token,
          provider: 'google'
        }
        this.AC_SOCIAL_LOGIN(payload);
      } catch (error) {
        console.error(error);
        alert("구글 로그인 도중 문제가 발생했습니다!", error);
      }
    },
  }
}
</script>
<style scoped>
  .v-dialog .signin-modal.wrap .signin-back{
    height: 280px;
    margin-bottom: 30px;
  }

  .provider-desc{
    padding: 0% 0% 0% 5%;
    width: 100%;
    font-size: 1.8rem;
    font-weight: bolder;
    height: 40px;
  }

  .provider-desc p{
    font-family: 'PermanentMarker-Regular';
  }

  .textfield-tape {
    margin-bottom: 11px;
    background-color: white;
    padding: 1% 5% 0% 7%;
    width: 100%;
    height: 45px;
    mask-image: url(https://cdn2.bustle.com/nylon/2020/tape_b-80d71c228a.svg);
    mask-repeat: no-repeat;
    mask-size: 100% 100%;
    background-image: url(https://imgix.bustle.com/uploads/image/2020/3/10/428e5a38-91a6-4f27-8187-e0c507694930-tape_poster.png);
    background-size: cover;
    background-repeat: no-repeat;
    background-blend-mode: difference;
  }

  .v-dialog .signin-modal.wrap .underline{
    background-color: black;
    height: 0.8px;
    width: 95%; 
    margin-bottom: 2%;
  }

  #modal-title{
    margin-top: 1.5%;
    margin-bottom: 1%;
    height: 60px;
    font-size: 2.5rem;
    font-weight: bold;
  }

  #singin-title{
    font-size: 2rem;
  }

  #show-error{
    color: red;
    font-weight: border;
    font-size: 1rem;
    height: 5%;
    margin-bottom: 15px; 
  }

  #horizon-line{
    background-color: #EF5350;
    position: absolute;
    left: 60%;
    top: 20%;
    height: 65%;
    width: 1px;
  }

  .v-dialog .signin-modal.wrap .row{
    margin-left: 3%;
    margin-right: 3%;
  }

  .v-dialog .signin-modal.wrap .col{
    margin: 0%;
    padding: 0%;
  }

  .logo_txt {
    margin-top: 1.2rem;
    margin-left: 0.3rem;
    height: 3rem;
  }

</style>
