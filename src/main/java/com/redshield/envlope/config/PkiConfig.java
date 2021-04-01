package com.redshield.envlope.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: PkiConfig
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/3/29   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@Component
@ConfigurationProperties(prefix = "pki-config")
public class PkiConfig {
    private String dllName;
    private String pkiKeyLable;
    private String pkiKeyPasswd;
    private int pkiKeyAlgo;
    private int pkiHashAlgo;
}
