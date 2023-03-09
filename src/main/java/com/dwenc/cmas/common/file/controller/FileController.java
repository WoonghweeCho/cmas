package com.dwenc.cmas.common.file.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.converter.JSONConverter;
import com.dwenc.cmas.common.ecm.service.EcmService;
import com.dwenc.cmas.common.file.domain.CoFile;
import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.FileUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;
import docfbaro.sua.persistence.file.CommonPersistenceManager;
import docfbaro.sua.persistence.file.policy.FilePolicy;
import docfbaro.sua.persistence.file.policy.FilePolicyManager;
import jcf.data.GridData;
import jcf.sua.mvc.file.MciPersistenceManager;
import jcf.upload.FileInfo;
import jcf.upload.MultiPartInfo;
import jcf.upload.handler.DownloadEventHandler;
import jcf.upload.handler.UploadEventHandler;
import jcf.upload.persistence.PersistenceManager;


/** * <pre>
* ---------------------------------------------------------------
* 업무구분 : 공통
* 프로그램 : COA0309
* 설 명    : 통합결재  첨부파일을 위한 Controller 클래스
* 작 성 자 : 변형구
* 작성일자 : 2013-03-21
* 수정이력
* ---------------------------------------------------------------
* 수정일 이 름 사유
* ---------------------------------------------------------------
* 2011-03-21 변형구 최초 작성
* ---------------------------------------------------------------
* </pre>
* @version 1.0 */

@Controller
@RequestMapping("/co/common/file/*")
public class FileController {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService service;                // service class

	@Autowired
	private MciPersistenceManager persistence;  // framework file persistence manager

	@Autowired
	private FilePolicyManager policyManager;    // framework file policy manager

	@Autowired
	private CommonPersistenceManager basePersistence;

	@Autowired
	private EcmService ecmService;

	private String noImagePath;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	/**
	  * <pre>
	  * 첨부파일 목록 조회
	  * </pre>
	  * @param request
	  * @param response
	  * */
	@RequestMapping("retrieveWebFileList.*")
	public void retrieveWebFileList(final MciRequest request, MciResponse response) throws Exception {

		Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> fileList = service.retrieveFilesListInfo(param);

		response.setList("fileList", fileList);
	}


	/**
	  * <pre>
	  * 신규 fileAtchId 조회ㅏ
	  * </pre>
	  * @param request
	  * @param response
	  * */
	@RequestMapping("retrieveFileAtchId.*")
	public void retrieveFileAtchId(final MciRequest request, MciResponse response) throws Exception {

		Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
		String newFileAtchId = service.retrieveFileAtchId();

		if ( StringUtil.getText(inputData.get("saveYn")).equals("Y") ) {
			service.saveWebFileMaster(newFileAtchId);
		}

		Map<String, Object> fileAtchId = new HashMap<String, Object>();
		fileAtchId.put("fileAtchId", newFileAtchId);
		response.setMap("fileAtchId", fileAtchId);
	}
	/**
	  * <pre>
	  * 첨부파일 업로드 및 파일 정보 저장
	  * </pre>
	  * @param request
	  * @param response
	  * */
  @RequestMapping("uploadEcmFile.*")
  public void uploadEcmFile(MciRequest request, MciResponse response) throws Exception {

	   try {
  		/*
  		 * @TODO 파일관리 정책은 약속된 Parameter 로 수신합니다. example) request.getParam("...")
  		 */
		logger.debug("=================== ECM UPLOAD ====================");
		HttpServletRequest req = WebContext.getRequest();
		HttpServletResponse res = WebContext.getResponse();
		String contextPath = req.getContextPath();
		final Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

		String tempFid = "";
		String[] tempFid2;

		if(ObjUtil.isNull(inputData.get("fileAtchId"))){
			tempFid = service.retrieveFileAtchId();
		}else{
			if( inputData.get("fileAtchId") instanceof String[]){
				tempFid2 = (String[]) inputData.get("fileAtchId");
				tempFid = tempFid2[0];
			}else if( inputData.get("fileAtchId") instanceof String){
				tempFid = inputData.get("fileAtchId").toString();
			}
		}

		final String fileAtchId = tempFid;

       // 이부분은 DEXTUploadX 를 사용할때만 동작한다.
		// 이미 저장된 파일 정보의 재저장시 삭제된 파일에 대한 정보를 처리한다.
		if ( !ObjUtil.isNull(inputData.get("fileAtchId")) ) {
			// DextuploadX 에서 삭제 한 파일의 정보가 넘어옴 input map 의 DEXTUploadX_Deleted_Uploaded 라는 key 값으로 String[] 이 넘어옴
			if ( !ObjUtil.isNull(inputData.get("DEXTUploadX_Deleted_Uploaded")) ) {
				if ( inputData.get("DEXTUploadX_Deleted_Uploaded") instanceof String[] ) {
					String[] deleteUploaded = (String[]) inputData.get("DEXTUploadX_Deleted_Uploaded");
					for ( int i = 0; i < deleteUploaded.length; i++ ) {
						// 삭제 처리된 파일에 대해 DB 삭제를 수행함  실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
						Map<String, Object> param = new HashMap<String, Object>();
//						param.put("fileAtchId", inputData.get("fileAtchId"));
						param.put("fileAtchId", fileAtchId);

						param.put("fileId", deleteUploaded[i]);
						service.deleteFileInfo(param);
					}
				}
				else if ( inputData.get("DEXTUploadX_Deleted_Uploaded") instanceof String ) {
					String deleteUploaded = (String) inputData.get("DEXTUploadX_Deleted_Uploaded");
					// 삭제 처리된 파일에 대해 DB 삭제를 수행함  실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("fileAtchId", fileAtchId);
					param.put("fileId", deleteUploaded);
					service.deleteFileInfo(param);
				}
			}
		}

		if ( ObjUtil.isNull(inputData.get("fileAtchId")) ) {
			service.saveWebFileMaster(fileAtchId);
		}

		ecmService.init(Constants.instcId);


		List<FileInfo> clientFileInfos = request.getAttachments();
		List<String> ecmIdList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		String ecmId = "";

		for (int i = 0; i < clientFileInfos.size(); i++) {
			FileInfo clientFileInfo = clientFileInfos.get(i);
			String filePath = clientFileInfo.getFolder() + "/" + clientFileInfo.getCallName();
			filePath = appProperties.get("dwe.serverInfo.upload.default") + "/" +  filePath;

			ecmId = ecmService.create(filePath);
			ecmIdList.add(ecmId);
			fileList.add(filePath);

		}

		ecmService.discon();

		for (int i = 0; i < fileList.size(); i++) {
			String delFilePath = fileList.get(i);
			File file = new File(delFilePath);
			file.delete();
		}

		try {
			service.saveEcmFileInfo(fileAtchId, inputData, clientFileInfos, ecmIdList);
		} catch (Exception e) {
			throw new BusinessException (e.getMessage());
		}

		PrintWriter pw = res.getWriter();


		try {

			if ( ObjUtil.isNull(inputData.get("type")) || inputData.get("type").toString().equals("ui")  ) {
				// 첨부파일 컴포넌트를 통한 업로드인경우 이 루틴을 탄다.
				res.setContentType("application/json");
				Map<String, Object> jsonMap = new HashMap<String, Object>();

				jsonMap.put("fileAtchId", fileAtchId);
				jsonMap.put("callFunc", "gf_UploadCallback('"+fileAtchId+"')");
				pw.write(JSONConverter.convertMap2Json(jsonMap).toString());
				pw.flush();
			}
		}
		catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
		finally {
			pw.close();
		}

		response.setViewName("/common/jsp/dummy.jsp");
	   }
	   catch (Exception exx) {
		   System.out.println(exx.getMessage());
		   System.out.println(exx.getCause());
		   System.out.println(exx.toString());
	   }
  }

