package com.dwenc.cmas.common.datasource.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : DatSrcService
 * 설    명 : xplatform의 transaction filtering 하는 서비스
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
public class DatSrcService {
    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    /**
     * xplatform의 transaction filter 정보 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveDatSrcList(Map<String, Object> parameter) {
        return dao.queryForMapList("datSrc.retrieveDatSrcList", parameter);
    }
}
