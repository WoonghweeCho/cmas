package com.dwenc.cmas.common.tdms.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.sysMng.service.support.FtpUtil;

import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;


/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 통합결재
 * 프로그램 : 통합결재 TDMS 연계 서비스
 * 설    명 : 결재 완료된 문서를 TDMS와 연계 시키는 서비스
 * 작 성 자 : 변형구
 * 작성일자 : 2013-07-04
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2013-07-04            최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service
public class TransferTDMSService {
	protected static final Logger logger = LoggerFactory.getLogger(TransferTDMSService.class);


	/**
	 * DB처리를 위한  공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;


	/*
	 *  결재 문서 정보
	 */
	public int insertBarnetDoc(Map<String, Object> input) {

		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		return commonDao.update("TDMS.insertBarnetDoc", input);
	}

	/*
	 *  결재 문서 정보
	 */
	public int deleteBarnetDoc(Map<String, Object> input) {

		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		return commonDao.update("TDMS.deleteBarnetDoc", input);
	}

	/*
	 * 결재선 정보
	 */
	public int insertBarnetDocSign(Map<String, Object> input) {
		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		return commonDao.update("TDMS.insertBarnetDocSign", input);
	}

	/*
	 * 결재선 정보
	 */
	public void deleteBarnetDocSign(Map<String, Object> input) {
		commonDao.update("TDMS.deleteBarnetDocSign", input);
	}

	/*
	 * url 정보
	 */
	public int insertBarnetDocUrl(Map<String, Object> input) {
		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		input.put("proxyUrl", StringUtil.getText(appProperties.get("dwe.serverInfo.url.context"))+"/common/jsp/sign/proxyDialog.jsp?");
		return commonDao.update("TDMS.insertBarnetDocUrl", input);
	}

	/*
	 * 회람자 정보
	 */
	public int insertBarnetDocReadtn(Map<String, Object> input) {
		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		return commonDao.update("TDMS.insertBarnetDocReadtn", input);
	}

	/*
	 *  바로넷 문서 권한
	 */
	public int insertBarnetDocPriv(Map<String, Object> input) {
		input.put("fstRegUserId", input.get("userId"));
		input.put("fnlEditUserId", input.get("userId"));
		return commonDao.update("TDMS.insertBarnetDocPriv", input);
	}

	/*
	 * 결재선 정보
	 */
	public void deleteBarnetDocPriv(Map<String, Object> input) {
		commonDao.update("TDMS.deleteBarnetDocPriv", input);
	}


	/**
	 * TDMS로 정보이관.
	 * @param depts
	 * @param map
	 */
	@Async
	public void saveAsyncTDMSInterfaceInfo(Map<String, Object> input) {
		saveTDMSInterfaceInfo(input);
	}

	public void saveTDMSInterfaceInfo(Map<String, Object> input) {

		String host = (String) appProperties.get("dwe.tdms.host");
		String user = (String) appProperties.get("dwe.tdms.user");
		String password = (String) appProperties.get("dwe.tdms.password");
		String encode = (String) appProperties.get("dwe.tdms.encode");
		String path = (String) appProperties.get("dwe.serverInfo.upload.default");

		String signId = (String) input.get("signId");
		String fileAtchId = null;
		StringBuffer buffer = new StringBuffer();

		if(input.get("fileAtchId") != null){
			fileAtchId = "" + input.get("fileAtchId");
		}

		try{
			List<Map<String, Object>> fileList = new ArrayList();

			if(fileAtchId != null)
				fileList = commonDao.queryForMapList("commonFile.retrieveFileList", input);

/*			if(fileList.isEmpty())
				return ;
*/

			// 파일 전송 로직.
			FtpUtil tdmsFtp = new FtpUtil(host, user, password, encode);
			tdmsFtp.connect();
			tdmsFtp.mkdir(signId);
			tdmsFtp.cd(signId);
			//logger.error(e.getMessage());

			for(int i = 0 ; i < fileList.size(); i++){
				try{
					String fileName = (String) fileList.get(i).get("fileNm");
					String orgFilePath = path + File.separator + (String) fileList.get(i).get("filePath");
					String uploadPath =  fileName;
					tdmsFtp.uploadFileToString(uploadPath, orgFilePath);
				}catch (Exception e) {
					buffer.append(e.getMessage() + "\r\n");
				}
			}

			tdmsFtp.disconnect();

			// 결재문서를 TDMS에 등록한다.
			insertBarnetDoc(input);

			// 결재선정보를 제거함.
			deleteBarnetDocSign(input);
			// 결재선정보를 재등록함.
			insertBarnetDocSign(input);

			//insertBarnetDocReadtn(input);

			// 회람자정보를 제거함.
			deleteBarnetDocPriv(input);
			// 회람자정보를 재등록함.
			insertBarnetDocPriv(input);

			//문서경로를 등록함.
			input.put("rem", buffer.toString());
			insertBarnetDocUrl(input);
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}


}
