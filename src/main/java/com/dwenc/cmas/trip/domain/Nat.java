package com.dwenc.cmas.trip.domain;

/**
 * 국내출장 Domain Class
 * @author DAEWOO
 *
 */
public class Nat {

	private String natCd;
	private String natNm;
	private String visaYn;
	private String visaInfo;
	private String fstRegDt;
	private String fstRegUserId;
	private String fnlEditDt;
	private String fnlEditUserId;

	private String ncsDoc;
	private String issueNeedPeriod;
	private String visaFee;

	private String stayAblePeriod;
	private String validPeriod;
	private String visaPrgrStage;

	private String pecul;
	private String rem;
	private String fileAtchId;
	private String riskYn;
	private String pportUseApprNcsYn;

	private String title;           //바로콘 연계 메일발송 제목
	private String cont;            //바로콘 연계 메일발송 내용
	private String sendUserId;      //바로콘 연계 메일발송 발송자
	private String fileNm;
	private String fileSize;
	private String ecmNo;

	private String fmlyInfoNcsYn;	//비자신청시 가족정보 필요여부
	private String armyInfoNcsYn;	//비자신청시 병역정보 필요여부


	public String getNatCd() {
		return natCd;
	}
	public void setNatCd(String natCd) {
		this.natCd = natCd;
	}
	public String getNatNm() {
		return natNm;
	}
	public void setNatNm(String natNm) {
		this.natNm = natNm;
	}
	public String getVisaYn() {
		return visaYn;
	}
	public void setVisaYn(String visaYn) {
		this.visaYn = visaYn;
	}
	public String getVisaInfo() {
		return visaInfo;
	}
	public void setVisaInfo(String visaInfo) {
		this.visaInfo = visaInfo;
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
	public String getNcsDoc() {
		return ncsDoc;
	}
	public void setNcsDoc(String ncsDoc) {
		this.ncsDoc = ncsDoc;
	}
	public String getIssueNeedPeriod() {
		return issueNeedPeriod;
	}
	public void setIssueNeedPeriod(String issueNeedPeriod) {
		this.issueNeedPeriod = issueNeedPeriod;
	}
	public String getVisaFee() {
		return visaFee;
	}
	public void setVisaFee(String visaFee) {
		this.visaFee = visaFee;
	}

	public String getPecul() {
		return pecul;
	}
	public void setPecul(String pecul) {
		this.pecul = pecul;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
	}
	public String getFileAtchId() {
		return fileAtchId;
	}
	public void setFileAtchId(String fileAtchId) {
		this.fileAtchId = fileAtchId;
	}

	public String getRiskYn() {
		return riskYn;
	}
	public void setRiskYn(String riskYn) {
		this.riskYn = riskYn;
	}
	public String getPportUseApprNcsYn() {
		return pportUseApprNcsYn;
	}
	public void setPportUseApprNcsYn(String pportUseApprNcsYn) {
		this.pportUseApprNcsYn = pportUseApprNcsYn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getEcmNo() {
		return ecmNo;
	}
	public void setEcmNo(String ecmNo) {
		this.ecmNo = ecmNo;
	}
	public String getFmlyInfoNcsYn() {
		return fmlyInfoNcsYn;
	}
	public void setFmlyInfoNcsYn(String fmlyInfoNcsYn) {
		this.fmlyInfoNcsYn = fmlyInfoNcsYn;
	}
	public String getArmyInfoNcsYn() {
		return armyInfoNcsYn;
	}
	public void setArmyInfoNcsYn(String armyInfoNcsYn) {
		this.armyInfoNcsYn = armyInfoNcsYn;
	}
	public String getStayAblePeriod() {
		return stayAblePeriod;
	}
	public void setStayAblePeriod(String stayAblePeriod) {
		this.stayAblePeriod = stayAblePeriod;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public String getVisaPrgrStage() {
		return visaPrgrStage;
	}
	public void setVisaPrgrStage(String visaPrgrStage) {
		this.visaPrgrStage = visaPrgrStage;
	}


 }