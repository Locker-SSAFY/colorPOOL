<template>
  <div class="library-container wrap">
    <div style="position: absolute; top: 8%; left: 25%; z-index: 1;">
      <v-icon v-if="!isShow" size="50" :color="crownColor">mdi-chess-queen</v-icon>
    </div>
    <div class="title-tape">
      <p>Library</p>
    </div>    
    <div class="library-left">
      <v-btn
        id="back-btn"
        v-if="isShow"
        @click="isShow = false"
        icon 
        text>
        <v-icon size="65" :color="magazine.color">mdi-arrow-left</v-icon>
      </v-btn>
      <div class="hand-drawing"></div>
      <div class="library-desc">
        <p>Meet the popular magazines</p>
        <p>in colorPOOL</p>
      </div>      
      <MagazineDetailCover v-if="isShow" v-bind:magazine="magazine"></MagazineDetailCover>
      <v-hover v-if="!isShow" v-slot:default="{ hover }">
      <v-carousel
        v-if="!isShow"
        class="magazine-honor"
        hide-delimiters
        :cycle="hover ? false: true"
        :continuous="true"
        :interval="2500"
        :show-arrows="false"
        @change="onSlideChange"
        style="display: flex; align-items: center; justify-content: center; width : 100%; height: 100%;"
        
      >
        <v-carousel-item v-for="(ranker,index) in topRank" v-bind:key="index">
          <MagazineRankCover
            v-bind:rank="index + 1"
            v-bind:aboutRanker="ranker"
          ></MagazineRankCover>
          <!-- <div style="position: absolute; top: 0%; right: -2%; z-index: 1;">
            <v-icon size="50" :color="crownColor">mdi-chess-queen</v-icon>
          </div> -->
          <v-btn class="icon-heart" icon text @click="clickHeart(ranker)">
            <v-icon size="40" v-if="ranker.clicked" :color="ranker.color">mdi-heart</v-icon>
            <v-icon size="40" v-else :color="ranker.color">mdi-heart-outline</v-icon>
          </v-btn>
          <span class="num-heart" :style="{'color' : ranker.color}">{{ranker.likeCount}}</span>
          <v-btn  class="icon-bookmark" icon text @click="ranker.bookmark = !ranker.bookmark">
            <v-icon size="40" v-if="ranker.bookmark" :color="ranker.color">mdi-bookmark</v-icon>
            <v-icon size="40" v-else :color="ranker.color">mdi-bookmark-outline</v-icon>
          </v-btn>
        </v-carousel-item>
        
      </v-carousel>
      </v-hover>
    </div>
    <div class="library-right">
      <ul v-for="(list, index) in magazineList" v-bind:key="index">
        <li v-for="(magazine, idx) in list" v-bind:key="idx">
          <MagazineListCover v-bind:magazineData="magazine" @show-magazine="showMagazine"></MagazineListCover>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import MagazineCover from '../components/magazine/magazineCover'
import MagazineRankCover from '../components/magazine/magazineRankCover'
import MagazineListCover from '../components/magazine/magazineListCover'
import MagazineDetailCover from '../components/magazine/magazineDetailCover'
import {mapGetters, mapActions} from 'vuex'
import axios from '../api/axiosCommon'
const rankStore = 'rankStore'

