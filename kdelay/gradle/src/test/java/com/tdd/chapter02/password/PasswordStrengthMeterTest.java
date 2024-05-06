package com.tdd.chapter02.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {

    @Test
    @DisplayName("모든 조건을 충족할 경우 (암호 강도: 강함)")
    void meetsAllCriteria_Then_Strong() {
        //given
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        //when
        PasswordStrength result = meter.meter("ab12!@AB");
        PasswordStrength result2 = meter.meter("abc1!Add");

        //then
        assertEquals(PasswordStrength.STRONG, result);
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    @DisplayName("길이가 8글자 미만인 경우 (암호 강도: 보통)")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        //given
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        //when
        PasswordStrength result = meter.meter("ab12!@A");
        PasswordStrength result2 = meter.meter("Ab12!c");

        //then
        assertEquals(PasswordStrength.NORMAL, result);
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @Test
    @DisplayName("숫자를 포함하지 않는 경우 (암호 강도: 보통)")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        //given
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        //when
        PasswordStrength result = meter.meter("ab!@ABqwer");

        //then
        assertEquals(PasswordStrength.NORMAL, result);
    }
}