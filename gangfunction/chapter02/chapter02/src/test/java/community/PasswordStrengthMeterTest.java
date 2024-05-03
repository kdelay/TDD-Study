package community;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 1. Objectives(교재)
 * - 값이 없는 경우
 * 2.Objectives(개인)
 * - 특수문자가 들어오는 경우
 * - 인코딩에 오류가 있는경우
 * - 복사 붙여넣기로 입력하는 경우
 * - 스페이스바가 입력되는 경우
 */
@DisplayName("암호 검사기 테스트")
public class PasswordStrengthMeterTest {

  PasswordStrengthMeter passWordStrengthMeter;

  @BeforeEach
  void setUp() {
    passWordStrengthMeter = new PasswordStrengthMeter();
  }

  @Nested
  @DisplayName("{Red}실패할 경우")
  class RedCases {

    @Test
    void not_moreThan8digits_numberIncludes_upperCaseIncludes() {
      // given
      String password = "abcdefgh";
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);
    }

    @Test
    void only_not_moreThan8digits() {
      // given
      String password = "1abcdAe";
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);

    }

    @Test
    void only_not_numberIncludes() {
      // given
      String password = "abcdefghA";
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);
    }

    @Test
    void only_not_upperCaseIncludes() {
      // given
      String password = "abcdefgh1";
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);
    }
    @Test
    void password_null_Input(){
      // given
      String password = null;
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);
    }
  }

    @Nested
    @DisplayName("{Green}성공할 경우")
    class GreenCases {

      @Test
      void moreThan8digits_numberIncludes_upperCaseIncludes() {
        // given
        String password = "abcdefgh1A";
        // when
        boolean actual = passWordStrengthMeter.calculate(password);
        //then
        Assertions.assertTrue(actual);
      }
  }
}

