package com.redshield.envlope.service;


import com.redshield.envlope.entity.EnterpriseInfo;
import com.redshield.envlope.response.Respone;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: CreationService
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/8   WangJiLin     Create the current class
 *************************************************************************
 ******/
public interface CreationService {
    Respone addEnterprise(EnterpriseInfo enterpriseInfo, String environment);
}
