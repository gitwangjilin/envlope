package com.redshield.envlope.paramet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: P10Paramet
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/1   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@ApiModel(value="P10需要参数")
public class CertParamet {
    @ApiModelProperty(value = "索引号")
    private String keyLable;
    @ApiModelProperty(value = "P10参数")
    List<SubjectsList> subjectsLists;
}
