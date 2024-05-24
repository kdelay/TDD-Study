# Mockist
- 모의 객체 생성 가능
- stub, spy 설정 가능

## Stub 설정
<details>

<summary>지정한 값 리턴</summary>

### 지정한 값 리턴
```text
//GameNumGen 클래스에 대해 mock을 설정한다.
GameNumGen genMock = mock(GameNumGen.class);

//BDDMockito.given
//GameLevel이 EASY 값일 경우, 123이 반환될 것이라고 stub을 설정한다.
given(genMock.generate(GameLevel.EASY)).willReturn("123");

//스텁 설정에 매칭되는 메서드를 실행한다.
String num = genMock.generate(GameLevel.EASY);
```
    
</details>

<details>

<summary>Exception 발생</summary>

### Exception 발생
```text
//GameNumGen 클래스에 대해 mock을 설정한다.
GameNumGen genMock = mock(GameNumGen.class);

//BDDMockito.given
//GameLevel이 null일 경우 IllegalArgumentException 발생할 것이라고 stub을 설정한다.
given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

//검증
assertThrows(
        IllegalArgumentException.class,
        () -> genMock.generate(null);
)
```
#### 리턴 타입이 void인 경우
```text
//List mock 설정
List<String> mockList = mock(List.class);

//mockList 모의 객체에 대해서 UnsupportedOperationException 발생할 것이라고 stub을 설정한다.
willThrow(UnsupportedOperationException.class)
    .given(mockList)
    .clear();
    
//검증
assertThrows(
    UnsupportedOpertionException.class,
    () -> mockList.clear();
);
```

</details>

## 인자 매칭 처리
- ArgumentMatchers 클래스를 통해 정확하게 일치하는 값 대신 임의의 값에 일치하도록 설정할 수 있다.
<details>
<summary>ArgumentMatchers 클래스 메서드 종류</summary>

### 기본 데이터 타입에 대한 임의 값 일치
- anyInt(), anyShort(), anyLong(), anyChar(), anyBoolean()...
### 문자열에 대한 임의 값 일치
- anyString()
### 임의 타입에 대한 일치
- any()
### 임의 콜렉션에 대한 일치
- anyList(), anySet(), anyMap(), anyCollection()
### 정규표현식을 이용한 String 값 일치 여부
- matches(String), matches(Pattern)
### 특정 값과 일치 여부
- eq(값)
</details>

<details>
<summary>메서드의 인자가 두 개 이상인 경우</summary>

```text
인자 값에 [anyInt(), "123"] 으로 설정할 수 없다.
"123"은 정확한 값으로 일치해야 하므로, eq를 사용해야 한다.

-> [anyInt(), eq("123")
```

</details>

## 행위 검증
- BDDMockito.then(): 메서드 호출 여부를 검증할 모의 객체를 전달받는다.
- should(): 모의 객체의 메서드가 불려야 한다는 것을 설정하고, 메서드 다음에 실제로 불려야 할 메서드를 지정한다.
```text
//genMock 모의 객체의 generate() 메서드가 GameLevel.EASY 인자를 사용해서 호출되었는지 검증한다.
then(genMock).should().generate(GameLevel.EASY);
```

<details>
<summary>메서드 호출 횟수 검증 메서드(Mockito 클래스 제공)</summary>

- only(): 한 번만 호출
- times(int): 지정한 횟수만큼 호출
- never(): 호출하지 않음
- atLeast(int): 적어도 지정한 횟수만큼 호출
- atLeastOnce(): atLeast(1)과 동일
- atMost(int): 최대 지정한 횟수만큼 호출
</details>

## 인자 캡쳐
- 모의 객체를 호출할 때 사용한 인자를 검증해야 할 때 사용한다. (많은 속성을 가진 객체인 경우)
- ArgumentCaptor: 메서드 호출 여부를 검증하는 과정에서 실제 호출할 때 전달한 인자를 보관할 수 있다.
```text
//String 타입의 인자를 보관할 수 있는 ArgumentCaptor를 생성한다.
ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

//mockEamilNotifier의 sendRegisterEmail 메서드가 호출되었는지 검증하고, 인자를 전달한다.
then(mockEmailNotifier)
    .should().sendRegisterEmail(captor.capture());
    
//인자 캡처를 통해 getter로 해당 값을 가져올 수 있다.
String realEamil = captor.getValue();
```