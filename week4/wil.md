## ERD(Entity-Relation Diagram)
### ERD 란,
- **데이터 청사진**
- Entity(개체): 데이터를 가진 대상
- Relation(관계): 개체 사이의 연관겅
- ER Model을 시작적으로 표현 -> ERD "개발자와 클라이언트 간 소통 도구"

### DB 설계
- 1. Entity: 관리해야 할 데이터의 주체
     `ex) Member, Product, Order`

- 2. Attribute: 각 엔티티가 가지는 구체적 정보
     `ex) Member: id, name, address
          Product: name, price, stock`

- 3. Primary Key(PK): member_id, product_id, order_id
     **고유하게 식별**하는 데 사용되는 하나 이상의 속성

- 4. Forign Key(FK):
     다른 테이블의 PK를 참조하는 속성

- 5. Relation(관계): 개체 사이의 연관성, 업무 규칙
     매핑카디럴리티: 다대일(N:1), 일대다(1:N), 일대일(1:1), 다대다(N:M)

    - 일대다
      1명의 member가 여러 개의 order를 가진다. -> order 테이블은 member_id를 FK로 가짐

    - 다대다
      한명의 student는 여러 개의 course를 수강할 수 있다. + 하나의 course는 여러명의 student가 수강할 수 있다. -> 중간 테이블 (연결 엔티티)로 Enrollment 테이블 도입.
      Enrollment 테이블의 구조: enrollment_id(PK), student_id(FK), course_id(FK) .. 추가 속성. 즉, 테이블로 관계를 구현한다.

### ERD Cloud
- 일대다 (1:N)

  ![](https://velog.velcdn.com/images/kkangmen/post/4a20beb3-f946-4752-9c3d-14d90045a6db/image.png)

- 다대다 (N:M)

  ![](https://velog.velcdn.com/images/kkangmen/post/3797f941-d284-4ed8-ac9b-cd2f4f1f0927/image.png)

- `long type -> bigint`

## 실전 코드
### .properties vs .yml
- `.properties` 파일에 비해 `.yml`은 계층 구조를 가진 데이터를 표현하는데 훨씬 강력하고 가독성이 좋다.
    1. 들여쓰기를 통해 상하 관계 표현 용이
    2. 리스트 및 배열 표현 용이
    3. 하나의 파일로 여러 프로필 관리 가능 (`---` 구분자 사용해서)

**application.yml**
``` yml
spring:
  application:
    name: demo

  datasource: # 관리자 콘솔 url(jdcd:h2:mem:demo로 접속)
    url: jdbc:h2:mem:demo;MODE=MYSQL # MODE=MYSQL(H2가 MySQL처럼 동작)

  h2: 
    console: # 관리자 콘솔 활성화
      enabled: true

  jpa:
    show-sql: true # JPA가 생성한 SQL 표시
    properties:
      hibernate:
        format_sql: true # 들여쓰기 적용
        dialect: org.hibernate.dialect.MySQL8Dialect # SQL 생성 시 MySQL8 문법 사용
```

### 엔티티 클래스 어노테이션
- `@Entity`: DB에서 저장되고 관리되는 객체임을 명시
- `@Id`: 해당 속성은 PK임을 지정
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: 키 값 결정을 DB에게 위임 (여기서는 ID값(PK)이 1씩 증가)
- `@Table(name = "members")`: DB에 저장되는 테이블 이름 명시
- `@NoArgsConstructor`: 기본생성자
  사용하는 이유: JPA가 DB에서 데이터를 조회한 뒤 엔티티로 변환할 때, 객체를 생성하기 위해 '기본 생성자'를 사용하기 때문.

### 외래키 어노테이션
- `@ManyToOne(fetch = FetchType.LAZY)`:
  다대일 관계를 나타낸다. 즉, Order의 엔티티의 `member`필드에 붙는다.
  지연로딩 - JPA가 `Order` 엔티티를 데이터베이스에서 조회할 때, 관계가 맺어진 `Member` 엔티티를 즉시 함께 조회하지 않고, `Order` 객체를 가져온 뒤, 실제 코드에서 `order.getMember()` 처럼 해당 `Member` 객체를 사용하려는 시점에 `Member` 데이터를 조회하는 쿼리가 나간다.

- `@JoinColumn(name = "member_id")`:
  DB 테이블의 FK 속성을 매핑할 때 사용
  `order` 테이블에 있는 FK 속성이 실제 이름이 `member_id`라는 것을 명시. JPA는 이 설정을 보고, `Order` 엔티티를 저장하거나 조회할 때 `order` 테이블의 `member_id` 컬럼을 사용

### E-R 다이어그램
![](https://velog.velcdn.com/images/kkangmen/post/649f9249-20a1-4ef6-99ab-a324c3329f3c/image.png)

### H2 database
![](https://velog.velcdn.com/images/kkangmen/post/6870ab7e-2822-4750-97d3-6730a6e462f5/image.png)

### Postman
![](https://velog.velcdn.com/images/kkangmen/post/23255a22-baf1-4f8e-8a51-efa2749cf808/image.png)

![](https://velog.velcdn.com/images/kkangmen/post/18ec69ed-42d0-4147-9aed-001c25b315f8/image.png)

**Member.java**
``` java
package com.example.demo.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity //저장되고 관리되는 데이터임을 명시
@Table(name = "members")
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 자동으로 생성
//public Member(){
//}
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_loginid", length = 20)
    private String loginId;

    @Column(name = "member_pw", length = 100)
    private String password;

    @Column(name = "member_phone", length = 20)
    private String phoneNumber;

    @Column(name = "member_address", length = 100)
    private String address;

    @Column(name = "member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    public void updateInfo(String password, String phoneNumber, String address){
        if (password != null){
            this.password = password;
        }
        if (phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
        if (address != null){
            this.address = address;
        }
    }
}

```
**Item.java**
``` java
package com.example.demo.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity // JPA는 이 클래스의 객체들을 영속성 컨텍스트로 관리하겠다.(Item 테이블로 인식)
@Table(name = "items")
@NoArgsConstructor
public class Item {

    // Item 테이블의 PK를 의미한다.
    @Id
    // PK값은 자동으로 생성된다. 개발자가 객체를 persist하려고 할 때, ID값을 비워두고 INSERT 쿼리를 보낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Integer price;

    @Column(name = "item_quantity")
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

```
**Order.java**
``` java
package com.example.demo.order;

import com.example.demo.item.entity.Item;
import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.CustomLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime dateTime;

    @Column(name = "total_sum")
    private int sum;

    @Column(name = "order_status", length = 20)
    private String status;

    public Order(Member member, LocalDateTime dateTime, int sum, String status) {
        this.member = member;
        this.dateTime = dateTime;
        this.sum = sum;
        this.status = status;
    }
}
```