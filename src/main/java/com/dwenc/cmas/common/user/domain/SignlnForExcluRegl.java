package com.dwenc.cmas.common.user.domain;

/**
 * 결재자 Domain
 * @author joonseokko
 *
 */
public class SignlnForExcluRegl {
	/**
	 * 결재순번
	 */
	private String signSeq;

	/**
	 * 결재유형 코드
	 */
	private String signTpCd;

	/**
	 * 결재자 부서 코드
	 */
	private String apperOrgCd;

	/**
	 * 결재자 부서 이름
	 */
	private String apperOrgNm;

	/**
	 * 결재자 ID
	 */
	private String signUserId;

	/**
	 * 결재자 이름
	 */
	private String signUserNm;

	/**
	* isNeedDirector
	*/
	private String isNeedDirector;

	public String getSignSeq() {
		return signSeq;
	}

	public void setSignSeq(String signSeq) {
		this.signSeq = signSeq;
	}

	public String getSignTpCd() {
		return signTpCd;
	}

	public void setSignTpCd(String signTpCd) {
		this.signTpCd = signTpCd;
	}

	public String getSignUserId() {
		return signUserId;
	}

	public void setSignUserId(String signUserId) {
		this.signUserId = signUserId;
	}

	public String getSignUserNm() {
		return signUserNm;
	}

	public void setSignUserNm(String signUserNm) {
		this.signUserNm = signUserNm;
	}

	public String getIsNeedDirector() {
		return isNeedDirector;
	}

	public void setIsNeedDirector(String isNeedDirector) {
		this.isNeedDirector = isNeedDirector;
	}

	public String getApperOrgCd() {
		return apperOrgCd;
	}

	public void setApperOrgCd(String apperOrgCd) {
		this.apperOrgCd = apperOrgCd;
	}

	public String getApperOrgNm() {
		return apperOrgNm;
	}

	public void setApperOrgNm(String apperOrgNm) {
		this.apperOrgNm = apperOrgNm;
	}
}
