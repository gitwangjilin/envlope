package com.redshield.envlope.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.redshield.envlope.entity.TimeServiceResponse;
import com.redshield.envlope.entity.TimeServviceRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: TimeController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/5/1   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "sm2")
@Slf4j
@RestController
@RequestMapping
public class TimeController {
    @ApiOperation(value = "sm2")
    @PostMapping("sm2")
    public String getSm2(@RequestBody TimeServviceRequest timeServviceRequest){
        TimeServiceResponse timeServiceResponse = new TimeServiceResponse();
        timeServiceResponse.setMessage("123");
        timeServiceResponse.setStatus(0);
        timeServiceResponse.setTsaBase64("1111111111111111111");
        return JSON.toJSONString(timeServiceResponse);
    }
    @ApiOperation(value = "verify")
    @PostMapping("verify")
    public String verify(@RequestBody TimeServviceRequest timeServviceRequest){
        TimeServiceResponse timeServiceResponse = new TimeServiceResponse();
        timeServiceResponse.setMessage("123");
        timeServiceResponse.setStatus(0);
        timeServiceResponse.setTsaBase64("1111111111111111111");
        return JSON.toJSONString(timeServiceResponse);
    }
}
