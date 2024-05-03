package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    @Test
    @DisplayName("모든 조건을 만족하면 암호 강도는 강함이어야 한다.")
    void meetsAllCriteria_Then_Strong(){
        //test code
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = passwordStrengthMeter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ab!@ABcd");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    @DisplayName("값이 없는 경우")
    void nullInput_Then_Invalid(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter(null);
        assertEquals(PasswordStrength.INVALID, result);
        PasswordStrength result2 = passwordStrengthMeter.meter("");
        assertEquals(PasswordStrength.INVALID, result2);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ab12!@cd");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyLengthCriteria_Then_Weak(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("abdefghi");
        assertEquals(PasswordStrength.WEEK, result);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyNumCriteria_Then_Weak(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("12345");
        assertEquals(PasswordStrength.WEEK, result);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyUpperCriteria_Then_Week(){
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ABZEF");
        assertEquals(PasswordStrength.WEEK, result);
    }

}

