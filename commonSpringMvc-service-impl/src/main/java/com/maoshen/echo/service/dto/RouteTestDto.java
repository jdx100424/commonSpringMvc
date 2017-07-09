package com.maoshen.echo.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RouteTestDto implements Serializable{
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
	
	public Double testDouble;

	public Double getTestDouble() {
		return testDouble;
	}

	public void setTestDouble(Double testDouble) {
		this.testDouble = testDouble;
	}
}
