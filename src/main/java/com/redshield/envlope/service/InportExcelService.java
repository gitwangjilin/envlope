package com.redshield.envlope.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: InportExcelService
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/9/26   WangJiLin     Create the current class
 *************************************************************************
 ******/
public interface InportExcelService {
    String tradeType();

    ResponseEntity<byte[]> tradeTypeAll();
}
