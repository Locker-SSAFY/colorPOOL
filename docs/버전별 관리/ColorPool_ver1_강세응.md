# 역할

- Front-End UI 초기 구현
- 반응형 웹 초기 구현

------

# 구현 내용

## [화면]

- 메인 화면

  - 랜딩 페이지

    <img src="https://i.ibb.co/88k3y1q/landing.png">

    색상을 고르는 **pick Color 컴포넌트**와 검색을 통한 이미지 검색에서 대표색을 가져오는 **get Color 컴포넌트**가 보이는 **랜딩 페이지**

    각 컴포넌트 선택시 pick Color 페이지가 보이거나 get Color 페이지가 보이도록 구현

  - 색상 선택 페이지

    <img src="https://i.ibb.co/xJ8Qttv/pick.png">

    원하는 색상을 총 **200가지 색깔이 있는 팔레트**에서 선택할 수 있음

    색상에 따라 **사진 뒷 배경색**이 변함

  - 색상 추출 페이지

    <img src="https://i.ibb.co/NVkHyF0/get.png">

    [server.picular.co](http://server.picular.co)에서 **구글 기반 이미지 검색**, 이미지의 메인 색상을 추출해서 가져올 수 있음

    색상들을 ul 4개로 보여줌 → *추후 responsive 하게 모바일에서는 ul 하나에 나오도록 변경할 예정*

    **색상에 마우스를 가져다 대면** 어떤 이미지인지 **preview 확인**할 수 있음

- 카테고리 & 이미지 화면

  - 카테고리 페이지

    <img src="https://i.ibb.co/2NBzdf9/category.png">

    로그인 후 **배색을 선택하면 카테고리 페이지**가 랜딩 페이지

    리빙, 악세사리, 패션, 영화, 아직 미정인 카테고리들.. 을 선택할 수 있도록 버튼이 있음

    왼쪽에 사용자가 선택한 배색 조합을 보여줌*(ThemeBar.vue)*

  - 이미지 페이지

    <img src="https://i.ibb.co/9ph3VjZ/image.png">

    카테고리를 선택하면 이미지들을 carousel-3d로 보여주고 **더블 클릭해서 선택**할 수 있게 함

    **선택**한 이미지들을 **오른편에 띄워주고** **삭제**할 수 있도록 함

    **이미지 선택**이 10개가 넘어도 추가는 가능하지만 **10개까지만 가능**하다고 보여줌

- 반응형 화면 🖥️ → 📱

  - 모바일 랜딩 페이지

    <img src="https://i.ibb.co/wRWKKZJ/mobland.png">

    🍎갓뮹님의 조언에 따라 화면을 그리드 12칸으로 나누는 방식으로 반응형 초기 구현

    모바일📱에서는 양쪽에 있던 컴포넌트를 위 아래 배치로 변경

  - 모바일 색상 선택 페이지

    <img src="https://i.ibb.co/Nt2Rh80/mobpick.png">

    컬러 피커를 왼쪽이 아닌 위에 배치


## [로직]

- imageStore.js

  <<img src="https://i.ibb.co/yyXGfzd/store.png">

  **store에서** state, getters, mutations, actions를 사용해서 **데이터를 저장하고 가져올 수 있음**

  imageStore에서는 추후에 axios를 사용해서 **백엔드에 이미지 크롤링을 요청하고 가져와야 함

## [이슈]

- carousel-3d에서 이미지들을 동적으로 처리할 때 carousel에 이미지가 나타나지 않는 현상 (div 높이를 조절하면서 해결)
- 반응형으로 웹을 만들 때 화면 오른쪽과 아래쪽에 여백이 생기는 현상 (overflow-x를 hidden 하여 임시로 해결)