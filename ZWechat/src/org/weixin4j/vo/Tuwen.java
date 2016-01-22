package org.weixin4j.vo;

import com.google.gson.annotations.SerializedName;

public class Tuwen {
	@SerializedName(value = "type")
	private String type;
	private int offset;
	private int count;
	
	public Tuwen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tuwen(String type, int i, int j) {
		super();
		this.type = type;
		this.offset = i;
		this.count = j;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	
}
