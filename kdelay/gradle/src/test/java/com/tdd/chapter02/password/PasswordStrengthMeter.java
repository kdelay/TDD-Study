package com.tdd.chapter02.password;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s.length() < 8) return PasswordStrength.NORMAL;

        boolean containsNum = false; //숫자 포함 여부
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                containsNum = true;
                break;
            }
        }

        if (!containsNum) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }
}
