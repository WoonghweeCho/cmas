package com.dwenc.cmas.common.duty.dm.dmz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.common.ObjUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;

/**
 * 첨부파일
 *
 * @author
 */
@Service
public class BaroconService {

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(BaroconService.class);

	// 바로콘 화면 업로드에서 기등록여부를 체크하는 메소드
	public String existDocFileCheck(Map<String, Object> map) throws Exception
	{
		String hoSiteCls = (String) map.get("hoSiteCls") ; // 본사/현장구분코드 (2:현장, 1:본사)
		String wkCd      = ( ObjUtil.isNull(map.get("wkCd")) ? "" : (String) map.get("wkCd")) ; // 매핑유형코드( 01:일반/본사문서, 02:PSCS문서 )

		if (hoSiteCls.equals("2")) {
			if(!wkCd.equals("")) return dao.queryForObject("dmBarocon.existPscsFileCheck", map, String.class) ;  // 0-1. PSCS문서파일 존재여부 체크
			else                 return dao.queryForObject("dmBarocon.existSiteFileCheck", map, String.class) ;  // 0-2. 현장문서파일 존재여부 체크
		}
		else {
			return dao.queryForObject("dmBarocon.existTeamFileCheck", map, String.class) ;                       // 0-3. 본사문서파일 존재여부 체크
		}
	}

