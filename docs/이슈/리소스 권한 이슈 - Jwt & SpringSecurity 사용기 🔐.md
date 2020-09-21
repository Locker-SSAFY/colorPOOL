## 리소스 권한 이슈 - Jwt & SpringSecurity 사용기 🔐

### 사건의 발단 🤷
로그은 개인적인 컨텐츠를 얻기위해서 Authentication(인증)을 받는 일련의 과정이다.

개인적인만큼 보안이 중요하다. 따라서 로그인하는 방식도 쿠키 or 세션 or 토큰 방식이 있는데, 이중에서 우리는 JWT(Json Web Token)방식을 채택했다.

그 이유는 보안적으로도 훌륭하기도 하지만, 세션같은경우 ID를 서버에 저장하고 있기때문에 그보다 가벼운 JWT를 사용하기로 했다.

그런데 이때, 로그인을 하게되면 우리 서비스의 일부를 사용할 수있는 것이 당연하다. 그러나 모든것을 사용할 수는 없다. 

> 즉, 권한 여부를 판단하는 로직이 들어가야 했다.

가령 모든 사람이 API 서버에 접근할 수 있다면 심각한 문제가 발생한다. 따라서 API에 대한 Authorization(인가)가 필요한 상황이었다.

<br>

### 해결 방향 ✔

우리는 SpringSecurity를 이용해서 API로 오는 특정 request에 대해 `사용 권한`을 부여하기로 했다. 이 때, SpringSecurity는 기본적으로 모든 request를 `UsernamePasswordAuthenticationFilter`를 통해 권한을 확인한다. 이는 권한이 없다면 로그인 폼으로 보내게되는데, 우리는 API 서버이기 때문에 이 필터를 사용할 이유가 없었다.


> 필터는 WAS의 수문장이라고 생각하면 편하다. dispatcher 앞단에서 필터링을 해주는 역할을 한다.

`UsernamePasswordAuthenticationFilter`는 `AbstractAuthenticationProcessingFilter`를 상속받고 있고, 해당 클래스에서 doFilter를 통해 필터링 하는것을 확인했다. 그리고 doFilter는 아래와 같은 로직을 가지고 있었다.


#### requiresAuthentication

``` java
if (!requiresAuthentication(request, response)) {
	chain.doFilter(request, response);
	return;
}
```

해당 필터를 컨트롤 할 수 있는 authentication이 있는지 확인한다. 만약 권한이 없다면 FilterChain를 통해서 다음 필터로 넘어간다.

#### attemptAuthentication

``` java
try {
   	authResult = attemptAuthentication(request, response);
	if (authResult == null) {
		// return immediately as subclass has indicated that it hasn't completed
		// authentication
		return;
	}
	sessionStrategy.onAuthentication(authResult, request, response);
}
```
sessionStrategy은 세션 보안 관련 인터페이스인데, 우리는 세션을 사용하지 않으니 신경쓰지 않았다. 여기서 봐야할 점은 `attemptAuthentication` 메서드를 통해 권한이 확인 된다면 두가지 경우, success or unsuccess 가 return 된다는 점이다.

먼저 성공할 경우 필터를 만들 것이므로 `successfulAuthentication()` 메서드 내부에서 이루어지는 동작을 확인해야 했다. 해당 메서드에서 핵심 동작으로 아래와 같은 코드를 확인했다.

``` java
SecurityContextHolder.getContext().setAuthentication(authResult);
```

[SpringSecurity 공식 문서 - securitycontextholder](https://godekdls.github.io/Spring%20Security/authentication/#101-securitycontextholder)에서는 다음과같이 설명하고 있다.

- SecurityContextHolder - 스프링 시큐리티에서 인증한 대상에 대한 상세 정보는 `SecurityContextHolder`에 저장한다.
- SecurityContext - `SecurityContextHolder`로 접근할 수 있으며, 현재 인증한 사용자의 `Authentication`을 가지고 있다.

![SecurityContextHolder](https://godekdls.github.io/images/springsecurity/securitycontextholder.png)

따라서 이후에 Authenticated User에 접근하기 위해서는 SecurityContext에서 Authentication을 얻어올 수 있다.

#### Example : Access Currently Authenticated User
``` java
SecurityContext context = SecurityContextHolder.getContext();
Authentication authentication = context.getAuthentication();
String username = authentication.getName();
Object principal = authentication.getPrincipal();
Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
```

<br>


### 해결 📌

처음 우리가 마주 했던 이슈는 유저마다 API 사용 권한을 부여하는 방법을 갈구했던 이슈였다. 위에서 우리가 어떻게 권한을 부여하는지 로직을 알았으니 `Custom Filter`를 만들 차례다.

우리는 JWT 토큰을 사용하기때문에 `SecurityContextHolder`에 저장할 `Authentication`을 JWT를 통해서 만들 필요가 있었다.

#### Authentication
- principal - 사용자를 식별한다. 사용자 이름/비밀번호로 인증할 땐 보통 UserDetails 인스턴스다.
- credentials - 주로 비밀번호. 대부분은 유출되지 않도록 사용자를 인증한 다음 비운다.
- authorities - 사용자에게 부여한 권한은 `GrantedAuthority`로 추상화한다. 예시로 role이나 scope가 있다.

credentials은 비워주고, authorities는 각자의 권한에 맞게 넣어준다. 그리고 principal는 `userDetailsService`라는 인터페이스에서 `loadUserByUsername`를 통해 얻어 낼 수 있다. 

이 때, `UsernamePasswordAuthenticationToken()`를 이용해서 `Authentication`을 받아 냈다. 그리고 이렇게 만든 필터를 SpringSecurity 설정을 하는 SecurityConfiguration 클래스안에서 `UsernamePasswordAuthenticationFilter` 클래스 앞단에 넣어주었다.

``` java
http
    ...
    ...

	.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);

```

<br>

#### REF 📄

- [SpringSecurity 공식 문서](https://godekdls.github.io/Spring%20Security/contents/)
- [daddyprogrammer - SpringSecurity](https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/)