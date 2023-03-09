package com.dwenc.cmas.common.login.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : LoginService
 * 설    명 : Login 처리를 위한 Service
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

import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.code.CommonCodes;
import com.dwenc.cmas.common.datasource.service.support.DatSrcFactory;
import com.dwenc.cmas.common.instance.service.support.InstcFactory;
import com.dwenc.cmas.common.locale.service.LocaleService;
import com.dwenc.cmas.common.login.domain.LoginInfo;
import com.dwenc.cmas.common.priv.service.PrivService;
import com.dwenc.cmas.common.session.domain.CoMenuBkmrk;
import com.dwenc.cmas.common.session.service.SessionService;
import com.dwenc.cmas.common.sysMng.service.support.WasFactory;
import com.dwenc.cmas.common.utils.CollectionUtil;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.MessageCodes;
import docfbaro.common.ObjUtil;
import docfbaro.common.ServletUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.CommonDao;
import docfbaro.security.SecurityUtil;
import docfbaro.sua.exception.BusinessException;

@Service("loginService")
public class LoginService {

    /**
     * 로그처리를 위한 logger
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    @Qualifier("mainDB")
    private CommonDao commonDao;

    @Autowired
    private PrivService authService;

    @Autowired
	private SessionService sessionService;

    @Autowired
    private DatSrcFactory datSrcFactory;

    @Autowired
    private InstcFactory instcFactory;

    @Autowired
    private WasFactory wasFactory;

    @Autowired
    private LocaleService localeService;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * 로그인을 위한 사용자 정보 상세조회  -- 사용됨
     *
     * @param Map[input member_id, member_pwd]
     * @return
     * @throws Exception
     */
    public UserDefinition memberLogin(Map<String, Object> data, UserDefinition userInfo){
        try{
            Map<String, Object> etcInfo = userInfo.getEtcInfo();
            Map<String, Object> input = (Map<String, Object>)((HashMap)data).clone();
            String userId = StringUtil.getText(data.get("tUserId"));
            String password = SecurityUtil.base64Encode(StringUtil.getText(data.get("j_password")));
            userInfo.setPasswd(password);

            logger.debug("memberLogin() userId :" + userId);
            input.put("useYn", "Y");

            String userTpCd = userInfo.getUserTpCd();	//사용자유형

            //사용자 유형별 Session Make
            logger.debug("memberLogin() userTpCd:" + userTpCd);
            input.put("userId", userInfo.getUserId());
            if(userTpCd.equals("01") || userTpCd.equals("02")){ //내부사용자, 내부임시사용자
                //사용자 직위,직급, 직책정보 Session 추가
                Map<String, Object> uData = commonDao.queryForMap("loginXP.userEtcInfo", input);
                userInfo.setTitleInfo((HashMap)uData);
            }

        	input.put("orgCd", userInfo.getOrgCd());
            //URL호출로 로긴시 겸직 부서를 세션값으로 변경함
            String BRANCH = StringUtil.getText(data.get("BRANCH"));
            String otherParams = StringUtil.getText(data.get("otherParams"));
            if (BRANCH.equals("op1")) {
            	if (otherParams.indexOf("apptCd^") > -1) {
            		int strIndex = otherParams.indexOf("apptCd^") ;
            		String apptCd = otherParams.substring(strIndex+7, strIndex+12) ;
            		if (!apptCd.equals("")) {
            			input.put("orgCd", apptCd);
            		}
            	}
            }

            //조직정보 조회
            List<Map<String, Object>> mDataO = commonDao.queryForMapList("loginXP.retrieveOrgList", input);
            userInfo.setOrgInfo(mDataO);

            userInfo.setEtcInfo(etcInfo);



        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
            throw new BusinessException(e.getMessage());
        }
        return toUserData(userInfo);
    }

