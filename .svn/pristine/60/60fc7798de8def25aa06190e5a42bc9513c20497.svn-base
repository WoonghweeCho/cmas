package com.dwenc.cmas.common.ecm.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : EcmService
 * 설    명 : ECM 처리를 위한 서비스
 * 작 성 자 : 변형구
 * 작성일자 : 2012-04-04
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-04-04   변형구    최초 작성
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import kr.co.rosis.ecm.service.client.EcmClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.instance.service.support.InstcFactory;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;

@Service
public class EcmService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(EcmService.class);

    private EcmClient con = null;

    @Autowired
    private InstcFactory instcFactory;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    private String strInstcId = "";

    /**
     * 해당 instcId Was에 연결되어 있는 ECM 서버와 Connection을 연결한다.
     *
     * @param instcId
     */
    public void init(String instcId) {
        if(StringUtil.getText(instcId).equals("")) {
            instcId = Constants.instcId;
        }
        strInstcId = instcId;

        Map<String, Object> map = instcFactory.getInstc(instcId);
        String ip = StringUtil.getText(map.get("ecmIp"));
        String port = StringUtil.getText(appProperties.get("dwe.file.ecm.port"));
        String userId = "SUPER";    // 고정
        String passwd = "SUPER";    // 고정

        con = new EcmClient();
        con.connect(ip, Integer.parseInt(port), userId, passwd);
    }

    /**
     * ECM 서버에 연결하여 신규 ECM ID를 생성한다.
     * @param strFilePath
     */
    public void createSimple(String strFilePath){

    	Map<String, Object> map = instcFactory.getInstc(strInstcId);
    	UserDefinition userData = UserInfo.getUserInfo();
    	String ecmSvcId = StringUtil.getText(map.get("ecmSvcId"));

    	String ContentId = con.create(strFilePath, UserInfo.getUserId(), StringUtil.getText(userData.getEtcInfo().get("siteCd")) , ecmSvcId);

    }

    /**
     * ECM 서버에 연결하여 신규 ECM ID를 생성한다.
     * @param strFilePath
     */
    public String create(String strFilePath){

    	Map<String, Object> map = instcFactory.getInstc(strInstcId);
    	UserDefinition userData = UserInfo.getUserInfo();
    	String ecmSvcId = StringUtil.getText(map.get("ecmSvcId"));

    	String ContentId = con.create(strFilePath, UserInfo.getUserId(), StringUtil.getText(userData.getEtcInfo().get("siteCd")) , ecmSvcId);
        return ContentId;
    }

    /**
    */
    public void createFilter(String strFilePath){
    	create( strFilePath);
    }

    /**
    */
    public void createListener(String strFilePath){
    	create( strFilePath);

    }

    /**
    */
    public void createWithExtAttrs(String strFilePath){
    	create( strFilePath );

    }

    /**
    */
    public void createIndex(String mElementId){
    }

    /////////////////////////////////////////////////////////////////////////////////

    public void download(String mElementId, String strFilePath){
        con.download(mElementId, strFilePath);
    }

    /**
     * mElementId 파일을 strFilePath 경로에 Stream으로 다운로드 한다.
     *
     * @param mElementId
     * @param strFilePath
     */
    public void downloadStream(String mElementId, String strFilePath){
    	ByteArrayOutputStream fileOut = new ByteArrayOutputStream();

		try {
			con.download(mElementId, fileOut);

			FileOutputStream fos = null;

			try{

				fos = new FileOutputStream(new File(strFilePath));
				byte[] tmp = fileOut.toByteArray();

				fos.write(tmp);

			} catch(IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if(fos != null) {
					try { fos.close(); } catch (IOException e) {}
				}
			}

		} finally {
			try { fileOut.close(); } catch (IOException e) {}
		}
    }

	/**
	 * mElementId 파일을 Stream 으로 다운로드 한다.
	 * @param mElementId
	 * @return
	 */
    public ByteArrayOutputStream downloadStream(String mElementId){

    	ByteArrayOutputStream fileOut = new ByteArrayOutputStream();

		try {
			con.download(mElementId, fileOut);
			return fileOut;
		} finally {
			try { fileOut.close(); } catch (IOException e) {}
		}
    }

	/**
	 * 해당 mElementId 파일을 strFilePath에 다운로드 한다.
	 *
	 * @param mElementId
	 * @param strFilePath
	 */
    public void downloadListener(String mElementId, String strFilePath){
    	con.download(mElementId, strFilePath);
    }

    public void getContentKey(String mElementId){

    }

    /**
     * ECM 연결을 종료한다.
     */
    public void discon(){
        // has to be called after all transaction;
        if(con != null){
            con.close();
            con = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    public void delete(String mElementId){
    	try {
    		con.delete(UserInfo.getUserId(), mElementId);
    	}
    	catch (Exception ex) {
    		logger.debug("ecm no : "+ mElementId+" can't delete ecm");

    	}
    }

    /***
     * ECM 연결 여부를 확인한다.
     * @return
     */
    public boolean isConnected() {

    	if(con != null)
    		return con.isConnect();
    	else
    		return false;
    }

}