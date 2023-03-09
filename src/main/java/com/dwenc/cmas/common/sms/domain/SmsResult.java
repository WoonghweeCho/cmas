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
public class SmsResult {

	/**
	 *  phoneNo
	 */
	private String phoneNo;
	/**
	 *  result
	 */
	private String result;

	/**
	* oggCd
	*/
	private String oggCd;

	/**
	* oggTime
	*/
	private String oggTime;

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
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
