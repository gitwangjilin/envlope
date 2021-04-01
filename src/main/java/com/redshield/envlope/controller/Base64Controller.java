package com.redshield.envlope.controller;

import com.redshield.envlope.entity.SignParamet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value = "编码")
    @PostMapping("encode")
    public String encode(@RequestBody SignParamet signParamet) {
        try {
            System.out.println(encoder.encodeToString(signParamet.getBase64Data().getBytes("UTF-8")));
            return encoder.encodeToString(signParamet.getBase64Data().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "编码失败";
    }

    @ApiOperation(value = "解码")
    @PostMapping("decode")
    public String decode(@RequestBody SignParamet signParamet) {
        try {
            return new String(decoder.decode(signParamet.getBase64Data()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "解码失败";
    }
}
