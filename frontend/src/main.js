import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import store from './store'
import store from '@/store/index.js'
import vuetify from './plugins/vuetify';
import GAuth from "vue-google-oauth2";

const gquthOption = {
  clientId:
    "858388202728-f26u33io27sn79ic2ac789sc03ht9vuf.apps.googleusercontent.com",
  // clientId: '768834812579-007e5802er7gj3c93p8qa9568h8bj3na.apps.googleusercontent.com',
  scope: "profile email",
  prompt: "select_account",
  fetch_basic_profile: false,
};

Vue.use(GAuth, gquthOption);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')