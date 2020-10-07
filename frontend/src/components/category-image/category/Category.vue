<template>
  <div class="category wrap">
    
    <div class="category-title">
      <p>PRODUCT</p>
      <p>IN YOUR</p>
      <p>COLOR</p>
    </div>
    <div class="category-desc">
      <p>Get an image with</p>
      <p>the color scheme</p>
      <p>you choose</p>
    </div>
    
    <div class="hand-drawing"></div>
    <div class="category-list">
      <table>
        <tr>
          <td>
            <span class="category-name">리빙</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor.hex}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickLiving"
            >
              <v-icon size="75">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">악세사리</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor.hex}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickAccesory"
            >
              <v-icon size="75">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
        </tr>
        <tr>
          <td>
            <span class="category-name">패션</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor.hex}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickFashion"
            >
              <v-icon size="75">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">영화</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor.hex}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickMovie"
            >
              <v-icon size="75">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>


export default {
  name: 'Category',
  components: {
    
  },
  data() {
    return {
      theme: this.$parent.theme  
    }
  },
  created(){
    
  },
  methods : {
    pickLiving() {
      this.$parent.category = 'living';
      document.body.className = "unlock";
      window.scrollTo({left: 0,top: 4000, behavior: 'smooth'});
    },
    pickAccesory() {
      this.$parent.category = 'accesory';
      window.scrollTo({left: 0,top: 4000, behavior: 'smooth'});
    },
    pickFashion() {
      this.$parent.category = 'fashion';
      window.scrollTo({left: 0,top: 4000, behavior: 'smooth'});
    },
    pickMovie() {
      this.$parent.category = 'movie';
      window.scrollTo({left: 0,top: 4000, behavior: 'smooth'});
    }
  }
}
</script>

<style scoped>
  .category.wrap {
    width: 100%;
    height: 100%;
    display : flex;
    padding-top: 80px;
    padding-left: 20px;
  }

  .category.wrap .category-desc {
    position: absolute;
    font-size: 2.3rem;
    text-align: left;
    left: 20%;
    bottom: 15%;
    transform: rotate(-15deg);
    z-index: 1;
  }

  .category.wrap .category-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.7;
  }

  .category.wrap .category-title {
    margin-left: 5%;
    margin-top: 3%;
  }

  .category.wrap .category-title p {
    font-family: 'PermanentMarker-Regular';
    font-size: 3.5rem;
    line-height: 0.9;
    /* color: white; */
  }

  .category.wrap .category-list {
    float: right;
    position: absolute;
    padding-top: 110px;
    top: 0;
    right: 0;
    height: 100%;
    width: 50%;
  }

  .category.wrap .category-list table {
    margin-top: 10%;
    height: 60%;
    width: 80%;
  }

  .category.wrap .category-list table tr td {
    padding: 10px 10px;
    text-align: center;
  }

  .category.wrap .category-list table .category-name {
    position: absolute;
    margin-top : 10px;
    margin-left: 10px;
    font-size: 25px;
    font-weight: 600;
  }

  .category.wrap .category-list table .category-layer {
    height: 45%;
  }

  .category.wrap .category-list table .category-button {
    position: absolute;
    margin-top : 200px;
    margin-left : -60px;
  }

  .hand-drawing {
    position: absolute;
    background: url(https://cdn2.bustle.com/nylon/2020/related-6439ff09ad.svg) no-repeat;
    top: 35%;
    left: 6%;
    height: 13%;
    width: 50%;
  }
</style>