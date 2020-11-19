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
            'https://i.pinimg.com/564x/61/8b/9e/618b9ee33091831ca102e6e623d4f9e7.jpg',
            'https://i.pinimg.com/564x/c2/2e/1f/c22e1f59ab5a5b31c64d9d273f299af0.jpg',
            'https://i.pinimg.com/564x/5e/63/7c/5e637c302a1fe2bac3d545d1bdd56c71.jpg',
            'https://i.pinimg.com/564x/0c/5d/d1/0c5dd143cccf299d7f6d15c71fffacb8.jpg'
          ]
        },
        {
          color: '#f1faee',
          url : [
            'https://i.pinimg.com/564x/81/df/2a/81df2a1f52033db4d0bffd501fa9e24f.jpg',
            'https://i.pinimg.com/564x/73/35/13/733513cf9a1de7eafaa36455c323d711.jpg',
            'https://i.pinimg.com/564x/73/ea/0b/73ea0be7f2faf9a73ca76c45b5977571.jpg',
            'https://i.pinimg.com/564x/50/cb/55/50cb559c3edb78f2c3898db5ca90b9ee.jpg',
          ]
        },
        {
          color: '#a8dadc',
          url : [
            'https://i.pinimg.com/564x/ff/9b/45/ff9b4553cab2536d22b8ceda40890e77.jpg',
            'https://i.pinimg.com/564x/f5/2e/7b/f52e7baed22820f3e6cf60e4978e4a13.jpg',
            'https://i.pinimg.com/564x/9c/1a/c1/9c1ac109d924ab0ded35abb45d3ef753.jpg',
            'https://i.pinimg.com/564x/70/48/17/704817773f56ba13c86df2898a16c060.jpg',
          ]
        },
        {
          color: '#457b9d',
          url : [
            'https://i.pinimg.com/564x/18/bf/0c/18bf0c1faeb1ede29e7fdcdf974db6d4.jpg',
            'https://i.pinimg.com/564x/d9/d2/7b/d9d27b53b48231f0123e862a626aa12a.jpg',
            'https://images.unsplash.com/photo-1594223515488-7dbc35d61eca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1388&q=80',
            'https://images.unsplash.com/flagged/photo-1553802922-5609062365b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2702&q=80'
          ]
        },
        {
          color: '#1d3557',
          url : [
            'https://images.unsplash.com/photo-1564139615082-01535600057f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1546180245-c59500ad14d0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80',
            'https://images.unsplash.com/photo-1586051385004-fde87ebea3d0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
            'https://images.unsplash.com/photo-1583858724332-4d5ead8afb00?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
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
            'https://i.pinimg.com/564x/c0/a4/8b/c0a48bbf02f72f809c6100b821601046.jpg',
            'https://i.pinimg.com/564x/d6/f6/23/d6f6232c59d24d98b6d3dfe98e860c4e.jpg',
            'https://i.pinimg.com/564x/52/82/d3/5282d3656ec95acb39bea0e25b33550f.jpg',
            'https://i.pinimg.com/564x/d0/b3/2e/d0b32ea51af5e82577adb4df3ad62c46.jpg',
          ]
        },
        {
          color : '#f1faee',
          url : [
            'https://i.pinimg.com/564x/d3/68/db/d368dbf71ab098a30ad95e0ba76eb5a3.jpg',
            'https://i.pinimg.com/564x/fc/a4/cd/fca4cdd9aa765d0ca38d8320442f2135.jpg',
            'https://i.pinimg.com/564x/bf/d2/9c/bfd29c49d103dad48609d8856df099a9.jpg',
            'https://i.pinimg.com/564x/26/34/2f/26342fd7483e0e14680d4b13b8048cda.jpg',
          ]
        },
        {
          color: '#a8dadc',
          url : [
            'https://i.pinimg.com/564x/c6/eb/35/c6eb3574385690ec703392c34beb2b9c.jpg',
            'https://i.pinimg.com/564x/d6/f6/23/d6f6232c59d24d98b6d3dfe98e860c4e.jpg',
            'https://i.pinimg.com/564x/c0/a4/8b/c0a48bbf02f72f809c6100b821601046.jpg',
            'https://i.pinimg.com/564x/52/82/d3/5282d3656ec95acb39bea0e25b33550f.jpg'
          ]
        },
        {
          color: '#457b9d',
          url : [
            'https://images.unsplash.com/photo-1504703395950-b89145a5425b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=651&q=80',
            'https://images.unsplash.com/photo-1456885284447-7dd4bb8720bf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1496217590455-aa63a8350eea?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1483181957632-8bda974cbc91?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80'
          ]
        },
        {
          color : '#1d3557',
          url: [
            'https://images.unsplash.com/photo-1475180098004-ca77a66827be?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=633&q=80',
            'https://images.unsplash.com/photo-1551803091-e20673f15770?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80',
            'https://images.unsplash.com/photo-1538331269258-6c97a6bdeae0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
            'https://images.unsplash.com/photo-1551310357-b26c1af069c6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80'
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
        },
        {
          color: '#f1faee',
          url : [
            'https://images.unsplash.com/photo-1534889156217-d643df14f14a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1400&q=80',
            'https://images.unsplash.com/photo-1513694203232-719a280e022f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2698&q=80',
            'https://images.unsplash.com/photo-1464288550599-43d5a73451b8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2744&q=80',
            'https://images.unsplash.com/photo-1501876725168-00c445821c9e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80',
          ]
        },
        {
          color: '#a8dadc',
          url : [
            'https://images.unsplash.com/photo-1521782462922-9318be1cfd04?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2710&q=80',
            'https://images.unsplash.com/photo-1522444195799-478538b28823?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1496180727794-817822f65950?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1400&q=80',
            'https://images.unsplash.com/photo-1505691938895-1758d7feb511?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80',
          ]
        },
        {
          color: '#457b9d',
          url : [
            'https://images.unsplash.com/photo-1551516595-09cb9fcf8db5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1467987506553-8f3916508521?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2500&q=80',
            'https://images.unsplash.com/photo-1509422007420-a57adf7b7fdf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80',
            'https://images.unsplash.com/photo-1517467139951-f5a925c9f9de?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
          ]
        },
        {
          color: '#1d3557',
          url : [
            'https://images.unsplash.com/photo-1556228453-efd6c1ff04f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80',
            'https://images.unsplash.com/photo-1533779283484-8ad4940aa3a8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80',
            'https://images.unsplash.com/photo-1539779651182-2ecf717caddb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2467&q=80',
            'https://images.unsplash.com/photo-1513161455079-7dc1de15ef3e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
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
        },
        {
          color: '#f1faee',
          url: [
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/ew1lAU8yV66JbivBU1Zh4Wj4pOB.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/mQbxBKeRuAofS5p2w4mK7DqtS97.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/xDZ0tHXxesM34GGLJxr3CCnwPHU.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/ylQRi3edixhzUiubw7LQP1YhI6W.jpg',
          ]
        },
        {
          color: '#a8dadc',
          url: [
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/bqZRy3tJjJIrA38FSdqJEufQ2Os.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/cOwVs8eYA4G9ZQs7hIRSoiZr46Q.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/oyyUcGwLX7LTFS1pQbLrQpyzIyt.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/u1L4qxIu5sC2P082uMHYt7Ifvnj.jpg', 
          ]
        },
        {
          color: '#457b9d',
          url: [
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/tAXXuEAKLk2mOEIJzwajJQEnVZu.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/8UUZ2oV1OjYd55BMZWaQrcRPTGN.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/f6dNinWX8rBM79JXKcShkfSh2oA.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/eKZ07Ted7VHxQjbuZrRBFOamcKJ.jpg',
          ]
        },
        {
          color: '#1d3557',
          url: [
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/vnWgIIEWAvWKOI65tm6h6VRbyY8.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/grKPq3rneLXn9XFE2JeUmBuu8lH.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/dVhP5LObWUYWwcHKufcdUabApII.jpg',
            'https://image.tmdb.org/t/p/w600_and_h900_bestv2/qZARQ9ztjIBRfwmg8q3SdOVI6H1.jpg'
          ]
        }
      ];
      payload = mvImgList;
      commit('MU_MOVIE_IMAGE', payload)
    }
  }
}

export default imageStore;