package com.redshield.envlope.controller;

import com.cntrust.phpkijni.PHPkiComm;
import com.framework.core.pki.algo.HashAlgo;
import com.google.common.base.CharMatcher;
import com.redshield.envlope.entity.SignParamet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: EnvController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/3/26   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "签名验签")
@Slf4j
@RequestMapping
@RestController
public class SignController {
    @ApiOperation(value = "签名验签")
    @PostMapping("signDateVerifySign")
    public boolean signDateVerifySign(@RequestBody SignParamet signParamet){
        return  verifySign(signData(signParamet.getData(),signParamet.getKeyLable()),signParamet.getCert(),signParamet.getData());
    }

    @ApiOperation(value = "签名")
    @PostMapping("signDate")
    public String signDate(@RequestBody SignParamet signParamet){
        return  signData(signParamet.getData(),signParamet.getKeyLable());
    }

    @ApiOperation(value = "验签")
    @PostMapping("verifySign")
    public boolean signData(@RequestBody SignParamet signParamet){
        return  verifySign(signParamet.getSignData(),signParamet.getCert(),signParamet.getData());
    }

    /**
     * @param data 原文
     * @Description: 生成签名
     * @return: java.lang.String 签名值
     * @Author: luolei
     * @Date: 2020/9/3 11:40
     **/
    public static String signData(String data,String keyLable) {
        PHPkiComm comm = new PHPkiComm();
        String sign = null;
        try {
            comm.init("libph_sdf.so");
            byte[] signData = comm.signData(keyLable, "1234567a", 131328,
                    1, data.getBytes(StandardCharsets.UTF_8));

            BASE64Encoder encode = new BASE64Encoder();
            sign = encode.encode(signData);
            if (StringUtils.isNotBlank(sign)) {
                sign = CharMatcher.breakingWhitespace().removeFrom(sign);
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                comm.release();
            } catch (Exception e) {
            }
        }
        return sign;
    }

    /**
     * 验签
     *
     * @param sign
     * @param sysComCert
     * @param data
     * @return
     */
    public static boolean verifySign(String sign, String sysComCert, String data) {
        if (StringUtils.isNotBlank(sign)) {
            sign = CharMatcher.breakingWhitespace().removeFrom(sign);
        }
        PHPkiComm comm = new PHPkiComm();
        try {
            comm = new PHPkiComm();
            comm.init("libph_sdf.so");
            BASE64Decoder decoder = new BASE64Decoder();
            boolean isPass = comm.verifySign(sysComCert, data.getBytes(StandardCharsets.UTF_8),
                    decoder.decodeBuffer(sign), HashAlgo.hashAlgo.sm3);
            return isPass;
        } catch (Exception e) {
        } finally {
            try {
                comm.release();
            } catch (Exception e) {
            }
        }
        return false;
    }
}
