package com.tdd.chapter02.password;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCounts = 0;

        //길이 8글자 충족 여부
        if (s.length() >= 8) metCounts++;
        //숫자 포함 여부
        if (meetsContainingNumberCriteria(s)) metCounts++;
        //대문자 포함 여부
        if (meetsContainingUpperCaseCriteria(s)) metCounts++;

        if (metCounts <= 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private static boolean meetsContainingUpperCaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
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
