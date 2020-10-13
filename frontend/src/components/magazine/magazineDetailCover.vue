<template>
  <div class="book-container" @click="showDetail">
    <div class="book">
      <div class="book-cover" :style="{'background-color': magazine.color}">
        <img class="image" :src="magazine.contents[0].url" />
        <span class="time">{{magazine.createdDate}}</span>
        <span class="logo">{{magazine.magazineName}}</span>
        <span class="user">{{magazine.userNickname}}</span>
        <span class="email">{{magazine.email}}</span>
      </div>
      <div class="book-spine"
      :style="{'background': 'linear-gradient(to bottom, ' + this.theme[0] + ' 20%, ' + this.theme[1] + ' 20% 40%, ' + this.theme[2] + ' 40% 60%, ' + this.theme[3] + ' 60% 80%, ' + this.theme[4] + ' 80%)'}">
        <span class="spine-logo">ColorPOOL</span>
      </div>
    </div>
  </div>
</template>

<script>
// import materialColors from '../../assets/color/colorList.js'
import axios from 'axios'
import {mapActions} from 'vuex'
const detailStore = 'detailStore'

export default {
  name: 'magazineDetail',
  props: {
    magazine: {
      default: void 0
    }
  },
  data() {
    return {
      mag_data: {},
      theme: [],
      themeData: {}
    }
  },
  methods: {
    ...mapActions(detailStore, ['AC_DETAIL']),
    showDetail() {
      var res = confirm("잡지를 보시겠습니까?")
      if(res) {
        // console.log(res)
        const payload = this.mag_data;
        console.log('잡지', payload);
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
        console.log('detail cover themeData', this.themeData)
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
  watch: {
    themeData(val) {
      this.mag_data[0].selectedColor.themes = val;
    },
    magazine() {
      this.getThemeColorData(this.magazine.themeId);
    this.mag_data = 
      [
        {
          "id": 0,
          "user": {
            "id": 0,
            "email": this.magazine.email,
            "nickname": this.magazine.userNickname,
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
          "contents": this.magazine.contents,
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
  },
  created() {
    console.log('detailcover', this.magazine);
    this.getThemeColorData(this.magazine.themeId);
    this.mag_data = 
      [
        {
          "id": 0,
          "user": {
            "id": 0,
            "email": this.magazine.email,
            "nickname": this.magazine.userNickname,
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
          "contents": this.magazine.contents,
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
  .book-container {
    width: 300px;
    height: 450px;
    perspective: 1200px;
    display: flex;
    align-items: center;
  }
  .book {
    width: 300px;
    height: 450px;
    z-index: 5;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
    transition: 0.75s;
    transform-style: preserve-3d;
    transform-origin: 125px 0;
    background-color: white;
    display: flex;
    align-items: center;
  }

  .book:after {
    content: "";
    position: absolute;
    top: 0;
    bottom: 0;
    left: 3px;
    width: 7px;
    z-index: 20;
    transform: translateZ(1px);
  }

  .book:hover {
    transform: translateX(-10px) translateZ(35px) translateX(35px) rotateY(60deg);
  }

  .book-cover { 
    width: 100%;
    height: 100%;
    position: relative; 
    z-index: 10; 
    display: flex;
    align-items: center;
    justify-content: center;
}

.book-cover span {
  position: absolute;
  color: transparent;
  background: inherit;
  background-clip: text;
  filter: invert(1);
}

.book-cover .time {
  width : 100%;
  font-size: 18px;
  font-weight: 600;
  margin-right: 250px;
  transform: rotate(-90deg);
}

.book-cover .logo {
  font-size: 30px;
  font-weight: 700;
  top: 0;
  left: 50px;
}

.book-cover .user {
  right: -20px;
  font-size: 20px;
  transform: rotate(90deg);
}

.book-cover .email {
  font-family: 'LibreBarcode128Text';
  font-size: 30px;
  bottom: 10px;
  left: 10px;
}

.book-cover .image {
  max-width: 80%;
  max-height: 70%;
  object-fit: contain;
}

.book-spine { 
  color: #fff; 
  position: absolute; 
  bottom: 0; 
  top: 0; 
  width: 50px;
  height: 100%; 
  z-index: 5; 
  overflow: hidden; 
  box-shadow: 2px 2px 6px rgba(0, 0, 0, 0.7);
  background-size: auto 100%;
  transform: rotateY(-90deg) translateX(-49px);
  transform-origin: 0 0;
  display: flex;
}
.book-spine span { 
  display: block; 
  text-align: left; 
  color: transparent;
  background: inherit;
  background-clip: text;
  filter: invert(1);
  position: absolute; 
  top: 0; 
  left: 39px; 
  text-indent: 43px; 
  text-transform: uppercase; 
  font-family: Helvetica, Arial, sans-serif; 
  -webkit-font-smoothing: antialiased;
  transform: rotateZ(90deg);
  transform-origin: 0 0;
}
.book-spine:before { 
  display: block; 
  content: ""; 
  width: 100%; 
  height: 100%; 
  background: rgba(0, 0, 0, 0.3); 
}

.book-spine .spine-logo {
  font-size: 30px;
  font-weight: 600;
}

</style>
