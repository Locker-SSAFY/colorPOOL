<template>
  <div class="library-container wrap">
    <div class="library-left">
      <v-btn
        id="back-btn"
        v-if="isShow"
        @click="isShow = false"
        icon 
        text>
        <v-icon size="100" :color="magazine.color">mdi-arrow-left</v-icon>
      </v-btn>
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
        style="display: flex; align-items: center; justify-content: center; width : 100%; height: 100%;"
      >
        <v-carousel-item v-for="(ranker,index) in topRank" v-bind:key="index">
          <MagazineRankCover
            v-bind:rank="index + 1"
            v-bind:aboutRanker="ranker"
          ></MagazineRankCover>
          <v-btn class="icon-heart" icon text @click="ranker.heart = !ranker.heart">
            <v-icon size="40" v-if="ranker.heart" :color="ranker.color">mdi-heart</v-icon>
            <v-icon size="40" v-else :color="ranker.color">mdi-heart-outline</v-icon>
          </v-btn>
          <span class="num-heart" :style="{'color' : ranker.color}">{{ranker.likes.length}}</span>
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
      storeRestRank: 'GE_REST_RANK'
    })
  },
  watch: {
    restRank() {
      const fromSize = this.magazineList.length;
      const toSize = this.restRank.length;
      for(var i = fromSize; i < toSize; i+=3) {
        var example = new Array(this.restRank[i], this.restRank[i + 1], this.restRank[i + 2]);
        example  = example.filter(function(item) {
          return item !== null && item !== undefined && item !== '';
        });
        this.magazineList.push(example);
      }
      console.log(this.magazineList);
    }
  },
  data() {
    return {
      topRank: [],
      restRank: [],
      isHover: false,
      magazineList: [],
      isShow: false,
      magazine: ''
    }
  },
  created() {
    // 순위별 이미지 가져오기
    this.AC_TOP_RANK();
    this.AC_REST_RANK();
    this.topRank = this.storeTopRank;
    this.restRank = this.storeRestRank;
    console.log('top', this.topRank);
    console.log('rest', this.restRank)
  },
  methods: {
    ...mapActions(rankStore, ['AC_TOP_RANK', 'AC_REST_RANK']),
    showMagazine(magazine) {
      this.isShow = true;
      this.magazine = magazine;
      console.log(magazine)
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


</style>