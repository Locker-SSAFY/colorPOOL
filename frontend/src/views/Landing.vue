<template>
  <div class="landing wrap">
    <div class="top-page">
      <img @click="goBack()" v-if="isPick || isGet" class="back-arrow" src="../assets/icon/arrow.png">
      <PickColor></PickColor>
      <GetColor></GetColor>
    </div>
    <div class="bottom-page" ref="messageDisplay">

    </div>
  </div>
</template>

<script>
import GetColor from '../components/landing/get-color/GetColor'
import PickColor from '../components/landing/pick-color/PickColor'

export default {
  components: {
    GetColor, PickColor
  },
  data() {
    return {
      isPick: null,
      isGet: null,
    }
  },
  created() {
    
  },
  methods : {
    goBack() {
      this.isPick = null;
      this.isGet = null;
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
</style>