package com.maoshen.echo.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RouteDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Double testDouble;
	private Double testDoubleNext;

	public Double getTestDoubleNext() {
		if(testDoubleNext!=null){
			return new BigDecimal(testDoubleNext).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}else{
			return testDoubleNext;
		}
	}

	public void setTestDoubleNext(Double testDoubleNext) {
		if(testDoubleNext!=null){
			this.testDoubleNext = new BigDecimal(testDoubleNext).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}else{
			this.testDoubleNext = testDoubleNext;
		}
	}

	public Double getTestDouble() {
		if(testDouble!=null){
			return new BigDecimal(testDouble).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}else{
			return testDouble;
		}
	}

	public void setTestDouble(Double testDouble) {
		if(testDouble!=null){
			this.testDouble = new BigDecimal(testDouble).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}else{
			this.testDouble = testDouble;
		}
	}
}
