package com.redshield.envlope.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TradeTypeAll implements Serializable {

	private List<TradeTypeAll> list;

	private Long aId;
	/**
	 * 分类名称
	 */
	private String aName;
	/**
	 * 分类代码
	 */
	private String aCode;

	private Long bId;
	/**
	 * 分类名称
	 */
	private String bName;
	/**
	 * 分类代码
	 */
	private String bCode;

	/**
	 * 上级分类ID
	 */
	private Long bParent;
	/**
	 *上级分类代码
	 */
	private String bParentCode;
	/**
	 *行业分类级别 1 2 3
	 */
	private Integer bTradeIndex;

	private Long cId;
	/**
	 * 分类名称
	 */
	private String cName;
	/**
	 * 分类代码
	 */
	private String cCode;

	/**
	 * 上级分类ID
	 */
	private Long cParent;
	/**
	 *上级分类代码
	 */
	private String cParentCode;
	/**
	 *行业分类级别 1 2 3
	 */
	private Integer cTradeIndex;

}
