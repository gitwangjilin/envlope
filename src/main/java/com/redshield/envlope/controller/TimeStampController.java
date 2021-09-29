package com.redshield.envlope.controller;

import com.redshield.envlope.entity.TimeServviceRequest;
import com.redshield.envlope.paramet.SignParamet;
import com.redshield.envlope.response.TimeRespone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: TimeStampController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/7/15   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Slf4j
@RestController
@RequestMapping
public class TimeStampController {
    @PostMapping("tsa/api/sign/sm2")
    public TimeRespone sm3Hash(@RequestBody TimeServviceRequest timeServviceRequest) {
        System.out.println("feign调用");
        return TimeRespone.success();
    }
}
