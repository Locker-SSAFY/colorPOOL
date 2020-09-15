<template>
<div class="theme-info wrap">
  <h1>YOUR COLOR SCHEME</h1>
  <div class="underline"></div>

  <!-- <div class="color-group">
    <div class="theme-colors" :style="{'background-color' : theme.color1}">
    </div>
    <div class="theme-colors" :style="{'background-color' : theme.color2}">
    </div>
    <div class="theme-colors" :style="{'background-color' : theme.color3}">
    </div>
    <div class="theme-colors" :style="{'background-color' : theme.color4}">
    </div>
    <div class="theme-colors" :style="{'background-color' : theme.color5}">
    </div>
  </div> -->
</div>
</template>
<script>
export default {
  data() {
    return {
      theme : {
        color1: "#e63946",
        color2: "#f1faee",
        color3: "#a8dadc",
        color4: "#457b9d",
        color5: "#1d3557",
      },
    }
  }
}
</script>
<style scoped>
  .theme-info.wrap{
  padding: 10%;
  padding-top: 40%;
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
</style>