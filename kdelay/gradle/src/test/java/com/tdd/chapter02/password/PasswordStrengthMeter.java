package com.tdd.chapter02.password;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        //길이 8글자 충족 여부
        boolean lengthEnough = s.length() >= 8;
        //숫자 포함 여부
        boolean containsNum = meetsContainingNumberCriteria(s);
        //대문자 포함 여부
        boolean containsUpp = meetsContainingUpperCaseCriteria(s);

        if (lengthEnough && !containsNum && !containsUpp) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && containsNum && !containsUpp) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && !containsNum && containsUpp) {
            return PasswordStrength.WEAK;
        }

        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containsNum) return PasswordStrength.NORMAL;
        if (!containsUpp) return PasswordStrength.NORMAL;

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