export default {
  name: 'Library',
  components: {
    // MagazineCover, 
    MagazineRankCover,
    MagazineListCover,
    MagazineDetailCover
  },
  computed: {
    ...mapGetters(rankStore, {
      storeTopRank: 'GE_TOP_RANK',
      storeRestRank: 'GE_REST_RANK',
      storeMagazineList: 'GE_MAGAZINE_LIST'
    })
  },
  watch: {
    restRank() {
      this.magazineList = [];
      const len = this.restRank.length;
      const div = len/3;
      for(var i = 0; i < div; i++){
        const example = [];
        for(let j = 0; j < 3; j++){
          if( i == div-1 && this.restRank[3*i+j] == null){
            example.push(null);
          } else {
            example.push(this.restRank[3*i+j]);
          }
        }
        this.magazineList.push(example);
        console.log(this.magazineList)
      }
    },
    storeMagazineList(val) {
      console.log(val);
      this.topRank = this.storeMagazineList.slice(0, 3);
      this.restRank = this.storeMagazineList.slice(3, this.storeMagazineList.length);
      console.log(this.topRank)
      console.log('rest', this.restRank)
    }
  },
  data() {
    return {
      topRank: [],
      restRank: [],
      isHover: false,
      magazineList: [],
      isShow: false,
      magazine: '',
      crownColor: ''
    }
  },
  created() {
    // 순위별 이미지 가져오기
    this.AC_MAGAZINE_LIST();
    // console.log(this.storeMagazineList);
    this.AC_TOP_RANK();
    this.AC_REST_RANK();
    // this.topRank = this.storeTopRank;
    // this.restRank = this.storeRestRank;
    // console.log('top', this.topRank);
    // console.log('rest', this.restRank)
    
  },
  methods: {
    ...mapActions(rankStore, ['AC_TOP_RANK', 'AC_REST_RANK', 'AC_MAGAZINE_LIST']),
    showMagazine(magazine) {
      this.isShow = true;
      this.magazine = magazine;
      console.log(magazine)
    },
    spliceMagazine() {
      this.topRank = this.storeMagazineList.slice(0, 3);
      this.restRank = this.storeMagazineList.slice(3, this.storeMagazineList.length);
      console.log(this.topRank);
    },
    onSlideChange(slideNumber) {
      // console.log(this.topRank[slideNumber].color)
      this.crownColor = this.topRank[slideNumber].color;
    },
    clickHeart(cover) {
      cover.clicked = !cover.clicked;
      if(cover.clicked) {
        cover.likeCount += 1;
      } else {
        cover.likeCount -= 1;
      }
      const token = localStorage.getItem('access_token');
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      axios.post('/magazine/like/' + cover.magazineId, {headers: header})
      .then((res) => console.log(res))
      .catch((err) => console.log(err))
    }
  }
} 
</script>

<style scoped>
  .library-container.wrap {
    width: 100vw;
    height: 100vh;
    padding-top: 80px;
    display: flex;
  }

  .library-left {
    width: 40%;
    height: 100%;
    float: left;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .library-left #back-btn {
    position: absolute;
    top: 100px;
    left: 40px;
  }

  .v-window-item {
    display: flex;
    align-items: center;
  }

  .icon-heart {
    position: absolute;
    margin-top: 8px;
    left: 40px;
  }

  .num-heart {
    position: absolute;
    margin-top: 8px;
    left: 80px;
    font-size: 25px;
    font-weight: 600;
  }

  .icon-bookmark {
    position: absolute;
    margin-top : 8px;
    left: 290px;
  }

  /* .v-window__container {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .v-window-item {
    display: flex;
    align-items: center;
  }

  .v-responsive__content {
    width: 100%;
  } */

  .library-right {
    width: 60%;
    height: 100%;
    overflow-y: scroll;
    float: right;
    padding-bottom: 50px;
  }

  .library-right ul {
    width: 100%;
    display: flex;
    padding-top: 50px;
    padding-bottom: 50px;
    padding-left: 50px;
    padding-right: 50px;
    justify-content: space-between;
    margin: auto auto;
    position: relative;
  }

  .library-right ul li {
    list-style-type: none;
    display: inline;
  }

  .hand-drawing {
    position: absolute;
    background: url(https://cdn2.bustle.com/nylon/2020/squiggle-line-febb0bf100.svg) no-repeat;
    top: 12%;
    left: 10%;
    height: 25%;
    width: 35%;
    z-index: 10;
  }

  .library-desc {
    position: absolute;
    font-size: 2.3rem;
    text-align: left;
    left: 20%;
    bottom: 15%;
    transform: rotate(-15deg);
    z-index: 1;
  }
  
  .library-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.7;
  }

  .title-tape {
    position: absolute;
    font-size: 3rem;
    font-weight: 600;
    left: 13%;
    width: 15%;
    height: 60px;
    background-color: #424242;
    padding: 1% 5% 0% 7%;
    margin-top: 30px;

    mask-image: url(https://cdn2.bustle.com/nylon/2020/tape_b-80d71c228a.svg);
    mask-repeat: no-repeat;
    mask-size: 100% 100%;
    background-image: url(https://imgix.bustle.com/uploads/image/2020/3/10/428e5a38-91a6-4f27-8187-e0c507694930-tape_poster.png);
    background-size: cover;
    background-repeat: no-repeat;
    background-blend-mode: difference;
  }
  
  .title-tape p {
    font-family: 'PermanentMarker-Regular';
    font-size: 2.1rem;
    color: white;
  }
</style>
