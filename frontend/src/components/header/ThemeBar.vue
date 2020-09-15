<template>
  <div class="theme-bar wrap">
    <div class="theme-color" v-for="color in colorList" v-bind:key="color" v-bind:style="{'background-color' : color}" >
      
    </div>
  </div>
</template>

<script>
export default {
  name: 'ThemeBar',
  data () {
    return {
      theme: {
        color1: "#e63946",
        color2: "#f1faee",
        color3: "#a8dadc",
        color4: "#457b9d",
        color5: "#1d3557",
      },
      colorList : []
    }
  },
  created() {
    this.colorList.push(this.theme.color1);
    this.colorList.push(this.theme.color2);
    this.colorList.push(this.theme.color3);
    this.colorList.push(this.theme.color4);
    this.colorList.push(this.theme.color5);
    console.log(this.colorList);
  },
  methods: {
    
  }
}
</script>

<style scoped>
  .theme-bar.wrap {
    position: fixed;
    top: 0;
    left: 0;
    width: 20px;
    height: 100%;
    transition-duration: 300ms;
  }

  .theme-bar.wrap:hover {
    width: 100px;
    height: 100%;
  }

  .theme-color {
    width : 100%;
    height: 20%;
  }
</style>