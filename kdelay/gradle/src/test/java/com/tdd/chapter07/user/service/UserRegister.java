package com.tdd.chapter07.user.service;

import com.tdd.chapter07.user.exception.WeakPasswordException;
import com.tdd.chapter07.user.validation.WeakPasswordChecker;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String pw, String email) {
        //약한 암호일 경우 exception 발생
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
    }
}
