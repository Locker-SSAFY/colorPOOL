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
        <TemplateA v-bind:image="img"></TemplateA>
      </v-carousel-item>
    </v-carousel>
    <div class="magazine-button-wrap">
      <v-btn
        class="magazine-result-button"
        icon
        text
        @click="goNext"
        >
          <v-icon size="100">mdi-arrow-right</v-icon>
        </v-btn>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
// import { Carousel, Slide } from "vue-carousel"
import TemplateA from '../components/magazine/magazineTemplateA'

const magazineStore = 'magazineStore'

export default {
  name: 'Magazine',
  components: {
    // Carousel, Slide, 
    TemplateA
  },
  computed: {
    ...mapGetters(magazineStore, {
      storeMagazineImages: 'GE_MAGAZINE_IMAGES'
    })
  },
  data() {
    return {
      images: []
    }
  },
  created() {
    this.images = this.storeMagazineImages;
    console.log(this.images);
    this.images=[{
      'category': "living",
      'color': "#457b9d",
      'url':"https://images.unsplash.com/photo-1551516595-09cb9fcf8db5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80"
    },
    {
      'category': "living",
      'color': "#1d3557",
      'url': "https://images.unsplash.com/photo-1533779283484-8ad4940aa3a8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80"
    }]
    
  },
  methods: {
    goNext() {
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