    /**
     * 권한 정보 세션화 -- 사용됨
     *
     * @param Map[input member_id, member_pwd]
     * @return
     */
    public UserDefinition makePriv(Map<String, Object> data, UserDefinition userInfo){
        try{
            data.put("userId", userInfo.getUserId());

            // 권한 세션화
            List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
            data.put("today", DateUtil.getCurrentDateString());
            if(WebContext.getRequest().getRequestURL().indexOf("Login.xpl") > -1){
                data.put("privCdAll", "true");
                roleList = authService.retrieveMyAllPrivCd(data);
                data.put("privCdAll", ""); // 초기화 필요!
            }else{
                roleList = authService.retrieveMyAllPrivCd(data);
            }

            String systemAdmin = Constants.privSystemAdmin;
            boolean bSysAdmin = false;
            for(int i = 0; i < roleList.size(); i++){
                String code = roleList.get(i).get("code").toString();
                if(code.equals(systemAdmin)){
                    bSysAdmin = true;
                }
                if(!userInfo.hasPriv(code)){
                    userInfo.addPriv(code);
                }
            }
            if(bSysAdmin){
                userInfo.setCurPriv(systemAdmin);
            }else if(!StringUtil.getText(data.get("CurPriv")).equals("")){
                userInfo.setCurPriv(data.get("CurPriv").toString());
            }
            logger.debug("makePriv() getPrivs  :" + userInfo.getPrivs());
            logger.debug("makePriv() getCurPriv:" + userInfo.getCurPriv());

            if(Constants.empInfoUseYn.equals("true")){
                data.put("queryUserId", data.get("userId"));
            }

            // 기타 시스템 정보
            Map<String, Object> etcInfo = userInfo.getEtcInfo();
            String dutySysCd = "co";

            etcInfo.put("dutySysCd", dutySysCd);
            etcInfo.put("docRoot", Constants.docRoot);

            etcInfo.put("instcId", Constants.instcId);
            data.put("instcId", Constants.instcId);

            // 서버 인스턴스 정보 추출
            String instcInfo = "|" + CollectionUtil.mapToString(getInstcInfo(data));
            etcInfo.put("instcInfo", instcInfo);
            String serverTime = DateUtil.getCurrentDateString("yyyyMMddHHmmssSSS");
            System.out.println("serverTime:" + serverTime);
            etcInfo.put("serverTime", serverTime);

            etcInfo.put("serverStage", Constants.stage);
            etcInfo.put("sysCd", Constants.sysCd);

            userInfo.setEtcInfo(etcInfo);

            logger.debug("makePriv() userData.getSource() : " + userInfo.getSource());
            logger.debug("makePriv() data.get( source ) : " + data.get("source"));
        }catch(Exception se){
            logger.trace("makePriv()=>" + se);
            WebContext.getRequest().setAttribute(Constants.ERROR_MESSAGE_KEY, se.getMessage());
            throw new BusinessException(se.getMessage());
        }
        return userInfo;
    }

    /**
    * 초기 화면에서 이동해야 하는 초기 로그인 화면을 추출함  -- 사용됨
    * @param input
    * @return
    */
    public Map<String, Object> getInstcInfo(Map<String, Object> data){
        String instcId = StringUtil.getText(data.get("instcId"));
        String loginUrl = "";
        String clientIp = "";
        String chkIp = "";


        loginUrl = StringUtil.getText(WebContext.getRequest().getRequestURL());
        try {
			loginUrl = ServletUtil.getDomain(StringUtil.getText(loginUrl) );
		} catch (Exception e) {
			loginUrl = "";
		}

        loginUrl = loginUrl.toUpperCase();


        if(instcId.equals("")){
            // 호출된 referer를 통해서 해당 instcId를 추정함
            String referer = StringUtil.getText(WebContext.getRequest().getParameter("referer"));
            if(referer.equals(""))
                referer = StringUtil.getText(data.get("referer"));
            if(!referer.equals("")){
                System.out.println("referer-1:" + referer);
                List<Map<String, Object>> list = wasFactory.getmWas();
                for(int i = 0; i < list.size(); i++){
                    if(referer.indexOf(StringUtil.getText(list.get(i).get("svcUrl"))) > -1){
                        instcId = StringUtil.getText(list.get(i).get("instcId"));
                        break;
                    }
                }
            }
        }
        if(instcId.equals(""))
        {
            instcId = StringUtil.getText(appProperties.get("dwe.serverInfo.instcId"));

        }


        clientIp = WebContext.getRequest().getHeader("Proxy-Client-IP");
              if(clientIp == null){
                  clientIp = WebContext.getRequest().getHeader("WL-Proxy-Client-IP");
                  if(clientIp == null){
                      clientIp = WebContext.getRequest().getHeader("X-Forwarded-For");
                      if(clientIp == null){
                          clientIp = WebContext.getRequest().getRemoteAddr();
                      }
                  }
              }

        System.out.println("instcId:" + instcId);
        Map<String, Object> instcInfo = instcFactory.getInstc(instcId);
        Map<String, Object> wasInfo = wasFactory.getWasFromInstcId(instcId);
        wasInfo.put("instcIp", instcInfo.get("ip"));
        wasInfo.put("instcNm", instcInfo.get("instcNm"));


        wasInfo.put("siteCd", instcInfo.get("siteCd"));
        wasInfo.put("oprMethCls", instcInfo.get("oprMethCls"));
        return wasInfo;
    }


