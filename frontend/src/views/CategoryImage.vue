<template>
  <div class="category-image wrap">
    <ThemeBar v-bind:colorList="colorList"></ThemeBar>
    <Category></Category>
    <ImageList
      v-bind:category="category"
      v-bind:images="images"
      v-bind:colorList="colorList"
    ></ImageList>
  </div>
</template>

<script>
import ThemeBar from '../components/header/ThemeBar'
import Category from '../components/category-image/category/Category'
import ImageList from '../components/category-image/image/Image'

import { mapGetters, mapActions } from 'vuex'
const imageStore = 'imageStore'
const colorStore = 'colorStore'

export default {
  name: 'CategoryImage',
  components: {
    Category, ImageList, ThemeBar
  },
  computed : {
    ...mapGetters(colorStore, {
      storeColor: 'GE_SELECTED_COLOR',
      storeTheme: 'GE_SELECTED_THEME'
    }),
    ...mapGetters(imageStore, 
      {
        storeAcImage: 'GE_ACCESORY_IMAGE', 
        storeFsImage: 'GE_FASHION_IMAGE',
        storeLvImage: 'GE_LIVING_IMAGE',
        storeMvImage: 'GE_MOVIE_IMAGE'
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
    },
  },
  created() {
    // 이미지들 가져오기
    window.scrollTo(0, 0);
    this.AC_ACCESORY_IMAGE();
    this.AC_FASHION_IMAGE();
    this.AC_LIVING_IMAGE();
    this.AC_MOVIE_IAMGE();
    // 가져온 이미지들 저장
    this.accesoryImages = this.storeAcImage;
    this.fashionImages = this.storeFsImage;
    this.livingImages = this.storeLvImage;
    this.movieImages = this.storeMvImage;
    // 배색 5개를 리스트에 넣기
    // this.colorList.push(this.theme.color1);
    // this.colorList.push(this.theme.color2);
    // this.colorList.push(this.theme.color3);
    // this.colorList.push(this.theme.color4);
    // this.colorList.push(this.theme.color5);
    // 배색 5개를 리스트에 넣기
    this.colorList = this.storeTheme;
    this.selectedColor = this.storeColor;
  },
  data() {
    return {
      category: '',
      images: [],
      accesoryImages : [],
      fashionImages: [],
      livingImages: [],
      movieImages: [],
      theme: [],
      colorList : [],
      selectedColor: '',
    }
  },
  methods : {
    ...mapActions(imageStore, ['AC_ACCESORY_IMAGE', 'AC_FASHION_IMAGE', 'AC_LIVING_IMAGE', 'AC_MOVIE_IAMGE']),
  },
}
</script>

<style scoped>
  .category-image.wrap {
    width: 100%;
    height: 100%;
  }
</style>