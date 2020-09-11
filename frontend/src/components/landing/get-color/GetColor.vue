<template>
  <div class="get-color wrap" :class="[{active : this.$parent.isGet}, {deactive : this.$parent.isPick}]">
    <v-card @click="clickGet()"
      class="mx-auto elevation-10"
      v-if="this.$parent.isGet == null"
    >
      <v-card-title>Get COLOR</v-card-title>
      
      <v-img
        class="white--text align-end"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>Get Your Color By Keyword</v-card-title>
      </v-img>
    </v-card>
    <div v-if="this.$parent.isGet" class="search-wrap">
      <div class="search-panel">
        <input v-model="keyword" placeholder="keyword">
        <v-btn @click="getPicularImages()" class="ma-2" tile large color="yellow" icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </div>
    </div>
    <div v-if="this.$parent.isGet" class="get-color left">
      <ul v-for="colorList in this.picularImages" v-bind:key="colorList.index">
        <li v-for="color in colorList" v-bind:key="color.color">
          <v-card class="mx-auto elevation-5">
            <v-img class="white--text align-end" :src="color.img">
              <v-overlay :absolute="true" :opacity="0" class="image-layer" v-bind:style="{'background-color': color.color}"></v-overlay>  
            </v-img>
          </v-card>
        </li>
      </ul>
     
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'GetColor',
  data () {
    return {
      picularResult: {},
      picularImages: [],
      keyword: '',
      absolute: true,
    opacity: 1,
    overlay: false,
    }
  },
  computed: {
  },
  methods: {
    clickGet() {
      this.$parent.isPick = false;
      this.$parent.isGet = true;
    }, async getPicularImages() {
      await axios.get('https://server.picular.co/' + this.keyword)
      .then((res) => {
        this.picularResult = res.data
        this.picularImages = [];
        this.picularImages.push(this.picularResult.colors.slice(0, 5));
        this.picularImages.push(this.picularResult.colors.slice(5, 10));
        this.picularImages.push(this.picularResult.colors.slice(10, 15));
        this.picularImages.push(this.picularResult.colors.slice(15, 20));
      })
      .catch((err) => {
        console.log(err);
      })
    }
  }
}
</script>

<style>
  .get-color.wrap {
    width: 50%;
    height: 100%;
    background-color: yellow;
    float: right;
    transition-duration: 300ms;
  }

  .get-color.wrap.active {
    width: 100%;
    height: 100%;
  }

  .get-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .get-color.wrap .v-card {
    position: absolute;
    right: 5%;
    width: 40%;
    height: 50%;
    margin-top: 15%;
  }

  .get-color.wrap .v-card .v-card-title {
    height: 10%;
  }

  .get-color.wrap .v-card .v-image {
    height: 90%;
  }

  .get-color.wrap .search-wrap {
    position: absolute;
    right: 5%;
    width: 40%;
    height: 50%;
    margin-top: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .get-color.wrap .search-wrap .search-panel {
    width: 100%;
    background-color: white;
    text-align: center;
  }

  .get-color.wrap .search-wrap .search-panel input {
    width: 50%;
    height: 100%;
    padding: 1% 1%;
    outline: none;
  }

  .get-color.wrap .get-color.left {
    position: absolute;
    margin-top: 10%;
    width: 50%;
    height: 80%;
    padding-left: 1%;
    display: flex;
    overflow: auto;
  }

  .get-color.left ul {
    width: 100%;
    height: 100%;
    list-style: none;
    padding: 0;
  }

  .get-color.left ul li {
    width: 100%;
    text-align: center;
  }

  .get-color.left ul li .v-card {
    position: relative;
    width: 90%;
    background-color: black;
  }
  .get-color.left ul li .image-layer {
    opacity: 1;
    transition-duration: 300ms;
  }

  .get-color.left ul li .image-layer:hover {
    opacity: 0.1;
  }

</style>