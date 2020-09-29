<template>
  <div class="get-color wrap" :class="[{active : this.$parent.isGet}, {deactive : this.$parent.isPick}]">
    
    <!-- Landing page의 getColor 화면 -->
    <v-card @click="clickGet()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isPick == false && this.$parent.isGet == false"
    >
      <v-card-title>Get COLOR</v-card-title>
      
      <div id="keyword-img-wrap">
        <img id="keyword-img" src="../../../assets/images/keywordimg.png">
      </div>
      <v-icon size="40" color="rgb(107, 203, 243)" id="keyword-img-icon">mdi-magnify</v-icon>
      <v-card-text style="position: absolute; bottom: 0; color: black; font-weight: 600; font-size: 18px;">
        Get Your Color By Keyword
      </v-card-text>
    </v-card>

    <!-- 검색 결과 -->
    <div v-if="this.$parent.isGet" class="get-color left">
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

    <!-- 오른쪽 배경 -->
    <div v-if="this.$parent.isGet" class="pick-color right" v-bind:style="{'background-color' : selectedColor}">
      <!-- <img src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"> -->
    </div>

    <!-- 검색창 -->
    <v-card class="search" v-if="this.$parent.isGet">
      <div class="search-wrap">
        <div class="search-panel">
          <input v-model="keyword" placeholder="keyword" v-on:keyup.enter="getPicularImages()">
          <v-btn @click="getPicularImages()" class="ma-2" tile large :color="selectedColor" icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
    </v-card>

    <div v-if="keyword==='' && this.$parent.isGet" id="require-text">
      키워드를 입력해주세요
    </div>

    <!-- 배색 추천 받으러 가기 버튼 + 선택 안한 경우 modal -->
    <div class="next-button" v-if="this.$parent.isGet">
      <NonPickDialog></NonPickDialog>
    </div>

    <!-- 배색 추천 화면 -->
    <div class="bottom-page" ref="messageDisplay">
      <RecommendTheme></RecommendTheme>
    </div>
  </div>
</template>

<script>
import RecommendTheme from '../recommend-theme/RecommendTheme';
import NonPickDialog from '../recommend-theme/NonPickDialog';
import axios from 'axios';
import { mapGetters, mapActions } from 'vuex';
const colorStore = 'colorStore'

export default {
  name: 'GetColor',
  components: {
    RecommendTheme,
    NonPickDialog
  },
  data () {
    return {
      picularResult: {},
      picularImages: [],
      keyword: '',
      absolute: true,
      opacity: 1,
      overlay: false,
      selectedColor: '',
    }
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  created(){
    this.selectedColor = this.storeSelectedColor;
  },
  watch: {
    storeSelectedColor(val){
      this.selectedColor = val
    }
  },
  methods: {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    clickGet() {
      this.$parent.isPick = false;
      this.$parent.isGet = true;
    }, async getPicularImages() {
      await axios.get('https://server.picular.co/' + this.keyword)
      .then((res) => {
        this.picularResult = res.data
        this.picularImages = [];
        this.picularImages.push(this.picularResult.colors.slice(0, 5));
        this.picularImages.push(this.picularResult.colors.slice(5, 10));
        this.picularImages.push(this.picularResult.colors.slice(10, 15));
        this.picularImages.push(this.picularResult.colors.slice(15, 20));
      })
      .catch((err) => {
        console.log(err);
      })
    },
    getTheme(){
      document.body.className = "unlock";
      console.log(document.body);
      window.scrollTo({left: 0, top: 1000, behavior: 'smooth'});
    },
    getColor(color){
      const payload = {selectedColor: color};
      this.AC_SELECTED_COLOR(payload);
    }
  }
}
</script>

<style scoped>

  .get-color.wrap {
    width: 50%;
    height: 100%;
    float: right;
    transition-duration: 300ms;
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
    margin-top: 15%;
  }

  .get-color.wrap .v-card .v-card-title {
    height: 10%;
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
  
  #require-text{
    position: absolute;
    right: 5%;
    top: 10%;
    width: 40%;
    height: 7%;
    margin-top: 15%;
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
    width: 50%;
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

  .next-button {
    position: absolute;
    right: 5%;
    top: 85%;
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