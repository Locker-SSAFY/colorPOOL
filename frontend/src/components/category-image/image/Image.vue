<template>
  <div class="image-list wrap">
    <div class="image-list-view">
      <div class="image-category-text">
        <strong>"{{category.toUpperCase()}}"</strong><strong> IMAGES</strong>
      </div>
      <div class="color-chip-wrap">
        <!-- <ColorChip class="color-chip-element" v-for="(color, index) in colorList" v-bind:key="index" v-bind:color="color" v-bind:index="index"></ColorChip> -->
        <ColorChip class="color-chip-element" v-bind:color="colorList[0]" v-bind:index="0"></ColorChip>
        <ColorChip class="color-chip-element" v-bind:color="colorList[1]" v-bind:index="1"></ColorChip>
        <ColorChip class="color-chip-element" v-bind:color="colorList[2]" v-bind:index="2"></ColorChip>
        <ColorChip class="color-chip-element" v-bind:color="colorList[3]" v-bind:index="3"></ColorChip>
        <ColorChip class="color-chip-element" v-bind:color="colorList[4]" v-bind:index="4"></ColorChip>
      </div>
      <carousel-3d class="carousel wrap" style="width: 100%; height: 50%;" :class="{'isSelected' : isSelected}">
        <slide v-for="(img, index) in imageList" :key="index" :index='index' style="width: 300px; height: 300px; border: none; border-radius: 5px;  visibility: visible;" class="color-chip-back" :style="{'background-image' : 'url(' + img.url + ')'}">
          <img :src="img.url" style="z-index: 4; width: 100%; height: 100%; object-fit: contain;" v-on:dblclick="addUrl(img.color, img.url)">
        </slide>
      </carousel-3d>
      <v-btn
        class="category-list-button"
        icon
        text
        @click="goCategory"
      >
        <v-icon size="100">mdi-arrow-left</v-icon>
      </v-btn>
    </div>
    <div class="image-select-view">
      <div class="image-select-text">
        <!-- <strong>YOUR CHOICE</strong> -->
        <strong v-if="selectImageList.length == 0">이미지를 더블 클릭해서 추가하세요</strong>
        <strong v-else-if="selectImageList.length <= 10">당신이 선택한 {{selectImageList.length}}개의 이미지</strong>
        <strong v-else>이미지는 최대 10개까지만 가능해요 : {{selectImageList.length}}개</strong>
      </div>
      <div ref="sic" class="selected-image-container" @wheel="zoom">
        <div class="selected-image-wrap" v-for="(img, index) in selectImageList" v-bind:key="index" >
        <img :src="img.url">
        <v-btn @click="deleteImage(index)" class="minus-icon-btn" icon text color="red">
          <v-icon large>mdi-minus-circle</v-icon>
        </v-btn>
        </div>
      </div>
      <div class="edit-magazine-button">
        <v-btn
        class="category-list-button"
        icon
        text
        @click="goNext"
        >
          <v-icon size="100">mdi-arrow-right</v-icon>
        </v-btn>
      </div>
    </div>
  </div>
</template> 
<script>
import {Carousel3d, Slide} from 'vue-carousel-3d';
import ColorChip from '../../common/ColorChip'
import {mapActions} from 'vuex'
import ments from '../../../assets/ment/mentList.js'
import questions from '../../../assets/ment/questionList.js'
const magazineStore = 'magazineStore'

