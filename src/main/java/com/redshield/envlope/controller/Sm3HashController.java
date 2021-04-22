package com.redshield.envlope.controller;

import com.bouncycastle.crypto.digests.SM3Digest;
import com.redshield.envlope.paramet.SignParamet;
import com.redshield.envlope.service.Sm3HashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm3HashController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/1   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "SM3hash")
@Slf4j
@RestController
@RequestMapping
public class Sm3HashController {

    @Autowired
    Sm3HashService sm3HashService;

    @ApiOperation(value = "sm3Hash")
    @PostMapping("sm3Hash")
    public String sm3Hash(@RequestBody SignParamet signParamet) {
        return sm3HashService.getSm3Hash(signParamet.getData());
    }
}
