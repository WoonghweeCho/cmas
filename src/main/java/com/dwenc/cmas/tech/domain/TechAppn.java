package com.dwenc.cmas.tech.domain;

/**
 * 기술정보자료  Domain Class
 * @author DAEWOO
 *
 */

public class TechAppn {

	private String cls;               //구분
	private String docNo;             //문서번호
	private String docSts;            //문서상태
	private String orgCd;             //조직코드
	private String orgNm;             //조직명
	private String userId;            //신청자사번
	private String userNm;            //신청자명
	private String appnDd;            //신청일
	private String grd;               //직급
	private String regNo;             //등록번호
	private String rentDd;            //대여일
	private String rtrnDd;            //반납일
	private String telno;             //전화번호
	private String subject;           //제목
	private String title;             //제목
	private String atchFileId;        //첨부파일ID
	private String issueRegNo;

	private String fnlEditDt;         //최종수정일시
	private String fnlEditUserId;     //최종수정사용자ID
	private String fstRegDt;          //최초등록일시
	private String fstRegUserId;      //최초등록사용자ID
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocSts() {
		return docSts;
	}
	public void setDocSts(String docSts) {
		this.docSts = docSts;
	}
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getAppnDd() {
		return appnDd;
	}
	public void setAppnDd(String appnDd) {
		this.appnDd = appnDd;
	}
	public String getGrd() {
		return grd;
	}
	public void setGrd(String grd) {
		this.grd = grd;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRentDd() {
		return rentDd;
	}
	public void setRentDd(String rentDd) {
		this.rentDd = rentDd;
	}
	public String getRtrnDd() {
		return rtrnDd;
	}
	public void setRtrnDd(String rtrnDd) {
		this.rtrnDd = rtrnDd;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getIssueRegNo() {
		return issueRegNo;
	}
	public void setIssueRegNo(String issueRegNo) {
		this.issueRegNo = issueRegNo;
	}

}



