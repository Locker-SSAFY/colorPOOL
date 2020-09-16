<template>
<div>
  <div class="theme-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    <div class="theme-layer" @click="notSelect()"></div>

    <!-- 선택한 색상칩 -->
    <div class="show-color left"  :style="{'background-color' : selectedColor}">
      <div class="color-code top">
        {{selectedColor}}
      </div>
      <div class="color-code bottom">
        {{selectedColor}}
      </div>
    </div>

    <!-- 추천 배색 보여주는 곳 -->
    <div class="show-theme">
      <v-icon
        class="ma-2"
        size="30"
      >
      mdi-refresh</v-icon>
      <div class="button-text">POOL</div>
      
      <div class="underline" :style="{'background-color' : selectedColor}"></div>
      <SelectTone></SelectTone>
      <div class="theme-scroll wrap">
        <div class="show-themes mt-8" v-for="(t, index) in theme" :key="index">
          <div class="color-group" @click="selectTheme(t.color1, t.color2, t.color3, t.color4, t.color5)">
            <div class="theme-colors" :style="{'background-color' : t.color1}">
            </div>
            <div class="theme-colors" :style="{'background-color' : t.color2}">
            </div>
            <div class="theme-colors" :style="{'background-color' : t.color3}">
            </div>
            <div class="theme-colors" :style="{'background-color' : t.color4}">
            </div>
            <div class="theme-colors" :style="{'background-color' : t.color5}">
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 이전으로 돌아가기 -->
    <v-btn
      class="goback-button"
      icon
      text
      @click="goBack"
    >
      <v-icon size="100">mdi-arrow-left</v-icon>
    </v-btn>

    
    <!-- 배색 선택 안했을 경우 -->
    <div v-if="selectedTheme==null" class="theme-color right" :style="{'background-color' : selectedColor}">
      <ColorInfo></ColorInfo>
    </div>

    <!-- 배색 선택한 경우 -->
    <div v-else class="theme-color right">
      <ThemeInfo></ThemeInfo>
      <div class="next-text">view more</div>
      <v-btn
        class="next-button"
        icon
        text
      >
        <v-icon size="100">mdi-arrow-right</v-icon>
      </v-btn>
    </div>
  </div>
</div>
</template>
<script> 
import { mapGetters,mapActions } from 'vuex'
import SelectTone from './SelectTone'
import ColorInfo from './ColorInfo'
import ThemeInfo from './ThemeInfo'
const colorStore = 'colorStore'

export default {
  name: 'RecommandTheme',
  components: {
    SelectTone, 
    ColorInfo,
    ThemeInfo
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR', storeSelectedTheme: 'GE_SELECTED_THEME'})
  },
  created(){
    this.selectedColor = this.storeSelectedColor;
    this.selectedTheme = this.storeSelectedTheme;
  },
  data () {
    return {
      selectedColor: '',
      selectedTheme: [],
      // selectedVariation: [],
      // showColorInfo: true,
      theme: [
        {
          color1: "#e63946",
          color2: "#f1faee",
          color3: "#a8dadc",
          color4: "#457b9d",
          color5: "#1d3557",
        },
        {
          color1: "#d90429",
          color2: "#ef233c",
          color3: "#edf2f4",
          color4: "#edf2f4",
          color5: "#8d99ae",
        },
        {
          color1: "#003049",
          color2: "#d62828",
          color3: "#f77f00",
          color4: "#fcbf49",
          color5: "#eae2b7",
        },
        {
          color1: "#03071e",
          color2: "#370617",
          color3: "#6a040f",
          color4: "#9d0208",
          color5: "#d00000",
        },
        {
          color1: "#e63946",
          color2: "#f1faee",
          color3: "#a8dadc",
          color4: "#457b9d",
          color5: "#1d3557",
        },
        {
          color1: "#d90429",
          color2: "#ef233c",
          color3: "#edf2f4",
          color4: "#edf2f4",
          color5: "#8d99ae",
        },
      ]
    }
  },
  watch: {
    storeSelectedColor(val){
      this.selectedColor = val
    },
    storeSelectedTheme(val){
      this.selectedTheme = val
    }
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_THEME']),
    goBack(){
      window.scrollTo(0, 0);
    },
    selectTheme(c1, c2, c3, c4, c5){
      const payload = { selectedTheme: [c1, c2, c3, c4, c5]};
      this.AC_SELECTED_THEME(payload);
    },
    notSelect(){
      this.AC_SELECTED_THEME({selectedTheme: null});
    }
  }
}
</script>

<style scoped>
  .theme-color.wrap {
    width: 50%;
    height: 100%;
    float: left;
    transition-duration: 300ms;
  }
  .theme-layer{
    position: absolute;
    left: 0%;
    top: 100%;
    height: 100%;
    width: 65%;
    z-index: 49;
  }

  .theme-color.wrap.active {
    width: 100%;
    max-height: 100%;
  }

  .theme-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .theme-scroll{
    overflow: scroll;
    height: 75%;
  }

  .color-group{
    height: 85px;
    width: 90%;
    margin-left: 5%;
  }
  .theme-colors{
    display: inline-block;
    height: 100%; 
    width: 20%;
  }
  .show-color.left{
    position: absolute;
    left: 7%;
    top: 135%;
    height: 30%;
    width: 13%;
    align-items: center;
  }

  .color-code.top{
    padding-top: 3px;
    text-align: center;
    color: white;
    background-color: black;
    height: 10%;
  }

  .color-code.bottom{
    padding-top: 7px;
    text-align: center;
    position: absolute;
    bottom: 0%;
    height: 13%;
    width: 100%;
    color: white;
    background-color: black;
  }

  .show-theme {
    position: absolute;
    left: 25%;
    top: 125%;
    height: 70%;
    width: 35%;
    z-index: 50;
  }

  .button-text{
    display: inline;
    font-size: 1.7rem;
    font-weight: bold;
  }

  .underline{
    position: absolute;
    left: 0%;
    top: 7%;
    /* background-color: #EF5350; */
    width: 100%;
    height: 2px;
  }

  .theme-color.right {
    width:35%;
    height: 100%;
    position: absolute;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .next-text{
    position: absolute;
    right: 20%;
    top: 80%;
  }

  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
    z-index: 50;
  }

  .goback-button{
    position: absolute;
    top: 185%;
    left: 5%;
    z-index: 50;
  }

</style>