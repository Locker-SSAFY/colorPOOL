<template>
  <div class="magazine wrap">
    <!-- <carousel :width="100" class="magazine-carousel wrap" :per-page="1">
      <slide class="magazine-slide" v-for="(img, index) in images" :key="index">
        <TemplateA v-bind:image="img" ></TemplateA>
      </slide>
    </carousel> -->
    <v-carousel
      height="100%"
      width="100%"
      class="magazine-carousel wrap" 
      hide-delimiter-background
      delimiter-icon="mdi-minus"
      show-arrows-on-hover>
      <v-carousel-item
        v-for="(img, index) in images" 
        :key="index"
        class="magazine-slide"
      >
        <v-radio-group style="background-color: rgba(0,0,0,0.5); position: absolute; top: 7px; padding: 0.5rem;" v-model="img.template">
          <p style="font-family: 'PermanentMarker-Regular'; font-size: 1.1rem; font-weight: 600;">Type of Template</p>
          <v-radio :key="0" :value="0" label="Template A"></v-radio>
          <v-radio :key="1" :value="1" label="Template B"></v-radio>
          <v-radio :key="2" :value="2" label="Template C"></v-radio>
          <!-- <v-radio>12</v-radio> -->
        </v-radio-group>
        <TemplateA v-if="img.template == 0" v-bind:image="img" v-bind:index="index"></TemplateA>
        <TemplateB v-if="img.template == 1" v-bind:image="img" v-bind:index="index"></TemplateB>
        <TemplateC v-if="img.template == 2" v-bind:image="img" v-bind:index="index"></TemplateC>
      </v-carousel-item>
    </v-carousel>
    <div class="magazine-button-wrap">
      <v-btn
        class="magazine-result-button"
        icon
        text
        @click="goNext"
        >
          <v-icon size="75">mdi-arrow-right</v-icon>
        </v-btn>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
// import { Carousel, Slide } from "vue-carousel"
import TemplateA from '../components/magazine/MagazineTemplateA'
import TemplateB from '../components/magazine/MagazineTemplateB'
import TemplateC from '../components/magazine/MagazineTemplateC'

const magazineStore = 'magazineStore'

export default {
  name: 'Magazine',
  components: {
    // Carousel, Slide, 
    TemplateA, TemplateB, TemplateC
  },
  computed: {
    ...mapGetters(magazineStore, {
      storeMagazineImages: 'GE_MAGAZINE_IMAGES'
    })
  },
  data() {
    return {
      images: [],
    }
  },
  created() {
    this.images = this.storeMagazineImages;
    console.log('잡지 이미지', this.images);    
  },
  methods: {
    goNext() {
      console.log(this.images);
      this.$router.push({ name: 'Result' });
    }
  }
}
</script>

<style scoped>
  .magazine.wrap {
    width: 100%;
    height: 100vh;
    padding-top: 80px;
  }
  .magazine-carousel.wrap {
    width: 100%;
    height: 100%;
  }

  .magazine-slide {
    width: 100%;
    height: 100%;
  }

  .magazine-button-wrap {
    position: absolute;
    bottom: 40px;
    right: 40px;
  }
</style>