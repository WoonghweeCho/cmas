package com.dwenc.cmas.common.excel.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.code.service.CodeService;
import com.dwenc.cmas.common.excel.service.ExcelService;
import com.dwenc.cmas.common.excel.service.ExtractExcelService;
import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;
import docfbaro.sua.persistence.file.CommonPersistenceManager;
import docfbaro.sua.persistence.file.policy.FilePolicyManager;
/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : ExcelController
 * 설 명 : DB에서 조회된 데이터를 엑셀로 저장하는 컨트롤러
 * 작 성 자 : 박광섭
 * 작성일자 : 2012.10.10
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
@Controller
@RequestMapping("co/common/excel/*")
public class ExcelController {

    @Autowired
    private ExcelService service;

    @Autowired
    private ExtractExcelService eservice;


    @Autowired
    private FileService fservice;

    @Autowired
    CodeService commonService = new CodeService();

    @Autowired
	private CommonPersistenceManager basePersistence;

    @Autowired
	private FilePolicyManager policyManager;    // framework file policy manager

	/**
	 * <pre>
	 * 조회된 데이터를 해당 파일이름 Excel로 Export 한다.
	 * </pre>
	 * @param request
	 * @param
	 */
	/*@RequestMapping("exportCommExcel.*")
	public void exportCommExcel(MciRequest request, MciResponse response){
        Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
        HttpServletRequest req = WebContext.getRequest();
        List<Map<String, Object>> resultDataList = (List<Map<String, Object>>)req.getAttribute("excelData");

        service.makeExcelFile(inputData, resultDataList);
        req.setAttribute("spec", "temp");
        req.setAttribute("fileNm", inputData.get("fileNm")+ ".xls");
        response.setViewName(inputData.get("returnType").toString());
    }*/

	/**
	 * <pre>
	 * 조회된 데이터를 해당 파일이름 Excel로 다운로드 한다.
	 * </pre>
	 * @param request
	 * @param response
	 */
    @RequestMapping("downloadFormatExcel.*")
    public void downloadFormatExcel(MciRequest request, MciResponse response){
        Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
        GridData<HashMap> mData = request.getGridData("input1", HashMap.class);
        String working = Constants.workingDir;
        new File(working).mkdirs();
        String fileName = working + "/" + inputData.get("fileName");
        service.toTMSFile(mData, fileName);
        response.addParam("fv_FilePath", StringUtil.getText(inputData.get("fileName")));
    }


    /**
	 * <pre>
	 * 조회된 데이터를 해당 파일이름 Excel로 다운로드 한다.
	 * </pre>
	 * @param request
	 *        fileNm :  파일명
	 *        mapperData : 컬럼 매핑 정보
	 *        code:STRING(30):코드,value:STRING(300):코드명,commGrpCd:STRING(5):코드그룹,
     *    	  refCd1:STRING(30):필터1,refCd2:STRING(30):필터2,refCd3:STRING(30):필터3," +
     *    	  refCd4:STRING(30):필터4,hgrCommGrpCd:STRING(5):상위그룹코드,hgfCommCd:STRING(5):상위코드"
	 * @param response
     * @throws Exception
	 */
    @RequestMapping("exportCommExcel.*")
	public void exportCommExcel(MciRequest request, MciResponse response) throws Exception{

    	Map<String, Object> mapperData = RequestUtil.getParam(request, WebContext.getRequest());
    	List<Map<String,String>> paramList	= request.getMapList("excelData");
    	List<Map<String, Object>> excelData = new ArrayList<Map<String, Object>>();

    	for ( int i = 0; i < paramList.size(); i++ ) {
    		Map <String, Object> map = new HashMap<String, Object>();
    		map.putAll(paramList.get(i));
    		excelData.add(map);
    	}

    	// 서버에 엑셀 파일이 생기는 경로는 dispatcher-import.xml 내의 excel file policy 참조
    	mapperData.put("policy", "excel");   // file ploicy 를 excel 로 맞춘다.


        // header & data mapper info
        /*inputData.put("mapperData", "code:STRING(30):코드,value:STRING(300):코드명,commGrpCd:STRING(5):코드그룹," +
         		                     "refCd1:STRING(30):필터1,refCd2:STRING(30):필터2,refCd3:STRING(30):필터3," +
         		                     "refCd4:STRING(30):필터4,hgrCommGrpCd:STRING(5):상위그룹코드,hgfCommCd:STRING(5):상위코드");*/

    	 mapperData = service.makeExcelFile(mapperData, excelData);

    	 mapperData.put("filePath", mapperData.get("filePath").toString().substring( mapperData.get("filePath").toString().indexOf(mapperData.get("policy").toString()) ));

    	 response.setMap("downResult", mapperData);
    }


