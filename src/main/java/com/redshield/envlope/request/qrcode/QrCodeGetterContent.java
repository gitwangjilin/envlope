package com.redshield.envlope.request.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzg
 */
@Data
@NoArgsConstructor
public class QrCodeGetterContent {

    /**
     * 二维码类型
     */
    @JsonProperty("qrtype")
    private String qrType = "1101";

    /**
     * 返回类型
     */
    @JsonProperty("rettype")
    private String retType = "1";

    /**
     * 请求时间
     */
    @JsonProperty("opertime")
    private String operTime = "20190408";

}
