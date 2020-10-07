<template>
<div>
  <div class="dim-layer" v-if="theme.length == 0">
    <div class="theme-loading">
      <img src="../../../assets/images/loading.gif">
    </div>
  </div>
  <div class="theme-color wrap" :class="[{active : this.$parent.isPick},{deactive : this.$parent.isGet}]">
    <div class="theme-layer" @click="notSelect()"></div>
    <!-- 선택한 색상칩 -->
    <div class="show-color left"  :style="{'background-color' : selectedColor.hex}">
      <div class="color-code top">
        {{selectedColor.name}}
      </div>
      <div class="color-code bottom">
        {{selectedColor.hex}}
      </div>
    </div>
    <!-- 추천 배색 보여주는 곳 -->
    <div class="show-theme">
      <div class="inner-theme">
        <div class="button-text">Color Scheme POOL</div>
        <div class="underline" :style="{'background-color' : selectedColor.hex}"></div>
        <div class="theme-scroll wrap">
          <div class="show-themes mt-8" v-for="(t, index) in theme" :key="index">
            <div class="color-group" @click="selectTheme(t.color1, t.color2, t.color3, t.color4, t.color5, t.id)">
              <div class="theme-colors" :style="{'background-color' : t.color1}">
              </div>
              <div class="theme-colors" :style="{'background-color' : t.color2}">
              </div>
              <div class="theme-colors" :style="{'background-color' : t.color3}">
              </div>
              <div class="theme-colors" :style="{'background-color' : t.color4}">
              </div>
              <div class="theme-colors" :style="{'background-color' : t.color5}">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="choose-desc">
      <p>Choose the color scheme</p>
      <p>you want</p>
    </div>
    
    <!-- 이전으로 돌아가기 -->
    <v-btn
      class="goback-button"
      icon
      text
      @click="goBack"
    >
      <v-icon size="70">mdi-arrow-left</v-icon>
    </v-btn>
    
    <!-- 배색 선택 안했을 경우 -->
    <div v-if="selectedTheme==null" class="show-color right" :style="{'background-color' : selectedColor.hex}">
      <ColorInfo v-bind:color="selectedColor"></ColorInfo>
    </div>

    <!-- 배색 선택한 경우 -->
    <div v-else class="theme-color right">
      <ThemeInfo></ThemeInfo>
      <v-btn
        class="next-button"
        icon
        text
        @click="goCategory"
      >
        <v-icon size="80">mdi-arrow-right</v-icon>
      </v-btn>

      <div class="button-desc">
        <p>Create a magazine by</p>
        <p>choosing images with the color scheme</p>
        <p>of your choice</p>
      </div>

    </div>
  </div>
</div>
</template>
<script> 
import { mapGetters, mapActions } from 'vuex'
import ColorInfo from './ColorInfo'
import ThemeInfo from './ThemeInfo'
const colorStore = 'colorStore'
const userStore = 'userStore'
const landingStore = 'landingStore'

export default {
  name: 'RecommandTheme',
  components: {
    ColorInfo,
    ThemeInfo,
  },
  computed: {
    ...mapGetters(userStore, {storeUserInfo: 'GE_USER_INFO',
                              storeDisplay: 'GE_DISPLAY',
                              storeIsLogin: 'GE_IS_LOGIN'}),
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR',
                               storeSelectedTheme: 'GE_SELECTED_THEME',
                               storeThemes: 'GE_THEMES'})
                               
  },
  created(){
    this.isLogin = this.storeIsLogin;
    this.userInfo = this.storeUserInfo;
    this.selectedColor = this.storeSelectedColor;
    this.selectedTheme = this.storeSelectedTheme;
    this.dialog = this.storeDisplay;
  },
  data () {
    return {
      isLogin: false,
      dialog: false,
      userInfo: null,
      selectedColor: '',
      selectedTheme: [],
      theme: [],
    }
  },
  watch: {
    storeSelectedColor(val){
      this.selectedColor = val
    },
    storeSelectedTheme(val){
      this.selectedTheme = val
    },
    storeUserInfo(val){
      this.userInfo = val;
    },
    storeDisplay(val){
      this.dialog = val;
    },
    storeIsLogin(val){
      this.isLogin = val;
    },
    storeThemes() {
      this.theme = [];
      this.storeThemes.forEach((th) => {
        var id = th.id;
        var color1 = 'rgb(' + th.red1 + ',' + th.green1 + ',' + th.blue1 + ')';
        var color2 = 'rgb(' + th.red2 + ',' + th.green2 + ',' + th.blue2 + ')';
        var color3 = 'rgb(' + th.red3 + ',' + th.green3 + ',' + th.blue3 + ')';
        var color4 = 'rgb(' + th.red4 + ',' + th.green4 + ',' + th.blue4 + ')';
        var color5 = 'rgb(' + th.red5 + ',' + th.green5 + ',' + th.blue5 + ')';
        const element = {
          'id': id,
          'color1' : color1,
          'color2' : color2,
          'color3' : color3,
          'color4' : color4,
          'color5' : color5,
        };
        this.theme.push(element);
      })
    }
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_THEME', 'AC_SELECTED_THEME_ID']),
    ...mapActions(userStore, ['AC_DISPLAY']),
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK', 'AC_IS_LANDING']),

    goBack(){
      const payload = {selectedTheme: null};
      this.AC_SELECTED_THEME(payload);
      this.theme = [];
      window.scrollTo(0, 0);
    },
    selectTheme(c1, c2, c3, c4, c5, id){
      const payload = { selectedTheme: [c1, c2, c3, c4, c5]};
      this.AC_SELECTED_THEME(payload);
      const payload2 = { selectedThemeId: id};
      this.AC_SELECTED_THEME_ID(payload2);
    },
    notSelect(){
      this.AC_SELECTED_THEME({selectedTheme: null});
      this.AC_SELECTED_THEME_ID({selectedThemeId: null});
    },
    goCategory() {
      if(this.isLogin == false){
        alert("더 많은 서비스를 이용하고 싶다면, 로그인을 먼저 해주세요!");
        this.AC_DISPLAY(true);
      } else {
        this.AC_IS_GET({isGet: false});
        this.AC_IS_PICK({isPick: false});
        this.AC_IS_LANDING({isLanding: false});
        this.$router.push({ name: 'CategoryImage' });
      }
    }
  }
}
</script>

