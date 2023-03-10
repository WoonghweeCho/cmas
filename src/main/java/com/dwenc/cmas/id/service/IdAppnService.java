package com.dwenc.cmas.id.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.user.domain.Sign;
import com.dwenc.cmas.id.domain.DaewooEmp;
import com.dwenc.cmas.id.domain.IdAppn;
import com.dwenc.cmas.id.domain.IdAppnDtl;
import com.dwenc.cmas.id.domain.IdAppnSys;
import com.dwenc.cmas.id.domain.IdAppnSysDtl;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.common.ObjUtil;	//(테스트를 위해 commit만 했음)1
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.exception.BusinessException;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통 - 코드관리
 * 프로그램 : COA0101
 * 설 명 : 공통코드 관리를 위한 service 클래스
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * --------------------------------------------------------------
 * 수정일                          이 름          사유
 * --------------------------------------------------------------
 *
 * --------------------------------------------------------------
 * </pre>
 * @version 1.0
 *
 */

@Service
public class IdAppnService {

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	@Autowired
	@Qualifier("2ndDB")
	private CommonDao c51Dao;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	public String getUserId() throws BusinessException{
		return dao.queryForObject("IdAppn.getUserId", new HashMap<String, Object>(), String.class);
	}

	public Map<String, Object> getDocNo(Map<String, Object> map) throws BusinessException{
		Map<String, Object> rslMap = null;
		rslMap = dao.queryForMap("IdAppn.getMaxDocNo", map);
		if(rslMap == null || rslMap.equals("null")){
			rslMap = map;
		}
		return dao.queryForMap("IdAppn.getDocNo", rslMap);
	}

	public List<IdAppn> retrieveIdAppnList(Map<String, Object> params) {
		return dao.queryForList("IdAppn.retrieveIdAppn", params, IdAppn.class);
	}

	public List<IdAppnDtl> retrieveIdAppnDtlList(Map<String, Object> params) {
		return dao.queryForList("IdAppn.retrieveIdAppnDtl", params, IdAppnDtl.class);
	}

	public List<IdAppnDtl> retrieveUserIdList(Map<String, Object> params) {
		return dao.queryForList("IdAppn.retrieveUserIdList", params, IdAppnDtl.class);
	}

	public List<IdAppnSys> retrieveIdAppnSys(Map<String, Object> params) {
		return dao.queryForList("IdAppn.retrieveIdAppnSys", params, IdAppnSys.class);
	}

	public List<IdAppnSysDtl> retrieveIdAppnSysDtlList() {
		return dao.queryForList("IdAppn.retrieveIdAppnSysDtl", null, IdAppnSysDtl.class);
	}

	public void saveIdAppn(Map<String, Object> map, GridData<IdAppnDtl> gData) {

		//테스트로 잠시 (시작)
		//Map<String, Object> daewooEmpMap = new HashMap<String, Object>();;
		//daewooEmpMap.put("userId", "ZA99999");
		//daewooEmpMap.put("compMail", "test@dwenc.com");
		//sendMailToMailManager3(daewooEmpMap);
		//테스트로 잠시 (끝)

		map.put("fnlEditUserId", UserInfo.getUserId());
		dao.update("IdAppn.saveIdAppn", map);

//		gData.forEachRow(new AbstractRowStatusCallback<IdAppnDtl>() {
//			@Override
//			public void normal(IdAppnDtl idAppnDtlList, int rowNum) {
//				if(rowNum == 0) dao.update("IdAppn.deleteAllIdAppnDtl", idAppnDtlList);
//				idAppnDtlList.setFstRegUserId(UserInfo.getUserId());
//				idAppnDtlList.setFnlEditUserId(UserInfo.getUserId());
//				dao.update("IdAppn.mergeIdAppnDtl", idAppnDtlList);
//			}
//		});
		gData.forEachRow(new AbstractRowStatusCallback<IdAppnDtl>() {
			@Override
			public void insert(IdAppnDtl IdAppnDtl, int rowNum) {
				IdAppnDtl.setFstRegUserId(UserInfo.getLoginId());
				IdAppnDtl.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 저장
				dao.update("IdAppn.insertIdAppn", IdAppnDtl);
			}

			@Override
			public void update(IdAppnDtl newRecord, IdAppnDtl oldRecord, int rowNum) {
				newRecord.setFstRegUserId(UserInfo.getLoginId());
				newRecord.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 수정.
				dao.update("IdAppn.updateIdAppn", newRecord);
			}

			@Override
			public void normal(IdAppnDtl IdAppnDtl, int rowNum) {
//				insert(IdAppnDtl, rowNum);
			}

			@Override
			public void delete(IdAppnDtl IdAppnDtl, int rowNum) {
				//시스템신청 정보 삭제
				dao.update("IdAppn.deleteIdAppn", IdAppnDtl);
			}
		});
	}

	public void deleteIdAppn(Map<String, Object> map) {
		map.put("fnlEditUserId", UserInfo.getUserId());
		dao.update("IdAppn.deleteIdAppn", map);
	}

	// 2019.03.20 개발서버에 commit해서 테스트용(sign으로 바꿔 테스트)
	public Map<String, Object> sign_test(Map<String, Object> map, Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("TYPE", "FAIL");
		result.put("MSG", "CMAS : 테스트 실패");
		return result;

	}

