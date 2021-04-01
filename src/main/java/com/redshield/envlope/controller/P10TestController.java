package com.redshield.envlope.controller;

import com.cntrust.phpkijni.PHPkiComm;
import com.framework.core.pki.algo.AsymmAlgo;
import com.framework.core.pki.algo.HashAlgo;
import com.framework.core.pki.exception.PKIException;
import com.redshield.envlope.entity.SignParamet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.framework.core.pki.util.ExtentionObject;
import com.framework.core.pki.util.Subject;
import com.framework.core.pki.util.RequestData;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: P10TestController
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
@RequestMapping
@RestController
public class P10TestController {
    @ApiOperation(value = "p10")
    @PostMapping("p10")
    public String p10(@RequestBody SignParamet signParamet){
        String province = "CN";//省
        String city = "北京"; //市
        String sysCode ="SAIC_FRONT_109999";  //系统标识码//TODO
//        C = CN
//        L = 北京
//        O = 市场监督管理总局
//        OU = 信息中心
//        CN = 数据中心接口服务器
        PHPkiComm comm2 = new PHPkiComm();
        /**
         *
         * 初始化
         *
         *
         */
        try {
            comm2.init("libph_sdf.so");

        } catch (PKIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        RequestData requ = new RequestData();

        Subject[] subjects = new Subject[5];
        Subject subjectCN = new Subject();
        subjectCN.setSubjectOid("2.5.4.3");
        subjectCN.setSubjectValue("数据中心接口服务器");
        subjectCN.setSubjectCoding(ExtentionObject.stringcode.utf_code);
        subjects[0] = subjectCN;

        Subject subjectCN11 = new Subject();
        subjectCN11.setSubjectOid("2.5.4.11");
        subjectCN11.setSubjectValue("信息中心");
        subjectCN11.setSubjectCoding(ExtentionObject.stringcode.utf_code);
        subjects[1] = subjectCN11;

        Subject subjectC = new Subject();
        subjectC.setSubjectOid("2.5.4.10");
        subjectC.setSubjectValue("市场监督管理总局");
        subjectC.setSubjectCoding(ExtentionObject.stringcode.utf_code);
        subjects[2] = subjectC;


        Subject subjectS = new Subject();
        subjectS.setSubjectOid("2.5.4.7");
        subjectS.setSubjectValue("北京");
        subjectS.setSubjectCoding(ExtentionObject.stringcode.utf_code);
        subjects[3] = subjectS;


        Subject subjectL = new Subject();
        subjectL.setSubjectOid("2.5.4.6");
        subjectL.setSubjectValue("CN");
        subjectL.setSubjectCoding(ExtentionObject.stringcode.utf_code);
        subjects[4] = subjectL;




        requ.setSubject(subjects);
        requ.setKeyAlgo(AsymmAlgo.asymmAlgo.SM2);
        requ.setHashAlgo(HashAlgo.hashAlgo.sm3);
        requ.setKeyLable(signParamet.getKeyLable());
        requ.setKeyPasswd("1234567a");
        try {
            String p10 = comm2.genCertRequest(requ);
            return p10;

        } catch (Exception e) {
            e.printStackTrace();

        }finally{
            comm2.release();
        }
        return null;
    }
}