	/**
	  * <pre>
	  * 첨부파일 업로드 및 파일 정보 저장
	  * </pre>
	  * @param request
	  * @param response
	  * */
@RequestMapping("uploadEcmFileActiveX.*")
public void uploadEcmFileActiveX(MciRequest request, MciResponse response) throws Exception {

	   try {
		/*
		 * @TODO 파일관리 정책은 약속된 Parameter 로 수신합니다. example) request.getParam("...")
		 */
		logger.debug("=================== ECM UPLOAD ====================");
		GridData<CoFile> mData = request.getGridData("input1", CoFile.class);
		Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> fileAtchIdMap = new HashMap<String, Object>();

		String tempFid = "";
		String[] tempFid2;

		if(ObjUtil.isNull(inputData.get("fileAtchId"))){
			tempFid = service.retrieveFileAtchId();
		}else{
			if( inputData.get("fileAtchId") instanceof String[]){
				tempFid2 = (String[]) inputData.get("fileAtchId");
				tempFid = tempFid2[0];
			}else if( inputData.get("fileAtchId") instanceof String){
				tempFid = inputData.get("fileAtchId").toString();
			}
		}

		String fileAtchId = tempFid;

		if ( ObjUtil.isNull(inputData.get("fileAtchId")) ) {
			service.saveWebFileMaster(fileAtchId);
		}

		try {
			service.saveEcmActiveXFileInfo(fileAtchId, inputData, mData);
		} catch (Exception e) {
			throw new BusinessException (e.getMessage());
		}

		fileAtchIdMap.put("fileAtchId", fileAtchId);
		response.setMap("fileAtchId", fileAtchIdMap);
	   }
	   catch (Exception exx) {
		   System.out.println(exx.getMessage());
		   System.out.println(exx.getCause());
		   System.out.println(exx.toString());
	   }
}

