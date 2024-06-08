package com.tdd.week04.chapter07.autodebit;

import com.tdd.week03.chapter07.autodebit.domain.AutoDebitInfo;
import com.tdd.week03.chapter07.autodebit.service.AutoDebitRegister;
import com.tdd.week03.chapter07.autodebit.domain.AutoDebitReq;
import com.tdd.week03.chapter07.autodebit.service.RegisterResult;
import com.tdd.week03.chapter07.autodebit.repository.MemoryAutoDebitInfoRepository;
import com.tdd.week03.chapter07.autodebit.validate.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * [대역 사용 내역 테스트]
 * 외부 카드 정보 API 연동 - StubCardNumberValidator
 * 자동이체 정보를 저장한 DB - MemoryAutoDebitInfoRepository
 */
public class AutoDebitRegister_Fake_Test {

    private AutoDebitRegister register;
    //유효하지 않거나 도난당한 카드번호 대역 객체 생성
    private StubCardNumberValidator cardNumberValidator;
    //메모리를 이용한 대역 객체 생성
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    @DisplayName("등록되어 있는 카드번호 변경 후 자동이체")
    void alreadyRegistered_InfoUpdated() {
        //이미 자동이체 정보가 등록되어 있는 상황을 위해 대역 사용(user1에 대한 자동이체 정보)
        repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        //user1은 `다른 카드번호`를 사용해서 자동이체 등록 기능 실행
        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        //자동이체한 번호가 user1의 `새로운 카드번호`인지 검증
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    @DisplayName("미등록 카드번호 등록 후 자동이체")
    void notYetRegistered_newInfoRegistered() {
        //user1의 카드번호 정보
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");

        //자동이체 실시
        RegisterResult result = this.register.register(req);

        //자동이체한 번호가 user1의 `기존 카드번호`인지 검증
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }
}
