package com.redshield.envlope.service.impl;


import com.alibaba.fastjson.JSON;
import com.redshield.envlope.entity.EnterpriseInfo;
import com.redshield.envlope.entity.license.RegLicense;
import com.redshield.envlope.factory.ParseLicenseFactory;
import com.redshield.envlope.response.EntInfo;
import com.redshield.envlope.response.Respone;
import com.redshield.envlope.service.CreationService;
import com.redshield.envlope.utils.IdCardGenerator;
import com.redshield.envlope.utils.LicenseUtlis;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: CreationServiceImpl
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/8   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Slf4j
@Service
public class CreationServiceImpl implements CreationService {
    @Resource
    RestTemplate restTemplate;

//    @Value("${full-char-converter}")
//    private String fullCharConverter;

    @Override
    public Respone addEnterprise(EnterpriseInfo enterpriseInfo, String environment) {
        log.info("注册企业默认入参：{}", enterpriseInfo);
        if (StringUtils.isBlank(enterpriseInfo.getEntName())) {
            enterpriseInfo.setEntName("测试企业公司");
        }
        if (StringUtils.isBlank(enterpriseInfo.getIdCardHash())) {
            enterpriseInfo.setIdCardHash(IdCardGenerator.generate(enterpriseInfo.getAreaCode().substring(0, 2)));
        }
        if (StringUtils.isBlank(enterpriseInfo.getCreditCode())) {
            enterpriseInfo.setOrgCode(LicenseUtlis.getCreditCode());
        }
        if (StringUtils.isBlank(enterpriseInfo.getRegCode())) {
            enterpriseInfo.setRegCode(LicenseUtlis.getRegCode());
        }
        //手机号
        String phone = StringUtils.isBlank(enterpriseInfo.getPhoneNumber()) ? "13116111111" : enterpriseInfo.getPhoneNumber();
        String infoXml = "<?xml version='1.0' encoding='utf-8'?><licence><firm>";
        infoXml = infoXml + "<type>" + enterpriseInfo.getEntTemplateType() + "</type>";
        infoXml = infoXml + "<attribute500>" + enterpriseInfo.getSubjectL() + "</attribute500>";
        infoXml = infoXml + "<attribute501>" + enterpriseInfo.getSubjectS() + "</attribute501>";
        infoXml = infoXml + "<attribute504>" + enterpriseInfo.getAreaCode() + "</attribute504>";
        infoXml = infoXml + "<attribute13>" + enterpriseInfo.getCreditCode() + "</attribute13>";
        infoXml = infoXml + "<attribute16>" + "123123123" + "</attribute16>";
        infoXml = infoXml + "<attribute17>" + enterpriseInfo.getRegCode() + "</attribute17>";
        infoXml = infoXml + "<attribute18>" + enterpriseInfo.getEntName() + "</attribute18>";
        infoXml = infoXml + "<attribute19>" + "电子营业执照" + "</attribute19>";
        RegLicense regLicense = ParseLicenseFactory.parse(infoXml, enterpriseInfo).paresLicense();
        infoXml = regLicense.getInfoXml();
        infoXml = infoXml + "<attribute48>" + "编号" + "</attribute48>";
        infoXml = infoXml + "<attribute49>" + "外国企业名称" + "</attribute49>";
        infoXml = infoXml + "<attribute50>" + "外国企业住所" + "</attribute50>";
        infoXml = infoXml + "<attribute51>" + "" + "</attribute51>";
        infoXml = infoXml + "<attribute52>" + phone + "</attribute52>";
        infoXml = infoXml + "<attribute56>" + enterpriseInfo.getIdCardHash() + "</attribute56>";
        infoXml = infoXml + "<attribute41>" + "工商行政管理局" + "</attribute41>";
        infoXml = infoXml + "<attribute42>" + "工商行政管理局" + "</attribute42>";
        infoXml = infoXml + "<attribute43>" + "2020年9月17日" + "</attribute43>";
        infoXml = infoXml + "<attribute44>" + "http://guojiagongs/public/gsgs/service.gov" + "</attribute44>";
        infoXml = infoXml + "<attribute90>" + enterpriseInfo.getTemplateType() + "</attribute90>";
        infoXml = infoXml + "</firm></licence>";
        log.info("注册企业参数：{}", infoXml);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("infoXml", infoXml);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(sendUrl(environment), httpEntity, String.class);
        log.info("响应结果：{}", responseEntity);
        String name =  StringUtils.isBlank(enterpriseInfo.getName()) ?"张某": enterpriseInfo.getName();
        EntInfo entInfo = EntInfo.builder().entName(enterpriseInfo.getEntName()).idCard(enterpriseInfo.getIdCardHash()).name(name).build();
        return Respone.success(entInfo);
    }

    /**
     * 环境地址
     *
     * @param environment
     * @return
     */
    private static String sendUrl(String environment) {
        switch (environment) {
            case "预生产环境":
                return "http://172.18.151.31:9090/enterprise/registerOrChange";
            case "测试环境":
                return "http://192.168.99.31:9090/enterprise/registerOrChange";
            case "开发环境":
                return "http://192.168.100.113:8083/enterprise/registerOrChange";
            default:
                return "http://172.18.151.31:9090/enterprise/registerOrChange";
        }
    }
}