    /**
	 * <pre>
	 * Excel 파일의 모든 sheet 를 map 에 저장
	 * </pre>
	 * @param request
	 * @param response
     * @throws Exception
	 */
    @RequestMapping("importCommExcel.*")
    public void importCommExcel(MciRequest request, MciResponse response) throws Exception{

    	Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
    	// 첨부 파일 정보 조회
    	Map<String, Object> fileInfo = fservice.retrieveFileInfo(map);
    	// 파일 경로 생성
    	String filePath = fileInfo.get("filePath").toString();
    	filePath = basePersistence.getBaseDirectory() + "/" + filePath;
    	filePath = filePath.replace('\\','/');
    	map.put("serverFileName", filePath);

    	// mapping data 생성
    	String[] mappedData = StringUtil.getText(map.get("mappedData")).split(",");
    	Map<String, Object> mapMap = new HashMap<String, Object>();

    	for ( int i = 0; i < mappedData.length; i ++ ) {
    		String[] keyVal = mappedData[i].split(":");
    		mapMap.put(keyVal[0], keyVal[1]);
    	}
    	// mapping data에서 key 추출
    	Set keyset = mapMap.keySet();
    	// 이 배열의 결과는 엑셀 헤더값이다.
    	Object[] hashkeys = keyset.toArray();

    	//map list 생성
    	List<Map<String, Object>> resultList = eservice.extractExcelNamedSheet(map);
    	List<Map<String, Object>> newResultList = new ArrayList<Map<String, Object>>();

    	// 엑셀에서 읽어온 데이터를 mappedData에 전달된 header : datafield 명으로 key 값들을 변환
    	// map.get("직책1") -> map.get("dutyNm1") 로 전환
    	for ( int i = 0; i < resultList.size(); i++ ) {
    		Map<String, Object> newResultMap = new HashMap<String, Object>();

    		for ( int j = 0; j < hashkeys.length; j++ ) {
    			if ( resultList.get(i).containsKey(hashkeys[j]) )  {
    				newResultMap.put(StringUtil.getText(mapMap.get(hashkeys[j])),
    						StringUtil.getText(resultList.get(i).get(hashkeys[j])) );
    			}
    		}
    		newResultList.add(newResultMap);
    	}

    	response.setMapList("importData", newResultList);

    }


    /**
	 * <pre>
	 * Excel 의 첫번째 시트만을 조회한다.
	 * </pre>
	 * @param request
	 * @param response
     * @throws Exception
	 */
    @RequestMapping("testExcelFirstSheet.*")
    public void testExcelFirstSheet(MciRequest request, MciResponse response) throws Exception{

    	Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

    	Map<String, Object> fileInfo = fservice.retrieveFileInfo(map);

    	String filePath = fileInfo.get("filePath").toString();

    	filePath = basePersistence.getBaseDirectory() + "/" + filePath;

    	filePath = filePath.replace('\\','/');

    	map.put("serverFileName", filePath);

    	List<Map<String, Object>> result = eservice.extractExcelNamedSheet(map);

    	response.addSuccessMessage("SUCCESS");

    }

    /**
	 * <pre>
	 * Excel 의 특정 명칭의 시트만을 조회한다.
	 * </pre>
	 * @param request
	 * @param response
     * @throws Exception
	 */
    @RequestMapping("testExcelNamedSheet.*")
    public void testExcelNamedSheet(MciRequest request, MciResponse response) throws Exception{
    	/*Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

    	Map<String, Object> fileInfo = fservice.retrieveFileInfo(map);

    	String filePath = fileInfo.get("filePath").toString();

    	filePath = basePersistence.getBaseDirectory() + "/" + filePath;

    	filePath = filePath.replace('\\','/');

    	map.put("serverFileName", filePath);

    	List<Map<String, Object>> result = eservice.extractExcelNamedSheet(map);

    	response.addSuccessMessage("SUCCESS");*/

    }


    /**
	 * <pre>
	 * Excel 의 첫번째 시트만을 조회한다.
	 * </pre>
	 * @param request
	 * @param response
     * @throws Exception
	 */
    @RequestMapping("testExcelDownload.*")
    public void testExcelDownload(MciRequest request, MciResponse response) throws Exception{

    	Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

    	Map<String, Object> input = new HashMap<String, Object>();

         input.put("sortCodeNotnull", "Y");
         input.put("loclCd", "ko_KR");
         List<Map<String, Object>> codeList = commonService.codeValueAllList(new ArrayList(), input);

         // header & data mapper info
         inputData.put("mapperData", "code:STRING(30):코드,value:STRING(300):코드명,commGrpCd:STRING(5):코드그룹," +
         		                     "refCd1:STRING(30):필터1,refCd2:STRING(30):필터2,refCd3:STRING(30):필터3," +
         		                     "refCd4:STRING(30):필터4,hgrCommGrpCd:STRING(5):상위그룹코드,hgfCommCd:STRING(5):상위코드");

         inputData = service.makeExcelFile(inputData, codeList);
         fservice.downloadStream(request, response, inputData, policyManager);
    	 response.setViewName("/common/jsp/dummy.jsp");
    }

}