export default {
  name: 'ImageList',
  components: {
    Carousel3d, Slide, ColorChip
  }, 
  props: {
    category : {
      default: void 0
    },
    images : {
      default: void 0
    },
    colorList: void 0,
  },
  data() {
    return {
      isColorOne : true,
      isColorTwo : true,
      isColorThree : true,
      isColorFour : true,
      isColorFive : true,
      imageList : [],
      isSelected : false,
      selectImageList : [],
      isScroll : true,
      ments: ments,
      questions: questions,
    }
  },
  created() {
    this.imageList = [];
  },
  watch: {
    category() {
      this.fillImageList();
    },
    isColorOne() {
      this.fillImageList();
    },
    isColorTwo() {
      this.fillImageList();
    },
    isColorThree() {
      this.fillImageList();
    },
    isColorFour() {
      this.fillImageList();
    },
    isColorFive() {
      this.fillImageList();
    }
  },
  methods : {
    ...mapActions(magazineStore, ['AC_MAGAZINE_IMAGES']),
    fillImageList () {
      this.imageList = [];
      if(this.isColorOne) {
        const color = this.images[0].color
        this.images[0].url.forEach((ele) => {
          this.imageList = this.imageList.concat({color: color, url: ele})
        });
      }
      if(this.isColorTwo) {
        const color = this.images[1].color
        this.images[1].url.forEach((ele) => {
          this.imageList = this.imageList.concat({color: color, url: ele})
        });
      }
      if(this.isColorThree) {
        const color = this.images[2].color
        this.images[2].url.forEach((ele) => {
          this.imageList = this.imageList.concat({color: color, url: ele})
        });
      }
      if(this.isColorFour) {
        const color = this.images[3].color
        this.images[3].url.forEach((ele) => {
          this.imageList = this.imageList.concat({color: color, url: ele})
        });
      }
      if(this.isColorFive) {
        const color = this.images[4].color
        this.images[4].url.forEach((ele) => {
          this.imageList = this.imageList.concat({color: color, url: ele})
        });
      }
      this.isSelected = true;
    }, 
    addUrl(color, url) {
      var flag = false;
      console.log(this.$parent.colorList);
      this.selectImageList.forEach((ele) => {
        if(ele.url == url) {
          flag = true;
          return;
        }
      })
      if(!flag) {
        // rgb 평균값을 이용해서 배경색 만들기
        var rgbs = this.$parent.colorList;
        var r = 0;
        var g = 0;
        var b = 0;
        rgbs.forEach((rgb) => {
          var temp = rgb.replace( /[^%,.\d]/g, "" ); 
          temp = temp.split( "," );
          r += temp[0] - 0;
          g += temp[1] - 0;
          b += temp[2] - 0;
        })
        r = Math.floor(r / 5);
        g = Math.floor(g / 5);
        b = Math.floor(b / 5);
        
        // 더미 멘트를 미리 저장해두기
        const category = this.$parent.category;
        var ment = '';
        this.ments.forEach(ele => {
          if(ele.category == category) {
            ment = ele.content[Math.floor(Math.random() * ele.content.length)]; 
          }
        })
        
        // 더미 질문을 미리 저장해두기
        var question = questions[Math.floor(Math.random() * questions.length)];
        var answer = '인터뷰에 답변을 적어주세요'
        let payload = {
          category: this.$parent.category,
          color: color,
          url: url,
          // 잡지 템플릿 : 초기는 0
          template: 0,
          // 더미 멘트
          mainText: ment,
          subText: 'shoes',
          // 더미 질문
          question: question,
          // 더미 대답
          answer: answer,
          // 배색 조합
          colorList: this.colorList,
          // rgb 배색 조합
          rgbs : rgbs,
          r: r,
          g: g,
          b: b
        }
        this.selectImageList.unshift(payload)
        this.$refs['sic'].scrollLeft = 0;
      }
    },
    deleteImage(index) {
      this.selectImageList.splice(index, 1);
    },
    zoom(event) {
      event.preventDefault();
      var amount = event.deltaY;
      if(!this.isScroll) return;
      if(amount > 0) {
        this.$refs['sic'].scrollLeft += 500;
      } else {
        this.$refs['sic'].scrollLeft -= 500;
      }
      this.isScroll = false;
      setTimeout(() => {
        this.isScroll = true;
      }, 200)
    },
    goCategory() {
      window.scrollTo({left: 0, top: 0, behavior: 'smooth'})
    },
    goNext() {
      if(this.selectImageList.length == 0) {
        return;
      } else if (this.selectImageList.length > 10) {
        alert('10개를 넘으면 안됩니다')
      } else {
        const payload = {
          magazineImages: this.selectImageList
        }
        this.AC_MAGAZINE_IMAGES(payload);
        this.goCategory();
        this.$router.push({ name: 'Magazine' });
      }
    }
  }
}
</script>
<style scoped>
  .image-list.wrap {
    width: 100%;
    height: calc(100% - 120px);
    padding-top : 80px;
    padding-left: 20px;
    display: flex;
  }

  .image-list.wrap .image-list-view {
    width : calc(50% - 30px);
    height: 100%;
    margin-left: 30px;
    float: left;
  }

  .image-list.wrap .image-select-view {
    width: 50%;
    height: 100%;
    float: right;
  }

  .carousel-3d-container .carousel .wrap .isSelected{
    height: 51%;
  }

  .image-list-view .image-category-text {
    margin-top: 15px;
    height: 10%;
  }

  .image-list-view .image-category-text strong {
    font-size: 45px;
  }

  .color-chip-wrap {
    display: flex;
    height: 10%;
  }

  .color-chip-back:before {
    content: '';
    position: absolute;
    top: 0; left: 0; right: 0; bottom: 0;
    background: inherit;
    z-index:-1;
    -webkit-filter: blur(8px); 
    filter: blur(8px); 
  }

  .image-list.wrap .image-list-view .color-chip-element:nth-child(1) {
    margin-left: 0;
  }

  .image-list.wrap .image-list-view .color-chip-element {
    margin-left: 20px;
  }

  .image-list.wrap .image-list-view .carousel.wrap {
    width: 100%;
    height: auto;
  }

  .image-select-view .image-select-text {
    width: 90%;
    height: 10%;
    margin-top: 15px;
    margin-left: 5%;
    font-size: 30px;
  }

  .image-select-view .selected-image-container {
    width: 90%;
    margin-left: 5%;
    height: 450px;
    overflow-x: visible;
    overflow-x: scroll;
    scroll-behavior: smooth;
    display: flex;
    padding: 30px 20px;
  }

  .image-select-view .selected-image-container .selected-image-wrap {
    height: 100%;
    width: 100%;
    display: flex;
  }

  .image-select-view .selected-image-container .selected-image-wrap img {
    height: 100%;
    margin-right: 30px;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }

  .image-select-view .selected-image-container .selected-image-wrap .minus-icon-btn {
    width: 10px;
    position: relative;
    right: 60px;
  }
</style>