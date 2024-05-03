package community;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
    void password_null_Input() {
      // given
      String password = null;
      // when
      boolean actual = passWordStrengthMeter.calculate(password);
      //then
      Assertions.assertFalse(actual);
    }

    @Test
    void password_SpecialCharacter_Input() {
      // given
      String password = "\"!@#$asdfsadA1%^&*()\"";
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

