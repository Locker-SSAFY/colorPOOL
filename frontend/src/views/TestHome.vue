<template>
    <v-container fluid fill-height style="overflow-x: hidden;" class="pa-0 ma-0">
      <div class="top-page" v-if="isPick || isGet">
        <v-btn v-bind="size"
          class="goback-button"
          icon text 
          @click="goBack"
          v-if="isPick || isGet">
          <v-icon v-bind="size">mdi-arrow-left</v-icon>
        </v-btn>
      </div>
      <v-row wrap>
        <v-col cols="12" sm="6" class="pick-get-card" v-if="!this.isGet">
          <PickColor></PickColor>
        </v-col>
        <v-col cols="12" sm="6" class="pick-get-card" v-if="!this.isPick">
          <GetColor></GetColor>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import { mapActions } from 'vuex';
import GetColor from '../components/landing/get-color/GetColorTest'
import PickColor from '../components/landing/pick-color/PickColorTest'
const colorStore = 'colorStore';

export default {
  name: 'TestHome',
  components : {
    GetColor, PickColor
  },
  data() {
    return {
      isPick: null,
      isGet: null,
      selectedColor: '',
    }
  },
  created() {

  },
  computed : {
    size() {
      const size = {xs:'medium',sm:'large',lg:'large',xl:'large'}[this.$vuetify.breakpoint.name];
      return size ? { [size]: true } : {}
    }
  },
  methods: {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    goBack() {
      this.isPick = null;
      this.isGet = null;
      const payload = {selectedColor: ''};
      this.AC_SELECTED_COLOR(payload);
    }
  }
}
</script>

<style scoped>
.top-page {
  position: absolute; 
  top:80px;
}
.pick-get-card {
  display: block;
  padding-right: 0;
}
</style>