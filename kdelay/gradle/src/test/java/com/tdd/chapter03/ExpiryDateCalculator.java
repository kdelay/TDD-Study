package com.tdd.chapter03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    /**
     * calcualteExpiryDate 메서드 리팩토링 순서 정리
     * 1. 중복 코드 제거
     * 2. 만료일 계산을 위한 필요한 값이 3개로 늘어났으므로 파라미터 객체(PayData)로 변경
     * 3. 상수 -> 변수 변경
     */
    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths = payData.getPayMount() / 10_000;

        //첫 납부일이 있는 경우
        if (payData.getFirstBillingDate() != null) {
            //금액만큼 만료 달이 증가된 임시 만료일
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

            //첫 납부일 != 임시 만료일
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {

                System.out.println("YearMonth.from(candidateExp) = " + YearMonth.from(candidateExp));
                
                //2만원 이상 납부 + (임시 만료일 < 첫 납부일): 임시 만료 달의 일자가 넘는 경우
                // -> 임시 만료 달의 마지막 날짜를 만료일로 사용
                if(YearMonth.from(candidateExp).lengthOfMonth() < payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(
                            YearMonth.from(candidateExp).lengthOfMonth()
                    );
                }
                //1만원 납부 -> 첫 납부일을 만료일로 사용
                return candidateExp.withDayOfMonth(
                        payData.getFirstBillingDate().getDayOfMonth()
                );
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
