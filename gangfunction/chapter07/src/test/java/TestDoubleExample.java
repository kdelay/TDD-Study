import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * TestDoubleExample
 * 1. 테스트 대상 식별
 * 2. 의존성 식별
 * 3. 테스트 더블 생성
 * 4. 스텁 설정
 * 5. 테스트 실행
 * 6. 결과 검증
 * 7. 테스트 정리
 */
@ExtendWith(MockitoExtension.class)
public class TestDoubleExample {
  /**
   * 1. 테스트 대상 식별
   * TooMuchTimeWastingRepository는 TimeWastingInjectedRepository에 의존성 주입되어있고,
   * TimeWastingInjectedRepository는 Thread.sleep(100000)을 실행하는 메소드가 있다.
   * 이때, TimeWastingInjectedRepository의 UsingStub_WithOutTimeWastingInjectedRepository() 메소드를 실행하지 않고 테스트를 진행하고 싶다.
   * 2. 의존성 식별
   * TooMuchTimeWastingRepository는 TimeWastingInjectedRepository에 의존성을 가지고 있다.
   */
  // Mock을 활용한 테스트 더블 생성
//  @Mock
//  TimeWastingInjectedRepository timeWastingInjectedRepository;
//  @InjectMocks
//  TooMuchTimeWastingRepository tooMuchTimeWastingRepository;


//  @Test
//  void UsingStub_WithOutTimeWastingInjectedRepository() {
//    // 4. 스텁 설정
//    // arrange
//    when(timeWastingInjectedRepository.run()).thenReturn(true);
//    // 5. 테스트 실행
//    // act
//    if(tooMuchTimeWastingRepository.run()){
//      System.out.println("Success");
//    }

    // 6. 결과 검증
    // assert
//    verify(tooMuchTimeWastingRepository).run();
//
//  }
  // 7. 테스트 정리 및 재설계
  @Mock
  TooMuchTimeWastingRepository tooMuchTimeWastingRepository;


  /**
   * 현재 테스트는 TimeWastingInjectedRepository의 run() 메소드를 실행하지 않고 테스트를 진행하고 있다.
   * 하지만 TooMuchTimeWastingRepository의 run() 메소드도 충분히 시간이 많이 걸린다..
   * 이를 해결하기 위해서는 TooMuchTimeWastingRepository의 run() 메소드를 실행하지 않고 테스트를 진행해야한다.
   */
  @Test
  void UsingStub_WithOutTooMuchTimeWastingRepository() {
    // 4. 스텁 설정
    // InjectMocks을 사용하여 의존성이 주입된 객체에도 실제 메소드가 호출될 수있음.
    // 중요한 것은 사용할 스텁에 대해서만 목을 생성해야한다.

    // arrange
    doReturn(true).when(tooMuchTimeWastingRepository).run();
    // 5. 테스트 실행
    // act
    boolean result = tooMuchTimeWastingRepository.run();
    if(result) {
      System.out.println("Success");
    }

    // 결과 검증
    verify(tooMuchTimeWastingRepository).run();
  }


}
