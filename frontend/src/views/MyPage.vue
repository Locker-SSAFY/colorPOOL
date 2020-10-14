<template>
<v-row>
  <div class="layer" v-if="showCover"  @click="closeCover" style="z-index: 89;"></div>
  <div class="content-cover" :style="{'width': coverWidth+'%'}">
    <div class="info" v-if="showCover">
      <div class="name-info">
        <span :style="[{'color': selected.color}, {'font-size': '25px'}, {'font-weight': '600'}]">{{selected.magazineName}}</span>
      </div>
      <div class="user-info">
        <span :style="[{'color': selected.color}, {'font-size': '20px'}]">{{selected.userNickname}}</span>
      </div>
      <div class="heart-number">
        <v-icon size="30" :color="selected.color">mdi-heart</v-icon>
        <span :style="[{'color': selected.color}, {'font-size': '22px'}]">{{selected.likeCount}}</span>
      </div>
      <v-btn id="close-btn" @click="closeCover" flat dark text icon>
        <v-icon size="45">
          mdi-chevron-double-left
        </v-icon>
      </v-btn>
      <div class="cover-image">
        <MagazineDetailCover v-bind:magazine="selected"></MagazineDetailCover>
      </div>
      <div class="cover-info">
        <div class="time-bar">
          <div class="themebar"
            :style="{'background': 'linear-gradient(to right, ' + this.theme[0] + ' 20%, ' + this.theme[1] + ' 20% 40%, ' + this.theme[2] + ' 40% 60%, ' + this.theme[3] + ' 60% 80%, ' + this.theme[4] + ' 80%)'}"
          ></div>
          <span :style="[{'color': selected.color}, {'font-size': '15px'}]">{{time}}</span>
        </div>       
      </div>
    </div>
  </div>
  <div class="my-magazine" @click="show('my')" @mouseover="hover('my')" @mouseout="out('my')" :style="{'width' : myWidth+'%'}">
    <div class="my-index">
      <span class="my-index-title">View my magazines</span>
      <div class="content-area" v-if="isMyMagazine">
        <span class="horizontal-title">My magazines</span>
        <div class="underline"></div>
        <div class="hand-drawing"></div>
        <ul v-for="(list, idx) in myMagazine" :key="idx">
          <li v-for="(magazine, indx) in list" v-bind:key="indx">
            <MagazineListCover v-if="magazine != null" v-bind:magazineData="magazine" @show-magazine="showMagazine(magazine)"></MagazineListCover>
            <div v-if="magazine == null" style="width: 200px;"></div>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="scrap-magazine" @click="show('scrap')" @mouseover="hover('scrap')" @mouseout="out('scrap')" :style="{'width' : scrapWidth+'%'}">
      <div class="scrap-index">
        <span class="scrap-index-title">View my liked magazines</span>
        <div class="content-area" v-if="isScrap">
          <span class="horizontal-title">Liked magazines</span>
          <div class="underline"></div>
          <div class="hand-drawing"></div>
          <ul v-for="(list, idx) in recentMagazine" :key="idx">
            <li v-for="(magazine, idx) in list" v-bind:key="idx">
              <MagazineListCover v-if="magazine != null" v-bind:magazineData="magazine" @show-magazine="showMagazine(magazine)"></MagazineListCover>
              <div v-if="magazine == null" style="width: 200px;"></div>
            </li>
          </ul>
        </div>
      </div>
  </div>
  <div class="recent-magazine" @click="show('recent')" @mouseover="hover('recent')" @mouseout="out('recent')" :style="{'width' : recentWidth+'%'}">
    <div class="recent-index">
      <span class="recent-index-title">View my bookmarked magazines</span>
      <div class="content-area" v-if="isRecent">
        <span class="horizontal-title">Bookmarked magazines</span>
        <div class="underline"></div>
        <div class="hand-drawing"></div>
          <ul v-for="(list, idx) in scrapMagazine" :key="idx">
            <li v-for="(magazine, idx) in list" v-bind:key="idx">
              <MagazineListCover v-bind:magazineData="magazine" @show-magazine="showMagazine(magazine)"></MagazineListCover>
              <div v-if="magazine == null" style="width: 200px;"></div>
            </li>
          </ul>
        </div>
    </div>
  </div>
</v-row>
</template>
<script>
import axios from '../api/axiosCommon'
import SERVER from '../api/restApi'
import { mapGetters, mapActions } from 'vuex'
// import MagazineRankCover from '../components/magazine/magazineRankCover'
import MagazineListCover from '../components/magazine/magazineListCover'
import MagazineDetailCover from '../components/magazine/magazineDetailCover'
const myPageStore = 'myPageStore'

