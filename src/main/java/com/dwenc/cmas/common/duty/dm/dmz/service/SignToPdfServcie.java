package com.dwenc.cmas.common.duty.dm.dmz.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.file.domain.CoFile;
import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.utils.StringUtil;

import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SignToPdfServcie
 * 설    명 : 결재 완료 후 결재 본문의 PDF 변환을 위한 처리를 수행하는 Service
 * 작 성 자 : 한지훈
 * 작성일자 : 2012-05-18
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-05-18            최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

@Service
public class SignToPdfServcie {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(SignToPdfServcie.class);

	/**
	 * DB처리를 위한  공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	@Autowired
	private FileService fileService;

	@Autowired
	private PdfService pdfService;

	/***
	 * CO
	 * @param param
	 * 		signId : 결재번호
	 * @return
	 */
	public void savePdfConvertResult(Map<String, Object> paramMap) throws BusinessException{

		/**
		 * 1. PDF I/F 정보 조회
		 * 2. CO_FILE에 변환된 파일정보 Insert
		 */
		Map<String, Object> retMap = new HashMap<String, Object>();
		paramMap.put("pdfChgId", StringUtil.nvl(paramMap.get("pdfChgId")));
		Map<String,Object> pefChgIfMap = commonDao.queryForMap("signPdf.retrievePdfChgIf", paramMap);
		String pdfChgSts = StringUtil.nvl(pefChgIfMap.get("pdfChgSts"));
		String signId = StringUtil.nvl(pefChgIfMap.get("signId"));
		String clbkParam = StringUtil.nvl(pefChgIfMap.get("clbkParam"));
		String siteCd = StringUtil.nvl(pefChgIfMap.get("siteCd"));

		// 변환이 성공된 경우에만 유효함
		if(!(pdfChgSts.equals("S") || pdfChgSts.equals("Z")))
			throw new BusinessException("false");

		String fildId = "1";
		CoFile coFile = new CoFile();
		coFile.setUseYn("Y");
		coFile.setFileId(new BigDecimal(fildId));
		coFile.setFileNm(StringUtil.nvl((String)pefChgIfMap.get("chgEcmFileNm")));
		coFile.setFileSize(StringUtil.nvl((String)pefChgIfMap.get("chgEcmFileSize")));
		coFile.setEcmNo(StringUtil.nvl(pefChgIfMap.get("chgEcmFileId")));
		coFile.setFstRegUserId("SYSTEM");
		coFile.setFnlEditUserId("SYSTEM");
		coFile.setOggCd(siteCd);
		//coFile.setOggTime(getCurrentDateStringForQuery());

		String fileAtchId = "";//fileService.insertPDFFileInfo(coFile);

		// TDMS 연동을 위한 Parameter 설정
		retMap.put("fileId", fildId);
		retMap.put("signId", signId);
		retMap.put("fileAtchId", fileAtchId);
		retMap.put("ecmNo", StringUtil.nvl(pefChgIfMap.get("chgEcmFileId")));

		retMap.put("chgTp", (String)pefChgIfMap.get("chgTp"));

		if(!clbkParam.equals("")) {
			retMap.put("clbkParam", convertStringToMap(clbkParam));
		}

		if(retMap != null)
			pdfService.saveSignedPdf(retMap);

	}

	/***
	 * PDF 변환 상태를 결재 및 TDMS에 업데이트
	 * @param param
	 * 		signId : 결재번호
	 * @return
	 */
	public void savePdfConvertResultForOnlyTdms(Map<String, Object> paramMap) {

		CoFile coFile = new CoFile();
		coFile.setFstRegUserId("SYSTEM");
		coFile.setFnlEditUserId("SYSTEM");
		coFile.setUseYn("Y");
		coFile.setFileNm(StringUtil.nvl(paramMap.get("fileNm")));
		coFile.setEcmNo(StringUtil.nvl(paramMap.get("ecmNo")));

		String siteCd = StringUtil.nvl(paramMap.get("siteCd"));
		coFile.setOggCd(siteCd);

		String fileAtchId = "";//fileService.insertPDFFileInfo(coFile);

		paramMap.put("outcomFileAtchId", fileAtchId);
		pdfService.outcomSquadFile(paramMap);
	}

	/**
	 * <pre>
	 * 업무시스템에서 전달받아 저장한 Callback Parameter String을 Map으로 변환한다.
	 * </pre>
	 *
	 * @param param
	 *            key=value&key=value ...
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> convertStringToMap(String param) {
		Map<String, Object> map = new HashMap();

		String[] pArr = StringUtil.split(param, "&");
		if (pArr != null) {
			int size = pArr.length;
			for (int i = 0; i < size; i++) {
				if (pArr[i] != null) {
					if (pArr[i].indexOf("=") > 0) {
						String[] arr = StringUtil.split(pArr[i], "=");

						if(arr.length < 2) map.put(arr[0], "");
						else map.put(arr[0], arr[1]);
					}
				}
			}
		}
		return map;
	}

	private String getCurrentDateStringForQuery() {
		java.text.SimpleDateFormat dateFormat  = new java.text.SimpleDateFormat
									         ("yyyyMMddHHmmss", java.util.Locale.KOREA);
		return dateFormat.format(new java.util.Date());
	}
}
