package org.weixin4j.qna.vo;

public class Candidate {

	private Integer id;
	private String name;			//	1.项目名称
	private String introduce;		//	2.一句话说明（项目解决问题，实现价值的高度概括）
	private String logoUrl;			//	3.项目LOGO
	private String financing;		//	4.融资情况：XX轮，领投方为XXX
	private String member;			//	5.团队成员简介：
	private String mode;			//	6.项目模式简介：
	private String productUrl;		//	7.产品/官网链接
	private String prospectusUrl;	//	8.商业计划书附件——跳转到官网。
	private Integer vote;			//	9.票数
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidate(Integer id, String name, String introduce, String logoUrl, String financing, String member,
			String mode, String productUrl, String prospectusUrl, Integer vote) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.logoUrl = logoUrl;
		this.financing = financing;
		this.member = member;
		this.mode = mode;
		this.productUrl = productUrl;
		this.prospectusUrl = prospectusUrl;
		this.vote = vote;
	}
	
	public Candidate(String name, String introduce, String logoUrl, String financing, String member,
			String mode, String productUrl, String prospectusUrl, Integer vote) {
		super();
		this.name = name;
		this.introduce = introduce;
		this.logoUrl = logoUrl;
		this.financing = financing;
		this.member = member;
		this.mode = mode;
		this.productUrl = productUrl;
		this.prospectusUrl = prospectusUrl;
		this.vote = vote;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the financing
	 */
	public String getFinancing() {
		return financing;
	}

	/**
	 * @param financing the financing to set
	 */
	public void setFinancing(String financing) {
		this.financing = financing;
	}

	/**
	 * @return the member
	 */
	public String getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(String member) {
		this.member = member;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the productUrl
	 */
	public String getProductUrl() {
		return productUrl;
	}

	/**
	 * @param productUrl the productUrl to set
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	/**
	 * @return the prospectusUrl
	 */
	public String getProspectusUrl() {
		return prospectusUrl;
	}

	/**
	 * @param prospectusUrl the prospectusUrl to set
	 */
	public void setProspectusUrl(String prospectusUrl) {
		this.prospectusUrl = prospectusUrl;
	}

	/**
	 * @return the vote
	 */
	public Integer getVote() {
		return vote;
	}

	/**
	 * @param vote the vote to set
	 */
	public void setVote(Integer vote) {
		this.vote = vote;
	}

	
}
