<template>
  <div class="get-color wrap" :class="[{active : this.$parent.isGet}, {deactive : this.$parent.isPick}]">
    <div v-if="this.$parent.isLanding && this.$parent.isGet == false && this.$parent.isPick == false">
      <div class="underline"></div>
      <div class="get-desc">
        <table>
          <tr>
            <td><span><strong>GET</strong></span></td>
          </tr>
          <tr>
            <td><p>Get your color by keyword</p></td>
          </tr>
        </table>
      </div>
    </div>

    <!-- Landing page의 getColor 화면 -->
    <v-card @click="clickGet()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isPick == false && this.$parent.isGet == false"
    > 
      <div id="keyword-img-wrap">
        <img id="keyword-img" src="../../../assets/images/keywordimg.png">
        <!-- <p style="text-align: center; font-size: 1.1rem; text-weight: thin;">키워드의 색에 대한 배색을 추천받아보세요</p> -->
      </div>
      <v-icon size="40" color="rgb(107, 203, 243)" id="keyword-img-icon">mdi-magnify</v-icon>
      
      <v-card-text style="position: absolute; bottom: 0; color: black; font-weight: 100; font-size: 18px; text-align: center; user-select: none;">
        키워드의 색에 대한 배색을 추천받아보세요
      </v-card-text>
    </v-card>

    <!-- 검색 결과 -->
    <div v-if="this.$parent.isGet && !loading" class="get-color left">
      <ul v-for="colorList in this.picularImages" v-bind:key="colorList.index">
        <li v-for="color in colorList" v-bind:key="color.color">
          <v-card class="mx-auto elevation-5" @click="getColor(color.color)">
            <v-img class="white--text align-end" :src="color.img">
              <v-overlay :absolute="true" :opacity="0" class="image-layer" v-bind:style="{'background-color': color.color}"></v-overlay>  
            </v-img>
          </v-card>
        </li>
      </ul>
    </div>

    <div v-if="keyword==='' && this.$parent.isGet" id="require-text">
      <p>키워드를 입력하고</p>
      <p>키워드의 색을 받아보세요</p>
    </div>


    <Loading v-if="this.$parent.isGet && loading" class="get-color left"></Loading>


    <!-- 오른쪽 배경 -->
    <div v-if="this.$parent.isGet" class="pick-color right" v-bind:style="{'background-color' : backColor}">
      <!-- <img src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"> -->
      <div class="button-desc">
        <p>Get color recommendations</p>
        <p>based on the selected color</p>
      </div>
    </div>

    <!-- 검색창 -->
    <v-card style="margin-top: -20%;" class="search" v-if="this.$parent.isGet">
      <div class="search-wrap">
        <div class="search-panel">
          <input v-model="keyword" placeholder="keyword" v-on:keyup.enter="getPicularImages()">
          <v-btn @click="getPicularImages()" class="ma-2" tile large :color="backColor" icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
    </v-card>


    <!-- 배색 추천 화면 -->
    <div class="bottom-page" ref="messageDisplay">
      <RecommendTheme></RecommendTheme>
    </div>
  </div>
</template>

<script>
import RecommendTheme from '../recommend-theme/RecommendTheme';
// import NonPickDialog from '../recommend-theme/NonPickDialog';
import Loading from '../../loading/GetColorLoading';
import axios from 'axios';
import { mapGetters, mapActions } from 'vuex';
import materialColors from '../../../assets/color/colorList.js'
const colorStore = 'colorStore'

