<template>
<div class="theme-info wrap">
  <h1 class="ml-8">YOUR COLOR SCHEME</h1>
  <div class="underline"></div>
  <div class="color-group mx-2">
    <div class="theme-colors" v-for="(color, index) in selectedTheme" :key="index" :style="{'background-color' : color}">
    </div>
  </div>
</div>
</template>
<script>
import { mapGetters} from 'vuex'
const colorStore = 'colorStore'

export default {
  computed: {
    ...mapGetters(colorStore, { storeSelectedTheme: 'GE_SELECTED_THEME'})
  },
  created(){
    this.selectedTheme = this.storeSelectedTheme;
  },
  data() {
    return {
      selectedTheme: null,
    }
  },
  watch: {
    storeSelectedTheme(val){
      this.selectedTheme = val
    }
  },
}
</script>
<style scoped>
  .theme-info.wrap{
  /* padding: 10%; */
  padding-top: 35%;
  height: 100%;
  width: 100%;
}
.color-group{
  position: absolute;
  top: 40%;
  height: 85px;
  width: 80%;
}
.theme-colors{
  display: inline-block;
  height: 100%; 
  width: 20%;
}
.underline{
  background-color: black;
  position: absolute;
  left: 0%;
  height: 2px;
  width: 80%;
}
</style>