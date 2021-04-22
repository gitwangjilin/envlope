package com.redshield.envlope.paramet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: MakeSystemCertParamet
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
@ApiModel(value="签发证书对象")
public class MakeSystemCertParamet {
    @ApiModelProperty(value = "证书")
    private String p10;
    @ApiModelProperty(value = "索引号")
    private String keyLable;
}
