package com.redshield.envlope.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: EnterpriseInfo
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
@Data
@ApiModel("注册企业对象")
public class EnterpriseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "所属区域代码")
    private String areaCode;

    private String businessId;

    private String businessIdBase;

    /*****变更次数****/
    private Integer changeCount = 0;

    private String dataSource;

    /*****统一社会信用代码****/
    private String creditCode;

    /*****组织机构代码证号****/
    private String orgCode;

    /*****税务登记证号****/
    private String taxRegNo;

    /*****注册号****/
    private String regNo;

    /****企业(机构)名称*****/
    private String entName;

    /*****类型***/
    private String entType;

    /*****法定代表人****/
    private String name;

    /*****执行事务合伙人****/
    private String partner;

    /*****投资人****/
    private String investor;

    /*****经营者****/
    private String manager;

    /*****负责人****/
    private String leader;

    /*****注册资本****/
    private String regCap;

    /*****注册资金****/
    private String regFund;

    /*****成员出资总额****/
    private String membersCap;

    /*****组成形式****/
    private String comFrom;

    /*****住所****/
    private String dom;

    /*****主要经营场所****/
    private String mainMgrLocation;

    /*****经营场所****/
    private String mgrLocation;

    /*****营业场所****/
    private String bizLocation;

    /*****成立日期****/
    private String foundDate;

    /*****开业日期****/
    private String esDate;

    /*****营业期限****/
    private String bizTerm;

    /*****经营期限****/
    private String mgrTerm;

    /*****合伙期限****/
    private String partnerTerm;

    /*****期限至****/
    private String termTo;

    /*****经营范围****/
    private String mgrScope;

    /*****业务范围****/
    private String bizScope;

    /*****二维码****/
    private String qrCode;

    /*****登记机关****/
    private String regName;

    /*****登记机关代码****/
    private String regCode;

    private String regShowName;

    /*****登记日期****/
    private String regDate;

    /******企业信用信息公示网址***/
    private String pubUrl;

    /******签发机构***/
    private String issuer;

    /******电子营业执照版本***/
    private String version;

    /******电子营业执照编号***/
    private String licenceNo;

    /******编号***/
    private String serialNumber;

    /******外国企业名称***/
    private String parentName;

    /******外国企业住所***/
    private String parentAddress;

    private String parentRegNo;

    /*****注册时手机号*****/
    private String phoneNumber;

    private String multiCertification;

    private String shortName;

    /******身份证密文***/
    private String idCardCiphertext;

    /******身份证HASH***/
    private String idCardHash;

    /***模板版式(模板类型) ***/
    private String templateType;

    /***企业类型 ***/
    private String entTemplateType;

    /******
     * 内资公司	1
     * 外资公司	2
     * 内资非公司企业	3
     * 合伙企业	4
     * 外商投资合伙企业	5
     * 农民专业合作社法人	6
     * 个人独资企业	7
     * 个体工商户	8
     * 分公司	9
     * 外资分公司	10
     * 内资非公司企业分支机构及非法人企业	11
     * 合伙企业分支机构	12
     * 外商投资合伙企业分支机构	13
     * 个人独资企业分支机构	14
     * 农民专业合作社分支机构	15
     */
    private Integer licenceType;

    private Integer orgId;

    private Integer orgType;

    private Integer pdfSignFlag = 0;

    /**
     * 主题身份代码
     */
    private String priPid;

    private Date registerTime;

    private String reqBusinessType;

    /**
     * 原来的：0有效 , 1吊销, 2注销, 4注销
     * 新的：1存续、2吊销、4注销、5撤销、6迁出
     */
    private Integer revokeFlag;

    private Integer serialNumbe = 0;

    private String subjectL;

    private String subjectS;

    /*****注册系统机构代码****/
    private String sysCode;

    /****使用模板ID***/
    private Integer templateId;

    /****上次修改时间***/
    private Date lastModifyTime;



}
