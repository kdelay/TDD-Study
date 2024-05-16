## TDD 5장 정리



### JUnit 5 모듈 구성

크게 세가지

1. JUnit 플랫폼: 테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API를 제공
2. JUnit 주피터(Jupitor): JUnit 5를 위한 테스트 API와 실행 엔진을 제공
3. JUnit 빈티지(Vintage): JUnit 3과 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈을 제공

JUnit 5는 테스트를 위한 API로 주피터API를 제공

### @Test 애노테이션과 테스트 메서드

@Test

- 테스트를 실행할 메서드에는 `@Test 애노테이션`을 붙인다
- @Test 애노테이션을 붙인 메서드는 `private`이면 안된다.

JUnit의 Assertions 클래스

- `assertEquals()`메서드와 같이 값을 검증하기 위한 목적의 다양한 정적 메서드를 제공한다.

### 주요 단언 메서드

Assertions 클래스는 `assertEquals()`를 포함해 다양한 단언 메서드를 제공한다.

|                  **메서드**                   |                           **설명**                           |
| :-------------------------------------------: | :----------------------------------------------------------: |
|        assertEquals(expected, actual)         |  실제 값(actual)이 기대하는 값(expected)과 같은지 검사한다.  |
|       assertNotEquals(expected, actual)       | 실제 값(actual)이 기대하는 값(expected)과 같지 않은지 검사한다. |
|  assertSame(Object expected, Object actual)   |             두 객체가 동일한 객체인지 검사한다.              |
| assertNotSame(Object expected, Object actual) |          두 객체가 동일하지 않은 객체인지 검사한다.          |
|         assertTrue(boolean condition)         |                   값이 true인지 검사한다.                    |
|        assertFalse(boolean condition)         |                   값이 false인지 검사한다.                   |
|           assertNull(Object actual)           |                   값이 null인지 검사한다.                    |
|         assertNotNull(Object actual)          |                 값이 null이 아닌지 검사한다.                 |
|                    fail()                     |                   테스트를 실패 처리한다.                    |

Assertions가 제공하는 익셉션 발생 유무 검사 메서드

|                          **메서드**                          |                           **설명**                           |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|  assertThrows(Class<T> expectedType, Excutable executable)   | executalble을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사한다. |
| assertDoesNotThrow(Class<T> expectedType, Excutable executable) | executalble을 실행한 결과로 지정한 타입의 익셉션이 발생하지 않는지 검사한다. |

assert 메서드는 실패하면 다음 코드를 실행하지 않고 바로 익셉션을 발생한다.

```java
assertEquals(3, 5/2); // 검증 실패로 에러 발생
assertEquals(4, 2*2); // 이 코드는 실행되지 않음
```

경우에 따라 일단 모든 검증을 실행하고 그중 실패한 것이 있는지 확인하고 싶을 때, 사용할 수 있는 것이 `assertAll()`메서드이다. 

```java
assertAll(
	() -> assertEquals(3, 5/2),
	() -> assertEquals(4, 2*2),
	() -> assertEquals(6, 11/2)
);
```

- assertAll() 메서드는 Executable 목록을 가변 인자로 전달 받아 각 Executable을 실행한다.
- 실행 결과로 검증에 실패한 코드가 있으면 그 목록을 모아서 에러 메세지로 보여준다.

### 테스트 라이프사이클

@BeforeEach 애노테이션과 @AfterEach 애노테이션

JUnit은 각 테스트 메서드마다 다음 순서대로 코드를 실행한다.

1. 테스트 메서드를 포함한 객체 생성
2. (존재하면) @BeforeEach 애노테이션이 붙은 메서드 실행
3. @Test 애노테이션이 붙은 메서드 실행
4. (존재하면)@AfterEach 애노테이션이 붙은 메서드 실행

```java
package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifecycleTest {
    public LifecycleTest(){
        System.out.println("LifecycleTest constructor");
    }

    @BeforeEach
    void setUp(){
        System.out.println("setUp");
    }

    @Test
    void a(){
        System.out.println("A");
    }

    @Test
    void b(){
        System.out.println("B");
    }

    @AfterEach
    void tearDown(){
        System.out.println("tearDown");
    }
}

```

![image-20240516203636190](/Users/choihyun-min/Library/Application Support/typora-user-images/image-20240516203636190.png)

