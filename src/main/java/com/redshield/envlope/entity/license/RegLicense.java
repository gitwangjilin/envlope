package com.redshield.envlope.entity.license;

import com.redshield.envlope.entity.EnterpriseInfo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

@Data
public abstract class RegLicense {
    private EnterpriseInfo enterpriseInfo;
    private String infoXml;
    private String name;
    private String regCap;
    private String dom;
    private String mainMgrLocation;
    private String foundDate;
    private String bizTerm;
    private String mgrScope;
    private static SimpleDateFormat imagetime = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

    public RegLicense() {
    }

    public RegLicense(EnterpriseInfo enterpriseInfo, String infoXml) {
        this.enterpriseInfo = enterpriseInfo;
        this.infoXml = infoXml;
    }

    public abstract void parseLicenseSub(String infoXml, EnterpriseInfo enterpriseInfo);


    public RegLicense paresLicense() {
        parseLicenseSub(infoXml,enterpriseInfo);
        return this;
    }


}
