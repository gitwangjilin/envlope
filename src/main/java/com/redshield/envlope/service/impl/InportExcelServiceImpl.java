package com.redshield.envlope.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redshield.envlope.mapper.app.TradeTypeMapper;
import com.redshield.envlope.request.BaseRequest;
import com.redshield.envlope.request.RequestHeader;
import com.redshield.envlope.request.qrcode.QrCodeGetterContent;
import com.redshield.envlope.response.TradeType;
import com.redshield.envlope.response.TradeTypeAll;
import com.redshield.envlope.service.InportExcelService;
import com.redshield.envlope.utils.PoiUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: InportExcelServiceImpl
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/9/26   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Service
public class InportExcelServiceImpl implements InportExcelService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    TradeTypeMapper tradeTypeMapper;
    @Override
    public String tradeType() {

        HttpHeaders httpHeaders = httpHeader();
        String requestTradeType = "{\n" +
                "\t\"message_header\": {\n" +
                "\t\t\"businesstype\": \"901\",\n" +
                "\t\t\"version\": \"1\",\n" +
                "\t\t\"sign\": \"MEUCIQCqe9X1ldhp4BJR/O+0SIhWbwXrFnwueFhWyCErjTco3gIgauAXVZkmn0dyr24dC2T41En0\\niuHZkMWSs2XRVsI23ns=\",\n" +
                "\t\t\"syscode\": \"SAIC_FRONT_100022\",\n" +
                "\t\t\"bsuniqueid\": \"100010100022\",\n" +
                "\t\t\"authcode\": \"2aed89b1ac2c44d9aa9ecfd93a911624\"\n" +
                "\t},\n" +
                "\t\"message_content\": {\n" +
                "\t\t\"opertime\": null\n" +
                "\t}\n" +
                "}";
        List<TradeType> all = tradeTypeMapper.findAll();
        System.out.println(all);
        HttpEntity<String> httpEntity = new HttpEntity(requestTradeType, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://zzyz.gsxt.gov.cn/authenticationService/httpserver.do", httpEntity, String.class);
        String masgees = responseEntity.getBody();
        String tradeType = null;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(masgees);
            tradeType = jsonNode.path("message_content").path("tradeType").toString();
        } catch (IOException e) {
        }

        List<TradeType> tradeTypes = JSON.parseArray(tradeType, TradeType.class);
        if(tradeTypes.size()>0){
            tradeTypeMapper.delete();
        }
        for (TradeType type : tradeTypes) {
            tradeTypeMapper.insert(type);
        }
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<byte[]> tradeTypeAll() {
        String s = tradeType();
        List<TradeTypeAll> all = tradeTypeMapper.find();
        return PoiUtils.exportUser2Excel(all);
    }

    /**
     * 设置请求header
     *
     * @return
     */
    private HttpHeaders httpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        ArrayList<MediaType> acceptList = new ArrayList<>(1);
        acceptList.add(MediaType.APPLICATION_JSON);
        // 设置contentType
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13";
        httpHeaders.set(httpHeaders.USER_AGENT, userAgent);
        return httpHeaders;
    }
}
