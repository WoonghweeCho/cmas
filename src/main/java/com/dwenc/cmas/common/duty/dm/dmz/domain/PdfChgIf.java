package com.dwenc.cmas.common.duty.dm.dmz.domain;

/**
 * PDF 변환을 위한 I/F 정보
 * @author 한지훈
 *
 */
public class PdfChgIf {


    /**
     * 작업아이디
     */
    private String pdfChgId;

    /**
     * instanceId
     */
    private String instanceId;

    /**
     * 현장코드
     */
    private String siteCd;

    /**
     * 결재 ID
     */
    private String signId;

    /**
     * 변환유형
     */
    private String chgTp;

    /**
     * 원본 파일 정보
     */
    private String orgnlFileCont;

    /**
     * 원본 파일 명
     */
    private String orgnlFileNm;

    /**
     * 원본 파일 패스워드
     */
    private String orgnlFilePwd;

    /**
     * 변환 ECM 파일 ID
     */
    private String chgEcmFileId;

    /**
     * 변환 ECM 파일 명
     */
    private String chgEcmFileNm;

    /**
     * 변환 ECM 파일 크기
     */
    private String chgEcmFileSize;

    /**
     * 상태 값
     */
    private String pdfChgSts;

    /**
     * PDF 옵션
     */
    private String pdfOption;

    /**
     * 작업 실행 날짜
     */
    private String chgExecDt;

    /**
     * 작업 완료 날짜
     */
    private String chgCmpltDt;

    /**
     * 에러 메시지
     */
    private String errMsg;

    /**
     * 콜백파라미터
     */
    private String clbkParam;

    /**
     * ECM파일여부
     */
    private String ecmFileYn;

    /**
     * 최초등록일시
     */
    private String fstRegDt;

    /**
     * 최초등록사용자ID
     */
    private String fstRegUserId;

    /**
     * 최종수정일시
     */
    private String fnlEditDt;

    /**
     * 최종수정사용자ID
     */
    private String fnlEditUserId;

    /**
     * OGG코드
     */
    private String oggCd;

    /**
     * OGG시간
     */
    private String oggTime;

    public String getPdfChgId() {
        return pdfChgId;
    }

    public void setPdfChgId(String pdfChgId) {
        this.pdfChgId = pdfChgId;
    }

    public String getSiteCd() {
        return siteCd;
    }

    public void setSiteCd(String siteCd) {
        this.siteCd = siteCd;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getChgTp() {
        return chgTp;
    }

    public void setChgTp(String chgTp) {
        this.chgTp = chgTp;
    }

    public String getOrgnlFileCont() {
        return orgnlFileCont;
    }

    public void setOrgnlFileCont(String orgnlFileCont) {
        this.orgnlFileCont = orgnlFileCont;
    }

    public String getOrgnlFileNm() {
        return orgnlFileNm;
    }

    public void setOrgnlFileNm(String orgnlFileNm) {
        this.orgnlFileNm = orgnlFileNm;
    }

    public String getOrgnlFilePwd() {
        return orgnlFilePwd;
    }

    public void setOrgnlFilePwd(String orgnlFilePwd) {
        this.orgnlFilePwd = orgnlFilePwd;
    }

    public String getChgEcmFileId() {
        return chgEcmFileId;
    }

    public void setChgEcmFileId(String chgEcmFileId) {
        this.chgEcmFileId = chgEcmFileId;
    }

    public String getChgEcmFileNm() {
        return chgEcmFileNm;
    }

    public void setChgEcmFileNm(String chgEcmFileNm) {
        this.chgEcmFileNm = chgEcmFileNm;
    }

    public String getChgEcmFileSize() {
        return chgEcmFileSize;
    }

    public void setChgEcmFileSize(String chgEcmFileSize) {
        this.chgEcmFileSize = chgEcmFileSize;
    }

    public String getPdfChgSts() {
        return pdfChgSts;
    }

    public void setPdfChgSts(String pdfChgSts) {
        this.pdfChgSts = pdfChgSts;
    }

    public String getPdfOption() {
        return pdfOption;
    }

    public void setPdfOption(String pdfOption) {
        this.pdfOption = pdfOption;
    }

    public String getChgExecDt() {
        return chgExecDt;
    }

    public void setChgExecDt(String chgExecDt) {
        this.chgExecDt = chgExecDt;
    }

    public String getChgCmpltDt() {
        return chgCmpltDt;
    }

    public void setChgCmpltDt(String chgCmpltDt) {
        this.chgCmpltDt = chgCmpltDt;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getClbkParam() {
        return clbkParam;
    }

    public void setClbkParam(String clbkParam) {
        this.clbkParam = clbkParam;
    }

    public String getEcmFileYn() {
        return ecmFileYn;
    }

    public void setEcmFileYn(String ecmFileYn) {
        this.ecmFileYn = ecmFileYn;
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

    public String getOggCd() {
        return oggCd;
    }

    public void setOggCd(String oggCd) {
        this.oggCd = oggCd;
    }

    public String getOggTime() {
        return oggTime;
    }

    public void setOggTime(String oggTime) {
        this.oggTime = oggTime;
    }


    public String getInstanceId(){
        return instanceId;
    }


    public void setInstanceId(String instanceId){
        this.instanceId = instanceId;
    }

}
