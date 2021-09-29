package com.redshield.envlope.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TradeType implements Serializable {

	private List<TradeType> list;

	private Long id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类代码
	 */
	private String code;
	/**
	 * 上级分类ID
	 */
	private Long parent;
	/**
	 *上级分类代码
	 */
	private String parentCode;
	/**
	 *行业分类级别 1 2 3
	 */
	private Integer tradeIndex;

}
