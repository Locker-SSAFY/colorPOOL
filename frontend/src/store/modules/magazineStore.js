import axios from 'axios'

const magazineStore = {
  namespaced: true,
  state: {
    magazineImages: [], 
    productNames: [],
  },
  getters: {
    GE_MAGAZINE_IMAGES: state => state.magazineImages,
    GE_PRODUCT_NAMES: state => state.productNames
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
    AC_MAGAZINE_POST: ({commit, state}, payload) => {
      console.log('AC_MAGAZINE_POST', payload);
      const token = localStorage.getItem('access_token');
      const url = 'https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api/magazine';
      const header = {
        'accept': '*',
        'X-AUTH-TOKEN': token,
        // 'Access-Control-Allow-Origin': '*',
        // 'X-Requested-With' : 'XMLHttpRequest'
      };
      const userInfo = {
        'id': 32,
        'email': 'kang@kang.com',
        'userNickname': 'kang', 
      }
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
      console.log(contents);
      const data = {
        // 'id': userInfo.id + "",
        'id': null,
        'email': userInfo.email,
        'userNickname': userInfo.userNickname,
        // 'createdDate': payload.date,
        'createdDate': "2020-10-06T08:55:02.396Z",
        'magazineName': payload.magazineName,
        'colorId': payload.selectedColor,
        // 'contents' : state.magazineImages
        'content': contents
      }
      console.log(commit);
      console.log(url);
      console.log(header);
      console.log('data', data);
      axios.post(url, data, {headers: header})
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}

export default magazineStore;