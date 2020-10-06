<template>
  <div ref="detail" class="detail wrap">
    <v-carousel
      height="100%"
      width="100%"
      class="magazine-carousel wrap" 
      hide-delimiter-background
      delimiter-icon="mdi-minus"
      show-arrows-on-hover>
      <v-carousel-item
        v-for="(content, index) in magazine.contents" 
        :key="index"
        class="magazine-slide"
      >
        <TemplateA v-if="content.template == '0'" v-bind:content="content" v-bind:rgb="rgb"></TemplateA>
        <TemplateB v-if="content.template == '1'" v-bind:content="content" v-bind:rgb="rgb"></TemplateB>
        <TemplateC v-if="content.template == '2'" v-bind:content="content" v-bind:rgb="rgb"></TemplateC>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
import TemplateA from '../components/magazine/MagazineViewerA'
import TemplateB from '../components/magazine/MagazineViewerB'
import TemplateC from '../components/magazine/MagazineViewerC'
import {mapGetters, mapActions} from 'vuex'
const detailStore = 'detailStore'
const headerStore = 'headerStore'

export default {
  name: 'Detail',
  components: {
    TemplateA, TemplateB, TemplateC
  },
  computed: {
    ...mapGetters(detailStore, {
      storeDetail: 'GE_DETAIL'
    })
  },
  created() {
    this.magazine = this.storeDetail[0];
    this.AC_HEADER(true);
    console.log('magazine', this.magazine)
    var theme = this.magazine.selectedColor.themes[0];
    console.log(theme);
    const r = parseInt((theme.red1 + theme.red2 + theme.red3 + theme.red4 + theme.red5) / 5);
    const g = parseInt((theme.green1 + theme.green2 + theme.green3 + theme.green4 + theme.green5) / 5);
    const b = parseInt((theme.blue1 + theme.blue2 + theme.blue3 + theme.blue4 + theme.blue5) / 5);
    this.rgb = 'rgb(' + r + ',' + g + ',' + b + ')';
  },
  data() {
    return {
      magazine: null,
      rgb: '',
    }
  },
  methods: {
    ...mapActions(headerStore, ['AC_HEADER']),
  }
}
</script>

<style scoped>
  .detail.wrap {
    width: 100vw;
    height: 100vh;
  }
</style>