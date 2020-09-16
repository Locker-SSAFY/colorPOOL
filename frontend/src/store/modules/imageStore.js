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
        },
        {
          color: '#f1faee',
          url : [
            'https://images.unsplash.com/photo-1570612112823-fd5f588cbedf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1231&q=80',
            'https://images.unsplash.com/photo-1595859703399-81f669e79fca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1575201647632-45fae95c9ce4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2666&q=80',
            'https://images.unsplash.com/photo-1584184804426-5e2aa23c2937?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
          ]
        },
        {
          color: '#a8dadc',
          url : [
            'https://images.unsplash.com/photo-1594223237623-0f92f53328da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1591765015673-af66196b407d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1591561954557-26941169b49e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1584917865442-de89df76afd3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80',
          ]
        },
        {
          color: '#457b9d',
          url : [
            'https://images.unsplash.com/photo-1597621804145-af3c39fb162c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
            'https://images.unsplash.com/photo-1546241183-0ed3f8a4a824?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80',
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
            'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=662&q=80',
            'https://images.unsplash.com/photo-1509631179647-0177331693ae?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1539109136881-3be0616acf4b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1548549557-dbe9946621da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
          ]
        },
        {
          color : '#f1faee',
          url : [
            'https://images.unsplash.com/photo-1545291730-faff8ca1d4b0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1479064555552-3ef4979f8908?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
            'https://images.unsplash.com/photo-1550614000-4895a10e1bfd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80',
            'https://images.unsplash.com/photo-1554141220-83411835a60b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=678&q=80',
          ]
        },
        {
          color: '#a8dadc',
          url : [
            'https://images.unsplash.com/photo-1495385794356-15371f348c31?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=619&q=80',
            'https://images.unsplash.com/photo-1500643752441-4dc90cda350a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=657&q=80',
            'https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
            'https://images.unsplash.com/photo-1487222477894-8943e31ef7b2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=726&q=80'
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