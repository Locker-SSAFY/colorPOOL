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
      <carousel-3d class="carousel wrap" style="width: 100%; height: 70%;" :class="{'isSelected' : isSelected}">
        <slide v-for="(url, index) in imageList" :key="index" :index='index' style="width: 300px; height: 300px; border: none; border-radius: 5px;  visibility: visible;" class="color-chip-back" :style="{'background-image' : 'url(' + url + ')'}">
          <img :src="url" style="z-index: 4; width: 100%; height: 100%; object-fit: contain;" v-on:dblclick="addUrl(url)">
        </slide>
      </carousel-3d>
      
    </div>
    <div class="image-select-view">
      <div class="image-select-text">
        <!-- <strong>YOUR CHOICE</strong> -->
        <strong v-if="selectImageList.length == 0">이미지를 더블 클릭해서 추가하세요</strong>
        <strong v-else-if="selectImageList.length <= 10">당신이 선택한 {{selectImageList.length}}개의 이미지</strong>
        <strong v-else>이미지는 최대 10개까지만 가능해요 : {{selectImageList.length}}개</strong>
      </div>
      <div ref="sic" class="selected-image-container" @wheel="zoom">
        <div class="selected-image-wrap" v-for="(url, index) in selectImageList" v-bind:key="index" >
        <img :src="url">
        <v-btn @click="deleteImage(index)" class="minus-icon-btn" icon text color="red">
          <v-icon large>mdi-minus-circle</v-icon>
        </v-btn>
        </div>
      </div>
    </div>
  </div>
</template> 
<script>
import {Carousel3d, Slide} from 'vue-carousel-3d';
import ColorChip from '../../common/ColorChip'

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
      isScroll : true
    }
  },
  created() {
    this.imageList = [];
  },
  watch: {
    category(val) {
      console.log(val);
      console.log(val, this.images);
      console.log(this.images[0].url);
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
    fillImageList () {
      this.imageList = [];
      if(this.isColorOne) {
        this.imageList = this.imageList.concat(this.images[0].url)
      }
      if(this.isColorTwo) {
        this.imageList = this.imageList.concat(this.images[1].url)
      }
      if(this.isColorThree) {
        this.imageList = this.imageList.concat(this.images[2].url)
      }
      if(this.isColorFour) {
        this.imageList = this.imageList.concat(this.images[3].url)
      }
      if(this.isColorFive) {
        this.imageList = this.imageList.concat(this.images[4].url)
      }
      console.log(this.imageList)
      this.isSelected = true;
    }, 
    addUrl(url) {
      var flag = false;
      this.selectImageList.forEach((ele) => {
        if(ele == url) {
          flag = true;
          return;
        }
      })
      if(!flag) {
        this.selectImageList.unshift(url)
        this.$refs['sic'].scrollLeft = 0;
      }
      console.log(this.selectImageList)
    },
    deleteImage(index) {
      console.log(index)
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
      // this.$refs['sic'].scrollLeft += (amount * 2);
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
    height: 81%;
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