	public Map<String, Object> sign(Map<String, Object> map, Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			if(data.get("docStsCd").toString().equals("D03")){
				List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveIdAppnDtlForDaewooEmp", map, DaewooEmp.class);
				for(int i=0; i<ds_DaewooEmp.size(); i++){
					Map<String, Object> daewooEmpMap = new HashMap<String, Object>();;
					daewooEmpMap.put("userId", ds_DaewooEmp.get(i).getEmpId());
					daewooEmpMap.put("userNm", ds_DaewooEmp.get(i).getEmpNm());
					daewooEmpMap.put("userNmEn", ds_DaewooEmp.get(i).getEmpNmEng());
					daewooEmpMap.put("grd", ds_DaewooEmp.get(i).getTitleType());
					daewooEmpMap.put("grd", ds_DaewooEmp.get(i).getPosType());
					daewooEmpMap.put("orgCd", ds_DaewooEmp.get(i).getOrgId());
					daewooEmpMap.put("orgNm", ds_DaewooEmp.get(i).getOrgNm());
					daewooEmpMap.put("compTelno", ds_DaewooEmp.get(i).getComtelNo());
					daewooEmpMap.put("mphoneNo", ds_DaewooEmp.get(i).getHpNo());
					daewooEmpMap.put("faxno", ds_DaewooEmp.get(i).getFaxNo());
					daewooEmpMap.put("compMail", ds_DaewooEmp.get(i).getComEmail());
					daewooEmpMap.put("baromi", ds_DaewooEmp.get(i).getSystem9());   /// daewooemp 테이블에 추가할수있도록
					daewooEmpMap.put("stYmd", ds_DaewooEmp.get(i).getIdStartYmd());
					daewooEmpMap.put("edYmd", ds_DaewooEmp.get(i).getIdEndYmd());
					daewooEmpMap.put("system1", ds_DaewooEmp.get(i).getSystem1());
					daewooEmpMap.put("system2", ds_DaewooEmp.get(i).getSystem2());
					daewooEmpMap.put("system3", ds_DaewooEmp.get(i).getSystem3());
					daewooEmpMap.put("system4", ds_DaewooEmp.get(i).getSystem4());
					daewooEmpMap.put("system5", ds_DaewooEmp.get(i).getSystem5());
					daewooEmpMap.put("system6", ds_DaewooEmp.get(i).getSystem6());
					daewooEmpMap.put("system7", ds_DaewooEmp.get(i).getSystem7());
					daewooEmpMap.put("system8", ds_DaewooEmp.get(i).getSystem8());
					daewooEmpMap.put("system9", ds_DaewooEmp.get(i).getSystem9());
					daewooEmpMap.put("system10", ds_DaewooEmp.get(i).getSystem10());     //// CWH 똑바로 안전 권한 추가 20220729
					daewooEmpMap.put("system11", ds_DaewooEmp.get(i).getSystem11()); //// CWH 똑바로 안전 권한 추가 20220729 (11 모바일용 웹권한 추가시 강제로 자동추가)
					daewooEmpMap.put("appnUserId", ds_DaewooEmp.get(i).getAppnUserId());
					daewooEmpMap.put("appnUserNm", ds_DaewooEmp.get(i).getAppnUserNm());
					daewooEmpMap.put("appnOrgNm", ds_DaewooEmp.get(i).getAppnOrgNm());
					daewooEmpMap.put("note", ds_DaewooEmp.get(i).getNote());
					/* SVPN 권한 부여(테스트커밋) (시작1) */
					if(data.get("programCode").toString().equals("SGNS070008")) { //수정
						daewooEmpMap.put("system5", "1");
					}else{
						daewooEmpMap.put("system5", ds_DaewooEmp.get(i).getSystem5());
					}
					/* SVPN 권한 부여(테스트커밋) (끝) */

					daewooEmpMap.put("modUserId", map.get("signUserId"));
					daewooEmpMap.put("empmtNo", ds_DaewooEmp.get(i).getEmpmtNo());	//(테스트를 위해 commit만 했음)1
					dao.update("IdAppn.mergeDaewooEmp", daewooEmpMap); //20220610 CWH TEST c51sDao 변경

					// 신규 신청 중 메일(PJ) 사용이 있는 경우, 메일시스템 담당자에게 메일 발송
					// SGNS070004 : 특별ID 신규 신청
					// SGNS070006 : 특별ID 연장 신청
					if(data.get("programCode").toString().equals("SGNS070004")) {
						if(ds_DaewooEmp.get(i).getSystem4().toString().equals("2")){ //PJ메일 안내메일
							sendMailToMailManager(daewooEmpMap);
						}
						if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")){ //dwenc.com 안내메일
							sendMailToMailManager3(daewooEmpMap);
						}
						// 이정민 안내메일 발송 (고용번호 존재 + 바로콘 사용) (테스트를 위해 commit만 했음)1
						if( !(ds_DaewooEmp.get(i).getSystem2().toString().equals("0")) && !ObjUtil.isNull(ds_DaewooEmp.get(i).getEmpmtNo()) ){
							sendMailToMailManager5(daewooEmpMap);
						}
						// 프로젝트 외주인력 보안 메일 담당자에게 메일 발송 (신청서 결재완료 시)
						if(ds_DaewooEmp.get(i).getSystem9().toString().equals("1")){ //프로젝트 외주인력 보안 사용
							sendMailToMailManager8(daewooEmpMap);
						}
						// SVPN 담당자에게 메일 발송 (신청서 결재완료 시)
						if(ds_DaewooEmp.get(i).getSystem5().toString().equals("1") ||
								   ds_DaewooEmp.get(i).getSystem5().toString().equals("2")){
									sendMailToMailManager9(daewooEmpMap);
						}

						/**
						 * 임시 권한 부여 시작
						 */
						// 바로콘 부분
						Map<String, Object> barocon = new HashMap<String, Object>();
						barocon.put("sysCd", "02");
						barocon.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", barocon);

						// 바로미 부분
						Map<String, Object> baromi = new HashMap<String, Object>();
						baromi.put("sysCd", "03");
						baromi.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", baromi);

						// 모바일 부분
						Map<String, Object> imms = new HashMap<String, Object>();
						imms.put("sysCd", "06");
						imms.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", imms);

						// 분양관리 부분
						Map<String, Object> poms = new HashMap<String, Object>();
						poms.put("sysCd", "08");
						poms.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", poms);

						// 똑바로 안전 부분 (웹권한(ssfs)가 추가되는경우 모바일권한(ssfm) 은 반드시 추가 되어야하므로 아래 같이 추가함)
						Map<String, Object> ssfs = new HashMap<String, Object>();
						ssfs.put("sysCd", "10");
						ssfs.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", ssfs);

						Map<String, Object> ssms = new HashMap<String, Object>();
						ssms.put("sysCd", "11");
						ssms.put("userId", ds_DaewooEmp.get(i).getEmpId());
						dao.update("IdAppn.insertCoUserCtgPriv", ssms);




						/**
						 * 임시 권한 부여 끝
						 */
					} else if(data.get("programCode").toString().equals("SGNS070006")) { //연장
//						if(ds_DaewooEmp.get(i).getSystem5().toString().equals("1")){  //SVPN담당자에게 연장안내 메일 발송
						if(ds_DaewooEmp.get(i).getSystem5().toString().equals("1") ||
						   ds_DaewooEmp.get(i).getSystem5().toString().equals("2")){  //SVPN담당자에게 연장안내 메일 발송
							sendMailToMailManager4(daewooEmpMap);
						}
						if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")){ //dwenc.com 안내메일
							sendMailToMailManager6(daewooEmpMap);
						}
						// 20211228 cwh 메일 발신 추가   //20220105 !ObjUtil.isNull 추가
						if(!ObjUtil.isNull(ds_DaewooEmp.get(i).getSystem9()) && ds_DaewooEmp.get(i).getSystem9().toString().equals("1")){ //프로젝트 외주인력 보안 연장 안내메일
							sendMailToMailManager10(daewooEmpMap);
						}
						// 20211228 cwh 메일 발신 추가
						/* 기존 ID USE_YN = N */
						Map<String, Object> temp = new HashMap<String, Object>();
						temp.put("userId", ds_DaewooEmp.get(i).getEmpId());
						temp.put("useYn", "N");
						dao.update("IdAppn.updateIdAppnDtlForUserId", temp);
					} else if(data.get("programCode").toString().equals("SGNS070007")) { //해지
						if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")){ //dwenc.com 안내메일
							sendMailToMailManager7(daewooEmpMap);
						}
						//20220105 !ObjUtil.isNull 추가
						if(!ObjUtil.isNull(ds_DaewooEmp.get(i).getSystem9()) && ds_DaewooEmp.get(i).getSystem9().toString().equals("1")){ //프로젝트 외주인력 보안 연장 안내메일
							sendMailToMailManager11(daewooEmpMap);
						}
						/* 기존 ID USE_YN = N */
						Map<String, Object> temp = new HashMap<String, Object>();
						temp.put("userId", ds_DaewooEmp.get(i).getEmpId());
						temp.put("useYn", "N");
						dao.update("IdAppn.updateIdAppnDtlForUserId", temp);
/* SVPN 권한 부여(테스트커밋) (시작1) */
					} else if(data.get("programCode").toString().equals("SGNS070008")) { //수정
						/* 기존 ID USE_YN = N */
						Map<String, Object> temp = new HashMap<String, Object>();
						temp.put("userId", ds_DaewooEmp.get(i).getEmpId());
						temp.put("useYn", "N");
						dao.update("IdAppn.updateIdAppnDtlForUserId", temp);

						/* SVPN 권한 업데이트(CMAA_ID_APPN_SYS) */
						Map<String, Object> temp2 = new HashMap<String, Object>();
						temp2.put("userId", ds_DaewooEmp.get(i).getEmpId());
						temp2.put("privCd", "1");
						temp2.put("fnlEditUserId", map.get("signUserId"));
						temp2.put("sysCd", "05");
						dao.update("IdAppn.updateIdAppnSys", temp2);
/* SVPN 권한 부여(테스트커밋) (끝1) */
					}
				}
				/* 신규, 연장, 수정 docNo에 대한 ID들 USE_YN=Y */
				if(!data.get("programCode").toString().equals("SGNS070007")) {
					map.put("useYn", "Y");
					dao.update("IdAppn.updateIdAppnDtlForDocNo", map);
				}

				sendMailToDrafter(ds_DaewooEmp, data);
			}
		} catch (Exception e) {
			result.put("TYPE", "FAIL");
			result.put("MSG", "CMAS : daewoo_emp 입력 실패");
			e.printStackTrace();
			return result;
		}

		try {
			dao.update("SignUser.updateSign", map);
		} catch (Exception e){
			result.put("TYPE", "FAIL");
			result.put("MSG", "CMAS : 결재선정보 업데이트 실패");
			e.printStackTrace();
			return result;
		}

		try {
			map.put("fnlEditUserId", data.get("signUserId"));
			dao.update("IdAppn.saveIdAppn", map);
		} catch (Exception e) {
			result.put("TYPE", "FAIL");
			result.put("MSG", "CMAS : 문서정보 업데이트 실패");
			e.printStackTrace();
			return result;
		}

		result.put("TYPE", "SUCCESS");
		result.put("MSG", "CMAS : 성공");
		return result;
	}

	public void saveIdAppnSysDtl(Map<String, Object> map, GridData<IdAppnSysDtl> gData) {
		gData.forEachRow(new AbstractRowStatusCallback<IdAppnSysDtl>() {
			@Override
			public void insert(IdAppnSysDtl IdAppnSysDtl, int rowNum) {
				IdAppnSysDtl.setFstRegUserId(UserInfo.getLoginId());
				IdAppnSysDtl.setFnlEditUserId(UserInfo.getLoginId());

				//담당자 정보 저장
				dao.update("IdAppn.insertIdAppnSysDtl", IdAppnSysDtl);
			}

			@Override
			public void update(IdAppnSysDtl newRecord, IdAppnSysDtl oldRecord, int rowNum) {
				newRecord.setFstRegUserId(UserInfo.getLoginId());
				newRecord.setFnlEditUserId(UserInfo.getLoginId());

				//담당자 정보 수정.
				dao.update("IdAppn.updateIdAppnSysDtl", newRecord);
			}

			@Override
			public void normal(IdAppnSysDtl IdAppnSysDtl, int rowNum) {
				insert(IdAppnSysDtl, rowNum);
			}

			@Override
			public void delete(IdAppnSysDtl IdAppnSysDtl, int rowNum) {
				//담당자 정보 삭제
				dao.update("IdAppn.deleteIdAppnSysDtl", IdAppnSysDtl);
			}
		});
	}

	public void saveIdAppnSys(Map<String, Object> map, GridData<IdAppnSys> gData) {
		gData.forEachRow(new AbstractRowStatusCallback<IdAppnSys>() {
			@Override
			public void insert(IdAppnSys IdAppnSys, int rowNum) {
				IdAppnSys.setFstRegUserId(UserInfo.getLoginId());
				IdAppnSys.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 저장
				dao.update("IdAppn.insertIdAppnSys", IdAppnSys);
			}

			@Override
			public void update(IdAppnSys newRecord, IdAppnSys oldRecord, int rowNum) {
				newRecord.setFstRegUserId(UserInfo.getLoginId());
				newRecord.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 수정.
				dao.update("IdAppn.updateIdAppnSys", newRecord);
			}

			@Override
			public void normal(IdAppnSys IdAppnSys, int rowNum) {
				insert(IdAppnSys, rowNum);
			}

			@Override
			public void delete(IdAppnSys IdAppnSys, int rowNum) {
				//시스템신청 정보 삭제
				dao.update("IdAppn.deleteIdAppnSys", IdAppnSys);
			}
		});
	}

