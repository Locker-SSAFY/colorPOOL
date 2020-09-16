<template>
  <div class="pick-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    
    <!-- Landing page의 pickColor 화면 -->
    <v-card @click="clickPick()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isPick == null"
    >
      <v-card-title>Pick COLOR</v-card-title>
      
      <v-img
        class="white--text align-end"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>Pick Your Color from Palette</v-card-title>
      </v-img>
    </v-card>

    <!-- 컬러 팔레트 -->
    <ColorPalette v-if="this.$parent.isPick"></ColorPalette>
    <div v-if="this.$parent.isPick" class="pick-color right" v-bind:style="{'background-color' : selectedColor}">
      <img src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
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
import { mapGetters } from 'vuex'
import ColorPalette from './ColorPalette'
import RecommendTheme from '../recommend-theme/RecommendTheme'
const colorStore = 'colorStore'

export default {
  name: 'PickColor',
  components: {
    ColorPalette,
    RecommendTheme
  },
  created(){
    this.selectedColor = this.storeSlectedColor;
  },
  computed: {
    ...mapGetters(colorStore, {storeSlectedColor: 'GE_SELECTED_COLOR'})
  },
  data () {
    return {
      selectedColor: '',
      selectedVariation: [],
    }
  },
  watch: {
    storeSlectedColor(val){
      this.selectedColor = val
    }
  },
  methods : {
    clickPick() {
      this.$parent.isPick = true;
      this.$parent.isGet = false;
    },
    getTheme(){
      document.body.className = "unlock";
      console.log(document.body);
      window.scrollTo({left: 0,top: 1000, behavior: 'smooth'});
    }
  }
}
</script>

<style scoped>
  .pick-color.wrap {
    width: 50%;
    height: 100%;
    /* background-color: skyblue; */
    float: left;
    transition-duration: 300ms;
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

  .pick-color.wrap .v-card .v-card-title {
    height: 10%;
  }

  .pick-color.wrap .v-card .v-image {
    height: 90%;
  }

  .pick-color.right {
    width:30%;
    height: 100%;
    position: absolute;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .pick-color.right img {
    max-width: 100%;
    max-height: 80%;
    position: absolute; 
    right: 20%;
    
    display: block;
    margin: auto;
  }

  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
  }

</style>