	/**
	  * <pre>
	  * 첨부파일 업로드 및 파일 정보 저장
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("uploadWebFile.*")
   public void uploadWebFile(final MciRequest request, MciResponse response) throws Exception {

	   try {
   		/*
   		 * @TODO 파일관리 정책은 약속된 Parameter 로 수신합니다. example) request.getParam("...")
   		 */
		HttpServletRequest req = WebContext.getRequest();
		HttpServletResponse res = WebContext.getResponse();
		String contextPath = req.getContextPath();
		final Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

//		final String fileAtchId = ObjUtil.isNull(inputData.get("fileAtchId"))  ? service.retrieveFileAtchId() : inputData.get("fileAtchId").toString();

//		String tempFid = ObjUtil.isNull(inputData.get("fileAtchId"))  ? service.retrieveFileAtchId() : inputData.get("fileAtchId").toString();

		String tempFid = "";
		String[] tempFid2;

		if(ObjUtil.isNull(inputData.get("fileAtchId"))){
			tempFid = service.retrieveFileAtchId();
		}else{
			if( inputData.get("fileAtchId") instanceof String[]){
				tempFid2 = (String[]) inputData.get("fileAtchId");
				tempFid = tempFid2[0];
			}else if( inputData.get("fileAtchId") instanceof String){
				tempFid = inputData.get("fileAtchId").toString();
			}
		}


		final String fileAtchId = tempFid;



//		final String[] fileAtchId2 = ObjUtil.isNull(inputData.get("fileAtchId"))  ? service.retrieveFileAtchId() : inputData.get("fileAtchId").toString();


		final String  policyId     = ObjUtil.isNull(inputData.get("policy"))  ? "default" : inputData.get("policy").toString();     // 특정 값이 넘어오지 않는다면 default policy 를 따른다.
		final boolean dateFolder   = false;           // 날자 형식의 폴더는 무조건 fasle;
		final String  endDirectory = DateUtil.getCurrentDateString("yyyy") + "/" + DateUtil.getCurrentDateString("MMdd");

        // 이부분은 DEXTUploadX 를 사용할때만 동작한다.
		// 이미 저장된 파일 정보의 재저장시 삭제된 파일에 대한 정보를 처리한다.
		if ( !ObjUtil.isNull(inputData.get("fileAtchId")) ) {
			// DextuploadX 에서 삭제 한 파일의 정보가 넘어옴 input map 의 DEXTUploadX_Deleted_Uploaded 라는 key 값으로 String[] 이 넘어옴
			if ( !ObjUtil.isNull(inputData.get("DEXTUploadX_Deleted_Uploaded")) ) {
				if ( inputData.get("DEXTUploadX_Deleted_Uploaded") instanceof String[] ) {
					String[] deleteUploaded = (String[]) inputData.get("DEXTUploadX_Deleted_Uploaded");
					for ( int i = 0; i < deleteUploaded.length; i++ ) {
						// 삭제 처리된 파일에 대해 DB 삭제를 수행함  실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
						Map<String, Object> param = new HashMap<String, Object>();
//						param.put("fileAtchId", inputData.get("fileAtchId"));
						param.put("fileAtchId", fileAtchId);

						param.put("fileId", deleteUploaded[i]);
						service.deleteFileInfo(param);
					}
				}
				else if ( inputData.get("DEXTUploadX_Deleted_Uploaded") instanceof String ) {
					String deleteUploaded = (String) inputData.get("DEXTUploadX_Deleted_Uploaded");
					// 삭제 처리된 파일에 대해 DB 삭제를 수행함  실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
					Map<String, Object> param = new HashMap<String, Object>();
//					param.put("fileAtchId", inputData.get("fileAtchId"));
					param.put("fileAtchId", fileAtchId);
					param.put("fileId", deleteUploaded);
					service.deleteFileInfo(param);
				}
			}
		}

		if ( ObjUtil.isNull(inputData.get("fileAtchId")) ) {
			service.saveWebFileMaster(fileAtchId);
		}





		request.handleIfMultipart(new UploadEventHandler() {

			public void prepareStorage(PersistenceManager persistenceManager, String folder) {
				/*
				 * 파일을 저장하기 전 기존 파일 삭제등의 작업을 수행한다.
				 */
			}

			public void postprocess(String folder, MultiPartInfo info, PersistenceManager persistenceManager) {
				/*
				 * 파일업로드 작업과 함께 처리되어야하는 후속작업(파일과 함께 전달된 폼필드 처리등..)을 수행함.
				 * - DB에 파일의 정보를 인서트 하는 로직이 있으므로.. 이곳에서 해당 서비스를 호출합니다.
				 *
				 * - 정책적용후 변경된 파일 이름을 필요로하므로.. 변경된 파일정보를 수신합니다.
				 *   MultiPartInfo.getFileInfos()로 변경된 파일 정보가 전달됩니다.
				 */

				try {
					service.saveWebFileInfo(fileAtchId, inputData, request.getAttachments(), info.getFileInfos());
				} catch (Exception e) {
					throw new BusinessException (e.getMessage());
				}
			}

			public long getMaxUploadSize() {
				// 정책에 정의된 최대 파일 사이즈를 가져온다.
				return policyManager.getFilePolicy(policyId).getMaxSize();
			}

			public String getFolder(HttpServletRequest request) {
				// 정책에 의해 현재 업로드된 파일이 저장된 경로를 지정한다.
				String plcy = policyId;
				policyManager.getFilePolicy(plcy).setCurrDateFolder(dateFolder);
				policyManager.getFilePolicy(plcy).setEndDirectory(endDirectory);
				String rsle = policyManager.getFilePolicy(plcy).getSubDirectory();
				rsle = rsle.replace('\\', '/');
				return rsle;
			}

			public String createFileNameIfAccepted(String folder, FileInfo fileInfo) {
				// 정책에 의한 업로드 파일명을 생성한다
				FilePolicy policy = policyManager.getFilePolicy(policyId);

				/*
				 *  파일관리 정책상의 위반여부를 체크함. 위반시 예외(@see FilePolicyViolationException)발생.
				 */
				policyManager.checkFilePolicy(fileInfo, policy);
				return policyManager.getNameEvaluator().buildIndividualFileName(policy.getRenamePattern(), fileInfo.getCallName(), policy.overriding());
			}
		}, persistence);

