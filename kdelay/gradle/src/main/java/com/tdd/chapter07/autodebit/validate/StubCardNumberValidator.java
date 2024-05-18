package com.tdd.chapter07.autodebit.validate;

/**
 * 카드 정보 API의 유효한 카드번호, 도난 카드번호 대역
 * 스텁(Stub) 사용
 */
public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidNo;
    private String theftNo;

    //setter
    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validity(String cardNumber) {

        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }

        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }
}
