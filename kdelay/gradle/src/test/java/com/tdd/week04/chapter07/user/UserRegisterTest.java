package com.tdd.week04.chapter07.user;

import com.tdd.week04.chapter07.user.domain.User;
import com.tdd.week04.chapter07.user.exception.DupIdException;
import com.tdd.week04.chapter07.user.exception.WeakPasswordException;
import com.tdd.week04.chapter07.user.repository.MemoryUserRepository;
import com.tdd.week04.chapter07.user.validation.SpyEmailNotifier;
import com.tdd.week04.chapter07.user.validation.StubWeakPasswordChecker;
import com.tdd.week04.chapter07.user.service.UserRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    private UserRegister userRegister;
    //약한 비밀번호인지 확인하는 stub 대역
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    //동일한 ID를 가진 회원이 존재하는지 확인하는 fake 대역
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    //이메일 발송 여부를 확인하는 spy 대역
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        //암호가 약하다고 응답하도록 설정
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class,
                () -> userRegister.register("id", "pw", "email"));
    }

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists() {
        //이미 같은 ID 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw", "email@email.com"));

        assertThrows(DupIdException.class,
                () -> userRegister.register("id", "pw2", "email"));
    }

    @Test
    @DisplayName("같은 ID가 없으면 회원 가입 성공")
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        //가입 결과 확인
        User savedUser = fakeRepository.findById("id");

        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    @DisplayName("가입하면 메일 전송")
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
