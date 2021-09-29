package com.redshield.envlope.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: Response
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/6/21   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respone {

    private String code;
    private String msg;
    private Object data;


    public Respone(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Respone success(Object idCardAndOrgCode) {
        Respone response = new Respone("0", "创建成功！");
        response.setData(idCardAndOrgCode);
        return response;
    }

    public static Respone error(String code, String message, String val) {
        Respone response = new Respone(code, message);
        response.setData("身份证号有问题请联系省重新推送！");
        return response;
    }

    public static Respone error(String code, String message) {
        Respone response = new Respone(code, message);
        response.setData("重推失败,身份证号有问题请联系省重新推送！");
        return response;
    }
    public static Respone error() {
        Respone response = new Respone("99999", "总局暂无此数据请联系省里重新推送！");
        return response;
    }
    public static Respone error(String msg) {
        Respone response = new Respone("99999", msg);
        return response;
    }
//    public static Respone error(String code, String message, String val) {
//        Respone response = new Respone(code, message);
//        response.setData("身份证号有问题请联系省重新推送！");
//        response.setLength("数据库中有 " + val + "条数据！");
//        return response;
//    }
}