<template>
<v-row>
  <div class="my-magazine" @click="show('my')" @mouseover="hover('my')" @mouseout="out('my')" :style="{'width' : myWidth+'%'}">
    <div class="my-index">
      <div class="content-area" v-if="isMyMagazine">
          <ul v-for="(list, idx) in myMagazine" :key="idx">
            <li v-for="(magazine, idx) in list" v-bind:key="idx">
              <MagazineListCover v-bind:magazineData="magazine"></MagazineListCover>
            </li>
          </ul>
      </div>
    </div>
  </div>
  <div class="scrap-magazine" @click="show('scrap')" @mouseover="hover('scrap')" @mouseout="out('scrap')" :style="{'width' : scrapWidth+'%'}">
      <div class="scrap-index">
        <div class="content-area" v-if="isScrap">
          <ul v-for="(list, idx) in scrapMagazine" :key="idx">
            <li v-for="(magazine, idx) in list" v-bind:key="idx">
              <MagazineListCover v-bind:magazineData="magazine"></MagazineListCover>
            </li>
          </ul>
        </div>
      </div>
  </div>
  <div class="recent-magazine" @click="show('recent')" @mouseover="hover('recent')" @mouseout="out('recent')" :style="{'width' : recentWidth+'%'}">
    <div class="recent-index">
      <div class="content-area" v-if="isRecent">
          <ul v-for="(list, idx) in recentMagazine" :key="idx">
            <li v-for="(magazine, idx) in list" v-bind:key="idx">
              <MagazineListCover v-bind:magazineData="magazine"></MagazineListCover>
            </li>
          </ul>
        </div>
    </div>
  </div>
</v-row>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
// import MagazineRankCover from '../components/magazine/magazineRankCover'
import MagazineListCover from '../components/magazine/magazineListCover'
// import MagazineDetailCover from '../components/magazine/magazineDetailCover'
const myPageStore = 'myPageStore'

export default {
  name: 'myPage',
  components:{
    MagazineListCover,
    // MagazineDetailCover
  },
  data(){
    return{
      myWidth: 89.5,
      scrapWidth: 5,
      recentWidth: 5,
      isMyMagazine: true,
      isScrap: false,
      isRecent: false,
      myList: [],
      scrapList: [],
      recentList: [],
      myMagazine: [],
      scrapMagazine: [],
      recentMagazine: []
    }
  },
  computed: {
    ...mapGetters(myPageStore, {
      storeMyList: 'GE_MY_LIST',
      storeScrapList: 'GE_SCRAP_LIST',
      storeRecentList: 'GE_RECENT_LIST'
    })
  },
  created(){
    this.AC_MY_LIST();
    this.AC_SCRAP_LIST();
    this.AC_RECENT_LIST();
    this.myList = this.storeMyList;
    this.scrapList = this.storeScrapList;
    this.recentList = this.storeRecentList;
  },
  watch: {
    myList() {
      const fromSize = this.myMagazine.length;
      const toSize = this.myList.length;
      for(var i = fromSize; i < toSize; i+=3) {
        var example = new Array(this.myList[i], this.myList[i + 1], this.myList[i + 2]);
        example  = example.filter(function(item) {
          return item !== null && item !== undefined && item !== '';
        });
        this.myMagazine.push(example);
      }
      console.log("myMagazine", this.myMagazine);
    },
    scrapList() {
      const fromSize = this.scrapMagazine.length;
      const toSize = this.scrapList.length;
      for(var i = fromSize; i < toSize; i+=3) {
        var example = new Array(this.scrapList[i], this.scrapList[i + 1], this.scrapList[i + 2]);
        example  = example.filter(function(item) {
          return item !== null && item !== undefined && item !== '';
        });
        this.scrapMagazine.push(example);
      }
      console.log("scrapMagazine", this.scrapMagazine);
    },
    recentList() {
      const fromSize = this.recentMagazine.length;
      const toSize = this.recentList.length;
      for(var i = fromSize; i < toSize; i+=3) {
        var example = new Array(this.recentList[i], this.recentList[i + 1], this.recentList[i + 2]);
        example  = example.filter(function(item) {
          return item !== null && item !== undefined && item !== '';
        });
        this.recentMagazine.push(example);
      }
      console.log("recentMagazine", this.recentMagazine);
    }
  },
  methods: {
    ...mapActions(myPageStore, ['AC_MY_LIST', 'AC_SCRAP_LIST', 'AC_RECENT_LIST']),
    hover(name){
      if(name==='my'){
        this.myWidth = this.myWidth+0.5;
      } else if(name==='scrap'){
        this.scrapWidth = this.scrapWidth+0.5;
      } else if(name==='recent'){
        this.recentWidth = this.recentWidth+0.5;
      }
    },
    out(name){
      if(name==='my'){
        this.myWidth = this.myWidth-0.5;
      } else if(name==='scrap'){
        this.scrapWidth = this.scrapWidth-0.5;
      } else if(name==='recent'){
        this.recentWidth = this.recentWidth-0.5;
      }
    },
    show(name){
      if(name === 'my'){
        this.isMyMagazine = true;
        this.isScrap = false;
        this.isRecent = false;
        this.myWidth = 89.5;
        this.scrapWidth = 5;
        this.recentWidth = 5;
      } else if(name === 'scrap'){
        this.isMyMagazine = false;
        this.isScrap = true;
        this.isRecent = false;
        this.myWidth = 5;
        this.scrapWidth = 89.5;
        this.recentWidth = 5;
      } else if(name === 'recent'){
        this.isMyMagazine = false;
        this.isScrap = false;
        this.isRecent = true;
        this.myWidth = 5;
        this.scrapWidth = 5;
        this.recentWidth = 89.5;
      }
    }
  }
}
</script>
<style scoped>
  .my-magazine{
    height: 100%;
    transition: 0.5s;
  }
  .content-area{
    height: 100%;
    width: 94%;
    background-color: white;
    padding-top: 70px;
    padding-left: 5%;
  }
  .content-area ul{
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
  .content-area ul li{
    list-style-type: none;
    display: inline;
  }
  .index-title{
    height: 100%;
    width: 100%;
    margin-left: 94%;
    /* background-color: red; */
  }
  .index-title h1{
    transform: rotate(90deg);
    transform-origin: left top;
    margin-top: 50px;
  }
  .my-index{
    background-color: navy;
    transition: 0.5s;
    height: 100%;
    width: 100%;
  }
  .scrap-magazine{
    height: 100%;
    transition: 0.5s;
  }
  .scrap-index{
    background-color:palegoldenrod;
    transition: 0.5s;
    height: 100%;
    width: 100%;
  }
  .recent-magazine{
    height: 100%;
    transition: 0.5s;
  }
  .recent-index{
    background-color:rosybrown;
    transition: 0.5s;
    height: 100%;
    width: 100%;
  }
</style>