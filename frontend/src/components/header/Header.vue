<template>
  <div class="header wrap">
    <table>
      <tr>
        <td class="header-logo" @click="goHome()">
          ColorPOOL
        </td>
        <td class="header-library">
          <v-btn icon text dark>
            LIBRARY
          </v-btn>
        </td>
        <td class="header-signin">
          <!-- <v-dialog v-model="dialog" persistent max-width="1000px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                icon
                text
                dark
                v-bind="attrs"
                v-on="on"
              >
              SIGNIN</v-btn>
            </template> -->
            <Signin></Signin>
            <!-- <v-card class="signin-modal wrap">
              <v-row>
                <v-col id="modal-title" cols="3">COLORPOOL</v-col>
                <v-col cols="9"></v-col>
              </v-row>
              <v-row>
                <v-col cols="2">
                  <v-row>SGININ</v-row>
                  <v-row>SGINUP</v-row>
                </v-col>
                <v-col cols="10">
                  <v-row style="height: 400px; background-color: #561E1D">
                    <div id="horizon-line"></div>
                    <v-col>
                      
                    </v-col>
                    <v-col>

                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn dark text @click="dialog = false">Close</v-btn>
                <v-btn dark text @click="dialog = false">Save</v-btn>
              </v-card-actions>
            </v-card> -->
          <!-- </v-dialog> -->
        </td>
        <td calss="header-signup">
          <v-btn icon text dark>
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
    background-color: rgba(0,0,0,0.8);
    transition-duration: 300ms;
    z-index: 90;
  }

  .header.wrap table {
    width: 90%;
    margin-left: 5%;
    line-height: 3;
  }

  .header.wrap .header-logo {
    color: white;
    font-size: 25px;
    width: 60%;
  }

  .header.wrap .header-library {
    /* color : white; */
    text-align: right;
  }

  .header.wrap .header-signin {
    /* color: white; */
    text-align: center;
  }

  .header.wrap .header-signup {
    /* color: white; */
    text-align: left;
  }
</style>