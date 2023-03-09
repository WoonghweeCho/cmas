package com.dwenc.cmas.common.menu.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : MenuFactoryImpl
 * 설    명 : MenuFactory의 구현체로 메뉴정보를 메모리에 캐쉬
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dwenc.cmas.common.locale.service.LocaleService;

import docfbaro.common.Constants;
import docfbaro.common.MapUtil;
import docfbaro.common.MenuFactory;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;

public class MenuFactoryImpl implements MenuFactory, Observer {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(MenuFactoryImpl.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    @Autowired
    private LocaleService localeService;

	private Map<String, List> mMenuList = new HashMap<String, List>();

    private Map<String, List> mDisabledMenuList = new HashMap<String, List>();

    /**
     * 메뉴정보를 메모리에 캐쉬한다.
     */
	@PostConstruct
	public void init() {
        this.mMenuList = new HashMap<String, List>();
        this.mDisabledMenuList = new HashMap<String, List>();
        try {
            List<Map<String, Object>> mData = localeService.retrieveLocaleList(new HashMap<String, Object>());
            for(int i=0;i<mData.size();i++) {
                findMenuInfo(mData.get(i).get("loclCd").toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
	}

	// 메뉴 조회
	public void findMenuInfo(String aJavaLoclCd) {
		Map<String, Object> input = new HashMap<String, Object>();
        input.put("sysCd", Constants.sysCd); //시스템코드 추가
        input.put("loclCd", aJavaLoclCd);
		mMenuList.put(aJavaLoclCd, dao.queryForMapList("menuSys.retrieveMenuList", input));
        input.put("useYn", "N");
        mDisabledMenuList.put(aJavaLoclCd, dao.queryForMapList("menuSys.retrieveDisabledMenuList", input));
	}

	/**
	 * 입력된 aLoclCd에 해당하는 menu 목록을 반환한다.
	 *
	 * @param rslt
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> get(String aLoclCd) {
		if (mMenuList.containsKey(aLoclCd)) {
			return (List<Map<String, Object>>) mMenuList.get(aLoclCd);
		}
		return new ArrayList();
	}

    /**
     * 입력된 aLoclCd에 해당하는 mDisabledMenu 목록을 반환한다.
     *
     * @param rslt
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDisabled(String aLoclCd) {
        if (mDisabledMenuList.containsKey(aLoclCd)) {
            return (List<Map<String, Object>>) mDisabledMenuList.get(aLoclCd);
        }
        return new ArrayList();
    }

	/**
	 * 입력된 aLoclCd에 해당하는 menu르 반환한다.
	 *
	 * @param rslt
	 * @return List<Map<String, Object>>
	 */
	public Map getMenuInfo(String aLoclCd, String aMenuId) {
		List<Map<String, Object>> mData = get(aLoclCd);
		for (int i = 0; i < mData.size(); i++) {
			if (MapUtil.get(mData, "menuId", i).toString().equals(aMenuId)) {
				return (Map<String, Object>) mData.get(i);
			}
		}
		return new HashMap();
	}

	/**
	 * MenuFactory에서 사용하는 Map을 초기화한다.
	 */
	public void refresh() {
		synchronized (this) {
		    init();
		}
	}

	/**
	 * <PRE>
	 * </PRE>
	 *
	 * @param o
	 * @param arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		this.refresh();
	}

	/**
	 * Menu Navigation 관련 정보 조회
	 *
	 * @param input[menuCd]
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> menuNavigationList(Map inputData) throws Exception {
		List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
		if (Constants.menuCacheUseYn.equals("true")) {
			String menuCd = StringUtil.getText(inputData.get("selectMenuCd"));
			List<Map<String, Object>> tData = this.get(UserInfo.getLoclCd());
			boolean bChk = true;
			String prntMenuCd = "";
			while (bChk) {
				for (int i = 0; i < tData.size(); i++) {
					if (StringUtil.getText(tData.get(i).get("menuCd")).equals(menuCd)) {
						prntMenuCd = StringUtil.getText(tData.get(i).get("prntMenuCd"));
						mData.add(mData.size(), tData.get(i));
						break;
					}
				}
				if (prntMenuCd.equals(menuCd)) {
					bChk = false;
				} else {
					menuCd = prntMenuCd;
				}
			}
			tData.addAll(mData);
			mData = new ArrayList<Map<String, Object>>();
			int cnt = tData.size() - 1;
			for (int i = cnt; i >= 0; i--) {
				mData.add(mData.size(), tData.get(i));
			}
		} else {
			String selectMenuId = getMenuId(inputData.get("selectMenuCd").toString());
			if (selectMenuId != null)
				inputData.put("selectMenuId", selectMenuId);
			mData = dao.queryForMapList("COA0201.menuNavigation", inputData);
		}
		return mData;
	}

	public String getMenuId(String menuCd) {
		List<Map<String, Object>> mData = this.get(UserInfo.getLoclCd());
		for (int i = 0; i < mData.size(); i++) {
			if (MapUtil.get(mData, "menuCd", i).toString().equals(menuCd)) {
				return MapUtil.get(mData, "menuId", i).toString();
			}
		}
		return "";
	}

	public String getMenuCd(String menuId) {
		List<Map<String, Object>> mData = this.get(UserInfo.getLoclCd());
		for (int i = 0; i < mData.size(); i++) {
			if (MapUtil.get(mData, "menuId", i).toString().equals(menuId)) {
				return MapUtil.get(mData, "menuCd", i).toString();
			}
		}
		return "";
	}

	public String getMenuNm(String menuCd) {
		List<Map<String, Object>> mData = this.get(UserInfo.getLoclCd());
		for (int i = 0; i < mData.size(); i++) {
			if (MapUtil.get(mData, "menuCd", i).toString().equals(menuCd)) {
				return MapUtil.get(mData, "menuNm", i).toString();
			}
		}
		return "";
	}
}

