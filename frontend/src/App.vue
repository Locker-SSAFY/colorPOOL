<template>
  <v-app>
    <head>
      <title>ColorPOOL</title>
    </head>
    <Header></Header>
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
<style>
  *{ font-family: 'Spoqa Han Sans', 'Spoqa Han Sans JP', 'Sans-serif'; }
</style>