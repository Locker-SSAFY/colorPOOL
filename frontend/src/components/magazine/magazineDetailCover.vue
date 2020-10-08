<template>
  <div class="book-container" @click="showDetail">
    <div class="book">
      <div class="book-cover" :style="{'background-color': magazine.color}">
        <img class="image" :src="magazine.coverImg" />
        <span class="time">{{magazine.date}}</span>
        <span class="logo">{{magazine.name}}</span>
        <span class="user">{{magazine.userName}}</span>
        <span class="email">{{magazine.userEmail}}</span>
      </div>
      <div class="book-spine"
      :style="{'background': 'linear-gradient(to bottom, ' + magazine.theme[0] + ' 20%, ' + magazine.theme[1] + ' 20% 40%, ' + magazine.theme[2] + ' 40% 60%, ' + magazine.theme[3] + ' 60% 80%, ' + magazine.theme[4] + ' 80%)'}">
        <span class="spine-logo">ColorPOOL</span>
      </div>
    </div>
  </div>
</template>

<script>
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
      mag_data: {}
    }
  },
  methods: {
    ...mapActions(detailStore, ['AC_DETAIL']),
    showDetail() {
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
              "mainText": "당신은 패션을 알고있거나, 그렇지 않다",
              "subText": "",
              "question": "요즘 내가 몰입하고 있는 것은 무엇인가요?",
              "answer": "빌런을 만족시킬 배색을, 컬러의 조합을 찾는 일",
              "template": "0"
            },
            {
              "id": 2,
              "url": "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=662&q=80",
              "mainText": "패션은 드레스에만 존재하는 것이 아니다. 패션은 하늘에도 거리에도 있고, 우리가 사는 방식, 일어나는 일 모두다",
              "subText": "",
              "question": "요즘 내가 자꾸 고민하게 되는 것은 무엇인가요?",
              "answer": "컬러, 컬러, 컬러, 온 세상의 조형이 컬러칩으로 보여요",
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