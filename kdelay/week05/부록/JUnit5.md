# 조건에 따른 테스트
- 특정 운영체제 동작/미동작 처리
  - @EnabledOnOs, @DisabledOs
- 자바 버전
  - @EnabledOnJre, @DisabledOnJre
- 시스템 프로퍼티 값 비교
  - @EnabledIfSystemProperty, @DisabledIfSystemProperty

# 태깅과 필터링
- 태그
  - @Tag
  - maven, gradle에서 실행할 테스트 선택 가능

# 중첩 구성
- 중첩 클래스
  - @Nested
  - 내부 클래스이므로 외부 클래스 멤버에 접근할 수 있다.

# 테스트 메시지
- assertEquals() 메서드의 세 번째 인자로 설명 문자열 추가 가능
- 예시: 몇 번째 요소인지 확인 가능 assertEquals(expected.get(i), ret.get(i), "ret[" + i + "]");

# 임시 폴더
- @TempDir
- File, Path 타입에 적용 가능

# 테스트 실행 시간 검증
- @Timeout
- 일정 시간 내에 테스트 메서드가 실행되는지 검증 가능