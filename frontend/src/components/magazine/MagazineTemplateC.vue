<template>
  <div class="magazine-template-c wrap" :style="{'background': 'linear-gradient(to bottom, rgb(' + image.r + ',' + image.g + ',' + image. b + '), 50%, white 50% 100%)'}">
    <div class="template-c-left">
      <img :src="image.url">
    </div>
    <div class="template-c-right">
      <span :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">{{this.ment}}</span>
    </div>
  </div>
</template>

<script>
import ments from '../../assets/ment/mentList.js'
import { mapGetters, mapActions } from 'vuex'
const magazineStore = 'magazineStore';

export default {
  name: 'templateC',
  props: {
    image: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(magazineStore, {storeProductNames: 'GE_PRODUCT_NAMES'}),
  },
  data() {
    return {
      ments: ments,
      ment: '',
      productNames: null, 
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
    this.image.template = 2;
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
  .magazine-template-c.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }

  .template-c-left {
    width: 50%;
    height: 100%;
    float: left;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-c-left img {
    max-width: 90%;
    max-height: 90%;
    object-fit: contain;
  }

  .template-c-right {
    width: 50%;
    height: 100%;
    float: right;
  }

  .template-c-right span {
    background: inherit;
    background-clip: text;
    color: transparent;
    font-size: 45px;
    font-weight: 600;
    filter: invert(1);
    position: absolute;
    top: calc(50% - 60px);
  }
</style>