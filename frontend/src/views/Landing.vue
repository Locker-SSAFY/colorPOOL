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
import { mapActions } from 'vuex';
import GetColor from '../components/landing/get-color/GetColor'
import PickColor from '../components/landing/pick-color/PickColor'
const colorStore = 'colorStore';

export default {
  components: {
    GetColor, PickColor,
  },
  data() {
    return {
      isPick: null,
      isGet: null,
      selectedColor: ''
    }
  },
  created() {
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    goBack() {
      this.isPick = null;
      this.isGet = null;
      const payload = {selectedColor: ''};
      this.AC_SELECTED_COLOR(payload);
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
    /* align-items: baseline; */
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