const questions = [
  '요즘 나는 안녕한가요?',
  '요즘 내가 주로 느끼는 감정은 무엇인가요?',
  '요즘 내 마음을 무언가에 비유한다면 무엇일까요?',
  '하루 중 내가 가장 좋아하는 순간은 언제인가요?',
  '하루 중 내가 가장 좋아하는 시간대는 언제인가요?',
  '듣는 것만으로 나를 행복하게 해주는 말 한마디는 무엇인가요?',
  '요즘 내가 좋아하는 색깔은 무엇인가요?',
  '요즘 나는 어떤 스타일의 옷을 즐겨 입나요?',
  '최근 내 마음에 들어온 책 제목은 무엇인가요?',
  '요즘 내 귀에 자꾸 맴도는 노래가사는 무엇인가요?',
  '방에 액자를 딱 하나 걸 수 있다면 어떤 사진을 걸고 싶은가요?',
  '최근 나에게 가장 좋았던 여행의 순간은 언제였나요?',
  '요즘 나와 참 닮았다고 느껴졌던 영화 속 주인공은 누구인가요?',
  '최근 내가 나답다라고 느꼈던 순간은 언제였나요?',
  '요즘 내가 애정하는 공간은 어디인가요?',
  '요즘 내가 몰입하고 있는 것은 무엇인가요?',
  '만약 지금 하고 있는 일이 아닌 다른 일을 할 수 있아면, 어떤 일을 해보고 싶나요?',
  '가까운 시일 내에 온전히 나만을 위한 하루가 주어진다면, 어떻게 보내고 싶나요?',
  '요즘 여행가고 싶은 곳은 어디인가요? 어떤 여행을 꿈꾸나요?',
  '나는 어떤 신념과 믿음을 가진 사람인가요?',
  '만약 지금 이 순간, 가장 아끼는 사람이 내게 인생 조언을 해달라고 한다면, 어떤 말을 해주고 싶나요?',
  '나 자신에게 작은 선물로 고마움을 표현한다면, 무엇을 선물해 주고 싶나요?',
  '지금 나를 가장 불안하게 하는 것은 무엇인가요?',
  '요즘 나는 나 자신을 얼마나 믿고 있나요?',
  '나는 어려움이 닥쳤을 때 어떻게 이겨내는 사람인가요?',
  '지금 나에게 필요한 위로는 무엇인가요?',
  '사람들에게 나는 살아가는 동안 어떻게 살았던 사람으로 기억되고 싶나요?',
  '나의 가까운 사람들에게 어떤 존재이길 바라나요?',
  '나에게 가장 큰 영향을 미친 사람은 누구인가요?',
  '최근 선택한 것 중 가장 잘했다고 생각하는 선택은 무엇인가요?',
  '올해 내 삶에 일어난 기적 같은 일은 무엇인가요?',
  '1년 이내 과거 어느 시점으로 돌아갈 수 있다면, 언제로 돌아가 어떻게 살아보고 싶나요?',
  '요즘 내 삶의 속도는 어떤가요? 그것이 나에게는 어떻게 느껴지나요?',
  '요즘 나는 내 인생에 있어 어떤 타이밍에 서 있다고 느껴지나요?',
  '요즘 내가 가지고 있는 습관은 무엇인가요?',
  '요즘 나는 하루 중 무엇에 가장 많은 시간을 할애하고 있나요?',
  '요즘 내 일상을 조금 더 만족스럽게 해 줄 무언가를 딱 하나 더한다면, 무엇이 좋을까요?',
  '요즘 내게 와 닿는 명언에는 무엇이 있나요?',
  '요즘 내게 가장 좋은 영감을 주는 것은 무엇인가요?',
  '요즘 내가 자꾸 고민하게 되는 것은 무엇인가요?',
  '요즘 나는 무엇을 배우고 싶은가요?',
  '요즘 나에게 하고 있는 것만으로도 행복한 일은 무엇인가요?',
  '오늘 저녁, 어떤 음식을 먹으면 행복할 것 같나요? ',
  '이 세상 누구나 초대할 수 있다면 저녁 식사에 누구를 초대하겠어요?',
  '유명해지고 싶나요? 그렇다면 어떤 방식으로?',
  '전화를 걸기 전에 무슨 말을 할지 미리 연습해 본 적이 있나요? 그랬다면 왜?',
  '당신이 생각하는 “완벽한 하루” 는 어떤 날인가요?',
  '해 본 적은 없지만, 늘 해보고 싶었던 일에는 무엇이 있나요?',
  '마지막으로 혼자 노래해본 적이 언제인가요? 누군가에게 불러준 적은?',
  '당신이 어떤 방식으로 죽음을 맞이할 것 같나요?',
  '살면서 가장 감사하게 생각하는 일은 무엇인가요?',
  '최근 나의 일년을 담아 한편의 영화로 만든다면, 그 영화의 제목은 무엇이 될까요?',
  '어린 시절 자라난 환경을 바꿀 수 있다면 무엇을 바꾸겠어요?',
  '아름다움, 성실함, 가창력 등 하나의 능력을 가질 수 있다면 무엇을 고르겠어요?',
  '살면서 가장 보람있었던 일은 무엇이었나요?',
  '진정한 우정을 위해 가장 필요한 건 무엇이라고 생각하나요?',
  '가장 소중한 추억에 대해 말해주세요.',
  '가장 끔찍한 기억은 무엇이었나요?',
  '가장 부끄러웠던 경험을 말해보세요.',
  '앞으로 1년밖에 살지 못한다면, 지금 당신이 살고 있는 방식에 어떤 변화를 줄 건가요?',
  '사랑과 애정은 당신의 인생에 있어서 얼마나 중요한가요?',
  '마지막으로 다른 사람 앞에서 울었던 적은 언제인가요? 혼자 울었던 적은?'
]

export default questions;