		/*response.addParam("safasfdas", "sdafs33333");
		response.addSuccessMessage("hello");
		response.setViewName("/common/jsp/comm/dummy.jsp");*/
		PrintWriter pw = res.getWriter();


		try {

			if ( ObjUtil.isNull(inputData.get("type")) || inputData.get("type").toString().equals("ui")  ) {
				// 첨부파일 컴포넌트를 통한 업로드인경우 이 루틴을 탄다.
				res.setContentType("application/json");
				Map<String, Object> jsonMap = new HashMap<String, Object>();

				jsonMap.put("fileAtchId", fileAtchId);
				jsonMap.put("callFunc", "gf_UploadCallback('"+fileAtchId+"')");
				pw.write(JSONConverter.convertMap2Json(jsonMap).toString());
				pw.flush();
			}
			else if ( inputData.get("type").toString().equals("editor") ) {
				// CK Editor의 이미지 업로드인경우 이 루틴을 탄다.
				String CKEditorFuncNum = inputData.get("CKEditorFuncNum").toString();
				String downloadUrl = StringUtil.getText(appProperties.get("dwe.serverInfo.url.context"))+"/co/common/file/downloadFileStream.xpl?fileAtchId="+fileAtchId+"&fileId=1";
				String returnScript = "<script type=\"text/javascript\"> ";
				returnScript += " window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+downloadUrl+"');";
				returnScript += " </script> ";
				pw.write(returnScript);
				pw.flush();
			}
			else if ( inputData.get("type").toString().equals("tweditor") ) {
				// tagfree Editor의 이미지 업로드인경우 이 루틴을 탄다.
				//String CKEditorFuncNum = inputData.get("CKEditorFuncNum").toString();
				String downloadUrl = StringUtil.getText(appProperties.get("dwe.serverInfo.url.context"))+"/co/common/file/downloadFileStream.xpl?fileAtchId="+fileAtchId+"&fileId=1";
				String returnScript = "<script type=\"text/javascript\"> ";
				returnScript += "$(\"#uploadRst\").val('"+fileAtchId+"')";
				returnScript += " </script> ";
				pw.write(returnScript);
				pw.flush();
			}
			else if ( inputData.get("type").toString().equals("geditor") ) {
				// good editor 이미지 업로드인경우 이 루틴을 탄다
				String downloadUrl = StringUtil.getText(appProperties.get("dwe.serverInfo.url.context")) + "/co/common/file/downloadFileStream.xpl?fileAtchId="+fileAtchId+"&fileId=1";
				pw.write(downloadUrl);
				pw.flush();
			}
//			else if ( inputData.get("type").toString().equals("tweditor") ) {
//				// tagfree editor 이미지 업로드인경우 이 루틴을 탄다
//				String downloadUrl = StringUtil.getText(appProperties.get("dwe.serverInfo.url.context")) + "/co/common/file/downloadFileStream.xpl?fileAtchId="+fileAtchId+"&fileId=1";
//				pw.write(downloadUrl);
//				pw.flush();
//			}
		}
		catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
		finally {
			pw.close();
		}
		/*

		javax.servlet.ServletOutputStream sos = res.getOutputStream();
		try {
			Map<String, Object> jsonMap = new HashMap<String, Object>();

			jsonMap.put("fileAtchId", fileAtchId);
			jsonMap.put("callFunc", "gf_UploadCallback('"+fileAtchId+"')");
			sos.println(JSONConverter.convertMap2Json(jsonMap).toString());
			sos.flush();
		}
		catch (IOException ex) {
			logger.debug(ex.getMessage());
		}
		finally {
			sos.close();
		}
		*/
		response.setViewName("/common/jsp/dummy.jsp");
	   }
	   catch (Exception exx) {
		   System.out.println(exx.getMessage());
		   System.out.println(exx.getCause());
		   System.out.println(exx.toString());
	   }
   }

	/**
	  * <pre>
	  * 첨부파일 업로드 및 파일 정보 저장
	  * </pre>
	  * @param request
	  * @param response
	  * */
	 @RequestMapping("uploadToHtml.*")
	 public void uploadToHtml(final MciRequest request, MciResponse response) throws Exception {

 		/*
 		 * @TODO 파일관리 정책은 약속된 Parameter 로 수신합니다. example) request.getParam("...")
 		 */

		final Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

		final StringBuilder html = new StringBuilder();
		final String  policyId     = "default";       // 무조건 default policy 를 따른다.
		final boolean dateFolder   = false;           // 날자 형식의 폴더는 무조건 fasle;
		final String  endDirectory = DateUtil.getCurrentDateString("yyyy") + "/" + DateUtil.getCurrentDateString("MMdd");

		request.handleIfMultipart(new UploadEventHandler() {

			public void prepareStorage(PersistenceManager persistenceManager, String folder) {
				/*
				 * 파일을 저장하기 전 기존 파일 삭제등의 작업을 수행한다.
				 */
			}

			public void postprocess(String folder, MultiPartInfo info, PersistenceManager persistenceManager) {
				/*
				 * 파일업로드 작업과 함께 처리되어야하는 후속작업(파일과 함께 전달된 폼필드 처리등..)을 수행함.
				 * - DB에 파일의 정보를 인서트 하는 로직이 있으므로.. 이곳에서 해당 서비스를 호출합니다.
				 *
				 * - 정책적용후 변경된 파일 이름을 필요로하므로.. 변경된 파일정보를 수신합니다.
				 *   MultiPartInfo.getFileInfos()로 변경된 파일 정보가 전달됩니다.
				 */
				FileInfo fileInfo = info.getFileInfos().get(0);
				String filePath = fileInfo.getFolder() + "/" + fileInfo.getName();
				File file = new File(filePath);

				ByteArrayOutputStream outputStream = null;
				InputStream inputStream = null;
				try {
					outputStream = new ByteArrayOutputStream();
					inputStream = new BufferedInputStream(new FileInputStream(file));
					byte[] bufferd = new byte[2048];
					int length = -1;
					while((length = inputStream.read(bufferd)) > 0){
						outputStream.write(bufferd, 0, length);
					}

					html.append(new String(outputStream.toByteArray()));
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				} finally{
					try {
						inputStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						outputStream.close();
					} catch (IOException e) {}
				}

				if(!file.delete())
					file.deleteOnExit();
			}

			public long getMaxUploadSize() {
				// 정책에 정의된 최대 파일 사이즈를 가져온다.
				return policyManager.getFilePolicy(policyId).getMaxSize();
			}

			public String getFolder(HttpServletRequest request) {
				// 정책에 의해 현재 업로드된 파일이 저장된 경로를 지정한다.
				return null;
			}

			public String createFileNameIfAccepted(String folder, FileInfo fileInfo) {
				// 정책에 의한 업로드 파일명을 생성한다
				FilePolicy policy = policyManager.getFilePolicy(policyId);

				/*
				 *  파일관리 정책상의 위반여부를 체크함. 위반시 예외(@see FilePolicyViolationException)발생.
				 */
				policyManager.checkFilePolicy(fileInfo, policy);
				return policyManager.getNameEvaluator().buildIndividualFileName(policy.getRenamePattern(), fileInfo.getCallName(), policy.overriding());
			}
		}, persistence);

		HttpServletResponse res = WebContext.getResponse();
		PrintWriter pw = res.getWriter();


		try {
			res.setContentType("application/json");
			Map<String, Object> jsonMap = new HashMap<String, Object>();

			jsonMap.put("callFunc", "gf_UploadCallback('"+html.toString()+"')");
			pw.write(JSONConverter.convertMap2Json(jsonMap).toString());
			pw.flush();
		}
		catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
		finally {
			pw.close();
		}
 	}



    /**
	  * <pre>
	  *  첨부파일 다운로드
	  * </pre>
	  * @param request
	  * @param response
	  * */
    @RequestMapping("downloadUploadedFile.*")
    public void downloadUploadedFile(MciRequest request, MciResponse response)  throws Exception {

    	Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
    	HttpServletResponse res = WebContext.getResponse();
   		HttpServletRequest req = WebContext.getRequest();
   		//WebContext.g

   		BufferedInputStream fin = null;
	    BufferedOutputStream outs = null;
	    // 다운로드 구분 ecm , nonecm
	    String downGbn = "";

   		try{

   			Map<String, Object> result = service.retrieveFileInfo(data);


           	String browserType = getBrowser(req);
      		String fileName = StringUtil.getText(result.get("fileNm"));
      		String filePath = StringUtil.getText(result.get("filePath"));
   			if (browserType.contains("Firefox")) {
   				logger.debug("Fire Fox Browser encode");
   	   	        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   	    res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	} else if (browserType.contains("Opera")) {
	   	   		logger.debug("Opera Browser encode");
	   	   		String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   		res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	} else if (browserType.contains("Chrome")) {
	   	   		logger.debug("Chrome Browser encode");
	   	   	    String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   	    res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	}
	   	   	else  {
	   	   		logger.debug("MSIE Browser encode");
   				String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
   	   	       	res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	}

   			String baseDir = basePersistence.getBaseDirectory();
   			logger.debug("basePersistence : " + baseDir);

   			if( StringUtil.getText(result.get("sysCd")).toUpperCase().equals("ACPF")) {
   				logger.debug("ACPF system download");
   				baseDir = StringUtil.getText(appProperties.get("dwe.serverInfo.upload.acpf"));
   	        }

   			if( StringUtil.getText(result.get("sysCd")).toUpperCase().equals("VOCS")) {
   				logger.debug("VOCS system download");
   				baseDir = StringUtil.getText(appProperties.get("dwe.serverInfo.upload.vocs"));
   	        }

   			if( StringUtil.getText(result.get("sysCd")).toUpperCase().equals("POMS")) {
   				logger.debug("POMS system download");
   				baseDir = StringUtil.getText(appProperties.get("dwe.serverInfo.upload.poms"));
   	        }

   			if( StringUtil.getText(result.get("sysCd")).toUpperCase().equals("CMAS")) {
   				logger.debug("CMAS system download");
   				baseDir = StringUtil.getText(appProperties.get("dwe.serverInfo.upload.cmas"));
   	        }

   			logger.debug("system별 : " + baseDir);

   			if ( StringUtil.getText(result.get("sysCd")).toUpperCase().equals("ADVS") ) {
   				logger.debug("ADVS system ecm download");
			    // 	자문 시스템의 경우 ecm을 사용하기 때문에 경로값에 자문시스템에서 등록한 ecm no를 받아 ecm download 로 처리 한다.
   				downGbn = "ECM";
   				ecmService.init(UserInfo.getInstanceId());
   				String mElementId = result.get("filePath").toString();
   				ByteArrayOutputStream fileOut = ecmService.downloadStream(mElementId);
   				byte[] buffer = fileOut.toByteArray();

	   			outs = new BufferedOutputStream(res.getOutputStream());
   				outs.write(buffer, 0, buffer.length);
  			}else if(filePath.startsWith("http")){
  				response.setViewName("redirect:"+filePath);
  				return;
  			}else {
   				logger.debug("Normal download");
   				downGbn = "NONECM";
	   			filePath = baseDir + "/" + filePath;

	   			logger.debug("filePath : " + filePath);

	   			File file = new File(filePath);

	   			InputStream is = new FileInputStream(file);

	   			//Content-Type 설정을 위해 파일 확장자를 추출
	   			String tempFileName = fileName.substring(fileName.length()-3, fileName.length());
	   			logger.debug("fileName : {}", tempFileName);

	   			String cType = "application/octet-stream";

//	   			if(tempFileName == "xls"){
//	   				cType = "application/vnd.ms-excel";
//	   			}else if(tempFileName == "txt"){
//	   				cType = "text/plain";
//	   			}else if(tempFileName == "doc"){
//		   				cType = "application/vnd.ms-word";
//	   			}else{
//	   				cType = "application/octet-stream";
//	   			}

	   			res.setHeader("Content-Type", cType);
	   			res.setContentLength((int)file.length());
	   			res.setHeader("Cache-Control", "public");
	   			res.setHeader("Content-Transfer-Encoding", "binary;");
	   			res.setHeader("Pragma", "no-cache;");
	   			res.setHeader("Expires", "-1;");

	   			fin = new BufferedInputStream(is);
	   			outs = new BufferedOutputStream(res.getOutputStream());

	   			byte[] buffer = new byte[8192];
	   			int read = 0;
	   			while ((read = fin.read(buffer)) != -1) {
	   				logger.debug("writing files" + Integer.toString(read));
	   				outs.write(buffer, 0, read);
	   			}
   			}
   		}catch(IOException io){
   			logger.debug("error!!!:" + io);
   			throw new Exception("simple download Exception " + io.getMessage());
       	}
   		finally
       	{
   			try {
   			   outs.flush();
    		   outs.close();
    	    } catch (Exception ex1) {    }

   			try {
   				fin.close();
    	    } catch (Exception ex2) {    }

       	}
   		response.setViewName("/common/jsp/dummy2.jsp?gubn="+downGbn);



    	/*Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
    	Map<String, Object> fileInfo = service.retrieveFileInfo(inputData);
        if (ObjUtil.isNull(fileInfo)) {
        	// 파일이 없음을 Exception 으로 처리한다.
        	throw new BusinessException("co.err.filedownload");
        }

        final String policyId 	= ObjUtil.isNull(inputData.get("policy"))  ? "default" : inputData.get("policy").toString();
        String filePath 		= fileInfo.get("filePath").toString();
        final String fileNm 	= fileInfo.get("fileNm").toString();

        if( StringUtil.getText(fileInfo.get("sysCd")).toUpperCase().equals("ACPF")) {
        	basePersistence.setBaseDirectory(StringUtil.getText(appProperties.get("dwe.serverInfo.upload.acpf")));
        }

        int index = filePath.lastIndexOf("/");

        String folder = "";
        String fileName = filePath;
        if(index > -1)	{
        	folder = filePath.substring(0, index);
        	fileName = filePath.substring(index + 1);
        }

        //fileName = URLDecoder.decode(fileName, "UTF-8");//URLEncoder.(fileName, "UTF-8");
        response.setDownloadFile(new DownloadEventHandler() {
        	public String createFileName(FileInfo fileInfo)	{
        		return fileNm;
        	}
			public void preprocess(FileInfo fileInfo) {
				FilePolicy policy = policyManager.getFilePolicy(policyId);

				 //  파일관리 정책상의 위반여부를 체크함. 위반시 예외(@see FilePolicyViolationException)발생.
				policyManager.checkFilePolicy(fileInfo, policy);
			}
		}, new FileInfo(folder, fileName));*/

        //response.setViewName("/common/jsp/comm/dummy.jsp");
    }

    /**
	  * <pre>
	  *  첨부파일 다운로드
	  * </pre>
	  * @param request
	  * @param response
	  * */
    @RequestMapping("downloadEcmFile.*")
    public void downloadEcmFile(MciRequest request, MciResponse response)  throws Exception {

		logger.debug("=================== ECM DOWNLOAD ====================");
    	Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
    	HttpServletResponse res = WebContext.getResponse();
   		HttpServletRequest req = WebContext.getRequest();
   		//WebContext.g

	    BufferedOutputStream outs = null;
	    // 다운로드 구분 ecm , nonecm
	    String downGbn = "";

   		try{

   			Map<String, Object> result = service.retrieveFileInfo(data);


           	String browserType = getBrowser(req);
      		String fileName = StringUtil.getText(result.get("fileNm"));
   			if (browserType.contains("Firefox")) {
   				logger.debug("Fire Fox Browser encode");
   	   	        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   	    res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	} else if (browserType.contains("Opera")) {
	   	   		logger.debug("Opera Browser encode");
	   	   		String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   		res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	} else if (browserType.contains("Chrome")) {
	   	   		logger.debug("Chrome Browser encode");
	   	   	    String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	   	   	    res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	}
	   	   	else  {
	   	   		logger.debug("MSIE Browser encode");
   				String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
   	   	       	res.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
	   	   	}

   			downGbn = "ECM";
   			ecmService.init(Constants.instcId);
   			String mElementId = result.get("ecmNo").toString();
   			ByteArrayOutputStream fileOut = ecmService.downloadStream(mElementId);
   			byte[] buffer = fileOut.toByteArray();

	   		outs = new BufferedOutputStream(res.getOutputStream());
   			outs.write(buffer, 0, buffer.length);
   		}catch(IOException io){
   			logger.debug("error!!!:" + io);
   			throw new Exception("simple download Exception " + io.getMessage());
       	}
   		finally
       	{
   			try {
   			   outs.flush();
    		   outs.close();
    	    } catch (Exception ex1) {    }

       	}
   		response.setViewName("/common/jsp/dummy2.jsp?gubn="+downGbn);


    }


    /**
	  * <pre>
	  *  첨부파일 다운로드
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("downloadFileStream.*")
   public void downloadFileStream(MciRequest request, MciResponse response)  throws Exception {

   		Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());

   		HttpServletResponse res = WebContext.getResponse();
   		HttpServletRequest req = WebContext.getRequest();

   		javax.servlet.ServletOutputStream servletoutputstream = res.getOutputStream();

   		String spec;
   		final String policyId = "default";
   		String serverFileName;
   		String clientFileName;
   		try{

           Map<String, Object> result = service.retrieveFileInfo(data);
           res.setContentType(service.getType(StringUtil.getText(data.get("fileNm"))));

           serverFileName = result.get("filePath").toString();
           clientFileName = result.get("fileNm").toString();
           int index = serverFileName.lastIndexOf("/");

           String folder = "";
           String fileName = serverFileName;

           if(index > -1)	{
           	folder = serverFileName.substring(0, index);
           	fileName = serverFileName.substring(index + 1);
           }

           persistence.getFileLoader(folder, fileName).sendFile(servletoutputstream);
           response.setViewName("/common/jsp/dummy2.jsp");
       }catch(IOException io){
           logger.debug("error!!!:" + io);
           throw new Exception("fileErr");
       }
       finally
       {
           servletoutputstream.flush();
           servletoutputstream.close();
       }
   }



    /**
	  * <pre>
	  *  파일 마스터 정보  삭제
	  * </pre>
	  * @param request
	  * @param response
	  * */
    @RequestMapping("deleteFileMasterInfo.*")
    public void deleteFileMasterInfo(MciRequest request, MciResponse response) throws Exception {

    	Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
    	// 파일 마스터 정보 삭제
    	// 마스터 정보 삭제시 detail 정보도 삭제됨
    	// 실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
    	service.deleteFileMasterInfo(param);

    }

    /**
	  * <pre>
	  *  파일 상세 정보 삭제
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("deleteFileInfo.*")
   public void deleteFileInfo(MciRequest request, MciResponse response) throws Exception {

   		Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
   		// 파일 정보 삭제
   		// 실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
   		service.deleteFileInfo(param);
   }


   /**
	  * <pre>
	  *  파일 상세 정보 삭제
	  * </pre>
	  * @param request
	  * @param response
	  * */
 @RequestMapping("initFileSeq.*")
 public void initFileSeq(MciRequest request, MciResponse response) throws Exception {

 		Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
 		// 파일 정보 삭제
 		// 실제 파일의 삭제는 수행하지 않음 Database 의 use_yn 컬럼 업데이트
 		for ( int i = 0; i < 73000; i++ ) {
 			service.retrieveFileAtchId();
 			System.out.println("i->"+Integer.toString(i));
 		}

 		response.setViewName("/common/jsp/dummy.jsp");

 }

   /**
	  * <pre>
	  *  엑셀 다운로드를 위한 다운로드 서비스
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("downloadStream4Excel.*")
   public void downloadStream4Excel( MciRequest request, MciResponse response ) throws Exception {

	   Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

	   final String policyId 	= ObjUtil.isNull(inputData.get("policy"))  ? "excel" : inputData.get("policy").toString();
       String filePath 			= inputData.get("filePath").toString();
       final String fileNm 		= inputData.get("fileNm").toString();


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


   	private String getBrowser(HttpServletRequest request) {

       String header =request.getHeader("User-Agent");

       if (header.contains("MSIE")) {

              return "MSIE";

       } else if(header.contains("Chrome")) {

              return "Chrome";

       } else if(header.contains("Opera")) {

              return "Opera";

       }

       return "Firefox";

 }


    /**
	  * <pre>
	  *  파일 마스터 정보  삭제
	  * </pre>
	  * @param request
	  * @param response
	  * */

    @RequestMapping("retrieveZipFileAtch.*")
    public void retrieveZipFileAtch(MciRequest request, MciResponse response) throws Exception {

    	Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
    	try{
	    	String res = service.retrieveZipFileAtch(param);
	        if(res.equals("0")) {
	        	res = "";
	        } else {
	            res = "zip";
	        }
	    	Map<String, Object> resMap = new HashMap<String, Object>();

	    	resMap.put("zipck", res);
			response.setMap("output1", resMap); 		//Map을 Client로 전송
    	}catch(Exception e){
    		throw new BusinessException(e.getMessage());
    	}
    }
