package com.dwenc.cmas.tech.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import jcf.data.GridData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.tech.domain.TechAppn;
import com.dwenc.cmas.tech.domain.TechDat;
import com.dwenc.cmas.common.mail.service.MailService;

import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.exception.BusinessException;


/**
 *
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
public class TechDatService {


	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(TechDatService.class);

	protected static Logger getLogger() {
		return logger;
	}

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	@Autowired
	private MailService mailService;



	public Map<String, Object> getDocNo(Map<String, Object> map) throws BusinessException{
		Map<String, Object> resMap = null;
		resMap = commonDao.queryForMap("TechDatSqlmap.getMaxDocNo", map);
		if(resMap == null || resMap.equals("null")){
			resMap = map;
		}
		return commonDao.queryForMap("TechDatSqlmap.getDocNo", resMap);
	}

	/**
	 * 1. 함수명 변경 : TechDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.insertTechDat
	 * @param map
	 */
	public Map<String, Object> insertTechDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.insertTechDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}


	/**
	 * 1. 함수명 변경 : TechDvdDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.insertTechDvdDat
	 * @param map
	 */
	public Map<String, Object> insertTechDvdDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.insertTechDvdDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechPubDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.insertTechPubDat
	 * @param map
	 */
	public Map<String, Object> insertTechPubDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.insertTechPubDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}

		List<HashMap> licAdmList = commonDao.queryForList("TechDatSqlmap.retrieveLicAdm", map, HashMap.class);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String appnDate = sdFormat.format(nowDate);

		String mailReciver = "";
		String tmpCls = "" ;

		String subject      = (String)map.get("subject");
		String issueTeam    = (String)map.get("issueTeam");
		String cls          = (String)map.get("cls");
		String fstRegUserId = (String)map.get("fstRegUserId");

		if(cls.equals("GR")){
			tmpCls = "서지정보등록" ;
		}

		if(licAdmList.size() > 0){
			for(int i = 0; i < licAdmList.size(); i++){
				mailReciver = mailReciver + licAdmList.get(i).get("privUserId") + "@daewooenc.com;" ;
			}

			StringBuffer html = new StringBuffer();
				html.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
				html.append("<head>");
				html.append("<meta http-equiv='Conte-Type' content='text/html; charset=utf-8'>");
				html.append("<title>[건설기술정보센터]"+ tmpCls +" 신청</title>");
				html.append("</head>");
				html.append("<body style=\"font-family:맑은 고딕;font-size: 12pt\">");
				html.append("<p style='font-size:15pt'> [건설기술정보센터]"+ tmpCls +" 신청</p>");
				html.append("<table border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none;font-size: 10pt;font-family: 맑은 고딕'>");
				html.append("<tr>");
				html.append("<td style='width:80pt;padding:3pt;background: #C6D9F1;text-align: center'>신 청 일</td>");
				html.append("<td style='width:80pt;padding:3pt;background: #C6D9F1;text-align: center'>구 분</td>");
				html.append("<td style='width:350pt;padding:3pt;background: #C6D9F1;text-align: center'>표 제</td>");
				html.append("<td style='width:150pt;padding:3pt;background: #C6D9F1;text-align: center'>발 행 팀</td>");
				html.append("</tr>");

				html.append("<tr>");
				html.append("<td style='padding:5pt;text-align:center'>" + appnDate + "</td>");
				html.append("<td style='padding:5pt;text-align:center'>" + tmpCls + "</td>");
				html.append("<td style='padding:5pt;text-align:center'>" + subject + "</td>");
				html.append("<td style='padding:5pt;text-align:center'>" + issueTeam + "</td>");
				//html.append("<td style='padding:5pt;text-align:center'>" + issueEr + "</td>");
				html.append("</tr>");
				html.append("</table>");
				html.append("</span></body></html>");

			List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

			Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("mailSubject", "[건설기술정보센터]"+ tmpCls +" 신청");
				obj.put("fromMailId", fstRegUserId +"@daewooenc.com");
				obj.put("fromMailName", "");
				obj.put("mailId", mailReciver);
				//obj.put("mailId", "1302148@daewooenc.com,1304252@daewooenc.com,1204594@daewooenc.com, test002@daewooenc.com");
				obj.put("htmlBody", html.toString());
				mData.add(obj);

				logger.debug(obj.get("htmlBody").toString());

				try
				{
					// 메일 전송
					mailService.sendMail(mData);
				}
				catch (Exception e)
				{
					throw new BusinessException("co.err.exec", new Object[] { "email" });
				}
			}

		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechPubDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.insertTechPubDatComp
	 * @param map
	 */
	public Map<String, Object> insertTechPubDatComp(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.insertTechPubDatComp", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}


	/**
	 * 1. 함수명 변경 : TechDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechDat
	 * @param map
	 */
	public Map<String, Object> updateTechDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}
		return resMap;

	}

	/**
	 * 1. 함수명 변경 : TechDvdDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechDvdDat
	 * @param map
	 */
	public Map<String, Object> updateTechDvdDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechDvdDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechDvdDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechPubDat
	 * @param map
	 */
	public Map<String, Object> updateTechPubDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechPubDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechDvdDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechPubDatComp
	 * @param map
	 */
	public Map<String, Object> updateTechPubDatComp(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechPubDatComp", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.deleteTechDat
	 * @param map
	 */
	public Map<String, Object> deleteTechDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.deleteTechDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.selectTechDat
	 * @param map
	 */
	public Map<String, Object> selectTechDat(Map<String, Object> map){
		 Map<String, Object> resMap = commonDao.queryForMap("TechDatSqlmap.selectTechDat", map);
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : 도서/간행물 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechDatList
	 * @param map
	 */
//	public Map<String, Object> retrieveTechDatList(Map<String, Object> map){
//		 Map<String, Object> resMap = commonDao.queryForMap("TechDatSqlmap.retrieveTechDatList", map);
//		return ds_TechDataList;
//	}

	public List<TechDat> retrieveTechDatList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechDatList", params, TechDat.class);
	}

	/**
	 * 1. 함수명 변경 : DVD 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechDvdDatList
	 * @param map
	 */

	public List<TechDat> retrieveTechDvdDatList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechDvdDatList", params, TechDat.class);
	}

	/**
	 * 1. 함수명 변경 : 해외산업규격 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechOsDatList
	 * @param map
	 */

	public List<TechDat> retrieveTechOsDatList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechOsDatList", params, TechDat.class);
	}

	/**
	 * 1. 함수명 변경 : 사내간행물목록 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechPubDatList
	 * @param map
	 */

	public List<TechDat> retrieveTechPubDatList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechPubDatList", params, TechDat.class);
	}

	/**
	 * 1. 함수명 변경 : 사내간행물등록신청 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechPubDatList1
	 * @param map
	 */

	public List<TechDat> retrieveTechPubDatList1(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechPubDatList1", params, TechDat.class);
	}


	/**
	 * 1. 함수명 변경 : 사내간행물 신청 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechPubAppnList
	 * @param map
	 */

	public List<TechAppn> retrieveTechPubAppnList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechPubAppnList", params, TechAppn.class);
	}


	/**
	 * 1. 함수명 변경 : 도서/DVD 신청 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechAppnList
	 * @param map
	 */

	public List<TechAppn> retrieveTechAppnList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechAppnList", params, TechAppn.class);
	}

	/**
	 * 1. 함수명 변경 : 해외산업규격 조회부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.retrieveTechOsAppnList
	 * @param map
	 */

	public List<TechAppn> retrieveTechOsAppnList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechOsAppnList", params, TechAppn.class);
	}


	/**
	 * 1. 함수명 변경 : PubAppnBas 부분
	 * 2. Sqlmap 명 변경 : PubDatSqlmap.insertPubAppnBas
	 * @param map
	 */
	public Map<String, Object> insertPubAppnBas(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{

			commonDao.update("TechDatSqlmap.insertTechAppnBas", map);
			Map<String, Object> docNoMap = new HashMap();
			docNoMap = commonDao.queryForMap("TechDatSqlmap.retrieveAppnBaseDocNo", map);
			map.put("docNo", docNoMap.get("no"));
			commonDao.update("TechDatSqlmap.insertTechAppnDtl", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechAppnBas 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.insertTechAppnBas
	 * @param map
	 */
	public Map<String, Object> insertTechAppnBas(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			// 시퀀스를 받아온다.
			// queryForList
			//Map<String, Object> seqMap = new HashMap();
			//seqMap = commonDao.queryForMap("TechDatSqlmap.retrieveAppnSeq", map);
			//System.out.println(">>>>"+seqMap.get("seq"));
			// map 의 docNo에 넣어준다.
			//map.put("docNo", seqMap.get("seq"));

			commonDao.update("TechDatSqlmap.insertTechAppnBas", map);

			Map<String, Object> docNoMap = new HashMap();
			docNoMap = commonDao.queryForMap("TechDatSqlmap.retrieveAppnBaseDocNo", map);

			System.out.println("docNo >>>> "+docNoMap.get("no"));

			map.put("docNo", docNoMap.get("no"));
			commonDao.update("TechDatSqlmap.insertTechAppnDtl", map);
			commonDao.update("TechDatSqlmap.updateTechAppnDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}


		List<HashMap> licAdmList = commonDao.queryForList("TechDatSqlmap.retrieveLicAdm", map, HashMap.class);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String appnDate = sdFormat.format(nowDate);

		String mailReciver = "";
		String tmpCls = "" ;

		String subject = (String)map.get("subject");
		String orgNm   = (String)map.get("orgNm");
		String userId  = (String)map.get("userId");
		String userNm  = (String)map.get("userNm");
		String cls     = (String)map.get("cls");

		//DVD대여신청은 메일발송에서 제외
		if(!cls.equals("DV")){
			if(cls.equals("BK")){
				tmpCls = "보관자료" ;
			}else{
				tmpCls = "해외산업규격" ;
			}

			if(licAdmList.size() > 0){
				for(int i = 0; i < licAdmList.size(); i++){
					mailReciver = mailReciver + licAdmList.get(i).get("privUserId") + "@daewooenc.com;" ;
				}

				StringBuffer html = new StringBuffer();
					html.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
					html.append("<head>");
					html.append("<meta http-equiv='Conte-Type' content='text/html; charset=utf-8'>");
					html.append("<title>[건설기술정보센터]"+ tmpCls +" 신청</title>");
					html.append("</head>");
					html.append("<body style=\"font-family:맑은 고딕;font-size: 12pt\">");
					html.append("<p style='font-size:15pt'> [건설기술정보센터]"+ tmpCls +" 신청</p>");
					html.append("<table border=1 cellspacing=0 cellpadding=0 style='border-collapse:collapse;border:none;font-size: 10pt;font-family: 맑은 고딕'>");
					html.append("<tr>");
					html.append("<td style='width:80pt;padding:3pt;background: #C6D9F1;text-align: center'>신 청 일</td>");
					html.append("<td style='width:80pt;padding:3pt;background: #C6D9F1;text-align: center'>구 분</td>");
					html.append("<td style='width:350pt;padding:3pt;background: #C6D9F1;text-align: center'>신청내용(제목)</td>");
					html.append("<td style='width:150pt;padding:3pt;background: #C6D9F1;text-align: center'>신 청 팀</td>");
					html.append("<td style='width:100pt;padding:3pt;background: #C6D9F1;text-align: center'>신 청 자</td>");
					html.append("</tr>");

					html.append("<tr>");
					html.append("<td style='padding:5pt;text-align:center'>" + appnDate + "</td>");
					html.append("<td style='padding:5pt;text-align:center'>" + tmpCls + "</td>");
					html.append("<td style='padding:5pt;text-align:center'>" + subject + "</td>");
					html.append("<td style='padding:5pt;text-align:center'>" + orgNm + "</td>");
					html.append("<td style='padding:5pt;text-align:center'>" + userId+" "+userNm + "</td>");
					html.append("</tr>");
					html.append("</table>");
					html.append("</span></body></html>");


				List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

				Map<String, Object> obj = new HashMap<String, Object>();
					obj.put("mailSubject", "[건설기술정보센터]"+ tmpCls +" 신청");
					obj.put("fromMailId", userId+"@daewooenc.com");
					obj.put("fromMailName", userNm);
					obj.put("mailId", mailReciver);
					//obj.put("mailId", "1202540@daewooenc.com,1202588@daewooenc.com");
					obj.put("htmlBody", html.toString());
					mData.add(obj);

					logger.debug(obj.get("htmlBody").toString());

					try
					{
						// 메일 전송
						mailService.sendMail(mData);
					}
					catch (Exception e)
					{
						throw new BusinessException("co.err.exec", new Object[] { "email" });
					}
			}
		}

		return resMap;

	}

	/**
	 * 1. 함수명 변경 : TechAppnDtl 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechOsAppnBas
	 * @param map
	 */
	public Map<String, Object> updateTechOsAppnBas(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechAppnBas", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechAppnDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechAppnDat
	 * @param map
	 */
	public Map<String, Object> updateTechAppnDat(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechAppnDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : TechAppnDat 부분
	 * 2. Sqlmap 명 변경 : TechDatSqlmap.updateTechAppnBas
	 * @param map
	 */
	public Map<String, Object> updateTechAppnBas(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechAppnBas", map);
			commonDao.update("TechDatSqlmap.updateTechAppnDat", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}


	// 공지사항 조회부분
	public List<TechDat> retrieveTechNoticeList(Map<String, Object> params) {
		return commonDao.queryForList("TechDatSqlmap.retrieveTechNoticeList", params, TechDat.class);
	}


	// 공지사항 insert부분
	public Map<String, Object> insertTechNotice(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.insertTechNotice", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	// 공지사항 update부분
	public Map<String, Object> updateTechNotice(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("TechDatSqlmap.updateTechNotice", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}


}

