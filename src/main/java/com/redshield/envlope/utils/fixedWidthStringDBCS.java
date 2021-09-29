package com.redshield.envlope.utils;

/*************************************************************************
 ******
 * - Copyright (c) 2021 shangzhao.com
 * - File Name: fixedWidthStringDBCS
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
public class fixedWidthStringDBCS {

    public static String fixedWidthStringDBCS(String str, int width, char fill) {
        if (str==null) str = "";
        if (fill < 256) fill = '　';
        StringBuffer sb = new StringBuffer();

        int i=0;
        for ( ; i<str.length() && i<width; i++) {
            int c = str.charAt(i);
            if (c >= 'a' && c<='z') { //ａｚＡＺ　０１９
                c = c + 'ａ' - 'a';
            } else if (c >= 'A' && c<= 'Z') {
                c = c + 'Ａ' - 'A';
            } else if (c >= '0' && c<= '9') {
                c = c + '０' - '0';
            } else if (c < 256) {
                c = fill;
            }

            sb.append((char)c);
        }

        for ( ; i<width; i++) {
            sb.append(fill);
        }

        return sb.toString();
    }



//    我改写的一个简便功能的方法：

    public static String fixedWidthStringDBCS(String str) {
        if (str==null) str = "";
        int width = str.length();
        StringBuffer sb = new StringBuffer();

        int i=0;
        for ( ; i<str.length() && i<width; i++) {
            int c = str.charAt(i);
            if (c >= 'a' && c<='z') { //ａｚＡＺ　０１９
                c = c + 'ａ' - 'a';
            } else if (c >= 'A' && c<= 'Z') {
                c = c + 'Ａ' - 'A';
            } else if (c >= '0' && c<= '9') {
                c = c + '０' - '0';
            }

            sb.append((char)c);

        }


        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("电子营业执照");
        System.out.println(fixedWidthStringDBCS("电子营业执照"));
//        王继林
                String a = "王继林";
                String ca = "王继林";
    }
}
