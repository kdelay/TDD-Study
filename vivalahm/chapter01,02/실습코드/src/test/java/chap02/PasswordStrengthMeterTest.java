package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 조건을 만족하면 암호 강도는 강함이어야 한다.")
    void meetsAllCriteria_Then_Strong(){
        //test code
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("값이 없는 경우")
    void nullInput_Then_Invalid(){
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야 한다.")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@ef", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi", PasswordStrength.WEEK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("12345", PasswordStrength.WEEK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하면 암호 강도는 약함")
    void meetsOnlyUpperCriteria_Then_Week(){
        assertStrength("ABZEF", PasswordStrength.WEEK);
    }

}

