## Vuex 모듈화🗃

### Vuex란?

- Vue.js 애플리케이션에 대한 상태 관리 패턴 + 라이브러리
- 애플리케이션의 모든 컴포넌트에 대한 중앙 집중식 저장소 역할
- 복잡한 애플리케이션에서 컴포넌트의 개수가 많아지면, 컴포넌트 간 데이터 전달이 어려워짐

#### Vuex 모듈화의 필요성

> 저번 프로젝트에서 회고하면서 이야기한 Vuex 모듈화의 필요성

* 프로젝트의 규모가 커짐에 따라 하나의 객체가 너무 커질 수 있음
  * 하나의 파일 내에 너무 많은 기능이 담겨있어 유지보수하기 어려워짐
* 기능별로 나누어 협업을 할 때, 같은 파일에서 작업하게 되면 충돌을 일으킬 수 있음

---

### Vuex 사용 규칙

> SOCKS 프론트엔드팀에서 사용하는 Vuex 사용 규칙📋

- state의 변경과 관련된 로직은 컴포넌트 내에 있으면 안됨
- state의 값을 변경할 때는 actions, mutations를 commit하거나 dispatch하여 사용할 것
- state의 값을 가져올 때에는 getters를 사용할 것
- actions 메소드 명은 `AC_`로 시작, mutations의 메소드 명은 `MU_`로 시작, getters 메소드 명은 `GE_`로 시작하게 함
- getters로 데이터 가져올 때에는 변수명 앞에 `store`를 붙인다.
- 컴포넌트에서 state의 getter, actions, mutations를 가져올 때는 mapGetters, mapActions, mapMutations를 사용할 것



#### 적용 예시

##### main.js

```javascript
import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import store from './store'
import store from '@/store/index.js'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
```

##### store/index.js

```javascript
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore.js'

export default new Vuex.Store({
  modules: {
    colorStore,
  }
})

```

##### store/modues/colorStore.js

````javascript
const colorStore = {
  namespaced: true,
  state: {
    selectedColor: ''
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      console.log('AC_SELECTED_COLOR', payload.selectedColor);
      commit('MU_SELECTED_COLOR', payload)
    }
  }
}
export default colorStore
````

##### Vue파일(component 내에서 사용하기)

```javascript
// <template> 부분 생략

<script> 
import { mapGetters, mapActions } from 'vuex'
const colorStore = 'colorStore' // namespaced: true

export default {
  name: 'RecommandTheme',
  computed: {
      //getters로 값 가져오기
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  created(){
      //가져온 값을 현재 컴포넌트의 data에 저장
    this.selectedColor = this.storeSelectedColor;
  },
  data () {
    return {
      selectedColor: '',
    }
  },
  watch: {
      //값 변경되는 것을 감지
    storeSelectedColor(val){
      this.selectedColor = val
    }
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    selectColor(color){
      const payload = { selectedColor: color};
      this.AC_SELECTED_COLOR(payload);
    },
  }
}
</script>
```



##### [참고]

[Vue 공식문서](https://vuex.vuejs.org/kr/)

[VUEX store 여러 개를 모듈화하기](https://velog.io/@skyepodium/VUEX-store-%EC%97%AC%EB%9F%AC-%EA%B0%9C%EB%A5%BC-%EB%AA%A8%EB%93%88%ED%99%94%ED%95%98%EA%B8%B0)

[5 Rules to Follow When Using the Vuex Store in Components](https://medium.com/swlh/5-rules-to-follow-when-using-the-vuex-store-in-components-9f4d03107894)