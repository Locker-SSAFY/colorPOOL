<template>
  <div class="theme-bar wrap">
    <div class="theme-color" v-for="color in colorList" v-bind:key="color" v-bind:style="{'background-color' : color}" >
      
    </div>
  </div>
</template>

<script>
export default {
  name: 'ThemeBar',
  props: {
    colorList: void 0
  },
  data () {
    return {
    }
  },
  created() {
    
  },
  methods: {
    
  }
}
</script>

<style scoped>
  .theme-bar.wrap {
    position: fixed;
    top: 80px;
    left: 0;
    width: 20px;
    height: calc(100% - 80px);
    transition-duration: 300ms;
    z-index: 5;
  }

  .theme-bar.wrap:hover {
    width: 100px;
  }

  .theme-color {
    width : 100%;
    height: 20%;
  }
</style>