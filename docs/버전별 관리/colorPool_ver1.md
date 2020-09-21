# 역할

- 회원가입/로그인, 소셜로그인
- 배색추천 화면 UI 구현
- 컬러 및 배색 데이터 관리 로직 구현

----

# 구현 내용

## [화면 구현]

### Signin / Signup

> Signin / Signup 화면을 **modal**로 구현

- 하나의 modal 안에 Signin과 Signup **탭**을 만들어서 전환

<img src="https://i.ibb.co/7pG602s/image.jpg" alt="image">

<img src="https://i.ibb.co/nnjp5BC/image.jpg" alt="image" border="0">

* 색을 선택한 이후에 Signin/Signup modal을 여는 경우에는 **배경 색이 선택한 색상으로 변경**됨

<img src="https://i.ibb.co/mhyqPzY/image.jpg" alt="image" border="0">



### 메인페이지

#### 랜딩 페이지

> 웹페이지의 첫 화면

* 색상을 고르는 **pick Color 컴포넌트**와 검색을 통한 이미지 검색에서 대표색을 가져오는 **get Color 컴포넌트**가 보이는 **랜딩 페이지**

* 각 컴포넌트 선택시 pick Color 페이지가 보이거나 get Color 페이지가 보이도록 구현

<img src="https://i.ibb.co/88k3y1q/landing.png">

#### 색상 선택 페이지

* 원하는 색상을 총 **200가지 색깔이 있는 팔레트**에서 선택할 수 있음

* 색상에 따라 **사진 뒷 배경색**이 변함

<img src="https://i.ibb.co/xJ8Qttv/pick.png">

#### 색상 추출 페이지

> 키워드를 검색하면, 해당 키워드 관련 색상 정보를 불러오는 화면

