<template>
<div>
  <div class="theme-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    <div class="show-color left"  v-bind:style="{'background-color' : selectedColor}">
      <div class="color-code top">
        {{selectedColor}}
      </div>
      <div class="color-code bottom">
        {{selectedColor}}
      </div>
    </div>

    <div class="show-theme">
      <v-icon
        class="ma-2"
        size="30"
      >
      mdi-refresh</v-icon>
      <div class="button-text">POOL</div>
      
      <div class="underline"></div>
      <SelectTone></SelectTone>
      <ThemeScroll></ThemeScroll>
    </div>

    <v-btn
        class="goback-button"
        icon
        text
        @click="goBack"
      >
        <v-icon size="100">mdi-arrow-left</v-icon>
      </v-btn>

    <div class="theme-color right" v-bind:style="{'background-color' : selectedColor}">
      <v-btn
        class="next-button"
        icon
        text
        @click="getTheme"
      >
        <v-icon size="100">mdi-arrow-right</v-icon>
      </v-btn>
    </div>
  </div>
</div>
</template>
<script> 
import SelectTone from './SelectTone'
import ThemeScroll from './ThemeScroll'

export default {
  name: 'RecommandTheme',
  computed: {
  },
  components: {
    SelectTone, ThemeScroll
  },
  data () {
    return {
      selectedColor: '#EF5350',
      selectedVariation: [],
    }
  },
  methods : {
    goBack(){
      window.scrollTo(0, 0);
    }
  }
}
</script>

<style scoped>
  .theme-color.wrap {
    width: 50%;
    height: 100%;
    float: left;
    transition-duration: 300ms;
  }

  .theme-color.wrap.active {
    width: 100%;
    max-height: 100%;
  }

  .theme-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .show-color.left{
    position: absolute;
    left: 10%;
    top: 135%;
    height: 30%;
    width: 13%;
    align-items: center;
  }

  .color-code.top{
    padding-top: 3px;
    text-align: center;
    color: white;
    background-color: black;
    height: 10%;
  }

  .color-code.bottom{
    padding-top: 7px;
    text-align: center;
    position: absolute;
    bottom: 0%;
    height: 13%;
    width: 100%;
    color: white;
    background-color: black;
  }

  .show-theme {
    position: absolute;
    left: 27%;
    top: 117%;
    height: 70%;
    width: 40%;
  }

  .button-text{
    display: inline;
    font-size: 1.7rem;
    font-weight: bold;
  }

  .underline{
    position: absolute;
    left: 0%;
    top: 7%;
    background-color: #EF5350;
    width: 100%;
    height: 0.5%;
  }

  .theme-color.right {
    width:30%;
    height: 100%;
    position: absolute;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
  }

  .goback-button{
    position: absolute;
    top: 185%;
    left: 5%
  }

</style>