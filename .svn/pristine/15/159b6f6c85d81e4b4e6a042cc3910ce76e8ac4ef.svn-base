package com.dwenc.cmas.common.menu.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : MenuService
 * 설    명 : 메뉴 관리를 위한 Service Class
 * 작 성 자 : DWE
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
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.code.CommonCodes;

import docfbaro.common.Constants;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;

@Service("MenuSysService")
public class MenuService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * BRANCH 구분, 권한, 시스템, 언어에 따라 메뉴정보를 조회한다.
	 * @param inputData
	 * @return
	 */
	public List<Map<String, Object>> retrieveMenu(Map<String,Object> inputData) {
		try {
			if(StringUtil.getText(inputData.get("BRANCH")).equals("op")) {
	            inputData.put("topMenuCd", StringUtil.stringToList(StringUtil.getText(inputData.get("menuCd")), ","));
			}
			// admin 이 아님을 의미한다.
			// 실제 쿼리 에서도 어드민이 아닐경우만 privcd 조건이 들어간다.
			if (!UserInfo.getCurPriv().equals(CommonCodes.AUTHORITY_SYSTEM_ADMIN)) {
				inputData.put("sysAdmin", "not sysAdmin");
			}
			if (!ObjUtil.isNull(inputData.get("privCd"))) {
				logger.debug("privCd->"+StringUtil.getText(inputData.get("privCd")));
				if ( inputData.get("privCd").toString().indexOf("+") >= 0) {
					logger.debug("+ sep applied");
					inputData.put("privCd", StringUtil.convertArray(inputData.get("privCd").toString(), "+"));
				}
				else {
					logger.debug("space sep applied");
					inputData.put("privCd", inputData.get("privCd").toString().replace(' ', '+'));
					logger.debug("replace sep string " + StringUtil.getText(inputData.get("privCd")));
					inputData.put("privCd", StringUtil.convertArray(inputData.get("privCd").toString(), " "));
				}
			}
			inputData.put("sysCd", Constants.sysCd);
			if ( ObjUtil.isNull(inputData.get("loclCd")) ) {
				inputData.put("loclCd", UserInfo.getLoclCd());
			}

			inputData.put("orgCd", UserInfo.getOrgCd());
			return dao.queryForMapList("menuSys.retrieveMenu", inputData);
		} catch (Exception se) {
			logger.debug(this.getClass().getName() + "." + "retrieveMenu()" + "=>" + se.getMessage());
			throw new BusinessException("co.err.retrieve", se);
		}
	}
}
