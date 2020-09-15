<template>
  <div class="image-list wrap">
    <carousel-3d class="carousel wrap">
      <slide v-for="(url, index) in images[0].url" :key="index" :index='index'>
        <img :src="url">
      </slide>
    </carousel-3d>
    <h1>{{category}}</h1>
    <div class="image-list-view">
      
    </div>
    <div class="image-select-view">

    </div>
  </div>
</template>

<script>
import {Carousel3d, Slide} from 'vue-carousel-3d';

export default {
  name: 'ImageList',
  components: {
    Carousel3d, Slide
  }, 
  props: {
    category : {
      default: void 0
    },
    images : {
      default: void 0
    }
  },
  data() {
    return {
    }
  },
  created() {
    
  },
  watch: {
    category(val) {
      console.log(val);
      console.log(this.images[0].url);
    }
  }
}
</script>

<style scoped>
  .image-list.wrap {
    width: 100%;
    height: 100%;
    padding-top : 80px;
    padding-left: 20px;
    /* background-color: cyan; */
  }
</style>