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
    AC_ACCESORY_IMAGE : ({commit}, payload) => {
      const acImgList = [
        {
          color: '#e63946',
          url : [
            'https://images.unsplash.com/photo-1558368204-cc52a4a4e519?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1052&q=80',
            'https://images.unsplash.com/photo-1590055094907-8657c11164f8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=632&q=80',
            'https://images.unsplash.com/photo-1546087760-2753fd6fc7b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1489&q=80',
            'https://images.unsplash.com/photo-1592500410507-3b10de929610?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80'
          ]
        }
      ];
      payload = acImgList;
      commit('MU_ACCESORY_IMAGE', payload)
    },
    AC_FASHION_IMAGE : ({commit}, payload) => {
      const fsImgList = [
        {
          color: '#e63946',
          url: [
            'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=662&q=80',
            'https://images.unsplash.com/photo-1509631179647-0177331693ae?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1539109136881-3be0616acf4b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1548549557-dbe9946621da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
          ]
        }
      ];
      payload = fsImgList;
      commit('MU_FASHION_IMAGE', payload)
    },
    AC_LIVING_IMAGE : ({commit}, payload) => {
      const lvImgList = [
        {
          color: '#e63946',
          url: [
            'https://images.unsplash.com/photo-1519710164239-da123dc03ef4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1589459072535-550f4fae08d2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1541123603104-512919d6a96c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
            'https://images.unsplash.com/photo-1506377295352-e3154d43ea9e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
          ]
        }
      ];
      payload = lvImgList;
      commit('MU_LIVING_IMAGE', payload)
    },
    AC_MOVIE_IAMGE : ({commit}, payload) => {
      const mvImgList = [
        {
          color: '#e63946',
          url: [
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/eJ0kCMcqKLBUaHhB9PfOMFu2uim.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/z7ilT5rNN9kDo8JZmgyhM6ej2xv.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/s2xcqSFfT6F7ZXHxowjxfG0yisT.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/p0L1f5YRiUmbExUTn5VbFQtxfpN.jpg',
          ]
        }
      ];
      payload = mvImgList;
      commit('MU_MOVIE_IMAGE', payload)
    }
  }
}

export default imageStore;