export default {
  name: 'myPage',
  components:{
    MagazineListCover,
    MagazineDetailCover
  },
  data(){
    return{
      myWidth: 90,
      scrapWidth: 5,
      recentWidth: 5,
      coverWidth: 0,
      isMyMagazine: true,
      isScrap: false,
      isRecent: false,

      myList: [],
      scrapList: [],
      recentList: [],
      tempList: [],
      
      myMagazine: [],
      scrapMagazine: [],
      recentMagazine: [],
      selected: null,
      showCover: false,

      nowSelected: 'my',
      
      theme: [],
      themeData: null,

      time: ''
    }
  },
  computed: {
    ...mapGetters(myPageStore, {
      storeMyList: 'GE_MY_LIST',
      storeScrapList: 'GE_SCRAP_LIST',
      storeRecentList: 'GE_RECENT_LIST'
    }),
  },
  created(){
    this.AC_MY_LIST();
    this.AC_SCRAP_LIST();
    this.AC_RECENT_LIST();
    this.myList = this.storeMyList;
    this.scrapList = this.storeScrapList;
    this.recentList = this.storeRecentList;
    this.detail = this.storeDetail;
  },
  watch: {
    storeMyList(val) {
      this.myList = val;
      this.myMagazine = [];
      const len = this.myList.length;
      const div = len/4;
      for(var i = 0; i < div; i++){
        const example = [];
        for(let j = 0; j < 4; j++){
          if( i == div-1 && this.myList[4*i+j] == null){
            example.push(null);
          } else {
            example.push(this.myList[4*i+j]);
          }
        }
        this.myMagazine.push(example);
      }
    },
    storeScrapList(val) {
      this.scrapList = val;
      this.scrapMagazine = [];
      const len = this.scrapList.length;
      const div = len/4;
      for(var i = 0; i < div; i++){
        const example = [];
        for(let j = 0; j < 4; j++){
          example.push(this.scrapList[4*i+j]);
        }
        this.scrapMagazine.push(example);
      }
    },
    storeRecentList(val) {
      this.recentList = val;
      this.recentMagazine = [];
      const len = this.recentList.length;
      const div = len/4;
      for(var i = 0; i < div; i++){
        const example = [];
        for(let j = 0; j < 4; j++){
          example.push(this.recentList[4*i+j]);
        }
        this.recentMagazine.push(example);
      }
    },
    selected() {
      if(this.selected == null) return;
      this.time = this.timeForToday(this.selected.createdDate)
      console.log('time 입니다', this.time);
      const token = localStorage.getItem('access_token')
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }
      axios.get(SERVER.ROUTES.getThemeColors + this.selected.themeId, {headers: header})
      .then((res) => {
        this.themeData = res.data.data;
        console.log('detail cover themeData', this.themeData)
        const color1 = 'rgb(' + this.themeData.red1 + ',' + this.themeData.green1 + ',' + this.themeData.blue1 + ')';
        const color2 = 'rgb(' + this.themeData.red2 + ',' + this.themeData.green2 + ',' + this.themeData.blue2 + ')';
        const color3 = 'rgb(' + this.themeData.red3 + ',' + this.themeData.green3 + ',' + this.themeData.blue3 + ')';
        const color4 = 'rgb(' + this.themeData.red4 + ',' + this.themeData.green4 + ',' + this.themeData.blue4 + ')';
        const color5 = 'rgb(' + this.themeData.red5 + ',' + this.themeData.green5 + ',' + this.themeData.blue5 + ')';
        this.theme = [color1, color2, color3, color4, color5];
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      })
    }
  },
  methods: {
    ...mapActions(myPageStore, ['AC_MY_LIST', 'AC_SCRAP_LIST', 'AC_RECENT_LIST']),
    timeForToday(time) {
      const today = new Date();
      var timeValue = new Date(time);
      timeValue.setHours(timeValue.getHours());
      const betweenTime = Math.floor(
        (today.getTime() - timeValue.getTime()) / 1000 / 60
      );
      if (betweenTime < 1) return "방금전";
      if (betweenTime < 60) {
        return `${betweenTime}분전`;
      }
      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }
      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }
      return `${Math.floor(betweenTimeDay / 365)}년전`;
    },
    hover(name){
      if(name==='my'){
        if(this.nowSelected !== name){
          this.myWidth = this.myWidth+0.4;
          this.scrapWidth = this.scrapWidth-0.5;
        }
      } else if(name==='scrap'){
        if(this.nowSelected !== name){
          this.scrapWidth = this.scrapWidth+0.4;
          this.recentWidth = this.recentWidth-0.5;
        }
      } else if(name==='recent'){
        if(this.nowSelected !== name){
          this.recentWidth = this.recentWidth+0.4;
          this.scrapWidth = this.scrapWidth-0.5;
        }
      }
    },
    out(name){
      if(name==='my'){
        if(this.nowSelected !== name){
          this.myWidth = this.myWidth-0.4;
          this.scrapWidth = this.scrapWidth+0.5;
        }
      } else if(name==='scrap'){
        if(this.nowSelected !== name){
          this.scrapWidth = this.scrapWidth-0.4;
          this.recentWidth = this.recentWidth+0.5; 
        }
      } else if(name==='recent'){
        if(this.nowSelected !== name){
          this.recentWidth = this.recentWidth-0.4;
          this.scrapWidth = this.scrapWidth+0.5;
        }
      }
    },
    show(name){
      if(name === 'my'){
        this.nowSelected = name;
        this.isMyMagazine = true;
        this.isScrap = false;
        this.isRecent = false;
        this.myWidth = 90;
        this.scrapWidth = 5;
        this.recentWidth = 5;
      } else if(name === 'scrap'){
        this.nowSelected = name;
        this.isMyMagazine = false;
        this.isScrap = true;
        this.isRecent = false;
        this.myWidth = 5;
        this.scrapWidth = 90;
        this.recentWidth = 5;
      } else if(name === 'recent'){
        this.nowSelected = name;
        this.isMyMagazine = false;
        this.isScrap = false;
        this.isRecent = true;
        this.myWidth = 5;
        this.scrapWidth = 5;
        this.recentWidth = 90;
      }
    },
    showMagazine(magazine){
      // alert(magazine.name);
      this.selected = magazine;
      this.coverWidth = 40;
      this.showCover = true;
      console.log(this.selected);
    },
    closeCover(){
      this.coverWidth = 0;
      this.selected = null;
      this.showCover = false;
    },
  }
}
</script>
<style scoped>
  .my-magazine{
    height: 100%;
    transition: 0.5s;
  }
  .content-cover{
    position: fixed;
    top: 60px;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    z-index: 90;
    transition: 0.5s;
    border-radius: 15px;
    /* display: flex;
    justify-content: center; */
  }

  #close-btn{
    float: right;
    margin-right: 8px;
    margin-top: 8px;
  }
  .layer{
    position: absolute;
    top: 60px;
    height: 100%;
    width: 120%;
    background-color: black;
    opacity: 70%;
    z-index: 89;
  }
  .cover-image{
    position: absolute;
    top: calc(50% - 250px);
    left: calc(50% - 150px);
  }

  .cover-info {
    position: absolute;
    top: calc(50% + 250px);
    left: calc(50% - 150px);
  }

  .cover-info .time-bar {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .cover-info .themebar {
    width: 220px;
    height: 40px;
    margin-right: 20px;
    background-color: pink;
  }

  .heart-number {
    position: absolute;
    top: 90px;
    right: calc(50% - 150px);
    display: flex;
    align-items: center;
  }

  .heart-number span {
    margin-left: 5px;
  }

  .name-info {
    position: absolute;
    top: 50px;
    left: calc(50% - 150px);
  }
  .user-info {
    position: absolute;
    top: 90px;
    left: calc(50% - 150px);
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
  }

  .index-title h1{
    transform: rotate(90deg);
    transform-origin: left top;
    margin-top: 50px;
  }

  .my-index{
    position: relative;
    background-color: #7678ed;
    transition: 0.3s;
    height: 100%;
    width: 100%;
  }

  .my-index .my-index-title {
    position: absolute;
    top: 3%;
    left: 100%;
    margin-top: 100px;
    white-space: nowrap;
    font-family: 'PermanentMarker-Regular';
    font-size: 2.3rem;
    transform: rotate( 90deg );
    transform-origin: left top;
  }

  .scrap-magazine{
    height: 100%;
    transition: 0.5s;
  }

  .scrap-index{
    position: relative;
    background-color: #f7b801;
    transition: 0.5s;
    height: 100%;
    width: 100%;
  }

  .scrap-index .scrap-index-title {
    position: absolute;
    top: 3%;
    left: 100%;
    margin-top: 100px;
    white-space: nowrap;
    font-family: 'PermanentMarker-Regular';
    font-size: 2.3rem;
    transform: rotate( 90deg );
    transform-origin: left top;
  }

  .recent-magazine {
    height: 100%;
    transition: 0.5s;
  }
  
  .recent-index {
    position: relative;
    background-color: #f35b04;
    transition: 0.5s;
    height: 100%;
    width: 100%;
  }

  .recent-index .recent-index-title {
    position: absolute;
    top: 3%;
    left: 100%;
    margin-top: 100px;
    white-space: nowrap;
    font-family: 'PermanentMarker-Regular';
    font-size: 2.3rem;
    transform: rotate( 90deg );
    transform-origin: left top;
  }

  .horizontal-title {
    font-size: 3rem;
    font-weight: 600;
    margin-left: 4%;
  }

  .underline {
    background-color: black;
    height: 0.5px;
    width: 35%;
    margin-left: 4%;
    /* margin-bottom: 2%; */
  }

  .hand-drawing {
    position: absolute;
    background: url(https://cdn2.bustle.com/nylon/2020/scribble-920a660055.svg) no-repeat;
    top: 4%;
    left: 7%;
    height: 13%;
    width: 25%;
  }
</style>