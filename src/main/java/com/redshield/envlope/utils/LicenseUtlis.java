package com.redshield.envlope.utils;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: LicenseUtlis
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/4/6   WangJiLin     Create the current class
 *************************************************************************
 ******/
public class LicenseUtlis {

    /**
     * 统一社会信用代码
     *
     * @return
     */
    public static String getCreditCode() {
        return "ZZJGD" + System.currentTimeMillis();
    }

    /**
     * 注册号
     *
     * @return
     */
    public static String getRegCode() {
        return "44"+System.currentTimeMillis();
    }
    public static String getAreaCode(String areaCode){
        return areaCode.substring(0,2);

    }

    public static void main(String[] args) {
        System.out.println(LicenseUtlis.getAreaCode("101111"));
    }
}
