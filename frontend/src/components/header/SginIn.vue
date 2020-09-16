<template>
<div>
  <v-dialog v-model="dialog" persistent max-width="1000px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        icon
        text
        v-bind="attrs"
        v-on="on"
      >
      SIGNIN</v-btn>
    </template>
    <v-card class="signin-modal wrap" :style="{'background-color': backColor  }">
      <v-row>
        <v-col cols="11" id="modal-title">COLORPOOL</v-col>
        <v-col cols="1" id="modal-title">
          <v-btn icon text large @click="dialog=false">
            <v-icon>mdi-window-close</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-row>
            <v-btn icon text @click="showSignin">
              SIGNIN
            </v-btn>
          </v-row>
          <v-row>
            <v-btn icon text @click="showSignup">
              SIGNUP
            </v-btn></v-row>
        </v-col>
        <v-col cols="10">
          <v-row class="signin-back">
            <!-- signin -->
            <v-col v-if="showSigninVal">
              <v-row id="singin-title">SIGNIN</v-row>
              <v-row>
                <v-text-field v-model="userEmail" label="email" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="userPassword" label="password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-btn
                  block
                  color="black"
                  dark
                  class="mb-2"
                  @click="signin"
                >
                SIGININ
                </v-btn>
                <v-btn
                  block
                  color="red"
                  dark
                  class="mb-2"
                >
                GOOGLE
                </v-btn>
                <v-btn
                  block
                  color="yellow"
                  dark
                  class="mb-2"
                >
                KAKAOTALK
                </v-btn>
              </v-row>
            </v-col>
            <!-- sign up-->
            <v-col v-else>
              <v-row id="singin-title">SIGNUP</v-row>
              <v-row>
                <v-text-field v-model="nickName" :couter="30" :rules="nickNameRules" label="nickname" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="email" :rules="emailRules" label="email" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="password" :counter="50" :rules="passwordRules" label="password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field v-model="passwordConfirm" :counter="50" :rules="passwordConfirmRules" label="check password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-btn
                  block
                  color="black"
                  dark
                  class="mb-2"
                  @click="signup"
                >
                SIGINUP
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
// import axios from '../../api/axiosCommon'
// import SERVER from '../../api/restApi'
import { mapGetters, mapActions } from 'vuex'
const colorStore = 'colorStore'
const userStore = 'userStore'

export default {
  computed: {
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR', storeIsLogin: 'GE_IS_LOGIN', storeUserInfo: 'GE_USER_INFO'}),
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
  created(){
    this.backColor = this.storeSelectedColor;
    this.isLogin = this.storeIsLogin;
    this.userInfo = this.storeUserInfo;
  },
  data(){
    return{
      dialog: false,
      isLogin: false,
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
  },
  methods: {
    ...mapActions(userStore, ['AC_SIGNIN', 'AC_SIGNUP']),
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
      this.AC_SIGNUP(payload);
    }
  }
}
</script>
<style scoped>
  .v-dialog .signin-modal.wrap .signin-back{
    height: 400px;
    margin-bottom: 30px;
    background-color: white
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