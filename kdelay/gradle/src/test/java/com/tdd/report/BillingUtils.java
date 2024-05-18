package com.tdd.report;

import java.time.LocalDateTime;

// 납부를 처리하는 로직
public class BillingUtils{

    //만료일 계산 기능
    public static LocalDateTime calculateExpiryDate(LocalDateTime startDate) {
        return startDate.plusDays(30);
    }

    //만료일 연장 기능
    public static LocalDateTime extendExpiryDate(LocalDateTime currentExpiryDate) {
        return currentExpiryDate.plusMonths(1);
    }
}
