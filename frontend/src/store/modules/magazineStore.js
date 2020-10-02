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
    MU_PRODUCT_NAMES: (state, payload) => {
      console.log('MU_PRODUCT_NAMES', payload);
      state.productNames = payload.productNames 
    }
  },
  actions: {
    AC_MAGAZINE_IMAGES: ({commit}, payload) => {
      commit('MU_MAGAZINE_IMAGES', payload)
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
    }
  }
}

export default magazineStore;