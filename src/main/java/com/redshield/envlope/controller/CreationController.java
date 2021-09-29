package com.redshield.envlope.controller;


import com.redshield.envlope.constant.TypeEnum;
import com.redshield.envlope.entity.EnterpriseInfo;
import com.redshield.envlope.response.Respone;
import com.redshield.envlope.service.CreationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Controller
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
@Api(tags = "创建测试企业")
@RestController
@RequestMapping("add")
public class CreationController {
    @Resource
    CreationService creationService;

    @ApiOperation(value = "A类型企业创建")
    @GetMapping("aType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名(张某)"),
            @ApiImplicitParam(name = "entName", value = "企业名称(测试企业公司)"),
            @ApiImplicitParam(name = "creditCode", value = "统一社会信用代码"),
            @ApiImplicitParam(name = "regCode", value = "注册号"),
            @ApiImplicitParam(name = "idcard", value = "身份证号"),
            @ApiImplicitParam(name = "regCap", value = "注册资本(2000万元)"),
            @ApiImplicitParam(name = "dom", value = "住所(测试住所地址)"),
            @ApiImplicitParam(name = "foundDate", value = "成立日期(当天)格式((2020年02月22日))"),
            @ApiImplicitParam(name = "bizTerm", value = "营业期限(2020年02月22日)"),
            @ApiImplicitParam(name = "termTo", value = "期限至(2038年08月12日)"),
            @ApiImplicitParam(name = "mgrScope", value = "经营范围(企业依法自主选择经营项目，开展经营活动-经营范围)"),
            @ApiImplicitParam(name = "environment", paramType = "query",
                    allowableValues = "开发环境,测试环境,预生产环境"),
            @ApiImplicitParam(name = "entTemplateType", value = "内资公司")
    })
    public Respone aType(TypeEnum type, String name, String entName, String idcard, String regCap, String dom,
                         String foundDate, String bizTerm, String termTo, String creditCode, String regCode,
                         String mgrScope, String environment, String entTemplateType) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode(type.getAreacode());
        enterpriseInfo.setSubjectL(type.getType());
        enterpriseInfo.setSubjectS(type.getType());
        enterpriseInfo.setCreditCode(creditCode);
        enterpriseInfo.setRegCode(regCode);
        enterpriseInfo.setName(name);
        enterpriseInfo.setEntName(entName);
        enterpriseInfo.setIdCardHash(idcard);
        enterpriseInfo.setRegCap(regCap);
        enterpriseInfo.setDom(dom);
        enterpriseInfo.setFoundDate(foundDate);
        enterpriseInfo.setBizTerm(bizTerm);
        enterpriseInfo.setTermTo(termTo);
        enterpriseInfo.setMgrScope(mgrScope);
        enterpriseInfo.setEntTemplateType(entTemplateType);
        if (StringUtils.isBlank(entTemplateType)){
            enterpriseInfo.setEntTemplateType("内资公司");
        }
        enterpriseInfo.setTemplateType("A");
        return creationService.addEnterprise(enterpriseInfo, environment);
