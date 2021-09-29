package com.redshield.envlope.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: EnvironmentSystem
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/9/7   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@Configuration
@ConfigurationProperties(prefix = "environment")
public class EnvironmentSystem {
    private Map<String,String> devs;
}