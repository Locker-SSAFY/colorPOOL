<template>
  <div class="pick-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    <v-card @click="clickPick()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isPick == null"
    >
      <v-card-title>Pick COLOR</v-card-title>
      
      <v-img
        class="white--text align-end"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>Pick Your Color from Palette</v-card-title>
      </v-img>
    </v-card>
    <ColorPalette v-if="this.$parent.isPick"></ColorPalette>
    <div v-if="this.$parent.isPick" class="pick-color right" v-bind:style="{'background-color' : selectedColor}">
      <img src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
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
</template>

<script>
import ColorPalette from './ColorPalette'

export default {
  name: 'PickColor',
  components: {
    ColorPalette
  },
  data () {
    return {
      selectedColor: '#EF5350',
      selectedVariation: [],
    }
  },
  methods : {
    clickPick() {
      this.$parent.isPick = true;
      this.$parent.isGet = false;
    },
    getTheme(){
      document.body.className = "unlock";
      console.log(document.body);
      window.scrollTo({left: 0,top: 1000, behavior: 'smooth'});
    }
  }
}
</script>

<style scoped>
  .pick-color.wrap {
    width: 50%;
    height: 100%;
    background-color: skyblue;
    float: left;
    transition-duration: 300ms;
  }

  .pick-color.wrap.active {
    width: 100%;
    max-height: 100%;
  }

  .pick-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .pick-color.wrap .v-card {
    position: absolute;
    left: 5%;
    width: 40%;
    height: 50%;
    margin-top: 15%;
  }

  .pick-color.wrap .v-card .v-card-title {
    height: 10%;
  }

  .pick-color.wrap .v-card .v-image {
    height: 90%;
  }

  .pick-color.right {
    width:30%;
    height: 100%;
    position: absolute;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .pick-color.right img {
    max-width: 100%;
    max-height: 80%;
    position: absolute; 
    right: 20%;
    
    display: block;
    margin: auto;
  }

  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
  }

</style>