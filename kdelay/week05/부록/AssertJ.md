기대값과 결과값을 알 수 있고, 실패한 원인을 JUnit보다 정확하게 알 수 있다.

# 기본 검증 메서드
- isEqualTo(값)
- isNotEqualTo(값)
- isNull()
- isNotNull()
- isIn(값 목록)
  - 가변 인자, List
- isNotIn(값 목록)
- isLessThan(값)

# Comparable 인터페이스를 구현한 타입이나 숫자 타입인 경우
- isLessThan(값)
- isLessThanOrEqualTo(값)
- isGreaterThan(값)
- isGreaterThanOrEqualTo(값)
- isBetween(값1, 값2)

# boolean, Boolean 타입 검증 메서드
- isTrue()
- isFalse()

# String 검증 메서드
- contains(CharSequence... values)
- containsOnlyOnce(CharSequence sequence)
- containsOnlyDigits(): 숫자만 포함하는지 검증
- containsWhitespaces()
- containsOnlyWhitespaces()
- containsPattern(CharSequence regex): 정규 표현식에 일치하는 문자 포함 검증
- containsPattern(Pattern pattern): 위와 동일
- doexNotContain(CharSequence... values)
- doesNotContainAnyWhitespaces()
- doesNotContainOnlyWhitespaces()
- doesNotContainPattern(CharSequence pattern)
- startsWith(CharSequence prefix)
- doesNotStartWith(CharSequence prefix)
- endsWith(CharSequence suffix)
- doesNotEndWith(CharSequence suffix)

# 숫자 검증 메서드
- isZero()
- isNotZero()
- isOne()
- isPositive(): 양수인지 검증
- isNotPositive()
- isNegative(): 음수인지 검증
- isNotNegative()

# 날짜/시간 검증 메서드
LocalDateTime, LocalDate, Date...
- isBefore(비교할 값)
- isBeforeOrEqualTo(비교할 값)
- isAfter(비교할 값)
- isAfterOrEqualTo(비교할 값)

LocalDateTime, OffsetDateTime, ZonedDateTime
- isEqualToIgnoringNanos(비교할 값)
- isEqualToIgnoringSeconds(비교할 값)
- isEqualToIgnoringMinutes(비교할 값)
- isEqualToIgnoringHours(비교할 값)

# 콜렉션 검증 메서드
List, Set...
- hasSize(int expected): 크기가 지정한 값과 같은지 검증
- contains(E... values)
- containsOnly(E... values)
- containsAnyOf(E... values): 지정한 값 중 일부를 포함하는지 검증
- containsOnlyOnce(E... values)

Map
- containsKey(K key)
- containsKeys(K... keys)
- containsOnlyKeys(K... keys)
- doesNotContainsKeys(K... keys)
- containsValues(VALUE... values)
- contains(Entry<K, V>... values)

# Exception 관련 검증 메서드
- assertThatThrownBy()
  - .isInstanceOf(): exception 타입 추가 검증
- -> assertThatExceptionOfType()
  - .isThrownBy(): exception 발생 코드 블록 지정

# SoftAssertions로 모아서 검증
- JUnit5의 assertAll() 과 유사하다.
```text
SoftAssertions soft = new SoftAssertions();
...
soft.assertAll();

-> SoftAssertions.assertSoftly(soft -> {...});
```

# as()와 describedAs()로 설명 추가
- assertThat(id).as("ID 검사").isEqualTo("abc");
  - org.opentest4j.AssertionFailedError: [ID 검사]
  - %s 인자 포맷팅 가능
- describedAs(): 몇 번째 값이 실패한지 알 수 있다.