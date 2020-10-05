<template>
  <div class="magazine-template-b wrap">
    <div class="template-b-left">
      <span :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">{{this.ment}}</span>
    </div>
    <div class="template-b-right"  :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">
      <img :src="image.url" @click="getProductName(image.url)">
    </div>
  </div>
</template>

<script>
import ments from '../../assets/ment/mentList.js'
import { mapGetters, mapActions } from 'vuex'
const magazineStore = 'magazineStore';

export default {
  name: 'templateB',
  props: {
    image: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(magazineStore, {storeProductNames: 'GE_PRODUCT_NAMES'})
  },
  data() {
    return {
      ments: ments,
      ment: '',
      productNames: null
    }
  },
  created() {
    this.productNames = this.storeProductNames;
    console.log(this.image);
    console.log(this.ments);
    this.ments.forEach((ele) => {
      if(ele.category == this.image.category) {
        this.ment = ele.content[Math.floor(Math.random() * ele.content.length)];
      }
    })
    this.image.template = 1;
  },
  watch: {
    storeProductNames(val){
      this.productNames = val;
    }
  },
  methods: {
    ...mapActions(magazineStore, ['AC_PRODUCT_DETECT']),
    getProductName(url){
      const payload = { url }
      this.AC_PRODUCT_DETECT(payload);
    }
  }
}
</script>

<style scoped>
  .magazine-template-b.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }
  .template-b-left {
    width: 50%;
    height: 100%;
    float: left;
    background-color: white;
    color: black;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-b-left span {
    background: inherit;
    background-clip: text;
    color: transparent;
    font-size: 45px;
    font-weight: 600;
    filter: invert(1);
  }

  .template-b-right {
    width: 50%;
    height: 100%;
    float: right;
    display: flex;
    justify-content: center;
  }

  .template-b-right img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
  }
</style>