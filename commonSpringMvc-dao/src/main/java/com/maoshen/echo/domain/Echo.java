package com.maoshen.echo.domain;

import com.maoshen.base.entity.BaseEntity;

public class Echo extends BaseEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Echo() {

	}
}
