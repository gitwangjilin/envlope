package com.redshield.envlope.controller;

import com.cntrust.phpkijni.PHPkiComm;
import com.framework.core.pki.algo.AsymmAlgo;
import com.framework.core.pki.algo.SymmAlgo;
import com.framework.core.pki.util.EnvelopeObject;
import com.framework.core.pki.util.ServerCertKey;
import com.redshield.envlope.entity.SignParamet;
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
@Api(tags = "数字信封加解密")
@Slf4j
@RestController
@RequestMapping
public class EnvController {
    @ApiOperation(value = "数字信封加解密")
    @PostMapping("makeEnvelopeOpenEnvelope")
    public String makeEnvelopeOpenEnvelope(@RequestBody SignParamet signParamet) {
        return openEnvelope(makeEnvelope(signParamet.getCert(), signParamet.getData()), signParamet.getCert(), signParamet.getKeyLable());
    }

    @ApiOperation(value = "加密")

    @PostMapping("makeEnvelope")
    public String makeEnvelope(@RequestBody SignParamet signParamet) {
        return makeEnvelope(signParamet.getCert(), signParamet.getData());
    }

    @ApiOperation(value = "解密")
    @PostMapping("openEnvelope")
    public String openEnvelope(@RequestBody SignParamet signParamet) {
        return openEnvelope(signParamet.getSignData(), signParamet.getCert(), signParamet.getKeyLable());
    }

    /**
     * 数字信封加密
     */
    public static String makeEnvelope(String cert, String data) {
        PHPkiComm comm = new PHPkiComm();
        String env = null;
        try {
            comm.init("libph_sdf.so");
            EnvelopeObject envelopeObj = new EnvelopeObject();
            String[] b64Certs = new String[1];
            b64Certs[0] = cert;//电子营业执照系统提供的证书
            envelopeObj.setB64Cert(b64Certs);
            envelopeObj.setPlainData(data.getBytes("UTF-8"));
            envelopeObj.setSymmAlgo(SymmAlgo.symmAlgoEnvelopedData.SM4_ECB);
            env = comm.sealEnvelope(envelopeObj);
            System.out.println("数字信封数据：" + env);
        } catch (Exception e) {
            System.out.println("数字信封加密失败：" + e.getMessage());
        } finally {
            try {
                comm.release();
            } catch (Exception e) {
                System.out.println("释放密码机资源时异常：" + e.getMessage());
            }
        }
        return env;
    }

    /**
     * 数字信封解密
     *
     * @param env
     * @return
     */
    public static String openEnvelope(String env, String cert, String keyLable) {
        byte[] decData = null;
        //返回原文
        String data = null;
        PHPkiComm comm = new PHPkiComm();
        try {
            comm.init("libph_sdf.so");
            ServerCertKey serverKey = new ServerCertKey();
            serverKey.setPkidllName("libph_sdf.so");
            serverKey.setB64ServerCert(cert);//你们自己的证书
            serverKey.setKeyAlgo(AsymmAlgo.asymmAlgo.SM2);
            serverKey.setKeyLable(keyLable);//密码机索引号
            serverKey.setKeyLength(256);
            serverKey.setKeyPasswd("1234567a");//密码机密码
            decData = comm.openEnvelope(env, serverKey);
            data = new String(decData, "UTF-8");
            System.out.println("数字信封解密：" + data);
        } catch (Exception e) {
            System.out.println("数字信封解密失败：" + e.getMessage());
        } finally {
            try {
                comm.release();
            } catch (Exception e) {
                System.out.println("释放密码机资源时异常：" + e.getMessage());
            }
        }
        return data;
    }

}
