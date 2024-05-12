# 🔶 교재 내 문제 출제
## 1️⃣ 챕터 2에서, '암호 강도 측정 기능'을 TDD로 구현했다. <br/> 가장 처음으로 작성이 필요한 테스트를 고르시오. ⭕
<br>
답: 2

1. 값이 없는 암호인 경우
2. 모든 규칙을 충족하는 경우 🔘
3. 숫자를 포함하지 않고 나머지 규칙은 충족하는 경우
4. 길이가 8글자 이상인 규칙만 충족하는 경우

## 2️⃣ 테스트 작성 시 올바른 경우를 고르시오. (답 2개) ⭕
답: 1, 3

1. 쉬운 경우에서 어려운 경우로 진행 🔘
2. 어려운 경우에서 쉬운 경우로 진행
3. 예외적인 경우에서 정상인 경우로 진행 🔘
4. 정상인 경우에서 예외적인 경우로 진행

## 3️⃣ 챕터 3에서, '매달 비용을 지불해야만 이용할 수 있는 유료 서비스'에 대해 <br/> 테스트 및 구현을 진행했다. <br/> 요구사항에 따른 빈칸을 작성하시오. ⭕

```
[요구사항]
- 10개월 요금(10만원)을 납부하는 경우 1년(12개월)을 제공해준다.

[힌트]
- addedMonths는 추가되는 달(month)의 수를 말한다.
- 코드는 3항 연산자로 이루어져 있다.
- 납입하는 금액은 `payData.getPayAmount()`로 값을 받을 수 있다.
```
답: payData.getPayAmount() == 100_000 ? 12

```java
public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = ______________ : payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }
    //...{생략}
}
```

---

# 🔶 Chapeter 3, 4 실습 문제
챕터 3와 챕터 4를 유기적으로 연결해서 실습예제를 만들어봤습니다.

## 상황
개발자 A군은 현재 사용하고있었던 결제시스템에서 이상이 생겨 팀 내부의 회의를 통해 새로운 결제시스템을 솔루션 B 회사에서 차용하기로 결정했습니다.
사수 B군은 새로운 결제시스템을 적용하기위해 기존의 시스템의 코드를 분석하고 A군에게 달성해야할 테스트 목록을 할당했습니다.
## 요구사항
- 만료일 계산: 기본 납부일로부터 기간은 30일이되는 만료일을 계산할 수 있어야합니다.
- 만료일 연장: 기본 납부금액(10,000)원 을 기간내에 납부하면 만료일이 1달 연장됩니다.
- 연속 납부: 기본 납부금액을 7일이내에 2회이상 납부하면 기본 납부금액 대비 20% 할인됩니다.
- 세금 계산: 세금은 별도로 납부금액 대비 10% 가산됩니다.
- 만료일의 영업일 포함: 만료일은 영업일에 포함되어야하며, 영업일은 공휴일 및 주말을 제외합니다.
- 결제 수단: 결제수단은 현금과 카드 두가지가 있고, 기존 고객의 납부 설정은 현금으로 되어있습니다.

## 문제
### 코드
현재 주어진 코드는 하기와 같으며, 하기의 코드는 변경이 불가능한 상태에서 개발을 진행해야합니다.
```java
import java.time.LocalDateTime;

public enum PaymentMethod {
  CASH, CREDIT_CARD
}
@Getter
public class Billing {

  private double total;
  private double tax;
  private LocalDateTime date;
  private PaymentMethod paymentMethod;

  public Billing(double total, double tax, LocalDateTime date, PaymentMethod paymentMethod) {
    this.total = total;
    this.tax = tax;
    this.date = date;
    this.paymentMethod = paymentMethod;
  }

}
public class BillingUtils{
  // 납부를 처리하는 로직
}

```

### 객관식 (4문제)
#### 1️⃣ 다음과 같은 상황에서 제일 먼저 진행해야할 테스트는 무엇일까요? ⭕
답: 1

1. 정상적인 납부후의 만료일 계산 테스트 🔘
2. 결제수단의 변경 테스트
3. 세금 계산 테스트
4. 연속 납부 할인율 테스트


#### 2️⃣ 만료일 영업일 포함을 위한 테스트를 진행하기 앞서 구체화해야하는 항목은 무엇일까요? ⭕
답: 4

1. 영업일의 정의
2. 공휴일의 정의
3. 주말의 정의
4. 만료일의 정의 🔘