export default {
  name: 'GetColor',
  components: {
    RecommendTheme,
    // NonPickDialog,
    Loading
  },
  data () {
    return {
      picularResult: {},
      picularImages: [],
      keyword: '',
      absolute: true,
      opacity: 1,
      overlay: false,
      selectedColor: null,
      backColor: '',
      loading: false,
      materialColors: materialColors
    }
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  created(){
    const bc = this.storeSelectedColor;
    if(bc === null){
      this.backColor = ''
    } else {
      this.selectedColor = this.storeSelectedColor;
      this.backColor = bc.hex;
    }
  },
  watch: {
    storeSelectedColor(val){
      this.selectedColor = val;
      this.backColor = val.hex;
    }
  },
  methods: {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR', 'AC_THEMES']),
    clickGet() {
      this.$parent.isPick = false;
      this.$parent.isGet = true;
    }, async getPicularImages() {
      this.loading = true;
      await axios.get('https://server.picular.co/' + this.keyword)
      .then((res) => {
        this.loading = false;
        this.picularResult = res.data
        this.picularImages = [];
        this.picularImages.push(this.picularResult.colors.slice(0, 5));
        this.picularImages.push(this.picularResult.colors.slice(5, 10));
        this.picularImages.push(this.picularResult.colors.slice(10, 15));
        this.picularImages.push(this.picularResult.colors.slice(15, 20));
      })
      .catch((err) => {
        this.loading = false;
        console.log(err);
      })
    },
    getColor(color){
      var rgb = this.getRGB(color);
      var select = null;
      var min = 1000000;
      this.materialColors.forEach((ele) => {
        ele.variations.forEach((hex) => {
          var rgb2 = this.getRGB(hex.hex);
          var distance = (rgb2.r - rgb.r) * (rgb2.r - rgb.r) + (rgb2.g - rgb.g) * (rgb2.g - rgb.g) + (rgb2.b - rgb.b) * (rgb2.b - rgb.b);
          if(distance < min) {
            min = distance;
            select = hex;
          }
        })
      })
      const payload = {selectedColor: select};
      this.AC_SELECTED_COLOR(payload);
    },
    getRGB(color) {
      var hex = color.substring(1, 7);
      var value = hex.match( /[a-f\d]/gi ); 
      if ( value.length == 3 ) hex = value[0] + value[0] + value[1] + value[1] + value[2] + value[2]; 
      value = hex.match( /[a-f\d]{2}/gi ); 
      var r = parseInt( value[0], 16 ); 
      var g = parseInt( value[1], 16 ); 
      var b = parseInt( value[2], 16 ); 
      return {
        r: r,
        g: g,
        b: b
      };
    }
  }
}
</script>

<style scoped>
  @font-face {
    font-family: 'ReenieBeanie-Regular';
    src: url('../../../assets/font/ReenieBeanie-Regular.ttf');
  }

  @font-face {
    font-family: 'Anton-Regular';
    src: url('../../../assets/font/Anton-Regular.ttf');
  }

  @font-face {
    font-family: 'PermanentMarker-Regular';
    src: url('../../../assets/font/PermanentMarker-Regular.ttf');
  }

  .get-color.wrap {
    width: 50%;
    height: 100%;
    float: right;
    transition-duration: 300ms;
    display: flex;
    align-items: center;
  }
  
  .get-color.wrap .underline{
    background-color: black;
    position: absolute;
    top: 30%;
    right: 5%;
    height: 0.7px;
    width: 40%;
  }

  .get-color.wrap .get-desc {
    text-align: right;
    position: absolute;
    top: 32%;
    right: 5%;
    font-size: 2.1rem;
    line-height: 0.7;
    width: 40%;
  }

  .get-color.wrap .get-desc span {
    float: left;
    font-size: 1.5rem;
  }

  .get-color.wrap .get-desc table {
    width: 100%;
    user-select: none;
  }

  .get-color.wrap .get-desc table tr:nth-child(1) {
    text-align: left;
    display: flex;
    align-items: center;
  }

  .get-color.wrap .get-desc table tr:nth-child(2) {
    text-align: right;
    font-size: 2rem;
  }

  .get-color.wrap .get-desc p {
    font-family: 'ReenieBeanie-Regular';
  }

  .get-color.wrap.active {
    width: 100%;
    height: 100%;
  }

  .get-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .get-color.wrap .v-card {
    position: absolute;
    right: 5%;
    width: 40%;
    height: 50%;
    margin-top: 17%;
  }

  .get-color.wrap .v-card .v-image {
    height: 90%;
  }

  .get-color.wrap .v-card.search {
    position: absolute;
    right: 5%;
    width: 40%;
    height: 7%;
    margin-top: 15%;
  }
  
  #require-text {
    position: absolute;
    right: 5%;
    top: 10%;
    width: 40%;
    height: 7%;
    margin-top: 19%;
    font-size: 1.1rem;
    line-height: 0.7;
  }

  .get-color.wrap .v-card .search-wrap {
    position: absolute;
    right: 5%;
    width: 90%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .get-color.wrap .v-card .search-wrap .search-panel {
    width: 100%;
    /* padding-left: 8%; */
    background-color: white;
  }

  .get-color.wrap .v-card .search-wrap .search-panel input {
    width: 80%;
    height: 100%;
    padding: 1% 1%;
    outline: none;
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

  .get-color.wrap .get-color.left {
    position: absolute;
    margin-top: 10%;
    width: 50%;
    height: 80%;
    padding-left: 1%;
    left: 3%;
    display: flex;
    overflow: auto;
  }

  .get-color.left ul {
    width: 100%;
    height: 100%;
    list-style: none;
    padding: 0;
  }

  .get-color.left ul li {
    width: 100%;
    text-align: center;
  }

  .get-color.left ul li .v-card {
    position: relative;
    width: 90%;
    background-color: black;
  }
  .get-color.left ul li .image-layer {
    opacity: 1;
    transition-duration: 300ms;
  }

  .get-color.left ul li .image-layer:hover {
    opacity: 0.1;
  }

  .button-desc {
    position: absolute;
    font-size: 2.3rem;
    text-align: left;
    width: 15rem;
    right: 6rem;
    bottom: 7.7rem;
    transform: rotate(-15deg);
    user-select: none;
  }
  
  .button-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.9;
  }


  .bottom-page {
    width: 100%;
    height: 100%;
  }

  .get-color.wrap .v-card:hover #keyword-img {
    transform: translateY(calc(-100% + 70px));
  }   

  #keyword-img-wrap {
    position : absolute;
    top: calc(50% - 60px);
    left: 10%;
    width: 80%; 
    height: 60px; 
    background-color: white;
    overflow-y: hidden;
    border: 5px solid rgb(107, 203, 243);
    box-shadow: 5px 5px 23px 0px rgba(0,0,0,0.75);
  }

  #keyword-img {
    transition-duration: 6s;
  }

  #keyword-img-icon {
    position: absolute;
    top: calc(40% - 10px);
    right: 15%;
  }
</style>