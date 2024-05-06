package com.tdd.chapter02.password;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        if (s.length() < 8) return PasswordStrength.NORMAL;

        //숫자 포함 여부
        boolean containsNum = meetsContainingNumberCriteria(s);

        if (!containsNum) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
