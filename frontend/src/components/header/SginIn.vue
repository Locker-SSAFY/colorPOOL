<template>
<div>
  <v-dialog v-model="dialog" persistent max-width="1000px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        icon
        text
        dark
        v-bind="attrs"
        v-on="on"
      >
      SIGNIN</v-btn>
    </template>
    <v-card class="signin-modal wrap">
      <v-row>
        <v-col cols="11" id="modal-title">COLORPOOL</v-col>
        <v-col cols="1" id="modal-title">
          <v-btn icon text large @click="dialog=false">
            <v-icon>mdi-window-close</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-row>
            <v-btn icon text @click="showSignin">
              SIGNIN
            </v-btn>
          </v-row>
          <v-row>
            <v-btn icon text @click="showSignup">
              SIGNUP
            </v-btn></v-row>
        </v-col>
        <v-col cols="10">
          <v-row class="signin-back">
            <!-- signin -->
            <v-col v-if="showSigninVal">
              <v-row id="singin-title">SIGNIN</v-row>
              <v-row>
                <v-text-field label="id" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field label="password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-btn
                  block
                  color="black"
                  dark
                  class="mb-2"
                  @click="signin"
                >
                SIGININ
                </v-btn>
                <v-btn
                  block
                  color="red"
                  dark
                  class="mb-2"
                >
                GOOGLE
                </v-btn>
                <v-btn
                  block
                  color="yellow"
                  dark
                  class="mb-2"
                >
                KAKAOTALK
                </v-btn>
              </v-row>
            </v-col>
            <!-- sign up-->
            <v-col v-else>
              <v-row id="singin-title">SIGNUP</v-row>
              <v-row>
                <v-text-field label="nickname" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field label="email" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field label="password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-text-field label="check password" type="password" required></v-text-field>
              </v-row>
              <v-row>
                <v-btn
                  block
                  color="black"
                  dark
                  class="mb-2"
                  @click="signin"
                >
                SIGINUP
                </v-btn>
              </v-row>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-card>
  </v-dialog>
</div>
</template>
<script>
export default {
  data(){
    return{
      dialog: false,
      id: '',
      password: '',
      showSigninVal: true,
    }
  },
  methods: {
    signin(){
      this.dialog = false
    },
    showSignin(){
      this.showSigninVal = true;
    },
    showSignup(){
      this.showSigninVal = false;
      console.log(this.showSigninVal);
    }
  }
}
</script>
<style scoped>
  .v-dialog .signin-modal.wrap {
    background-color: #EF5350;
  }

  .v-dialog .signin-modal.wrap .signin-back{
    height: 400px;
    margin-bottom: 30px;
    background-color: white
  }

  #modal-title{
    margin-top: 1.5%;
    margin-bottom: 1%;
    height: 60px;
    font-size: 2.5rem;
    font-weight: bold;
  }

  #singin-title{
    font-size: 2rem;
  }

  #horizon-line{
    background-color: #EF5350;
    position: absolute;
    left: 60%;
    top: 20%;
    height: 65%;
    width: 1px;
  }

  .v-dialog .signin-modal.wrap .row{
    margin-left: 3%;
    margin-right: 3%;
  }

  .v-dialog .signin-modal.wrap .col{
    margin: 0%;
    padding: 0%;
  }
</style>