<style scoped>
  .theme-color.wrap {
    width: 50%;
    height: 100%;
    float: left;
    transition-duration: 300ms;
  }

  .theme-layer{
    position: absolute;
    left: 0%;
    top: 100%;
    height: 100%;
    width: 65%;
    z-index: 49;
  }

  .theme-color.wrap.active {
    width: 100%;
    max-height: 100%;
  }

  .theme-color.wrap.deactive {
    width: 0%;
    height: 0%;
    opacity: 0;
  }

  .dim-layer {
    display: flex;
    align-items: center;
    justify-content: center;
    position: absolute;
    top:100%;
    width: 100%;
    height: 100%;
    z-index: 89;
    background-color: rgb(0, 0, 0, 0.6); 
  }
  .theme-loading img{
    z-index: 90;
    width: 250px;
  }

  .theme-scroll {
    position: relative;
    overflow: scroll;
    height: 78%;
  }

  .color-group{
    height: 85px;
    width: 90%;
    margin-left: 5%;
  }
  .theme-colors{
    display: inline-block;
    height: 100%; 
    width: 20%;
  }
  .show-color.left{
    position: absolute;
    left: 7%;
    top: 126%;
    height: 30%;
    width: 13%;
    align-items: center;
  }

  .color-code.top{
    padding-top: 3px;
    text-align: center;
    color: white;
    background-color: black;
    height: 10%;
  }

  .color-code.bottom{
    padding-top: 7px;
    text-align: center;
    position: absolute;
    bottom: 0%;
    height: 13%;
    width: 100%;
    color: white;
    background-color: black;
  }

  .show-theme {
    position: absolute;
    left: 25%;
    top: 100%;
    height: 70%;
    width: 35%;
    z-index: 50;
  }

  .inner-theme{
    margin-top: 35%;
    height: 100%; 
  }

  .button-text {
    display: inline;
    font-family: 'PermanentMarker-Regular';
    font-size: 2.1rem;
    font-weight: bold;
    margin-left: 4rem;
  }

  .underline{
    position: absolute;
    left: 0%;
    top: 7%;
    width: 100%;
    height: 2px;
    margin-top: 35%;
  }

  .show-color.right {
    width:30%;
    height: 100%;
    position: absolute;
    top: 100%;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .theme-color.right {
    width:35%;
    height: 100%;
    position: absolute;
    top: 100%;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .next-text{
    position: absolute;
    right: 20%;
    top: 80%;
  }

  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
    z-index: 50;
  }

  .goback-button{
    position: absolute;
    top: 185%;
    left: 5%;
    z-index: 50;
  }

  .button-desc {
    position: absolute;
    font-size: 1.9rem;
    text-align: left;
    right: 15%;
    bottom: 10%;
    transform: rotate(-15deg)
  }

  .button-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.7;
  }

  .choose-desc {
    position: absolute;
    font-size: 1.9rem;
    text-align: left;
    right: 50%;
    top: 170%;
    transform: rotate(-15deg)
  }

  .choose-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.7;
  }
</style>