package com.tdd.chapter07.autodebit.repository;

import com.tdd.chapter07.autodebit.domain.AutoDebitInfo;

public interface AutoDebitInfoRepository {

    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}
