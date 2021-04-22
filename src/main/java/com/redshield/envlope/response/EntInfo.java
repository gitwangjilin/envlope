package com.redshield.envlope.response;

import lombok.Builder;
import lombok.Data;

import java.util.regex.PatternSyntaxException;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: respone
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/9   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@Builder
public class EntInfo {
    private String name;
    private String idCard;
    private String entName;
}
