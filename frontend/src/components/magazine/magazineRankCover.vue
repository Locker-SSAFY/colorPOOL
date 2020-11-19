<template>
  <div class="magazine-rank wrap" :style="{'background-color': color}" @click="showMag">
    <span class="cover-name">{{name}}</span>
    <span class="cover-date">{{date}}</span>
    <span class="cover-username">{{userName}}</span>
    <span class="cover-email">{{userEmail}}</span>
    <img class="cover-img" :src="coverImg">

    <div class="cover-theme" 
    :style="{'background': 'linear-gradient(to right, ' + this.theme[0] + ' 20%, ' + this.theme[1] + ' 20% 40%, ' + this.theme[2] + ' 40% 60%, ' + this.theme[3] + ' 60% 80%, ' + this.theme[4] + ' 80%)'}">

    </div>

  </div>
</template>

<script>
import materialColors from '../../assets/color/colorList.js'
import {mapActions, mapGetters} from 'vuex'
import axios from 'axios'
const detailStore = 'detailStore'
const themeStore = 'themeStore'

export default {
  name: 'MagazineRankCover',
  props: {
    rank: {
      default: void 0
    },
    aboutRanker: {
      default: void 0
    }
  },
  data() {
    return {
      name: '',
      color: '',
      theme: [],
      userName: '',
      userEmail: '',
      coverImg: '',
      date: '',
      mag_data: {},
      materialColors: materialColors,
      themeData: {}
    }
  },
  computed: {
    ...mapGetters(themeStore, {
      storeThemeColor: 'GE_THEME_COLOR'
    })
  },
  watch: {
    storeThemeColor(val) {
      console.log(val);
    },
    themeData(val) {
      this.mag_data[0].selectedColor.themes = val;
    }
  },
  methods: {
    ...mapActions(detailStore, ['AC_DETAIL']),
    ...mapActions(themeStore, ['AC_THEME_COLOR']),
    showMag() {
      var res = confirm("잡지를 보시겠습니까?")
      if(res) {
        // console.log(res)
        const payload = this.mag_data;
        this.AC_DETAIL(payload);
        let route = this.$router.resolve({path: '/detail'});
        window.open(route.href, 'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=1200,height=600,left=0, top=0, scrollbars=yes');
      } 
    },
    getThemeColorData(themeId) {
      const token = localStorage.getItem('access_token')
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      
      }
      axios.get('https://j3a303.p.ssafy.io/api/colors/' + themeId, {headers: header})
      // axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/colors/' + themeId, {headers: header})
      .then((res) => {
        this.themeData = res.data.data;
        const color1 = 'rgb(' + this.themeData.red1 + ',' + this.themeData.green1 + ',' + this.themeData.blue1 + ')';
        const color2 = 'rgb(' + this.themeData.red2 + ',' + this.themeData.green2 + ',' + this.themeData.blue2 + ')';
        const color3 = 'rgb(' + this.themeData.red3 + ',' + this.themeData.green3 + ',' + this.themeData.blue3 + ')';
        const color4 = 'rgb(' + this.themeData.red4 + ',' + this.themeData.green4 + ',' + this.themeData.blue4 + ')';
        const color5 = 'rgb(' + this.themeData.red5 + ',' + this.themeData.green5 + ',' + this.themeData.blue5 + ')';
        this.theme = [color1, color2, color3, color4, color5];
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      })
    }
  },
  created() {
    this.name = this.aboutRanker.magazineName;
    this.color = this.aboutRanker.color;
    this.theme = ['#1A44AD','#505C7A','#38C0E0','#E49872','#AD6A5B'];
    this.userName = this.aboutRanker.userNickname;
    this.userEmail = this.aboutRanker.email;
    this.coverImg = this.aboutRanker.contents[0].url;
    this.date = this.aboutRanker.createdDate;
    this.getThemeColorData(this.aboutRanker.themeId);
    // const payload = {
    //   'themeId': this.aboutRanker.themeId
    // }
    // this.AC_THEME_COLOR(payload)

    // this.name = this.aboutRanker.name;
    // this.color = this.aboutRanker.color;
    // this.theme = this.aboutRanker.theme;
    // this.userName = this.aboutRanker.userName;
    // this.userEmail = this.aboutRanker.userEmail;
    // this.coverImg = this.aboutRanker.coverImg;
    // this.date = this.aboutRanker.date;
    // this.likes = this.aboutRanker.likes;
    
    this.mag_data = 
      [
        {
          "id": 0,
          "user": {
            "id": 0,
            "email": this.userEmail,
            "nickname": this.userNickname,
            "provider": "root",
            "roles": [
              "ROLE_USER"
            ]
          },
          "selectedColor": {
            "id": 0,
            "color": {
              "red": 0,
              "green": 0,
              "blue": 0
            },
            "themes": [
            {
              "id": 1204,
              "red1": 152,
              "green1": 232,
              "blue1": 66,
              "red2": 199,
              "green2": 250,
              "blue2": 140,
              "red3": 255,
              "green3": 241,
              "blue3": 118,
              "red4": 150,
              "green4": 244,
              "blue4": 255,
              "red5": 77,
              "green5": 208,
              "blue5": 225,
              "selectedColor": null,
              "crawledImages": []
            }
            ]
          },
          "contents": this.aboutRanker.contents,
          "likes": [
            {
              "id": 0,
              "user": {
                "id": 0,
                "email": "string",
                "nickname": "string",
                "provider": "string",
                "roles": [
                  "string"
                ]
              }
            }
          ],
          "bookmarks": [
            {
              "id": 0,
              "user": {
                "id": 0,
                "email": "string",
                "nickname": "string",
                "provider": "string",
                "roles": [
                  "string"
                ]
              }
            }
          ],
          "createdDate": "2020-10-06T15:08:56.655Z"
        }
      ]
    
  
  }
}
</script>

<style scoped>
  @font-face {
    font-family: 'LibreBarcode128Text';
    src: url('../../assets/font/LibreBarcode128Text-Regular.ttf');
  }

  .magazine-rank.wrap {
    margin: 0px 40px;
    width: 280px;
    height: 400px;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }

  .magazine-rank.wrap span {
    position: absolute;
    color: transparent;
    background: inherit;
    background-clip: text;
    filter: invert(1);
  }

  .magazine-rank.wrap .cover-name {
    margin-bottom: 350px;
    font-size: 35px;
    font-weight: 600;
  }

  .magazine-rank.wrap .cover-img {
    max-width: 80%;
    max-height: 70%;
    object-fit: contain;
  }

  .magazine-rank.wrap .cover-date {
    font-size: 18px;
    font-weight: 600;
    width: 100%;
    margin-right: 250px;
    transform: rotate(-90deg);
  }

  .magazine-rank.wrap .cover-username {
    font-size: 18px;
    font-weight: 600;
    width: 100%;
    margin-left: 250px;
    transform: rotate(90deg);
  }

  .magazine-rank.wrap .cover-email {
    font-family: 'LibreBarcode128Text';
    margin-top : 350px;
    font-size: 24px;
  }

  .magazine-rank.wrap .cover-theme {
    position: absolute;
    bottom: 0;
    width: 280px;
    height: 50px;
    border-radius: 5px;
  }

</style>