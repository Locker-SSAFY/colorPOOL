# axios에서 보낸 post 요청이 백엔드에서 차단하는 이슈

# axios

> Vue에서 권고하는 HTTP 통신 라이브러리. Promise 기반이고 문서화가 잘 되어있는 편이다.

다양한 request(get, post, update, delete 등)를 지원하고 있고 이번 프로젝트에서는 src/assets/api/axiosCommon.js에서 baseURL을 관리하고 모든 요청에 application/json이라는 헤더를 보낼 수 있도록 했다.

------

## 발생한 이슈는?

프론트엔드와 백엔드의 데이터 바인딩 작업을 하던 차 콘솔에 이상한 문구가 찍혔다.

```jsx
this request has been blocked; the content must be served over https.
```

우리가 흔히 아는 프로토콜은 **HTTP**와 **HTTPS**이다. 그럼 그 둘의 차이를 명확하게 구분지어 볼 수 있따. 우리가 프로젝트에서 프론트엔드를 만들 때는 localhost로 개발을 하는데, npm 또는 yarn을 주로 사용해서 개발을 하곤한다. 자세히 살펴보면 [https://localhost가](https://xn--localhost-jo27a) 아닌 [http://localhost인](http://xn--localhost-o292b) 것을 확인할 수 있는데 **https는 http 통신 프로토콜에서** secuirty 즉, **보안 요소를 추가한다는 점**이다.

왜 보안 요소가 중요할까?

> 백엔드와 보안의 중요성

웹 프로젝트에서 중요한 것은 서버와 클라이언트의 통신이다. 서버와 클라이언트가 통신하면서 모든 통신 내용이 오픈되어 있다면? 누구든지 백엔드에서 통신을 가로채 **무한 post를 통해 백엔드를 무력화시킬 수도 있다**. 백엔드의 Spring Security는 번거로울 수 있지만 반드시 필요한 것이다. 

> http와 https의 mixed content 이슈

프론트엔드가 요청을 보낼 때 발생한 에러는 **mixed content 관련 이슈**였다. 방금까지 서버랑 클라이언트랑 신나게 https 프로토콜 상에서 작업하면서 통신하고 놀고 있는데 사이에 갑자기 http가 끼어들면? 갑자기 서버는 얼굴을 붉히며 "아 너네랑 안 놀아"하면서 **요청을 무시하고 클라이언트에 에러가 있다**고 말해준다. 이 상황이 바로 mixed content 에러 상황이다. 단순히 http 통신으로 보낼 때만 발생하는 것이 아닌, http://image.jpg 이런식으로 이미지 url에 http가 끼어있어도 서버가 얼굴을 붉히는 경우가 있다.

------

## 그러면 해결 방법은?

> mixed content 해결 방법에 대하여

**mixed content의 문제**라고 생각했던 나는 https 프로토콜을 어기는, 즉 http로 시작하는 이미지나 통신이 있는지 몽땅 뒤져보게 되었다. 마치 힘들게 뒤진 것 같지만 사실은 visual studio code에서 그냥 검색했더니 이미지 몇개가 http로 시작하나 vue에 정적으로 들어있어 괜찮았던 것 같고, 코드 몇줄에도 있긴 했지만 주석처리가 되어 있었다.

구글에서 검색된 해결 방법 : <meta http-equiv="Content-Security-Policy" content="block-all-mixed-content">

모든 mixed content를 차단한다는 내용의 http 헤더 내용이었는데 뭔가 의심쩍었지만 **index.html 헤더**에 넣어보았지만 여전히 작동되지 않았다.

두번째 해결 방법 : <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

같은 내용이었지만 **upgrade-insecure-request**를 추가하라고 하셨고, 대충 해석해 본 결과 **서버에게 request를 보낼때** 서버가 얼굴을 붉히며 "아 너네랑 안 놀아" 하기 전에 이미 "야! 놀자"라고 **선수치는 거**랑 비슷하다. 서버에게 클라이언트가 **안전한 페이지라는 것을 명시적으로 언급**하고, 서버는 이것을 이해해주면서 **암호화되고 인증된 response를 클라이언트에게 보내주는 것**이다.

> 백엔드가 200을 주기는 하는데...

두번째 해결 방법을 사용하면서 프론트엔드의 post 요청에 200을 리턴하기 시작했다. 하지만...

```jsx
const url = "서버 요청 경로"
const header = {
	X-AUTH : 토큰,
	accept : '*'
}
axios.post(url, {headers: header}).then().catch();
```

일반적으로 axios 요청을 보내는 방식이다. url과 header에 미리 넣어놓고 요청을 해서 가독성을 최대한 높이려고 한다. 하지만 request에는 'header : {axios.post(url, header).then().catch()}' 이런식으로 보내고 있었다. url은 비어있고 header 하나에 모든 요청 내용(json)이 들어가 있었다. 즉, url 따로 header 따로 이런 게 안되는 것이었다.

> axios 공식 문서를 참고해보자

**axios 요청 자체에 이상이 있다.** 이럴 땐 어떻게 하는게 좋을까? 공식 문서와 실제 사용 예제를 보는 것도 하나의 방법이다.

```jsx
const token = localStorage.getItem('access_token');
var authOptions = {
	method: 'POST',
  url: SERVER.ROUTES.postMagazineLike + '?magazineId=' + this.magazine.magazineId,
  headers: {
	  'accept': '*',
    'X-AUTH-TOKEN': token
  },
  json: true
};
axios(authOptions)
.then((res) => {
	console.log(res)
	this.isLikeClicked = true;
})
.catch((err) => console.log(err))
```

공식 문서와 실제 사용 예제를 통해 이런 식으로 바꿔 보았다. 사실 이전에 내가 보내던 방식과 똑같은 것이다. 하지만 좀 더 명시적으로 method, url, headers를 지정하면서 백엔드에 알맞은 요청을 제대로 보낼 수 있었다.

------

## 결론

> 백엔드의 마인드... 천사 마인드

이렇게 요청에서 문제가 발생하면 프론트엔드 입장에서는 사실 억울할 수 있는 부분이 있다. 왜냐면 '지금까지 get 요청 보내서 잘 받아왔는데 게다가 post 방식으로 잡지까지 저장하고 있었는데 왜 이것만 안돼!!!' 라고 망상에 빠질수도 있으니까 말이다. 백엔드든 프론트엔드든 똑같이 **'post건 get이건 지금 다 똑같이 잘 맞춰놨는데?'**라고 할 입장이 충분하다. 나는 **프론트에서 해결할 만한 경우의 수 X 백엔드에서 해결할 만한 경우의 수**를 모두 디버깅해보는 편이다. 나는 요청 오류를 해결해보기 위해 백엔드 담당자 룬로에게 무한 배포를 시키고 말았고 결국 다음 날로 이슈를 미뤄졌다. 결국 어찌됐든 적은 것과는 달리 힘든 여정과 시간 투자를 거쳐 해결할 수 있었고 이 문서를 통해 다시 한 번 **백엔드의 아버지 룬로의 선한 심성과** **나를 이해해주는 마음씨에 고마움을 표하는 바**이다.