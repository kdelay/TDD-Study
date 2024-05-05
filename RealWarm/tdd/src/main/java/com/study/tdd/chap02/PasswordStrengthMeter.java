package com.study.tdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCriteriaCounts(s);

        /*if(lengthEnough && !containsNum && !containsUpp){
            return  PasswordStrength.WEAK;
        }
        if(!lengthEnough && containsNum && !containsUpp){
            return  PasswordStrength.WEAK;
        }
        if(!lengthEnough && !containsNum && containsUpp){
            return  PasswordStrength.WEAK;
        }*/
        if (metCounts <= 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;

    }//meter

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if (s.length() >= 8) metCounts++;
        if (meetsContainingNumberCriteria(s)) metCounts++;
        if (meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
    }//getMetCriteriaCounts

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }//meetsContainingUppercaseCriteria

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= 9) {
                return true;
            }
        }
        return false;
    }//meetsContainingNumberCriteria


}
