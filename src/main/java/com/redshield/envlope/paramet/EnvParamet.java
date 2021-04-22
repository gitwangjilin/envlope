package com.redshield.envlope.paramet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: EnvParamet
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
@ApiModel(value="信封对象")
public class EnvParamet {
    @ApiModelProperty(value = "证书")
    private String cert;
    @ApiModelProperty(value = "数据")
    private String data;
    @ApiModelProperty(value = "索引号")
    private String keyLable;
    @ApiModelProperty(value = "信封值")
    private String envData;
}
