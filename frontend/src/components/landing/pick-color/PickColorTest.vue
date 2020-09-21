<template>
  <v-container fluid fill-height pa-0>
    <v-row v-if="this.$parent.isPick == null" justify="center" align="center" class="pick-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
      <v-col sm="9" cols="10">
        <v-card @click="clickPick()" class="mx-auto elevation-10" v-if="this.$parent.isPick == null">
          <v-card-title>PICK COLOR</v-card-title>
          <v-img
            class="white--text align-end"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <v-card-title>Pick Your Color By Keyword</v-card-title>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-row v-if="this.$parent.isPick">
      <v-col cols="12" sm="6">
        <ColorPalette></ColorPalette>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import ColorPalette from './ColorPaletteTest'
const colorStore = 'colorStore'

export default {
  name: 'PickColor',
  components: {
    ColorPalette
  },
  data() {
    return {
      selectedColor: '',
      selectedVariation : [],
    }
  },
  created() {
    this.selectedColor = this.storeSelectedColor;
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  methods: {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    clickPick() {
      this.$parent.isPick = true;
      this.$parent.isGet = false;
      const payload = { selectedColor: '#EF5350'};
      this.AC_SELECTED_COLOR(payload);
    },
    getTheme() {
      document.body.className = "unlock";
      window.scrollTo({left: 0, top: 1000, behavior: 'smooth'})
    }
  }
}
</script>

<style scoped>
  .pick-color.wrap {
    width: 100%;
    height: 100%;
  }

  /* .pick-color.wrap.active {
    width: 100%;
    height: 100%;
  }
  .pick-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  } */
</style>