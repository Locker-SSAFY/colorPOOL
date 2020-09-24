<template>
  <div class="header wrap">
    <v-row class="mx-16">
      <v-col class="header-logo" cols="7">
        ColorPOOL
      </v-col>
      <v-col cols="5" class="header-signin" v-if="userInfo == null && !isLogin">
        <Signin></Signin>
      </v-col>
      <v-col cols="2" style="text-align: center;" class="mt-2" v-if="isLogin">
        <!-- {{userInfo.nickname}}님, 반갑습니다! -->
      </v-col>
      <v-col cols="1" class="header-library" v-if="isLogin">
        <v-btn icon text>
          LIBRARY
        </v-btn>
      </v-col>
      <v-col cols="1" class="header-mypage" v-if="isLogin">
        <v-btn icon text>
          MYPAGE
        </v-btn>
      </v-col>
      <v-col cols="1" class="header-library" v-if="isLogin">
        <v-btn icon text @click="logout">
          LOGOUT
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import Signin from './Signin'
const userStore = 'userStore'
const landingStore = 'landingStore'

export default {
  components: {
    Signin
  },
  computed: {
    ...mapGetters(userStore, { storeIsLogin: 'GE_IS_LOGIN',
                              storeUserInfo: 'GE_USER_INFO'}),
    ...mapGetters(landingStore, { storeIsLanding: 'GE_IS_LANDING'})
  },
  created(){
    this.userInfo = this.storeUserInfo;
    this.isLogin = this.storeIsLogin;
    this.isLanding = this.storeIsLanding;
  },
  data() {
    return {
      scrolled: false,
      // dialog: false,
      userInfo: null,
      isLogin: false,
      isLanding: false,
    }
  },
  watch: {
    storeUserInfo(val){
      this.userInfo = val;
    },
    storeIsLogin(val){
      this.isLogin = val;
    },
    storeIsLanding(val){
      this.isLanding = val;
    }
  },
  methods: {
    ...mapActions(landingStore, ['AC_IS_GET','AC_IS_PICK', 'AC_IS_LANDING']),
    ...mapActions(userStore, ['AC_LOGOUT']),
    detectWindowScrollY() {
      // 감지 이벤트 메서드
      this.scrolled = window.scrollY > 0
    },
    goHome() {
      console.log(this);
      this.$router.push({ name: 'Landing' })
    },
    logout(){
      var result = confirm("정말 로그아웃 하시겠어요?");
      if(result){
          this.userInfo = null;
          this.AC_LOGOUT(null);
          localStorage.removeItem('access_token');
          localStorage.removeItem('kakao_token');
          localStorage.removeItem('google_token');
          if(!this.isLanding){
            this.AC_IS_GET({isGet: false});
            this.AC_IS_PICK({isPick: false});
            this.$router.push({name: 'Landing'});
          }
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', this.detectWindowScrollY)
  },
  beforeDestory() {
    window.removeEventListener('scroll', this.detectWindowScrollY)
  }
}
</script>

<style>
  .header.wrap {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 80px;
    transition-duration: 300ms;
    z-index: 90;
    background-color: rgb(255, 255, 255, 0.8);
  }

  .header.wrap table {
    width: 90%;
    margin-left: 5%;
    line-height: 3;
  }

  .header.wrap .header-logo {
    /* color: white; */
    font-size: 25px;
    width: 60%;
    font-weight: bolder;
  }

  .header.wrap .header-library {
    /* color : white; */
    text-align: center;
  }

  .header.wrap .header-library .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signin {
    text-align: right;
  }
  
  .header.wrap .header-signin .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signup {
    text-align: center;
  }

  .header.wrap .header-signup .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-mypage {
    text-align: center;
  }

  .header.wrap .header-mypage .v-btn{
    font-size: 1.2rem;
  }

</style>