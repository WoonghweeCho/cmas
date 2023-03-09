package com.dwenc.cmas.common.bridge.excel;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : ExcelConverter
 * 설    명 : Excel 파일정보를 전달받아 List<Map<String, Object>> 형태로 변환한다.
 *           세로 병합된 정보에 대해서 데이터화 하며, 첫번째 Row 를 column으로 간주한다.
 * 작 성 자 :
 * 작성일자 : 2012-02-06
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일                    이  름          사유
 * ---------------------------------------------------------------
 * 2012-02-06
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.StringUtil;

public class ExcelConverter {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelConverter.class);

    ArrayList regions;

    Short[] mergerdCol;

    int regionSize = 0;

    HSSFSheet sheet;

    HSSFWorkbook wb;

    Workbook wb2; //use jxl;

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    int titleRow = 0;

    public ExcelConverter() {
    }

    public ExcelConverter(File excelFile) {
        regions = new ArrayList();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));
            // 워크북 오브젝트의 취득
            wb = new HSSFWorkbook(fs);

        } catch (Exception e) {
            logger.debug("Can't Load Excel file [" + excelFile.getName()
                    + "]. It may broken excel file or Not an excel file format.");
        }
    }

    public ExcelConverter(String excelFilePath) {
        try {
            wb2 = Workbook.getWorkbook(new File(excelFilePath));
        } catch (Exception e) {
            logger.debug("Can't Load Excel file [" + excelFilePath
            		+ "]. It may broken excel file or Not an excel file format.");
        }
    }


    private void setRegion(HSSFSheet sheet){
        this.sheet = sheet;
        this.regionSize = sheet.getNumMergedRegions();

        for (int i = 0; i < regionSize; i++) {
            this.regions.add(sheet.getMergedRegionAt(i));
        }
    }

    private int isMergedCell(int rowIdx, short colIdx){
//        for (int i = 0; i < regions.size(); i++) {
//            Region region = (Region)regions.get(i);
//            if (region.contains(rowIdx, colIdx))
//                return i;
//        }
        return -1;
    }

    private int getMergedRowIdx(int regionIndex){
        return ((Region)regions.get(regionIndex)).getRowFrom();
    }

    /**
     * <pre>
     * Excel 파일의 모든 sheet 의 정보를 Map<String, Object> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     * 각 sheet 명 별로 map에 List<Map<String, Object>> 형태로 저장되어 리턴된다.
     *
     * </pre>
     * @param  N/A
     * @return Map<String, Object>
     */
    public Map<String, List<Map<String, Object>>> toAllData(){
    	Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

    	for ( int i = 0; i < wb.getNumberOfSheets(); i++) {
    		String sheetName = wb.getSheetName(i);
    		map.put(sheetName, toMultiData(sheetName, titleRow+1, -1));
    	}

    	return map;

    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(String sheetName){
        return toMultiData(sheetName, titleRow+1, -1);
    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(String sheetName, HashMap headers){
        return toMultiData(sheetName, titleRow+1, -1, headers);
    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @param  fromIdx   데이터 영역의 시작 인덱스
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(String sheetName, int fromIdx){
        return toMultiData(sheetName, fromIdx, -1);
    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @param  fromIdx   데이터 영역의 시작 인덱스
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(String sheetName, int fromIdx, HashMap header){
        return toMultiData(sheetName, fromIdx, -1, header);
    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @param  fromIdx   데이터 영역의 시작 인덱스
     * @param  lineCnt   데이터 영역의 읽을 라인수
     *
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("deprecation")
	public List<Map<String, Object>> toMultiData(String sheetName, int fromIdx, int lineCnt){

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        HSSFSheet sheet = null;
        if (sheetName == null) {
            sheet = wb.getSheetAt(0); // 엑셀을 Sheet 단위로 리턴 받는다.
        } else {
            sheet = wb.getSheet(sheetName); // 엑셀을 Sheet 단위로 리턴 받는다.
        }
        setRegion(sheet);

        if (sheet != null && sheet.getLastRowNum() > 0) {

            int firstRowSize = titleRow;
            int lastRowSize = 0;

            if ( lineCnt == -1 )
                lastRowSize = sheet.getLastRowNum()+1;
            else
                lastRowSize = fromIdx + lineCnt;

            HSSFRow firstRow = sheet.getRow(firstRowSize);
            final int size = firstRow.getLastCellNum();

            for (int rowIdx = fromIdx; rowIdx < lastRowSize; rowIdx++) {
                // 행을 표시하는 오브젝트의 취득
                HSSFRow row = sheet.getRow(rowIdx);

                // 행에 데이터가 없는 경우
                if (row == null) {
                    continue;
                }

                result.add(new HashMap<String, Object>());

                for (short i = 0; i < size; i++) {
                    // 셀을 표시하는 오브젝트를 취득
                    HSSFCell cell = row.getCell(i);
                    if (cell == null)
                        result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), "");
                    else {
                        int type = cell.getCellType();
                        // 데이터 종류별로 데이터를 취득
                        switch (type) {
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                boolean bdata = cell.getBooleanCellValue();
                                result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), new Boolean(bdata)
                                        .toString());
                                break;

                            case HSSFCell.CELL_TYPE_NUMERIC:
                                String tDouble = cell.getNumericCellValue() + "";
                                String[] slice = StringUtil.split(tDouble, '.');
                                if (slice.length == 2 && slice[1].equals("0")) {
                                    tDouble = slice[0];
                                }
                                result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), tDouble);
                                break;

                            case HSSFCell.CELL_TYPE_STRING:
                                result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), cell.getStringCellValue());
                                break;

                            case HSSFCell.CELL_TYPE_BLANK:
                            case HSSFCell.CELL_TYPE_ERROR:
                            case HSSFCell.CELL_TYPE_FORMULA:
                                int checkMerge = isMergedCell(rowIdx, i);
                                if (checkMerge > -1) {
                                    result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), result.get(getMergedRowIdx(checkMerge) - 1).get(firstRow.getCell(i).getStringCellValue()));
                                } else {
                                    result.get(result.size()-1).put(firstRow.getCell(i).getStringCellValue(), "");
                                }

                            default:
                                continue;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * <pre>
     * Excel 파일에 sheetName에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName Excel Sheet
     * @param  fromIdx   데이터 영역의 시작 인덱스
     * @param  lineCnt   데이터 영역의 읽을 라인수
     *
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(String sheetName, int fromIdx, int lineCnt, HashMap header){

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        HSSFSheet sheet = null;
        if (sheetName == null || sheetName.equals("")) {
            sheet = wb.getSheetAt(0); // 엑셀을 Sheet 단위로 리턴 받는다.
        } else {
            sheet = wb.getSheet(sheetName); // 엑셀을 Sheet 단위로 리턴 받는다.
        }
        setRegion(sheet);

        if (sheet != null && sheet.getLastRowNum() > 0) {

            int firstRowSize = titleRow;
            int lastRowSize = 0;

            if ( lineCnt == -1 )
                lastRowSize = sheet.getLastRowNum()+1;
            else
                lastRowSize = fromIdx + lineCnt;

            HSSFRow firstRow = sheet.getRow(firstRowSize);
            final int size = firstRow.getLastCellNum();

            for (int rowIdx = fromIdx; rowIdx < lastRowSize; rowIdx++) {
                // 행을 표시하는 오브젝트의 취득
                HSSFRow row = sheet.getRow(rowIdx);

                // 행에 데이터가 없는 경우
                if (row == null) {
                    continue;
                }

                HashMap arrayHeader = new HashMap();
                ArrayList arrayType = new ArrayList();
                Set dataKeySet = header.keySet();
                Iterator dataIterator = dataKeySet.iterator();
                while(dataIterator.hasNext()){
                    String dataKey = dataIterator.next().toString();
                    arrayHeader.put(dataKey, dataKey);
                    arrayType.add(header.get( dataKey ));
                }

                for (short i = 0; i < size; i++) {
                    // 셀을 표시하는 오브젝트를 취득
                    HSSFCell cell = row.getCell(i);
                    if (cell == null)
                        result.get(result.size()).put((String)arrayHeader.get(i), arrayHeader.get(i));
                    else {
                        int type = cell.getCellType();
                        String colNm = "";
                        if(i >= arrayHeader.size()) {
                            colNm = "dummy";
                        } else {
                            colNm = arrayHeader.get(i).toString();
                        }
                        // 데이터 종류별로 데이터를 취득
                        switch (type) {
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                boolean bdata = cell.getBooleanCellValue();
                                result.get(result.size()).put(colNm, new Boolean(bdata)
                                        .toString());
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                // TODO : 엑셀 쉬트 버전에 따른 지수형태 데이터 문제
                            	// 변경라인.
                            	String tDouble = Double.toString((double)cell.getNumericCellValue());
                            	// 원본라인.
                                //String tDouble = cell.getNumericCellValue() + "";
                                String[] slice = StringUtil.split(tDouble, '.');
                                if (slice.length == 2 && slice[1].equals("0")) {
                                    tDouble = slice[0];
                                }
                                result.get(result.size()).put(colNm, tDouble);
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                String colType = "";
                                if(i < arrayType.size()) {
                                    colType = arrayType.get(i).toString();
                                } else {
                                    logger.debug("컬럼 갯수가 상이합니다.!!!" + arrayType.size());
                                }
                                String value = cell.getStringCellValue();
                                if(colType.equals("")) {
                                    result.get(result.size()).put(colNm, value);
                                } else {
                                    colType = colType.substring(0, colType.indexOf("|"));
                                    int nColType = Integer.parseInt(colType);
                                    if(nColType == Types.VARCHAR){
                                        result.get(result.size()).put(colNm, value);
                                    }else if(nColType == Types.INTEGER){
                                        result.get(result.size()).put(colNm, Integer.parseInt(value));
                                    }else if(nColType == Types.DECIMAL){
                                        result.get(result.size()).put(colNm, Float.parseFloat(value));
                                    }
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                            case HSSFCell.CELL_TYPE_ERROR:
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 변경 후 라인.
                                result.get(result.size()).put(colNm, cell.getStringCellValue());
                            	// TODO : 셀타입 CELL_TYPE_FORMULA 사용 부분 수정.(문제점 발생시 공통 or 원가에 알려주세요!)

                            	/* 원본 라인.
                                int checkMerge = isMergedCell(rowIdx, i);
                                if (checkMerge > -1) {
                                    result.addString(colNm, result.get(
                                            colNm, getMergedRowIdx(checkMerge) - 1));
                                } else {
                                    result.addString(colNm, "");
                                }
								*/
                            default:
                                continue;
                        }

                    }
                }
            }
        }
        return result;
    }

    /**
     * <pre>
     * Excel 파일에 첫번째 sheet에 해당하는 Excel Sheet 정보를 List<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> toMultiData(){
        return toMultiData(null);
    }

    /**
     * <pre>
     * Excel 파일에 첫번째 sheet에 해당하는 Excel Sheet 정보를 IndexedList<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @return IndexedList<Map<String, Object>>
     */
    public IndexedMap toIndexedMultiData(){
        return new IndexedMap(toMultiData(null));
    }

    /**
     * <pre>
     * Excel 파일의 sheetName에 해당하는 Excel Sheet 정보를 IndexedList<Map<String, Object>> 형태로 리턴한다.
     * 이때 sheet의 병합된 정보는 모두 개별 셀에 데이터로 정리되어 리턴된다.
     *
     * </pre>
     * @param  sheetName 엑셀 sheet명
     * @return IndexedList<Map<String, Object>>
     */
    public IndexedMap toIndexedMultiData(String sheetName){
        return new IndexedMap(toMultiData(sheetName));
    }

    /**
     * <pre>
     * List<Map<String, Object>> 형태의 데이터를 엑셀파일로 저장한다.
     *
     * </pre>
     * @param  mData 엑셀로 변환할 List<Map<String, Object>>
     * @param  fileName 엑셀파일명
     * @return File 엑셀파일
     */
    public static File toExcelFile(List<Map<String, Object>> mData, String fileName){
        Object columnName[];
        File file = new File(fileName + ".xls");
        try {

            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            ArrayList al = new java.util.ArrayList(mData.get(0).keySet());
            columnName = al.toArray();

            if (columnName.length == 0) {
                workbook.write();
                workbook.close();
                return file;
            }

            for (int i = 0; i < columnName.length; i++) {
                Label label = new Label(i, 0, columnName[i].toString());
                sheet.addCell(label);
            }

            for (int i = 0; i < mData.size(); i++) {
                for (int j = 0; j < columnName.length; j++) {
                    Label label = new Label(j, i + 1, mData.get(i).get(columnName[j]).toString());
                    sheet.addCell(label);
                }
            }

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return file;
    }

    /**
     * <pre>
     * List<Map<String, Object>> 형태의 데이터를 엑셀파일로 저장한다.
     *
     * </pre>
     * @param  mData 엑셀로 변환할 List<Map<String, Object>>
     * @param  fileName 엑셀파일명
     * @return File 엑셀파일
     */
    public File toExcelFile(List<Map<String, Object>> mData, String fileName, String SheetName){
        Object columnName[];
        File file = new File(fileName);
        try {

            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet(SheetName, 0);

            ArrayList al = new java.util.ArrayList(mData.get(0).keySet());
            columnName = al.toArray();

            if (columnName.length == 0) {
                workbook.write();
                workbook.close();
                return file;
            }

            for (int i = 0; i < columnName.length; i++) {
                Label label = new Label(i, 0, columnName[i].toString());
                sheet.addCell(label);
            }

            for (int i = 0; i < mData.size(); i++) {
                for (int j = 0; j < columnName.length; j++) {
                    Label label = new Label(j, i + 1, mData.get(i).get(columnName[j]).toString());
                    sheet.addCell(label);
                }
            }

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return file;
    }

    /**
     * <pre>
     * List<Map<String, Object>> 형태의 데이터를 엑셀파일로 저장 후 이를 압축한 파일로 리턴한다.
     *
     * </pre>
     * @param  mData 엑셀로 변환할 List<Map<String, Object>>
     * @param  fileName 엑셀파일명
     * @return File Zip 파일
     */
    public static File toExcelZipFile(List<Map<String, Object>> mData, String fileName){

        File localXFile = toExcelFile(mData, fileName);
        File file = new File(fileName + ".zip");
        try {
            byte b[] = new byte[512];

            FileOutputStream fos = new FileOutputStream(file);
            ZipOutputStream zout = new ZipOutputStream(fos);

            InputStream in = new FileInputStream(localXFile);
            ZipEntry e = new ZipEntry(localXFile.getName());
            zout.putNextEntry(e);
            int len = 0;
            while ((len = in.read(b)) != -1) {
                zout.write(b, 0, len);
            }
            zout.closeEntry();
            zout.close();
            fos.close();

        } catch (Exception e) {
            logger.debug(e.toString());
        } finally {
            localXFile.delete();
        }
        return file;
    }

    public static void main(String args[]) {
        try {
            ExcelConverter ec = new ExcelConverter(new File("c:/test1.xls"));
            List<Map<String, Object>> result1 = ec.toMultiData("Sheet1");
            logger.debug(result1.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    public void setTitleRow(int titleRow){
        this.titleRow = titleRow;
    }
}



