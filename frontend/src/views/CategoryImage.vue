<template>
  <div class="category-image wrap">
    <Category></Category>
    <ImageList
      v-bind:category="category"
      v-bind:images="images"
    ></ImageList>
  </div>
</template>

<script>
import Category from '../components/category-image/category/Category'
import ImageList from '../components/category-image/image/Image'

import { mapGetters, mapActions } from 'vuex'
const imageStore = 'imageStore'

export default {
  name: 'CategoryImage',
  components: {
    Category, ImageList
  },
  computed : {
    
    ...mapGetters(imageStore, 
      {
        acImage: 'GE_ACCESORY_IMAGE', 
        fsImage: 'GE_FASHION_IMAGE',
        lvImage: 'GE_LIVING_IMAGE',
        mvImage: 'GE_MOVIE_IMAGE'
      })
  },
  watch : {
    category(val) {
      this.category = val;
      if(val == 'living') {
        this.images = this.livingImages;
      } else if (val == 'accesory') {
        this.images = this.accesoryImages;
      } else if (val == 'fashion') {
        this.images = this.fashionImages;
      } else if (val == 'movie') {
        this.images = this.movieImages;
      }
    }
  },
  created() {
    this.AC_ACCESORY_IMAGE();
    this.AC_FASHION_IMAGE();
    this.AC_LIVING_IMAGE();
    this.AC_MOVIE_IAMGE();
    this.accesoryImages = this.acImage;
    this.fashionImages = this.fsImage;
    this.livingImages = this.lvImage;
    this.movieImages = this.mvImage;
  },
  data() {
    return {
      category: '',
      images: [],
      accesoryImages : [],
      fashionImages: [],
      livingImages: [],
      movieImages: [],
    }
  },
  methods : {
    ...mapActions(imageStore, ['AC_ACCESORY_IMAGE', 'AC_FASHION_IMAGE', 'AC_LIVING_IMAGE', 'AC_MOVIE_IAMGE']),
  }
}
</script>

<style scoped>
  .category-image.wrap {
    width: 100%;
    height: 100%;
    background-color: lightgoldenrodyellow;
  }
</style>