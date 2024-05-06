package chap02;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        if (isSameDayOfMonth(dayOfFirstBilling, candidateExp)) {
            final int dayLenOfDayOfCandiMon = this.lastDayOfMonth(candidateExp);

            if (dayLenOfDayOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfDayOfCandiMon);
            }

            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(int dayOfFirstBilling, LocalDate candidateExp){
        return dayOfFirstBilling != candidateExp.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp){
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
