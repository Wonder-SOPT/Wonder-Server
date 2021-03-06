package com.wonder.bring.service;

import com.wonder.bring.model.DefaultRes;

import java.util.Optional;

/**
 * Created by bomi on 2019-01-02.
 */

public interface MapService {
    DefaultRes getStores(final Optional<Double> la, final Optional<Double> lo); // 위도, 경도 받아오기
    DefaultRes getStoreInfo(final int storeIdx);
}
