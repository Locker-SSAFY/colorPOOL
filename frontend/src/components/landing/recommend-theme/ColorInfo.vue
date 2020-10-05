<template>
<div class="color-info wrap">
  <h1>YOUR COLOR</h1>
  <div class="underline"></div>
  <div class="color-name wrap">
    <span class="color-name">{{color.name}}</span>
  </div>
</div>
</template>
<script>
import complementaryColors from 'complementary-colors'

export default {
  props: {
    color: {
      default: void 0
    },
  },
  data() {
    return {
      complementary: [],
      triad: [],
      square: [],
      analogous: [],
      splitComplementary: [],
      tetradic: [],
    }
  },
  created() {
    var myColor = new complementaryColors(this.color.hex);
    this.complementary = myColor.complementary();
    this.triad = myColor.triad();
    this.squre = myColor.square();
    this.analogous = myColor.analogous();
    this.splitComplementary = myColor.splitComplementary();
    this.tetradic = myColor.tetradic();
  }
}
</script>
<style scoped>
.color-info.wrap{
  padding: 5%;
  padding-top: 25%;
  height: 100%;
  width: 100%;
}

.underline{
  background-color: black;
  position: absolute;
  left: 0%;
  height: 2px;
  width: 80%;
}

.color-name.wrap {
  width: 100%;
}

.color-name.wrap .color-name {
  font-size: 35px;
  font-weight: 600;
}

</style>