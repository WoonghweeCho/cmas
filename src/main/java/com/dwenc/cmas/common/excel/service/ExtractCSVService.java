package com.dwenc.cmas.common.excel.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwenc.cmas.common.bridge.csv.CSVConverter;
import com.dwenc.cmas.common.utils.DaoUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : ExtractCSVService
 * 설    명 :  엑셀 첨부 파일의 데이터를 추출하는 Service 클래스.
 * 작 성 자 : 2011-04-16
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
public class ExtractCSVService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ExtractCSVService.class);

    /**
     * <pre>
     * 엑셀정보를 List<Map<String, Object>>로 추출하고, 그리드로 뿌릴수 있도록 데이터셋의 메터정보와 병합한다.(사용자 정의 헤더 적용)
     * </pre>
     * @param inputData Command로 부턴 전달받은 input HashMapProtocol
     *
     * @return List<Map<String, Object>> 조회된 리스트 결과.
     * @exception Exception 메소드 수행시 발생한 모든 에러.
     */
    public List<Map<String, Object>> extractCSV(List<Map<String, Object>> inputData, Map header) throws Exception {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try{
            if (inputData.get(0).containsKey(DaoUtil.DWE_CUD_FILTER_KEY)) {
                String fileFullPath = inputData.get(0).get("serverFileName").toString();

                File file = new File(fileFullPath);
                if (!file.exists())
                    throw new Exception("co.err.notexistfile");
                CSVConverter cov = new CSVConverter(file);
                result = cov.toMultiData(header);
                file.delete();
            }
        } catch (Exception se) {
            logger.debug(  this.getClass().getName() + "." + "extractCSV()" + "=>" + se.getMessage());
            throw new Exception("co.err.retrieve", se);
        }
        return result;
    }
}



