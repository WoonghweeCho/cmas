package com.dwenc.cmas.common.excel.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.excel.support.ExcelWriter;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : ExcelService
 * 설    명 : Excel 파일 생성 지원 서비스
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
@Service
public class ExcelService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

	/**
	 * <pre>
	 *     List<Map<String, Object>>로부터 엑셀 파일을 생성하는 서비스
	 * </pre>
     * @param inputData
     * @param mData
	 * @return Map<String, Object>
	 */
    public Map<String, Object> makeExcelFile(Map<String, Object> inputData, List<Map<String, Object>> mData) {

        WritableWorkbook book   = null;
        WritableSheet sheet     = null;
        String filePath         = "";
        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
        int currMs = oCalendar.get(Calendar.MILLISECOND);
        try {

            String fileNm = StringUtil.getText(inputData.get("fileNm"));
            filePath = Constants.uploadDefault.replace('\\', '/') + "/excel/" + fileNm  ;
            String sheetNm = StringUtil.getText(inputData.get("sheetNm"));

            String strMapper = StringUtil.getText(inputData.get("mapperData"));
            String mapperDataArry[] = strMapper.split(",");
            List<Map<String, Object>> mapperData = new ArrayList<Map<String, Object>>();
//          num:STRING(10):사번,
            for(int i=0;i<mapperDataArry.length;i++){
                String strTmp = mapperDataArry[i];
                String tmpArry[] = strTmp.split(":");
                Map<String, Object> tmp = new HashMap<String, Object>();
                tmp.put("colId", tmpArry[0]);
                tmp.put("colType", tmpArry[1].substring(0, tmpArry[1].indexOf("(")));
                String colSize = tmpArry[1].substring(tmpArry[1].indexOf("(") + 1, tmpArry[1].indexOf(")"));
                tmp.put("colSize", Integer.parseInt(colSize));
                tmp.put("colNm", tmpArry[2]);
                mapperData.add(tmp);
            }

            File f = new File(filePath);
            logger.debug("file path :"+f.getCanonicalPath());
            logger.debug("file name :"+f.getName());
            inputData.put("fileNm",   fileNm);
            inputData.put("filePath", filePath);

            book = Workbook.createWorkbook(f);
            sheet = book.createSheet(sheetNm, 0);

            Label label;
            // 레이블 용
            WritableCellFormat wcfLabel = new WritableCellFormat();
            wcfLabel.setAlignment(Alignment.CENTRE);
            wcfLabel.setWrap(false);
            wcfLabel.setVerticalAlignment(VerticalAlignment.TOP);
            wcfLabel.setBorder(Border.ALL, BorderLineStyle.THIN);
            wcfLabel.setBackground(Colour.GOLD);

            // 일반 데이터용
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setWrap(false);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

            // 제목용
            WritableCellFormat wcft = new WritableCellFormat();
            wcft.setWrap(false);
            wcft.setAlignment(Alignment.LEFT);
            wcft.setBorder(Border.ALL, BorderLineStyle.THIN);

            // 숫자용
            WritableCellFormat wcfn = new WritableCellFormat();
            wcfn.setWrap(false);
            wcfn.setAlignment(Alignment.RIGHT);
            wcfn.setBorder(Border.ALL, BorderLineStyle.THIN);

            /*********************************************
            * title
            * No    DESCRIPTION LEN TYPE    내용  구분
            *********************************************/
            for(int i=0;i<mapperData.size();i++){
                int nColSize = Integer.parseInt(mapperData.get(i).get("colSize").toString());
                sheet.setColumnView(i , nColSize);
                label = new Label(i , 0, mapperData.get(i).get("colNm").toString(), wcfLabel);
                sheet.addCell(label);
            }

            Label label1;
            if(mData.size() > 0){
                for(int idx=0; idx < mData.size(); idx++){
                    for(int i=0;i<mapperData.size();i++){
                        if(StringUtil.getText(mapperData.get(i).get("colType")).equals("INT") || StringUtil.getText(mapperData.get(i).get("colType")).equals("DECIMAL")) {
                            label1 = new Label(i, idx+1, StringUtil.getText(mData.get(idx).get(mapperData.get(i).get("colId"))),wcfn); sheet.addCell(label1);
                        } else if (StringUtil.getText(mapperData.get(i).get("colNm")).equals("제목") || StringUtil.getText(mapperData.get(i).get("colNm")).equals("TITLE")) {
                        	label1 = new Label(i, idx+1, StringUtil.getText(mData.get(idx).get(mapperData.get(i).get("colId"))),wcft); sheet.addCell(label1);
                        } else {
                            label1 = new Label(i, idx+1, StringUtil.getText(mData.get(idx).get(mapperData.get(i).get("colId"))),wcf); sheet.addCell(label1);
                        }
                    }
                }
            }

            book.write();
            book.close();
            logger.debug("msg:"+"파일을 생성하였음.");

        } catch (Exception e) {
            logger.debug(this.getClass().getName()+".makeExcelFile] " + e );
        } finally {
            if (book != null) try {book.close();} catch (Exception e){}
        }
        return inputData;
    }

    /**
     * <pre>
     *  List<Map<String, Object>> 형태의 데이터를 포맷을 바꿔 엑셀파일로 저장한다.(샘플!!!)
     * </pre>
     * @param  mData 엑셀로 변환할 List<Map<String, Object>>
     * @param  fileName 엑셀파일명
     * @return String
     */
    public String toTMSFile(GridData<HashMap> mData, String fileName){
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
            wt.addSheetForGrid(mData, "SheetName");

            File file = wt.execute(fileName);
            fileName = file.getAbsoluteFile().toString();
        }catch(Exception e){
            logger.debug("**** toTMSFile error!!! : " + e.getMessage());
        }
        return fileName;
    }
}