/* SVPN 권한 부여(테스트커밋) (시작1)*/
	// SVPN 권한 부여 신청 저장용(update)
	public void saveIdAppnSys2(Map<String, Object> map, GridData<IdAppnSys> gData) {
		gData.forEachRow(new AbstractRowStatusCallback<IdAppnSys>() {
			@Override
			public void insert(IdAppnSys IdAppnSys, int rowNum) {
				IdAppnSys.setFstRegUserId(UserInfo.getLoginId());
				IdAppnSys.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 저장
				dao.update("IdAppn.updateIdAppnSys", IdAppnSys);
			}

			@Override
			public void update(IdAppnSys newRecord, IdAppnSys oldRecord, int rowNum) {
				newRecord.setFstRegUserId(UserInfo.getLoginId());
				newRecord.setFnlEditUserId(UserInfo.getLoginId());

				//시스템신청 정보 수정.
				dao.update("IdAppn.updateIdAppnSys", newRecord);
			}

			@Override
			public void normal(IdAppnSys IdAppnSys, int rowNum) {
				insert(IdAppnSys, rowNum);
			}

			@Override
			public void delete(IdAppnSys IdAppnSys, int rowNum) {
				//시스템신청 정보 삭제
				dao.update("IdAppn.deleteIdAppnSys", IdAppnSys);
			}
		});
	}
