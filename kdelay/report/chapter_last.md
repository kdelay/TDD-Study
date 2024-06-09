# 문제

## 1. 과도하게 구현 검증하지 않기

1. 과도하게 구현 검증을 피해야 하는 이유는 무엇인가요?
    - a) 테스트 코드가 더 간결해지기 위해 
    - b) 내부 구현이 변경될 때 테스트가 깨질 가능성을 줄이기 위해 🔘
    - c) 테스트 실행 속도를 높이기 위해 
    - d) 테스트 코드의 가독성을 높이기 위해 


2. 테스트 코드에서 내부 구현 검증 대신 검증해야 할 것은 무엇인가요?
    - a) 메서드 호출 여부
    - b) 클래스의 구조
    - c) 실행 결과 🔘
    - d) 변수의 타입


3. 모의 객체를 사용할 때 주의해야 할 점은 무엇인가요?
    - a) 정확하게 일치하는 값으로 설정하기 
    - b) 내부 구현을 검증하지 않기 🔸
    - c) 가능한 많은 단언을 추가하기
    - d) 모든 메서드를 모의 객체로 대체하기 🔘


4. 과도한 구현 검증이 테스트 코드에 미치는 영향은 무엇인가요?
    - a) 테스트 코드의 유지보수가 쉬워진다
    - b) 테스트 코드의 실행 속도가 빨라진다
    - c) 테스트 코드가 더 자주 깨진다 🔘
    - d) 테스트 코드의 가독성이 높아진다


## 2. 스프링 부트의 내장서버를 이용한 API 테스트


1. 스프링 부트의 내장 서버를 이용한 API 테스트에서 @SpringBootTest 애노테이션의 역할은 무엇인가요?
    - a) 특정 메서드를 모의 객체로 대체한다
    - b) 스프링 애플리케이션 컨텍스트를 로드한다 🔘
    - c) 테스트 메서드를 실행한다
    - d) 데이터베이스 연결을 설정한다


2. 스프링 부트의 내장 서버를 이용한 API 테스트에서 MockMvc를 사용하는 이유는 무엇인가요? ✔️
    - a) 데이터베이스 연결을 설정하기 위해
    - b) HTTP 요청과 응답을 테스트하기 위해 🔸
    - c) 스프링 애플리케이션 컨텍스트를 로드하기 위해
    - d) 테스트 메서드를 실행하기 위해 🔘



3. 스프링 부트의 내장 서버를 이용한 API 테스트에서 MockMvc를 설정하는 방법은 무엇인가요?
    - a) @Autowired 애노테이션을 사용한다
    - b) @MockBean 애노테이션을 사용한다
    - c) @SpringBootTest 애노테이션을 사용한다
    - d) @WebMvcTest 애노테이션을 사용한다 🔘



## 3. 테스트 코드 한 클래스당 두 개 이상을 검증하지 않기



1. 한 테스트 메서드에서 두 개 이상의 검증을 피해야 하는 이유는 무엇인가요?
    - a) 테스트 코드의 길이를 줄이기 위해
    - b) 첫 번째 검증이 실패하면 두 번째 검증 결과를 알 수 없기 때문에 🔘
    - c) 테스트 코드의 실행 속도를 높이기 위해
    - d) 테스트 코드의 가독성을 높이기 위해



2. 한 테스트 메서드에서 서로 다른 내용을 검증할 때의 문제점은 무엇인가요?
    - a) 테스트 코드의 길이가 길어진다
    - b) 테스트 코드의 실행 속도가 느려진다
    - c) 테스트 결과를 확인할 때 집중도가 떨어진다 🔘
    - d) 테스트 코드의 가독성이 낮아진다



## 4. 셋업을 통하여 중복된 상황을 설정하지 않기

1. 셋업 메서드를 이용한 중복 상황 설정의 단점은 무엇인가요?
    - a) 테스트 코드의 길이가 길어진다
    - b) 테스트 코드의 실행 속도가 느려진다
    - c) 초기화 쿼리 파일을 조금만 변경해도 많은 테스트가 깨질 수 있다 🔘
    - d) 테스트 코드의 가독성이 낮아진다

   

2. 셋업 메서드를 사용하지 않고 중복된 상황을 설정하는 방법은 무엇인가요?
    - a) 각 테스트 메서드에서 직접 상황을 구성한다 🔘
    - b) @BeforeEach 애노테이션을 사용한다
    - c) @MockBean 애노테이션을 사용한다
    - d) @SpringBootTest 애노테이션을 사용한다



## 5. 객체 생성을 위한 보조 클래스 생성하기

1. 객체 생성을 위한 보조 클래스를 사용하는 이유는 무엇인가요? ✔️
    - a) 테스트 코드의 길이를 줄이기 위해
    - b) 테스트 코드의 실행 속도를 높이기 위해
    - c) 객체 생성을 더 간편하게 하기 위해 🔸
    - d) 테스트 코드의 가독성을 높이기 위해 🔘



2. 객체 생성을 위한 보조 클래스를 사용하는 방법은 무엇인가요?
    - a) @Autowired 애노테이션을 사용한다
    - b) @MockBean 애노테이션을 사용한다
    - c) 별도의 헬퍼 클래스를 만든다 🔘
    - d) @SpringBootTest 애노테이션을 사용한다



## 6. JUnit 5


1. JUnit 5에서 테스트 메서드의 이름을 지정하는 애노테이션은 무엇인가요?
    - a) @Test
    - b) @BeforeEach
    - c) @AfterEach
    - d) @DisplayName 🔘



2. JUnit 5에서 테스트 클래스 전체를 실행하기 전에 실행되는 메서드를 표시하는 애노테이션은 무엇인가요?
    - a) @Test
    - b) @BeforeAll 🔘
    - c) @AfterAll
    - d) @DisplayName


