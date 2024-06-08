package com.tdd.week03.chapter07.autodebit.service;


import com.tdd.week03.chapter07.autodebit.domain.AutoDebitInfo;
import com.tdd.week03.chapter07.autodebit.domain.AutoDebitReq;
import com.tdd.week03.chapter07.autodebit.repository.MemoryAutoDebitInfoRepository;
import com.tdd.week03.chapter07.autodebit.validate.CardNumberValidator;
import com.tdd.week03.chapter07.autodebit.validate.CardValidity;

import java.time.LocalDateTime;

/**
 * 자동이체 비즈니스 로직
 * 구현 X
 */
public class AutoDebitRegister {

    private CardNumberValidator validator;
    private MemoryAutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, MemoryAutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());

        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserId());

        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }
        return RegisterResult.success();
    }
}
