package com.redshield.envlope.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class QueryEnterpriseInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String idCardHash;

    //统一社会信用代码
    private String creditCode;

    //注册号
    private String regNo;

    //企业名称
    private String entName;

    //法人名称
    private String name;

    //企业类型
    private String entType;

    //手机号
    private String phoneNumber;

    //登记机关代码
    private String regCode;

    private Integer state;

    private Date syncDate;

}
