## JPA 사용시 객체 지향 설계에 대한 고찰

### 데이터 중심 설계의 문제점 ❗️

- 객체 설계를 테이블 설계 방식에 맞추면 객체 지향적이지 못함
- 테이블 외래키를 객체에 그대로 가져옴
- 객체 그래프 탐색이 불가능 (dot으로 들어가는 것)
- 참조가 없으므로 UML도 잘못됨


<br>

### Solution : Embedded Type 🔑
- 자바 객체에 Embedded Type을 사용해도 매핑하는 테이블은 같음
- 잘 설계한 ORM은 매핑한 테이블보다 클래스의 수가 많음
- 객체와 테이블을 세밀하게 매핑하는 것이 가능

#### 잘못된 예
``` java
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String city;
  private String street;
  private String zipcode;
}
```

#### 올바른 예
``` java
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private Address homeAddress;
}

@Embeddable
public class Address {
  private String city;
  private String street;
  private String zipcode;
}
```


<br>

### Solution : 연관관계 🔑

#### 잘못된 예
``` java
  Order findOrder = em.find(Order.class, 1L);
  Long memberId = findOrder.getMemberId();

  Member findMember = em.find(Order.class, memberId);
```

#### 올바른 예
``` java
  Order findOrder = em.find(Order.class, 1L);
  Member findMember = order.getMember();
```

> 이것을 해결하기 위해서는 연관관계를 맺어야 한다.

<br>

#### 객체의 연관관계
- 맴버 -> 주문 연관관계 1개 (단방향)
- 주문 -> 맴버 연관관계 1개 (단방향)

#### 테이블의 연관관계
- 멤버 <-> 주문 연관관계 1개 (양방향)

#### 연관관계 주인(Owner)

> 양방향 연관관마를 객체지향적으로 구성하기위해 단방향 2개로 만드는데서 오는 딜레마

- 객체의 두 관계중 하나의 연관관계의 주인으로 지정
- 연관관계의 주인만이 외래키를 관리(등록,수정)
- 주인이 아닌쪽은 읽기만 가능
- 주인이 아니면 mapped by 속성 사용 
- **외래키가 있는 테이블을 주인으로 한다 (ManyToOne)**
- 순수 객체 상태를 유지하기위해서 양쪽 객체에 모두 값을 셋팅한다
  - 영속상태에서 주인이 아닌쪽에 값 세팅을 안했을 경우 flush,clear를 안해주면 1차캐시에 남아있는 갑때문에 새로 조회를 하지않아서 에러가 발생한다.

> 주인 setter에서 함께 값을 세팅 한다.

``` java
  /* 연관관계 편의 메서드 */
  public void addSelectedColor(SelectedColor selectedColor) {
    this.selectedColor = selectedColor;
    this.selectedColor.getThemes().add(this);	// 양방향의 순수 객체 상태를 유지하기위함
   }
```
