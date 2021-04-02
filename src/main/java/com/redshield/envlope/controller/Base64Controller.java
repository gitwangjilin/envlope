package com.redshield.envlope.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Base64Controller
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
@Api(tags = "base64编码解密")
@Slf4j
@RestController
@RequestMapping
public class Base64Controller {
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();

    @GetMapping("encode")
    @ApiOperation(value = "编码")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "encode", value = "字符串", dataType = "String", required = true)
    )
    public String encode(String encode) {
        try {
            return encoder.encodeToString(encode.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("decode")
    @ApiOperation(value = "解码")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "decode", value = "base64码", dataType = "String", required = true)
    )
    public String decode(String decode) {
        try {
            return new String(decoder.decode(decode), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "error";
    }
}
