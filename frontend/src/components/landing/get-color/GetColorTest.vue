<template>
  <v-container style="padding: 0" fluid fill-height>
    <v-row v-if="this.$parent.isGet == null" justify="center" align="center" class="get-color wrap" :class="[{active : this.$parent.isGet}, {deactive : this.$parent.isPick}]">
      <v-col sm="9" cols="10">
        <v-card @click="clickGet()" class="mx-auto elevation-10" v-if="this.$parent.isGet == null">
          <v-card-title>GET COLOR</v-card-title>
          <v-img
            class="white--text align-end"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <v-card-title>Get Your Color By Keyword</v-card-title>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  name: 'GetColor',
  data() {
    return {

    }
  },
  created() {
    
  },
  methods: {
    clickGet() {
      this.$parent.isPick = false;
      this.$parent.isGet = true;
    }
  }
}
</script>

<style scoped>
  .get-color.wrap {
    width: 100%;
    height: 100%;
  }

  /* .get-color.wrap.active {
    width: 100%;
    height: 100%;
  }

  .get-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  } */
</style>