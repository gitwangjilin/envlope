package com.redshield.envlope.controller;


import com.redshield.envlope.entity.UserDto;
import com.redshield.envlope.excel.ExcelUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;


/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: InportExcelController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/21   WangJiLin     Create the current class
 *************************************************************************
 ******/
@RestController
@Api(tags = "导入Excel文件")
@Slf4j
public class InportExcelController {
    @Autowired
    private ExcelUtil excelUtil;

    @PostMapping(value = "/uploadExcel", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "用户信息Excel导入数据", notes = "用户信息Excel导入数据", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public String uploadExcel(@ApiParam(value = "用户信息Excel导入数据", required = true) MultipartFile file) throws Exception {
        List<UserDto> dtoList = excelUtil.readExcelFileToDTO(file, UserDto.class);
        int i = 0;
        for (UserDto userDto : dtoList) {

        }
        log.info("总数：" + i);
        log.info("长度：" + dtoList.size());
        //TODO 入库的代码自行补充
        return "导入成功";
    }

    public static void main(String[] args) {
        byte[] encodeBase64 = new byte[0];
        try {
            encodeBase64 = org.apache.commons.codec.binary.Base64.encodeBase64("vkLPwVNCFnY/CGLe5p1RRv9CXy93R7r9RBJsnslRjGQ=".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("vkLPwVNCFnY/CGLe5p1RRv9CXy93R7r9RBJsnslRjGQ=".length());
        System.out.println("RESULT: " + new String(encodeBase64));
        System.out.println("RESULT: " + new String(encodeBase64).length());
    }
}
