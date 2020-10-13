<template>
  <div class="landing wrap">
    <div class="top-page">
      <div class="background"></div>
      <div class="landing-logo" v-if="isLanding && isGet == false && isPick == false">
        <img class="logo_img" src="../assets/images/logo/logo_img.png">
        <img class="logo_txt" src="../assets/images/logo/logo_text.png">
        <p class="landing-desc">Create the perfect palette with</p>
      </div>
      
      <!-- 이전으로 돌아가기 버튼 -->
      <v-btn
        class="goback-button"
        icon
        text
        @click="goBack"
        v-if="isPick || isGet"
      >
        <v-icon size="40">mdi-arrow-left</v-icon>
      </v-btn>

      <PickColor></PickColor>
      <GetColor></GetColor>

      <!-- 배색 추천 받으러 가기 버튼 -->
      <v-btn
        class="next-button"
        icon
        text
        @click="getTheme"
      >
        <v-icon size="80">mdi-arrow-right</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import GetColor from '../components/landing/get-color/GetColor'
import PickColor from '../components/landing/pick-color/PickColor'
const colorStore = 'colorStore';
const landingStore = 'landingStore';
const userStore = 'userStore';

export default {
  components: {
    GetColor, PickColor,
  },
  data() {
    return {
      isPick: null,
      isGet: null,
      isLanding: null,
      selectedColor: '',
      isLogin: false,
    }
  },
  created() {
    this.isPick = false;
    this.isGet = false;
    this.isLanding = this.storeIsLanding
    this.AC_IS_LANDING({isLanding: true});
    window.scrollTo({left: 0,top: 0});
    this.AC_SELECTED_COLOR({selectedColor: null});
    this.AC_SELECTED_THEME({selectedTheme: null});
    this.isLogin = this.storeIsLogin;
  },
  computed: {
    ...mapGetters(landingStore, {storeIsGet: 'GE_IS_GET', storeIsPick: 'GE_IS_PICK', storeIsLanding: 'GE_IS_LANDING'}),
    ...mapGetters(userStore, {storeIsLogin: 'GE_IS_LOGIN'})
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR','AC_SELECTED_THEME', 'AC_THEMES']),
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK', 'AC_IS_LANDING']),
    ...mapActions(userStore, ['AC_IS_LOGIN', 'AC_DISPLAY']),
    goBack() {
      this.isPick= false;
      this.isGet = false;
      const payload = {selectedColor: null};
      this.AC_SELECTED_COLOR(payload);
    },
    getTheme(){
      const payload = this.selectedColor;
      if(this.isLogin){
        this.AC_THEMES(payload);
        window.scrollTo({left: 0,top: 1000, behavior: 'smooth'});
      } else {
        alert('배색을 추천받으려면 로그인을 먼저해주세요!');
        this.AC_DISPLAY(true);
      }
    }
  },
  watch: {
    isPick(val) {
      this.isPick = val;
      this.AC_IS_PICK({isPick: val})
    },
    isGet(val) {
      this.isGet = val
      this.AC_IS_GET({isGet: val})
    },
    storeIsLanding(val){
      this.isLanding = val;
    },
    storeIsLogin(val){
      this.isLogin = val;
    }
  }
}
</script>

<style scoped>
  @font-face {
    font-family: 'PermanentMarker-Regular';
    src: url('../assets/font/PermanentMarker-Regular.ttf');
  }

  .landing.wrap {
    width: 100%;
    height: 100%;
  }

  .landing.wrap .top-page {
    width: 100%;
    height: 100%;
    display: flex;
  }

  .landing.wrap .top-page .background {
    width: 100%;
    height: 77%;
    position: absolute;
    background-color: #eee;
    background-image: url("../assets/images/layer.png");
    background-repeat: repeat;
  }

  .landing.wrap .top-page .landing-logo {
    display: block;
    text-align: center;
    position: absolute;
    margin: 0 auto;
    top: 7rem;
    width: 100%;
    /* line-height: 0.5rem; */
  }

  .landing.wrap .top-page .landing-logo .logo_img {
    width: 4.5rem;
    height: 4.5rem;
    margin-bottom: 1rem;
  }

  .landing.wrap .top-page .landing-logo .logo_txt {
    margin-left: 1rem;
    height: 5.5rem;
  }

  .landing.wrap .top-page .landing-logo .landing-desc {
    font-family: 'PermanentMarker-Regular';
    font-size: 1.7rem;
  }

  /* .landing.wrap .top-page .landing-desc {
    font-family: PermanentMarker-Regular;
    font-size: 1.7rem;
    position:relative;
  } */

  .landing.wrap .top-page .back-arrow {
    position: absolute;
  }

  .landing.wrap .bottom-page {
    width: 100%;
    height: 100%;
  }

  .goback-button{
    position: absolute;
    top: 15.5%;
    left: 4%;
    z-index: 50;
  }
  

  .next-button {
    position: absolute;
    right: 5%;
    top: 85%;
  }
</style>