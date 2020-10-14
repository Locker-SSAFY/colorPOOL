<template>
  <div v-if="magazineData != null">
    <div class="magazine-list wrap" :style="{'background-color': magazine.color}" @click="showMag">
      <span class="cover-name">{{magazine.magazineName}}</span>
      <span class="cover-date">{{magazine.createdDate}}</span>
      <span class="cover-username">{{magazine.userNickname}}</span>
      <span class="cover-email">{{magazine.email}}</span>
      <img class="cover-img" :src="magazine.contents[0].url">
      
    </div>
    <div class="cover-bar">
      <v-btn class="icon-heart" icon text @click="clickHeart">
        <v-icon size="30" v-if="magazine.likeClicked" :color="magazine.color">mdi-heart</v-icon>
        <v-icon size="30" v-else :color="magazine.color">mdi-heart-outline</v-icon>
      </v-btn>
      <span class="num-heart" :style="{'color': magazine.color}">{{this.magazine.likeCount}}</span>  
      <v-btn  class="icon-bookmark" icon text @click="clickBookmark">
        <v-icon size="30" v-if="magazine.bookmarkClicked" :color="magazine.color">mdi-bookmark</v-icon>
        <v-icon size="30" v-else :color="magazine.color">mdi-bookmark-outline</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
import axios from '../../api/axiosCommon'
import SERVER from '../../api/restApi'

export default {
  name: 'MagazineListCover',
  props: {
    magazineData: {
      default: void 0
    }
  },
  data() {
    return {
      magazine: {
        name: '',
        color: '',
        theme: [],
        userName: '',
        userEmail: '',
        coverImg: '',
        date: '',
        heart: false,
        bookmark: '',
        likes: [],
        heart_click: false,
        likeClicked: '',
        likeCount: 0,
      }
    }
  },
  created() {
    this.magazine = this.magazineData;
    console.log(this.magazine.likeClicked + ":" + this.magazine.likeCount);
    // this.magazine.heart = false;
    this.heart_click = false;
  },
  methods: {
    showMag() {
      console.log('before send', this.magazine)
      this.$emit('show-magazine', this.magazine)
    }, 
    clickHeart() {
      this.magazine.likeClicked = !this.magazine.likeClicked;
      if(this.magazine.likeClicked) {
        this.magazine.likeCount += 1;
      } else {
        this.magazine.likeCount -= 1;
      }
      const token = localStorage.getItem('access_token');
      var authOptions = {
        method: 'POST',
        url: SERVER.ROUTES.postMagazineLike + '?magazineId=' + this.magazine.magazineId,
        headers: {
          'accept': '*',
          'X-AUTH-TOKEN': token
        },
        json: true
      };
      axios(authOptions)
      .then((res) => console.log(res))
      .catch((err) => console.log(err))
    },
    clickBookmark () {
      this.magazine.bookmarkClicked = !this.magazine.bookmarkClicked;
      const token = localStorage.getItem('access_token');
      var authOptions = {
        method: 'POST',
        url: SERVER.ROUTES.postMagazineBookmark + '?magazineId=' + this.magazine.magazineId,
        headers: {
          'accept': '*',
          'X-AUTH-TOKEN': token
        },
        json: true
      };
      axios(authOptions)
      .then((res) => console.log(res))
      .catch((err) => console.log(err))
    }
  },
}
</script>

<style scoped>
  @font-face {
    font-family: 'LibreBarcode128Text';
    src: url('../../assets/font/LibreBarcode128Text-Regular.ttf');
  }

  .magazine-list.wrap {
    width: 200px;
    height: 280px;
    display: flex;
    align-items: center;
    justify-content: center;
    transition-duration: 300ms;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }

  .magazine-list.wrap:hover {
    transform: scale(1.1);
    box-shadow: 15px 15px 23px 0px rgba(0,0,0,0.5);
  }

  .magazine-list.wrap .cover-img {
    max-width: 80%;
    max-height: 70%;
    object-fit: contain;
  }

  .magazine-list.wrap span:nth-child(-n+4) {
    position: absolute;
    color: transparent;
    background: inherit;
    background-clip: text;
    filter: invert(1);
  }

  .magazine-list.wrap .cover-name {
    margin-bottom: 250px;
    font-size: 20px;
    font-weight: 600;
  }

  .magazine-list.wrap .cover-date {
    font-size: 12px;
    font-weight: 600;
    margin-right: 180px;
    width: 200px;
    transform: rotate(-90deg);
  }

  .magazine-list.wrap .cover-username {
    font-size: 12px;
    font-weight: 600;
    margin-left: 180px;
    transform: rotate(90deg);
  }

  .magazine-list.wrap .cover-email {
    font-family: 'LibreBarcode128Text';
    font-size: 25px;
    margin-top: 240px;
  }

  .cover-bar {
    margin-top: 10px;
    width: 200px;
    display:flex;
    align-items: center;
    justify-content: center;
  }

  .cover-bar .icon-heart {
    margin-right: 5px;
  }

  .cover-bar .num-heart {
    font-size: 22px;
    font-weight: 600;
  }

  .cover-bar .icon-bookmark {
    margin-left: 120px;
  }

  /* .icon-heart {
    position: absolute;
    margin-top: 340px;
    margin-right: 160px;
  } */

  /* .num-heart {
    position: absolute;
    margin-top: 340px;
    margin-right: 100px;
    font-size: 25px;
    font-weight: 600;
  } */

  /* .icon-bookmark {
    position: absolute;
    margin-top: 340px;
    margin-left: 170px;
  } */

</style>