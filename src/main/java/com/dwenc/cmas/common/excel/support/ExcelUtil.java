package com.dwenc.cmas.common.excel.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : ExcelTemplateManager
 * 설    명 : 엑셀 템플릿 파일을 파싱하거나 생성을 지원하는 유틸
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

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwenc.cmas.common.bridge.excel.ExcelConverter;
import com.dwenc.cmas.common.file.service.FileDownload;

import docfbaro.common.Constants;
import docfbaro.common.WebContext;

public class ExcelUtil {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * List<Map<String, Object>>를 엑셀로 만들기 위한 메소드
     * @param input
     */
    public static void downloadTempExcel( Map<String, Object> input, List<Map<String, Object>> mData ) throws Exception {
        try {
            String working = Constants.workingDir;
            new File(working).mkdirs();
            String fileName = working + "/" + input.get("fileName") + ".xls";
            File file = ExcelConverter.toExcelFile(mData, fileName);
            FileDownload fileDownload = new FileDownload( WebContext.getRequest(), WebContext.getResponse(), "excel" );
            fileDownload.streamTo( file, input.get("fileName") + ".xls" );
            file.delete();
        } catch ( Exception e ) {
            logger.debug( "**** Could not read source file: " + e.getMessage() );
        }
    }
}


