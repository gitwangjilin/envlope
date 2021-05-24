package com.redshield.envlope.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: TestController
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/5/10   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Api(tags = "image")
@Slf4j
@RestController
@RequestMapping
public class TestController {

//
//    @ApiOperation(value = "image")
//    @PostMapping("image")
//    public String image() {
//        ImgRequest imgRequest = new ImgRequest();
//        imgRequest.setAppCode("105");
//        imgRequest.setAppId("105");
//        imgRequest.setAreaCode("310000");
//        imgRequest.setEntity("MIIFODCCBN+gAwIBAgIQMQAAICEFEBFTBAAAAABQWDAKBggqgRzPVQGDdTA2MTQwMgYDVQQKDCvlm73lrrbluILlnLrnm5HnnaPnrqHnkIbmgLvlsYDkv6Hmga/kuK3lv4MgMB4XDTIxMDUxMDAzNTMwNFoXDTI2MDExMDAzNTMwNFowbzELMAkGA1UEBhMCQ04xLzAtBgNVBAoMJuWogea1pijkuIrmtbcp5oqV6LWE566h55CG5pyJ6ZmQ5YWs5Y+4MS8wLQYDVQQDDCblqIHmtaYo5LiK5rW3KeaKlei1hOeuoeeQhuaciemZkOWFrOWPuDBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABIOqO4LaxPc9G+6He/W23cFNy1kX4uKiXcb9E53JRKA0q1nG1W+KQBlMUSGRzB+Ws8ZJr4VTvcSy3HklAwfiQNWjggOUMIIDkDAbBgUGA4gQDQQSOTEzMTAxMThNQTFKTDZVTjZMMBgGBQYDiBARBA8zMTAxMTgwMDMyNTA4NzIwLwYFBgOIEBIEJuWogea1pijkuIrmtbcp5oqV6LWE566h55CG5pyJ6ZmQ5YWs5Y+4MCQGBQYDiBATBBvpooTnlJ/kuqfnjq/looPokKXkuJrmiafnhacwDwYFBgOIEBQEBue9l+aYjjANBgUGA4gQGQQEMzAwMDBGBgUGA4gQHQQ95LiK5rW35biC6Z2S5rWm5Yy65Y2O5paw6ZWH5Y2O6IW+6LevMTI4OOWPtzHluaIy5bGCSOWMujIyOeWupDAaBgUGA4gQIQQRMjAxNuW5tDAz5pyIMDjml6UwGgYFBgOIECMEETIwMTblubQwM+aciDA45pelMBoGBQYDiBAmBBEyMDI25bm0MDPmnIgwN+aXpTCCARkGBQYDiBAnBIIBDuaKlei1hOeuoeeQhu+8jOWunuS4muaKlei1hO+8jOaKlei1hOWSqOivou+8jOS8geS4mueuoeeQhuWSqOivou+8jOi0ouWKoeWSqOivou+8iOS4jeW+l+S7juS6i+S7o+eQhuiusOi0puS4muWKoe+8ie+8jOWVhuWKoeS/oeaBr+WSqOivou+8jOiuoeeul+acuue9kee7nOS4k+S4mumihuWfn+WGheeahOaKgOacr+WSqOivouOAguOAkOS+neazlemhu+e7j+aJueWHhueahOmhueebru+8jOe7j+ebuOWFs+mDqOmXqOaJueWHhuWQjuaWueWPr+W8gOWxlee7j+iQpea0u+WKqOOAkTAnBgUGA4gQKgQe6Z2S5rWm5Yy65biC5Zy655uR552j566h55CG5bGAMBoGBQYDiBArBBEyMDIw5bm0MTDmnIgxMOaXpTAtBgUGA4gQLAQk5rWL6K+V5LyB5Lia5L+h55So5L+h5oGv5YWs56S6572R5Z2AMB4GBQYDiBAtBBXlt6XllYbooYzmlL/nrqHnkIblsYAwDQYFBgOIEC4EBFYyLjAwFQYFBgOIEDAEDOa1i+ivlee8luWPtzAhBgUGA4gQMQQY5rWL6K+V5aSW5Zu95LyB5Lia5ZCN56ewMCEGBQYDiBAyBBjmtYvor5XlpJblm73kvIHkuJrkvY/miYAwCgYFBgOIEFoEAUEwCgYFBgOIEFsEATEwDwYFBgOIEF0EBjMxMDAwMDAKBggqgRzPVQGDdQNHADBEAiADRTSZhNykRM5X9+oRsGyAqpB9CB2jmUeYhPADhaa5oQIgPSsz2cGUmmsZrlcjKlscSgbw7Ja5j0ko8GyZa3Y/xio=");
//        imgRequest.setOper("罗明");
//        imgRequest.setRole("0");
//        imgRequest.setStat("1");
//        imgRequest.setDate(new Date());
//        ImgDTO img = new LicImgServiceImpl().getImg(imgRequest);
//        return img.getImg();
//    }
}
