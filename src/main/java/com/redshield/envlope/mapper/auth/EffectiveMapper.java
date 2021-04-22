package com.redshield.envlope.mapper.auth;


import com.redshield.envlope.entity.Effective;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EffectiveMapper {

    //根据licenceSn查询有效数据
    Effective findEffectiveBySn(@Param("tableName") String tableName, @Param("licenceSn") String licenceSn);

    List<Effective> getEffectiveByOrgCode(@Param("tableName") String effTable, @Param("creditCode") String uniscid);
}
