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
public class TimeRespone {

    private String status;
    private String message;
    private String tsa_base64;


    public TimeRespone(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static TimeRespone success( ) {
        TimeRespone response = new TimeRespone("0", "创建成功！");
        response.setTsa_base64("MIIDogYJKoZIhvcNAQcCoIIDkzCCA48CAQMxDjAMBggqgRzPVQGDEQUAMIIBjAYLKoZIhvcNAQkQAQSgggF7BIIBdzCCAXMCAQEGBCoDBAEwMDAMBggqgRzPVQGDEQUABCCCG1UtQ1CVSAmtzog+ONLF1PL2MxqVBrCPiEYur3h+rwJAoNVxkwrPkGSlpttgVhxOTvdz4WfHMltXS3ArH9q50wqt4z2y/aIAMlsOAllgXapWaaqfzJOiZZIBXxPwXkWySBgTMjAyMTA3MTUwNjA1NDAuNzMzWjAHgAECgQIBkAEB/wIEMyXmXKB2pHQwcjELMAkGA1UEBhMCQ04xDzANBgNVBAcMBuWMl+S6rDEhMB8GA1UECgwY5biC5Zy655uR552j566h55CG5oC75bGAMRUwEwYDVQQLDAzkv6Hmga/kuK3lv4MxGDAWBgNVBAMMD+aXtumXtOaIs+acjeWKoaFVMFMGA1UdFQRMMEoxEzARBgNVBAMMCm5tZGMuYWMuY24xDDAKBgNVBAoMA05JTTELMAkGA1UEBwwCQkoxCzAJBgNVBAgMAkNOMQswCQYDVQQGEwJDTjGCAegwggHkAgEBMEowPjE8MDoGA1UECgwz5Lit5Y2O5Lq65rCR5YWx5ZKM5Zu95Zu95a625bel5ZWG6KGM5pS/566h55CG5oC75bGAAgggIQUQAAABQzAMBggqgRzPVQGDEQUAoIIBLzAaBgkqhkiG9w0BCQMxDQYLKoZIhvcNAQkQAQQwHAYJKoZIhvcNAQkFMQ8XDTIxMDcxNTA2MDU0MFowKQYJKoZIhvcNAQk0MRwwGjAMBggqgRzPVQGDEQUAoQoGCCqBHM9VAYN1MC8GCSqGSIb3DQEJBDEiBCDLXy+ydIhklDdhfxSA1hvyd5BMQ/AetI+p2vhiDux4EjCBlgYLKoZIhvcNAQkQAi8xgYYwgYMwgYAwfjAKBggqgRzPVQGDEQQg5a+8jzuc4PFf0Tl9YBDbepJ7t53CYo2zgiSv07I7ryEwTjBCpEAwPjE8MDoGA1UECgwz5Lit5Y2O5Lq65rCR5YWx5ZKM5Zu95Zu95a625bel5ZWG6KGM5pS/566h55CG5oC75bGAAgggIQUQAAABQzAKBggqgRzPVQGDdQRGMEQCIJ91XjMLw03E1C46BD1oV85QGhCierpbz03T9ZO6LpdeAiAmkwQD332bUMV/IUedDQbRpfuZzkUlvs1ihucb4Xkh5w==");
        return response;
    }
}