import axios from 'axios'
import SERVER from '../../api/restApi'

const magazineStore = {
  namespaced: true,
  state: {
    magazineImages: [], 
    productNames: [],
    selectedMagazine: {},
  },
  getters: {
    GE_MAGAZINE_IMAGES: state => state.magazineImages,
    GE_PRODUCT_NAMES: state => state.productNames,
    GE_SELECTED_MAGAZINE: state => state.selectedMagazine
  },
  mutations: {
    MU_MAGAZINE_IMAGES: (state, payload) => {
      state.magazineImages = payload.magazineImages
    },
    MU_MAGAZINE_IMAGES_ONE: (state, payload) => {
      state.magazineImages[payload.index] = payload.image;
    },
    MU_PRODUCT_NAMES: (state, payload) => {
      console.log('MU_PRODUCT_NAMES', payload);
      state.productNames = payload.productNames 
    },
    MU_SELECTED_MAGAZINE: (state, payload) => {
      state.selectedMagazine = payload;
    }
  },
  actions: {
    AC_MAGAZINE_IMAGES: ({commit}, payload) => {
      commit('MU_MAGAZINE_IMAGES', payload)
    },
    AC_MAGAZINE_IMAGES_ONE: ({commit}, payload) => {
      commit('MU_MAGAZINE_IMAGES_ONE', payload)
    },
    AC_PRODUCT_DETECT: ({commit}, payload) => {
      console.log('AC_PRODUCT_DETECT', payload);
      // axios.request('/v2/vision/product/detect',
      // axios.request('https://dapi.kakao.com/v2/vision/product/detect',
      axios.request('https://cors-anywhere.herokuapp.com/https://dapi.kakao.com/v2/vision/product/detect',
                      { params: {'image_url': payload.url },
                        headers: {'Authorization': 'KakaoAK f820c9d3518ab3543b0918e9d6676dff'}})
      .then((res) => {
        console.log(res.data.result.objects)
        
        const names = [];
        for(var i=0; i < res.data.result.objects.length; i++){
          names.push(res.data.result.objects[i].class);
        }
        console.log(names)
        commit('MU_PRODUCT_NAMES', {productNames: names})
      })
      .catch((err) => {
        console.log(err.response)
      })
    },
    AC_MAGAZINE_ONE: ({commit}, payload) => {
      const token = localStorage.getItem('access_token')
      const header = {
        'accept' : '*',
        'X-AUTH-TOKEN': token,
      }

      axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/magazine/' + payload.magazineId, {headers: header})
      .then((res) => {
        console.log(res)
        console.log(commit)
        // commit('MU_SELECTED_MAGAZINE', res.data)
      })
      .catch((err) => {
        console.err(err);
      })
      
      ///////////////////////////////////////////////
      // 정상적인 axios 통신
      // axios.get(SERVER.ROUTES.getMagazineOne + payload.magazineId, {headers: header})
      // .then((res) => {
      //   console.log(res);
      // })
      // .catch((err) => {
      //   console.log(err)
      // })
      ///////////////////////////////////////////////
    },
    AC_MAGAZINE_POST: ({commit, state}, payload) => {
      console.log('AC_MAGAZINE_POST', payload);
      const token = localStorage.getItem('access_token');
      const url = 'https://j3a303.p.ssafy.io/api/magazine';
      const header = {
        'accept': '*',
        'X-AUTH-TOKEN': token,
        // 'Access-Control-Allow-Origin': '*',
        // 'X-Requested-With' : 'XMLHttpRequest'
      };
      var contents = [];
      state.magazineImages.forEach((image) => {
        contents.push({
          // 'id': null,
          'url': image.url,
          'mainText': image.mainText,
          'subText': image.subText,
          'question': image.question,
          'answer': image.answer,
          'template': image.template + ""
        })
      })
      const data = {
        // 'id': userInfo.id + "",
        'email': payload.userInfo.email,
        'contents': contents,
        'createdDate': null,
        'magazineName': payload.magazineName,
        'userNickname': payload.userInfo.nickname,
        'themeId': parseFloat(payload.themeId),
        'selectedColorId': parseFloat(payload.selectedColor),
        // 'contents' : state.magazineImages
        
      }
      console.log(commit);
      console.log(url);
      console.log(header);
      console.log('data', data);

      // 비정상적인 통신
      // axios.post(url, data, {headers: header})
      // .then((res) => {
      //   console.log(res);
      //   if(res.data.success) {
      //     return true;
      //   } else {
      //     alert("잡지가 추가되지 않았습니다")
      //     return false;
      //   }
      // })
      // .catch((err) => {
      //   console.log(err)
      //   return false;
      // })
    
      ////////// 정상적인 axios 통신 /////////////
      axios.post(SERVER.ROUTES.postMagazine, data, {headers: header})
      .then((res) => {
        console.log(res);
        if(res.data.success) {
          alert("잡지가 라이브러리에 추가되었습니다")
          return true;
        } else {
          alert("잡지가 추가되지 않았습니다")
          return false;
        }
      })
      .catch((err) => {
        console.log(err)
        return false;
      })
      ////////////////////////////////////////////
    },
    AC_MAGAZINE_LIKE: (payload) => {
      const magazineId = payload.magazineId;
      const token = localStorage.getItem('access_token');
      const header = {
        'accept': '*',
        'X-AUTH-TOKEN': token,
      }

      //////////////// 비정상적인 axios 통신 ///////////////
      axios.get('https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/magazine/like' + magazineId, {headers: header})
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.err(err);
      })
      //////////////////////////////////////////////////////

      /////////////////// 정상적인 axios 통신 /////////////////////
      // axios.post(SERVER.ROUTES.postMagazineLike + magazineId, {headers: header})
      // .then((res) => {
      //   console.log(res);
      // })
      // .catch((err) => {
      //   console.log(err);
      // })
      /////////////////////////////////////////////////////////////
    }
  }
  
}

export default magazineStore;