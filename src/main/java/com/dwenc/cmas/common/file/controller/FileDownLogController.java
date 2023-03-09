package com.dwenc.cmas.common.file.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.file.domain.CoFileDownLog;
import com.dwenc.cmas.common.file.service.FileDownLogService;

import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;


/** * <pre>
* ---------------------------------------------------------------
* 업무구분 : 공통
* 프로그램 :
* 설 명    : 첨부파일 다운로드 로그를 위한 Controller 클래스
* 작 성 자 : 변형구
* 작성일자 : 2012-11-26
* 수정이력
* ---------------------------------------------------------------
* 수정일 이 름 사유
* ---------------------------------------------------------------
* ---------------------------------------------------------------
* </pre>
* @version 1.0 */


@Controller
@RequestMapping("/co/common/filelog/*")
public class FileDownLogController {

	/**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(FileDownLogController.class);

    @Autowired
	private FileDownLogService service;                // service class


    /**
	  * <pre>
	  * 첨부파일 마스터 정보 저장
	  * </pre>
	  * @param request
	  * @param response
	  * */
    @RequestMapping("insertFileDownLog.*")
    public void insertFileMaster(MciRequest request, MciResponse response) {
    	CoFileDownLog downLog = request.get("input1", CoFileDownLog.class);
        service.insertCoFileDownLog(downLog);
   }

}
