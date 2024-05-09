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

        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        if (isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);

            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else return candidateExp;
    }

    private static boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() != date2.getDayOfMonth();
    }

    private static int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }

}
