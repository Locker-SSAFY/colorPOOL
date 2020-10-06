<template>
  <v-app>
    <head>
      <title>ColorPOOL</title>
    </head>
    <Header style="z-index: 88;"></Header>
    <router-view/>
  </v-app>
</template>

<script>
import Header from './components/header/Header'

export default {
  name: 'App',

  components: {
    Header
  },

  data: () => ({
    //
  }),
};
</script>