package com.dwenc.cmas.common.session.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jcf.data.GridData;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.config.service.ConfigService;
import com.dwenc.cmas.common.instance.service.support.InstcFactory;
import com.dwenc.cmas.common.login.domain.LoginInfo;
import com.dwenc.cmas.common.menu.service.MenuService;
import com.dwenc.cmas.common.priv.service.PrivService;
import com.dwenc.cmas.common.session.domain.CoMenuBkmrk;
import com.dwenc.cmas.common.utils.CollectionUtil;

import docfbaro.common.ClassUtils;
import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.MapUtil;
import docfbaro.common.MessageCodes;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.exception.BusinessException;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SessionService
 * 설    명 :  사용자 세션 조회를 위한 Service Class
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
@Service("SessionSysService")
public class SessionService {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

	/**
	 * DB 처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	 @Autowired
	 @Qualifier("appProperties")
	 private Properties appProperties;

    @Autowired
    private PrivService authService;

    //@Autowired
    //private SiteService siteService;

    @Autowired
    private MenuService menuXPService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private InstcFactory instcFactory;

	/**
	 * <pre>
	 * 사용자 세션 조회를 위한 메소드
	 * </pre>
	 *
	 * @param input
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> retrieveSessionList(Map<String, Object> input) {
        List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
		try {
            UserDefinition userData = UserInfo.getUserInfo();
            mData = convertObj2Map(userData, mData);
            LoginInfo loginInfo = (LoginInfo)UserInfo.getLoginInfo();
            mData = convertObj2Map(loginInfo, mData);

            // 세금계산서 연동 정보
            input.put("key", "dwe.company.");
            List<Map<String, Object>> mData2 = configService.retrieveConfigList(input);
            String strCompany = "";
            Map<String, Object> input2 = mData2.get(0);
            Set dataKeySet = input2.keySet();
            Iterator dataIterator = dataKeySet.iterator();
            while(dataIterator.hasNext()){
                String dataKey = dataIterator.next().toString();
                String dataKey2 = dataKey.substring("dwe.company.".length(), dataKey.length());
                strCompany += dataKey2 + "=" + input2.get(dataKey) + "|";
            }
            if(!strCompany.equals("")) strCompany = strCompany.substring(0, strCompany.length() - 1);
            mData.add(mData.size(), setValue("userSession", "CompanyInfo", strCompany));

            // 전자증빙 url
            mData.add(mData.size(), setValue("userSession", "DweesUrl", StringUtil.getText(appProperties.getProperty("dwe.dwees.url"))));

            @SuppressWarnings("unchecked")
    		List<Map<String,Object>> sysList = (List<Map<String,Object>>)input.get("sysList");
            // 어드민 권한 여부 확인

			String adminSystemList = "";
			String isSystemAdmin = "false";
			for ( int i = 0; i < sysList.size(); i++ ) {
				if (sysList.get(i).containsValue(Constants.sysCd)) {
					isSystemAdmin = "true";
				}
				adminSystemList += sysList.get(i).get("sysCd").toString() + "|";
			}

			mData.add(mData.size(), setValue("userSession", "isSystemAdmin", isSystemAdmin));
			if (adminSystemList.endsWith("|")) {
				adminSystemList = adminSystemList.substring(0, adminSystemList.length() - 1);
				mData.add(mData.size(), setValue("userSession", "adminSystemList", adminSystemList));
			}

			List<Map<String, Object>> instcList = instcFactory.getmInst();

			String ecmSiteCdList = "";
            if (instcList != null) {
                for (int j = 0; j < instcList.size(); j++) {
                	ecmSiteCdList += CollectionUtil.mapToString((HashMap) instcList.get(j)) + "<br>";
                }
                if (ecmSiteCdList.indexOf("<br>") > 0) {
                	ecmSiteCdList = ecmSiteCdList.substring(0, ecmSiteCdList.length() - 4);
                }
            }
            mData.add(mData.size(), setValue("userSession", "ecmSiteCdList", ecmSiteCdList));

            // 초기화
            UserInfo.setLoginInfo(null);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return mData;
	}

    /**
     * <pre>
     * 오브젝트를 맵형태로 변경
     * </pre>
     *
     * @param input
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> convertObj2Map(Object sourceObj, List<Map<String, Object>> mData) {
        try {
            Method methods[] = null;
            if(sourceObj.getClass().getSimpleName().equals("UserDefinition")) {
                UserDefinition userData = (UserDefinition)sourceObj;
                methods = userData.getClass().getMethods();
            } else {
                LoginInfo loginInfo = (LoginInfo)sourceObj;
                methods = loginInfo.getClass().getMethods();
            }
            for (int i = 0; i < methods.length; i++) {
                try {
                    String rtype = methods[i].getReturnType().getSimpleName();
                    String methodNm = methods[i].getName();
                    Class[] param = methods[i].getParameterTypes();
                    if (param.length == 0 && !methodNm.equals("wait") && !methodNm.equals("reset")
                            && !methodNm.equals("notify") && !methodNm.equals("notifyAll") && !methodNm.equals("getAuthorityString")
                            && !methodNm.equals("hashCode") && !methodNm.startsWith("reset")) {
                        String value = "";
                        Object obj = ClassUtils.invokeSimple(sourceObj, methodNm);
                        if (rtype.equals("int")) {
                            value = Integer.toString((Integer) obj);
                        } else if (rtype.equals("String")) {
                            value = (String) obj;
                        } else if (rtype.equals("List")) {
                            value = CollectionUtil.listToString(obj);
                        } else if (rtype.equals("boolean")) {
                            value = obj.toString();
                        } else if (rtype.equals("HashMap")) {
                            Map objList = (Map) obj;
                            String listValues = "";
                            if (objList != null) {
                                listValues = CollectionUtil.mapToString(objList);
                            }
                            value = listValues;
                        } else if (rtype.equals("List<Map<String, Object>>")) {
                            List<Map<String, Object>> objList = (List<Map<String, Object>>) obj;
                            String listValues = "";
                            if (objList != null) {
                                for (int j = 0; j < objList.size(); j++) {
                                    listValues += CollectionUtil.mapToString((HashMap) objList.get(j)) + "<br>";
                                }
                                if (listValues.indexOf("<br>") > 0) {
                                    listValues = listValues.substring(0, listValues.length() - 3);
                                }
                            }
                            value = listValues;
                        } else {
                            logger.debug(obj.toString());
                        }
                        mData.add(mData.size(), setValue("userSession", methodNm, value));
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return mData;
    }

	/**
	 * <pre>
	 * 맵에 값 넣기
	 * </pre>
	 *
	 * @param
	 * @return
	 */
	public Map<String, Object> setValue(String aType, String aName, String aValue) {
		Map<String, Object> mData = new HashMap<String, Object>();
		aName = aName.trim();
		mData.put("type", "userSession");
		if (aName.startsWith("get")) {
			aName = aName.substring(3, aName.length());
		} else if (aName.startsWith("is")) {
			aName = aName.substring(2, aName.length());
		}
		mData.put("name", aName);
		mData.put("value", aValue);
		return mData;
	}

