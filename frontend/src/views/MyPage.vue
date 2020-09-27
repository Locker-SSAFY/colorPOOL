<template>
<v-row>
  <div class="my-magazine" @click="show('my')" @mouseover="hover('my')" @mouseout="out('my')" :style="{'width' : myWidth+'%'}">

  </div>
  <div class="scrap-magazine" @click="show('scrap')" @mouseover="hover('scrap')" @mouseout="out('scrap')" :style="{'width' : scrapWidth+'%'}">

  </div>
  <div class="recent-magazine" @click="show('recent')" @mouseover="hover('recent')" @mouseout="out('recent')" :style="{'width' : recentWidth+'%'}">

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
      isecent: false,
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
        this.myWidth = 89.5;
        this.scrapWidth = 5;
        this.recentWidth = 5;
      } else if(name === 'scrap'){
        this.myWidth = 5;
        this.scrapWidth = 89.5;
        this.recentWidth = 5;
      } else if(name === 'recent'){
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
    background-color:blueviolet;
    height: 100%;
  }

  .scrap-magazine{
    background-color:burlywood;
    height: 100%;
  }
  .recent-magazine{
    background-color:chocolate;
    height: 100%;
  }
</style>