package com.dwenc.cmas.common.priv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.common.Constants;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : PrivService
 * 설    명 : 메뉴 및 권한 관리 위한 service 클래스
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
@Service("PrivSysService")
public class PrivService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(PrivService.class);

    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    /**
     * <pre>
     * User 리스트 조회
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrievePrivList(Map<String,Object> inputData) {
    	inputData.put("sysCd", Constants.sysCd);
        return dao.queryForMapList("priv.retrievePrivList", inputData);
    }

    /**
     * <pre>
     * 권한에 따른 메뉴 리스트 조회
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrievePrivMenuList(Map<String,Object> input) {
    	input.put("sysCd", Constants.sysCd);
    	return dao.queryForMapList("priv.retrievePrivMenuList", input);
    }

    /**
     * <pre>
     * 사용자 리스트를 조회하는 메소드
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrieveUserList(Map<String,Object> inputData) {
        return dao.queryForMapList("priv.retrieveUserList", inputData);
    }

    /**
     * <pre>
     * 사용자 프로그램 리스트 조회
     * </pre>
     * @param inputData
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> retrieveUserMenuList(Map<String,Object> inputData) {
        String privCd = StringUtil.getText(inputData.get("privCd"));
        inputData.put( "seperator", "?" );
        if(!privCd.equals("")) {
            // 현장 권한 여부 확인
            if (!ObjUtil.isNull(inputData.get("privCd"))) {
                inputData.put("privCd", StringUtil.convertArray(inputData.get("privCd").toString(), "+"));
            }
        }
        inputData.put("sysCd", Constants.sysCd);
        return dao.queryForMapList("priv.retrieveUserMenuList", inputData);
    }

    /**
     * <pre>
     * 권한별 메뉴목록
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrieveMenuListByPriv(Map<String,Object> inputData) {
    	inputData.put("sysCd", Constants.sysCd);
    	return dao.queryForMapList("priv.retrieveMenuListByPriv", inputData);
    }

    /**
     * <pre>
     * 권한코드 조회
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrieveMyPrivCd(Map<String,Object> input) {
    	input.put("sysCd", Constants.sysCd);
        return dao.queryForMapList("priv.retrieveMyPrivCd", input);
    }
    /**
     * <pre>
     * 발령사항 조회
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrieveMyAppt(Map<String,Object> input) {
        return dao.queryForMapList("priv.retrieveMyAppt", input);
    }

    /**
     * <pre>
     * 일반 권한 및 현장 권한 모두 추출
     * </pre>
     * @param inputData
     * @return
     */
    public List<Map<String, Object>> retrieveMyAllPrivCd(Map<String,Object> inputData) {
        List<Map<String, Object>> mData2 = new ArrayList<Map<String, Object>>();
        logger.debug("retrieveMyAllPrivCd inputData :" + inputData);
        // 시스템을 분리했기 때문에 자기도메인의 권한과 공통 권한만 조회해야 함

        String dutySysCd = StringUtil.getText(inputData.get("dutySysCd")).toUpperCase();
        if (StringUtil.getText(dutySysCd).equals("")) {
            dutySysCd = "CO" ;
        }

        List<String> dutySysCdList = new ArrayList<String>();

        if(StringUtil.getText(inputData.get("privCdAll")).equals("true")) {
//                dutySysCdList = appConfUtil.getAppSysList();
        } else {
            dutySysCdList.add( dutySysCd );
        }

        logger.debug("dutySysCdList :" + dutySysCdList);
        inputData.put("dutySysCd", dutySysCdList);
        inputData.put("userId", UserInfo.getUserId());
        List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        mData = retrieveMyAppt(inputData);

        List<Map<String, Object>> mData1 = new ArrayList<Map<String, Object>>();
        mData1 = retrieveMyPrivCd(inputData);

        if(inputData.get("dutySysCd") == null) {
            List<String> list = new ArrayList<String>();
            list.add("CO");
            inputData.put("dutySysCd", list);
        }

        // 같은 현장에서 복수의 권한을 갖을 경우 권한의 합으로 처리함
        for(int i=0;i<mData1.size();i++) {      //권한정보 Retrieve
            String siteCd = StringUtil.getText(mData1.get(i).get("siteCd"));
            if(siteCd.equals("")) continue;
            for(int j=0;j<mData1.size();j++) {
                if(j != i
                    && StringUtil.getText(mData1.get(j).get("siteCd")).equals(siteCd)
                    && !StringUtil.getText(mData1.get(i).get("code")).equals("")) {
                        String code = mData1.get(i).get("code").toString() + "+" + mData1.get(j).get("code").toString();
                        mData1.get(i).put("code", code);
                        String value = mData1.get(i).get("value").toString() + "+" + mData1.get(j).get("value").toString();
                        mData1.get(i).put("value", value);
                        mData1.get(j).put("code", "");
                }
            }
        }

        String codeS = "";
//        String valueS = "";
        for(int i=mData1.size() - 1;i>=0;i--) { //중복권한 삭제
            if(mData1.get(i).get("code").toString().equals("")) {
                mData1.remove(i);
            } else {
                if(mData1.get(i).get("siteCd").toString().equals("00000")) {
                    codeS = mData1.get(i).get("code").toString() ;
                    //valueS = mData1.get(i).get("value").toString() ;
                }
            }
        }

        String userTpCd = UserInfo.getUserTpCd();
        if (userTpCd.equals("01")||userTpCd.equals("02")) {	//e-HR 연계 즉 사원이면 *** 추후 02는 제거
	        for(int j=0;j<mData.size();j++) {       //발령정보
	            String orgCd = StringUtil.getText(mData.get(j).get("orgCd"));  //발령 조직코드
	            String code  = codeS;
	            String code1  = codeS;
	            String putCls = "N" ;
	            String value = StringUtil.getText(mData.get(j).get("orgNm"));

	            String siteCd = "";
	            String siteYn = "N" ;	//현장권한유무 체크
	            if (StringUtil.getText(mData.get(j).get("orgChrcCls")).equals("C")
	  	            ||StringUtil.getText(mData.get(j).get("orgChrcCls")).equals("G")
		            ||StringUtil.getText(mData.get(j).get("orgChrcCls")).equals("J")) { //현장 (C국내현장, G지사, J 해외현장))
	            	siteYn = "Y"  ;		//현장권한일 경우
	            }
	            if (orgCd.equals("1DCAH")) {
	            	siteYn = "N"  ;		//정보시스템팀
	            }
	            for(int i=0;i<mData1.size();i++) {  //권한정보 - 조직권한과 같은것 부여
	                siteCd = StringUtil.getText(mData1.get(i).get("siteCd"));
	                if(orgCd.equals(siteCd)) {	//발령과 권한의 현장이 같을 경우
	                    code  =  mData1.get(i).get("code").toString() ;
	                    value =  mData1.get(i).get("siteNm").toString() ;
	                    putCls = "Y" ;              //발령과 맵핑되는 조직이 있을경우를 표시함
	                    break ;
	                } else if(siteCd.equals("00000")) {   //권한이 멀티조회일 경우
	                	//if (siteYn.equals("Y")) {	//현장발령은 되었으나 권한이 없을경우 초기화
	                	//	code1  =  "";
	                	//	value  =  "";
	                	//}else {
	                		code  =  mData1.get(i).get("code").toString() ;
	                		code1  =  mData1.get(i).get("code").toString() ;
	                	//}
	                }
	            }

	            mData.get(j).put("code", code);
                mData.get(j).put("value", value);
                /*
	            if (putCls == "Y") {  //발령과 조직이 맵핑되어진 권한부여
	                mData.get(j).put("code", code);
	                mData.get(j).put("value", value);
	            } else if (siteYn == "Y") {               //현장이면서 매핑되는 권한이 없을경우(권한부여가 안된 발령은 권한이 없어야 한다)
	            	if (mData1.size() == 1) {  //dweadm권한 때문에 임의 셋팅함
	                    mData.get(j).put("code", code1);
	                    mData.get(j).put("value", value);
	            	} else {
		                mData.get(j).put("code", "");
		                mData.get(j).put("value", "");
	            	}
	            } else {                                  //발령과 권한이 맵핑이 안되고 본사권한도 안일경우
	                mData.get(j).put("code", code1);
	                mData.get(j).put("value", value);
	            }
	            */
	        }

	        for(int i=mData.size() - 1;i>=0;i--) {  //현장발령이면서 권한이 없을경우 권한 삭제
	            if(mData.get(i).get("code").toString().equals("")) {
	                mData.remove(i);
	            }
	        }
        } else {
        	mData = mData1 ;
        }

        // 중복 권한 제거
        for(int i=0;i<mData.size();i++) {
            if(mData2.isEmpty()) {
                mData2.add(mData2.size(), mData.get(i));
            }
        }
        return mData;
    }

    /**
     * 권한 존재 여부 확인을 하는 메소드
     * @param input
     * @return Map
     */
    public boolean isPrivMenuExist( Map<String,Object> input )  {
        if ( dao.queryForMap("priv.isPrivMenuExist", input) != null )
            return true;
        return false;
    }
}