- [server.picular.co](http://server.picular.co)에서 **구글 기반 이미지 검색**, 이미지의 메인 색상을 추출해서 가져올 수 있음

- 색상들을 ul 4개로 보여줌 → *추후 responsive 하게 모바일에서는 ul 하나에 나오도록 변경할 예정*

- **색상에 마우스를 가져다 대면** 어떤 이미지인지 **preview 확인**할 수 있음

* **배경색**이 클릭한 색상으로 변경됨

<img src="https://i.ibb.co/NVkHyF0/get.png">



#### 배색 추천 화면

>  Pick Color 화면이나, Get Color 화면에서 선택한 색상이 넘어오면 해당 색에 대한 **추천 배색을 보여주는** 화면 

- 앞서 선택한 색상 정보를 불러와 배경색으로 사용

<img src="https://i.ibb.co/Z1CBxFw/your-color.jpg" alt="your-color" border="0" >

* 불러온 배색 정보들은 div안의 스크롤로 확인 가능함
* 배색 정보를 클릭하면, **클릭한 배색이 오른쪽에 표시** 됨

<img src="https://i.ibb.co/5LWfgx3/your-color-scheme.jpg" alt="your-color-scheme" border="0" >

* 검색하고자하는 배색 tone을 선택하는 화면 **modal**로 구현

<img src="https://i.ibb.co/f9qKrKb/select-tone.jpg" alt="select-tone" border="0">



### 카테고리 & 이미지 화면

#### 카테고리 페이지

* 로그인 후 **배색을 선택하면 카테고리 페이지**가 랜딩 페이지

* 리빙, 악세사리, 패션, 영화, 아직 미정인 카테고리들.. 을 선택할 수 있도록 버튼이 있음

* 왼쪽에 사용자가 선택한 배색 조합을 보여줌*(ThemeBar.vue)*

<img src="https://i.ibb.co/2NBzdf9/category.png">

#### 이미지 페이지

* 카테고리를 선택하면 이미지들을 carousel-3d로 보여주고 **더블 클릭해서 선택**할 수 있게 함

* **선택**한 이미지들을 **오른편에 띄워주고** **삭제**할 수 있도록 함

* **이미지 선택**이 10개가 넘어도 추가는 가능하지만 **10개까지만 가능**하다고 보여줌

<img src="https://i.ibb.co/9ph3VjZ/image.png">



### 반응형 화면 🖥️ → 📱

#### 모바일 랜딩 페이지

- 🍎갓뮹님의 조언에 따라 화면을 그리드 12칸으로 나누는 방식으로 반응형 초기 구현

- 모바일📱에서는 양쪽에 있던 컴포넌트를 위 아래 배치로 변경

<img src="https://i.ibb.co/wRWKKZJ/mobland.png" style="zoom:50%;" >

#### 모바일 색상 선택 페이지

* 컬러 피커를 왼쪽이 아닌 위에 배치

<img src="https://i.ibb.co/Nt2Rh80/mobpick.png" style="zoom:50%;" >



## [기능 구현]

#### 로그인

```javascript
axios.post("/singin", payload)

//axios post요청 시 보내는 값
const payload = {
        userInfo: {
          email: this.userEmail, 
          password: this.userPassword,
          provider: 'root'
        }
      }
```

#### 회원가입

```javascript
axios.post("signup", payload)

//axios post요청 시 보내는 값
const payload = {
        nickname: this.nickName,
        userInfo: {
          email: this.email, 
          password: this.password,
          provider: 'root'
        }
      }
```

#### 소셜로그인

- **카카오톡**
  - javascript SDK 사용

- **구글**
  - vue-google-oauth2 플러그인 사용



## [로직]

### colorStore.js

###### [전체코드 보기](https://lab.ssafy.com/s03-bigdata-sub2/s03p22a303/blob/6e13d59f422ac87ca0d7c5506b11bd4117b9d31e/frontend/src/store/modules/colorStore.js)

```javascript
const colorStore = {
  namespaced: true,
  state: {
    selectedColor: '',
    selectedTheme: null
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      state.selectedTheme = payload.selectedTheme
    }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      console.log('AC_SELECTED_COLOR', payload.selectedColor);
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      console.log('AC_SELECTED_THEME', payload.selectedTheme);
      commit('MU_SELECTED_THEME', payload)
    }
  }
}

export default colorStore
```

* 선택한 color나 theme 정보를 **actions, mutations**를 이용하여 **저장**
* 저장한 color, theme 정보는 **getters**를 이용하여 **불러올 수 있음**

### imageStore.js

###### [전체코드 보기](https://lab.ssafy.com/s03-bigdata-sub2/s03p22a303/blob/ed81d0b7357798fc6999a3a717f546e9846e6147/frontend/src/store/modules/imageStore.js)

```javascript
const imageStore = {
  namespaced: true,
  state: {
    accesoryImages: [],
    fashionImages: [],
    livingImages: [],
    movieImages: []
  },
  getters: {
    GE_ACCESORY_IMAGE : state => state.accesoryImages,
    GE_FASHION_IMAGE : state => state.fashionImages,
    GE_LIVING_IMAGE : state => state.livingImages,
    GE_MOVIE_IMAGE : state => state.movieImages
  },
  mutations: {
    MU_ACCESORY_IMAGE : (state, payload) => {
      state.accesoryImages = payload;
    },
    MU_FASHION_IMAGE : (state, payload) => {
      state.fashionImages = payload;
    },
    MU_LIVING_IMAGE : (state, payload) => {
      state.livingImages = payload;
    },
    MU_MOVIE_IMAGE : (state, payload) => {
      state.movieImages = payload;
    }
  },
  actions: {
    AC_ACCESORY_IMAGE : ({commit}, payload) => {...}
    AC_FASHION_IMAGE : ({commit}, payload) => {...},
    AC_MOVIE_IAMGE : ({commit}, payload) => {...}
  }
}

export default imageStore;

```

* **store에서** state, getters, mutations, actions를 사용해서 **데이터를 저장하고 가져올 수 있음**

* imageStore에서는 추후에 axios를 사용해서 **백엔드에 이미지 크롤링을 요청하고 가져와야 함

----

## [이슈]

* carousel-3d에서 이미지들을 동적으로 처리할 때 carousel에 이미지가 나타나지 않는 현상 (div 높이를 조절하면서 해결)
* 반응형으로 웹을 만들 때 화면 오른쪽과 아래쪽에 여백이 생기는 현상 (overflow-x를 hidden 하여 임시로 해결)
* 특정 div 밖을 선택했을 때의 이벤트를 주기 위해서 div로 layer를 하나 만들고, z-index를 사용하여 특정 div 아래에 넣어주고 onclick을 주어서 해결