//    @RequestMapping("retrieveZipFileAtch.*")
//    public void retrieveZipFileAtch(MciRequest request, MciResponse response) throws Exception {
//
//    	Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
//
//    	String res = service.retrieveZipFileAtch(param);
//        if(res.equals("0")) {
//        	res = "";
//        } else {
//            res = "zip";
//        }
//    	Map<String, Object> resMap = new HashMap<String, Object>();
//
//    	resMap.put("zipck", res);
//		response.setMap("output1", resMap); 		//Map을 Client로 전송
//    }

    /**
	  * <pre>
	  *  이미지 파일 또는 문서파일을 스트림으로 클라이언트에 전송한다.
	  * </pre>
	  * @param request
	  * @param response
	  * */
    @RequestMapping("imageFile.*")
    public void imageFile(MciRequest request, MciResponse response) throws Exception {
    	Map<String, Object> data = request.getParam();
        HttpServletResponse res = WebContext.getResponse();
        javax.servlet.ServletOutputStream servletoutputstream = res.getOutputStream();


        String serverFileName;

        try{
            noImagePath = Constants.fileNoImg;
            Map<String, Object> result = service.retrieveFileInfo(data);

            String folder = "";
            String fileName = "";

            if(result == null){
            	logger.debug("=================== No Image ==================== : " + noImagePath);
                int noImgPathIdx = noImagePath.lastIndexOf("/");
            	folder = noImagePath.substring(0, noImgPathIdx);
            	fileName = noImagePath.substring(noImgPathIdx + 1);
            }else{
                res.setContentType(service.getType(StringUtil.getText(data.get("fileNm"))));
                serverFileName = result.get("filePath").toString();
                int index = serverFileName.lastIndexOf("/");

                folder = "";
                fileName = serverFileName;

                if(index > -1)	{
                	folder = serverFileName.substring(0, index);
                	fileName = serverFileName.substring(index + 1);
                }
            }
            persistence.getFileLoader(folder, fileName).sendFile(servletoutputstream);

            response.setViewName("/common/jsp/dummy2.jsp");
        }catch(IOException io){
            logger.debug("error!!!:" + io);
            throw new Exception("fileErr");
        }
        finally
        {
            servletoutputstream.flush();
            servletoutputstream.close();
        }
    }



    @RequestMapping("uploadTest.*")
	public void upload(MciRequest request, MciResponse response) throws Exception {

		try {
			List<FileInfo> fileItems = request.getAttachments();
			for (FileInfo fileInfo : fileItems) {
				File file = new File(Constants.uploadDefault + "/" + fileInfo.getFolder() + "/" + fileInfo.getName());
				if (file.exists()) {
					OutputStream output = new FileOutputStream("C:/" + fileInfo.getName());
					FileUtil.write(output, file);
				}
			}

		} catch (Exception e) {
			logger.error("error occurred whild uploading files ... ", e);

		} finally {
			response.setViewName("/common/jsp/dummy.jsp");

		}
	}

}
