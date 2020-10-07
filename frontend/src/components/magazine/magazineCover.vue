<template>
  <div class="book-container">
    <div class="book">
      <div class="book-cover" :style="{'background-color': color}">
        <img class="image" :src="coverImage" />
        <span class="time">{{this.issueTime}}</span>
        <span contenteditable @blur="changeName" class="logo">{{magazineName}}</span>
        <span class="user">BeaverBae</span>
        <span class="email">beaverBae@naver.com</span>
      </div>
      <div class="book-spine"
      :style="{'background': 'linear-gradient(to bottom, ' + this.theme[0] + ' 20%, ' + this.theme[1] + ' 20% 40%, ' + this.theme[2] + ' 40% 60%, ' + this.theme[3] + ' 60% 80%, ' + this.theme[4] + ' 80%)'}">
        <span class="spine-logo">ColorPOOL</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
const colorStore = 'colorStore'
const magazineStore = 'magazineStore'

export default {
  name: 'Cover',
  props: {
    nowDate: {
      default: void 0
    },
    magazineName: {
      default: void 0
    }
  },
  data() {
    return {
      month : '',
      date: '',
      year: '',
      day: '',
      hour : '',
      hourDivide: 'AM',
      issueTime: '',
      color: '',
      theme: ["#e63946", "#f1faee", "#a8dadc", "#457b9d", "#1d3557"],
      coverImage: '',
    }
  },
  created() {
    // 오늘의 날짜 확인
    this.getDateOfToday();
    // 색깔과 테마 받아오기
    this.getColorAndTheme();
    // 잡지 첫 이미지 가져오기
    this.getFirstImage();
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR', storeSelectedTheme: 'GE_SELECTED_THEME'}),
    ...mapGetters(magazineStore, {storeMagazineImages: 'GE_MAGAZINE_IMAGES'})
  },
  methods: {
    getDateOfToday() {
      // 오늘의 날짜 확인
      const time = new Date(this.nowDate);
      this.month = time.getMonth()
      this.date = time.getDate()
      this.year = time.getFullYear()
      this.day = time.getDay()
      this.hour = time.getHours()
      if(this.hour >= 12) {
        this.hourDivde = 'PM';
        this.hour -= 12;
      }
      this.hour = this.hour.toString().padStart(2, '0');
      this.month = this.month.toString().padStart(2, '0');
      this.date = this.date.toString().padStart(2, '0');

      if (this.day == 1) {
        this.day = 'MON'
      } else if (this.day == 2) {
        this.day = 'TUE'
      } else if (this.day == 3) {
        this.day = 'WED'
      } else if (this.day == 4) {
        this.day = 'THU'
      } else if (this.day == 5) {
        this.day = 'FRI'
      } else if (this.day == 6) {
        this.day = 'SAT'
      } else if (this.day == 0) {
        this.day = 'SUN'
      }

      this.issueTime = this.year + '.' + this.month + '.' + this.date + ' ' + this.day;
      console.log(this.issueTime)
    },
    getColorAndTheme() {
      // 색깔과 테마 받아오기
      this.color = this.storeSelectedColor.hex;
      this.theme = this.storeSelectedTheme;
      console.log(this.storeSelectedColor)
      if(this.storeSelectedColor == '') 
        this.color = "#7986CB";
      if(this.storeSelectedTheme == null)
        this.theme = ["#e63946", "#f1faee", "#a8dadc", "#457b9d", "#1d3557"];
    },
    getFirstImage() {
      console.log(this.storeMagazineImages)
      if(this.storeMagazineImages.length == 0) 
        this.coverImage = "https://images.unsplash.com/photo-1551516595-09cb9fcf8db5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80";
      else
        this.coverImage = this.storeMagazineImages[this.storeMagazineImages.length - 1].url;
    },
    changeName(event) {
      if(event.target.innerText.length == 0) {
        event.target.innerText = this.magazineName;
      }
      else {
        this.$parent.magazineName = event.target.innerText;
      }
    }
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
    transform: translateX(-10px) translateZ(35px) translateX(35px) rotateY(30deg);
  }

  .book:before {
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
    transform: translateX(0px) translateZ(0px) translateX(0px) rotateY(0deg);
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