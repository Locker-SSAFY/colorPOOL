<template>
  <div class="magazine-template-b wrap">
    <div class="template-b-left">
      <div class="interview wrap">
        <div>
          <span class="question">{{question}}</span>
        </div>
        <div>
          <span contenteditable @blur="changeAnswer" class="answer">{{answer}}</span>
        </div>
      </div>
      <span contenteditable  @blur="changeMent" :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">{{ment}}</span>
    </div>
    <div class="template-b-right"  :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">
      <img :src="image.url" @click="getProductName(image.url)">
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const magazineStore = 'magazineStore';

export default {
  name: 'templateB',
  props: {
    image: {
      default: void 0
    },
    index: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(magazineStore, {storeProductNames: 'GE_PRODUCT_NAMES'})
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
    this.image.template = 1;
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

  .interview.wrap {
    position: absolute;
    top: 100px;
    height: 200px;
    width: 40%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* align-items: center;  */
  }

  .interview.wrap span{
    color: black;
    font-size: 20px;
    filter: none;
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