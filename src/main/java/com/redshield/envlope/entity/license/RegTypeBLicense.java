package com.redshield.envlope.entity.license;

import com.redshield.envlope.entity.EnterpriseInfo;
import com.redshield.envlope.utils.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: RegTypeALicense
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/7   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
public class RegTypeBLicense extends RegLicense {
    private String infoXml;

    public RegTypeBLicense(EnterpriseInfo enterpriseInfo, String infoXml) {
        super(enterpriseInfo, infoXml);
    }

    @Override
    public void parseLicenseSub(String infoXml, EnterpriseInfo enterpriseInfo) {
        //法人名称
        String name = StringUtils.isBlank(enterpriseInfo.getName()) ? "张某" : enterpriseInfo.getName();
        //注册资金
        String reegFund = StringUtils.isBlank(enterpriseInfo.getRegFund()) ? "2000万元" : enterpriseInfo.getRegFund();
        //住所
        String dom = StringUtils.isBlank(enterpriseInfo.getDom()) ? "测试住所地址" : enterpriseInfo.getDom();
        //成立日期
        String foundDate = StringUtils.isBlank(enterpriseInfo.getFoundDate()) ? DateUtil.getDate(new Date()) : enterpriseInfo.getFoundDate();
        //经营期限
        String mgrTerm = StringUtils.isBlank(enterpriseInfo.getMgrTerm()) ? "2020年02月22日" : enterpriseInfo.getMgrTerm();
        //期限至
        String termTo = StringUtils.isBlank(enterpriseInfo.getBizTerm()) ? "2038年08月12日" : enterpriseInfo.getBizTerm();
        //经营范围
        String mgrScope = StringUtils.isBlank(enterpriseInfo.getMgrScope()) ? "企业依法自主选择经营项目，开展经营活动-经营范围" : enterpriseInfo.getMgrScope();
        infoXml = infoXml + "<attribute20>" + name + "</attribute20>";
        infoXml = infoXml + "<attribute26>" + reegFund + "</attribute26>";
        infoXml = infoXml + "<attribute29>" + dom + "</attribute29>";
        infoXml = infoXml + "<attribute33>" + foundDate + "</attribute33>";
        infoXml = infoXml + "<attribute36>" + mgrTerm + "</attribute36>";
        infoXml = infoXml + "<attribute38>" + termTo + "</attribute38>";
        this.infoXml = infoXml + "<attribute39>" + mgrScope + "</attribute39>";
    }
}