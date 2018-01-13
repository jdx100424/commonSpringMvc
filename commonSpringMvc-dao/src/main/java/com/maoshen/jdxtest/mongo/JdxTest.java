package com.maoshen.jdxtest.mongo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="jdxTest")
public class JdxTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field(value="id")
	private String id;
	@Field(value="name")
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
