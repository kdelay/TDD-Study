package com.study.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpiryDateCalculatorTest {

//    private void assertExpiryDate_origin(
//            LocalDate billingDate,
//            int payAmount,
//            LocalDate expectedExpiryDate
//    ){
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//        assertEquals(expectedExpiryDate, realExpiryDate);
//    }//assertExpiryDate
    private void assertExpiryDate(
            PayData payData,
            LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

//    @Test
//    void 만원_납부하면_한달_뒤가_만료일이_됨1(){
//        LocalDate billingDate = LocalDate.of(2019, 3, 1);
//        int payAmount = 10_000;
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);
//        //////////////////////////////////////////////////////////////////////////////
//        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
//        int payAmount2 = 10_000;
//        ExpiryDateCalculator cal2=new ExpiryDateCalculator();
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);
//        assertEquals(billingDate2.of(2019, 6, 5), expiryDate2);
//    }//만원_납부하면_한달_뒤가_만료일이_됨

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨_refact(){
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(10_000).build(),
                LocalDate.of(2019, 4, 1));
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨_리팩토링(){
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28));
        ///////////////////////////////////////////////////////////
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000).build(),
                LocalDate.of(2019, 6, 5));
    }//만원_납부하면_한달_뒤가_만료일이_됨_리팩토링

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
//        assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000).build(),
                LocalDate.of(2019, 2, 28));

//        assertExpiryDate(LocalDate.of(2019, 5, 31), 10_000, LocalDate.of(2019, 6, 30));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000).build(),
                LocalDate.of(2019, 6, 30));

//        assertExpiryDate(LocalDate.of(2020, 1, 31), 10_000, LocalDate.of(2020, 2, 29));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000).build(),
                LocalDate.of(2020, 2, 29));
    }//납부일과_한달_뒤_일자가_같지_않음

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));
        /////////////////////////////////
        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019, 7, 31));
    }//첫_납부일과_만료일_일자가_다를때_만원_납부

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1));
    }//이만원_이상_납부하면_비례해서_만료일_계산

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30));
    }//첫_납부일과_만료일_일자가_다를때_이만원_이상_납부



}//end






























