package com.redshield.envlope.entity;

import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm2Request
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
public class TimeServviceRequest {
    /**
     * PDF摘要的16进制串
     */
    private String imprintHex;
    /**
     * sm3算法
     */
    private String algo;
    /**
     * 随机数
     */
    private String nonce;
}
