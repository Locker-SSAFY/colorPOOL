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
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import GetColor from '../components/landing/get-color/GetColor'
import PickColor from '../components/landing/pick-color/PickColor'
const colorStore = 'colorStore';
const landingStore = 'landingStore';

export default {
  components: {
    GetColor, PickColor,
  },
  data() {
    return {
      isPick: null,
      isGet: null,
      isLanding: null,
      selectedColor: ''
    }
  },
  created() {
    this.isPick = this.storeIsPick;
    this.isGet = this.storeIsGet;
    this.isLanding = this.storeIsLanding
    this.AC_IS_LANDING({isLanding: true});
    window.scrollTo({left: 0,top: 0});
    this.AC_SELECTED_COLOR({selectedColor: null});
    this.AC_SELECTED_THEME({selectedTheme: null});
  },
  computed: {
    ...mapGetters(landingStore, {storeIsGet: 'GE_IS_GET', storeIsPick: 'GE_IS_PICK', storeIsLanding: 'GE_IS_LANDING'})
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR','AC_SELECTED_THEME']),
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK', 'AC_IS_LANDING']),
    goBack() {
      this.isPick= false;
      this.isGet = false;
      const payload = {selectedColor: null};
      this.AC_SELECTED_COLOR(payload);
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
    isLanding(val){
      this.isLanding = val;
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
</style>