# 심화 프로젝트 기획

> 큰 주제 : 빅데이터 기반의 컬러 추천 시스템 구현

<br>

# Wireframe

## 리서치 & 논의사항

### 컬러 추천 UI 관련

1. 메인 컬러 선택하기 관련
   - 수만가지의 선택지
     - 스포이트 형태
   - 그룹핑 된 선택지
     - **`컬러칩 형태 (팬톤)`**

2. 배색 선택지 보여주기 관련
   - 추천할 컬러 조합 단 한가지 제시 + 새로고침
   - **`여러 선택지 제시 (5개 정도)`**
   - 추천할 컬러 조합의 메인 / 서브를 어떤 비중으로 보여줄 것인지 고민
     - 선택지 가중치 차별
   - 메인컬러 선택 + 톤 선택 (ver.1.0.0)
     - Monochromatic / Analogous / Complementary / Split Complementary / Triadic / Tetradic
   - 메인 + 서브 개수 선택할 수 있도록 (ver.1.0.1)
   - 컬러칩 너비 & 위치 조정할 수 있도록 (ver.1.0.2)

<br>

### UI 레퍼런스

> 큰 키워드 : 패션 매거진, 고해상도 큰 이미지 + 여백 + 감각적인 폰트

- 웹매거진

  [나일론](https://www.nylon.com/)

  [Modern Matter](http://amodernmatter.com/shop/)

  [더젠틀우먼](https://thegentlewoman.co.uk/)

- 레이아웃 참고 (여백 쓰기)

  [렉토](http://recto.co/)

  [조이리치](https://joyrich.com/)

  [네타포르테](https://www.net-a-porter.com/en-kr/?cm_mmc=GoogleKR-KR--c-_-NAP_KR_KR-_-KREN_Brand--Netaporter_Exact-_-netaporter_e_aud-343396193481%3Akwd-488702032752_APAC&gclid=Cj0KCQjwv7L6BRDxARIsAGj-34rsYVHkr245wZayNY2z9Ju2bzFpBEOkhndPQMrsmgdFxD_my0B51IMaAgdTEALw_wcB&gclsrc=aw.ds)

- 고해상도 이미지 & 텍스트 조화

  [언더커버](https://undercoverism.com/stores/?s=JAPAN&name=KANTO)

<br>

### 레퍼런스에 따른 인사이트

- [카카오페이지](http://www.kakaopagecorp.com/)
  - 정량적 데이터를 시각화: 신뢰도 up 
  - 컬럼 기반의 그리드 시스템 + 가로 화면 풀로 쓰기: 동적 특성 up
- 서브 기능 => 배색에 대한 크롤링 정보의 유사도 값 제공하기

<br>

## 화면 단위의 논의

> 메인컬러 선택 후 이와 어울리는 컬러 조합 선택 하여 이를 통한 컨텐츠 구현하기

#### 키워드

- 직관적 화면 구성
  - 화면 전환 굳이 필요 없는 경우 전환 X
  - 화면 뎁스 깊지 않도록
- 반응형으로 구현
  - 잡지 커스텀 기능 -> 웹에서만 가능, 모바일에서는 뷰어만 제공

<br>

### 1. 랜딩 페이지

- 슬라이드 교차 효과
- 간단한 사용법 설명

<br>

### 2. 메인 페이지

- 맛보기 겸 튜토리얼
- 메인 컬러 선택([UI링크](https://codepen.io/cmegown/pen/VaMzQq)) 후 컬러 조합 확인
  - 세부 정보 보려면 로그인 필요 (로그인 유도)

<br>

### 3. 컨텐츠 추천

- 카테고리 : 이미지(패션, 리빙, 악세사리)
- 컨텐츠 기반 필터링 => 컨텐츠 주기적으로 업데이트
- 더 고민할 부분
  - 카테고리별로 나눠서 추천 컨텐츠를 제공할지?
  - 카테고리 없이 제공할지?
  - 한 카테고리에 집중할지(타겟 구체화)?, 매거진처럼 여러 카테고리를 가져갈지?

<br>

### 4. 컨텐츠 상세

- 컨텐츠 클릭 시 컨텐츠 디테일 보여줌 (ver 1.0.0)

- 나만의 매거진 커스텀하기 기능

  - pdf 저장 기능 구현 (ver 1.0.1)
  - template 설정 (ver 1.0.1)

  - 공유 기능 구현 (ver 1.0.2)

