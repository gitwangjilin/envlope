package com.redshield.envlope.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/*******************************************************************************
 * - Copyright (c)  2018  gla.com
 *   @author Seven Liu
 * - File Name: RequestHeader
 * - @Author: Seven Liu
 * - Description:
 *
 *
 * - History:
 * Date         Author          Modification
 * 2019-03-18    Seven Liu    Create the current class
 *******************************************************************************/
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeader {

    @JsonProperty("syscode")
    private String sysCode;

    @JsonProperty("authcode")
    private String authCode;

    @JsonProperty("itemcode")
    private String itemCode;

    @JsonProperty("businesstype")
    private String businessType = "003";

    @JsonProperty("bsuniqueid")
    private String bsuniqueid;

    @JsonProperty("sign")
    private String sign = "sign3";

    @JsonProperty("version")
    private String version = "1";


}
