package com.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Attribute", description="Details about a particular attribute")
public class Attribute {
	@ApiModelProperty(value="name of attribute")
	String name;
	@ApiModelProperty(value="code of attribute")
	String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
