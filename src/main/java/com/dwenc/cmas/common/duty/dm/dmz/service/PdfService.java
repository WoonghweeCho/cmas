package com.dwenc.cmas.common.duty.dm.dmz.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;

/**
 * 첨부파일
 *
 * @author
 */
@Service
public class PdfService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(PdfService.class) ;

    @Autowired
    @Qualifier("mainDB")
    private CommonDao dao ;

    // 1 - Barocon Rexpert : BaroconService.saveBaroconPdf 구현
    // 2 - TDMS Office(사인 포함)
    // 3 - TDMS Office(사인 미포함)
    // 4 - TDMS Rexpert ( 싸인위치 불변 )
    // 5 - 원본파일(N개)를 PDF파일(1개)로 변환
    // 6 - TDMS Rexpert ( 싸인위치 가변 )

    // 1. 결재요청 승인에 대한 PDF변환
    public void saveSignedPdf(Map<String, Object> requestMap) {
        boolean bSuccess = false ;

        String chgTp = requestMap.get("chgTp").toString() ;
        String progClscd = "" ;  // 프로그램분류코드
        requestMap.put("userId", "SYSTEM") ;

        // 1-1. 변환유형코드 IN ( 2, 3 )일 때
        if(chgTp.equals("2") || chgTp.equals("3")){

            Map<String, Object> clbkMap = (Map) requestMap.get("clbkParam") ;
            progClscd = clbkMap.get("progClscd").toString() ;

            requestMap.put("progClscd", progClscd)              ;
            requestMap.put("docMno"   , clbkMap.get("docMno"))  ;
            requestMap.put("atchSeq"  , clbkMap.get("atchSeq")) ;
            requestMap.put("coverYn"  , clbkMap.get("coverYn")) ;
            requestMap.put("siteCd"   , clbkMap.get("siteCd"))  ;

            if(progClscd.equals("01")) {
                dao.update("dmPdf.insertDMBTeamDocSignFile", requestMap) ;  // 1-1-1. DMB팀문서결재파일 등록
                bSuccess = true ;
            }
            else if (progClscd.equals("02")) {
                dao.update("dmPdf.insertDMBSiteDocSignFile", requestMap) ;  // 1-1-2. DMB현장문서결재파일 등록
                bSuccess = true ;
            }
            else if (progClscd.equals("04")) {
                dao.update("dmPdf.insertDMBRefDocSignFile", requestMap)  ;  // 1-1-3. DMB참고문서결재파일 등록
                bSuccess = true ;
            }
            else {
                dao.update("dmPdf.insertDMDSiteDocSignFile", requestMap) ;  // 1-1-4. DMD현장문서결재파일 등록
                bSuccess = true ;
            }
        }
        // 1-2. 변환유형코드 = '4'일 때
        else if(chgTp.equals("4")){
            dao.update("dmPdf.insertDMDSiteDocSignFileRep", requestMap) ;   // 1-2-1. DMD현장문서결재파일 등록( e-Book목차파일 )
            bSuccess = true ;
        }
       // 1-3. 변환유형코드 = '5'일 때
        else if(chgTp.equals("5")){
            Map<String, Object> map = (Map) requestMap.get("clbkParam") ;
            progClscd = map.get("progClscd").toString() ;

            requestMap.put("progClscd" , map.get("progClscd"))  ;
            requestMap.put("docMno"    , map.get("docMno"))     ;
            requestMap.put("sbmtStepCd", map.get("sbmtStepCd")) ;
            requestMap.put("sbmtSeq"   , map.get("sbmtSeq"))    ;
            requestMap.put("atchSeq"   , map.get("atchSeq"))    ;

            if(progClscd.equals("06")) {
                dao.update("dmPdf.updateProcFile", requestMap)    ;  // 1-3-1. DMD현장절차서파일 업데이트
                bSuccess = true ;
            }
            else if (progClscd.equals("07")) {
                dao.update("dmPdf.updateEngdeliFile", requestMap) ;  // 1-3-2. DMD설계성과품파일 업데이트
                bSuccess = true ;
            }
            else if (progClscd.equals("08")) {
                dao.update("dmPdf.updateVenderFile", requestMap)  ;  // 1-3-3. DMD공급자문서파일 업데이트
                bSuccess = true ;
            }
            else if (progClscd.equals("09")) {
                dao.update("dmPdf.updateIsodwgFile", requestMap)  ;  // 1-3-4. DMD현장ISO도면파일 업데이트
                bSuccess = true ;
            }
        }
        // 1-4. 서신문서 리포트일 때
        else if(chgTp.equals("6")){
        	Map<String, Object> map = (Map) requestMap.get("clbkParam") ;
        	requestMap.put("docMno", map.get("corrMno")) ;
            requestMap.put("siteCd", map.get("siteCd"))  ;

            dao.update("dmPdf.insertDMDSiteCorrFile", requestMap) ;   // 1-4-1. DMD현장서신문서파일
            bSuccess = true ;
        }

        if (!bSuccess) {
            logger.error("문서관리 - PDF 변환 정보 저장 실패");
            throw new BusinessException("dm.err.PDF");
        }
    }

    // 2. PDF협업 서버 완료파일 업로드
    public void outcomSquadFile(Map<String, Object> requestMap) {
        if( requestMap.get("outcomFileAtchId").toString() == "" ||
            requestMap.get("squrvwMno").toString()        == "" ||
            requestMap.get("rvwFileSeq").toString()       == "" ||
            requestMap.get("progClscd").toString()        == ""  ) {
            throw new BusinessException("dm.err.PDF");
        }
        dao.update("dmPdf.updateSquadFile", requestMap) ; // 2-1. DMD_SQURVW_FILE 테이블 결과파일등록
    }
}
