<template>
  <div class="magazine-template-a wrap">
    <div class="template-a-left" :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">
      <img :src="image.url" @click="getProductName(image.url)">
    </div>
    <div class=template-a-right>
      <span contenteditable  @blur="changeMent" :style="{'background-color' : 'rgb(' + image.r + ',' + image.g + ',' + image.b + ')'}">{{ment}}</span>
      <br><br>
      <h3 v-if="productNames.length != 0">{{productNames}}</h3>
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
  name: 'templateA',
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
    this.image.template = 0;
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
  .magazine-template-a.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }
  .template-a-left {
    width: 50%;
    height: 100%;
    float: left;
    display: flex;
    justify-content: center;
  }

  .template-a-left img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
  }

  .template-a-right {
    width: 50%;
    height: 100%;
    float: right;
    background-color: white;
    color: black;
  }
  .template-a-right span {
    background: inherit;
    background-clip: text;
    color: transparent; 
    font-size: 45px;
    font-weight: 600;
    filter: invert(1);
    margin-left: -100px;
  }

  .interview.wrap {
    margin-top: calc(50% - 200px);
    height: 200px;
    width: 80%;
    margin-left: 10%;
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