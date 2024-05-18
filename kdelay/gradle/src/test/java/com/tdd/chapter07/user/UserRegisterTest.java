package com.tdd.chapter07.user;

import com.tdd.chapter07.user.exception.WeakPasswordException;
import com.tdd.chapter07.user.validation.StubWeakPasswordChecker;
import com.tdd.chapter07.user.service.UserRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    private UserRegister userRegister;
    //약한 비밀번호인지 확인하는 stub 대역
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        //암호가 약하다고 응답하도록 설정
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class,
                () -> userRegister.register("id", "pw", "email"));
    }
}
