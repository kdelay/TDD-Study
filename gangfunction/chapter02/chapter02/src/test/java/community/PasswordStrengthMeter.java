package community;

public class PasswordStrengthMeter {

  /**
   *
   * @param password : 입력받은 패스워드
   * @return : 패스워드의 강도를 boolean으로 반환
   */
  boolean calculate(String password){
    if(password == null){
      return false;
    }
    //길이가 8글자 이상인 경우
    if(password.length() >= 8 && containsNumber(password) && containsUppercase(password)
        && !containsSpecialChar(password)){
      return true;
    }
    return false;
  }
  boolean containsNumber(String str) {
    String regex = ".*[0-9].*";
    return str.matches(regex);
  }
  boolean containsUppercase(String str) {
    String regex = ".*[A-Z].*";
    return str.matches(regex);
  }
  boolean containsSpecialChar(String str) {
    String regex = ".*[`~!@#$%^&*()-_=+|[{]};:'\",<.>/?].*";
    return str.matches(regex);
  }

}