/* SVPN 권한 부여(테스트커밋) (끝1)*/
// CWH TEST 20220610 DB 변경
	public void saveAfterFinish(Map<String, Object> map) {
		List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveIdAppnDtlForDaewooEmp", map, DaewooEmp.class);
		for(int i=0; i<ds_DaewooEmp.size(); i++){
			Map<String, Object> daewooEmpMap = new HashMap<String, Object>();;
			daewooEmpMap.put("userId", ds_DaewooEmp.get(i).getEmpId());
			daewooEmpMap.put("userNm", ds_DaewooEmp.get(i).getEmpNm());
			daewooEmpMap.put("userNmEn", ds_DaewooEmp.get(i).getEmpNmEng());
			daewooEmpMap.put("grd", ds_DaewooEmp.get(i).getTitleType());
			daewooEmpMap.put("grd", ds_DaewooEmp.get(i).getPosType());
			daewooEmpMap.put("orgCd", ds_DaewooEmp.get(i).getOrgId());
			daewooEmpMap.put("orgNm", ds_DaewooEmp.get(i).getOrgNm());
			daewooEmpMap.put("compTelno", ds_DaewooEmp.get(i).getComtelNo());
			daewooEmpMap.put("mphoneNo", ds_DaewooEmp.get(i).getHpNo());
			daewooEmpMap.put("faxno", ds_DaewooEmp.get(i).getFaxNo());
			daewooEmpMap.put("compMail", ds_DaewooEmp.get(i).getComEmail());
			daewooEmpMap.put("stYmd", ds_DaewooEmp.get(i).getIdStartYmd());
			daewooEmpMap.put("edYmd", ds_DaewooEmp.get(i).getIdEndYmd());
			daewooEmpMap.put("system1", ds_DaewooEmp.get(i).getSystem1());
			daewooEmpMap.put("system2", ds_DaewooEmp.get(i).getSystem2());
			daewooEmpMap.put("system3", ds_DaewooEmp.get(i).getSystem3());
			daewooEmpMap.put("system4", ds_DaewooEmp.get(i).getSystem4());
			daewooEmpMap.put("system5", ds_DaewooEmp.get(i).getSystem5());
			daewooEmpMap.put("baromi", ds_DaewooEmp.get(i).getSystem9());
			daewooEmpMap.put("modUserId", UserInfo.getUserId());
			dao.update("IdAppn.mergeDaewooEmp", daewooEmpMap); //20220610 CWH c51Dao ->Dao 변경 후 저장 테스트
		}
	}

	public void confirmIdAppnDtl(Map<String, Object> map) {
		dao.update("IdAppn.confirmIdAppnDtl", map);
	}

	private void sendMailToMailManager(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("안내메일(PJ메일신청)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "04");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-PJ메일 사용신청 안내");
			mail.put("fromMailId", mailFrom);
			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = map.get("userId").toString() + "(" + map.get("compMail").toString() + ")";
			mail.put("htmlBody", mailContent);


			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// dwenc.com 메일 담당자에게 메일 발송
	private void sendMailToMailManager3(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("안내메일(dwenc.com메일신청)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "07");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-dwenc.com메일 사용신청 안내");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "-특별ID : " + map.get("userId").toString() + "<br>";
			mailContent += "-이메일 : " + map.get("compMail").toString();

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SVPN 담당자에게 메일 발송
	private void sendMailToMailManager4(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("안내메일(SVPN 연장)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "05");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-SVPN 연장 사용신청 안내");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "-특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += "-특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += "-특별ID 연장 종료일자 : " + map.get("edYmd").toString();

			mail.put("htmlBody", mailContent);

			System.out.println("--------------------------sendMailToMailManager4-----------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이정민 안내메일 발송 (고용번호 존재 + 바로콘 사용) (테스트를 위해 commit만 했음)1
	private void sendMailToMailManager5(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("안내메일(특별아이디 고용번호 존재 건)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "09");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-고용번호 포함된 특별ID 접수 안내");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "-특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += "-특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += "-특별ID 조직코드 : " + map.get("orgCd").toString() + "<br>";
			mailContent += "-특별ID 조직명 : " + map.get("orgNm").toString() + "<br>";
			mailContent += "-특별ID 시작일자 : " + map.get("stYmd").toString() + "<br>";
			mailContent += "-특별ID 종료일자 : " + map.get("edYmd").toString() + "<br>";
			mailContent += "-외국인 고용번호 : " + map.get("empmtNo").toString();

			mail.put("htmlBody", mailContent);

			System.out.println("--------------------------sendMailToMailManager5-----------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// dwenc.com 메일 담당자에게 메일 발송 (연장 시)
	private void sendMailToMailManager6(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("--------------------------sendMailToMailManager6-1---------------------------");
		System.out.println("연장시 안내메일(dwenc.com메일신청)을 발송한다.");
		System.out.println("-----------------------------------------------------------");

		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "07");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-dwenc.com메일 [연장]");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "특별ID dwenc.com 메일 [연장] 안내입니다" + "<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 연장 종료일자 : " + map.get("edYmd").toString() + "<br>";
			mailContent += " -특별ID 이메일 : " + map.get("compMail").toString() + "<br><br>";
			mailContent += " -신청자 사번 : " + map.get("appnUserId").toString() + "<br>";
			mailContent += " -신청자 성명 : " + map.get("appnUserNm").toString() + "<br>";
			mailContent += " -신청자 조직명 : " + map.get("appnOrgNm").toString() + "<br>";

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// dwenc.com 메일 담당자에게 메일 발송 (해지 시)
	private void sendMailToMailManager7(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("해지시 안내메일(dwenc.com메일신청)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "07");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-dwenc.com메일 [해지]");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "특별ID dwenc.com 메일 [해지] 안내입니다" + "<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 해지 종료일자 : " + map.get("edYmd").toString() + "<br>";
			mailContent += " -특별ID 이메일 : " + map.get("compMail").toString() + "<br><br>";
			mailContent += " -신청자 사번 : " + map.get("appnUserId").toString() + "<br>";
			mailContent += " -신청자 성명 : " + map.get("appnUserNm").toString() + "<br>";
			mailContent += " -신청자 조직명 : " + map.get("appnOrgNm").toString() + "<br>";

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 프로젝트 외주인력 보안 메일 담당자에게 메일 발송 (신청서 결재완료 시)
	private void sendMailToMailManager8(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("신청서 결재완료시 안내메일(프로젝트 외주인력 보안)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "14");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-프로젝트 외주인력 보안 [사용 신청]");
			mail.put("fromMailId", mailFrom);

			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String mailContent = "";
			mailContent += "특별ID 프로젝트 외주인력 보안 [사용 신청] 안내입니다" + "<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 조직코드 : " + map.get("orgCd").toString() + "<br>";
			mailContent += " -특별ID 조직명 : " + map.get("orgNm").toString() + "<br>";
			mailContent += " -특별ID 시작일자 : " + map.get("stYmd").toString() + "<br>";
			mailContent += " -특별ID 종료일자 : " + map.get("edYmd").toString() + "<br><br>";
			mailContent += " -신청자 사번 : " + map.get("appnUserId").toString() + "<br>";
			mailContent += " -신청자 성명 : " + map.get("appnUserNm").toString() + "<br>";
			mailContent += " -신청자 조직명 : " + map.get("appnOrgNm").toString() + "<br><br>";
			mailContent += " -사용목적(메모) : " + map.get("note").toString() + "<br>";

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SVPN 담당자에게 메일 발송
	private void sendMailToMailManager9(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("안내메일(SVPN 신규)을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "05");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";


		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));

			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-SVPN [사용신청]");
			mail.put("fromMailId", mailFrom);
			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}


			String s_system1 = "사용";	//바로넷
			String s_system2 = "사용";	//바로콘
			String s_system3 = "사용";	//바로미
			String s_system4 = "사용";	//메일
			String s_system5 = "사용";	//SVPN
			String s_system6 = "사용";	//모바일
			String s_system7 = "사용";	//DRM
			String s_system8 = "사용";	//분양관리
			String s_system9 = "사용";	//프로젝트 외주인력 보안
			String s_system10 = "사용";   ////똑바로 (안전)

			if( map.get("system1").toString().equals("0") || map.get("system1").toString().equals("") || ObjUtil.isNull(map.get("system1").toString()) ){
				s_system1 = "";
			}
			if( map.get("system2").toString().equals("0") || map.get("system2").toString().equals("") || ObjUtil.isNull(map.get("system2").toString()) ){
				s_system2 = "";
			}
			if( map.get("system3").toString().equals("0") || map.get("system3").toString().equals("") || ObjUtil.isNull(map.get("system3").toString()) ){
				s_system3 = "";
			}
			if( map.get("system4").toString().equals("0") || map.get("system4").toString().equals("") || ObjUtil.isNull(map.get("system4").toString()) ){
				s_system4 = "";
			}
			if( map.get("system5").toString().equals("0") || map.get("system5").toString().equals("") || ObjUtil.isNull(map.get("system5").toString()) ){
				s_system5 = "";
			}
			if( map.get("system6").toString().equals("0") || map.get("system6").toString().equals("") || ObjUtil.isNull(map.get("system6").toString()) ){
				s_system6 = "";
			}
			if( map.get("system7").toString().equals("0") || map.get("system7").toString().equals("") || ObjUtil.isNull(map.get("system7").toString()) ){
				s_system7 = "";
			}
			if( map.get("system8").toString().equals("0") || map.get("system8").toString().equals("") || ObjUtil.isNull(map.get("system8").toString()) ){
				s_system8 = "";
			}
			if( map.get("system9").toString().equals("0") || map.get("system9").toString().equals("") || ObjUtil.isNull(map.get("system9").toString()) ){
				s_system9 = "";
			}
			if( map.get("system10").toString().equals("0") || map.get("system10").toString().equals("") || ObjUtil.isNull(map.get("system10").toString()) ){
				s_system10 = "";
			}


			String mailContent = "";
			mailContent += " 특별 ID SVPN [사용신청] 안내입니다." +"<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 조직코드 : " + map.get("orgCd").toString() + "<br>";
			mailContent += " -특별ID 조직명 : " + map.get("orgNm").toString() + "<br>";
			mailContent += " -특별ID 시작일자 : " + map.get("stYmd").toString() + "<br>";
			mailContent += " -특별ID 종료일자 : " + map.get("edYmd").toString() + "<br><br>";
			mailContent += " (1) 바로넷 : " + s_system1 + "<br>";
			mailContent += " (2) 바로콘 : " + s_system2 + "<br>";
			mailContent += " (3) 바로미 : " + s_system3 + "<br>";
			mailContent += " (4) 메일 : " + s_system4 + "<br>";
			mailContent += " (5) SVPN : " + s_system5 + "<br>";
			mailContent += " (6) 모바일 : " + s_system6 + "<br>";
			mailContent += " (7) DRM (프로젝트외주인력보안 과 통합) " + s_system7 + "<br>";  // 20210813 변경
			mailContent += " (8) 분양관리 : " + s_system8 + "<br>";
			mailContent += " (9) 프로젝트 외주인력 보안 : " + s_system9 + "<br>";
			mailContent += " (10) 스마티(안전) : " + s_system10 + "<br><br>";  //// cwh 똑바로 추가

			mail.put("htmlBody", mailContent);

			System.out.println("--------------------------sendMailToMailManager4-----------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");
			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 20211228 CWH 메일 발신 추가
	// 외주인력보안 메일 담당자에게 메일 발송 (연장 시)

	private void sendMailToMailManager10(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("--------------------------sendMailToMailManager10---------------------------");
		System.out.println("연장시 안내메일(프로젝트 외주인력 보안)을 발송한다.");
		System.out.println("-----------------------------------------------------------");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "14");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";
		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-프로젝트 외주인력 보안 [연장]");
			mail.put("fromMailId", mailFrom);
			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}
			String mailContent = "";
			mailContent += "특별ID 프로젝트 외주인력 보안 [연장] 안내입니다" + "<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 연장 종료일자 : " + map.get("edYmd").toString() + "<br>";
			mailContent += " -신청자 사번 : " + map.get("appnUserId").toString() + "<br>";
			mailContent += " -신청자 성명 : " + map.get("appnUserNm").toString() + "<br>";
			mailContent += " -신청자 조직명 : " + map.get("appnOrgNm").toString() + "<br>";

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");
			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 외주인력보안 메일 담당자에게 메일 발송 (해지 시)
	private void sendMailToMailManager11(Map<String, Object> map) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("--------------------------sendMailToMailManager11---------------------------");
		System.out.println("해지시 안내메일(프로젝트 외주인력 보안)을 발송한다.");
		System.out.println("-----------------------------------------------------------");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "14");
		String mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";
		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-프로젝트 외주인력 보안 [해지]");
			mail.put("fromMailId", mailFrom);
			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}
			String mailContent = "";
			mailContent += "특별ID 프로젝트 외주인력 보안 [해지] 안내입니다" + "<br><br>";
			mailContent += " -특별ID 사번 : " + map.get("userId").toString() + "<br>";
			mailContent += " -특별ID 성명 : " + map.get("userNm").toString() + "<br>";
			mailContent += " -특별ID 해지 종료일자 : " + map.get("edYmd").toString() + "<br>";
			mailContent += " -신청자 사번 : " + map.get("appnUserId").toString() + "<br>";
			mailContent += " -신청자 성명 : " + map.get("appnUserNm").toString() + "<br>";
			mailContent += " -신청자 조직명 : " + map.get("appnOrgNm").toString() + "<br>";

			mail.put("htmlBody", mailContent);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");
			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 20211228 CWH 메일 발신 추가


	private void sendMailToDrafter(List<DaewooEmp> ds_DaewooEmp, Map<String, Object> data) {
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		try {
			System.out.println("안내메일(결재완료)을 발송한다.");
			// to email 주소 get
			String mailTo = data.get("userId").toString() + "@daewooenc.com";
			// from email 주소 get
			String mailFrom = "cmasadmin@daewooenc.com";

			Map<String, Object> mail = new HashMap<String, Object>();
			String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
			mail.put("createDate", createDate);
			mail.put("fromMailName", "특별ID-자동발송");
			mail.put("mailSubject", "특별ID-결재완료 안내");
			mail.put("fromMailId", mailFrom);
			if ( !mailTestMode ) {
				mail.put("mailId", mailTo);
			}

			String htmlBodyStart = "<span style='font-size:12px;'>";
			String htmlBody = "[본 메일은 특별ID 신청자에게 발송 되었습니다.]<br>";
			htmlBody += "다음 특별ID는 결재완료되었고 내일 시스템에서 자동으로 생성됩니다.<br><br>";
			for(int i=0; i<ds_DaewooEmp.size(); i++){
				htmlBody += ds_DaewooEmp.get(i).getEmpId() + " (" + ds_DaewooEmp.get(i).getEmpNm() + " ";
				htmlBody += ds_DaewooEmp.get(i).getTitleType() + ")<br>";
			}
			htmlBody += "<br>";
			htmlBody += "최초 비밀번호는 특별ID와 동일(대문자, SVPN은 소문자)<br>";
			htmlBody += "[예] 특별ID가 ZA99999인 경우,<br>";
			htmlBody += "접속ID : ZA99999 (SVPN은 za99999)<br>";
			htmlBody += "접속PW : ZA99999 (SVPN은 za99999)<br>";
			htmlBody += "<br>";
			htmlBody += "[시스템별 ID 담당자]<br>";
			htmlBody += " -바로넷 : IT운영팀 차장 제창현(4063)<br>";
			htmlBody += " -메일 : IT운영팀 대리 이진혁 (3464)<br>";
			htmlBody += " -바로콘 : IT운영팀 과장 신동길 (2068)<br>";
			htmlBody += " -모바일(One Touch HSE-Q) : 안전운영팀 대리 박진석 (4435)<br>";
			htmlBody += " -바로미 : IT운영팀 차장 제창현 (4063)<br>";            // 20211019  CWH 담당자 변경
			htmlBody += " -SVPN : IT기획팀 차장 장승호 (4357)<br>";
//			htmlBody += " -DRM(문서보안) : 정보보호팀 대리 조예진 (5901)<br>";
			htmlBody += " -분양관리 : 판매홍보팀 과장 이혜용 (4461)<br>";
			htmlBody += " -외주인력보안 : 정보보호팀 대리 조예진 (5901)<br>";
			htmlBody += " -스마트세이프티(안전) : 안전혁신팀 과장 김정규 (2182)<br>";

			String htmlBodyEnd = "</span>";

			mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

			System.out.println("-------------------------------------------------------");
			System.out.println("mailSubject :"+mail.get("mailSubject"));
			System.out.println("fromMailId :"+mail.get("fromMailId"));
			System.out.println("fromMailName :"+mail.get("fromMailName"));
			System.out.println("mailId :"+mail.get("mailId"));
			System.out.println("-------------------------------------------------------");

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> sendMailForExpId()	{
		Map<String, Object> resMap = new HashMap();
		System.out.println("종료안내메일을 발송한다.");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String before = "30";
		paraMap.put("before", before);	// 30일 이전
		String mailTo = "";
/**///	String mailToBcc //20211230 CWH - 관리자 숨은참조 메일 추가
		Map<String, Object> paraMap2 = new HashMap<String, Object>();
		paraMap2.put("sysCd", "15");
		String mailToBcc = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap2, String.class);
/**///	String mailToBcc //20211230 CWH - 관리자 숨은참조 메일 추가

		// 종료 예정 ID 검색
		List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveExpId", paraMap, DaewooEmp.class); //20220610 CWH c51Dao ->Dao 변경 후 저장 테스트
		// 종료 예정 ID List (DBPICM 용)
		List<String> userIdList = new ArrayList<String>();
		String userIdListEmail = "";

		String mailFrom = "cmasadmin@daewooenc.com";

		// 종료 예정 대상 ID
		if(ds_DaewooEmp.size() > 0){
			for(int i=0; i<ds_DaewooEmp.size(); i++){
				userIdList.add(ds_DaewooEmp.get(i).getEmpId());

				if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")
						&& !ObjUtil.isNull(ds_DaewooEmp.get(i).getComEmail())
						&& !ds_DaewooEmp.get(i).getComEmail().equals("")
				  ){	//dwenc.com 메일

					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getComEmail()+ ";";
				}else{	//daewooenc.com 메일
					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getEmpId() + "@daewooenc.com;";
				}

			}
		}

		if(userIdList.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userIdList", userIdList);
			List<IdAppnDtl> ds_IdAppnDtl = dao.queryForList("IdAppn.retrieveExpIdForMail", map, IdAppnDtl.class);

			// 종료 예정 대상 ID에 메일 발송
			try{
				Map<String, Object> mail = new HashMap<String, Object>();
				String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
				mail.put("createDate", createDate);
				mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
				mail.put("fromMailId", mailFrom);
				mail.put("fromMailName", "특별ID-자동발송");
				mail.put("mailId", userIdListEmail);
				String htmlBodyStart = "<span style='font-size:12px;'>";
				String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
								+ "사용 중 이신 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
								+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
								+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
								+ "[English Version]<br>"
								+ "Please note that your ID will be expired after " + before + " days.<br>"
								+ "You should extend your expiration date for continuous use of your ID.<br>"
								+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
								+ "This email is only for sending. Do not reply to this email.";
				String htmlBodyEnd = "</span>";
				mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

				System.out.println("-------------------------------------------------------");
				System.out.println("mailSubject :"+mail.get("mailSubject"));
				System.out.println("fromMailId :"+mail.get("fromMailId"));
				System.out.println("fromMailName :"+mail.get("fromMailName"));
				System.out.println("mailId :"+mail.get("mailId"));
				System.out.println("-------------------------------------------------------");

				mailUtil.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("TYPE", "FAIL");
				resMap.put("MSG", "Message Content");
			}

			// 종료 예정 대상 ID의 신청자에게 메일 발송
			if(ds_IdAppnDtl.size() > 0){
				for(int i=0; i<ds_IdAppnDtl.size(); i++){
					try{
						Map<String, Object> mail = new HashMap<String, Object>();
						String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
						mail.put("createDate", createDate);
						mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
						mail.put("fromMailId", mailFrom);
						mail.put("fromMailName", "특별ID-자동발송");
						mail.put("mailId", ds_IdAppnDtl.get(i).getAppnUserId() + "@daewooenc.com");
						mail.put("mailBCCId", mailToBcc); //202111230 CWH - 관리자 숨은참조 메일 추가
						String htmlBodyStart = "<span style='font-size:12px;'>";
						String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
										+ "아래의 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
										+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
										+ "----- 아 래 -------------------------------<br>"
										+ ds_IdAppnDtl.get(i).getUserId().replace(",", "<br>") + "<br>"
										+ "--------------------------------------------<br><br>"

										+ "----- 특별ID 연장 방법 --------------------<br>"
										+ "&nbsp; *메뉴위치 &nbsp;>&nbsp; 바로넷 &nbsp;>&nbsp; 바로지원 &nbsp;>&nbsp; (IT도우미) 시스템 ID신청 &nbsp;>&nbsp; (좌측메뉴) 특별ID 신청 <br>"
										+ "&nbsp;&nbsp;&nbsp; 1) 연장버튼 클릭 <br>"
										+ "&nbsp;&nbsp;&nbsp; 2) [특별ID 연장 신청] 화면에서 추가버튼 클릭하여 추가대상 선택 <br>"
										+ "&nbsp;&nbsp;&nbsp; 3) 종료일자 입력 <br>"
										+ "&nbsp;&nbsp;&nbsp; 4) 상신버튼 클릭 <br>"
										+ "-------------------------------------------------<br><br>"

										+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
										+ "[English Version]<br>"
										+ "Please note that your ID will be expired after " + before + " days.<br>"
										+ "You should extend your expiration date for continuous use of your ID.<br>"
										+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
										+ "This email is only for sending. Do not reply to this email.";
						String htmlBodyEnd = "</span>";
						mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

						System.out.println("-------------------------------------------------------");
						System.out.println("mailSubject :"+mail.get("mailSubject"));
						System.out.println("fromMailId :"+mail.get("fromMailId"));
						System.out.println("fromMailName :"+mail.get("fromMailName"));
						System.out.println("mailId :"+mail.get("mailId"));
						System.out.println("-------------------------------------------------------");

						mailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("TYPE", "FAIL");
						resMap.put("MSG", "Message Content");
					}
				}
			}
		}

		resMap.put("TYPE", "SUCCESS");
		return resMap;
	}

	public Map<String, Object> sendMailForExpId10()	{
		Map<String, Object> resMap = new HashMap();
		System.out.println("종료안내메일을 발송한다.");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String before = "10";
		paraMap.put("before", before);	// 10일 이전
		String mailTo = "";
		String mailContent = "";

		// 종료 예정 ID 검색
		List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveExpId", paraMap, DaewooEmp.class); //20220610 CWH c51Dao ->Dao 변경 후 저장 테스트
		// 종료 예정 ID List (DBPICM 용)
		List<String> userIdList = new ArrayList<String>();
		String userIdListEmail = "";

		String mailFrom = "cmasadmin@daewooenc.com";

		// 종료 예정 대상 ID
		if(ds_DaewooEmp.size() > 0){
			for(int i=0; i<ds_DaewooEmp.size(); i++){
				userIdList.add(ds_DaewooEmp.get(i).getEmpId());

				if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")
						&& !ObjUtil.isNull(ds_DaewooEmp.get(i).getComEmail())
						&& !ds_DaewooEmp.get(i).getComEmail().equals("")
				  ){	//dwenc.com 메일

					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getComEmail()+ ";";
				}else{	//daewooenc.com 메일
					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getEmpId() + "@daewooenc.com;";
				}

			}
		}

		if(userIdList.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userIdList", userIdList);
			List<IdAppnDtl> ds_IdAppnDtl = dao.queryForList("IdAppn.retrieveExpIdForMail", map, IdAppnDtl.class);

			// 종료 예정 대상 ID에 메일 발송
			try{
				Map<String, Object> mail = new HashMap<String, Object>();
				String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
				mail.put("createDate", createDate);
				mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
				mail.put("fromMailId", mailFrom);
				mail.put("fromMailName", "특별ID-자동발송");
				mail.put("mailId", userIdListEmail);
				String htmlBodyStart = "<span style='font-size:12px;'>";
				String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
								+ "사용 중 이신 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
								+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
								+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
								+ "[English Version]<br>"
								+ "Please note that your ID will be expired after " + before + " days.<br>"
								+ "You should extend your expiration date for continuous use of your ID.<br>"
								+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
								+ "This email is only for sending. Do not reply to this email.";
				String htmlBodyEnd = "</span>";
				mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

				System.out.println("-------------------------------------------------------");
				System.out.println("mailSubject :"+mail.get("mailSubject"));
				System.out.println("fromMailId :"+mail.get("fromMailId"));
				System.out.println("fromMailName :"+mail.get("fromMailName"));
				System.out.println("mailId :"+mail.get("mailId"));
				System.out.println("-------------------------------------------------------");

				mailUtil.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("TYPE", "FAIL");
				resMap.put("MSG", "Message Content");
			}

			// 종료 예정 대상 ID의 신청자에게 메일 발송
			if(ds_IdAppnDtl.size() > 0){
				for(int i=0; i<ds_IdAppnDtl.size(); i++){
					try{
						Map<String, Object> mail = new HashMap<String, Object>();
						String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
						mail.put("createDate", createDate);
						mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
						mail.put("fromMailId", mailFrom);
						mail.put("fromMailName", "특별ID-자동발송");
						mail.put("mailId", ds_IdAppnDtl.get(i).getAppnUserId() + "@daewooenc.com");
						String htmlBodyStart = "<span style='font-size:12px;'>";
						String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
										+ "아래의 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
										+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
										+ "----- 아 래 -------------------------------<br>"
										+ ds_IdAppnDtl.get(i).getUserId().replace(",", "<br>") + "<br>"
										+ "--------------------------------------------<br><br>"

										+ "----- 특별ID 연장 방법 --------------------<br>"
										+ "&nbsp; *메뉴위치 &nbsp;>&nbsp; 바로넷 &nbsp;>&nbsp; 바로지원 &nbsp;>&nbsp; (IT도우미) 시스템 ID신청 &nbsp;>&nbsp; (좌측메뉴) 특별ID 신청 <br>"
										+ "&nbsp;&nbsp;&nbsp; 1) 연장버튼 클릭 <br>"
										+ "&nbsp;&nbsp;&nbsp; 2) [특별ID 연장 신청] 화면에서 추가버튼 클릭하여 추가대상 선택 <br>"
										+ "&nbsp;&nbsp;&nbsp; 3) 종료일자 입력 <br>"
										+ "&nbsp;&nbsp;&nbsp; 4) 상신버튼 클릭 <br>"
										+ "-------------------------------------------------<br><br>"

										+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
										+ "[English Version]<br>"
										+ "Please note that your ID will be expired after " + before + " days.<br>"
										+ "You should extend your expiration date for continuous use of your ID.<br>"
										+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
										+ "This email is only for sending. Do not reply to this email.";
						String htmlBodyEnd = "</span>";
						mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

						System.out.println("-------------------------------------------------------");
						System.out.println("mailSubject :"+mail.get("mailSubject"));
						System.out.println("fromMailId :"+mail.get("fromMailId"));
						System.out.println("fromMailName :"+mail.get("fromMailName"));
						System.out.println("mailId :"+mail.get("mailId"));
						System.out.println("-------------------------------------------------------");

						mailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("TYPE", "FAIL");
						resMap.put("MSG", "Message Content");
					}
				}
			}
		}

		resMap.put("TYPE", "SUCCESS");
		return resMap;
	}

	public Map<String, Object> sendMailForExpId5()	{
		Map<String, Object> resMap = new HashMap();
		System.out.println("종료안내메일을 발송한다.");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String before = "5";
		paraMap.put("before", before);	// 5일 이전
		String mailTo = "";
		String mailContent = "";


		// 종료 예정 ID 검색
		List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveExpId", paraMap, DaewooEmp.class); //20220610 CWH c51Dao ->Dao 변경 후 저장 테스트
		// 종료 예정 ID List (DBPICM 용)
		List<String> userIdList = new ArrayList<String>();
		String userIdListEmail = "";

		String mailFrom = "cmasadmin@daewooenc.com";

		// 종료 예정 대상 ID
		if(ds_DaewooEmp.size() > 0){
			for(int i=0; i<ds_DaewooEmp.size(); i++){
				userIdList.add(ds_DaewooEmp.get(i).getEmpId());

				if(ds_DaewooEmp.get(i).getSystem4().toString().equals("3")
						&& !ObjUtil.isNull(ds_DaewooEmp.get(i).getComEmail())
						&& !ds_DaewooEmp.get(i).getComEmail().equals("")
				  ){	//dwenc.com 메일

					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getComEmail()+ ";";
				}else{	//daewooenc.com 메일
					userIdListEmail = userIdListEmail + ds_DaewooEmp.get(i).getEmpId() + "@daewooenc.com;";
				}

			}
		}

		if(userIdList.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userIdList", userIdList);
			List<IdAppnDtl> ds_IdAppnDtl = dao.queryForList("IdAppn.retrieveExpIdForMail", map, IdAppnDtl.class);

			// 종료 예정 대상 ID에 메일 발송
			try{
				Map<String, Object> mail = new HashMap<String, Object>();
				String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
				mail.put("createDate", createDate);
				mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
				mail.put("fromMailId", mailFrom);
				mail.put("fromMailName", "특별ID-자동발송");
				mail.put("mailId", userIdListEmail);
				String htmlBodyStart = "<span style='font-size:12px;'>";
				String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
								+ "사용 중 이신 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
								+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
								+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
								+ "[English Version]<br>"
								+ "Please note that your ID will be expired after " + before + " days.<br>"
								+ "You should extend your expiration date for continuous use of your ID.<br>"
								+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
								+ "This email is only for sending. Do not reply to this email.";
				String htmlBodyEnd = "</span>";
				mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

				System.out.println("-------------------------------------------------------");
				System.out.println("mailSubject :"+mail.get("mailSubject"));
				System.out.println("fromMailId :"+mail.get("fromMailId"));
				System.out.println("fromMailName :"+mail.get("fromMailName"));
				System.out.println("mailId :"+mail.get("mailId"));
				System.out.println("-------------------------------------------------------");

				mailUtil.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("TYPE", "FAIL");
				resMap.put("MSG", "Message Content");
			}

			// 종료 예정 대상 ID의 신청자에게 메일 발송
			if(ds_IdAppnDtl.size() > 0){
				for(int i=0; i<ds_IdAppnDtl.size(); i++){
					try{
						Map<String, Object> mail = new HashMap<String, Object>();
						String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
						mail.put("createDate", createDate);
						mail.put("mailSubject", "특별ID " + before + "일 후, 종료 예정 안내(Notice of ID expiration)");
						mail.put("fromMailId", mailFrom);
						mail.put("fromMailName", "특별ID-자동발송");
						mail.put("mailId", ds_IdAppnDtl.get(i).getAppnUserId() + "@daewooenc.com");
						String htmlBodyStart = "<span style='font-size:12px;'>";
						String htmlBody = "[본 메일은 종료 예정인 특별ID 신청자 및 대상자에게 발송 되었습니다.]<br><br>"
										+ "아래의 특별ID가 " + before + "일 후에 종료될 예정이오니,<br>"
										+ "종료일자 연장이 필요하신 경우, 필히 연장신청하시기 바랍니다.<br><br>"
										+ "----- 아 래 -------------------------------<br>"
										+ ds_IdAppnDtl.get(i).getUserId().replace(",", "<br>") + "<br>"
										+ "--------------------------------------------<br><br>"

										+ "----- 특별ID 연장 방법 --------------------<br>"
										+ "&nbsp; *메뉴위치 &nbsp;>&nbsp; 바로넷 &nbsp;>&nbsp; 바로지원 &nbsp;>&nbsp; (IT도우미) 시스템 ID신청 &nbsp;>&nbsp; (좌측메뉴) 특별ID 신청 <br>"
										+ "&nbsp;&nbsp;&nbsp; 1) 연장버튼 클릭 <br>"
										+ "&nbsp;&nbsp;&nbsp; 2) [특별ID 연장 신청] 화면에서 추가버튼 클릭하여 추가대상 선택 <br>"
										+ "&nbsp;&nbsp;&nbsp; 3) 종료일자 입력 <br>"
										+ "&nbsp;&nbsp;&nbsp; 4) 상신버튼 클릭 <br>"
										+ "-------------------------------------------------<br><br>"

										+ "※ 본 메일은 회신할 수 없습니다.<br><br><br>"
										+ "[English Version]<br>"
										+ "Please note that your ID will be expired after " + before + " days.<br>"
										+ "You should extend your expiration date for continuous use of your ID.<br>"
										+ "If you need to extend your ID, Please ask DAEWOO's site employee.<br><br>"
										+ "This email is only for sending. Do not reply to this email.";
						String htmlBodyEnd = "</span>";
						mail.put("htmlBody", htmlBodyStart + htmlBody + htmlBodyEnd);

						System.out.println("-------------------------------------------------------");
						System.out.println("mailSubject :"+mail.get("mailSubject"));
						System.out.println("fromMailId :"+mail.get("fromMailId"));
						System.out.println("fromMailName :"+mail.get("fromMailName"));
						System.out.println("mailId :"+mail.get("mailId"));
						System.out.println("-------------------------------------------------------");

						mailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("TYPE", "FAIL");
						resMap.put("MSG", "Message Content");
					}
				}
			}
		}

		resMap.put("TYPE", "SUCCESS");
		return resMap;
	}

	public Map<String, Object> sendMailToSVPNManager()	{
		Map<String, Object> resMap = new HashMap();
		boolean mailTestMode = Boolean
                .parseBoolean(StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")) == null ? "false"
                        : StringUtil.getText(appProperties.getProperty("dwe.mail.default.testMode")));

		System.out.println("SVPN 종료안내메일을 발송한다.");
		// to email 주소 get
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sysCd", "05");
		String mailTo = "";
		String mailContent = "";

		mailTo = dao.queryForObject("IdAppn.retrievePerchrgId", paraMap, String.class);
		List<DaewooEmp> ds_DaewooEmp = dao.queryForList("IdAppn.retrieveSvpnExpId", null, DaewooEmp.class);//20220610 CWH c51Dao ->Dao 변경 후 저장 테스트
		// from email 주소 get
		String mailFrom = "cmasadmin@daewooenc.com";

		try {
			Map<String, Object> mail = new HashMap<String, Object>();
			if(mailTo != null && ds_DaewooEmp.size() > 0){
				for(int i=0; i<ds_DaewooEmp.size(); i++){
					mailContent = mailContent + ds_DaewooEmp.get(i).getEmpId() +
							" (" + ds_DaewooEmp.get(i).getEmpNm() + "," + ds_DaewooEmp.get(i).getOrgNm() + ") "
							+ "종료일:" + ds_DaewooEmp.get(i).getIdEndYmd() + "<br>";
				}

				String createDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(System.currentTimeMillis()));
				mail.put("createDate", createDate);
				mail.put("mailSubject", "특별ID-SVPN 종료 안내");
				mail.put("fromMailId", mailFrom);
				mail.put("fromMailName", "특별ID-자동발송");
				if ( !mailTestMode ) {
					mail.put("mailId", mailTo);
				}
				mail.put("htmlBody", mailContent);

				System.out.println("-------------------------------------------------------");
				System.out.println("mailSubject :"+mail.get("mailSubject"));
				System.out.println("fromMailId :"+mail.get("fromMailId"));
				System.out.println("fromMailName :"+mail.get("fromMailName"));
				System.out.println("mailId :"+mail.get("mailId"));
				System.out.println("-------------------------------------------------------");

				mailUtil.sendMail(mail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}

		resMap.put("TYPE", "SUCCESS");
		return resMap;
	}
}

