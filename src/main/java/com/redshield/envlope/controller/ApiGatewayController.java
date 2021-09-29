package com.redshield.envlope.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: ApiGatewayController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/7/18   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Slf4j
@RestController
@RequestMapping
public class ApiGatewayController {

    @Value("${apiString}")
    private String apiString;

    @GetMapping("license/getLoginQrCode")
    public String verify( ){
      log.info("调用鉴权测试。");
      return "{\"MSG\":\"操作成功\",\"FRONTEND\":\"{\\\"uid\\\":\\\"WQL4LFJE\\\",\\\"appid\\\":\\\"3a9c9fbdb012e257\\\",\\\"sysCode\\\":\\\"SAIC_BIZ_4403001G\\\",\\\"authCode\\\":\\\"bd6bd71a80004a9c9514dc36c08602ad\\\",\\\"tradeType\\\":\\\"\\\",\\\"state\\\":\\\"2\\\",\\\"startTime\\\":\\\"2021-01-26 00:00:00\\\",\\\"endTime\\\":\\\"2023-01-26 23:59:59\\\"}\",\"CODE\":200,\"COLUMNIDS\":[\"businessLicense-getLoginQrCode.DATA.QRID\",\"businessLicense-getLoginQrCode.DATA.QRINFO\"]}";
    }
}