	/**
	 * <pre>
	 * CO메뉴즐겨찾기를 조건에 의한 자료 조회한다.
	 * </pre>
	 *
	 * @param requestMap
	 * @return CO메뉴즐겨찾기리스트
	 */
	public List<CoMenuBkmrk> retrieveCoMenuBkmrkList(Map<String, Object> requestMap) {
		return commonDao.queryForList("Session.selectCoMenuBkmrkList", requestMap, CoMenuBkmrk.class);
	}

	/**
	 * <pre>
	 * CO메뉴즐겨찾기 LIST를 저장
	 * </pre>
	 *
	 * @param gridData
	 */
	public void saveCoMenuBkmrkList(GridData<CoMenuBkmrk> gridData) {
		logger.debug("gridData.size()=" + gridData.size());
		gridData.forEachRow(new AbstractRowStatusCallback<CoMenuBkmrk>() {
			@Override
			public void insert(CoMenuBkmrk record, int rowNum) {
				record.setUserId(UserInfo.getUserId());// 최초등록자
				record.setFstRegUserId(UserInfo.getUserId());// 최초등록자
				record.setFnlEditUserId(UserInfo.getUserId());// 최종수정자
				commonDao.update("Session.insertCoMenuBkmrk", record);
			}
			@Override
			public void update(CoMenuBkmrk updatedRecord, CoMenuBkmrk oldRecord, int rowNum) {
				updatedRecord.setFnlEditUserId(UserInfo.getUserId());// 최종수정자
				commonDao.update("Session.updateCoMenuBkmrk", updatedRecord);
			}
			@Override
			public void delete(CoMenuBkmrk record, int rowNum) {
				commonDao.update("Session.deleteCoMenuBkmrk", record);
			}
		});
	}

