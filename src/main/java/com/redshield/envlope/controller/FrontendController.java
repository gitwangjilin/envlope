package com.redshield.envlope.controller;

import com.redshield.envlope.constant.EnvironmentSystem;
import com.redshield.envlope.request.BaseRequest;
import com.redshield.envlope.request.RequestHeader;
import com.redshield.envlope.request.qrcode.QrCodeGetterContent;
import com.redshield.envlope.utils.DateUtil;
import dm.jdbc.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: FrontendController
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
@Api(tags = "前置相关业务")
@RestController
@RequestMapping
public class FrontendController {
    @Resource
    EnvironmentSystem environmentSystem;

    @Resource
    RestTemplate restTemplate;

//    @ResponseBody
//    @RequestMapping(value = "/downloadInfo")
//    @ApiOperation(value = "下载信息", httpMethod = "GET", notes = "下载符合条件的Excel",produces = 	MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public ResultBody downloadInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//        String filename = StringUtil.encodeDownloadFileName("DownloadInfo" + DateUtil.yyyyMMdd.format(new Date()) + ".xlsx", userAgent);
//        response.setHeader("Content-disposition", "attachment; filename=" + filename);
//    }
    @ApiOperation(value = "获取登陆二维码",produces = 	MediaType.IMAGE_GIF_VALUE)
    @GetMapping("getQrCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "environment", paramType = "query",
                    allowableValues = "开发环境,测试环境,预生产环境,生产环境"),
            @ApiImplicitParam(name = "itemcode", value = "授权事项码")})
    public void getQrCode(String environment, String itemcode, HttpServletResponse response) {
        String environment1 = getEnvironment(environment);
        Map<String, String> devs = environmentSystem.getDevs();
        String syscode = null;
        String authcode = null;
        String url = null;
        for (String s : devs.keySet()) {
            if (s.equals(environment1)) {
                String s1 = devs.get(s);
                String[] split = s1.split(",");
                syscode = split[0];
                authcode = split[1];
                url = split[2];
            }
        }
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setAuthCode(authcode);
        requestHeader.setSysCode(syscode);
        if (StringUtils.isNoneBlank(itemcode)) {
            requestHeader.setItemCode(itemcode);
        }
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setHeader(requestHeader);
        baseRequest.setContent(new QrCodeGetterContent());
        HttpHeaders httpHeaders = httpHeader();
        HttpEntity<String> httpEntity = new HttpEntity(baseRequest, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        String valueData = "iVBORw0KGgoAAAANSUhEUgAAAJQAAACUCAIAAAD6XpeDAAADDElEQVR42u3bUXLCMAwEUO5/aXqCDiTelQ28fDINTfzSsbRKH4/C8fzneOdnJs9N3ePVez/6gAcPHrwfwXsuHFcXqLGIKwuaeoBWgK9eAzx48ODBy+Dt2rSfoaMB3ChqVr4HHjx48OCdhXdCk9to9uHBgwcPHrzVhneyeJnMgeHBgwcP3rl4jYtrFw7tYLodGhw9VYAHDx68L8NrvAvj86FFhwcPEjx4Y8PPXQ31rmHsEUUQPHjw4MEb6/naw9LGS03tcPljiiB48ODBgxfZwCcHqqcNY9trGLs4ePDgwftxvFRj2PieRmHSLojahR48ePDgwevO83YVI5PBdKN4ST3oS1MFePDgwftxvNPedposKBoPVmO4erlJhwcPHjx4L/e5yeQ7NfCcDKYbAf3SOsODBw8evNvr3FiIRqPdeLAaD1OjaOp3+/DgwYP3vXjtF34mf287mG4AwIMHDx68ObxUsNv4fLK5btx7I5heqkLhwYMHD17khMlNO9UUpxr5VLhRmarDgwcPHrxL87zGcDLVyDeC411hemWqAA8ePHjwxl6qmVyURoA++XBfvnd48ODBg5fYR0f/4SPVwO4aop6wbvDgwYMH71qemQphJ4e3lQ0/VLzUizV48ODBg3cbr93MNproyYA49UDEihp48ODBgxfBa/yylUVpHI2HbzRAgAcPHjx4t2uRVAPb2FNT19AIiycfptzoAR48ePB+Gy/VeJ6wEI2GevIaloojePDgwYN3u8/bFRZPFiONACG1tkt/bfDgwYMHb2y2d3J4veu+Gp/DgwcPHrz7eKn8MzXATJ2b+s7jii948ODBg/fyOidD4ROKo9MC9yVgePDgwYM3tb/GNt4URvtnUoFyHQAePHjw4EU22PZCNM6dDLgb9wUPHjx48Lp4qcJkV1icKqBOaJzf+n548ODBg3csXqMAmQymG+HA0oMFDx48ePA+Aq8xnGwPh0cb8NOadHjw4MH7MrxGUdB++afRaLeb91iRBQ8ePHjwprLTpUKmvcm3Q/Bd58KDBw8evEvHH284VrFWyRPBAAAAAElFTkSuQmCC";
        byte[] decode = this.decode(valueData);
        ByteArrayOutputStream output = new ByteArrayOutputStream();//可以捕获内存缓冲区【生成的图片在缓冲区里面】的数据，将数据装换成字节数组  ,输出流的缓冲区的大小会随着数据的不断写入而自动增加  使用toByteArray()  toString()获得生成字节数组的数据
        try {
            output.write(decode);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ServletOutputStream out = response.getOutputStream();//servlet程序想servletOutputStream或PrintWriter对象中写入数据将被servlet引擎从response中获得
            output.writeTo(out);//将byte数组输出流的全部内容写到指定的输出流参数中
        } catch (IOException e) {
        }
    }

    //    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ApiOperation(value = "123", produces = "image/jpeg")
    @GetMapping("getQrCodae")
    public void getQrCodae(HttpServletResponse response) {
        String valueData = "iVBORw0KGgoAAAANSUhEUgAAAJQAAACUCAIAAAD6XpeDAAADDElEQVR42u3bUXLCMAwEUO5/aXqCDiTelQ28fDINTfzSsbRKH4/C8fzneOdnJs9N3ePVez/6gAcPHrwfwXsuHFcXqLGIKwuaeoBWgK9eAzx48ODBy+Dt2rSfoaMB3ChqVr4HHjx48OCdhXdCk9to9uHBgwcPHrzVhneyeJnMgeHBgwcP3rl4jYtrFw7tYLodGhw9VYAHDx68L8NrvAvj86FFhwcPEjx4Y8PPXQ31rmHsEUUQPHjw4MEb6/naw9LGS03tcPljiiB48ODBgxfZwCcHqqcNY9trGLs4ePDgwftxvFRj2PieRmHSLojahR48ePDgwevO83YVI5PBdKN4ST3oS1MFePDgwftxvNPedposKBoPVmO4erlJhwcPHjx4L/e5yeQ7NfCcDKYbAf3SOsODBw8evNvr3FiIRqPdeLAaD1OjaOp3+/DgwYP3vXjtF34mf287mG4AwIMHDx68ObxUsNv4fLK5btx7I5heqkLhwYMHD17khMlNO9UUpxr5VLhRmarDgwcPHrxL87zGcDLVyDeC411hemWqAA8ePHjwxl6qmVyURoA++XBfvnd48ODBg5fYR0f/4SPVwO4aop6wbvDgwYMH71qemQphJ4e3lQ0/VLzUizV48ODBg3cbr93MNproyYA49UDEihp48ODBgxfBa/yylUVpHI2HbzRAgAcPHjx4t2uRVAPb2FNT19AIiycfptzoAR48ePB+Gy/VeJ6wEI2GevIaloojePDgwYN3u8/bFRZPFiONACG1tkt/bfDgwYMHb2y2d3J4veu+Gp/DgwcPHrz7eKn8MzXATJ2b+s7jii948ODBg/fyOidD4ROKo9MC9yVgePDgwYM3tb/GNt4URvtnUoFyHQAePHjw4EU22PZCNM6dDLgb9wUPHjx48Lp4qcJkV1icKqBOaJzf+n548ODBg3csXqMAmQymG+HA0oMFDx48ePA+Aq8xnGwPh0cb8NOadHjw4MH7MrxGUdB++afRaLeb91iRBQ8ePHjwprLTpUKmvcm3Q/Bd58KDBw8evEvHH284VrFWyRPBAAAAAElFTkSuQmCC";
        byte[] decode = this.decode(valueData);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "image/jpeg");
        try {
            output.write(decode);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
        }
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
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

    private String getEnvironment(String environment) {
        switch (environment) {
            case "开发环境":
                return "dev";
            case "测试环境":
                return "uat";
            case "预生产环境":
                return "ut";
            case "生产环境":
                return "prod";
            default:
                return null;
        }

    }
}
//{
//        "message_header": {
//        "syscode": "{{syscode}}",
//        "authcode": "{{authcode}}",
//        "businesstype": "003",
//        "sign": "sign3",
//        "version": "1"
//        },
//        "message_content": {
//        "qrtype": "1101",
//        "rettype": "1",
//        "opertime": "20190408"
//        }
//        }