## 7. JUnit 4


1. JUnit 4에서 테스트 클래스 전체를 실행하기 전에 실행되는 메서드를 표시하는 애노테이션은 무엇인가요?
    - a) @Test
    - b) @BeforeClass 🔘
    - c) @AfterClass
    - d) @Ignore



2. JUnit 4에서 테스트 메서드를 무시하는 애노테이션은 무엇인가요?
    - a) @Test
    - b) @Before
    - c) @After
    - d) @Ignore 🔘



## 8. Mockito


1. Mockito에서 모의 객체를 주입하는 방법은 무엇인가요? ✔️
    - a) @Mock 애노테이션을 사용한다 
    - b) @InjectMocks 애노테이션을 사용한다 🔘
    - c) @Spy 애노테이션을 사용한다
    - d) @Captor 애노테이션을 사용한다



2. Mockito에서 메서드 호출 시 특정 값을 반환하도록 설정하는 방법은 무엇인가요?
    - a) verify() 메서드를 사용한다
    - b) when() 메서드를 사용한다
    - c) thenReturn() 메서드를 사용한다 🔘
    - d) doReturn() 메서드를 사용한다


3. Mockito에서 메서드 호출 시 예외를 던지도록 설정하는 방법은 무엇인가요?
    - a) verify() 메서드를 사용한다
    - b) when() 메서드를 사용한다
    - c) thenThrow() 메서드를 사용한다 🔘
    - d) doThrow() 메서드를 사용한다



## 9. BDD (Behavior-Driven Development)


1. BDD의 주요 목표는 무엇인가요? ✔️
    - a) 테스트 코드의 길이를 줄이기 위해
    - b) 테스트 코드의 실행 속도를 높이기 위해
    - c) 비즈니스 요구사항을 명확하게 표현하기 위해 🔘
    - d) 테스트 코드의 가독성을 높이기 위해



## 10. Classicist vs Mockist

1. Classicist와 Mockist의 주요 차이점은 무엇인가요?
    - a) 테스트 코드의 길이
    - b) 테스트 코드의 실행 속도
    - c) 모의 객체 사용 여부 🔘
    - d) 테스트 코드의 가독성



2. Classicist 접근 방식에서 주로 사용하는 테스트는 무엇인가요? ✔️
    - a) 단위 테스트 
    - b) 통합 테스트 🔘
    - c) 시스템 테스트
    - d) 성능 테스트




3. Mockist 접근 방식에서 주로 사용하는 테스트는 무엇인가요?
    - a) 단위 테스트 🔘
    - b) 통합 테스트
    - c) 시스템 테스트
    - d) 성능 테스트



## 11. Advanced (선택 10문제)

### 1. 과도하게 구현 검증하지 않기

1. 과도한 구현 검증을 피하기 위해 테스트 코드에서 어떤 원칙을 적용해야 하나요?
    - a) 단일 책임 원칙 (Single Responsibility Principle) 🔘
    - b) 개방-폐쇄 원칙 (Open-Closed Principle) 
    - c) 리스코프 치환 원칙 (Liskov Substitution Principle)
    - d) 인터페이스 분리 원칙 (Interface Segregation Principle)

   

2. @SpringBootTest 애노테이션을 사용할 때, 특정 프로파일을 활성화하는 방법은 무엇인가요?
    - a) @ActiveProfiles 애노테이션을 사용한다 🔘
    - b) @Profile 애노테이션을 사용한다 
    - c) @TestProfile 애노테이션을 사용한다
    - d) @SpringProfile 애노테이션을 사용한다




3. MockMvc를 사용하여 비동기 요청을 테스트하는 방법은 무엇인가요? ✔️
    - a) perform() 메서드를 사용한다
    - b) asyncDispatch() 메서드를 사용한다 🔘
    - c) andExpect() 메서드를 사용한다
    - d) andDo() 메서드를 사용한다



4. 한 테스트 메서드에서 두 개 이상의 검증을 피하기 위해 어떤 패턴을 사용하는 것이 좋나요? ✔️
    - a) Page Object 패턴
    - b) Arrange-Act-Assert 패턴 🔸
    - c) Singleton 패턴
    - d) Factory 패턴 🔘



5. 테스트 메서드에서 두 개 이상의 검증을 피하기 위해 어떤 도구를 사용하는 것이 좋나요? ✔️
    - a) JUnit
    - b) Mockito
    - c) AssertJ 🔘
    - d) Hamcrest 



6. 객체 생성을 위한 보조 클래스를 사용할 때, 어떤 패턴을 사용하는 것이 좋나요? ✔️
    - a) Factory 패턴
    - b) Singleton 패턴
    - c) Prototype 패턴
    - d) Builder 패턴 🔘

   

7. JUnit 5에서 테스트 메서드의 실행 순서를 지정하는 애노테이션은 무엇인가요?
    - a) @Order 🔘
    - b) @Test
    - c) @BeforeEach
    - d) @AfterEach



8. JUnit 5에서 조건부 테스트를 실행하는 애노테이션은 무엇인가요? ✔️
    - a) @EnabledIf 🔸
    - b) @DisabledIf
    - c) @ConditionalTest 🔘
    - d) @TestCondition

   

9. JUnit 4에서 테스트 메서드의 실행 순서를 지정하는 애노테이션은 무엇인가요?
    - a) @Order 🔘
    - b) @Test
    - c) @Before
    - d) @After



10. Mockito에서 모의 객체의 메서드 호출 순서를 검증하는 방법은 무엇인가요? ✔️
    - a) InOrder 객체를 사용한다 🔘
    - b) verify() 메서드를 사용한다
    - c) when() 메서드를 사용한다
    - d) thenReturn() 메서드를 사용한다