    /**
     * <pre>
     * changeSession 업무 좌측상위 조직 및 권한 변경시 세션값 변경
     * </pre>
     * @param inputData
     * @return
     */
    public void changeSession(Map<String, Object> inputData)  {
	    String userTpCd = UserInfo.getUserInfo().getUserTpCd();
		inputData.put("orgCd", StringUtil.getText(inputData.get("orgCd")));
		inputData.put("userId", UserInfo.getUserInfo().getUserId());

		if (userTpCd.equals("01") || userTpCd.equals("02")) {	//내부사용자, 내부임시사용자
			//사용자 직위,직급, 직책정보 Session 추가
			Map<String, Object> uData = commonDao.queryForMap("loginXP.userEtcInfoChg", inputData);
			UserInfo.getUserInfo().setTitleInfo((HashMap)uData);
		}

		UserInfo.getUserInfo().setOrgCd(StringUtil.getText(inputData.get("orgCd")));
		List<Map<String, Object>> mDataO = commonDao.queryForMapList("loginXP.retrieveOrgList", inputData);
		UserInfo.getUserInfo().setOrgInfo(mDataO);

    }

    /**
     * <pre>
     * changePriv
     * 권한변경
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> changePriv(Map<String, Object> inputData)  {
        try {
            String privCd = StringUtil.getText(inputData.get("privCd"));
            String siteCd = "" ;
            if (privCd.equals("")) { // 최초 로그인
                inputData.put("userId", UserInfo.getUserId());
                if (WebContext.getRequest().getAttribute("auth") != null) {
                    privCd = (String) WebContext.getRequest().getAttribute("auth");
                }
                if (!privCd.equals("")) {
                    UserInfo.getUserInfo().setCurPriv(privCd);
                }
                privCd = UserInfo.getCurPriv();
            } else {
                siteCd = inputData.get("siteCd").toString();
            }
            logger.debug("changePriv privCd :" + privCd);

            // 현재의 권한 및 현장을 세션에 담음
            inputData.put("privCd", StringUtil.convertArray(privCd, "+"));

            //사용자 개인권한
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            inputData.put("userId", UserInfo.getUserId());
            inputData.put("today", DateUtil.getCurrentDateString());  //권한시작일, 종료일 비교
            result = authService.retrieveMyAllPrivCd(inputData);
            if(result.isEmpty()) {
                logger.debug(this.getClass().getName() + " co.err.noAuth");
                throw new BusinessException("co.err.noAuth");
            }
            if (siteCd.equals("")) {
                Map<String, Object> siteCdMap = result.get(0);
                siteCdMap =  MapUtil.setNullString(siteCdMap) ;
                siteCd = siteCdMap.get("siteCd").toString() ;
            }
            // 해당 권한의 현장 세션화
            if (WebContext.getRequest().getAttribute("auth") != null) {
                privCd = (String) WebContext.getRequest().getAttribute("auth");
            }
            if (WebContext.getRequest().getAttribute("siteCd") != null) {
                siteCd = (String) WebContext.getRequest().getAttribute("siteCd");
            }
            logger.debug("changePriv privCd from  session :" + privCd);

            //현장조회리스트 변경
            /* Map<String,Object> input2 = new HashMap<String,Object>();
            input2.put("siteCd", siteCd);
            input2.put("privCd", StringUtil.convertArray(privCd, "+"));
            input2.put("today", DateUtil.getCurrentDateString());

            Map<String, Object> etcInfo = UserInfo.getUserInfo().getEtcInfo();
	        etcInfo.put("privSiteCd", siteCd);
	        UserInfo.getUserInfo().setEtcInfo(etcInfo);

            List<Map<String, Object>> mData = siteService.retrievePrjCode(input2);
            Map<String, Object> curSite = new HashMap<String,Object>();
            if(mData.size() == 1) {
	            String orgChrcCls = StringUtil.getText(mData.get(0).get("orgChrcCls"));
	            if(orgChrcCls.equals("C")||orgChrcCls.equals("J")||orgChrcCls.equals("H")||orgChrcCls.equals("G")) {
	                curSite = mData.get(0); // 목록이라면 첫번째 현장로
	            }
            }
            UserInfo.getUserInfo().setCurSite((HashMap<String,Object>)curSite); */  //  결재에서는 로그인시 현장 처리 필요없음 2013.02.20 변형구

            LoginInfo loginInfo = new LoginInfo();
            loginInfo.resetMenuList();
            loginInfo.resetUserPriv();
            UserInfo.getUserInfo().setCurPriv(privCd);
            //UserInfo.getUserInfo().setCurPriv("123456");
            String source = StringUtil.getText(inputData.get("source"));