	/**
	 * 바로콘 -> TDMS 연동
	 * Map 정의
	 * hoSiteCls    0.  본사현장구분 ( 1:본사 / 2:현장 )
	 * siteCd	    1.  현장코드
	 * docclsCd	    2.  문서분류코드
	 * atchFileSeq  3.  첨부파일순번 ( DMB_SITEDOC_MP_DTL.ATCH_FILE_SEQ ) - 컬럼명 확인
	 * docno	    4.  문서번호
	 * title	    5.  문서제목
	 * keywd	    6.  키워드
	 * fileAtchId   7.  파일첨부ID
	 * fileId	    8.  파일ID
	 * signDemYn    9.  결재요청여부
	 * refDocMno	10. 문서관리번호  - 링크첨부시 : 본사현장구분코드 = '2' 일때만 링크버튼 활성화
	 * refRevisnCd	11. 개정코드        - 링크첨부시
	 * refAtchSeq	12. 첨부순번        - 링크첨부시
	 * delFlag      13. 삭제 FLAG ('1')
	 *
	 * wkCd         14. 공종코드   ( 추가 : PSCS문서 )
	 * mrOutNo      15. MR표시번호 ( 추가 : PSCS문서 )
	 * poOutNo      16. PO표시번호 ( 추가 : PSCS문서 )
	 *
	 * @param List<Map> Map
	 * @return ?
	 * @exception Exception
	 */
	public void sendFromBaroconToTdmsSingle(HashMap<String, Object> map) throws Exception
	{
		long docMno       = 0 ;                            // 문서관리번호
		String instanceId = UserInfo.getInstanceId() ;     // WAS코드
		String userId     = UserInfo.getUserId()     ;     // 사용자ID
		String hoSiteCls  =(String) map.get("hoSiteCls") ; // 본사/현장구분코드
		String mpTpCd     = "" ;                           // PSCS여부 ( 자재파트 PSCS관련 문서 저장요청 )
		map.put("fstRegUserId" , userId) ;
		map.put("fnlEditUserId", userId) ;

		List<Map<String, Object>> mpInfos = dao.queryForMapList("dmBarocon.retrieveMpInfo", map) ;

		if (mpInfos.size() > 0) {
			Map<String, Object> mpInfo = mpInfos.get(0) ;
            mpTpCd = (ObjUtil.isNull(mpInfo.get("mpTpCd")) ? "" : mpInfo.get("mpTpCd").toString()) ;

			// 1. 현장문서 저장요청
			if(hoSiteCls.equals("2")) {
				// *** 현장 일반문서 저장
				if(mpTpCd.equals("01")) {
					docMno = dao.queryForObject("dmBarocon.retrieveExistSitedoc", map, Long.class) ;  // 1-1. 현장문서 존재여부 체크
					map.put("docMno", docMno) ;  // 1:기등록(한건) / -1:기등록(여러건) / 0:신규

					// 1-2. 삭제 요청시
					if (map.containsKey("delFlag") && map.get("delFlag").toString().compareTo("1") == 0) {
						dao.update("dmBarocon.deleteSitedocFile", map) ;
					}
					else {
						if(docMno == 0) {
							// 1-3. 현장문서관리번호 채번
							docMno = dao.queryForObject("dmBarocon.siteDocMnoSeq", new HashMap(), Long.class) ;
							map.put("docMno", instanceId + String.valueOf(docMno)) ;
							// 1-4. 현장문서 등록
							dao.update("dmBarocon.insertSitedoc", map) ;
							dao.update("dmBarocon.insertSitedocFile", map) ;
						}
						// 1-5. 현장문서파일 등록 ( MERGE문 수정 )
						else if(docMno > 0) dao.update("dmBarocon.insertSitedocFile", map) ;
						else throw new BusinessException("dm.err.docNo") ; // 동일 문서번호로 저장을 요청하였습니다.
					}
				}
				// *** PSCS문서 저장
				else {
					docMno = dao.queryForObject("dmBarocon.retrieveExistPscsdoc", map, Long.class) ;  // 3-1. 구매일정문서 존재여부 체크
					map.put("docMno", docMno) ;  // 1:기등록(한건) / -1:기등록(여러건) / 0:신규

					// 3-2. 삭제 요청시
					if (map.containsKey("delFlag") && map.get("delFlag").toString().compareTo("1") == 0) {
						dao.update("dmBarocon.deletePscsdocFile", map) ;
					}
					else {
						if(docMno == 0) {
							// 3-3. 구매일정문서관리번호 채번
							docMno = dao.queryForObject("dmBarocon.pscsDocMnoSeq", new HashMap(), Long.class) ;
							map.put("docMno", instanceId + String.valueOf(docMno)) ;
							// 3-4. 구매일정문서 등록
							dao.update("dmBarocon.insertPscsdoc", map) ;
							dao.update("dmBarocon.insertPscsdocFile", map) ;
						}
						// 3-5. 현장문서파일 등록
						else if(docMno > 0) dao.update("dmBarocon.insertPscsdocFile", map) ;
						else throw new BusinessException("dm.err.docNo") ; // 동일 문서번호로 저장을 요청하였습니다.
					}
				}
			}
			// *** 팀문서 저장요청
			else {
				// 2-1. 팀문서 존재여부 체크
				docMno = dao.queryForObject("dmBarocon.retrieveExistTeamdoc", map, Long.class) ;
				map.put("docMno", docMno) ;

				// 2-2. 삭제 요청시
				if (map.containsKey("delFlag") && map.get("delFlag").toString().compareTo("1") == 0) {
					dao.update("dmBarocon.deleteTeamdocFile", map);
				}
				else {
					if(docMno == 0) {
						// 2-3. 팀문서관리번호 채번
						docMno = dao.queryForObject("dmBarocon.teamDocMnoSeq", new HashMap(), Long.class) ;
						map.put("docMno", instanceId + String.valueOf(docMno)) ;
						// 2-4. 팀문서 등록
						dao.update("dmBarocon.insertTeamdoc", map) ;
						dao.update("dmBarocon.insertTeamdocFile", map) ;
					}
					// 2-5. 팀문서파일 등록
					else if(docMno > 0) dao.update("dmBarocon.insertTeamdocFile", map) ;
					else throw new BusinessException("dm.err.docNo") ; // 동일 문서번호로 저장을 요청하였습니다.
				}
			}
		}
	}

	/**
	 * 바로콘화면에서 삭제요청
	 * Map 정의
	 * fileAtchId   파일첨부ID
	 *
	 * @param List<Map> Map
	 * @return ?
	 * @exception Exception
	 */
    public void deleteAllBarocon(Map<String, Object> requestMap) {

    	// 1. fileAtchId 로 조회하여 본사문서/현장문서 , 문서관리번호를 추출한다.
    	List<Map<String, Object>> mpInfos = dao.queryForMapList("dmBarocon.deleteDocMno", requestMap) ;

    	if (mpInfos.size() == 1) {

    		Map<String, Object> mpInfo = mpInfos.get(0) ;
    		String hoSiteCls = mpInfo.get("hoSiteCls").toString() ;
    		requestMap.put("docMno", mpInfo.get("docMno").toString()) ;

    		if(hoSiteCls.equals("2"))      {  // 2-1. 현장문서 삭제요청
    			dao.update("dmBarocon.deleteAllSitedocFile", requestMap) ; // 2-1-1. 현장문서파일 삭제
    			dao.update("dmBarocon.deleteAllSitedoc", requestMap)     ; // 2-1-2. 현장문서 삭제
    		}
    		else if(hoSiteCls.equals("3")) {  // 2-3. PSCS문서 삭제요청
    			dao.update("dmBarocon.deleteAllPscsdocFile", requestMap) ; // 2-3-1. PSCS문서파일 삭제
    			dao.update("dmBarocon.deleteAllPscsdoc", requestMap)     ; // 2-3-2. PSCS문서 삭제
    		}
    		else {                           // 2-2. 본사문서 삭제요청
    			dao.update("dmBarocon.deleteAllTeamdocFile", requestMap) ; // 2-2-1. 본사문서파일 삭제
    			dao.update("dmBarocon.deleteAllTeamdoc", requestMap)     ; // 2-2-2. 본사문서 삭제
    		}
    	}
    	else if(mpInfos.size() > 1) throw new BusinessException("dm.err.dupDocno") ; // 동일 첨부그룹ID로 여러개의 문서가 존재합니다.

    }

