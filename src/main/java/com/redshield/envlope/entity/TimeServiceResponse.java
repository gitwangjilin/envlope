package com.redshield.envlope.entity;

import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm2Response
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/30   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
public class TimeServiceResponse {
    /**
     * 错误码
     */
    private int status;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 时间戳的base64编码
     */
    private String tsaBase64;
}
