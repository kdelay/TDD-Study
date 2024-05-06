package com.study.tdd.chap02;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    public void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }//assertStrength

    @Test
    void meetsAllCriteria_Then_Strong(){
//        PasswordStrength result = meter.meter("ab12!@AB");
//        assertEquals(PasswordStrength.STRONG, result);
//        PasswordStrength result2 = meter.meter("abc1!Add");
//        assertEquals(PasswordStrength.STRONG, result2);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteria_excpt_for_Length_Then_Normal(){
//        PasswordStrength result = meter.meter("ab12!@A");
//        assertEquals(PasswordStrength.NORMAL, result);
//        PasswordStrength result2 = meter.meter("Ab12!c");
//        assertEquals(PasswordStrength.NORMAL, result2);
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
//        PasswordStrength result = meter.meter("ab!@ABqwer");
//        assertEquals(PasswordStrength.NORMAL, result);
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid(){
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInput_Then_Invalid(){
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteria_Then_Weeak(){
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_WEAK(){
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }
}























