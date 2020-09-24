<template>
  <div class="landing wrap">
    <div class="top-page">

      <!-- 이전으로 돌아가기 버튼 -->
      <v-btn
        class="goback-button"
        icon
        text
        @click="goBack"
        v-if="isPick || isGet"
      >
        <v-icon size="100">mdi-arrow-left</v-icon>
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
  },
  computed: {
    ...mapGetters(landingStore, {storeIsGet: 'GE_IS_GET', storeIsPick: 'GE_IS_PICK', storeIsLanding: 'GE_IS_LANDING'})
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK', 'AC_IS_LANDING']),
    goBack() {
      this.isPick= false;
      this.isGet = false;
      const payload = {selectedColor: ''};
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
  .landing.wrap {
    width: 100%;
    height: 100%;
  }

  .landing.wrap .top-page {
    width: 100%;
    height: 100%;
    display: flex;
  }

  .landing.wrap .top-page .back-arrow {
    position: absolute;
  }

  .landing.wrap .bottom-page {
    width: 100%;
    height: 100%;
  }

  .goback-button{
    position: absolute;
    top: 13%;
    left: 4%;
    z-index: 50;
  }
</style>