	/**
	 * 바로콘 결재요청 PDF변환 : 6/25 : PdfService에 있는 메소드를 여기로 이동 함
	 * Map 정의
	 * hoSiteCls    0.  본사현장구분 ( 1:본사 / 2:현장 )
	 * progId	    1.  프로그램ID
	 * atchGrp	    2.  첨부그룹
	 * orgCd        3.  현장(팀)코드
	 * docno	    4.  문서번호
	 *
	 * @param List<Map> Map
	 * @return ?
	 * @exception Exception
	 */
    public void saveBaroconPdf(Map<String, Object> requestMap) {
        boolean bSuccess = false ;
        // SING-1. 바로콘 연동 : 문서분류코드, 본사/현장 여부 및 매핑분류유형 조회
        List<Map<String, Object>> mpInfos = dao.queryForMapList("dmBarocon.retrieveMpInfo", requestMap) ;

        if (mpInfos.size() > 0) {
            Map<String, Object> mpInfo = mpInfos.get(0) ;
            requestMap.put("docclsCd", mpInfo.get("docclsCd")) ;

            String hoSiteCls = (ObjUtil.isNull(mpInfo.get("hoSiteCls")) ? "" : mpInfo.get("hoSiteCls").toString()) ;
            String mpTpCd    = (ObjUtil.isNull(mpInfo.get("mpTpCd")) ? "" : mpInfo.get("mpTpCd").toString()) ;

            long docMno = 0 ;
            String instanceId = UserInfo.getInstanceId() ; // WAS코드
            requestMap.put("fstRegUserId", UserInfo.getUserId())  ;
            requestMap.put("fnlEditUserId", UserInfo.getUserId()) ;
            requestMap.put("userId", UserInfo.getUserId()) ;

            if(hoSiteCls.equals("2")) {
                if(mpTpCd.equals("01")) {  // 현장일반문서
                	docMno = dao.queryForObject("dmBarocon.retrieveExistSitedoc", requestMap, Long.class) ;  // 1-1-1. 현장별 문서분류별 문서번호 존재여부 체크
                	requestMap.put("docMno", docMno) ;
                	if(docMno == 0) {      // 1. 갑지 최초 생성
                		docMno = dao.queryForObject("dmBarocon.siteDocMnoSeq", new HashMap(), Long.class) ; // 1-4-0. 현장문서관리번호 채번
    					requestMap.put("docMno", instanceId + String.valueOf(docMno)) ;
    					dao.update("dmBarocon.insertSitedoc", requestMap) ;          // 1-4-1. 현장문서등록
                		dao.update("dmBarocon.insertSitePdfSignFile", requestMap) ;  // 1-4-2. 현장문서결재파일
                	}
                	else if(docMno > 0) {  // 2. 문서번호 가등록 상태
                		dao.update("dmBarocon.insertSitePdfSignFile", requestMap) ;  // 1-2-1. 현장문서 결재파일
                	}
                	else {                 // 3. 동일 문서번호로 저장을 요청하였습니다.
                		throw new BusinessException("dm.err.SITE_PDF");
                	}
                	bSuccess = true ;
                }
                else {                     // 현장PSCS문서
                	docMno = dao.queryForObject("dmBarocon.retrieveExistPscsdoc", requestMap, Long.class) ;  // 1-1-3. 현장별 문서분류별 문서번호 존재여부 체크 ( PSCS )
                	requestMap.put("docMno", docMno) ;
                    if(docMno == 0) {      // 1. 갑지 최초 생성
                    	docMno = dao.queryForObject("dmBarocon.pscsDocMnoSeq", new HashMap(), Long.class) ; // 1-4-0. PSCS 현장문서관리번호 채번
    					requestMap.put("docMno", instanceId + String.valueOf(docMno)) ;
    					dao.update("dmBarocon.insertPscsdoc", requestMap) ;          // 1-4-1. PSCS 현장문서등록
                		dao.update("dmBarocon.insertPscsPdfSignFile", requestMap) ;  // 1-4-2. PSCS 현장문서결재파일
                	}
                	else if(docMno > 0) {  // 2. 문서번호 가등록 상태
                		dao.update("dmBarocon.insertPscsPdfSignFile", requestMap) ;  // 1-2-1. PSCS 현장문서 결재파일
                	}
                	else {                 // 3. 동일 문서번호로 저장을 요청하였습니다.
                		throw new BusinessException("dm.err.PSCS_PDF");
                	}
                    bSuccess = true ;
                }
            }
            else {                         // 본사문서
            	dao.queryForObject("dmBarocon.retrieveExistTeamdoc", requestMap, Long.class) ;               // 1-1-2. 팀별 문서분류별 문서번호 존재여부 체크
            	requestMap.put("docMno", docMno) ;
            	if(docMno == 0) {      // 1. 갑지 최초 생성
            		docMno = dao.queryForObject("dmBarocon.teamDocMnoSeq", new HashMap(), Long.class) ; // 1-5-0. 본사문서관리번호 채번
            		requestMap.put("docMno", instanceId + String.valueOf(docMno)) ;
            		dao.update("dmBarocon.insertTeamdoc", requestMap) ;          // 1-5-1. 본사문서등록
            		dao.update("dmBarocon.insertTeamPdfSignFile", requestMap) ;  // 1-5-2. 본사문서결재파일
            	}
            	else if(docMno > 0) {  // 2. 문서번호 가등록 상태
            		dao.update("dmBarocon.insertTeamPdfSignFile", requestMap) ;  // 1-3-1. 팀문서 결재파일
            	}
            	else {                 // 3. 동일 문서번호로 저장을 요청하였습니다.
            		throw new BusinessException("dm.err.TEAM_PDF");
            	}
            	bSuccess = true ;
            }
        }

        if (!bSuccess) {
            throw new BusinessException("dm.err.PDF");
        }
    }

