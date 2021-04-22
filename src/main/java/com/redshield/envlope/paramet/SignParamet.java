package com.redshield.envlope.paramet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: SignParamet
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/3/26   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@ApiModel(description= "请求参数信息")
public class SignParamet {
    @ApiModelProperty(value = "证书")
    private String cert;
    @ApiModelProperty(value = "数据")
    private String data;
    @ApiModelProperty(value = "索引号")
    private String keyLable;
    @ApiModelProperty(value = "签名值")
    private String signData;
//    @ApiModelProperty(value = "信封值")
//    private String envData;
    @ApiModelProperty(value = "系统代码")
    private String syscode;
    @ApiModelProperty(value = "base64参数")
    private String base64Data;
}
