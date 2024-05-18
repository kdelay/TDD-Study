package com.tdd.chapter07.autodebit.repository;

import com.tdd.chapter07.autodebit.domain.AutoDebitInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 특정 사용자에 대한 자동이체 정보(등록/미등록) 대역
 * 가짜(Fake) 사용
 */
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {

    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