    /**
	 * 결재화면에서 삭제요청1 - 결재파일 ALL 삭제
	 * Map 정의
	 * progId / atchGrp / atchFileSeq / docno / fileAtchId
	 *
	 * @param List<Map> Map
	 * @return ?
	 * @exception Exception
	 */
    public void deleteSignBarocon(Map<String, Object> requestMap) {

    	String  docclsCd  = ""    ;
    	String  hoSiteCls = ""    ;
    	String  mpTpCd    = ""    ;

    	// 1. 삭제대상 테이블 및 문서분류코드 조회
    	List<Map<String, Object>> delDocclsCds = dao.queryForMapList("dmBarocon.signMpInfoAll", requestMap) ;

    	if (delDocclsCds.size() > 0) {
    		Map<String, Object> delDocclsCd = delDocclsCds.get(0) ;
    		docclsCd  = (String) delDocclsCd.get("docclsCd")  ;
    		hoSiteCls = (String) delDocclsCd.get("hoSiteCls") ;
    		mpTpCd    = (String) delDocclsCd.get("mpTpCd")    ;
    		requestMap.put("docclsCd", docclsCd) ;

    		if(hoSiteCls.equals("2")) {
    			if(mpTpCd.equals("01")) {  // 2. 현장문서 결재파일 삭제
    				dao.update("dmBarocon.deleteSignSitedocFile1", requestMap) ; // 2-1. 현장문서결재파일 ALL 삭제
    			}
    			else {                     // 3. PSCS문서 결재파일 삭제
    				dao.update("dmBarocon.deleteSignPscsdocFile1", requestMap) ; // 3-1. PSCS문서결재파일 ALL 삭제
    			}
    		}
    		else {                         // 4. 본사문서 결재파일 삭제
    			dao.update("dmBarocon.deleteSignTeamdocFile1", requestMap) ;    // 4-1. 본사문서결재파일 ALL 삭제
    		}
    	}
    	//else throw new BusinessException("dm.err.docclsCd") ; // 문서저장 : 요청하신 분류코드정보가 잘못되었습니다.
    }

