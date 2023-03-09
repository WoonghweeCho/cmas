package com.dwenc.cmas.common.file.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : COA0309  첨부파일 공통 컴포넌트
 * 설 명    : 첨부파일 공통 서비스 클래스
 * 작 성 자 : 변형구
 * 작성일자 : 2011-11-02
 * 수정이력 : 문서관리 연동 로직 추가
 *           ECM 연동 로직 추가
 * --------------------------------------------------------------------
 * 수정일 이 름 사유
 * --------------------------------------------------------------------
 * 2011-11-02 변형구 최초 작성
 * 2012-10-10 변형구 문서관리 연동로직 및 ECM 연동로직 추가 분리된 클래스 통합
 * --------------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;
import jcf.sua.mvc.file.MciPersistenceManager;
import jcf.upload.FileInfo;
import jcf.upload.handler.DownloadEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.file.domain.CoFile;
import com.dwenc.cmas.id.domain.IdAppnDtl;

import docfbaro.common.Constants;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;
import docfbaro.sua.persistence.file.CommonPersistenceManager;
import docfbaro.sua.persistence.file.policy.FilePolicy;
import docfbaro.sua.persistence.file.policy.FilePolicyManager;

@Service
public class FileService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    /**
     * DB 처리를 위한 공통 dao
     */
    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;


    @Autowired
	private CommonPersistenceManager basePersistence;

    @Autowired
	private MciPersistenceManager persistence;  // framework file persistence manager



    /**
	 * file atch id 조회
	 *
	 * @return file_atch_id
	 */
	public String retrieveFileAtchId() {
		return dao.queryForObject("commonFile.retrieveWebFileAtchId", new HashMap<String, Object>(), String.class);
	}

    /**
	 * file atch id 조회
	 *
	 * @return file_atch_id
	 */
	public String retrieveZipFileAtch(Map input) {
		return dao.queryForObject("commonFile.retrieveZipFileAtch", input, String.class);
	}

	/**
	 * 파일 정보 조회
	 *
	 * @param  input  ( fileAtchId, fileId )
	 * @return 파일 정보 한건
	 */
	public Map<String, Object> retrieveFileInfo(Map input) {
		return dao.queryForMap("commonFile.retrieveFileInfo", input);
	}

	/**
	 * 파일목록정보 조회
	 *
	 * @param  input  ( fileAtchId )
	 * @return file_atch_id 의 모든 파일 목록
	 */
	public List<Map<String, Object>> retrieveFilesListInfo(Map input) {
		return dao.queryForMapList("commonFile.retrieveFileList", input);
	}


	/**
	 * co_file_atch 정보 저장
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void saveWebFileMaster(String fileAtchId) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		String userId = UserInfo.getUserId();

		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(param.get("fstRegUserId")).equals("")) {
				param.put("fstRegUserId", "SYSTEM");
			}
		}
		else {
			if (StringUtil.getText(param.get("fstRegUserId")).equals("")) {
				param.put("fstRegUserId", userId);
			}
		}

		param.put("useYn", "Y");
		param.put("fileAtchId", fileAtchId);
		dao.update("commonFile.insertWebCoFileAtch", param);
	}


	public Map<String, Object> copyFileAtch(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();
		try{
			dao.update("commonFile.copyFileAtch", map);
			dao.update("commonFile.copyFile", map);
			resMap.put("TYPE", "SUCCESS");
			resMap.put("MSG", "첨부파일 복사가 완료 되었습니다.");
		}catch(Exception e){
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}
		return resMap;

	}

	/**
	 * 결재를 위한 파일 정보 저장
	 *
	 * @param fileList 파일 정보 리스트
	 * @exception Exception
	 */
	public String saveFileInfo4Sign(List<Map<String, Object>> fileList) throws Exception {
		if ( fileList.size() <= 0) {
			return null;
		}

		String fileAtchId = retrieveFileAtchId();
		saveWebFileMaster(fileAtchId);

		for ( int i = 0; i < fileList.size(); i ++ ) {
			Map<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("fileAtchId"	, fileAtchId);
			fileMap.put("sysCd"			, fileList.get(i).get("dutySysCd"));
			fileMap.put("filePath"		, fileList.get(i).get("filePath"));
			fileMap.put("fileNm"		, fileList.get(i).get("fileNm"));
			fileMap.put("fileSize"		, fileList.get(i).get("fileSize"));
			fileMap.put("useYn"			, "Y");
			fileMap.put("fstRegUserId"	, fileList.get(i).get("signUserId"));

			dao.update("commonFile.insertWebCoFile", fileMap);
		}
		return fileAtchId;
	}

	/**
	 * 파일정보 저장
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void saveWebFileInfo(String fileAtchId, Map<String, Object> data, List<FileInfo> clientFiieInfos, List<FileInfo> serverFileInfos) throws Exception {

		String userId = UserInfo.getUserId();

		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(data.get("fstRegUserId")).equals("")) {
				userId = "SYSTEM";
			}
			else {
				userId = StringUtil.getText(data.get("fstRegUserId"));
			}
		}


		// co_file insert
		Map<String, Object> fileIdMap = new HashMap<String,Object>();
		fileIdMap.put("fileAtchId", fileAtchId);
        int fileId = dao.queryForObject("commonFile.retrieveWebFileId", fileIdMap, java.lang.Integer.class);

		if (ObjUtil.isNull(fileId)) {
			throw new RuntimeException("fileId 정보가 없습니다.");
		}

		for (int i = 0; i < clientFiieInfos.size(); i++) {
			Map<String, Object> fileMap = new HashMap<String, Object>();

			FileInfo clientFileInfo = clientFiieInfos.get(i);
			FileInfo serverFileInfo = serverFileInfos.get(i);

			String fileNm = clientFileInfo.getCallName();
			String filePath = serverFileInfo.getFolder() + "/" + serverFileInfo.getName();

			fileMap.put("fileAtchId"	, fileAtchId);
			fileMap.put("sysCd"			, Constants.sysCd);
			fileMap.put("filePath"		, filePath);
			fileMap.put("fileNm"		, fileNm);
			fileMap.put("fileSize"		, clientFileInfo.getLength() + "");
			fileMap.put("useYn"			, "Y");
			fileMap.put("fstRegUserId"	, userId);

			dao.update("commonFile.insertWebCoFile", fileMap);
		}
	}

	/**
	 * 파일정보 저장
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void saveEcmFileInfo(String fileAtchId, Map<String, Object> data, List<FileInfo> clientFileInfos, List<String> ecmIdList ) throws Exception {

		String userId = UserInfo.getUserId();

		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(data.get("fstRegUserId")).equals("")) {
				userId = "SYSTEM";
			}
			else {
				userId = StringUtil.getText(data.get("fstRegUserId"));
			}
		}


		// co_file insert
		Map<String, Object> fileIdMap = new HashMap<String,Object>();
		fileIdMap.put("fileAtchId", fileAtchId);
        int fileId = dao.queryForObject("commonFile.retrieveWebFileId", fileIdMap, java.lang.Integer.class);

		if (ObjUtil.isNull(fileId)) {
			throw new RuntimeException("fileId 정보가 없습니다.");
		}

		for (int i = 0; i < clientFileInfos.size(); i++) {
			Map<String, Object> fileMap = new HashMap<String, Object>();

			FileInfo clientFileInfo = clientFileInfos.get(i);
			String ecmId = ecmIdList.get(i);

			String fileNm = clientFileInfo.getCallName();

			String filePath = clientFileInfo.getFolder() + "/" + clientFileInfo.getCallName();

			fileMap.put("fileAtchId"	, fileAtchId);
			fileMap.put("sysCd"			, Constants.sysCd);
			fileMap.put("filePath"		, filePath);
			fileMap.put("fileNm"		, fileNm);
			fileMap.put("fileSize"		, clientFileInfo.getLength() + "");
			fileMap.put("useYn"			, "Y");
			fileMap.put("fstRegUserId"	, userId);
			fileMap.put("ecmNo"	, ecmId);

			dao.update("commonFile.insertWebCoFile", fileMap);
		}
	}


	/**
	 * 파일정보 저장
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void saveEcmActiveXFileInfo(String fileAtchId, Map<String, Object> data, GridData<CoFile> gData) throws Exception {

		String userId = UserInfo.getUserId();

		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(data.get("fstRegUserId")).equals("")) {
				userId = "SYSTEM";
			}
			else {
				userId = StringUtil.getText(data.get("fstRegUserId"));
			}
		}


		// co_file insert
		Map<String, Object> fileIdMap = new HashMap<String,Object>();
		fileIdMap.put("fileAtchId", fileAtchId);
        int fileId = dao.queryForObject("commonFile.retrieveWebFileId", fileIdMap, java.lang.Integer.class);

		if (ObjUtil.isNull(fileId)) {
			throw new RuntimeException("fileId 정보가 없습니다.");
		}

		for (int i = 0; i < gData.size(); i++) {
			Map<String, Object> fileMap = new HashMap<String, Object>();

			CoFile fileInfos = gData.get(i);

			fileMap.put("fileAtchId"	, fileAtchId);
			fileMap.put("sysCd"			, Constants.sysCd);
			fileMap.put("filePath"		, fileInfos.getFilePath());
			fileMap.put("fileNm"		, fileInfos.getFileNm());
			fileMap.put("fileSize"		, fileInfos.getFileSize());
			fileMap.put("useYn"			, "Y");
			fileMap.put("fstRegUserId"	, userId);
			fileMap.put("ecmNo"	, fileInfos.getEcmNo());

			dao.update("commonFile.insertWebCoFile", fileMap);
		}
	}

	/**
	 * 파일 master 정보 삭제
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void deleteFileMasterInfo(Map<String, Object> param) {

		// 사용자 id 설정
		String userId = UserInfo.getUserId();
		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(param.get("fnlEditUserId")).equals("")) {
				 param.put("fnlEditUserId","SYSTEM");
			}
		}
		else {
			if (StringUtil.getText(param.get("fnlEditUserId")).equals("")) {
				 param.put("fnlEditUserId", userId);
			}
		}

		// file master 정보의 삭제는 전체 파일 묶음의 삭제 이기때문에 co_file_atch, co_file 두개 테이블 모두 삭제 처리를 한다.
		// CO_FILE_ATCH USE_YN 컬럼 N으로 업데이트
		dao.update("commonFile.deleteMasterFileInfo", param);
		// CO_FILE USE_YN 컬럼 N으로 업데이트
		dao.update("commonFile.deleteFileInfo", param);

		deleteFileInfo(param);

	}

	public void downloadStream(MciRequest request, MciResponse response , Map<String, Object> inputData, final FilePolicyManager policyManager) throws Exception {

		final String policyId 	= ObjUtil.isNull(inputData.get("policy"))  ? "default" : inputData.get("policy").toString();
        String filePath 		= inputData.get("filePath").toString();
        final String fileNm 	= inputData.get("fileNm").toString();


        int index = filePath.lastIndexOf("/");

        String folder = "";
        String fileName = filePath;
        if(index > -1)	{
        	folder = filePath.substring(0, index);
        	fileName = filePath.substring(index + 1);
        }
        //folder = "excel";
        fileName = fileNm;
        response.setDownloadFile(new DownloadEventHandler() {
        	public String createFileName(FileInfo fileInfo)	{
        		return fileNm;
        	}
			public void preprocess(FileInfo fileInfo) {
				FilePolicy policy = policyManager.getFilePolicy(policyId);

				 //  파일관리 정책상의 위반여부를 체크함. 위반시 예외(@see FilePolicyViolationException)발생.
				policyManager.checkFilePolicy(fileInfo, policy);
			}
		}, new FileInfo(folder, fileName));

        //response.setViewName("/common/jsp/dummy2.jsp");
	}

	/**
	 * 파일 정보 삭제
	 *
	 * @param input 파일정보
	 * @exception Exception
	 */
	public void deleteFileInfo(Map<String, Object> param) {

		// 사용자 id 설정
		String userId = UserInfo.getUserId();
		if (ObjUtil.isNull(userId)) {
			if (StringUtil.getText(param.get("fnlEditUserId")).equals("")) {
				 param.put("fnlEditUserId","SYSTEM");
			}
		}
		else {
			if (StringUtil.getText(param.get("fnlEditUserId")).equals("")) {
				 param.put("fnlEditUserId", userId);
			}
		}

		// CO_FILE USE_YN 컬럼 N으로 업데이트
		dao.update("commonFile.deleteFileInfo", param);
	}




	/**
	  * <pre>
	  *  파일의 컨텐츠 및 mime ,티입을 리턴함
	  * </pre>
	  * @param String 파일 명
	  * @return mime type
	  * */
	public String getType(String s){
		int i = s.lastIndexOf(".");
		if(i > 0 || i < s.length() - 1)
			return getMime(s.substring(i + 1));
	     else
	        return "application/octet-stream";
	}

	/**
	  * <pre>
	  *  파일의 활장자별 mimetype 리턴
	  * </pre>
	  * @param String 파일 확장자
	  * @return mime type
	  * */
	private String getMime(String s){
		String s1 = s.toUpperCase();
		if(s1.equals("GIF"))
			return "image/gif";
		if(s1.equals("JPG") || s1.equals("JPEG") || s1.equals("JPE"))
			return "image/jpeg";
		if(s1.startsWith("TIF"))
         	return "image/tiff";
		if(s1.startsWith("PNG"))
			return "image/png";
     	if(s1.equals("IEF"))
     		return "image/ief";
     	if(s1.equals("BMP"))
     		return "image/bmp";
     	if(s1.equals("RAS"))
     		return "image/x-cmu-raster";
     	if(s1.equals("PNM"))
     		return "image/x-portable-anymap";
     	if(s1.equals("PBM"))
     		return "image/x-portable-bitmap";
     	if(s1.equals("PGM"))
     		return "image/x-portable-graymap";
     	if(s1.equals("PPM"))
          return "image/x-portable-pixmap";
     	if(s1.equals("RGB"))
     		return "image/x-rgb";
     	if(s1.equals("XBM"))
     		return "image/x-xbitmap";
     	if(s1.equals("XPM"))
     		return "image/x-xpixmap";
     	if(s1.equals("XWD"))
     		return "image/x-xwindowdump";
     	else
     		return "application/octet-stream";
	}

}
