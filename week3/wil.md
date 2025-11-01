## 1. Spring Layered Architecture
**스프링 웹 계층**
![](https://velog.velcdn.com/images/kkangmen/post/04a5f46e-d419-4539-94a8-b48748d8095c/image.png "GDG 홍익대 백엔드 정규 스터디")

- Brower(웹 계층): Client
- Controller: Http request/response가 오면 controller에서 가장 먼저 처리. 알맞은 controller를 찾아 DTO를 사용하여 service 계층과 데이터를 주고받음.
- Service: 비즈니스 로직이 담기는 계층. 로직 실현 위해 레포지토리 계층 접근할 수도. 로직 실현 후 controller 계층에 반환.
- DAO/Repository: DB에 접근하여 CRUD 작업을 수행하는 계층.
- DB: 실질적 정보 저장 공간.

- DTO: Data Transfer Object, 데이터 전송 객체
- Entity(Domain): DB와 직접 매핑되는 객체

## 2. ORM과 JPA
### JDBC(Java Database Connectivity)
- Java 프로그램이 DB와 소통할 수 있도록 도와주는 '표준 자바 API'. Java와 DB 사이의 통역기.

- JDBC가 왜 필요한가요?
  DB는 MySQL, Oracle, PostgreSQL 등 종류가 많고, 각각의 프로토콜이 다르다. 만약 JDBC가 없다면, 개발자는 3가지 DB와 소통하기 위한 코드를 따로 만들어야 한다. JDBC는 이 문제를 해결하기 위해 "표준화된 통신 API"를 제공한다.

- JPA는 JDBC없이 작동할 수 없다. JPA는 개발자를 편리하게 해주는 도구이고, DB와 실제로 통신하는 마지막 단계는 JDBC가 담당한다.

### ORM(Object-Relational Mapping)
- ORM 등장배경
  DB를 다루기 위해 같은 객체에 대해 반복적인 SQL 코드를 작성하는것은 힘들다. 그래서 객체와 관계형 DB의 데이터를 자동으로 매핑해주는 ORM 기술이 등장하였다.

### JPA(Java Persistence API)
- 자바 진영의 ORM 기술 표준이다.
- 자바에서 객체를 데이터베이스에 저장하고 관리하기 위한 인터페이스와 기능을 제공하는 API이다. 구현체로는 **Hibernate**가 있다.

즉, 개발자는 SQL문이 아닌 객체 중심 함수 형태로 작성하여 호출하여 JPA(Hibernate)가 알아서 SQL문으로 바꿔준다. 그러면 JDBC가 DB에 통신하여 SQL문을 처리하여준다.

![](https://velog.velcdn.com/images/kkangmen/post/d7c342f2-621e-421c-a601-87d0add49fe3/image.png)

## 3. JPA의 동작 방식
### EntityManager
- JPA에서 DB 작업을 실제로 수행하는 핵심 객체.(Entity 객체를 DB와 연결해주는 창구 역할)
- **EntityManager가 하는 가장 중요한 역할**: **영속성 컨텍스트(Persistence Context)** 관리.
- **영속성 컨텍스트(1차 캐시)**: Entity객체들을 DB에 저장하기 전에 **임시로 보관하는 메모리상의 '가상 저장소'.** 한 번 조회한 객체를 영속성 컨텍스트가 내부에 저장해두고 재사용하는 구조. 이미 등록된 객체를 조회할 때는 DB를 거치지 않아도 된다.
+ 동일성 보장 -> 트랜잭션 안에서는 같은 엔티티를 여러번 조회해도 영속성 컨텍스트에 보관된 동일한 객체를 그대로 반환한다.
- **EntityManager의 역할**: `EntityManager`가 생성될 때, 영속성 컨텍스트도 함께 만들어진다. `EntityManager`는 `em.persist, em.find`와 같은 모든 작업을 DB로 직접 보내는 것이 아니라, **영속성 컨텍스트(1차 캐시)**를 먼저 거치도록 만들어줌
  ![](https://velog.velcdn.com/images/kkangmen/post/ba5a2d93-170b-47af-8d54-a36ea44fb316/image.png "https://psvm.kr/posts/tutorials/jpa/3-em-and-persistence-context")

### 트랜잭션
- DB에서 수행되는 작업의 단위
- 여러 DB연산을 하나의 논리적인 작업 단위로 묶어서 실행

    - 모든 연산이 성공적으로 수행되면 트랜잭션을 커밋하여 DB에 반영
    - 하나라도 실패하면 롤백하여 이전 상태로 되돌림
- **쓰기 지연**
  DB에 바로 쓰지 않고, 변경사항들을 영속성 컨텍스트에 모아두었다가 트랜잭션이 커밋되는 시점에 한 번에 묶어서 DB에 보냄.
- **변경 감지**
  영속성 컨텍스트가 엔티티의 변경 사항을 자동으로 감지하고, 트랜잭션 커밋 시점에 변경 내용을 DB에 반영한다.
- 예제
  **Entity 등록**
  `em.persist(member1)` -> 영속성 컨텍스트에 member1객체 보관
  `em.persist(member2)` -> 영속성 컨텍스트에 member2객체 보관
  `em.flush()` -> 영속성 컨텍스트의 변경 내용을 DB에 동기화(한번에)
  **Entity 조회**
  `em.find(member1)` -> 영속성 컨텍스트 확인, 없다면 DB조회 후 1차 캐시에 저장 후 반환
  `em.find(member1)` -> 영속성 컨텍스트 확인, 있으므로 DB에 접근하지 않고 바로 반환.
  **Entity 수정**
  `em.find(member1)` -> 영속성 컨텍스트 확인, 없다면 DB조회 후 1차 캐시에 저장
  서비스 로직에서 `member1.setName("new name")` -> 영속성 컨텍스트 내 객체가 변경됨
  `@Transactional`이 붙은 서비스 메서드가 종류되고, 커밋되는 시점에 변경감지를 통해 감지하였고 DB에 반영하기 위해 `UPDATE` SQL문으로 자동으로 변경
  **Entity 삭제**
  `em.find(member1)` -> 영속성 컨텍스트 확인, 없다면 DB조회 후 1차 캐시에 저장
  `em.remove(member1)` -> 멤버 엔티티 1번 삭제
  `DELETE member1 from table` -> DB에서 entity 삭제