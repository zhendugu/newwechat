package org.weixin4j.vo;

import java.util.Date;

/**
 * 为了方便存取数据库中的access_token的临时对象
 * 
 * @author huizhitiancheng
 *
 */

public class Tokens {
	private String appId;
	private String secret;
	private String access_token;
	private Date createTime;

	public Tokens() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tokens(String appId, String secret, String access_token, Date createTime) {
		super();
		this.appId = appId;
		this.secret = secret;
		this.access_token = access_token;
		this.createTime = createTime;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret
	 *            the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token
	 *            the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
