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
      SIGNIN/SIGNUP</v-btn>
    </template>
    <v-card class="signin-modal wrap" :style="{'background-color': backColor  }">
      <v-row>
        <v-col cols="11" id="modal-title">COLORPOOL</v-col>
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
            <v-btn dark @click="showSignin" style="width: 14%;">
              SIGNIN
            </v-btn>
          </v-row>
          <v-row v-else >
            <v-btn text @click="showSignin" style="width: 14%;">
              SIGNIN
            </v-btn>
          </v-row>
          <v-row v-if="showSigninVal">
            <v-btn text @click="showSignup" style="width: 14%;" >
              SIGNUP
            </v-btn>
          </v-row>
          <v-row v-else>
            <v-btn dark @click="showSignup" style="width: 14%;">
              SIGNUP
            </v-btn>
          </v-row>
        </v-col>
        <v-col cols="10">
          <v-row class="signin-back">
            <!-- signin -->
            <v-col v-if="showSigninVal">
              <!-- <v-row id="singin-title">SIGNIN</v-row> -->
              <v-row id="show-error">{{errorMsg}}</v-row>
              <v-row>
                <img src="../../assets/images/tape.svg" style="position: absolute; top:30%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:31%; left: 26%; width: 63%" id="email-text" v-model="userEmail" placeholder="email" required></v-text-field>
                <img src="../../assets/images/tape.svg" style="position: absolute; top:44%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:45%; left: 26%; width: 63%" v-model="userPassword" placeholder="password" type="password" required></v-text-field>
                <v-btn
                  color="black"
                  dark
                  class="mb-2"
                  @click="signin"
                  text
                  style="position: absolute; top: 58%; right: 8%; font-size: 1.3rem;"
                >
                > SIGININ
                </v-btn>
              </v-row>
              <v-row style="position: absolute; top: 60%;">
                <v-btn
                  color="black"
                  icon
                  class="mb-2"
                  @click="handleClickSignIn"
                >
                <v-icon class="mr-2">mdi-google</v-icon>
                </v-btn>
                <v-btn
                  color="black"
                  icon
                  class="mb-2"
                  @click="AC_KAKAO_SIGNIN"
                >
                <v-icon class="mr-2">mdi-chat</v-icon>
                </v-btn>
              </v-row>
            </v-col>

            <!-- sign up-->
            <v-col v-else>
              <!-- <v-row id="singin-title">SIGNUP</v-row> -->
              <v-row>
                <!-- <v-text-field v-model="nickName" :couter="30" :rules="nickNameRules" label="nickname" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="email" :rules="emailRules" label="email" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="password" :counter="50" :rules="passwordRules" label="password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="passwordConfirm" :counter="50" :rules="passwordConfirmRules" label="check password" type="password" required></v-text-field> -->
                <img src="../../assets/images/tape.svg" style="position: absolute; top:25%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:26%; left: 26%; width: 63%" id="email-text" v-model="nickname" placeholder="nickname" required></v-text-field>
                <img src="../../assets/images/tape.svg" style="position: absolute; top:39%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:40%; left: 26%; width: 63%" v-model="email" :rules="emailRules" placeholder="email" required></v-text-field>
                <img src="../../assets/images/tape.svg" style="position: absolute; top:53%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:54%; left: 26%; width: 63%" id="email-text" v-model="password" :rules="passwordRules" placeholder="password" type="password" required></v-text-field>
                <img src="../../assets/images/tape.svg" style="position: absolute; top:67%; left: 22%; height: 45px; width: 70%;">
                <v-text-field dense style="position: absolute; top:68%; left: 26%; width: 63%" v-model="passwordConfirm" :rules="passwordConfirmRules" placeholder="check password" type="password" required></v-text-field>
                <v-btn
                  color="black"
                  dark
                  class="mb-2"
                  @click="signup"
                  text
                  style="position: absolute; top: 82%; right: 8%; font-size: 1.3rem;"
                >
                > SIGINUP
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
import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'
const colorStore = 'colorStore'
const userStore = 'userStore'

export default {
  computed: {
    ...mapGetters(userStore, { storeIsLogin: 'GE_IS_LOGIN',
                              storeUserInfo: 'GE_USER_INFO',
                              storeIsLoginError: 'GE_IS_LOGIN_ERROR',
                              storeDisplay: 'GE_DISPLAY',
                              storeErrorMsg: 'GE_ERROR'}),
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
    this.backColor = this.storeSelectedColor;
    this.dialog = this.storeDisplay;
    this.isLogin = this.storeIsLogin;
    this.isLoginError = this.storeIsLoginError;
    this.userInfo = this.storeUserInfo;
    this.errorMsg = this.storeErrorMsg;
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
      backColor: '',
      email: "",
      emailRules: [
        v => !!v || "이메일을 입력해주세요",
        v =>
          /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9]+/.test(v) ||
          "이메일 형식에 맞게 입력해주세요"
      ],
      nickName: "",
      nickNameRules: [
        v => !!v || "닉네임을 입력해주세요",
        v =>
          (v && v.length >= 1 && v.length <= 30) ||
          "닉네임은 최소 1자 최대 30자 입니다"
      ],
      password: "",
      passwordRules: [
        v => !!v || "비밀번호를 입력해주세요",
        v =>
          (v && v.length >= 4 && v.length <= 50) ||
          "비밀번호는 최소 4자 최대 50자 입니다"
      ],
      passwordConfirm: ""
    }
  },
  watch: {
    storeSelectedColor(val){
      this.backColor = val
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
    }
  },
  methods: {
    ...mapActions(userStore, ['AC_SIGNIN', 'AC_SIGNUP', 'AC_DISPLAY','AC_KAKAO_SIGNIN','AC_ERROR']),
    showSignin(){
      this.showSigninVal = true;
    },
    showSignup(){
      this.showSigninVal = false;
      console.log(this.showSigninVal);
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
      const payload = {
        nickname: this.nickName,
        userInfo: {
          email: this.email, 
          password: this.password,
          provider: 'root'
        }
      }
      // this.AC_SIGNUP(payload);
      
      axios.post(SERVER.ROUTES.signup, payload)
      .then(function (response) {
        console.log(response);
        alert('회원가입 성공!');
      })
      
      this.showSigninVal = true;
      this.nickName = '',
      this.email = '',
      this.password = '',
      this.passwordConfirm = ''
    },
    close(){
      this.AC_ERROR(null);
      this.userEmail = '';
      this.userPassword = '';
      this.AC_DISPLAY(false);
    },

    //구글 로그인
    async handleClickSignIn() {
      try {
        const googleUser = await this.$gAuth.signIn();
        let token = googleUser.getAuthResponse().access_token;
        console.log(
          "google - access_token : ",
          googleUser.getAuthResponse().access_token
        );
        this.isSignIn = this.$gAuth.isAuthorized;
        token;
        // this.signinWithSocial({ access_token: token, provider: this.google });
      } catch (error) {
        console.error(error);
        // alert("구글 로그인 도중 문제가 발생했습니다!", error);
      }
    },
  }
}
</script>
<style scoped>
  .v-dialog .signin-modal.wrap .signin-back{
    height: 250px;
    margin-bottom: 30px;
    /* background-color: white */
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
</style>