package com.tdd.chapter07.autodebit.service;

import com.tdd.chapter07.autodebit.validate.CardValidity;

/**
 * 자동이체 결과
 * 구현 X
 */
public class RegisterResult {

    public static RegisterResult error(CardValidity validity) {
        return null;
    }

    public static RegisterResult success() {
        return null;
    }

    public CardValidity getValidity() {
        return null;
    }
}