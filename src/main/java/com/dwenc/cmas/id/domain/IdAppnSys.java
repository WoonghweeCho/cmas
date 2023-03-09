package com.dwenc.cmas.id.domain;

/**
 * 특별ID Domain Class
 * @author DAEWOO
 *
 */
public class IdAppnSys {
	private String userId;
	private String sysCd;
	private String privCd;
	private String fstRegDt;
	private String fstRegUserId;
	private String fnlEditDt;
	private String fnlEditUserId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	public String getPrivCd() {
		return privCd;
	}
	public void setPrivCd(String privCd) {
		this.privCd = privCd;
	}
	public String getFstRegDt() {
		return fstRegDt;
	}
	public void setFstRegDt(String fstRegDt) {
		this.fstRegDt = fstRegDt;
	}
	public String getFstRegUserId() {
		return fstRegUserId;
	}
	public void setFstRegUserId(String fstRegUserId) {
		this.fstRegUserId = fstRegUserId;
	}
	public String getFnlEditDt() {
		return fnlEditDt;
	}
	public void setFnlEditDt(String fnlEditDt) {
		this.fnlEditDt = fnlEditDt;
	}
	public String getFnlEditUserId() {
		return fnlEditUserId;
	}
	public void setFnlEditUserId(String fnlEditUserId) {
		this.fnlEditUserId = fnlEditUserId;
	}
}