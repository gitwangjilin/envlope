package com.redshield.envlope.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*******************************************************************************
 * - Copyright (c)  2018  gla.com
 *   @author Seven Liu
 * - File Name: BaseRequest
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
public class BaseRequest<T> implements Serializable {

    @JsonProperty("message_header")
    RequestHeader header;

    @JsonProperty("message_content")
    T content;
}
