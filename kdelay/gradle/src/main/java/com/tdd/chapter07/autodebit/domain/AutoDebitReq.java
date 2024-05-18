package com.tdd.chapter07.autodebit.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * [카드번호 변경]
 * - 사용자명
 * - 카드번호
 */
@Getter @Setter
public class AutoDebitReq {

    private String userId;
    private String cardNumber;

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }
}
