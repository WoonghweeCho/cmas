package com.dwenc.cmas.common.org.domain;

import java.math.BigDecimal;

public class OrgInfo {

	/**
	 * 조직코드
	 */
	private String orgCd;
	/**
	 * 조직명
	 */
	private String orgNm;
	/**
	 * 조직레벨
	 */
	private BigDecimal orgLvl;
	/**
	 * 상위조직코드
	 */
	private String hgrOrgCd;
	/**
	 * 상위조직명
	 */
	private String hgrOrgNm;
	/**
	 * 사용여부
	 */
	private String useYn;
	/**
	 * 본부코드
	 */	
	private String hdofcOrgCd;

	/**
	* oggCd
	*/
	private String oggCd;

	/**
	* oggTime
	*/
	private String oggTime;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public BigDecimal getOrgLvl() {
		return orgLvl;
	}

	public void setOrgLvl(BigDecimal orgLvl) {
		this.orgLvl = orgLvl;
	}

	public String getHgrOrgCd() {
		return hgrOrgCd;
	}

	public void setHgrOrgCd(String hgrOrgCd) {
		this.hgrOrgCd = hgrOrgCd;
	}

	public String getHgrOrgNm() {
		return hgrOrgNm;
	}

	public void setHgrOrgNm(String hgrOrgNm) {
		this.hgrOrgNm = hgrOrgNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getHdofcOrgCd() {
		return hdofcOrgCd;
	}

	public void setHdofcOrgCd(String hdofcOrgCd) {
		this.hdofcOrgCd = hdofcOrgCd;
	}



	/**
	* oggCd getter
	* @return oggCd
	*/
	public String getOggCd() {
		return oggCd;
	}

	/**
	* oggCd setter
	* @param oggCd
	*/
	public void setOggCd(String oggCd) {
		this.oggCd = oggCd;
	}

	/**
	* oggTime getter
	* @return oggTime
	*/
	public String getOggTime() {
		return oggTime;
	}

	/**
	* oggTime setter
	* @param oggTime
	*/
	public void setOggTime(String oggTime) {
		this.oggTime = oggTime;
	}
}
