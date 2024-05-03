package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()){
            return PasswordStrength.INVALID;
        }
        int meterCount = 0;

        if(s.length() >= 8){
            meterCount++;
        }

        if(meetsContainingUppercaseCriteria(s)){
            meterCount++;
        }

        if(meetsContainingNumberCriteria(s)){
            meterCount++;
        }

        if(meterCount == 1){
            return PasswordStrength.WEEK;
        }

        if(meterCount == 2){
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    boolean meetsContainingNumberCriteria(String s){
        for (char ch : s.toCharArray()) {
            if ("0123456789".contains(String.valueOf(ch))) {
                return true;
            }
        }
        return false;
    }

    boolean meetsContainingUppercaseCriteria(String s){
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }


}
