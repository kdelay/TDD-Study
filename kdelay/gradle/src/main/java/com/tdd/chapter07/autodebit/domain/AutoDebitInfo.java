package com.tdd.chapter07.autodebit.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * [자동이체 정보]
 * - 사용자명
 * - 카드번호
 * - 변경 시간
 */
@Getter @Setter
public class AutoDebitInfo {

    private String userId;
    private String cardNumber;
    private LocalDateTime localDateTime;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime localDateTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.localDateTime = localDateTime;
    }

    public void changeCardNumber(String cardNumber) {
        setCardNumber(cardNumber);
    }
}
