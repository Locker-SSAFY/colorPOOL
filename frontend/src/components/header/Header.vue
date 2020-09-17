<template>
  <div class="header wrap">
    <v-row class="mx-16">
      <v-col class="header-logo" cols="10">
        ColorPOOL
      </v-col>
      <v-col cols="1" class="header-signin" v-if="userInfo == null">
        <Signin></Signin>
      </v-col>
      <v-col cols="1" class="header-signup" v-if="userInfo == null">
        <v-btn icon text>
          SIGNUP
        </v-btn>
      </v-col>
      <v-col cols="1" class="header-library" v-if="userInfo != null">
        <v-btn icon text>
          LIBRARY
        </v-btn>
      </v-col>
      <v-col cols="1" class="header-signin" v-if="userInfo != null">
        <v-btn icon text @click="logout">
          LOGOUT
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Signin from './SginIn'
const userStore = 'userStore'

export default {
  components: {
    Signin
  },
  computed: {
    ...mapGetters(userStore, { storeIsLogin: 'GE_IS_LOGIN',
                              storeUserInfo: 'GE_USER_INFO'}),
  },
  created(){
    this.userInfo = this.storeUserInfo;
  },
  data() {
    return {
      scrolled: false,
      dialog: false,
      userInfo: null
    }
  },
  watch: {
    storeUserInfo(val){
      this.userInfo = val;
    }
  },
  methods: {
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
      }
    }
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
    text-align: right;
  }

  .header.wrap .header-library .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signin {
    text-align: center;
  }
  
  .header.wrap .header-signin .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signup {
    text-align: left;
  }

  .header.wrap .header-signup .v-btn{
    font-size: 1.2rem;
  }
</style>