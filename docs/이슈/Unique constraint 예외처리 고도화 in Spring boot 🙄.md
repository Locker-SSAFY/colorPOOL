## 중복 회원 검증에 대한 이슈 🙄


### 사건의 발단 🤷
Spring JPA를 사용하면 EntityManager를 통해 Entity를 관리한다. 관리되는 Entity중 persist 메서드를 통해 영속성에 올릴 수 있다. 회원 가입 로직을 구성하면서 User Entity를 영속성에 올렸다.

#### UserRepository
``` java
private final EntityManager em;

public void save(User user) {
	em.persist(user);
}
```

물론 우리는 Spring boot data jpa를 사용함으로 위와같은 과정이 함축되어 있다. 이렇게 구성된 save() 메서드를 통해서 회원가입을 진행 할 수 있다. 여기서 생각해봐야할 점은 '중복 회원 검증을 어떻게 할 것인가' 이다.

일반적으로 생각할 수 있는 것은, 아래와 같이 서비스단에서 DB에 중복 회원이 있는지 검사하는 방법이다.


#### UserService

``` java
	/**
	 * 회원 가입
	 * @param user
	 * @return
	 */
	@Transactional
	public Long join(User user) {
		validateDuplicateUser(user); // 중복회원 검증
		userJpaRepository.save(user);
		return user.getId();
	}

/**
	 * 중복회원 검사
	 * 
	 * @param user
	 */
	private void validateDuplicateUser(User user) {
		Optional<User> optionalUser = userJpaRepository.findByEmail(user.getEmail());
		optionalUser.ifPresent(findUser -> {
			throw new CUserDuplicatedException();
		});
	}
```

이와 같이 서비스단에서 중복 검증 과정을 거치면서 1차적인 방지를 할 수 있다. 그러나 한가지 간과한 사실이 있다는 것을 깨달았다. 만약 많은 클라이언트들이 동시에 같은 아이디로 회원 가입을 한다면, 미처 데이터베이스에 들어가기 전에 중복 회원 검증이 이루어져서 동일한 아이디로 회원가입이 이루어진다는 것이다. 즉, 데이터베이스에 commit이 되기전에 중복 회원을 검증할 필요가 있었다.

이 부분은 회원의 아이디를 단순히 유니크 키로 설정하면 되는 부분이었다. 따라서 User Entity의 유저 아이디로 사용하고 있는 email에 유니크 제약조건을 걸어 주었다.

#### User Entity
``` java
@Entity
Public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	private Long id;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	...
}
```

이제 이러한 로직을 가지고 두가지 상황에 대해 테스트코드를 작성해 봤다. 

#### Test code

``` java
	 @Test
	 public void 중복_회원_예외() throws Exception {
		 // given
		 User user1 = User.builder()
			 .email("test@test.com")
			 .password(passwordEncoder.encode("1234!"))
			 .nickname("testMan")
			 .provider("root")
			 .roles(Collections.singletonList("ROLE_TEST"))
			 .build();

		 User user2 = User.builder()
			 .email("test@test.com")
			 .password(passwordEncoder.encode("1234!"))
			 .nickname("testMan")
			 .provider("root")
			 .roles(Collections.singletonList("ROLE_TEST"))
			 .build();

		 // when
		 userService.join(user1);

		 // then
		 assertThrows(CUserDuplicatedException.class, () -> {
			 userService.join(user2);
		 });
	 }
```

처음 작성한 테스트 코드는 validateDuplicated에 대한 검증으로 예상한대로 Custom User Duplicated Exception이 발생하는 것을 확인했다. 이제 unique constraint에 대한 처리가 잘 적용이 되었나 확인이 필요했다.

 ``` java
 	@Test
	public void 동시_같은_아이디_가입() throws Exception {
		// given
		User user1 = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.nickname("testMan")
			.provider("root")
			.roles(Collections.singletonList("GUEST"))
			.build();

		User user2 = User.builder()
			.email("test@test.com")
			.password(passwordEncoder.encode("1234!"))
			.nickname("testMan")
			.provider("root")
			.roles(Collections.singletonList("GUEST"))
			.build();

		// when
		userJpaRepository.save(user1);

		// then
		userJpaRepository.save(user2);
	}
 ```

 위와 같이 테스트 코드를 작성하였더니 마찬가지로 예외가 발생했다. 그러나 이번에 발생한 예외는 `DataIntegrityViolationException`를 던지고 있었다. 해당 Exception은 Spring에서 제공하는 Exception으로 Hibernate에서의 예외인 `ConstraintViolationException`을 변환해서 사용하고 있었다. 그런데 여기서 문제는 번역하는 과정에서 Constraint name이 유실되고 있다는 점이었다.

 <br>

 ### 해결 방향 ✔

 해결하기위해서 만약 Hibernate의 번역 로직을 customize 하려면 `LocalSessionFactoryBean`에 접근이 가능해야한다. `LocalSessionFactoryBean.jdbcExceptionTranslator`에서 주어진 HibernateException을 적절한 Exception으로 번역할 수 있기 때문이다. 그렇게 한다면 유실된 내용을 보존할 수 있다.


``` java
	/**
	 * Convert the given HibernateException to an appropriate exception from the
	 * {@code org.springframework.dao} hierarchy.
	 * ... more
	 */ 
	 protected DataAccessException convertHibernateAccessException(HibernateException ex) {}
```

그러나 우리는 Spring에서 번역한  `DataIntegrityViolationException`를  `@ExceptionHandler`로 예외 메세지를 controller 할 수 있다. 이 때, MessageSource와 i18n로 다국어 메세지를 정의했다. 메세지 소스는 client가 request시 언어에 대한 정보를 `LocaleChangeInterceptor`를 통해서 인터셉트하고 `LocaleResolver`가 세션에 지역설정하는 식으로 이루어진다.

``` java
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // 409
	protected CommonResult userEmailConstraintException(HttpServletRequest request, DataIntegrityViolationException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("userEmailConstraint.code")),
			getMessage("userEmailConstraint.msg"));
	}
```

<br>

### REF 📄
[How to handle dataintegrityviolationexception in Spring](https://stackoverflow.com/questions/2109476/how-to-handle-dataintegrityviolationexception-in-spring)