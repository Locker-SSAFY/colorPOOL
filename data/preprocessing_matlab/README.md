# Matlab을 활용한 데이터 전처리

- kulerData.mat

  - 테마 정보가 담겨있는 파일

  - field(구성요소)

    - data: 테마를 구성하는 컬러 5개 각각의 r,g,b 값 (r1,...,r5, g1,...,g5, b1,...,b5 순)

    - ids : 테마의 고유 번호

    - names : 테마의 이름

    - targets: 디자이너들이 평가한 테마의 점수

      

- kulerDataFilter.m
  - kulerData.mat에서 target<4인 field들을 필터링
  - 이후 정제된 field들을 묶어 table(KulerDataTable)을 구성한 후, 이를  kulerData.csv로 저장



- RGBtoLab.m

  - 양자화(인간의 시각 기준으로 색차 보정)룰 위해 KulerDataTable의 RGB 를 CIE L*a*b으로 변환 

  - 실행 후 dataCIEtable 생성 되며, 이를 kulerCIEdata.csv로 저장

    

