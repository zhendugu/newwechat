package org.weixin4j.vo;

import java.util.Date;

public class UserVo {
	private String openid; // 用户的标识，对当前公众号唯一
	private String subscribe; // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private Integer option1;
	private Integer option2;
	private Integer option3;
	private Integer pointTime;
	private Date createTime;
	
	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserVo(String openid, String subscribe, Integer option1, Integer option2, Integer option3,
			Integer pointTime) {
		super();
		this.openid = openid;
		this.subscribe = subscribe;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.pointTime = pointTime;
	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the subscribe
	 */
	public String getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the option1
	 */
	public Integer getOption1() {
		return option1;
	}

	/**
	 * @param option1 the option1 to set
	 */
	public void setOption1(Integer option1) {
		this.option1 = option1;
	}

	/**
	 * @return the option2
	 */
	public Integer getOption2() {
		return option2;
	}

	/**
	 * @param option2 the option2 to set
	 */
	public void setOption2(Integer option2) {
		this.option2 = option2;
	}

	/**
	 * @return the option3
	 */
	public Integer getOption3() {
		return option3;
	}

	/**
	 * @param option3 the option3 to set
	 */
	public void setOption3(Integer option3) {
		this.option3 = option3;
	}

	/**
	 * @return the pointTime
	 */
	public Integer getPointTime() {
		return pointTime;
	}

	/**
	 * @param pointTime the pointTime to set
	 */
	public void setPointTime(Integer pointTime) {
		this.pointTime = pointTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
