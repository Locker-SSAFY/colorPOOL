<template>
  <div class="magazine-template-a wrap">
    <div class="template-a-left" :style="{'background-color' : image.color}">
      <img :src="image.url">
    </div>
    <div class=template-a-right>
      <span :style="{'background-color' : image.color}">{{this.ment}}</span>
    </div>
  </div>
</template>

<script>
import ments from '../../assets/ment/mentList.js'
export default {
  name: 'templateA',
  props: {
    image: {
      default: void 0
    }
  },
  computed: {
    
  },
  data() {
    return {
      ments: ments,
      ment: '',
    }
  },
  created() {
    console.log(this.image);
    console.log(this.ments);
    this.ments.forEach((ele) => {
      if(ele.category == this.image.category) {
        this.ment = ele.content[Math.floor(Math.random() * ele.content.length)];
      }
    })
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
</style>