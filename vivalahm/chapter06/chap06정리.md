## 테스트 코드의 구성

### 기능에서의 상황

기능은 주어진 상황에 따라 다르게 동작한다.

기능 예시

- 파일에서 숫자를 읽어와 숫자의 합을 구한다.
- 한 줄마다 한 개의 숫자를 포함한다.

#### 이 기능을 구현하려면 고려할 것

- 파일이 없는 상황을 처리해야 한다.
- 데이터를 읽을 파일이 없다면 인자가 잘못되었다는 익셉션을 발생한다.
- 문제 상황을 알려줄 수 있는 값을 리턴해야 한다.
- 숫자가 아닌 잘못된 데이터가 존재하는 경우에도 알맞은 결과를 생성해야 한다.

### 테스트 코드의 구성 요소: 상황, 실행, 결과 확인

- 기능은 `상황`에 따라 결과가 달라진다.
- 테스트 코드는 `기능을 실행`하고 그 `결과를 확인`
- 즉, `상황`, `실행`, `결과 확인`의 세 가지 요소로 테스트를 구성 할 수 있다.
  - 영어 표현으로 `given`, `when`, `then` 에 대응한다.

```java
@Test
void exactMatch(){
	//given (정답이 456인 상황)
	BaseballGame game = new BaseballGame("456");
	//when (실행)
	Score score = game.guess("456");
	//then (결과 확인)
	assertEquals(3,score.strikes());
	assertEquals(0,score.balls());
}

@Test
void noMatch(){
	//given (정답이 123인 상황)
	BaseballGame game = new BaseballGame("123");
	//when (실행)
	Score score = game.guess("456");
	//then (결과 확인)
	assertEquals(0,score.strikes());
	assertEquals(0,score.balls());
}
```

- 각 테스트 메서드마다 객체를 생성해서 상황을 설정할 수 있다.

@BeforeEach를 적용한 메서드에서 상황을 설정하는 방법도 있다.

```java
Private BaseballGame game;

@BeforeEach
void givenGame(){
	//given (정답이 456인 상황)
	game = new BaseballGame("456")
}

@Test
void exactMatch(){
	//when (실행)
	Score score = game.guess("456");
	//then (결과 확인)
	assertEquals(3,score.strikes());
	assertEquals(0,score.balls());
}

```

-----

### 상황이 없는 경우

2장의 암호 강도 측정 예가 이에 해당

암호 강도 측정의 경우 결과에 영향을 주는 상황이 존재하지 않으므로 테스트는 기능을 실행하고 결과를 확인하는 코드만 포함 한다.

```java
@Test
void meetsAllCriteria_Then_Strong() {
  	//when
  	PasswordStrengthMeter meter = new PasswordStrengthMeter();
    PasswordStrength result = meter.meter("ab12!@AB");
  	//then
  	assertEquals(PasswordStrength.STRONG, result);
}
```

----

### 실행 결과가 항상 리턴 값으로 존재하는 것은 아니다.

```java
@Test
void getnGame_With_DupNumber_Then_fail() {
		assertThrows(IllegalArgumentException.class, () -> new BaseballGame("100"));
}
```

실행 결과로 익셉션을 발생하는 것이 정상인 경우도 있다.

이 경우 게임 생성 실패 결과를 표시하기 위해 BaseBallGame 생성자가 IllegalArgumentException을 발생시키도록 구현할 수 있다.

------

## 외부 사황과 외부 결과

상황 설정이 테스트 대상으로 국한 되는 것은 아니다. 상황에는 외부 요인도 있다.

- 예를 들어 앞선 파일에서 숫자를 읽어 들일 때 파일이 존재하지 않는 상황에서의 결과도 확인해야한다.
- 파일이 존재하지 않는 상황을 만드려면, 가장 쉬운 방법은 존재 하지 않는 파일을 경로를 사용하는 것이다.

```java
@Test
void noDataFile_Then_Exception() {
    givenNoFile("badpath.txt");

    File dataFile = new File("badpath.txt");
    assertThrows(IllegalArgumentException.class,
            () -> MathUtils.sum(dataFile)
    );
}

private void givenNoFile(String path) {
    File file = new File(path);
    if (file.exists()) {
        boolean deleted = file.delete();
        if (!deleted)
            throw new RuntimeException("fail givenNoFile: " + path);
    }
}
```

givenNoFile() 메서드는 해당 경로에 파일이 존재하는지 검사해서 존재할 경우 해당 파일을 삭제한다.

- 반대로 파일을 존재하는 상황은 어떻게 만들까? 
- 쉬운 방법은 상황에 알맞은 파일을 미리 만들어 두는 것이다.
- 예를 들어 다음 데이터를 갖는 "datafile.txt" 파일을 src/test/resources 폴더에 미리 만드는 것

```java
@Test
void dataFileSumTest() {
    File dataFile = new File("src/test/resources/datafile.txt");
    long sum = MathUtils.sum(dataFile);
    assertEquals(10L,sum);
}
```

