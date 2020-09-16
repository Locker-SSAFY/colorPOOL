<template>
  <div class="header wrap">
    <table>
      <tr>
        <td class="header-logo" @click="goHome()">
          ColorPOOL
        </td>
        <td class="header-library">
          <v-btn icon text>
            LIBRARY
          </v-btn>
        </td>
        <td class="header-signin">
            <Signin></Signin>
        </td>
        <td class="header-signup">
          <v-btn icon text>
            SIGNUP
          </v-btn>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import Signin from './SginIn'

export default {
  components: {
    Signin
  },
  data() {
    return {
      scrolled: false,
      dialog: false,
    }
  },
  methods: {
    detectWindowScrollY() {
      // 감지 이벤트 메서드
      this.scrolled = window.scrollY > 0
    },
    goHome() {
      console.log(this);
      this.$router.push({ name: 'Landing' })
    }
  },
  mounted() {
    window.addEventListener('scroll', this.detectWindowScrollY)
  },
  beforeDestory() {
    window.removeEventListener('scroll', this.detectWindowScrollY)
  }
}
</script>

<style>
  .header.wrap {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 80px;
    transition-duration: 300ms;
    z-index: 90;
    background-color: rgb(255, 255, 255, 0.8);
  }

  .header.wrap table {
    width: 90%;
    margin-left: 5%;
    line-height: 3;
  }

  .header.wrap .header-logo {
    /* color: white; */
    font-size: 25px;
    width: 60%;
    font-weight: bolder;
  }

  .header.wrap .header-library {
    /* color : white; */
    text-align: right;
  }

  .header.wrap .header-library .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signin {
    text-align: center;
  }
  
  .header.wrap .header-signin .v-btn{
    font-size: 1.2rem;
  }

  .header.wrap .header-signup {
    text-align: left;
  }

  .header.wrap .header-signup .v-btn{
    font-size: 1.2rem;
  }
</style>