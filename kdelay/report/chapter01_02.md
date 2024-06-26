**문제1. TDD의 주요 목적이 무엇인가?** ⭕
=
답: 5
1) 코드를 최대한 빨리 작성하는 것

2) 새로운 프로젝트의 아키텍처를 설계하는 것

3) 코드의 성능을 최적화하는 것

4) 기능 개발을 용이하게 하기 위해 문서화하는 것

5) 테스트 코드를 작성함으로써 기능 코드의 동작을 보장하는 것 🔘



**문제2. TDD의 기본 순서를 올바르게 배열하십시오.** ❌
=
답: 2 -> 3 -> 4 -> 3 -> 1 `실패 케이스를 보고 성공 케이스를 위해 코드를 작성해야 한다고 생각했음.` 
<br/>
답:: 2 -> 4 -> 3 -> 1
1. 코드를 리팩토링한다.
2. 테스트를 작성한다.
3. 테스트를 실행한다.
4. 코드를 작성한다.



**문제3. TDD를 사용할 때 주의해야 할 점은 무엇인가?** ❌
=
답: 2 <br/>
답:: 3
1. 모든 코드에 대해 테스트를 작성하려고 하면 안 된다.
2. 테스트가 실패하면 즉시 코드를 수정해야 한다. 🔘 `테스트 자체가 실패했는지 확인 필요하다고 생각했었음.`
3. 리팩토링은 선택적인 단계이다. ✔️
4. TDD만으로는 좋은 코드를 작성할 수 없다.



**문제4. 다음은 리팩토링의 특징에 대해서 설명한 것이다. 내용이 올바르지 않은 것은 무엇인가?** ⭕
=
답: 2
1) 리팩토링은 새로운 기능을 추가하는 것은 아니다.

2) 패턴을 적용하게 되면 리팩토링은 할 필요가 없다. 🔘

3) 복잡한 코드일수록 리팩토링을 해야 할 필요가 있다.

4) 유지보수가 용이하려면 리팩토링을 해야만 한다.



**문제5. 어떤 상황에서 TDD가 비효율적일 수 있습니까?** ⭕
=
답: 2
1. 코드의 통합 테스트가 필요할 때
2. 매우 빠른 프로토타이핑이 요구될 때 🔘
3. 소프트웨어의 안정성이 중요할 때
4. 코드 리팩토링이 자주 필요할 때



**문제6. TDD를 적용하기 전에 개발자가 고민해야 하는 주요 사항은 무엇인가요? 자유롭게 생각하고 이야기해봅시다.** 
=
답: 어떤 객체를 사용할지, 메서드명과 파라미터, 반환 값 등의 설정은 어떻게 할 것인지 고민하는 것이 필요하다. <br/>
초기 테스트를 어떤 것으로 먼저 진행을 하는 것이 가장 간단하고 효율적인 테스트 방안일지 생각해야 한다. 

**문제7. 다음 코드를 보고 리팩토링하면 좋은 부분을 생각해봅시다.**
= 
답: products에서 각 값이 "avaliable" 상태이면 total값에 값을 더하는 기능이다. <br/>
주요 비즈니스 로직이기도 하고, 하나의 메서드에 여러 기능이 있기 때문에 따로 메서드로 빼내서 리팩토링을 진행한다. 
```java
public class ShoppingCart {

public double calculateTotalPrice(List<Product> products) {
    double total = 0;
    for (Product product : products) {
        if (product.getStatus().equals("available")) {
            total += product.getPrice();
        }
    }
    return total;
    }
}

public class Product {
    private String status;
    private double price;

// Constructor, getters, setters...
}
```

**문제 8. 테스트 주도 개발(TDD)에서 초기 테스트 코드 작성 시 추천되는 접근법은 무엇입니까?** ⭕
=
답: 2
1) 가장 복잡한 테스트 케이스로 시작한다.
2) 가장 쉬운 테스트 케이스로 시작한다. 🔘
3) 무작위 순서로 테스트 케이스를 선택한다.
4) 모든 테스트 케이스를 동시에 개발한다.

**문제 9. 테스트 주도 개발에서 예외 상황을 먼저 테스트하는 이유는 무엇입니까?** ⭕
=
답: 4
1) 예외 상황이 가장 흔하게 발생하기 때문이다.
2) 예외 상황을 테스트하는 것이 가장 쉬우므로 빠른 성공 경험을 제공한다.
3) 예외 상황을 테스트하는 것이 가장 어렵기 때문에 먼저 처리하는 것이 좋다. 
4) 예외 상황을 먼저 테스트하면 나중에 코드를 전면적으로 수정할 필요가 없어진다. 🔘 




![image](https://github.com/vivalahm/shop/assets/48741014/3a403562-0a0d-4ac9-b046-e7e3c4b2210b)

**문제 10. 위 그림은 TDD 개발 주기를 표현한 것이다. 공백을 채우시오.** ⭕
=

1) Red 단계에서는 `실패` `테스트`를 먼저 작성한다.
2) Green 단계에서는 `테스트`를 `성공` 위한 실제 코드를 작성한다.
3) Yellow 단계에서는 중복 코드 제거, 일반화 등의 `리팩토링`을 수행한다.