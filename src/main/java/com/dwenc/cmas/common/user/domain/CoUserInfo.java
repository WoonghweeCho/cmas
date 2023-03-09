package com.dwenc.cmas.common.user.domain;
/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 설    명 : 사용자 정보 조회를 위한 domain 클래스
 * 작 성 자 : 이재열
 * 작성일자 : 2011-12-01
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class CoUserInfo {

  	/**
 	 * 사용자ID
 	 */
 	private String userId;
  	/**
 	 * 조직코드
 	 */
 	private String orgCd;
  	/**
 	 * 사용자유형코드
 	 */
 	private String userTpCd;
    /**
     * 사용자한글명
     */
    private String userKnm;
  	/**
 	 * 사용자영문명
 	 */
 	private String userEnm;
  	/**
 	 * 사용여부
 	 */
 	private String useYn;
  	/**
 	 * 조직명
 	 */
	private String orgNm;

  	/**
 	 * 휴대폰 번호
 	 */
	private String mphoneNo;

	/**
	* oggCd
	*/
	private String oggCd;

	/**
	* oggTime
	*/
	private String oggTime;

	private String userPositCd;

	private String userRealPositCd;



	public String getUserRealPositCd() {
		return userRealPositCd;
	}

	public void setUserRealPositCd(String userRealPositCd) {
		this.userRealPositCd = userRealPositCd;
	}

	public String getUserPositCd() {
		return userPositCd;
	}

	public void setUserPositCd(String userPositCd) {
		this.userPositCd = userPositCd;
	}

	private String userRpswrkCd;

	public String getUserRpswrkCd() {
		return userRpswrkCd;
	}

	public void setUserRpswrkCd(String userRpswrkCd) {
		this.userRpswrkCd = userRpswrkCd;
	}

	private String apptCls;

	private String apptOrgCd;



	public String getApptCls() {
		return apptCls;
	}

	public void setApptCls(String apptCls) {
		this.apptCls = apptCls;
	}

	public String getApptOrgCd() {
		return apptOrgCd;
	}

	public void setApptOrgCd(String apptOrgCd) {
		this.apptOrgCd = apptOrgCd;
	}

	/**
 	 * 사용자ID getter
 	 * @return userId
 	 */
    public String getUserId() {
       return userId;
    }

 	/**
 	 * 사용자ID setter
 	 * @param userId
 	 */
    public void setUserId(String userId) {
       this.userId = userId;
    }

 	/**
 	 * 조직코드 getter
 	 * @return orgCd
 	 */
    public String getOrgCd() {
       return orgCd;
    }

 	/**
 	 * 조직코드 setter
 	 * @param orgCd
 	 */
    public void setOrgCd(String orgCd) {
       this.orgCd = orgCd;
    }

 	/**
 	 * 사용자유형코드 getter
 	 * @return userTpCd
 	 */
    public String getUserTpCd() {
       return userTpCd;
    }

 	/**
 	 * 사용자유형코드 setter
 	 * @param userTpCd
 	 */
    public void setUserTpCd(String userTpCd) {
       this.userTpCd = userTpCd;
    }

 	/**
 	 * 사용자한글명 getter
 	 * @return userKnm
 	 */
    public String getUserKnm() {
       return userKnm;
    }

 	/**
 	 * 사용자한글명 setter
 	 * @param userKnm
 	 */
    public void setUserKnm(String userKnm) {
       this.userKnm = userKnm;
    }

 	/**
 	 * 사용자영문명 getter
 	 * @return userEnm
 	 */
    public String getUserEnm() {
       return userEnm;
    }

 	/**
 	 * 사용자영문명 setter
 	 * @param userEnm
 	 */
    public void setUserEnm(String userEnm) {
       this.userEnm = userEnm;
    }


 	/**
 	 * 사용여부 getter
 	 * @return useYn
 	 */
    public String getUseYn() {
       return useYn;
    }

 	/**
 	 * 사용여부 setter
 	 * @param useYn
 	 */
    public void setUseYn(String useYn) {
       this.useYn = useYn;
    }

    /**
 	 * 조직명 getter
 	 * @return orgNm
 	 */
	public String getOrgNm() {
		return orgNm;
	}
 	/**
 	 * 조직명 setter
 	 * @param orgNm
 	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

 	public String getMphoneNo() {
		return mphoneNo;
	}

	public void setMphoneNo(String mphoneNo) {
		this.mphoneNo = mphoneNo;
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
