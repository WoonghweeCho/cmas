package com.dwenc.cmas.common.sysMng.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : WasFactory
 * 설    명 : 와스 정보를 메모리로 관리
 * 작 성 자 :
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01             최초 작성
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
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.sysMng.service.WasService;

import docfbaro.common.MapUtil;

@Service("wasFactory")
public class WasFactory implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(WasFactory.class);

    @Autowired
    private WasService service;

    private List<Map<String, Object>> mWas = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> wasAdminList = new ArrayList<Map<String, Object>>();

	@PostConstruct
	public void init() {
	    mWas = service.retrieveWasList(new HashMap<String, Object>());
        wasAdminList = service.retrieveWasAdmin(new HashMap<String, Object>());
	}

	/**
	 * 입력된 wasId에 해당하는 was 목록을 반환한다.
	 *
	 * @param wasId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getWas(String wasId) {
	    for (int i = 0; i < mWas.size(); i++) {
            if (MapUtil.get(mWas, "wasId", i).toString().equals(wasId)) {
                return (Map<String, Object>) mWas.get(i);
            }
        }
		return new HashMap<String, Object>();
	}

    /**
     * 입력된 instcId에 해당하는 was 목록을 반환한다.
     *
     * @param instcId
     * @return Map<String, Object>
     */
    public Map<String, Object> getWasFromInstcId(String instcId) {
        for (int i = 0; i < mWas.size(); i++) {
            if (MapUtil.get(mWas, "instcId", i).toString().equals(instcId)) {
                return (Map<String, Object>) mWas.get(i);
            }
        }
        return new HashMap<String, Object>();
    }

    /**
     * WasAdmin 목록을 반환한다.
     *
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getWasAdminList() {
        return wasAdminList;
    }

	/**
	 * WasFactory에서 사용하는 Map을 초기화한다.
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

    public List<Map<String, Object>> getmWas(){
        return mWas;
    }

    public void setmWas(List<Map<String, Object>> mWas){
        this.mWas = mWas;
    }
}

