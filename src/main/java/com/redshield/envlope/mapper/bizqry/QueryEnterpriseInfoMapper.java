package com.redshield.envlope.mapper.bizqry;


import com.redshield.envlope.entity.QueryEnterpriseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QueryEnterpriseInfoMapper {


    //法人身份核验
    List<QueryEnterpriseInfo> findIdCardAndOrgCode(@Param("idCardHash") String idCard);

}
