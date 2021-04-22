package com.redshield.envlope.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("USER_INFO")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID=1L;

    @TableField("ID")
    private Integer id;

    @TableField("SUBJECT_L")
    private String subjectL;

    @TableField("SUBJECT_S")
    private String subjectS;

    @TableField("AREA_CODE")
    private String areaCode;

    @TableField("ORG_CODE")
    private String orgCode;

    @TableField("REG_CODE")
    private String regCode;

    @TableField("ENT_NAME")
    private String entName;

    @TableField("STATE")
    private String state;

    @TableField("NAME")
    private String name;
}
