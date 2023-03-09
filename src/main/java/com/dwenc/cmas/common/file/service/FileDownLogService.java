package com.dwenc.cmas.common.file.service;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.file.domain.CoFileDownLog;

import docfbaro.common.ObjUtil;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 :
 * 설 명    : 첨부파일 다운로드 로그 처리 서비스 클래스
 * 작 성 자 : 변형구
 * 작성일자 : 2012-11-26
 * 수정이력 : 최초작성
 * --------------------------------------------------------------------
 * 수정일 이 름 사유
 * --------------------------------------------------------------------
 * --------------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

@Service
public class FileDownLogService {

	 /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(FileDownLogService.class);

    /**
     * DB 처리를 위한 공통 dao
     */
    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    /**
	 * 로그 한건 생성
	 *
	 * @param coFile 파일 마스터 정보
     **/
	public void insertCoFileDownLog(CoFileDownLog coFileDownLog) {

		if (ObjUtil.isNull(coFileDownLog))
			return;

		if ( ObjUtil.isNull(coFileDownLog.getUserId())) {
	        if (UserInfoHolder.getUserInfo(UserDefinition.class) == null) {
	        	coFileDownLog.setUserId("REXPERT");
			} else {
				if ( ObjUtil.isNull(UserInfo.getUserId()) ) {
					coFileDownLog.setUserId("REXPERT");
				}
				else {
					coFileDownLog.setUserId(UserInfo.getUserId());
				}
			}
		}

		dao.update("commonFileDownLog.insertFileDownLog", coFileDownLog);
	}

}
