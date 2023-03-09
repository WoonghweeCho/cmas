package com.dwenc.cmas.sample.service;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.sample.domain.Draft;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통 - 코드관리
 * 프로그램 : COA0101
 * 설 명 : 공통코드 관리를 위한 service 클래스
 * 작 성 자 :
 * 작성일자 :
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

@Service
public class SampleService {

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	public List<Draft> retrieveDraftList(Map<String, Object> params) {
		return dao.queryForList("Sample.retrieveDraftList", params, Draft.class);
	}

	public void saveDraft(Map<String, Object> map) {
		map.put("fstRegUserId", "TEST");
		map.put("fnlEditUserId", "TEST");
		dao.update("Sample.saveDraft", map);
	}
}

