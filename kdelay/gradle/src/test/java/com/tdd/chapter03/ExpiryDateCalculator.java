package com.tdd.chapter03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    /**
     * 리팩토링 순서 정리
     * 1. Test 중복 코드 함수화 (assertExpiryDate)
     * 2. 만료일 계산을 위한 필요한 값이 3개로 늘어났으므로 파라미터 객체로 변경 (PayData)
     * 3. 상수 -> 변수 변경 (addedMonths)
     * 4. 중복 코드 변수화 (dayLenOfCandiMon, dayOfFirstBilling)
     * 5. 비즈니스 로직 함수화 (expiryDateUsingFirstBillingDate)
     */
    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths = payData.getPayMount() / 10_000;

        //첫 납부일이 있는 경우
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private static LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        //금액만큼 만료 달이 증가된 임시 만료일
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        //첫 납부일의 일자
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        //첫 납부일 != 임시 만료일
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {

            //만료 달의 가장 마지막 일자
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            //2만원 이상 납부 + (임시 만료일 < 첫 납부일): 임시 만료 달의 일자가 넘는 경우
            // -> 임시 만료 달의 마지막 날짜를 만료일로 사용
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            //1만원 납부 -> 첫 납부일을 만료일로 사용
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else return candidateExp; //첫 납부일 == 임시 만료일인 경우, 그대로 반환
    }

}
