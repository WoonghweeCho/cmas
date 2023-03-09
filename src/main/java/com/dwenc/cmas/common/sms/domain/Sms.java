package com.dwenc.cmas.common.sms.domain;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 설    명 : sms 처리를 위한 domain 클래스
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class Sms {
	/**
	 *  indate
	 */
	private String indate   ;
	/**
	 *  intime
	 */
	private String intime  ;

	/**
	 *  sendid
	 */
	private String sendid    ;
	/**
	 *  sendname
	 */
	private String sendname  ;
	/**
	 *  rphone
	 */
	private String rphone    ;
	/**
	 *  recvname
	 */
	private String recvname  ;
	/**
	 *  sphone
	 */
	private String sphone    ;
	/**
	 *  msg
	 */
	private String msg       ;
	/**
	 *  rdate
	 */
	private String rdate     ;
	/**
	 *  rtime
	 */
	private String rtime     ;
	/**
	 *  kind
	 */
	private String kind      ;
	/**
	 *  sys_cd
	 */
	private String sys_cd    ;
	/**
	 *  rem1
	 */
	private String rem1      ;
	/**
	 *  emp_no
	 */
	private String emp_no    ;

	/**
	* oggCd
	*/
	private String oggCd;

	/**
	* oggTime
	*/
	private String oggTime;

	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}

	/**
	 * @return the sendid
	 */
	public String getSendid() {
		return sendid;
	}
	/**
	 * @param sendid the sendid to set
	 */
	public void setSendid(String sendid) {
		this.sendid = sendid;
	}
	/**
	 * @return the sendname
	 */
	public String getSendname() {
		return sendname;
	}
	/**
	 * @param sendname the sendname to set
	 */
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}
	/**
	 * @return the rphone
	 */
	public String getRphone() {
		return rphone;
	}
	/**
	 * @param rphone the rphone to set
	 */
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	/**
	 * @return the recvname
	 */
	public String getRecvname() {
		return recvname;
	}
	/**
	 * @param recvname the recvname to set
	 */
	public void setRecvname(String recvname) {
		this.recvname = recvname;
	}
	/**
	 * @return the sphone
	 */
	public String getSphone() {
		return sphone;
	}
	/**
	 * @param sphone the sphone to set
	 */
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the rdate
	 */
	public String getRdate() {
		return rdate;
	}
	/**
	 * @param rdate the rdate to set
	 */
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	/**
	 * @return the rtime
	 */
	public String getRtime() {
		return rtime;
	}
	/**
	 * @param rtime the rtime to set
	 */
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}
	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	/**
	 * @return the sys_cd
	 */
	public String getSys_cd() {
		return sys_cd;
	}
	/**
	 * @param sys_cd the sys_cd to set
	 */
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}
	/**
	 * @return the rem1
	 */
	public String getRem1() {
		return rem1;
	}
	/**
	 * @param rem1 the rem1 to set
	 */
	public void setRem1(String rem1) {
		this.rem1 = rem1;
	}
	/**
	 * @return the emp_no
	 */
	public String getEmp_no() {
		return emp_no;
	}
	/**
	 * @param emp_no the emp_no to set
	 */
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
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
