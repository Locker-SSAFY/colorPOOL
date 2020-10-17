<template>
<div style="padding-bottom: 50px;">
<div class="wrapper" v-for="(colorM, index) in colors" v-bind:key="index">
  <div class="chip" v-for="(color, idx) in colorM" v-bind:key="idx">
    <div class="color" :style="{'background-color' : color.variations[4].hex}"></div>
    <span class="text" style="color: white">{{color.color}}</span>
  </div>
</div>
</div>
</template>

<script>
import materialColors from '../assets/color/colorList.js'
export default {
  data() {
    return {
      materialColors: materialColors,
      colors: []
    }
  },
  created() {
    for(var i = 0; i < 20; i+=5) {
      this.colors.push(new Array(this.materialColors[i], this.materialColors[i + 1], this.materialColors[i + 2], this.materialColors[i + 3], this.materialColors[i + 4]))
    }
  }
}
</script>

<style scoped>
  * {
    font-family: 'Helvetica Neue';
  }
  .wrapper {
    padding-top: 100px;
    padding-left: 100px;
    height: 400px;
    width: auto;
    display: flex;
    overflow-x: hidden;
  }

  .chip {
    position: relative;
    width: 200px;
    height: 300px;
    background-color: black;
    margin-top: 10px;
    margin-left: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .color {
    position: absolute;
    top: 5%;
    width: 90%;
    height: 70%;
  }

  .text {
    position: absolute;
    font-size: 30px;
    bottom: 8%;
    font-weight: 700;
  }
</style>