#### 3️⃣ 연속 납부의 경우 할인율을 변경하고 싶다면 어떤 테스트를 먼저 해야할까요? ⭕
답: 1

1. 납부일로부터 7일이내에 2회이상 납부하는 테스트 🔘
2. 할인율을 변경하는 테스트
3. 할인율의 변경을 위한 조건을 검증하는 테스트

#### 4️⃣ 카드결제가 거부될 경우 어떤 테스트를 먼저 진행해야할까요?
답: 3

1. 사용자로 부터 카드정보를 입력받는 테스트
2. 사용자가 결제실패시 현금결제로 변경하는 테스트
3. 카드결제 실패시 카드사와 연결된 api의 테스트 🔘
4. 사용자의 카드정보가 정확한지 검증하는 테스트


### 주관식 (3문제)
#### 5️⃣ 해외에 거주하는 고객이 한국의 공휴일을 고려하여 만료일을 계산하려면 어떤 필드를 확인해야할까요?
답: date 필드를 확인해야 한다. ZoneId를 통해 한국으로 설정한다.   
🔶 LocalDateTime의 timezone을 확인한다.

#### 6️⃣ 만료일 연장 기능을 테스트하기 위해 고려해야 할 주요 요소는 무엇인가요?
답: 만료일. 만료일이 되기 전 기간 내에 납부해야 1달 연장된다.   
🔶 납부일로부터 만료일까지의 계산 로직, 납부 금액의 정확한 처리, 연장된 만료일이 영업일에 포함되는지 확인해야 한다.

#### 7️⃣ 세금 계산 기능을 테스트할 때 어떤 변수들을 고려해야 하나요?
답: 연속 납부로 인해 기본 납부 금액이 20% 할인된 금액일 경우, 세금 계산 시 최종 할인된 금액에 따라 10% 가산해야 한다.   
🔶 납부 금액에 대한 세금이 10%로 정확히 계산되는지 확인한다. 세금이 올바르게 추가되었는지 테스트하고, 세금 계산 후의 총 금액이 시스템에 정확히 반영되었는지도 확인해야 한다.

### 프로그래밍 (2문제)
#### 8️⃣ 만료일을 계산하는 기능을 구현해보세요.
```java
@Test
public void calculateExpirationDate() {
  // 구현해보세요.
}
public class BillingUtilsTest {
  public static LocalDateTime calculateExpiryDate(LocalDateTime startDate) {
    // 구현해보세요.
  }
}
```

#### 9️⃣ 만료일 연장 기능을 구현해보세요.
```java
@Test
public void extendExpirationDate() {
  // 구현해보세요.
}
public class BillingUtilsTest {
  public static LocalDateTime extendExpiryDate(LocalDateTime currentExpiryDate) {
    // 구현해보세요.
  }
}
```

```java
package com.tdd.report;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class reportTest {

    private static final BillingUtils billingUtils = new BillingUtils();

    @Test
    @DisplayName("만료일 계산 기능 테스트")
    void calculateExpirationDate() {
        assertExpiredDateCal(
                LocalDateTime.of(2024, 5, 12, 0, 0),
                LocalDateTime.of(2024, 6, 11, 0, 0)
        );
        assertExpiredDateCal(
                LocalDateTime.of(2024, 5, 31, 0, 0),
                LocalDateTime.of(2024, 6, 30, 0, 0)
        );
    }

    private static void assertExpiredDateCal(LocalDateTime startDate, LocalDateTime expiredDate) {
        assertThat(billingUtils.calculateExpiryDate(startDate)).isEqualTo(expiredDate);
    }

    @Test
    @DisplayName("만료일 연장 기능 테스트")
    public void extendExpirationDate() {
        assertExpiredDateExtend(
                LocalDateTime.of(2024, 5, 12, 0, 0),
                LocalDateTime.of(2024, 7, 11, 0, 0),
                10_000
        );
        assertExpiredDateExtend(
                LocalDateTime.of(2024, 5, 31, 0, 0),
                LocalDateTime.of(2024, 7, 30, 0, 0),
                10_000
        );
    }

    private static void assertExpiredDateExtend(LocalDateTime startDate, LocalDateTime expiredDate, int price) {
        LocalDateTime expectedDate = null;

        if (price == 10_000) {
            expectedDate = billingUtils.extendExpiryDate(
                    billingUtils.calculateExpiryDate(startDate)
            );
        }

        assertThat(expectedDate).isEqualTo(expiredDate);
    }
}

```