//        return null;
    }
    @ApiOperation(value = "B类型企业创建")
    @GetMapping("bType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名(张某)"),
            @ApiImplicitParam(name = "entName", value = "企业名称(测试企业公司)"),
            @ApiImplicitParam(name = "creditCode", value = "统一社会信用代码"),
            @ApiImplicitParam(name = "regCode", value = "注册号"),
            @ApiImplicitParam(name = "idcard", value = "身份证号"),
            @ApiImplicitParam(name = "regFund", value = "注册资金(2000万元)"),
            @ApiImplicitParam(name = "dom", value = "住所(测试住所地址)"),
            @ApiImplicitParam(name = "foundDate", value = "成立日期(当天)"),
            @ApiImplicitParam(name = "mgrTerm", value = "经营期限(2020年02月22日)"),
            @ApiImplicitParam(name = "termTo", value = "期限至(2038年08月12日)"),
            @ApiImplicitParam(name = "mgrScope", value = "经营范围(企业依法自主选择经营项目，开展经营活动-经营范围)"),
            @ApiImplicitParam(name = "environment", paramType = "query",
                    allowableValues = "开发环境,测试环境,预生产环境"),
            @ApiImplicitParam(name = "entTemplateType", value = "内资非公司企业")
    })
    public Respone bType(TypeEnum type, String name, String entName, String idcard, String regFund, String dom,
                        String foundDate, String mgrTerm, String termTo,String creditCode,String regCode,
                        String mgrScope, String environment, String entTemplateType) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode(type.getAreacode());
        enterpriseInfo.setSubjectL(type.getType());
        enterpriseInfo.setSubjectS(type.getType());
        enterpriseInfo.setCreditCode(creditCode);
        enterpriseInfo.setRegCode(regCode);
        enterpriseInfo.setName(name);
        enterpriseInfo.setEntName(entName);
        enterpriseInfo.setIdCardHash(idcard);
        enterpriseInfo.setRegFund(regFund);
        enterpriseInfo.setDom(dom);
        enterpriseInfo.setFoundDate(foundDate);
        enterpriseInfo.setMgrTerm(mgrTerm);
        enterpriseInfo.setTermTo(termTo);
        enterpriseInfo.setMgrScope(mgrScope);
        enterpriseInfo.setEntTemplateType(entTemplateType);
        if (StringUtils.isBlank(entTemplateType)){
            enterpriseInfo.setEntTemplateType("内资非公司企业");
        }
        enterpriseInfo.setTemplateType("B");
        return creationService.addEnterprise(enterpriseInfo, environment);
//        return null;
    }
    @ApiOperation(value = "C类型企业创建")
    @GetMapping("cType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名(张某)"),
            @ApiImplicitParam(name = "entName", value = "企业名称(测试企业公司)"),
            @ApiImplicitParam(name = "creditCode", value = "统一社会信用代码"),
            @ApiImplicitParam(name = "regCode", value = "注册号"),
            @ApiImplicitParam(name = "idcard", value = "身份证号"),
            @ApiImplicitParam(name = "mainMgrLocation", value = "主要经营场所(测试主要经营场所)"),
            @ApiImplicitParam(name = "foundDate", value = "成立日期(当天)"),
            @ApiImplicitParam(name = "partnerTerm", value = "合伙期限(2020年02月22日)"),
            @ApiImplicitParam(name = "termTo", value = "期限至(2038年08月12日)"),
            @ApiImplicitParam(name = "mgrScope", value = "经营范围(企业依法自主选择经营项目，开展经营活动-经营范围)"),
            @ApiImplicitParam(name = "environment", paramType = "query",
                    allowableValues = "开发环境,测试环境,预生产环境"),
            @ApiImplicitParam(name = "entTemplateType", value = "合伙企业")
    })
    public Respone cType(TypeEnum type, String name, String entName, String idcard, String mainMgrLocation,
                        String foundDate, String partnerTerm, String termTo,String creditCode,String regCode,
                        String mgrScope, String environment, String entTemplateType) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setAreaCode(type.getAreacode());
        enterpriseInfo.setSubjectL(type.getType());
        enterpriseInfo.setSubjectS(type.getType());
        enterpriseInfo.setCreditCode(creditCode);
        enterpriseInfo.setRegCode(regCode);
        enterpriseInfo.setName(name);
        enterpriseInfo.setEntName(entName);
        enterpriseInfo.setIdCardHash(idcard);
        enterpriseInfo.setMainMgrLocation(mainMgrLocation);
        enterpriseInfo.setFoundDate(foundDate);
        enterpriseInfo.setPartnerTerm(partnerTerm);
        enterpriseInfo.setTermTo(termTo);
        enterpriseInfo.setMgrScope(mgrScope);
        enterpriseInfo.setEntTemplateType(entTemplateType);
        if (StringUtils.isBlank(entTemplateType)){
            enterpriseInfo.setEntTemplateType("合伙企业");
        }
        enterpriseInfo.setTemplateType("C");
        return creationService.addEnterprise(enterpriseInfo, environment);
    }

}