- @Test 메서드를 실행할 때마다 객체를 새로 생성하고 테스트 메서드를 실행하기 전과 후에 @BeforeEach 애노테이션과 @AfterEach 애노테이션을 붙인 메서드를 실행한다.
- @BeforeEach 애노테이션은 테스트를 실행하는데 필요한 준비 작업을 할 때 사용한다.
  - 테스트에서 사용할 임시 파일을 생성한다거나 테스트 메서드에서 사용할 객체를 생성한다.
- @AfterEach 애노테이션은 테스트를 실행한 후에 정리할 것이 있을 때 사용한다.
  - 테스트에서 사용한 임시 파일을 삭제해야 할 때
- `@BeforeEach 애노테이션`과 `@AfterEach 애노테이션`을 붙인 메서드는 @Test 애노테이션과 마찬가지로 `private`이면 안된다.

----

@BeforeAll 애노테이션과 @AfterAll 애노테이션

- @BeforeAll
  - 한 클래스의 모든 테스트 메서드가 실행되기 전에 특정 작업을 수행해야 할 때 사용하는 애노테이션
  - 정적 메서드에 붙이며, 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행 됨
- @AfterAll
  - 클래스의 모든 테스트 메서드를 실행한 뒤에 실행된다.
  - 정적 메서드에 적용된다.

----

### 테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기

```java
public class BadTest {
    private FileOperator operator = new FileOperator();
    private static File file; // 두 테스트가 데이터를 공유할 목적으로 필드 사용
    
    @Test
    void fileCreationTest() {
        File createdFile = operator.createFile();
        assertTrue(createdFile.length() > 0);
        this.fileCreationTest() = createdFile;
    }

    @Test
    void readFileTest() {
        long data = operator.readData(file);
        assertTrue(data >0);
    }
}
```

- JUnit을 사용한 테스트 코드에서 각 테스트 메서드는 독립적으로 동작해야 하며, 한 테스트의 결과가 다른 테스트에 영향을 미쳐서는 안 된다.

- 이를 위해, 테스트 메서드 간에 필드를 공유하거나 실행 순서를 가정하지 말아야 한다.
- 이 코드는 `file` 필드를 이용해서 `fileCreationTest()` 메서드에서 생성한 File을 저장하고, `readFileTest()` 메서드에서 이를 사용한다. 하지만 각 테스트 메서드는 서로 독립적으로 동작해야 하며, 한 테스트 메서드의 결과가 다른 테스트 메서드의 실행 결과에 영향을 주면 안 된다. 따라서 테스트 메서드가 필드를 공유하거나 실행 순서를 가정해서는 안 된다.

----

### 추가 애노테이션: @DisplayName, @Disabled



 `@DisplayName 애노테이션`

- 자바는 메서드 이름에 공백이나 특수 문자를 사용할 수 없기 때문에 메서드 이름만으로 테스트 내용을 설명하기가 부족할 수 있다. 
- 해당 애노테이션을 사용해서 테스트에 표시 이름을 붙일 수 있다.

```java
@DisplayName("같은 값인지 비교하는 테스트")
@Test
void assertEqualsMethod() {
    ...생략
}
```



`@Disabled 애노테이션`

- 특정 테스트를 실행하지 않고 싶을 때 사용
- JUnit은 `@Disabled 애노테이션`이 붙은 `클래스나 메서드`는 테스트 실행 대상에서 제외한다.
- 아직 테스트 코드가 완성되지 않았거나 잠시 동안 테스트를 실행하지 말아야 할 때 이 애노테이션을 사용한다.

```java
@Disabled
@Test
void failMethod() {
	try{
		AuthService authService = new AuthService();
		authService.authenticate(null,null);
		fail();
	}catch(IllegalArgumentException e){
	}
}
```

----

### 모든 테스트 실행하기

메이븐과 그레이들의 경우 각각 다음 명령어를 사용하여 모든 테스트를 실행한다.

메이븐

- mvnTest (래퍼를 사용하는 경우 mvnw test)

그레이들

- gradle test (래퍼를 사용하는 경우 gradlew test)



혹은 IDE에서 src/test/java 폴더의 오른쪽 마우스를 누르고 run all tests를 클릭해서 실행 시킨다.