<template>
  <div class="magazine-template-c wrap" :style="{'background': 'linear-gradient(to bottom, rgb(' + image.r + ',' + image.g + ',' + image. b + '), 50%, white 50% 100%)'}">
    <div class="template-c-left">
      <img :src="image.url">
    </div>
    <div class="template-c-right">
      <span contenteditable  @blur="changeMent" :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">{{ment}}</span>
      <div class="interview wrap">
        <div>
          <span class="question">{{question}}</span>
        </div>
        <div>
          <span contenteditable @blur="changeAnswer" class="answer">{{answer}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const magazineStore = 'magazineStore';

export default {
  name: 'templateC',
  props: {
    image: {
      default: void 0
    },
    index: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(magazineStore, {storeProductNames: 'GE_PRODUCT_NAMES'}),
  },
  data() {
    return {
      productNames: null, 
      ment: this.image.mainText,
      question: this.image.question,
      answer: this.image.answer,
    }
  },
  created() {
    this.productNames = this.storeProductNames;
    console.log(this.image);
    this.image.template = 2;
    this.storeMagazineImageOne();
  },
  watch: {
    storeProductNames(val){
      this.productNames = val;
    }
  },
  methods: {
    ...mapActions(magazineStore, ['AC_PRODUCT_DETECT', 'AC_MAGAZINE_IMAGES_ONE']),
    getProductName(url){
      const payload = { url }
      this.AC_PRODUCT_DETECT(payload);
    },
    changeMent(event) {
      if(event.target.innerText.length == 0) {
        event.target.innerText = this.image.mainText;
      }
      else {
        this.image.mainText = event.target.innerText;
        this.storeMagazineImageOne();
      }
    },
    changeAnswer(event) {
      if(event.target.innerText.length == 0) {
        event.target.innerText = this.image.answer;
      } else {
        this.image.answer = event.target.innerText;
        this.storeMagazineImageOne();
      }
    },
    storeMagazineImageOne() {
      const payload = {
        'index': this.index,
        'image': this.image
      }
      this.AC_MAGAZINE_IMAGES_ONE(payload);
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
    /* top: calc(50% - 60px); */
    top: 2.5rem;
  }

  .interview.wrap {
    margin-top: calc(50% + 60px);
    height: 200px;
    width: 80%;
    margin-left: 10%;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .interview.wrap span{
    color: black;
    font-size: 20px;
    filter: none;
    position: static;
    margin-left: 0;
  }

  .interview.wrap div:nth-child(1) {
    text-align: center;
    width: 100%;
    float: top;
  }
  .interview.wrap div:nth-child(2) {
    text-align: center;
    width: 100%;
    float: bottom;
  }

  .interview.wrap .question {
    font-size: 25px;
  }

  .interview.wrap .answer {
    color: slategray;
  }
</style>