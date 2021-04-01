package com.redshield.envlope.controller;

import com.redshield.envlope.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: SendController
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
@Api(tags = "请求外部系统")
@Slf4j
@RestController
@RequestMapping
public class SendController {
    @Autowired
    SendService sendService;

    @ApiOperation(value = "外部系统解密")

    @PostMapping("openEnv")
    public String openEnv(@RequestParam String data) {
        return sendService.openEnv(data);
    }

}
