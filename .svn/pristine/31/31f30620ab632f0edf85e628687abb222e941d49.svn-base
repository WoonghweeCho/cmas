package com.dwenc.cmas.common.locale.service;

import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.locale.domain.LoclInfo;

import docfbaro.common.StringUtil;
import docfbaro.config.EnvironmentConfigProperties;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : LocaleService
 * 설    명 : 로케일정보를 조회하는 service 클래스
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
@Service
public class LocaleService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(LocaleService.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;
	@Autowired
	private EnvironmentConfigProperties properties;

	/**
	 * 조회
	 *
	 * @param input
	 * @return
	 */
	public List<Map<String, Object>> retrieveLocaleList(Map<String, Object> input) {
	    String sysCd = "";
	    if(StringUtil.getText(input.get("sysCd")).equals("")) {
	        sysCd = properties.getSysCd();
	        logger.debug("Constants.sysCd = " + sysCd);
	        input.put("sysCd", properties.getSysCd());
	    }
		return dao.queryForMapList("locale.retrieveLocaleList", input);
	}
	/**
	 * <pre>
	 * User에 LoclCd update
	 * </pre>
	 * @param gridData
	 */
	public void updateUserLocl(GridData<LoclInfo> mData) {
		mData.forEachRow(new AbstractRowStatusCallback<LoclInfo>() {
			@Override
			public void insert(LoclInfo record, int rowNum) {
				dao.update("locale.updateUserLocl", record);
			}
		});
	}

}
