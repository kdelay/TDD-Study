package com.tdd.week04.chapter07.user;

import com.tdd.week04.chapter07.user.exception.WeakPasswordException;
import com.tdd.week04.chapter07.user.repository.MemoryUserRepository;
import com.tdd.week04.chapter07.user.service.UserRegister;
import com.tdd.week04.chapter07.user.validation.EmailNotifier;
import com.tdd.week04.chapter07.user.validation.WeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserRegisterMockTest {

    private UserRegister userRegister;
    @Mock
    private WeakPasswordChecker mockPasswordChecker;
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    @Mock
    private EmailNotifier mockEmailNotifier;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("Mock 객체 이용(인자 캡처) + 가입하면 메일 전송")
    void whenRegisterThenSendMailWithMock() {
        userRegister.register("id", "pw", "email@email.com");

        //인자 캡처
        //String 타입의 인자를 보관할 수 있는 ArgumentCaptor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //모의 객체 호출 여부 검증
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        //모의 객체를 실행할 때 사용한 인자 값
        String realEamil = captor.getValue();
        assertEquals("email@email.com", realEamil);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        //인자 값으로 "pw"를 주었을 때 결과 값이 true이다.
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        //true일 때 exception 발생 (조건문)
        assertThrows(WeakPasswordException.class,
                () -> userRegister.register("id", "pw", "email"));
    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행")
    void checkPassword() {
        //회원 가입
        userRegister.register("id", "pw", "email");

        //checkPasswordWeak 메서드가 호출되었는지 검증(파라미터는 String 타입)
        then(mockPasswordChecker).should().checkPasswordWeak(anyString());
    }
}
