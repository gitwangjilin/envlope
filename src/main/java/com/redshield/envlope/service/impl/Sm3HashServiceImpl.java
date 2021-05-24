package com.redshield.envlope.service.impl;

import com.bouncycastle.crypto.digests.SM3Digest;
import com.redshield.envlope.service.Sm3HashService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: Sm3HashServiceImpl
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
@Service
public class Sm3HashServiceImpl implements Sm3HashService {

    /**
     * 身份号转hash
     *
     * @param data
     * @return
     */
    @Override
    public String getSm3Hash(String data) {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] md = new byte[32];
        byte[] msg1 = data.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        return encoder.encode(md);
    }

    public static void main(String[] args) {
//        System.out.println("�~�eh P��*���'���\u001A?s�,���w��");
        String a = "152103199710293320";
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] md = new byte[32];
        byte[] msg1 = a.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        System.out.println(encoder.encode(md));
    }
}