    /**
     * RSA Key 생성  -- 사용되었음
     */
    public Map<String, Object> getRSAKey(Map<String, Object> output){
        try{
            // 암호화
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(512);
            KeyPair keyPair = generator.genKeyPair();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            HttpServletRequest req = WebContext.getRequest();
            HttpSession session = req.getSession();
            // 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
            session.setAttribute("__rsaPrivateKey__", privateKey);
            // 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
            RSAPublicKeySpec publicSpec = (RSAPublicKeySpec)keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            String publicKeyModulus = publicSpec.getModulus().toString(16);
            String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
            output.put("publicKeyModulus", publicKeyModulus);
            output.put("publicKeyExponent", publicKeyExponent);
            req.setAttribute("publicKeyModulus", publicKeyModulus);
            req.setAttribute("publicKeyExponent", publicKeyExponent);
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }


    /**
     * 사용자정보 Map를 UserData로 변환  -- 사용됨
     *
     * @param result
     * @return null if result is empty.
     */
    private UserDefinition toUserData(UserDefinition userInfo){
        if(userInfo == null){
            return null;
        }

        userInfo.setIpAddress(WebContext.getRequest().getRemoteAddr());
        userInfo.setAccessTm(DateUtil.getCurrentDateString("yyyyMMddHHmmss"));
        if(userInfo.getSource() == null){
            userInfo.setSource(WebContext.getRequest().getHeader("referer"));
        }

        logger.info("toUserData() userData.getSource() : " + userInfo.getSource());
        return userInfo;
    }

    /**
     * 사용자 권한(역할) 목록조회
     *
     * @param input
     * @return
     */
    public List<Map<String, Object>> userPrivList(Map<String, Object> input){
        return commonDao.queryForMapList("loginXP.userPrivList", input);
    }

    /**
     * 패스워드를 변경하는 메소드
     */
    public void changePwd(Map<String, Object> input){
        try{
            if(!isValidPwd(input)){
                WebContext.getRequest().setAttribute("msg", MessageCodes.MSG_WARN_PWD_INVALID);
                throw new BusinessException(MessageCodes.MSG_WARN_PWD_INVALID);
            }

            String userId = input.get("tUserId").toString();
            String dencodedUserId = new String(SecurityUtil.base64Decode(userId));
            dencodedUserId = URLDecoder.decode(dencodedUserId, "utf-8");

            String userPwd = input.get("tUserPwd").toString();
            String dencodedPwd = new String(SecurityUtil.base64Decode(userPwd));
            dencodedPwd = URLDecoder.decode(dencodedPwd, "utf-8");

            String newPwd = input.get("newPwd").toString();
            newPwd = new String(SecurityUtil.base64Decode(newPwd));
            newPwd = URLDecoder.decode(newPwd, "utf-8");
            input.put("userId", dencodedUserId);
            if(input.get("userId").equals("") && input.get("loginId").equals(""))
                throw new BusinessException(MessageCodes.MSG_ERROR_UPDATE);

            UserDefinition result = UserInfoHolder.getUserInfo(UserDefinition.class);

            // 패스워드 불일치 확인
            String pwd = result.getPassword();
            if(!pwd.equals("")){
                String digestedPwd = "";
                if(pwd.length() == 24){
                    digestedPwd = SecurityUtil.Encode(dencodedPwd);
                    newPwd = SecurityUtil.Encode(newPwd);
                }else{
                    digestedPwd = SecurityUtil.makeUniqueKey(dencodedPwd);
                    newPwd = SecurityUtil.makeUniqueKey(newPwd);
                }
                if(!digestedPwd.equals(result.getPassword())){
                    logger.info("pwd is different :" + input.get("userPwd") + "/" + digestedPwd + "/"
                            + SecurityUtil.Decode(digestedPwd));
                    throw new BusinessException(MessageCodes.MSG_ERROR_PWD);
                }

                input.put("pwd", newPwd);
                commonDao.update("loginXP.changePwd", input);
            }
        }catch(Exception e){
            WebContext.getRequest().setAttribute("msg", MessageCodes.MSG_ERROR_UPDATE);
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 패스워드가 유효한 값들(숫자와 문자의 조합)로 구성되어 있는지 확인
     *
     * @param input
     * @return
     */
    public boolean isValidPwd(Map<String, Object> input){
        boolean result = false;
        try{
            String userPwd = input.get("tUserPwd").toString();
            String dencodedPwd = new String(SecurityUtil.base64Decode(userPwd));
            dencodedPwd = URLDecoder.decode(dencodedPwd, "utf-8");

            int letterCnt = 0;

            for(int i = 0; i < dencodedPwd.length(); i++){
                char c = dencodedPwd.charAt(i);
                if(Character.isLetter(c))
                    letterCnt++;
                else if(Character.isDigit(c)){
                }
            }

            // 8자리 이상이고(입력 때 체크되지만) 문자와 숫자가 적어도 한 개씩은 있어야 한다.
            if(dencodedPwd.length() >= 6 && letterCnt > 0){  // && digitCnt > 0
                result = true;
            }
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        return result;
    }

    /**
     * 로그아웃을 위한 처리
     *
     * @param Map[input member_id, member_pwd]
     * @return
     */
    public void logOut(Map<String, Object> input){
        WebContext.removeUserSessionId();
        WebContext.getRequest().getSession().invalidate();

        WebContext.setRequest(WebContext.getRequest());
        WebContext.setResponse(WebContext.getResponse());

        if(StringUtil.getText(input.get("source")).equals("front")){
            WebContext.getRequest().setAttribute("href", WebContext.getRequest().getHeader("referer"));
        }else if(StringUtil.getText(input.get("source")).equals("index")){
            WebContext.getRequest().setAttribute("href", input.get("url"));
        }
    }

    /**
     * 서블릿을 통한 임의 로그인
     *
     * @param Map[input member_id, member_pwd]
     * @return
     * @throws Exception
     */
    public void servletLogin(Map<String, Object> inputData, UserDefinition userInfo){
        String crossDomain = "";
        if(WebContext.getRequest().getParameter("crossDomain") != null){
            crossDomain = WebContext.getRequest().getParameter("crossDomain");
        }
        String source = "";
        if(!ServletUtil.getData(WebContext.getRequest(), "source").equals("")){
            source = ServletUtil.getData(WebContext.getRequest(), "source");
        }

        String cookieUserId = "";
        String jSessionId = "";
        Cookie[] cookies = ((HttpServletRequest)WebContext.getRequest()).getCookies();

        if(cookies != null){
            for(int i = 0; i < cookies.length; ++i){
                logger.info("cookies(" + cookies[i].getName() + "):" + cookies[i].getValue());
                if(cookieUserId.equals("") && cookies[i].getName().equals("UID")){
                    cookieUserId = getPortalUserId(cookies[i].getValue());
                    if(cookieUserId.equals("")){
                        logger.debug("servletLogin() cookie에 UserId 가 없습니다.");
                    }
                }
                if(jSessionId.equals("") && cookies[i].getName().equals("JSESSIONID")){
                    jSessionId = cookies[i].getValue();
                    logger.debug("servletLogin() JSESSIONID :" + jSessionId);
                }
            }
        }

        // 개발환경에서 tempUserUseYn 일 경우 또는 crossDomain으로 요청된 경우(PM -> SM)
        String userId = "";
        String passwd = "";
        if(inputData.get("tUserId") != null)
            userId = inputData.get("tUserId").toString();

        logger.debug("servletLogin() tUserId:" + userId);
        logger.debug("servletLogin() source:" + source);
        logger.debug("servletLogin() cookieUserId:" + cookieUserId);
        logger.debug("servletLogin() jSessionId:" + jSessionId);
        logger.debug("servletLogin() crossDomain:" + crossDomain);
        if(userId.equals("")){
            if(cookieUserId.equals("") && Constants.tempUserUseYn.equals("true")){
                userId = Constants.tempUserId;
                passwd = Constants.tempPassword;
                logger.debug("servletLogin() tempUser:" + userId);
                source = "imsi";
            }else if(!cookieUserId.equals("")){
                userId = cookieUserId;
                source = "imsi";
            }
        }else{
            passwd = inputData.get("tUserPwd").toString();
        }

        logger.debug("servletLogin() JSESSIONID =>" + jSessionId);
        if(!crossDomain.equals("")){
            Cookie cookie = new Cookie("JSESSIONID", jSessionId);
            cookie.setMaxAge(1 * 60 * 60);
            cookie.setDomain(".daewooenc.com");
            WebContext.getResponse().addCookie(cookie);
            WebContext.setResponse(WebContext.getResponse());
        }
        logger.debug("servletLogin() userId:" + userId);
        logger.debug("servletLogin() passwd:" + passwd);

        if(!userId.equals("")){
            inputData.put("source", source);
            inputData.put("tUserId", userId);
            inputData.put("tUserPwd", passwd);
            inputData.put("CurPriv", WebContext.getRequest().getParameter("CurPriv"));
            UserDefinition user = memberLogin(inputData, userInfo);
            user.setSource(source);
            logger.debug("servletLogin() ServletUtil.getJSessionId():" + ServletUtil.getJSessionId());
            WebContext.setUserSessionId(ServletUtil.getJSessionId());
            makePriv(inputData, user);
            logger.debug("servletLogin() servletLogin process -> " + ServletUtil.serverURL(WebContext.getRequest()));
            // 나머지 정보 세션 로딩
            retrieveLoadAllInfo(inputData, userInfo);
        }else{
            throw new BusinessException(MessageCodes.MSG_ERROR_NOSESSION);
        }
    }

    /**
     * 포탈 계정을 쿠키에서 추출
     *
     * @param getPortalUserId
     * @return
     * @throws Exception
     */
    public String getPortalUserId(String cookieUserId){
        if(cookieUserId.indexOf("__") > 0){
            cookieUserId = cookieUserId.substring(0, cookieUserId.indexOf("__"));
        }
        cookieUserId = SecurityUtil.base64Decode(cookieUserId).toString();
        int nbs_cnt = 0;
        int bs_cnt = 0;
        byte[] bs = cookieUserId.getBytes();
        // 0이 아닌 바이트수를 구한다.
        for(int j = 0; j < bs.length; j++){
            if(bs[j] != 0){
                nbs_cnt++;
            }
        }
        byte[] nbs = new byte[nbs_cnt];
        for(int j = 0; j < bs.length; j++){
            if(bs[j] != 0){
                nbs[bs_cnt] = bs[j];
                bs_cnt++;
            }
        }
        cookieUserId = new String(nbs);
        return cookieUserId;
    }

    /**
     * TOP Menu 목록 조회
     *
     * @param input
     * @return
     */
    public List<Map<String, Object>> topMenuList(String source, String curPriv, List<String> roles){
        Map<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("sysCd", Constants.sysCd);
        if(source != null){
            inputData.put("sysAdmin", "not sysAdmin");
            inputData.put("privCd", StringUtil.convertArray(curPriv, "+"));
        }else{
            if(curPriv.equals("") && !curPriv.equals(CommonCodes.AUTHORITY_SYSTEM_ADMIN)){
                inputData.put("sysAdmin", "not sysAdmin");
                if(Constants.privAuthSum.equals("true")){
                    inputData.put("privCd", roles);
                }else{
                    inputData.put("privCd", StringUtil.convertArray(curPriv, "+"));
                }
            }
        }
        return commonDao.queryForMapList("loginXP.topMenuList", inputData);
    }



    // input : orgCd, privCd, siteCd
    /**
	 * <pre>
	 *  retrieveLoadAllInfo
	 * </pre>
	 * @param
	 * @param
	 */
    public void retrieveLoadAllInfo( Map<String, Object> input, UserDefinition userInfo )  {

	    if(input.get("orgCd") == null || input.get("orgCd").equals(""))
	    	input.put("orgCd", StringUtil.getText(UserInfo.getOrgInfo().get("orgCd"))) ;

		// 로긴시 권한,현장 조회범위등 관리
		List<Map<String, Object>> authList = sessionService.changePriv(input);

		//  권한 변경 로그인
		if(input.get("orgCd") != null) {
			sessionService.changeSession(input);
		}

        // 최초 로딩 시에 올려야 할 것들만 기술함.
        String initInfo = "EtcInfo,UserNm,CurSite,LoclCd,Conf,Passwd,UserId,CurPriv";
        input.put("initInfo", initInfo);

        // 관리자 권한을 가지고 잇는 시스템 코드 세션과 global dataset 에 추가 하기 위해 2012-05-24
        List<Map<String,Object>> sysList = sessionService.retrieveAdminSysCd(input);
        input.put("sysList", sysList);

		List<Map<String, Object>> sessionList = retrieveSession(input);
		// dutySysCd privCd


        // 로케일 조회
        List<Map<String, Object>> loclLst = localeService.retrieveLocaleList(new HashMap<String, Object>());
        // 세션 etcInfo 개체 get
        Map etcInfo = userInfo.getEtcInfo();

        // 로케일별 메뉴를 가져온다.
        for ( int i = 0; i < loclLst.size(); i++ ) {
        	input.put("loclCd", StringUtil.getText(loclLst.get(i).get("loclCd")));
        	List<Map<String, Object>> menuList = sessionService.retrieveMenu(input);
        	String menuJStr = makeMenuPrivJSONData(menuList, sessionList);

//        	// 로케일별 메뉴목록 세션에 저장
        	etcInfo.put("JSONMenuAuth_"+StringUtil.getText(loclLst.get(i).get("loclCd")), menuJStr);
        }
        String authJStr = makeAuthPrivJSONData( authList );  // 권한 목록

        etcInfo.put("JSONAuthList", authJStr);

        userInfo.setEtcInfo(etcInfo);
	}

    /**
	 * <pre>
	 *  javascript 에서 사용할 JSON 형태의 겸직 데이터 만든다. .
	 * </pre>
	 * @param
	 * @param
	 */
    private String makeAuthPrivJSONData( List<Map<String, Object>> authList ) {

    	StringBuffer strJs = new StringBuffer();
        strJs.append(" { \"auth\": [ \n");

        for ( int idx = 0; idx < authList.size(); idx++ ) {
        	String orgNm = StringUtil.getText(authList.get(idx).get("orgNm"));
        	String orgNmEn = StringUtil.getText(authList.get(idx).get("orgNmEn"));
        	String siteCd = StringUtil.getText(authList.get(idx).get("siteCd"));
        	String userId = StringUtil.getText(authList.get(idx).get("userId"));
        	String value = StringUtil.getText(authList.get(idx).get("value"));
        	String siteNm = StringUtil.getText(authList.get(idx).get("siteNm"));
        	String siteNmEn = StringUtil.getText(authList.get(idx).get("siteNmEn"));
        	String orgChrcCls = StringUtil.getText(authList.get(idx).get("orgChrcCls"));
        	String code = StringUtil.getText(authList.get(idx).get("code"));
        	String apptCls = StringUtil.getText(authList.get(idx).get("apptCls"));
        	String orgCd = StringUtil.getText(authList.get(idx).get("orgCd"));

        	strJs.append("{ \"orgNm\":\""+orgNm+"\","+
        				   "\"orgNmEn\":\""+orgNmEn+"\","+
        				   "\"siteCd\":\""+siteCd+"\","+
        				   "\"userId\":\""+userId+"\","+
        				   "\"value\":\""+value+"\","+
        				   "\"siteNm\":\""+siteNm+"\","+
        				   "\"siteNmEn\":\""+siteNmEn+"\","+
        				   "\"orgChrcCls\":\""+orgChrcCls+"\","+
        				   "\"code\":\""+code+"\","+
        				   "\"apptCls\":\""+apptCls+"\","+
        				   "\"orgCd\":\""+orgCd+"\"} " );

        	if ( idx ==  authList.size() -1 ) {
        		strJs.append("\r\n ] }; \r\n");
        	}
        	else {
        		strJs.append(", \r\n");
        	}
        }
    	return strJs.toString();
    }


    /**
	 * <pre>
	 *  javascript 에서 사용할 JSON 형태의 메뉴데이터를 만든다. .
	 * </pre>
	 * @param
	 * @param
	 */
    private String makeMenuPrivJSONData( List<Map<String, Object>> menuList, List<Map<String, Object>> sessionList ) {

    	LoginInfo loginInfo = (LoginInfo) UserInfo.getLoginInfo();
    	String sMenuList = "";
    	String sMenuPriv = "";
    	String sUserPriv = "";

    	for ( int idx = 0; idx < sessionList.size(); idx++ ) {
    		if ( sessionList.get(idx).containsValue("MenuList") ) {
    			sMenuList = StringUtil.getText(sessionList.get(idx).get("value"));
    		}
    		else if ( sessionList.get(idx).containsValue("MenuPriv") ) {
    			sMenuPriv = StringUtil.getText(sessionList.get(idx).get("value"));
    		}
    		else if ( sessionList.get(idx).containsValue("UserPriv") ) {
    			sUserPriv = StringUtil.getText(sessionList.get(idx).get("value"));
    		}
    	}


    	String[] aryMenuList = sMenuList.split("\\|");
    	String[] aryMenuPriv = sMenuPriv.split("\\|");
    	String[] aryUserPriv = sUserPriv.split("\\|");


    	StringBuffer strJs = new StringBuffer();
        strJs.append(" { \n");


        for ( int idx = 0; idx < menuList.size(); idx++) {
    		boolean bMakeStr = false;
    		String menuId = StringUtil.getText(menuList.get(idx).get("menuId"));
			String menuCd = StringUtil.getText(menuList.get(idx).get("menuCd"));
			String menuNm = StringUtil.getText(menuList.get(idx).get("caption"));
			String menuUrl = StringUtil.getText(menuList.get(idx).get("formId"));
			String menuDepth = StringUtil.getText(menuList.get(idx).get("depth"));
			String menuVar = ObjUtil.isNull(menuList.get(idx).get("menuVar")) ? "" : "?" + StringUtil.getText(menuList.get(idx).get("menuVar"));
			String parentId = StringUtil.getText(menuList.get(idx).get("parentId"));
			String parentCd = StringUtil.getText(menuList.get(idx).get("prntMenuCd"));
			String menuSeq = StringUtil.getText(menuList.get(idx).get("menuSeq"));
			String helpNm = StringUtil.getText(menuList.get(idx).get("menuHpwrdFileNm"));
			String hlightYn = StringUtil.getText(menuList.get(idx).get("hlightYn"));
			String dutySysCd = StringUtil.getText(menuList.get(idx).get("dutySysCd"));
			String menuUseUnaResn = StringUtil.getText(menuList.get(idx).get("menuUseUnaResn"));

			String menuDepthNext = null;
			if( menuList.size() <= (idx+1) ) {

			}
			else {
				menuDepthNext = StringUtil.getText(menuList.get(idx+1).get("depth"));
			}

			String lastMenu = "N";

			if ( Integer.parseInt(menuDepth) >= 2 && !ObjUtil.isNull(menuDepthNext) ) {
				if ( Integer.parseInt(menuDepth) >= Integer.parseInt(menuDepthNext)) {
					lastMenu = "Y";
				}

			}
			else if ( Integer.parseInt(menuDepth) >= 2 && ObjUtil.isNull(menuDepthNext) ) {
				lastMenu = "Y";
			}


    		for ( int idxSub = 0; idxSub < aryMenuList.length; idxSub++) {
    			if ( StringUtil.getText(menuList.get(idx).get("menuId")).equals(aryMenuList[idxSub]) ) {
        			strJs.append("\""+menuId+"\": { \"menuCD\":\""+menuCd+"\",\"menuNm\":\""+menuNm+"\","+
        			                               "\"menuUrl\":\""+menuUrl+"\",\"menuVar\":\"" +menuVar+"\","+
        			                               "\"parentId\":\""+(menuDepth.equals("2") ? "0" : parentId)+"\",\"parentCd\":\"" +(menuDepth.equals("2") ? "0" : parentCd)+"\","+
        			                               "\"menuSeq\":\""+menuSeq+"\",\"helpNm\":\"" +helpNm+"\","+
        			                               "\"hlightYn\":\""+hlightYn+"\",\"dutySysCd\":\"" +dutySysCd+"\","+
        			                               "\"menuPriv\":\""+aryMenuPriv[idxSub]+"\",\"userPriv\":\"" +aryUserPriv[idxSub]+"\","+
        			                               "\"menuDepth\":\""+menuDepth+"\",\"lastMenu\":\"" +lastMenu + "\",\"menuUseUnaResn\":\"" +menuUseUnaResn + "\"" +
        			             		"}  " );
        			bMakeStr = true;
        			break;
    			}
    		}

    		if ( !bMakeStr ) {
    			strJs.append("\""+menuId+"\": { \"menuCD\":\""+menuCd+"\",\"menuNm\":\""+menuNm+"\","+
                        	 "\"menuUrl\":\""+menuUrl+"\",\"menuVar\":\"" +menuVar+"\","+
                        	 "\"parentId\":\""+(menuDepth.equals("2") ? "0" : parentId)+"\",\"parentCd\":\"" +(menuDepth.equals("2") ? "0" : parentCd)+"\","+
                        	 "\"menuSeq\":\""+menuSeq+"\",\"helpNm\":\"" +helpNm+"\","+
                        	 "\"hlightYn\":\""+hlightYn+"\",\"dutySysCd\":\"" +dutySysCd+"\","+
                        	 "\"menuPriv\":\"\",\"userPriv\":\"\","+
                        	 "\"menuDepth\":\""+menuDepth+"\",\"lastMenu\":\"" +lastMenu + "\",\"menuUseUnaResn\":\"" +menuUseUnaResn + "\"" +
    						"}  " );
    		}

			if ( idx + 1 == menuList.size()) {
				strJs.append(" \n }; \n ");
            }
            else {
            	strJs.append(" , \n ");
            }

    	}
    	return strJs.toString();
    }



    public List<Map<String, Object>> retrieveSession(Map<String, Object> inputData)  {
		return sessionService.retrieveSessionList(inputData);
	}

    /**
	 * <pre>
	 * 즐겨찾기자료
	 * </pre>
	 * @param inputData
	 * @return
	 */
	private List<CoMenuBkmrk> retrieveCoMenuBkmrkList(Map<String, Object> inputData) {
		inputData.put("userId",UserInfo.getUserId());
		String sysCd = Constants.sysCd;
		inputData.put("sysCd", sysCd);
	    return sessionService.retrieveCoMenuBkmrkList(inputData);
	}

}