            // 메뉴 프로그램 정보 세션화
            inputData.put("privCd", privCd);
            inputData.put("userId", UserInfo.getUserId());
            inputData.put("today", DateUtil.getCurrentDateString());
            if (source.equals("link")) {
                String menuId = StringUtil.getText(inputData.get("BRANCHPAGE")).replace("undefined", "");
                if(NumberUtils.isNumber(menuId)) {
                    inputData.put("menuId", menuId);
                } else {
                    inputData.put("menuCd", menuId);
                }
            }
            // 메뉴리스트 세션화
            List<Map<String, Object>> menuList = authService.retrieveUserMenuList(inputData);
            int pgmSize = menuList.size();
            String menuArry = "";
            String userPrivArry = "";
            String menuPrivArry = "";
            for (int i = 0; i < pgmSize; i++) {
                menuArry += menuList.get(i).get("menuId").toString() + "|";
                userPrivArry += menuList.get(i).get("userPriv").toString() + "|";
                menuPrivArry += menuList.get(i).get("menuPriv").toString() + "|";
            }

            loginInfo.addMenuList(menuArry);
            loginInfo.addUserPriv(userPrivArry);
            loginInfo.addMenuPriv(menuPrivArry);
            UserInfo.setLoginInfo(loginInfo);


            //사용자별 개인권한 RETURN
            return result ;
        } catch (Exception se) {
            logger.debug(this.getClass().getName() + "." + "changePriv()" + "=>" + se.getMessage());
            throw new BusinessException(se.getMessage());
        }
    }
    /**
     * <pre>
     * 권한조회
     * </pre>
     * @param inputData
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unused" })
	public List<Map<String, Object>> retrieveMenu(Map<String, Object> data) {
        String dutySysCd = StringUtil.getText(data.get("dutySysCd")).toString();
        String privCdList =  StringUtil.getText(data.get("privCd")).toString();
        String sysCd = Constants.sysCd ;
        data.put("sysCd", sysCd);

        List roleList = new ArrayList();
        String UserType = UserInfo.getUserTpCd();
        String curPrivCd = UserInfo.getCurPriv() ;

        if (privCdList.equals("")) {
        	data.put("privCd", StringUtil.convertArray(curPrivCd, ","));
        } else {
        	data.put("privCd", StringUtil.convertArray(privCdList, ","));
        }

        @SuppressWarnings("unchecked")
		List<Map<String,Object>> sysList = (List<Map<String,Object>>)data.get("sysList");
        // 어드민 권한 여부 확인

		boolean isSystemAdmin = false;
		for ( int i = 0; i < sysList.size(); i++ ) {
			if (sysList.get(i).containsValue(Constants.sysCd)) {
				isSystemAdmin = true;
				break;
			}
		}

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String source = StringUtil.getText(data.get("source"));

        if (curPrivCd != null || !privCdList.equals("")) {
            result = menuXPService.retrieveMenu(data);
            if (!isSystemAdmin && !source.equals("imsi")) {
                if (result.isEmpty()) {
                    logger.debug(this.getClass().getName() + "." + "retrieveMenu() is Empty!");
                    throw new BusinessException(MessageCodes.MSG_ERROR_AUTHORIZE);
                }
            }
        } else {
            if (!isSystemAdmin && !source.equals("imsi")) {
                logger.debug(this.getClass().getName() + "." + "retrieveMenu() roleList is Empty!");
                throw new BusinessException(MessageCodes.MSG_ERROR_AUTHORIZE);
            }
        }
        return result;
    }


    /**
	 * <pre>
	 * 사용자가 관리자 권한을 가지는 시스템코드를 조회한다.
	 * </pre>
	 *
	 * @param requestMap
	 * @return 시스템 코드 Map List
	 */
	public List<Map<String, Object>> retrieveAdminSysCd(Map<String, Object> requestMap) {
		return commonDao.queryForMapList("Session.retrieveAdminSysCd", requestMap);
	}

	/**
	 * <pre>
	 * 권한 현장 조회 범위 설정
	 * </pre>
	 *
	 * @param requestMap
	 * @return
	 */
	public List<Map<String, Object>> retrievePrivTpList(Map<String, Object> requestMap) {
		List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        mData = commonDao.queryForMapList("Session.retrievePrivTpList", requestMap);
        String orgCd = StringUtil.getText(UserInfo.getOrgInfo().get("orgCd"));
        String centerOrgCd = StringUtil.getText(UserInfo.getOrgInfo().get("centerOrgCd"));

        for(int i=0;i<mData.size();i++) {
            String qrylang = StringUtil.getText(mData.get(i).get("qrylang")).toString();
            qrylang = qrylang.replace("#orgCd#", "'" + orgCd + "'");
            qrylang = qrylang.replace("#centerOrgCd#", "'" + centerOrgCd + "'");
            mData.get(i).put("qrylang", qrylang);
        }
        return mData ;
	}
}
