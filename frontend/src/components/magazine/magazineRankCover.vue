<template>
  <div class="magazine-rank wrap" :style="{'background-color': color}">
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
      likes: []
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