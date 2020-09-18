<template>
  <v-container fluid fill-height class="color-palette-container">
    <v-row justify-center align-center class="color-palette">
      <v-col class="material-color-left" cols="12" sm="1">
        <ul class="color-category" v-if="this.size != 'medium'">
          <li v-for="color in this.materialColors" v-bind:key="color.color">
            <div class="color-preview" :class="{pick : color.variations[4].hex == selectedColorCategory}" :style="{'background-color': color.variations[4].hex}" @click="changeColorCategory(color.variations[4].hex, color.variations)"></div>
          </li>
        </ul>
        <ul class="color-sm-category" v-else>
          <div>
            <li v-for="color in this.materialColors" v-bind:key="color.color">
              <div class="color-sm-preview" style="padding: 0;" :class="{pick : color.variations[4].hex == selectedColorCategory}" :style="{'background-color': color.variations[4].hex}" @click="changeColorCategory(color.variations[4].hex, color.variations)"></div>
            </li>
          </div>
        </ul>
      </v-col>
      <v-col cols="12" sm="5" class="material-color-right">
        <ul class="color-variation">
          <li v-for="variation in this.selectedColorVariation" v-bind:key="variation.hex">
            <div class="color-variation-preview" v-bind:style="{'background-color' : variation.hex}" @click="changeColor(variation.hex)"></div>
          </li>
        </ul>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import materialColors from '../../../assets/color/colorList.js'
import { mapActions } from 'vuex'
const colorStore = 'colorStore'

export default {
  data() {
    return {
      materialColors : materialColors,
      selectedColorCategory: '#EF5350',
      selectedColorVariation: this.$parent.selectedVariation
    }
  },
  computed : {
    size() {
      const size = {xs:'medium',sm:'large',lg:'large',xl:'large'}[this.$vuetify.breakpoint.name];
      return size;
      // return size ? { [size]: true } : {}
    }
  },
  created() {
    // if(this.$parent.selectedVariation.length == 0)
    this.selectedColorVariation = this.materialColors[0].variations;
  },
  methods: {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    changeColorCategory(color, variation) {
      this.selectedColorVariation = variation;
      this.$parent.selectedVariation = variation;
      this.selectedColorCategory = color;
    },
  }
}
</script>

<style scoped>
  .color-palette-container {
    padding: 0 0;
  }

  .color-palette {
    position: absolute;
    width: 100%;
    height: 60%;
    display: flex;
  }

  .color-palette .material-color-left {
    width: 100%;
    float: left;
    margin-right: 10px;
    padding: 0;
  }

  .color-palette .material-color-left .color-category {
    width: 100%;
    height: 100%;
    list-style: none;
    padding: 0;
  }
  .color-palette .material-color-left .color-category li {
    height: 5%;
  }

  .color-palette .material-color-left .color-preview {
    width: 80%;
    height: 80%;
    margin-left: 20%;
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
    transition-duration: 300ms;
    box-shadow: 2px 5px 5px 0px rgba(0,0,0,0.5);
  }

  .color-sm-category {
    margin: 0;
    padding: 0;
    display: flex;
    list-style: none;
  }

  .color-sm-category div {
    display : flex;
    width: 100%;
    overflow-x: scroll;
  }


  .color-sm-category .color-sm-preview {
    width: 40px;
    height: 40px;
    margin-left: 5px;
    padding: 0;
  }

  .color-sm-category li:nth-child(1) div{
    margin-left: 20px;
  }

  .color-palette .material-color-right {
    width: 70%;
    height: 100%;
    float: right;
    padding : 0;
  }

  .color-palette .material-color-right .color-variation {
    width: 100%;
    height: 100%;
    list-style: none;
    padding: 0;
  }

  .color-palette .material-color-right .color-variation li {
    height: 10%;
    display:flex;
    justify-content: center;
    align-items: center;
  }

  .color-palette .material-color-right .color-variation-preview {
    width: 100%;
    height: 90%;
    border-radius: 5px;
  }

</style>