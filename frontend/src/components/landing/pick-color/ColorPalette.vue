<template>
  <div class="color-palette">
    <div class="material-color-left">
      <ul class="color-category">
        <li v-for="color in this.materialColors" v-bind:key="color.color">
          <div class="color-preview" v-bind:style="{'background-color' : color.variations[4].hex}" @click="changeColorCategory(color.variations[4].hex, color.variations)"></div>
        </li>
      </ul>
    </div>
    <div class="material-color-right">
      <ul class="color-variation">
        <li v-for="variation in this.selectedColorVariation" v-bind:key="variation.hex">
          <div class="color-variation-preview" v-bind:style="{'background-color' : variation.hex}" @click="changeColor(variation.hex)"></div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import materialColors from '../../../assets/color/colorList.js'


export default {
  data() {
    return {
      materialColors : materialColors,
      selectedColorCategory : '#EF5350',
      selectedColorVariation : this.$parent.selectedVariation,
    }
  },
  created() {
    if(this.$parent.selectedVariation.length == 0)
      this.selectedColorVariation = this.materialColors[0].variations;
  },
  methods: {
    changeColorCategory(color, variation) {
      this.selectedColorVariation = variation;
      this.$parent.selectedVariation = variation;
    },
    changeColor(hex) {
      this.$parent.selectedColor = hex;
    }
  }
}
</script>

<style>    
  .color-palette {
    position: absolute;
    width: 40%;
    height: 80%;
    margin-top: 5%;
    left: 5%;
    display: flex;
  }

  .color-palette .material-color-left {
    width: 20%;
    height: 100%;
    float: left;
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
    width: 100%;
    height: 90%;
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
  }

  .color-palette .material-color-right {
    width: 80%;
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
  }

  .color-palette .material-color-right .color-variation-preview {
    width: 100%;
    height: 90%;
    border-radius: 15px;
  }
  
</style>