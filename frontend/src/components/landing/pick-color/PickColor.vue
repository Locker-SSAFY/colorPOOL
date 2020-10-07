<template>
  <div class="pick-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    <div class="underline"></div>
    <div class="pick-desc">
      <p>Pick your color from colorPOOL</p>
    </div>
    <!-- Landing page의 pickColor 화면 -->
    <v-card @click="clickPick()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isPick == false && this.$parent.isGet == false"
    >
      <p>PICK color</p>
        <div style="overflow-x: hidden; height: 90%;">
          <img id="pick_img" src="../../../assets/images/colorimg.png">
        </div>
        <!-- <v-card-text style="position: absolute; bottom: 0; color: black; font-weight: 600; font-size: 18px;">
          Pick Your Color From Palette
        </v-card-text> -->
    </v-card>
    <!-- 컬러 팔레트 -->
    <ColorPalette v-if="this.$parent.isPick"></ColorPalette>

    <!-- 오른쪽 배경 -->
    <div v-if="this.$parent.isPick" class="pick-color right" v-bind:style="{'background-color' : selectedColor.hex}">
      <!-- <img :src="imgUrl"> -->
      <img id="selected_img" :src="require(`@/assets/images/colorimages/${folder}/${pos}.jpg`)">
      <!-- 배색 추천 받으러 가기 버튼 -->
      <v-btn
        class="next-button"
        icon
        text
        @click="getTheme"
      >
        <v-icon size="100">mdi-arrow-right</v-icon>
      </v-btn>
    </div>
    
    <!-- 배색 추천 화면 -->
    <div class="bottom-page" ref="messageDisplay">
      <RecommendTheme></RecommendTheme>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import ColorPalette from './ColorPalette'
import RecommendTheme from '../recommend-theme/RecommendTheme'
import materialColors from '../../../assets/color/colorList.js'
const colorStore = 'colorStore'

export default {
  name: 'PickColor',
  components: {
    ColorPalette,
    RecommendTheme
  },
  created(){
    this.selectedColor = this.storeSelectedColor.hex;
    this.folder = parseInt((this.storeSelectedColor.id) / 10) + 1;
    this.pos = (Number(this.storeSelectedColor.id) % 10);
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  data () {
    return {
      materialColors : materialColors,
      selectedColor: '',
      selectedVariation: [],
      folder: '',
      pos: '',
    }
  },
  watch: {
    storeSelectedColor(val){
      this.selectedColor = val;
      this.folder = parseInt((this.selectedColor.id) / 10) + 1;
      this.pos = (Number(this.selectedColor.id) % 10);
    }
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR', 'AC_THEMES']),
    clickPick() {
      this.$parent.isPick = true;
      this.$parent.isGet = false;
      const payload = { selectedColor: this.materialColors[0].variations[4]};
      this.AC_SELECTED_COLOR(payload);
    },
    getTheme(){
      const payload = this.selectedColor;
      console.log('pick color payload', payload);
      this.AC_THEMES(payload)
      document.body.className = "unlock";
      console.log(document.body);
      window.scrollTo({left: 0,top: 1000, behavior: 'smooth'});
    }
  }
}
</script>

<style scoped>
  @font-face {
    font-family: 'ReenieBeanie-Regular';
    src: url('../../../assets/font/ReenieBeanie-Regular.ttf');
  }

  .pick-color.wrap {
    width: 50%;
    height: 100%;
    /* background-color: skyblue; */
    float: left;
    transition-duration: 300ms;
    display: flex;
    align-items: center;
  }

  .pick-color.wrap .underline{
    background-color: black;
    position: absolute;
    top: 30%;
    left: 5%;
    height: 0.5px;
    width: 40%;
  }

  .pick-color.wrap .pick-desc {
    text-align: right;
    position: absolute;
    top: 32%;
    right: 55%;
    font-size: 2.1rem;
    line-height: 0.7;
  }

  .pick-color.wrap .pick-desc p {
    font-family: 'ReenieBeanie-Regular';
  }

  .pick-color.wrap.active {
    width: 100%;
    max-height: 100%;
  }

  .pick-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .pick-color.wrap .v-card {
    position: absolute;
    left: 5%;
    width: 40%;
    height: 50%;
    margin-top: 15%;
  }

  .pick-color.wrap .v-card p {
    font-size: 2rem;
    font-weight: bold;
    text-align: center;
    margin-top: 1rem;
  }

  .pick-color.wrap .v-card .v-image {
    height: 90%;
  }

  .pick-color.right {
    width: 30%;
    height: 100%;
    position: absolute;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #selected_img {
    position: absolute;
    max-width: 90%;
    max-height: 80%;
    object-fit: contain;
    right: 50%;
  }


  #pick_img {
    height: 90%;
    transform-origin: left;
    transition-duration: 10s;
  }
  
  .pick-color.wrap .v-card:hover #pick_img {
    transform: translateX(calc(-100% + 550px));
  }

  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
  }

</style>