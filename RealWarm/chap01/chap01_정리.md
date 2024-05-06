[1] JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
- JUnit Platform : 테스트를 실행해주는 런처 제공, TestEngine API 정의
- JUnit Jupiter : TestEngine API 구현체로 JUnit5 제공
- JUnit Vintage : JUnit4와 JUnit43을 지원하는 TestEngine 구현체

2.1 JUnit Platform
JVM에서 테스트 프레임워크를 시작하기 위한 런처를 제공한다.
Junit과 클라이언트 간의 인터페이스를 제공한다.
JUnit 플랫폼에서 실행되는 테스트 프레임워크를 개발하기 위한 TestEngine API를 정의한다.

2.2 Junit Jupiter
테스트 작성을 위한 새로운 프로그래밍 모델과 확장 모델 조합이다.
Junit 4 에서 다음 기능이 추가되었다.
@TestFactory – 동적 테스트 지원
@DisplayName – 테스트 클래스, 테스트 메서드에 대해 사용자 정의하는 이름 노출
@Nested – 클래스가 중첩된 비정적 테스트 클래스임을 나타냄
@Tag – 테스트를 필터링하기 위한 태그 선언
@ExtendWith – custom extensions 등록
@BeforeEach – 각각의 테스트 메서드 실행 이전에 매번 실행 (JUnit4: @Before)
@AfterEach – 각각 테스트 메소드 실행 이후에 실행 (JUnit4: @After)
@BeforeAll – 현재 클래스의 모든 테스트 메서드 실행 이전에 한 번만 실행 (JUnit4: @BeforeClass)
@AfterAll –현재 클래스의 모든 테스트 메소드 실행 이후에 한번만 실행 ( (JUnit4: @AfterClass)
@Disable – 테스트 클래스, 테스트 메서드 비활성화 (JUnit4: @Ignore)

2.3 JUnit Vintage
Junit 5에서 Junit 4와 Junit3가 동작할 수 있도록 지원한다.

