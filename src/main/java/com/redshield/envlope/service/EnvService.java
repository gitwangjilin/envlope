package com.redshield.envlope.service;

import com.cntrust.phpkijni.PHPkiComm;
import com.framework.core.pki.algo.AsymmAlgo;
import com.framework.core.pki.algo.SymmAlgo;
import com.framework.core.pki.util.EnvelopeObject;
import com.framework.core.pki.util.ServerCertKey;
import com.redshield.envlope.config.PkiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: EnvService
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
@Component
public class EnvService {
    @Value("system-cert")
    private String cert;
    @Autowired
    PkiConfig pkiConfig;
    /**
     * 数字信封解密
     *
     * @param env
     * @return
     */
    public String openEnvelope(String env) {
        byte[] decData = null;
        //返回原文
        String data = null;
        PHPkiComm comm = new PHPkiComm();
        try {
            comm.init(pkiConfig.getDllName());
            ServerCertKey serverKey = new ServerCertKey();
            serverKey.setPkidllName(pkiConfig.getDllName());
            serverKey.setB64ServerCert(cert);
            serverKey.setKeyAlgo(AsymmAlgo.asymmAlgo.SM2);
            serverKey.setKeyLable("14");//密码机索引号
            serverKey.setKeyLength(256);
            serverKey.setKeyPasswd(pkiConfig.getPkiKeyPasswd());//密码机密码
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

    /**
     * 数字信封加密
     */
    public String makeEnvelope(String cert, String data) {
        PHPkiComm comm = new PHPkiComm();
        String env = null;
        try {
            comm.init(pkiConfig.getDllName());
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

}
