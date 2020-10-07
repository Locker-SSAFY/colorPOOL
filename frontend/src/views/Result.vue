<template>
  <div class="result wrap">
    <div class="left">
      <Cover v-bind:nowDate="date" v-bind:magazineName="magazineName"></Cover>
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
            <span class="category-name">현재잡지<br>인쇄하기</span>
            <img class="category-layer" src="../assets/images/layer.png" :style="{'background-color' : this.color}">
            <v-btn
              class="category-button"
              icon
              text
              @click="saveAsPDF"
            >
              <v-icon size="80">mdi-printer</v-icon>
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
import { mapActions, mapGetters } from 'vuex'
// import Cover from '../components/magazine/magazineCover'
// import Book from '../components/magazine/book'
import Cover from '../components/magazine/magazineCover'
const colorStore = 'colorStore'
const userStore = 'userStore'
const magazineStore = 'magazineStore'
const detailStore = 'detailStore'

export default {
  name: 'Result',
  components: {
    Cover,
  },
  data() {
    return {
      color: '',
      theme: [],
      userInfo: null,
      date: null,
      magazineName: 'ColorPOOL'
    }
  },
  created() {
    this.color = this.storeSelectedColor.hex;
    this.theme = this.storeSelectedTheme;
    // userInfo 가져오는거 왜 안되지? 재원이한테 물어볼 것
    this.userInfo = this.storeUserInfo;
    console.log(this.userInfo);
    // userInfo 가져오는거 해결해주세염
    if(this.storeSelectedColor == '') 
      this.color = "#7986CB";
    if(this.storeSelectedTheme == null)
      this.theme = ["#e63946", "#f1faee", "#a8dadc", "#457b9d", "#1d3557"];
    this.date = new Date();
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR', storeSelectedTheme: 'GE_SELECTED_THEME', storeSelectedThemeId: 'GE_SELECTED_THEME_ID', storeThemes: 'GE_THEMES'}),
    ...mapGetters(userStore, {storeUserInfo: 'GE_USER_INFO'}),
    ...mapGetters(magazineStore, {storeMagazineImages: 'GE_MAGAZINE_IMAGES'})
  },
  methods: {
    ...mapActions(magazineStore, ['AC_MAGAZINE_POST']),
    ...mapActions(detailStore, ['AC_DETAIL']),
    addMagazine() {
      alert('구현 예정입니다!')
      const payload = {
        'date': this.date,
        'magazineName': this.magazineName,
        'selectedColor': this.storeSelectedColor.id,
        'themeId': this.storeSelectedThemeId,
      }
      console.log(payload);
      this.AC_MAGAZINE_POST(payload);
    },
    saveAsPDF(){
      const data = [
        {
          "user": {
            "email": "kang@kang.com",
            "nickname" : "kang",
          },
          "selectedColor": {
            "themes": this.storeThemes,
          },
          "contents" : this.storeMagazineImages,
        }      
      ];
      this.AC_DETAIL(data);
      let route = this.$router.resolve({path: '/print'});
      window.open(route.href, 'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=1200,height=600,left=0, top=0, scrollbars=yes');
    },
    goMain() {
      var res = confirm('추가 또는 저장되지 않은 잡지는 사라집니다. 메인으로 돌아가시겠습니까?')
      if(res) {
        this.$router.push({ name: 'Landing' });
      } 
    }
  },
  watch: {
    magazineName(val) {
      console.log(val);
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