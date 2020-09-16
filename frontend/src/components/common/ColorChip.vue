<template>
  <v-card @click="showMsg" class="color-chip wrap" :class="{'elevation-10' : isOn}" v-bind:style="{'background-color' : color}">
    <!-- <v-card-title class="chip-title justify-center">{{color}}</v-card-title> -->
  </v-card>
</template>

<script>
export default {
  name: 'ColorChip',
  props : {
    color: void 0,
    index: void 0,
  },
  data () {
    return {
      isOn: true,
    }
  },
  methods: {
    showMsg() {
      this.isOn = !this.isOn;
      if(this.index == 0) {
        this.$parent.isColorOne = this.isOn;
      } else if(this.index == 1) {
        this.$parent.isColorTwo = this.isOn;
      } else if(this.index == 2) {
        this.$parent.isColorThree = this.isOn;
      } else if(this.index == 3) {
        this.$parent.isColorFour = this.isOn;
      } else if(this.index == 4) {
        this.$parent.isColorFive = this.isOn;
      }
    }
  }
}
</script>

<style scoped>
  .color-chip.wrap {
    width: 50px;
    height: 50px;
  }

  .color-chip.wrap .chip-title {
    font-size: 13px;
    background-color: rgb(0,0,0);
    padding: 1px 5px;
    width: 100%;
    position: relative;
    bottom: 0;
    color: white;
    text-align: center;
  }

 
</style>