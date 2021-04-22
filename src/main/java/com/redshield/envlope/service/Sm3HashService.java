package com.redshield.envlope.service;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm3HashService
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
public interface Sm3HashService {
    String getSm3Hash(String data);
}
