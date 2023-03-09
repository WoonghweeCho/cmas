package com.dwenc.cmas.tech.domain;

/**
 * 기술정보자료  Domain Class
 * @author DAEWOO
 *
 */

public class TechDat {
	private String regNo;              //등록번호
	private String title;               //제목
	private String actorNm;            //배우명
	private String ath;                 //저자
	private String chnl;                //채널
	private String cls;                 //구분
	private String interClscd;          //UDC코드
	private String cont;                //내용
	private String dbng;                //더빙
	private String distrCls;           //배부구분
	private String docSts;             //문서상태
	private String dvdCls;             //디비디분류
	private String edtPerchrg;         //편집담당자
	private String eval;                //평가
	private String grade;               //등급
	private String issueCpys;          //발행부수
	private String issueDd;            //발행일
	private String issueEr;            //발행자
	private String issueMgr;           //발행팀장
	private String issuePl;            //발행처
	private String issueTeam;          //발행팀

	private String issueYr;            //발행년도
	private String keywd;               //키워드
	private String playTime;           //상영시간
	private String prfrd;               //감수자
	private String sbtl;                //자막
	private String spv;                 //감독
	private String sumry;               //요약
	private String mkco;                //제작사
	private String recTp;               //녹음유형
	private String scrn;                //화면

	private String atchFileId;        //첨부파일ID
	private String fnlEditDt;         //최종수정일시
	private String fnlEditUserId;    //최종수정사용자ID
	private String fstRegDt;          //최초등록일시
	private String fstRegUserId;     //최초등록사용자ID
	private String subject;          //제목
	private String disregno;
	private String issueRegNo;
	private String imgAtchFileId;
	private String disdocsts;
	private String userId;           //등록신청자ID
	private String userNm;           //등록신청자성명
	private String userPositCd;      //등록신청자직위

	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getActorNm() {
		return actorNm;
	}
	public void setActorNm(String actorNm) {
		this.actorNm = actorNm;
	}
	public String getAth() {
		return ath;
	}
	public void setAth(String ath) {
		this.ath = ath;
	}
	public String getChnl() {
		return chnl;
	}
	public void setChnl(String chnl) {
		this.chnl = chnl;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getDbng() {
		return dbng;
	}
	public void setDbng(String dbng) {
		this.dbng = dbng;
	}
	public String getDistrCls() {
		return distrCls;
	}
	public void setDistrCls(String distrCls) {
		this.distrCls = distrCls;
	}
	public String getDocSts() {
		return docSts;
	}
	public void setDocSts(String docSts) {
		this.docSts = docSts;
	}
	public String getDvdCls() {
		return dvdCls;
	}
	public void setDvdCls(String dvdCls) {
		this.dvdCls = dvdCls;
	}
	public String getEdtPerchrg() {
		return edtPerchrg;
	}
	public void setEdtPerchrg(String edtPerchrg) {
		this.edtPerchrg = edtPerchrg;
	}
	public String getEval() {
		return eval;
	}
	public void setEval(String eval) {
		this.eval = eval;
	}
	public String getIssueCpys() {
		return issueCpys;
	}
	public void setIssueCpys(String issueCpys) {
		this.issueCpys = issueCpys;
	}
	public String getIssueDd() {
		return issueDd;
	}
	public void setIssueDd(String issueDd) {
		this.issueDd = issueDd;
	}
	public String getIssueEr() {
		return issueEr;
	}
	public void setIssueEr(String issueEr) {
		this.issueEr = issueEr;
	}
	public String getIssueMgr() {
		return issueMgr;
	}
	public void setIssueMgr(String issueMgr) {
		this.issueMgr = issueMgr;
	}
	public String getIssuePl() {
		return issuePl;
	}
	public void setIssuePl(String issuePl) {
		this.issuePl = issuePl;
	}
	public String getIssueTeam() {
		return issueTeam;
	}
	public void setIssueTeam(String issueTeam) {
		this.issueTeam = issueTeam;
	}
	public String getIssueYr() {
		return issueYr;
	}
	public void setIssueYr(String issueYr) {
		this.issueYr = issueYr;
	}
	public String getKeywd() {
		return keywd;
	}
	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public String getPrfrd() {
		return prfrd;
	}
	public void setPrfrd(String prfrd) {
		this.prfrd = prfrd;
	}
	public String getSbtl() {
		return sbtl;
	}
	public void setSbtl(String sbtl) {
		this.sbtl = sbtl;
	}
	public String getSpv() {
		return spv;
	}
	public void setSpv(String spv) {
		this.spv = spv;
	}
	public String getSumry() {
		return sumry;
	}
	public void setSumry(String sumry) {
		this.sumry = sumry;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDisregno() {
		return disregno;
	}
	public void setDisregno(String disregno) {
		this.disregno = disregno;
	}
	public String getMkco() {
		return mkco;
	}
	public void setMkco(String mkco) {
		this.mkco = mkco;
	}
	public String getRecTp() {
		return recTp;
	}
	public void setRecTp(String recTp) {
		this.recTp = recTp;
	}
	public String getScrn() {
		return scrn;
	}
	public void setScrn(String scrn) {
		this.scrn = scrn;
	}
	public String getInterClscd() {
		return interClscd;
	}
	public void setInterClscd(String interClscd) {
		this.interClscd = interClscd;
	}
	public String getIssueRegNo() {
		return issueRegNo;
	}
	public void setIssueRegNo(String issueRegNo) {
		this.issueRegNo = issueRegNo;
	}
	public String getImgAtchFileId() {
		return imgAtchFileId;
	}
	public void setImgAtchFileId(String imgAtchFileId) {
		this.imgAtchFileId = imgAtchFileId;
	}
	public String getDisdocsts() {
		return disdocsts;
	}
	public void setDisdocsts(String disdocsts) {
		this.disdocsts = disdocsts;
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
	public String getUserPositCd() {
		return userPositCd;
	}
	public void setUserPositCd(String userPositCd) {
		this.userPositCd = userPositCd;
	}

}
