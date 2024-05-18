Q1. `@Test` 애노테이션을 붙인 메서드에 대해 옳지 않은 것은? ⭕ 

답: 1
1. 메서드는 `private`일 수 있다. 🔘
2. 메서드는 테스트를 실행할 수 있다.
3. 메서드는 JUnit 5의 테스트 메서드이다.
4. `assertEquals()` 메서드를 사용하여 값을 검증할 수 있다.

Q2. Assertions 클래스에서 제공하는 메서드가 아닌 것은? ⭕

답: 5
1. assertEquals()
2. assertNotEquals()
3. assertSame() 
4. assertFalse() 
5. assertContains() 🔘

Q3. JUnit에서 테스트 메서드 실행 순서를 바르게 나열하세요. ⭕

답: 2 - 3 - 1 - 4
1. @Test 애노테이션이 붙은 메서드 실행
2. 객체 생성
3. @BeforeEach 애노테이션이 붙은 메서드 실행
4. @AfterEach 애노테이션이 붙은 메서드 실행

Q4. 테스트 코드에서 `@Disabled` 애노테이션을 사용하는 이유로 가장 적절한 것은? ⭕ 

답: 2
1. 특정 테스트 메서드를 항상 실행하기 위해
2. 특정 테스트 메서드를 실행하지 않기 위해 🔘
3. 테스트 메서드의 실행 순서를 변경하기 위해
4. 테스트 메서드의 실행 결과를 확인하기 위해

Q5. 외부 요인이 테스트 대상의 결과에 영향을 주는 경우 가장 적절한 해결책은? ⭕

답: 3
1. 외부 요인을 무시한다.
2. 외부 요인을 테스트 코드에서 직접 다룬다.
3. 외부 요인의 실제 구현을 대체하는 대역을 사용한다. 🔘
4. 외부 요인을 테스트 코드에 포함하지 않는다.

Q6. 다음 중 테스트 코드에서 대역을 사용해야 하는 이유로 적절하지 않은 것은? ⭕

답: 4
1. 외부 요인에 의존하는 테스트 대상이 있을 때 테스트 작성을 쉽게 하기 위해
2. 외부 요인이 테스트 결과를 예측할 수 없게 만들기 때문에
3. 실제 구현을 작성하지 않고도 테스트를 완성하기 위해 
4. 코드의 전체 성능을 크게 향상시키기 위해 🔘

## 주관식 문제

Q7. 대역을 사용하는 주된 이유는 무엇인가요? 두 가지 이상 서술하세요.
1. 외부 요인에 따라 테스트 성공 유무가 달라질 수 있고, 외부 요인에 따른 테스트 데이터의 의존성을 약하게 하기 위해 대역이 필요하다. 
2. 구현 코드를 작성하지 않고도 대역을 설정할 수 있기 때문에 확장성이 용이하다.

Q8. 대역의 종류 중 `스파이(Spy)`와 `모의(Mock)`의 차이점을 설명하세요.
- 스파이: 호출된 데이터를 가지고 데이터를 검증한다.
- 모의: Mockito와 같은 라이브러리를 사용하고 스텁, 스파이 역할을 한다.

Q9. 모의 객체(Mock)를 과하게 사용하는 것의 단점은 무엇인가요? 예를 들어 설명하세요.
- 테스트 코드가 불가피하게 복잡해지고 길어질 수 있다. 예시로 DB에 접근하지 않고 Memory로 fake 대역을 설정함으로써 DB와의 외부 요인 의존성을 줄일 수 있다. 

## 코드 문제

Q10. `대역`을 사용하여 외부 의존성을 제거하고 테스트를 작성해보세요.
아래의 코드는 사용자 서비스(UserService)를 테스트하는 예제입니다. 이 서비스는 외부 이메일 검증 서비스(EmailValidatorService)를 호출하여 이메일의 유효성을 확인하고, 데이터베이스(UserRepository)에 사용자를 저장합니다. 이 외부 의존성을 대역으로 교체하여 테스트를 작성하세요.

### 제공되는 코드

-----

### EmailValidatorService

```java
public class EmailValidatorService {
    public boolean isValid(String email) {
        // 실제 외부 서비스 호출 코드
        // 예시: return ExternalEmailValidator.validate(email);
        return true; // 실제 구현은 외부 서비스를 호출함
    }
}
```

### User

```java
public class User {
    private String id;
    private String email;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
```

### UserRepository

```java
public interface UserRepository {
    void save(User user);
    User findById(String id);
}
```

### UserService

```java
public class UserService {
    private EmailValidatorService emailValidatorService;
    private UserRepository userRepository;

    public UserService(EmailValidatorService emailValidatorService, UserRepository userRepository) {
        this.emailValidatorService = emailValidatorService;
        this.userRepository = userRepository;
    }

    public boolean registerUser(String id, String email) {
        if (!emailValidatorService.isValid(email)) {
            return false;
        }

        User user = new User(id, email);
        userRepository.save(user);
        return true;
    }
}
```

-----

### 문제

1. `EmailValidatorService`의 대역을 작성하세요.
2. `UserRepository`의 대역을 작성하세요.
3. `UserService`를 테스트하는 코드를 작성하세요. `EmailValidatorService`와 `UserRepository`의 대역을 사용하여 외부 의존성을 제거한 테스트를 작성하세요.

