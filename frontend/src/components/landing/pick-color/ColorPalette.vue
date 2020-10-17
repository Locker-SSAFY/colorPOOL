<template>
  <div class="color-palette">
    <div class="material-color-left">
      <ul class="color-category">
        <li v-for="color in this.materialColors" v-bind:key="color.color">
          <div class="color-preview" :class="{pick : color.variations[4].hex == selectedColorCategory}" v-bind:style="{'background-color' : color.variations[4].hex}" @click="changeColorCategory(color.variations[4].hex, color.variations)"></div>
        </li>
      </ul>
    </div>
    <div class="material-color-right">
      <ul class="color-variation">
        <li v-for="variation in this.selectedColorVariation" v-bind:key="variation.hex">
          <div class="color-variation-preview" v-bind:style="{'background-color' : variation.hex}" @click="changeColor(variation)"></div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import materialColors from '../../../assets/color/colorList.js'
import colorNames from '../../../assets/color/static_colorNames.js'
import { mapActions } from 'vuex'
const colorStore = 'colorStore'

export default {
  data() {
    return {
      materialColors : materialColors,
      selectedColorCategory : '#EF5350',
      selectedColorVariation : this.$parent.selectedVariation,
      colorNames: colorNames
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
    changeColor(variation) {
      const payload = { selectedColor: variation };
      this.AC_SELECTED_COLOR(payload);  
    }
  }
}
</script>

<style scoped>    
  .color-palette {
    position: absolute;
    width: 40%;
    height: 80%;
    margin-top: 7%;
    left: 14%;
    display: flex;
  }

  .color-palette .material-color-left {
    width: 15%;
    height: 100%;
    float: left;
    margin-right: 10px;
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

  .color-palette .material-color-left .color-preview.pick {
    /* box-shadow: 2px 5px 5px 0px black; */
    box-shadow: 2px 5px 10px 0px rgba(0,0,0,0.75);
    margin-left: 0%;
    height: 90%;
    width: 100%;
  }

  .color-palette .material-color-right {
    width: 70%;
    height: 100%;
    float: right;
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