package com.dwenc.cmas.common.instance.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : InstcFactory
 * 설    명 : 모든 서버의 정보를 인스턴스별로 메모리하여 관리하는 서비스
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.instance.service.InstcService;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.FileUtil;
import docfbaro.common.MapUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.config.EnvironmentConfigProperties;
import docfbaro.iam.authentication.UserDefinition;

@Service("instcFactory")
public class InstcFactory implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(InstcFactory.class);

    @Autowired
    private InstcService service;

    @Autowired
    private EnvironmentConfigProperties properties;

    private List<Map<String, Object>> mInst = new ArrayList<Map<String, Object>>();

    private List<Map<String, Object>> mSite = new ArrayList<Map<String, Object>>();

	@PostConstruct
	public void init() {
        mInst = service.retrieveInstcList(new HashMap<String, Object>());
        mSite = service.retrieveInstcSiteLst(new HashMap<String, Object>());
	    writeConfigForXP();
	}

    /**
     * 정보가 없을 경우에 정보를 다시 찾아 오는 처리
     */
	private void isEmpty() {
        if(mInst.isEmpty() || mSite.isEmpty()) {
            mInst = service.retrieveInstcList(new HashMap<String, Object>());
            mSite = service.retrieveInstcSiteLst(new HashMap<String, Object>());
        }
    }

	/**
	 * 입력된 instcId에 해당하는 instc 목록을 반환한다.
	 *
	 * @param instcId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getInstc(String instcId) {
	    isEmpty();
	    for (int i = 0; i < mInst.size(); i++) {
            if (MapUtil.get(mInst, "instcId", i).toString().equals(instcId)) {
                Map<String, Object> instcInfo = (Map<String, Object>) mInst.get(i);
                // 관리자화면에서 인스턴스가 현장 장비가 아닐 경우는 100으로 고정
                if(StringUtil.getText(instcInfo.get("siteCd")).equals("")) instcInfo.put("siteCd", "100");
                return instcInfo;
            }
        }
		return new HashMap<String, Object>();
	}

    /**
     * 입력된 siteCd에 해당하는 instc 목록을 반환한다.
     *
     * @param siteCd
     * @return Map<String, Object>
     */
    public Map<String, Object> getInstcFromSiteCd(String siteCd) {
        isEmpty();
        for (int i = 0; i < mInst.size(); i++) {
            if (MapUtil.get(mInst, "siteCd", i).toString().equals(siteCd)) {
                return (Map<String, Object>) mInst.get(i);
            }
        }
        return new HashMap<String, Object>();
    }


    /**
     * OggCd로 부터 instcId를 리턴
     *
     * @return String
     */
    public String getInstcIdFromOggCd() {
        String oggCd = getOggCd();
        if(oggCd.equals(""))
            return "";
        Map<String, Object> input = getInstcFromSiteCd(oggCd);
        return StringUtil.getText(input.get("instcId"));
    }

    /**
     * 처리하고 있는 data에 맞는 oggCd를 리턴
     *
     * @return String
     */
    public String getOggCd() {
        Map<String, Object> instc = getInstc(Constants.instcId);
        String oggCd = StringUtil.getText(instc.get("siteCd")); // defaultSiteCd
        UserDefinition user = UserInfoHolder.getUserInfo(UserDefinition.class);
        if(user != null){
            // 결재 후 처리를 위해서 ectInfo를 사용함
            // 1. 기안 시점에 curSite를 결재 상신 table에 기록
            // 2. 결재 후 처리에 기록된 curSite 정보를 세션에 넘겨 줌(Header)
            if(!StringUtil.getText(WebContext.getRequest().getHeader("oggCd")).equals("")){
                oggCd = StringUtil.getText(WebContext.getRequest().getHeader("oggCd"));
            } else {
                // 기본 세션이 있을 경우
                oggCd = StringUtil.getText(user.getCurSite().get("siteCd"));
                if(oggCd.equals("")) {
                    // 세션을 잃어 버렸을 경우 COMTRAN.xjs 에서 전달됨
                    oggCd = StringUtil.getText(WebContext.getRequest().getParameter("oggCd"));
                    if(oggCd.equals("")) {
                        oggCd = user.getOrgCd();
                    }
                }
            }
        }
        return oggCd;
    }

	/**
	 * InstcFactory에서 사용하는 Map을 초기화한다.
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
     * domain 에 따른 javascript 분기 스크립트 리턴
     * sType - typedef : default_typedef.xml
     */
    public String getDomainJsScript(String sType){
        String script = "";
        try{
            // 로컬 서버에서 서비스 (본사일 경우는 로컬 자체)
            String url = properties.getUrl();
            if(url.endsWith("/")) url = url.substring(0, url.length());
            script += "<Service prefixid=\"dwe_svc\" type=\"JSP\" url=\""
            + url
            + "/\" cachelevel=\"none\" version=\"0\" communicationversion=\"0\"/>\n";

            // 본사 서버에서 서비스
            url = properties.getCenterUrl();
            if(url.endsWith("/")) url = url.substring(0, url.length());
            script += "    <Service prefixid=\"dwe_center\" type=\"JSP\" url=\""
                + url
                + "/\" cachelevel=\"none\" version=\"0\" communicationversion=\"0\"/>\n";
        }catch(Exception e){
            e.printStackTrace();
        }
        return script;
    }

    /**
     * default_typedef.xml 파일 변경
     */
    public void writeConfigForXP(){
        try{
            if(properties.getXpSetDomainUseYn().equals("true")){
                String scriptFile = properties.getDocRoot() + "/xui/default_typedef.xml";
                logger.debug("default_typedef.xml file 수정 : " + scriptFile);
                String bodytext = FileUtil.getFromFile(scriptFile, "utf-8").toString();
                String bodytext2 = bodytext.substring(0, bodytext.indexOf("url=\"http"));
                bodytext2 = bodytext2.substring(0, bodytext2.lastIndexOf("/>") + 2) + "\n";
                String bodytext3 = bodytext.substring(bodytext.lastIndexOf("url=\"http"), bodytext.length());
                bodytext3 = bodytext3.substring(bodytext3.indexOf("<Service"), bodytext3.length()).trim();
                bodytext = bodytext2 + "    " + getDomainJsScript("typedef") + "    " + bodytext3;
                FileUtil.write(scriptFile, new StringBuffer(bodytext), "utf-8");
            }
        }catch(Exception e){
            logger.debug("**** Could not read source file: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> getmInst(){
        return mInst;
    }

    public void setmInst(List<Map<String, Object>> mInst){
        this.mInst = mInst;
    }

    /**
     * Ogg time 얻기
     */
    public String getOggTime(String oggTime, String instcId){
        Map<String, Object> inst = getInstc(instcId);
        String timeGap = StringUtil.getText(inst.get("timeGap"));
        if(timeGap.equals("")) timeGap = "0";
        int minutGap = 60 * Integer.parseInt(timeGap);
        try{
            return DateUtil.addMinute(oggTime, minutGap, "yyyyMMddHHmmssSSS");
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Ogg time 얻기
     */
    public String getOggTime(){
        return getOggTime(DateUtil.getCurrentDateString("yyyyMMddHHmmssSSS"), Constants.instcId); //yyyyMMddHHmmssSSS, YYYYMMDDHH24MISSFF3
    }

    /**
     * 대표 siteCd 얻기
     *
     * @return String
     */
    public String getRepSiteCd(String siteCd) {
        isEmpty();
        if(siteCd.equals("100")) return Constants.instcId;
        if(mSite.size() == 0) return siteCd;
        String defaultSiteCd = "";
        String instcId = "";
        for(int i=0;i<mSite.size();i++) {
            if(StringUtil.getText(mSite.get(i).get("siteCd")).equals(siteCd)) {
                instcId = StringUtil.getText(mSite.get(i).get("instcId"));
                break;
            }
        }
        for(int i=0;i<mSite.size();i++) {
            if(StringUtil.getText(mSite.get(i).get("instcId")).equals(instcId)
                    && StringUtil.getText(mSite.get(i).get("repSiteYn")).equals("Y")) {
                defaultSiteCd = StringUtil.getText(mSite.get(i).get("siteCd"));
                break;
            }
        }
        if(siteCd.equals("")) {
            return Constants.instcId;
        } else if(defaultSiteCd.equals("")) {
            return siteCd;
        }
        return defaultSiteCd;
    }
}

