package com.tsuyu.util;

public class ExtJSFilter {

	private String type;
	private String comparison;
	private String value;
	private String field;
	private String table;
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getComparison() {
		return comparison;
	}
	
	public void setComparison(String comparison) {
		this.comparison = comparison;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
}
