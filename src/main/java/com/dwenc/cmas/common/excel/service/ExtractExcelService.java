package com.dwenc.cmas.common.excel.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.bridge.excel.ExcelConverter;
import com.dwenc.cmas.common.excel.support.ExcelWriter;
import com.dwenc.cmas.common.file.service.FileDownload;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : ExtractExcelService
 * 설    명 : 엑셀 첨부 파일의 데이터를 추출하는 Service 클래스.
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service("ExtractExcelService")
public class ExtractExcelService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ExtractExcelService.class);

    private static final String FILE_TIME_SEPARATOR = "_time_";

    /**
     * <pre>
     * 엑셀정보를 List<Map<String, Object>>로 추출하고, 그리드로 뿌릴수 있도록 데이터셋의 메터정보와 병합한다.
     * </pre>
     * @param inputData Command로 부턴 전달받은 input HashMapProtocol
     * @return List<Map<String, Object>> 조회된 리스트 결과.
     * @exception Exception 메소드 수행시 발생한 모든 에러.
     */
    public List<Map<String, Object>> extractExcel(List<Map<String, Object>> inputData) throws Exception{
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try{
            if(!inputData.isEmpty()){
                String fileFullPath = inputData.get(0).get("serverFileName").toString();

                File file = new File(fileFullPath);
                if(!file.exists())
                    throw new Exception("co.err.notexistfile");
                ExcelConverter cov = new ExcelConverter(file);
                result = cov.toMultiData("archievement");
                file.delete();
            }
        }catch(Exception se){
            logger.debug(this.getClass().getName() + "." + "extractExcel()" + "=>" + se.getMessage());
            throw new Exception("co.err.retrieve", se);
        }
        return result;
    }

    /**
     * <pre>
     * 엑셀정보를 각 시트별로 List<Map<String, Object>>로 추출하고 Map<String, List<Map<String, Object>>> 에 담아 리턴한다.
     * </pre>
     * @param inputData Command로 부턴 전달받은 input HashMapProtocol
     * @return Map<String, List<Map<String, Object>>> 조회된 리스트 결과.
     * @exception Exception 메소드 수행시 발생한 모든 에러.
     */
    public Map<String, List<Map<String, Object>>> extractExcelAllSheet(Map<String, Object> inputData) throws Exception{
        Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        try{
            if(!inputData.isEmpty()){
                String fileFullPath = inputData.get("serverFileName").toString();

                File file = new File(fileFullPath);
                if(!file.exists())
                    throw new Exception("co.err.notexistfile");
                ExcelConverter cov = new ExcelConverter(file);
                result = cov.toAllData();
                file.delete();
            }
        }catch(Exception se){
            logger.debug(this.getClass().getName() + "." + "extractExcel()" + "=>" + se.getMessage());
            throw new Exception("co.err.retrieve", se);
        }
        return result;
    }

    /**
     * <pre>
     * 엑셀정보를 List<Map<String, Object>>로 추출하고, 그리드로 뿌릴수 있도록 데이터셋의 메터정보와 병합한다.
     * </pre>
     * @param inputData Command로 부턴 전달받은 input HashMapProtocol
     * @return List<Map<String, Object>> 조회된 리스트 결과.
     * @exception Exception 메소드 수행시 발생한 모든 에러.
     */
    public List<Map<String, Object>> extractExcelNamedSheet(Map<String, Object> inputData) throws Exception{
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try{
            if(!inputData.isEmpty()){
                String fileFullPath = inputData.get("serverFileName").toString();

                File file = new File(fileFullPath);
                if(!file.exists())
                    throw new Exception("co.err.notexistfile");
                ExcelConverter cov = new ExcelConverter(file);

                // 첫번째 시트의 데이터를 가져온다.
                if ( ObjUtil.isNull(inputData.get("sheetName")) ) {
                	result = cov.toMultiData();
                }
                else {
                	// 인자로 전달된 이름의 시트의 데이터를 가져온다.
                	result = cov.toMultiData(inputData.get("sheetName").toString());
                }
                file.delete();
            }
        }catch(Exception se){
            logger.debug(this.getClass().getName() + "." + "extractExcel()" + "=>" + se.getMessage());
            throw new Exception("co.err.retrieve", se);
        }
        return result;
    }

    /**
     * <pre>
     * 엑셀정보를 List<Map<String, Object>>로 추출하고, 그리드로 뿌릴수 있도록 데이터셋의 메터정보와 병합한다.(사용자 정의 헤더 적용)
     * </pre>
     * @param inputData Command로 부턴 전달받은 input HashMapProtocol
     * @return List<Map<String, Object>> 조회된 리스트 결과.
     * @exception Exception 메소드 수행시 발생한 모든 에러.
     */
    public Map<String, HashMap> extractExcel(List<Map<String, Object>> mData, List<Map<String, Object>> mHeader) throws Exception{
        Map<String, HashMap> mHolder = new HashMap<String, HashMap>();
        String strSheetNm = "";
        String fileFullPath = "";
        try{
            fileFullPath = mData.get(0).get("serverFileName").toString();
            File file = new File(fileFullPath);
            if(!file.exists())
                throw new Exception("co.err.notexistfile");
            ExcelConverter cov = new ExcelConverter(file);
            for(int i=0;i<mData.size();i++) {
                strSheetNm = mData.get(i).get("sheetNm").toString();
                HashMap header = null;
                if(!strSheetNm.equals("")){
                    header = (HashMap)mHeader.get(0).get(strSheetNm);
                    cov.setTitleRow((Integer)mData.get(i).get("titleRow"));
                    mHolder.put(strSheetNm, (HashMap)cov.toMultiData(strSheetNm, header));
                }else{
                    header = (HashMap)mHeader.get(0);
                    cov.setTitleRow((Integer)mData.get(i).get("titleRow"));
                    mHolder = (HashMap)cov.toMultiData(strSheetNm, header);
                }
            }
            file.delete();
        }catch(Exception se){
            logger.debug(this.getClass().getName() + "." + "extractExcel()" + "=>" + se.getMessage() + "/"
                    + fileFullPath + "/" + strSheetNm);
            throw new Exception("co.err.retrieve", se);
        }
        return mHolder;
    }

    /**
     * <pre>
     * 임시 엑셀 파일 저장
     * </pre>
     * @param input
     * @throws Exception
     */
    public String writeTempExcel(Map input, List<Map<String, Object>> mData) throws Exception{
        String serverFileName = "";
        try {
            String working = Constants.workingDir;
            new File(working).mkdirs();
            // 파일명 중복 방지
            StringBuffer sfFileName = new StringBuffer();
            sfFileName.append(working)
                      .append("/")
                      .append(input.get("fileName"))
                      .append(FILE_TIME_SEPARATOR)
                      .append(DateUtil.getCurrentDateString("yyyyMMddHHmmssSSS"));
            File file = ExcelConverter.toExcelFile(mData, sfFileName.toString());
            if (file.exists()) {
                serverFileName = file.getName();
                logger.debug("[Excel Write - File Path] " + file.getAbsolutePath());
            } else {
                throw new FileNotFoundException("co.err.notexistfile");
            }
        } catch (Exception e) {
            logger.debug("**** Could not read source file: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return serverFileName;
    }

    /**
     * <pre>
     * 임시 엑셀 파일 다운로드
     * </pre>
     * @param input
     * @throws Exception
     */
    public void downloadTempExcel(Map input) throws Exception{
        try {
            String working = Constants.workingDir;
            File file = new File(working + File.separator + input.get("serverFileName"));
            if (file.exists()) {
                FileDownload fileDownload = new FileDownload(WebContext.getRequest(), WebContext.getResponse(), "excel");
                String fileName = input.get("fileName").toString();
                if (fileName == null || fileName.isEmpty()) {
                    fileName = file.getName();
                }
                // 파일명 중복방지문자 제거
                fileName = fileName.indexOf(FILE_TIME_SEPARATOR) > 0 ?
                        (fileName.substring(0, fileName.indexOf(FILE_TIME_SEPARATOR)) + ".xls")
                        : fileName;
                logger.debug("[Excel Download - File Path] " + file.getAbsolutePath());
                fileDownload.streamTo(file, fileName);
                file.delete();
            } else {
                throw new FileNotFoundException("co.err.notexistfile");
            }
        } catch (Exception e) {
            logger.debug("**** Could not read source file: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * <pre>
     * List<Map<String, Object>> 형태의 데이터를 포맷을 바꿔 엑셀파일로 저장한다.(샘플!!!)
     * </pre>
     * @param  mData 엑셀로 변환할 List<Map<String, Object>>
     * @param  fileName 엑셀파일명
     * @return File 엑셀파일
     */
    public String writeTempExcel(List<Map<String, Object>> mData, String fileName){
        try{
            ExcelWriter wt = new ExcelWriter();
            //wt.setEncoding("utf-8");
            HSSFWorkbook workbook = wt.getWorkbook();
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(POIConstants.FONT_WEIGHT_NORMAL);
            font.setColor(POIConstants.FONT_COLOR_RED);
            font.setFontHeightInPoints((short)12);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cellStyle.setFont(font);
            cellStyle.setLocked(false);
            wt.setTitleCellStyle("testStatCd", cellStyle);

            HSSFCellStyle cellStyle2 = workbook.createCellStyle();
            HSSFFont font2 = workbook.createFont();
            font2.setBoldweight(POIConstants.FONT_WEIGHT_NORMAL);
            font2.setColor(POIConstants.FONT_COLOR_BLACK);
            font2.setFontHeightInPoints((short)10);
            cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cellStyle2.setFont(font);
            cellStyle2.setLocked(false);
            wt.setValueCellStyle("testStatCd", cellStyle2);
            wt.addSheetForMap(mData, "SheetName");

            File file = wt.execute(fileName);
            fileName = file.getAbsoluteFile().toString();
        }catch(Exception e){
            logger.debug("**** toTMSFile error!!! : " + e.getMessage());
        }
        return fileName;
    }


}
