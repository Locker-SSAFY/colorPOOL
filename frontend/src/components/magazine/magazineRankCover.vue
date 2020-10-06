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
import {mapActions} from 'vuex'
const detailStore = 'detailStore'

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
      likes: [],
      mag_data: {}
    }
  },
  methods: {
    ...mapActions(detailStore, ['AC_DETAIL']),
    showMag() {
      var res = confirm("잡지를 보시겠습니까?")
      if(res) {
        // console.log(res)
        const payload = this.mag_data;
        this.AC_DETAIL(payload);
        let route = this.$router.resolve({path: '/detail'});
        window.open(route.href, 'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=1200,height=600,left=0, top=0, scrollbars=yes');
      } 
    }
  },
  created() {
    this.name = this.aboutRanker.name;
    this.color = this.aboutRanker.color;
    this.theme = this.aboutRanker.theme;
    this.userName = this.aboutRanker.userName;
    this.userEmail = this.aboutRanker.userEmail;
    this.coverImg = this.aboutRanker.coverImg;
    this.date = this.aboutRanker.date;
    this.likes = this.aboutRanker.likes;
    
    this.mag_data = 
      [
        {
          "id": 0,
          "user": {
            "id": 0,
            "email": "kang@kang.com",
            "nickname": "kang",
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
          "contents": [
            {
              "id": 1,
              "url": "https://images.unsplash.com/photo-1548549557-dbe9946621da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
              "mainText": "패션은 나이, 성별, 인종을 초월한다",
              "subText": "shoes",
              "question": "당신에게 코딩이란?",
              "answer": "인터뷰에 답변을 적어주세요",
              "template": "0"
            },
            {
              "id": 2,
              "url": "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=662&q=80",
              "mainText": "Fashion is about Passion",
              "subText": "shoes",
              "question": "당신에게 코딩이란?",
              "answer": "인터뷰에 답변을 적어주세요",
              "template": "2"
            }
          ],
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