    /**
	 * 결재화면에서 삭제요청2 - 결재파일 단건 삭제
	 * Map 정의
	 * progId / atchGrp / atchFileSeq / docno / fileAtchId / atchFileSeq
	 *
	 * @param List<Map> Map
	 * @return ?
	 * @exception Exception
	 */
    public void deleteSignBaroconEtc(Map<String, Object> requestMap) {

    	String  docclsCd  = ""    ;
    	String  hoSiteCls = ""    ;
    	String  mpTpCd    = ""    ;

    	// 1. 삭제대상 테이블 및 문서분류코드 조회
    	List<Map<String, Object>> delDocclsCds = dao.queryForMapList("dmBarocon.signMpInfoEtc", requestMap) ;

    	if (delDocclsCds.size() > 0) {
    		Map<String, Object> delDocclsCd = delDocclsCds.get(0) ;
    		docclsCd  = (String) delDocclsCd.get("docclsCd")  ;
    		hoSiteCls = (String) delDocclsCd.get("hoSiteCls") ;
    		mpTpCd    = (String) delDocclsCd.get("mpTpCd")    ;
    		requestMap.put("docclsCd", docclsCd) ;

    		if(hoSiteCls.equals("2")) {
    			if(mpTpCd.equals("01")) {  // 2. 현장문서 결재파일 삭제
    				dao.update("dmBarocon.deleteSignSitedocFileEtc", requestMap) ; // 2-1. 현장문서결재파일 단건 삭제
    			}
    			else {                     // 3. PSCS문서 결재파일 삭제
    				dao.update("dmBarocon.deletePscsSitedocFileEtc", requestMap) ; // 3-1. PSCS문서결재파일 단건 삭제
    			}
    		}
    		else {                         // 4. 본사문서 결재파일 삭제
    			dao.update("dmBarocon.deleteSignTeamdocFileEtc", requestMap) ;   // 4-1. 본사문서결재파일 단건 삭제
    		}
    	}
    	//else throw new BusinessException("dm.err.docclsCd") ; // 문서저장 : 요청하신 분류코드정보가 잘못되었습니다.
    }

    /**
	 * 결재상태 업데이트 ( 승인시 문서관리에서 조회여부 = 'Y'로 업데이트 함 ) : 호출 - 최종 결재가 완료 시점
	 * Map 정의
	 * fileAtchId   파일첨부ID
	 * signStsCd    결재상태코드 ( 승인(D03) 또는 ELSE )
	 *
	 * @param List<Map> Map
	 * @return
	 * @exception Exception
	 */
    public void updateSingStsCd(Map<String, Object> requestMap) {

    	// 1. fileAtchId 로 조회하여 본사문서/현장문서 , 문서관리번호를 추출한다.
    	List<Map<String, Object>> mpInfos = dao.queryForMapList("dmBarocon.deleteSignDocMno", requestMap) ;
    	String signStsCd = (String) requestMap.get("signStsCd") ;  // 필수 파라메터

    	if (mpInfos.size() == 1) {  // 1. 일반또는본사문서 결재파일 생성되었을 때,

    		Map<String, Object> mpInfo = mpInfos.get(0) ;
    		String hoSiteCls = mpInfo.get("hoSiteCls").toString() ;
    		requestMap.put("docMno", mpInfo.get("docMno").toString()) ;

    		if(hoSiteCls.equals("2")) {  // 2-1. 현장문서 조회여부 = 'Y'
    			if(signStsCd.equals("D03")) {
    				dao.update("dmBarocon.updateSitePdfSignFile1", requestMap)     ; // 현장문서 조회여부 = 'Y'
    			}
    		}
    		else if(hoSiteCls.equals("3")) {  // 2-3. PSCS 현장문서 조회여부 = 'Y'
    			if(signStsCd.equals("D03")) {
    				dao.update("dmBarocon.updatePscsPdfSignFile1", requestMap)     ; // 현장문서 조회여부 = 'Y'
    			}
    		}
    		else {                       // 2-2. 본사문서 조회여부 = 'Y'
                if(signStsCd.equals("D03")) {
                	dao.update("dmBarocon.updateTeamPdfSignFile1", requestMap)     ; // 본사문서 조회여부 = 'Y'
    			}
    		}
    	}
    	else if(mpInfos.size() > 1) throw new BusinessException("dm.err.dupDocno") ; // 동일 첨부그룹ID로 여러개의 문서가 존재합니다.
    }

}

