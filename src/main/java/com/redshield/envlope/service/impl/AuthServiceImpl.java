package com.redshield.envlope.service.impl;

import com.redshield.envlope.entity.Effective;
import com.redshield.envlope.entity.QueryEnterpriseInfo;
import com.redshield.envlope.mapper.auth.EffectiveMapper;
import com.redshield.envlope.mapper.bizqry.QueryEnterpriseInfoMapper;
import com.redshield.envlope.service.AuthService;
import com.redshield.envlope.service.Sm3HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: AuthServiceImpl
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/6   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    Sm3HashService sm3HashService;

    @Autowired
    QueryEnterpriseInfoMapper queryEnterpriseInfoMapper;

    @Autowired
    EffectiveMapper effectiveMapper;
    @Override
    public String getEntInfo(String idCardHash) {
        String hash = sm3HashService.getSm3Hash(idCardHash);
        List<QueryEnterpriseInfo> idCardAndOrgCode = queryEnterpriseInfoMapper.findIdCardAndOrgCode(hash);
        return idCardAndOrgCode.toString();
    }

    @Override
    public String verifySn(String licenseSn) {
        Effective effectiveBySn = effectiveMapper.findEffectiveBySn("EFFECTIVE", licenseSn);
        return effectiveBySn.toString();
    }
//    /**
//     * 兼容总局有效表和省有效表
//     * @param areaCode
//     * @return
//     */
//    private String getEffTable(String areaCode) {
//        String localSysCode = sysConfig.getSyscode().substring(sysConfig.getSyscode().lastIndexOf("_") + 1);
//        String effecTable=null;
//        //判断是否是本总局签发的执照
//        if(localSysCode.substring(0, 2).equals(areaCode)){
//            effecTable="EFFECTIVE";
//        }else{
//            effecTable="EFFECTIVE_"+areaCode;
//        }
//        return effecTable;
//    }
}
