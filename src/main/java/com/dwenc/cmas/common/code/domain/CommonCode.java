package com.dwenc.cmas.common.code.domain;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : CommonCode
 * 설    명 : 공통코드 처리를 위한 domain 클래스
 * 작 성 자 :
 * 작성일자 : 2011-12-27
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class CommonCode {

    /**
     * code
     */
	private String code;
	/**
	 * value
	 */
	private String value;

	/**
	* oggCd
	*/
	private String oggCd;

	/**
	* oggTime
	*/
	private String oggTime;

	/**
	 * code getter
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
     * value getter
     * @return value
     */
	public String getValue() {
		return value;
	}

	/**
     * @return value
     */
	public void setValue(String value) {
		this.value = value;
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
