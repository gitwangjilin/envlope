package com.redshield.envlope.mapper.app;

import com.redshield.envlope.response.TradeType;
import com.redshield.envlope.response.TradeTypeAll;

import java.util.List;
import java.util.Map;

public interface TradeTypeMapper {

    List<TradeType> findAll();
    List<TradeTypeAll> find();
    /**
     * 通过授权内容
     *
     * @param code
     * @return
     */
    String findByCode(String code);

    void insert(TradeType type);

    void delete();
}
