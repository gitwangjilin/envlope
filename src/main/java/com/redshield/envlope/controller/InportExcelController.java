package com.redshield.envlope.controller;


import com.redshield.envlope.entity.UserDto;
import com.redshield.envlope.excel.ExcelUtil;
import com.redshield.envlope.service.InportExcelService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    @Resource
    private ExcelUtil excelUtil;

    @Resource
    InportExcelService inportExcelService;

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

    @GetMapping("tradeType")
    @ApiOperation(value = "同步授权树")
    public String tradeType() {
        return inportExcelService.tradeType();
    }
//    SwaggerBootstrapUiDownload.txt.crdownload

    @GetMapping("tradeTypeAll")
    @ApiOperation(value = "获取授权树", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> tradeTypeAll() {
        ResponseEntity<byte[]> responseEntity = inportExcelService.tradeTypeAll();
        return responseEntity;
    }

}
