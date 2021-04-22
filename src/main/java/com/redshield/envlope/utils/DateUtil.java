package com.redshield.envlope.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*************************************************************************
 ******
 * - Copyright (c) 2020 redshield.com
 * - File Name: DateUtil
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2020/12/11   WangJiLin     Create the current class
 *************************************************************************
 ******/
public class DateUtil {

    private static SimpleDateFormat operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static SimpleDateFormat date = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * 验证响应时间 格式：1607594670000
     * 响应：时间格式 2020-12-31 10:07:18
     *
     * @param time
     * @return
     */
    public static String getOperTime(String time) {
        if (StringUtils.isEmpty(time)) {
            Date date = new Date();
            return operTime.format(date.getTime());
        }
        return operTime.format(Long.valueOf(time));
    }

    /**
     * 验证响应时间 格式：20201230173548
     * 响应：时间格式 2020-12-31 10:07:18
     *
     * @param time
     * @return
     */
    public static String getDate(String time) {
        try {
            if (StringUtils.isEmpty(time)) {
                Date date = new Date();
                return operTime.format(date.getTime());
            }
            Date date = sdf.parse(time);
            return operTime.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 前置系统返回当前系统时间
     * 响应：时间格式 2020-12-31 10:07:18
     *
     * @param time
     * @return
     */
    public static String getFrontendPresentDate(Date time) {
        if (StringUtils.isEmpty(time)) {
            Date date = new Date();
            return operTime.format(date.getTime());
        }
        return operTime.format(time);
    }

    /**
     * 生成图片时间
     * 响应：时间格式：2020年12月31日10时27分23秒
     *
     * @param time
     * @return
     */
    public static String getDate(Date time) {
        if (StringUtils.isEmpty(time)) {
            Date date = new Date();
            return operTime.format(date.getTime());
        }
        return date.format(time);
    }

}