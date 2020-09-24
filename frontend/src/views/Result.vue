<template>
  <div class="result wrap">
    <div class="left">
      <Cover></Cover>
    </div>
    <div class="right">
      <table>
        <tr>
          <td>
            <span class="category-name">내잡지에<br>추가하기</span>
            <img class="category-layer" src="../assets/images/layer.png" :style="{'background-color' : this.color}">
            <v-btn
              class="category-button"
              icon
              text
              @click="addMagazine"
            >
              <v-icon size="100">mdi-plus</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">PDF 문서<br>저장하기</span>
            <img class="category-layer" src="../assets/images/layer.png" :style="{'background-color' : this.color}">
            <v-btn
              class="category-button"
              icon
              text
              @click="saveAsPDF"
            >
              <v-icon size="100">mdi-content-save</v-icon>
            </v-btn>
          </td>
          <td>
            <span class="category-name">메인으로<br>돌아가기</span>
            <img class="category-layer" src="../assets/images/layer.png" :style="{'background-color' : 'white'}">
            <v-btn
              class="category-button"
              icon
              text
              @click="goMain"
            >
              <v-icon size="100">mdi-keyboard-return</v-icon>
            </v-btn>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
// import Cover from '../components/magazine/magazineCover'
// import Book from '../components/magazine/book'
import Cover from '../components/magazine/magazineCover'
const colorStore = 'colorStore'

export default {
  name: 'Result',
  components: {
    Cover,
  },
  data() {
    return {
      color: '',
      theme: []
    }
  },
  created() {
    this.color = this.storeSelectedColor;
    this.theme = this.storeSelectedTheme;
    if(this.storeSelectedColor == '') 
      this.color = "#7986CB";
    if(this.storeSelectedTheme == null)
      this.theme = ["#e63946", "#f1faee", "#a8dadc", "#457b9d", "#1d3557"];
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR', storeSelectedTheme: 'GE_SELECTED_THEME'})
  },
  methods: {
    addMagazine() {
      alert('구현 예정입니다!')
    },
    saveAsPDF(){
      alert('구현 예정입니다!')
    },
    goMain() {
      var res = confirm('추가 또는 저장되지 않은 잡지는 사라집니다. 메인으로 돌아가시겠습니까?')
      if(res) {
        this.$router.push({ name: 'Landing' });
      } 
    }
  }
}
</script>

<style scoped>
  .result.wrap {
    width: 100%;
    height: 100vh;
    padding-top: 80px;
    display: flex;
  }

  .result.wrap .left {
    width: 40%;
    height: 100%;
    float: left;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .result.wrap .right {
    width: 60%;
    height: 100%;
    float: right;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .result.wrap .right table {
    height: 60%;
    width: 80%;
  }

  .result.wrap .right table tr td {
    padding: 10px 10px;
    text-align: center;
  }

  .result.wrap .right table .category-name {
    position: absolute;
    margin-top : 10px;
    margin-left: 10px;
    font-size: 25px;
    font-weight: 600;
  }

  .result.wrap .right table .category-layer {
    height: 45%;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }

  .result.wrap .right table .category-button {
    position: absolute;
    margin-top : 200px;
    margin-left : -60px;
  }
</style>