package com.redshield.envlope.utils;

import com.redshield.envlope.response.TradeTypeAll;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: PoiUtils
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/9/29   WangJiLin     Create the current class
 *************************************************************************
 ******/
public class PoiUtils {

    // 将数据导出成excel文件
    public static ResponseEntity<byte[]> exportUser2Excel(List<TradeTypeAll> tradeTypeAlls) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("授权事项信息");
            //3.2设置文档管理员
            dsi.setManager("王继林");
            //3.3设置组织机构
            dsi.setCompany("北京商兆科技有限公司");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("授权事项信息");
            //4.2.设置文档标题
            si.setTitle("授权事项信息");
            //4.3 设置文档作者
            si.setAuthor("王继林");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("授权事项信息");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 20 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            sheet.setColumnWidth(2, 30 * 256);
            sheet.setColumnWidth(3, 15 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("一级授权事项");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("二级授权事项");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("三级授权事项");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("授权事项编码");
            cell3.setCellStyle(headerStyle);
            //6.装数据
            for (int i = 0; i < tradeTypeAlls.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                TradeTypeAll tradeType= tradeTypeAlls.get(i);
                row.createCell(0).setCellValue(tradeType.getAName());
                row.createCell(1).setCellValue(tradeType.getBName());
                row.createCell(2).setCellValue(tradeType.getCName());
                row.createCell(3).setCellValue(tradeType.getCCode()+"00");
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("授权事项信息.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }
}