파일을 미리 만들지 않고 테스트 코드에서 상황에 맞는 파일을 생성하는 방법도 있다.

```java
@Test
void dataFileSumTest2() {
    givenDataFile("target/datafile.txt", "1", "2", "3", "4");
    File dataFile = new File("src/test/resources/datafile.txt");
    long sum = MathUtils.sum(dataFile);
    assertEquals(10L, sum);
}

private void givenDataFile(String path, String... lines) {
    try {
        Path dataPath = Paths.get(path);
        if (Files.exists(dataPath)) {
            Files.delete(dataPath);
        }
        Files.write(dataPath, Arrays.asList(lines));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

----

### 외부상태가 테스트 결과에 영향을 주지 않게 하기

- 테스트 코드는 한 번만 실행하고 끝나지 않는다.
- TDD를 진행하는 동안에도 계속 실행하고 개발이 끝난 후에도 반복적으로 테스트를 실행해서 문제가 없는지 검증한다.
- 테스트는 언제 실행해도 항상 정상적으로 동작하는 것이 중요하다.

회원 가입 기능을 예로 들면 테스트에는 다음을 포함한다.

- 중복된 ID가 이미 존재하면 가입 실패

- 모든 조건을 충족하면 가입 성공

  ```java
  @Test
  void dupIdTest(){
  	RegistReq req = new RegistReq("test01","테스터01");
  	assertThrows(DuplicatedIdException.Class, ()-> registerService.register(req));
  }
  
  @Test
  void registerSuccessfully(){
  		RegistReq req = new RegistReq("tester","테스터");
  		registerService.register(req);
  		Member mem = memberRepo.findById("tester");
  		assertEquals("테스터",mem.getName());
  }
  ```

  - dupIdTest() 테스트를 검증하려면 DB의 회원 테이블에 아이디가 "test01"인 데이터를 미리 추가해야 한다.
  - 아이디가 "tester"인 데이터가 없는 상태에서 registerSuccessfully() 테스트를 실행해 통과했다고 가정하면 테스트 성공후 회원 테이블에 "tester"인 데이터가 생성된다.
    - 다시 registerSuccessfully()테스트를 실행하면 아이디 중복으로 테스트가 실패한다.
    - 즉, DB 데이터의 상태에 따라 테스트가 성공하기도 하고 실패하기도 한는 것
  - 외부상태에 따라 성공 여부가 바뀌지 않으려면 테스트 실행 전에 외부를 원하는 상태로 만들거나 테스트 실행 후에 외부 상태를 원래대로 돌려둬야 한다.
    - 테스트 실행 후 회원 데이터를 삭제하는 로직을 실행하던가 트랜잭션을 롤백하는 방법이 있다.

  ----

### 외부 상태와 테스트 어려움

상황과 결과에 영향을 주는 외부 요인은 파일, DBMS, 외부 서버 등 다양하다.

이런 외부 환경 요인을 테스트에 맞게 구성하는 것이 항상 가능한 것이 아니다.

- 예시 - 자동이체 등록 기능
  - 입력 받은 계좌 번호가 올바른지 확인해야 함
    - 이를 위해 제공하는 REST API를 사용한다면 자동이체 등록 기능에 대한 테스트는 다음 상황에서의 결과를 확인 할 수 있어야 한다.
      1. REST API 응답 결과가 유효한 계좌 번호인 상황
      2. REST API 응답 결과가 유효하지 않은 계좌 번호인 상황
      3. REST API 서버에 연결할 수 없는 상황
      4. REST API 서버에서 응답을 5초 이내에 받지 못하는 상황
    - 1번의 경우 API 제공 업체에서 정보를 받아 테스트해 볼 수 있다.
    - 3번의 경우는 외부 업체에 요청하기 힘들다.

실행 결과가 외부 시스템에 기록되는 경우도 있다

외부 택배사에 배송 정보를 전달하기 위해 택배사가 제공한 DB 테이블을 사용한 사례가 있다.

-  주문이 들어오면 택배사가 제공한 DB 테이블에 필요한 데이터를 추가하는 방식으로 배송 정보를 전달했다.
-  동일한 테스트를 여러 번 수행할 수 있으려면 택배사가 제공한 DB 테이블에서 데이터를 삭제할 수 있어야 하지만,택배사는 해당 테이블에 대해 INSERT와 SELECT 권한만 주고 DELETE 권한은 주지 않았다.

#### 이처럼 테스트 대상이 아닌 외부 요인은 테스트 코드에서 다루기 힘든 존재이다 

테스트 대상의 `상황`과 `결과`에 외부 요인이 관여할 경우 `대역`을 사용하면 테스트 작성이 쉬워진다.
`대역`은 테스트 대상이 의존하는 대상의 실제 구현을 대신하는 구현인데 이 `대역을 통해서 외부 상황이나 결과를 대체할 수 있다.` 