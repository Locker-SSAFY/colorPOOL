<template>
<v-row>
  <div class="my-magazine" @click="show('my')" @mouseover="hover('my')" @mouseout="out('my')" :style="{'width' : myWidth+'%'}">
    <div class="my-index">
      <div class="content-area" v-if="isMyMagazine">
      </div>
    </div>
  </div>
  <div class="scrap-magazine" @click="show('scrap')" @mouseover="hover('scrap')" @mouseout="out('scrap')" :style="{'width' : scrapWidth+'%'}">
      <div class="scrap-index">
        <div class="content-area" v-if="isScrap"></div>
      </div>
  </div>
  <div class="recent-magazine" @click="show('recent')" @mouseover="hover('recent')" @mouseout="out('recent')" :style="{'width' : recentWidth+'%'}">
    <div class="recent-index">
      <div class="content-area" v-if="isRecent"></div>
    </div>
  </div>
</v-row>
</template>
<script>
export default {
  data(){
    return{
      myWidth: 89.5,
      scrapWidth: 5,
      recentWidth: 5,
      isMyMagazine: true,
      isScrap: false,
      isRecent: false,
    }
  },
  methods: {
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