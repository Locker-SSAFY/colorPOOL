<template>
  <div class="category wrap">
    
    <div class="category-description">
      <strong>PRODUCT</strong><br>
      <strong>IN YOUR</strong><br>
      <strong>COLOR</strong>
    </div>
    <div class="category-list">
      <table>
        <tr>
          <td>
            <span class="category-name">리빙</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickLiving"
            >
              <v-icon size="100">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">악세사리</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickAccesory"
            >
              <v-icon size="100">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
        </tr>
        <tr>
          <td>
            <span class="category-name">패션</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickFashion"
            >
              <v-icon size="100">mdi-arrow-right</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">영화</span>
            <img class="category-layer" src="../../../assets/images/layer.png" :style="{'background-color' : this.$parent.selectedColor}">
            <v-btn
              class="category-button"
              icon
              text
              @click="pickMovie"
            >
              <v-icon size="100">mdi-arrow-right</v-icon>
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
      theme: {
        color1: "#e63946",
        color2: "#f1faee",
        color3: "#a8dadc",
        color4: "#457b9d",
        color5: "#1d3557",
      }
    }
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

  .category.wrap .category-description {
    float: left;
    height: 100%;
    width: 30%;
    /* background-color: yellow; */
    padding: 10px 100px;
    font-size: 45px;
  }

  .category.wrap .category-list {
    float: right;
    position: absolute;
    padding-top: 50px;
    top: 0;
    right: 0;
    height: 100%;
    width: 50%;
    /* background-color: skyblue; */
  }

  .category.wrap .category-list table {
    margin-top: 10%;
    height: 60%;
    width: 80%;
    /* background-color: lightcyan; */
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
    /* background-color: lightcoral; */
  }

  .category.wrap .category-list table .category-button {
    position: absolute;
    margin-top : 200px;
    margin-left : -60px;
  }
</style>