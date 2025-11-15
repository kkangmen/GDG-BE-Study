## 객체 지향이란?
### 객치 지향 프로그래밍
- 여러 프로그램을 객체들의 상호작용으로 파악하고자하는 컴퓨터 프로그래밍의 패러다임
- 유연하고 변경이 용이하다. (`객체들을 레고처럼 사용`)

### OOP의 4가지 특징
- 1. Abstraction: 객체의 공통적인 속성과 기능을 추출하여 정의
- 2. Encapsulation: 서로 연관있는 속성과 기능들을 하나의 캡슐로 만들어 외부로부터 보호
- 3. Inheritance: 기존 클래스의 속성과 기능을 자식 클래스가 물려받아 재사용
- 4. Polymorphism: 어떤 객체의 속성이나 기능이 상황에 따라 여러 가지 형태를 지닐 수 있음 -> **어떤 인터페이스에 대해 여러 구현 방식이 존재할 수 있는것**

## SOLID 원칙
### SOLID: 좋은 객체 지향 설계를 위한 5가지 원칙
- SRP: 단일 책임 원칙

    - 하나의 클래스는 단 한 개의 책임을 가져야 함
- OCP: 개방-폐쇄 원칙

    - 확장에는 열려있고, 수정에는 닫혀있다.
    - 기존 코드를 변경하지 않고, 기능을 확장할 수 있도록 설계할 수 있어야 함.

- LSP: 리스코프 치환 원칙

    - 자식 클래스는 언제나 자신의 부모 클래스를 대체할 수 있어야 함.
    - 단순히 문법적인 상속이나 타입 호환성을 넘어서 행동적인 호환성까지 보장되어야함 (`기어 D면 전진을 해야함. 자식 클래스에서 후진하면 안됨`)

- ISP: 인터페이스 분리 원칙

    - 인터페이스를 각각 사용에 맞게끔 분리하여야 함. (`리모컨 기능에 온도조절, 에어컨 전원, 라디오 버튼 기능이 모두 있는데, 라디어 버튼에 어떤 기능을 추가하려면 다른 필요없는 인터페이스까지 필요함`)

- DIP: 의존관계 역전 원칙

    - 인터페이스에 의존해야지, 구현체에 의존하면 안됨

![](https://velog.velcdn.com/images/kkangmen/post/811504d2-266a-4bbf-a9d7-e8864d19efba/image.png)
- 이렇게 `MemberService`가 상황에 따라서 `JpaMemberRepository`나 `MemoryMemberRepository`를 바꿔 끼울 수 있게 할때 **OCP와 DIP**를 위반하게 된다. `MemberRepository`의 구현체를 변경하고 싶으면 `MemberService`코드를 변경해야 하기에 OCP가 위반되고, `MemberService`는 구현 클래스를 의존하고 있기에 DIP도 위반이 된다.

- 결국 **의존성 주입**을 통해 해결한다.
## Spring Bean, Dependency Injection, Inversion of Control
### IoC (제어의 역전)
- 객체 생성 및 관리에 대한 제어권을 스프링 컨테이너가 해준다. 스프링 컨테이너를 IoC 컨테이너라고도 한다. Spring Container는 Spring Bean이 담겨있는 저장소이다. `Application Context`를 통해 선언.

### 싱글톤 컨테이너
- Spring Container는 객체를 딱 1개만 생성해서 필요할때마다 재사용할 수 있게 한다. 매번 필요한 객체를 생성하는 대신, 생성해둔 객체를 사용하므로 메모리를 효율적으로 사용할 수 있다.

### 의존성 주입 (Dependency Injection)
- IoC를 구현하는 방법이다. 의존하는 객체를 직접 생성하지 않고 밖에서 주입받는다. 즉, 스프링 컨테이너에 필요한 Bean을 미리 생성해두고, 다른 객체에서 이 객체가 필요할 때 주입한다.
- 인터페이스만 의존하고, 구현 객체는 외부에서 주입받는다.
- application 실행 시점에 객체 간의 관계를 결정한다.
- **빈 자동 등록**
  `@ComponentScan`, `@Component`를 통해 이 클래스는 Spring Bean임을 명시한다.
- **의존관계 자동 주입**
  `@Autowired`를 통해 spring container에 등록된 빈 중에서 필요한 타입의 객체를 자동으로 찾아서 생성자 주입을 통해 주입시켜준다.
- **생성자 주입**
``` java
public class MemberService {
	private final MemberRepository memberRepository;
    
    @Autowired
    public MemberService(MemberRepository memberRepository){
    	this.memberRepository = memberRepository;
    }
}
```
    - 생성자 호출 시점에 1번만 호출되는 것을 보장한다.
    - `@RequiredArgsConstructor`가 final이 붙은 필드의 생성자 주입을 자동으로 수행해준다. 
- **조회되는 빈이 2개 이상일때**

    - 구현체에 `@Primary`를 통해 명시해준다. 스프링 컨테이너는 `@Primary`가 붙은 빈을 먼저 주입해준다.
- 기능을 확장할 가능성이 없다면? 우선 구현체로 만들고 나중에 확장이 필요해졌을 때 리펙터링을 통해 인터페이스를 도입

## 실전 코드
### member 도메인에서
`MemberController` -> `MemberService (인터페이스)` -> `MemberServiceImpl | 다른 구현체 선택` -> `MemberRepository(인터페이스)` -> `JpaMemberRepository(구현체) | MemoryMemberRepository(구현체)`

**MemberController**
``` java
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
```

**MemberServiceImpl**
``` java
@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
```

**JpaMemberRepository**
``` java
@Repository
@Primary
public class JpaMemberRepository implements MemberRepository{
```