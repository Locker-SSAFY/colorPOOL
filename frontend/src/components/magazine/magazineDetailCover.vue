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
export default {
  name: 'magazineDetail',
  props: {
    magazine: {
      default: void 0
    }
  },
  methods: {
    showDetail() {
      alert("매거진 보기는 구현 예정입니다")
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