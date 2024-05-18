package com.tdd.addition.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class GameGenMockTest {

    @Mock
    private GameNumGen genMock;

    @Test
    @DisplayName("mock 테스트")
    void mockTest() {
        //모의(mock) 객체 생성
//        GameNumGen genMock = mock(GameNumGen.class);

        //BDDMockito.given -> stub 설정
        //1) 지정한 값 리턴
        // -> 일치하는 stub 설정이 없을 경우, 리턴 타입의 기본 값을 리턴한다.
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        //stub 설정에 매칭되는 메서드 실행
        String num = genMock.generate(GameLevel.EASY);

        //검증
        assertEquals("123", num);

        /* ----------------------------------------- */

        //2) exception 발생
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
//        given(genMock.generate(null)).willThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> genMock.generate(null));
    }

    /**
     * return type: void
     * exception 발생 -> BDDMockito.willThrow() 메서드 사용
     */
    @Test
    @DisplayName("리턴 타입이 void일 경우")
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class) //exception type or 객체 인자로 받음
                .given(mockList) //모의 객체 전달 받음
                .clear();

        assertThrows(UnsupportedOperationException.class, () -> mockList.clear());
    }

    /**
     * ArgumentMachers <- Mockito, BDDMockito
     */
    @Test
    @DisplayName("인자 매칭 처리")
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);

        //any(): 모든 값에 일치하도록 stub 설정
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num2);
    }

    /**
     * anyInt(), any() 등 메서드는 내부적으로 인자의 일치 여부를 판단하기 위해 ArgumentMatcher를 등록한다.
     * Mockito는 한 인자라도 ArgumentMatcher를 사용해서 설정한 경우 모든 인자를 ArgumentMatcher를 이용해서 설정한다.
     */
    @Test
    @DisplayName("stub을 설정할 메서드의 인자가 두 개 이상인 경우")
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);

        //정확한 값 일치가 필요한 경우 eq() 메서드 사용하기
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456", old);
    }

    /**
     * 행위 검증
     * then(): 메서드 호출 여부를 검증할 모의 객체 전달 받음
     * should(): 메서드 다음에 실제로 불려야 할 메서드 지정
     */
    @Test
    @DisplayName("모의 객체의 메서드가 불렸는지 여부를 검증")
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        //genMock 모의 객체의 generate() 메서드가 GameLevel.EASY 인자를 사용해서 호출되었는지 검증하고 싶은 경우
        then(genMock).should().generate(GameLevel.EASY);

        //정확한 값이 아닌 메서드 호출 여부가 중요한 경우
        then(genMock).should().generate(any());

        //정확하게 한 번만 호출된 것을 검증하고 싶은 경우
        then(genMock).should(only()).generate(any());
    }
}