package com.redshield.envlope.controller;

import com.alibaba.fastjson.JSON;
import com.business.template.TemplateHelper;
import com.cntrust.ph.pkijni.Extension;
import com.cntrust.phpkijni.PHPkiComm;
import com.framework.core.pki.algo.AsymmAlgo;
import com.framework.core.pki.algo.HashAlgo;
import com.framework.core.pki.ex.Exts;
import com.framework.core.pki.exception.PKIException;
import com.framework.core.pki.util.*;
import com.redshield.envlope.config.PkiConfig;
import com.redshield.envlope.paramet.CertParamet;
import com.redshield.envlope.paramet.MakeSystemCertParamet;
import com.redshield.envlope.paramet.SubjectsList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

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
@Api(tags = "证书管理")
@Slf4j
@RequestMapping
@RestController
public class CertController {

    final Base64.Decoder decoder = Base64.getDecoder();

    @Autowired
    PkiConfig pkiConfig;

    @ApiOperation(value = "p10")
    @PostMapping("p10")
    public String p10(@RequestBody CertParamet certParamet) {
        String province = "CN";//省
        String city = "北京"; //市
        String sysCode = "SAIC_FRONT_109999";  //系统标识码//TODO
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
            comm2.init(pkiConfig.getDllName());
        } catch (PKIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<SubjectsList> subjectsLists = certParamet.getSubjectsLists();
        RequestData requ = new RequestData();
        Subject[] subjects = new Subject[subjectsLists.size()];
        for (int i = 0; i < subjectsLists.size(); i++) {
            Subject subjectCN = new Subject();
            subjectCN.setSubjectOid(subjectsLists.get(i).getSubjectOid());
            subjectCN.setSubjectValue(subjectsLists.get(i).getSubjectValue());
            subjectCN.setSubjectCoding(ExtentionObject.stringcode.utf_code);
//            subjectCN.s
            subjects[i] = subjectCN;
        }
        requ.setSubject(subjects);
        requ.setKeyAlgo(AsymmAlgo.asymmAlgo.SM2);
        requ.setHashAlgo(HashAlgo.hashAlgo.sm3);
        requ.setKeyLable(certParamet.getKeyLable());
        requ.setKeyPasswd(pkiConfig.getPkiKeyPasswd());
        try {
            String p10 = comm2.genCertRequest(requ);
            return p10;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            comm2.release();
        }
        return null;
    }

    @ApiOperation(value = "签发证书")
    @PostMapping("makeSystemCert")
    public String p10(@RequestBody MakeSystemCertParamet makeSystemCertParamet) {
        String notbefor = "";
        String notafter = "";
        PHPkiComm pkiComm = new PHPkiComm();
        ServerCertKey rootCertKey = new ServerCertKey();
        rootCertKey.setB64ServerCert(makeSystemCertParamet.getP10());
        rootCertKey.setKeyAlgo(AsymmAlgo.asymmAlgo.SM2);
        rootCertKey.setKeyLable(makeSystemCertParamet.getKeyLable());
        rootCertKey.setKeyLength(256);
        rootCertKey.setKeyPasswd(pkiConfig.getPkiKeyPasswd());
        rootCertKey.setPkidllName(pkiConfig.getDllName());
        SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");

        SignP10Ext caP10 = new SignP10Ext();
        Date notbefordate = new Date();
        notbefor = formate.format(notbefordate);
        caP10.setAvailabilityTime(notbefor);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 30);
        caP10.setB64P10Request(makeSystemCertParamet.getP10());
        notafter = formate.format(calendar.getTime());
        caP10.setExpiryTime(notafter);
        String signCertSn = "100000" + caP10.getAvailabilityTime();
        caP10.setSignCertSn(signCertSn);
        int extsLen = 3;
        Exts[] exts = new Exts[extsLen];
        if (extsLen > 0) {
            Exts ext = new Exts();
            ext.setExtOid(Extension.SubjectKeyIdentifier);
            exts[0] = ext;
        }
        if (extsLen > 1) {
            Exts ext1 = new Exts();
            ext1.setExtOid(Extension.AuthorityKeyIdentifier);
            exts[1] = ext1;
        }
        if (extsLen > 2) {
            Exts ext2 = new Exts();
            ext2.setExtOid(Extension.KeyUsage);
            ext2.setExtValue("162");
            exts[2] = ext2;
        }
        caP10.setExts(exts);

        CertObject certobj = null;
        try {
            pkiComm.init(pkiConfig.getDllName());
            certobj = pkiComm.genCACert(caP10, rootCertKey);
            return certobj.getB64SignCert();
        } catch (PKIException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("签发失败");
        } finally {
            pkiComm.release();
        }
        return null;
    }


    @GetMapping("openCert")
    @ApiOperation(value = "解执照实体")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "cert", value = "证书", dataType = "String", required = true),
                    @ApiImplicitParam(name = "role", value = "角色", dataType = "String")
            }
    )
    public String openCert(String cert, String role) {
        if (Objects.equals("2", role)) {
            try {
                byte[] content = decoder.decode(cert);
                return JSON.parseObject(new String(content, StandardCharsets.UTF_8), Map.class).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            TemplateHelper helper = new TemplateHelper(cert);
            return helper.getIdAndValueMap().toString();
        }
        return null;
    }

}
