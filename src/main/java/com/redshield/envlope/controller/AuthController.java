package com.redshield.envlope.controller;

import com.redshield.envlope.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: AuthController
 * - @Author: WangJiLIn
 * - Description:
 * 接口描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/6   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "查询验证库")
@RestController
@RequestMapping
public class AuthController {

    @Autowired
    AuthService authService;


    @GetMapping("queryIdCard")
    @ApiOperation(value = "查询系统-身份证查企业信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "idCardHash", value = "身份证号", dataType = "String", required = true),
            }
    )
    public String queryIdCardHash(String idCardHash) {
        return authService.getEntInfo(idCardHash);
    }

    @GetMapping("verifySn")
    @ApiOperation(value = "验证系统-有效执照")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "licenseSn", value = "sn", dataType = "String", required = true),
            }
    )
    public String verifySn(String licenseSn) {
        return authService.verifySn(licenseSn);
    }

}
