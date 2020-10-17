<template>
  <div v-if="magazineData != null">
    <div class="magazine-list wrap" :style="{'background-color': magazine.color}" @click="showMag">
      <span class="cover-name">{{magazine.name}}</span>
      <span class="cover-date">{{magazine.date}}</span>
      <span class="cover-username">{{magazine.userName}}</span>
      <span class="cover-email">{{magazine.userEmail}}</span>
      <img class="cover-img" :src="magazine.coverImg">
      
    </div>
    <div class="cover-bar">
      <v-btn class="icon-heart" icon text @click="heartChange(magazine.heart)">
        <v-icon size="30" v-if="magazine.heart" :color="magazine.color">mdi-heart</v-icon>
        <v-icon size="30" v-else :color="magazine.color">mdi-heart-outline</v-icon>
      </v-btn>
      <span class="num-heart" :style="{'color': magazine.color}">{{this.heart_count}}</span>  
      <v-btn  class="icon-bookmark" icon text @click="magazine.bookmark = !magazine.bookmark">
        <v-icon size="30" v-if="magazine.bookmark" :color="magazine.color">mdi-bookmark</v-icon>
        <v-icon size="30" v-else :color="magazine.color">mdi-bookmark-outline</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
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
        heart: '',
        bookmark: '',
        heart_count: 0,
      }
    }
  },
  created() {
    this.magazine = this.magazineData;
    this.heart_count = this.magazineData.likes.length;
  },
  methods: {
    showMag() {
      this.$emit('show-magazine', this.magazine)
    },
    heartChange(heart) {
      if(!heart) {
        this.magazine.heart = true;
        this.heart_count ++;
        }
      else {
        this.magazine.heart = false;
        this.heart_count --;
      }
    }
  },
  watch: {
    heart(val) {
      if(val) {
        this.heart_count += 1;
      } else {
        this.heart_count -= 1;
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
