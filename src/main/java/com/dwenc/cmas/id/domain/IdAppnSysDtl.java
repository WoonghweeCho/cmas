package com.dwenc.cmas.id.domain;

/**
 * 특별ID Domain Class
 * @author DAEWOO
 *
 */
public class IdAppnSysDtl {
	private String sysCd;
	private String moduleNm;
	private String perchrgId;
	private String perchrgNm;
	private String orgId;
	private String fstRegDt;
	private String fstRegUserId;
	private String fnlEditDt;
	private String fnlEditUserId;

	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	public String getModuleNm() {
		return moduleNm;
	}
	public void setModuleNm(String moduleNm) {
		this.moduleNm = moduleNm;
	}
	public String getPerchrgId() {
		return perchrgId;
	}
	public void setPerchrgId(String perchrgId) {
		this.perchrgId = perchrgId;
	}
	public String getPerchrgNm() {
		return perchrgNm;
	}
	public void setPerchrgNm(String perchrgNm) {
		this.perchrgNm = perchrgNm;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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