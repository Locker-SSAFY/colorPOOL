<template>
  <v-app>
    <head>
      <title>ColorPOOL</title>
    </head>
    <Header v-if="!isViewer"></Header>
    <router-view/>
  </v-app>
</template>

<script>
import Header from './components/header/Header'
import {mapGetters} from 'vuex'
const headerStore = 'headerStore'

export default {
  name: 'App',
  computed: {
    ...mapGetters(headerStore, {
      storeHeader: 'GE_HEADER'
    })
  },
  created() {
    this.isViewer = this.storeHeader
  },
  watch: {
    storeHeader(val) {
      this.isViewer = val;
    }
  },
  methods: {
  },
  components: {
    Header
  },

  data: () => ({
    //
    isViewer: false,
  }),
};
</script>