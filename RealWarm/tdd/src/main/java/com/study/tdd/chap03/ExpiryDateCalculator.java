package com.study.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths=payData.getPayAmount()/10_000;
        if(payData.getFirstBillingDate()!=null){
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if(payData.getFirstBillingDate().getDayOfMonth()!=
                    candidateExp.getDayOfMonth()){
                if(YearMonth.from(candidateExp).lengthOfMonth()<
                        payData.getFirstBillingDate().getDayOfMonth()){
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }//if-3
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }//if-2
        }//if-1
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
