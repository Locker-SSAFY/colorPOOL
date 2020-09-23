import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

import Landing from '../views/Landing.vue'
import CategoryImage from '../views/CategoryImage.vue'
import TestHome from '../views/TestHome.vue'
import Magazine from '../views/Magazine.vue'
import Result from '../views/Result.vue'
import TestVision from '../components/TestVision.vue'

Vue.use(VueRouter)

  const routes = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
  {
    path: '/',
    name: 'Landing',
    component: Landing
  },
  {
    path: '/category',
    name : 'CategoryImage',
    component: CategoryImage
  },
  {
    path: '/testHome',
    name : 'TestHome',
    component: TestHome
  },
  {
    path: '/magazine',
    name: 'Magazine',
    component: Magazine
  },
  {
    path: '/result',
    name: 'Result',
    component: Result
  },
  {
    path: '/testVision',
    name: 'testVision',
    component: TestVision
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
