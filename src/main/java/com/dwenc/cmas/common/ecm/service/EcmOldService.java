package com.dwenc.cmas.common.ecm.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : EcmOldService
 * 설    명 : ECM 처리를 위한 서비스
 * 작 성 자 : 변형구
 * 작성일자 : 2012-04-04
 * 수정이력 : ECMService로 대체
 *         EcmOldService는 사용하지 않는 Class
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.instance.service.support.InstcFactory;
import com.windfire.apis.asysConnectData;
import com.windfire.apis.asysSnooperEvent;
import com.windfire.apis.asysSnooperListenerIF;
import com.windfire.apis.asys.asysUsrElement;
import com.windfire.apis.asys.asysUsrIndex;
import com.windfire.apis.data.asysDataIndex;
import com.windfire.apis.data.asysDataIndexColl;
import com.windfire.apis.data.asysDataResult;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;

@Service
public class EcmOldService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(EcmService.class);

    private asysConnectData con = null;

    @Autowired
    private InstcFactory instcFactory;

    public void init(String instcId) {
        if(StringUtil.getText(instcId).equals("")) {
            instcId = Constants.instcId;
        }
        Map<String, Object> map = instcFactory.getInstc(instcId);
        String ip = StringUtil.getText(map.get("ecmIp"));
        String port = "2102";       // 고정
        String userId = "SUPER";    // 고정
        String passwd = "SUPER";    // 고정
        String ecmSvcId = StringUtil.getText(map.get("ecmSvcId"));
        con = new asysConnectData(ip, Integer.parseInt(port), ecmSvcId, userId, passwd);
    }

    /**
    */
    public void createSimple(String strFilePath){
        asysUsrElement uePage = new asysUsrElement(con);
        uePage.setInfo("ScanedImage", "NONE", "IMAGE", strFilePath);
        uePage.m_cClassId = "BASIC";
        int ret = uePage.create("XVARM_MAIN");
        // Check for errors - last message always in API object
        if(ret != 0)
            logger.error("Error, createSimple, " + uePage.getLastError());
        else
            logger.debug("Success, createSimple, " + uePage.m_elementId);

    }

    /**
    */
    public String create(String strFilePath){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_localFile = strFilePath;
        uePage1.m_descr = "ScanedImage";
        uePage1.m_cClassId = "BASIC";
        uePage1.m_userSClass = "SUPER";
        uePage1.m_eClassId = "IMAGE";
        // ����� �ε��� ����
        uePage1.addIndexValue("DOC", "f1", "1");
        uePage1.addIndexValue("DOC", "f2", "1");

        String mElementId = "";
        int ret = uePage1.create("XVARM_MAIN");
        // Check for errors - last message always in API object
        if(ret != 0){
            logger.error("Error, create normal, " + uePage1.getLastError());
        }else{
            logger.debug("Success, create normal, " + uePage1.m_elementId);
            mElementId = uePage1.m_elementId;
            mElementId = mElementId.substring(mElementId.indexOf("::") + 2, mElementId.lastIndexOf("::"));
        }
        return mElementId;
    }

    /**
    */
    public void createFilter(String strFilePath){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_localFile = strFilePath;
        uePage1.m_descr = "ScanedImage";
        uePage1.m_cClassId = "BASIC";
        uePage1.m_userSClass = "NONE";
        uePage1.m_eClassId = "IMAGE";
        uePage1.addIndexValue("DOC", "f1", "2");
        uePage1.addIndexValue("DOC", "f2", "2");
        int ret = uePage1.create("XVARM_MAIN", "", "");
        // Check for errors - last message always in API object
        if(ret != 0)
            logger.error("Error, createFilter, " + uePage1.getLastError());
        else
            logger.debug("Success, createFilter, " + uePage1.m_elementId);
    }

    /**
    */
    public void createListener(String strFilePath){
        asysUsrElement uePage = new asysUsrElement(con);
        uePage.setInfo("listener test", "NONE", "IMAGE", strFilePath);
        uePage.m_archive = "MAIN"; //.m_cClassId = "SL_MCC";
        //uePage.setDownloadListener(new MyUploadListener());
        int ret = uePage.create("XVARM_MAIN");
        // Check for errors - last message always in API object
        if(ret != 0)
            logger.error("Error : " + uePage.getLastError());
        else
            logger.debug("Success : " + uePage.m_elementId);

    }

    /**
    */
    public void createWithExtAttrs(String strFilePath){
        asysUsrElement uePage = new asysUsrElement(con);
        uePage.setInfo("An image", "PUBLIC", "IMAGE", strFilePath);
        uePage.m_archive = "MAIN"; //.m_cClassId = "SL_MCC";
        uePage.setExAttribute("NAME", "David.");
        uePage.setExAttribute("PHONE", "011-987-9876");
        int ret = uePage.create("XVARM_MAIN");
        // Check for errors - last message always in API object
        if(ret != 0)
            logger.error("Error : " + uePage.getLastError());
        else
            logger.debug("Success : " + uePage.m_elementId);

    }

    /**
    */
    public void createIndex(String mElementId){
        asysUsrIndex newIdx = new asysUsrIndex(con);
        newIdx.m_indexId = "XVARM_MAIN::DOC::DEF";
        newIdx.addIndexValue("f1", "2");
        newIdx.addIndexValue("f2", "2");
        int ret = newIdx.create("XVARM_MAIN::" + mElementId + "::IMAGE");
        //int ret = newIdx.update("XVARM_MAIN::200710221922550::IMAGE");
        //int ret = newIdx.delete("XVARM_MAIN::200710221922550::IMAGE");
        // Check for errors - last message always in API object
        if(ret != 0)
            logger.error("Error : " + newIdx.getLastError());
        else
            logger.debug("Success");

    }

    /////////////////////////////////////////////////////////////////////////////////

    public void download(String mElementId, String strFilePath){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_elementId = "XVARM_MAIN::" + mElementId + "::IMAGE";
        int ret = uePage1.getContent(strFilePath, "", "");
        if(ret != 0)
            logger.error("Error, download normal, " + uePage1.m_lastError);
        else
            logger.debug("Success, download normal, " + uePage1.m_elementId);
    }

    /**
    */
    public void downloadStream(String mElementId, String strFilePath){
        int rCode = -1;
        asysUsrElement ue = new asysUsrElement(con);
        // String descr, String userSClass, String eClassId, String file
        ue.m_elementId = "XVARM_MAIN::" + mElementId + "::IMAGE";
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        rCode = ue.getContent(fileOut, "", "");
        if(rCode != 0){
            logger.error("Error, downloadStream, " + ue.getLastError());
        }else{
            try{
                byte[] tmp = fileOut.toByteArray();
                FileOutputStream fos = new FileOutputStream(new File(strFilePath));
                fos.write(tmp);
                fos.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            logger.debug("SUCCESS, downloadStream");
        }

        try{
            fileOut.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
    */
    public ByteArrayOutputStream downloadStream(String mElementId){

        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        int rCode = -1;
        asysUsrElement ue = new asysUsrElement(con);
        // String descr, String userSClass, String eClassId, String file
        ue.m_elementId = "XVARM_MAIN::" + mElementId + "::IMAGE";
        rCode = ue.getContent(fileOut, "", "");
        if(rCode != 0){
            logger.error("Error, downloadStream, " + ue.getLastError());
        }else{
            logger.debug("SUCCESS, downloadStream");
            return fileOut;
        }
        return null;
    }

    /**
    */
    public void downloadListener(String mElementId, String strFilePath){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_elementId = "XVARM_MAIN::" + mElementId + "::IMAGE";
        uePage1.setDownloadListener(new MyDownloadListener());
        int ret = uePage1.getContent(strFilePath);
        if(ret != 0)
            logger.error("Error : " + uePage1.m_lastError);
        else
            logger.debug("Success : " + uePage1.m_contentKey);
    }

    /**
    */
    public void getContentKey(String mElementId){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_elementId = "XVARM_MAIN::" + mElementId + "::IMAGE";
        // �ӽ� Rank�� 1�� ��ī�̺긦 �˻�
        int ret = uePage1.getContentKey(1, "10.30.200.70", "10.30.200.70");
        if(ret != 0)
            logger.error("Error, downloadContentKey#1," + uePage1.m_lastError);
        else
            logger.debug("Success, downloadContentKey#1, " + uePage1.m_contentKey);
        // ���
        // ����ID ### ����IP ### ��Ʈ ### ����� ### ������ ### ������ ��ġ ### ���� Ŭ���� ### ���� �Ķ����
        // T_ARC###dabby###2102###VOL4###100638###VOL4/1/200611151835000######

        // �ӽ� Rank�� 2�� ��ī�̺긦 �˻�
        ret = uePage1.getContentKey(2, "10.30.200.70", "10.30.200.70");
        if(ret != 0)
            logger.error("Error, downloadContentKey#2, " + uePage1.m_lastError);
        else
            logger.debug("Success, downloadContentKey#2, " + uePage1.m_contentKey);

        // ���
        // ����ID ### ����IP ### ��Ʈ ### ����� ### ������ ### ������ ��ġ ### ���� Ŭ���� ### ���� �Ķ����
        // MAIN###dabby###2102###LOCALDISK###100640###LOCALDISK/1/200611151835000###filter.DecryptJCE###
    }

    public void discon(){
        // has to be called after all transaction;
        if(con != null){
            con.close();
            con = null;
        }
    }

    class MyDownloadListener implements asysSnooperListenerIF {

        public void snooperChanged(asysSnooperEvent e){

            logger.debug(e.getByteCount() + " / " + e.getTotalSize());
            if(e.getByteCount() == e.getTotalSize()){
                logger.debug("the downloading is completed...");
            }
        }

    }

    /////////////////////////////////////////////////////////////////////////////////

    public void retrieveIndexes(String gateway){
        asysDataIndexColl adic = new asysDataIndexColl(con, false);
        int res = adic.retrieveIndexes(gateway, 1, "USER_*");
        if(res == 0){
            int cnt = adic.getCollCount();
            for(int i = 0; i < cnt; i++){
                asysDataIndex adi = (asysDataIndex)adic.getCollObject(i);
                logger.debug(adi.m_indexId);
                logger.debug(adi.m_indexType);
                logger.debug(adi.m_descr);
            }
        }else{
            logger.error(adic.getLastError());
        }
    }

    public String retrieveIndex(){
        String strRslt = "";
        asysDataIndex idx = new asysDataIndex(con);
        idx.m_engineId = "XVARM_MAIN";
        idx.m_indexId = "DOC";
        idx.addResultField("f1");
        idx.addResultField("f2");
        idx.addResultField("$ELEMENTID$");
        idx.addResultField("$DESCR$");
        idx.addResultField("$SCLASS$");
        idx.addResultField("$ECLASS$");
        idx.addSearchField("DOC", "f1", "=", "1", "and");
        idx.addSearchField("DOC", "f2", "<>", "3", "and");

        idx.addOrderByField("$CREATEDATE$", true);
        idx.addOrderByField("$DESCR$", false);

        int rCode = idx.open(1000, "USER_VIEW", "");
        if(rCode != 0){
            logger.error(idx.getLastError());
            return null;
        }

        asysDataResult sel = idx.getResults();
        int colCount = sel.getColCount();
        while(sel.nextRow()){
            for(int i = 0; i < colCount; i++){
                String lbl = sel.getColLabel(i);
                String val = sel.getColValue(i);
                strRslt += lbl + " : " + val + "\n";
                logger.debug(lbl + " : " + val);
            }
        }
        sel.close();
        return strRslt;
    }

    public String retrieveIndexSubfolder(String mElementId){
        String strRslt = "";
        asysDataIndex idx = new asysDataIndex(con);
        idx.m_engineId = "XVARM_MAIN";
        idx.m_indexId = "DOC";
        idx.addResultField("f1");
        idx.addResultField("f2");
        idx.addResultField("$ELEMENTID$");
        idx.addResultField("$DESCR$");
        idx.addResultField("$SCLASS$");
        idx.addResultField("$ECLASS$");
        idx.addSearchField("DOC", "f1", "=", "LGT_SAMPLE000", "and");

        //idx.addOrderByField("$CREATEDATE$",true);
        idx.addOrderByField("$DESCR$", false);

        //int rCode = idx.open(1000, "USER_VIEW", "order by f2", "200710211447351", false);
        int rCode = idx.open(1000, "USER_VIEW", "", mElementId, false);
        if(rCode != 0){
            logger.debug(idx.getLastError());
            return null;
        }

        asysDataResult sel = idx.getResults();
        int colCount = sel.getColCount();
        while(sel.nextRow()){
            for(int i = 0; i < colCount; i++){
                String lbl = sel.getColLabel(i);
                String val = sel.getColValue(i);
                strRslt += lbl + " : " + val + "\n";
                logger.debug(lbl + " : " + val);
            }
        }
        sel.close();
        return strRslt;
    }

    /////////////////////////////////////////////////////////////////////////////////

    public void delete(String mElementId){
        asysUsrElement uePage1 = new asysUsrElement(con);
        uePage1.m_elementId = "XVARM_MAIN::" + mElementId + "::MAIN";
        int res = uePage1.delete();
        if(res != 0)
            logger.error("Error, delete, " + uePage1.getLastError());
        else
            logger.debug("Success, delete");
    }
}