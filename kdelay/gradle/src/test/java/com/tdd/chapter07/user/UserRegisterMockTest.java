package com.tdd.chapter07.user;

import com.tdd.chapter07.user.repository.MemoryUserRepository;
import com.tdd.chapter07.user.service.UserRegister;
import com.tdd.chapter07.user.validation.EmailNotifier;
import com.tdd.chapter07.user.validation.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserRegisterMockTest {

    private UserRegister userRegister;

    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    @Mock
    private EmailNotifier mockEmailNotifier;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, mockEmailNotifier);
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
}
