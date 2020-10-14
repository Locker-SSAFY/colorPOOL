export default {
  ROUTES: {
    //회원관련
    signup: '/signup',
    socialSignup: '/signup',
    signin: '/signin',
    socialSignin: '/signin/social',
    getUserInfo: '/user?lang=ko',
    // 색깔과 배색
    getThemes: '/themes/',
    // 잡지 넣기
    postMagazine: '/api/magazine',
    // 모든 잡지 가져오기
    getMagazineList: '/magazines',
    getMagazineOne: '/magazine/',
    // 나랑 관련된 잡지 가져오기
    getMagazineMine: '/magazine',
    getMagazineBookmark: '/magazines/bookmark',
    // 잡지에 좋아요 누르기(또는 좋아요 취소하기)
    postMagazineLike: '/magazine/like',
  }
}