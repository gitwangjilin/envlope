package com.redshield.envlope.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixian
 * @since 2019-12-17
 */
@Data
public class Effective implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String areaCode;

    private Date createTime;

    //统一社会信用代码
    private String creditCode;

    //注册号
    private String regNo;

    //企业类型
    private String entType;

    private Clob licenceEntity;

    //企业名称
    private String entName;

    //执照来源0:APP  1:支付宝  2:微信
    private String licenceSource;

    //卡片载体电子执照=0，软电子执照=1，手机载体电子执照=2
    private Integer licenceType;

    private String licenceSn;

    //失效原因
    private String reason;

    //电子执照签发时间
    private Date signTime;

    //签发时间，用字符串记录，比如格式为：2017-05-01，记录为20170501
    private String licenceSignTimeStr;

    //电子执照状态
    private String state;

    //更新时间
    private Date updateTime;

}
