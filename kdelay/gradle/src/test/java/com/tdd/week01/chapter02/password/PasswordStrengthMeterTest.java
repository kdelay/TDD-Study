package com.tdd.week01.chapter02.password;

import com.tdd.week01.chapter02.password.PasswordStrength;
import com.tdd.week01.chapter02.password.PasswordStrengthMeter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 조건을 충족할 경우 (암호 강도: 강함)")
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8글자 미만인 경우 (암호 강도: 보통)")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않는 경우 (암호 강도: 보통)")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("값이 없는 경우 (null)")
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("값이 없는 경우 (빈 문자열)")
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않는 경우 (암호 강도: 보통")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이만 8글자 이상인 경우 (암호 강도: 약함)")
    void meetsOnlyLengthCriteria_Then_weak() {
        assertStrength("abdefghui", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자만 포함한 경우 (암호 강도: 약함)")
    void meetsOnlyNumCriteria_Then_weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자만 포함한 경우 (암호 강도: 약함)")
    void meetsOnlyUpperCriteria_Then_weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않은 경우 (암호 강도: 약함")
    void meetsNopCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}