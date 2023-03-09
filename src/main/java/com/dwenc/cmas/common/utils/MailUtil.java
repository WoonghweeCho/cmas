package com.dwenc.cmas.common.utils;

import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.mail.Mail;

import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : MailUtil
 * 설      명 : Mail 발송 처리를 위한 Utility Class
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service("MailUtil")
public class MailUtil {

    @Autowired
    private Mail mail;

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * <pre>
     * 메일을 발송한다.
     * </pre>
     *
     * @param inputData
     * @throws Exception
     */
    public synchronized void sendMail(Map<String, Object> inputData) throws Exception{
        try{
            String spec = StringUtil.getText(inputData.get("spec"));
            if(spec.equals(""))
                spec = "default";
            String bodyTemplate = StringUtil.getText(inputData.get("bodyTemplate"));

            logger.debug("=========== spec:" + spec);
            logger.debug("=========== bodyTemplate:" + bodyTemplate);
            if(!bodyTemplate.equals("")){
                mail.setMail(spec, bodyTemplate);
            }else{
                mail.setMail(spec);
            }
            if(!StringUtil.getText(inputData.get("fromMailId")).equals("")){
                mail.setFromMailAddress(inputData.get("fromMailId").toString(),
                        StringUtil.getText(inputData.get("fromMailName")));
            }

            String mailId = StringUtil.getText(inputData.get("mailId"));
            if(mailId.indexOf(",") > -1){
                mail.setToMailAddress(mailId.split(","));
            }else if(mailId.indexOf(";") > -1){
                mail.setToMailAddress(mailId.split(";"));
            }else if(!mailId.equals("")){
                mail.setToMailAddress(mailId);
            }
            String mailCCId = StringUtil.getText(inputData.get("mailCCId"));
            if(mailCCId.indexOf(",") > -1){
                mail.setCcMailAddress(mailCCId.split(","));
            }else if(mailCCId.indexOf(";") > -1){
                mail.setCcMailAddress(mailCCId.split(";"));
            }else if(!mailCCId.equals("")){
                mail.setCcMailAddress(mailCCId);
            }
            String mailBCCId = StringUtil.getText(inputData.get("mailBCCId"));
            if(mailBCCId.indexOf(",") > -1){
                mail.setBccMailAddress(mailBCCId.split(","));
            }else if(mailBCCId.indexOf(";") > -1){
                mail.setBccMailAddress(mailBCCId.split(";"));
            }else if(!mailBCCId.equals("")){
                mail.setBccMailAddress(mailBCCId);
            }
            mail.setSubject(StringUtil.getText(inputData.get("mailSubject"))); // Mail Title
            String htmlMessage = "";
            if(!bodyTemplate.equals("")){
                htmlMessage = mail.loadHtml(bodyTemplate, (Map<String, Object>)inputData); // bodyTemplate Mail, Body Spec
            }else{
                htmlMessage = StringUtil.getText(inputData.get("htmlBody"));
            }
            // logger.debug( mail.printCurrentConfigInfo());

            // file 첨부 예제 : 절대 경로의 스트링배열로 지정한다.
            //String attachs = StringUtil.getText(inputData.get("attachs"));
            /*if(!attachs.equals("")){
                if(attachs.indexOf(",") == -1){
                    mail.setHtmlAndFile(htmlMessage, attachs);
                }else{
                    mail.setHtmlAndFile(htmlMessage, attachs.split(","));
                }
            }else{
                mail.setHtml(htmlMessage);
            }*/



            if(!ObjUtil.isNull(inputData.get("attachs"))){
                //if(attachs.get("fileList").toString().indexOf(",") == -1){
            		Map <String, Object> attachs = (Map<String,Object>)inputData.get("attachs");
                    mail.setHtmlAndFile(htmlMessage, attachs);
                //}else{
                    //mail.setHtmlAndFile(htmlMessage, attachs.split(","));
                //}
            }else{
                mail.setHtml(htmlMessage);
            }

            mail.printCurrentConfigInfo();
            mail.send();
        }catch(Exception me){
            logger.debug("MailUtil.sendMail()" + "=>" + me.getMessage());
            throw new Exception("co.err.sendmail", me);
        }
    }



    /**
     * <pre>
     * 메일을 발송한다.
     * </pre>
     *
     * @param inputData
     * @throws Exception
     */
    public void sendMail(Map<String, Object> inputData, Map<String, Object> mailHeader) throws Exception{
        try{
            String spec = StringUtil.getText(inputData.get("spec"));
            if(spec.equals(""))
                spec = "default";
            String bodyTemplate = StringUtil.getText(inputData.get("bodyTemplate"));

            logger.debug("=========== spec:" + spec);
            logger.debug("=========== bodyTemplate:" + bodyTemplate);
            if(!bodyTemplate.equals("")){
                mail.setMail(spec, bodyTemplate);
            }else{
                mail.setMail(spec);
            }

            mail.setMailHeader(mailHeader);


            if(!StringUtil.getText(inputData.get("fromMailId")).equals("")){
                mail.setFromMailAddress(inputData.get("fromMailId").toString(),
                        StringUtil.getText(inputData.get("fromMailName")));
            }

            String mailId = StringUtil.getText(inputData.get("mailId"));
            if(mailId.indexOf(",") > -1){
                mail.setToMailAddress(mailId.split(","));
            }else if(mailId.indexOf(";") > -1){
                mail.setToMailAddress(mailId.split(";"));
            }else if(!mailId.equals("")){
                mail.setToMailAddress(mailId);
            }
            String mailCCId = StringUtil.getText(inputData.get("mailCCId"));
            if(mailCCId.indexOf(",") > -1){
                mail.setCcMailAddress(mailCCId.split(","));
            }else if(mailCCId.indexOf(";") > -1){
                mail.setCcMailAddress(mailCCId.split(";"));
            }else if(!mailCCId.equals("")){
                mail.setCcMailAddress(mailCCId);
            }
            String mailBCCId = StringUtil.getText(inputData.get("mailBCCId"));
            if(mailBCCId.indexOf(",") > -1){
                mail.setBccMailAddress(mailBCCId.split(","));
            }else if(mailBCCId.indexOf(";") > -1){
                mail.setBccMailAddress(mailBCCId.split(";"));
            }else if(!mailBCCId.equals("")){
                mail.setBccMailAddress(mailBCCId);
            }
            mail.setSubject(StringUtil.getText(inputData.get("mailSubject"))); // Mail Title
            String htmlMessage = "";
            if(!bodyTemplate.equals("")){
                htmlMessage = mail.loadHtml(bodyTemplate, (Map<String, Object>)inputData); // bodyTemplate Mail, Body Spec
            }else{
                htmlMessage = StringUtil.getText(inputData.get("htmlBody"));
            }
            // logger.debug( mail.printCurrentConfigInfo());

            // file 첨부 예제 : 절대 경로의 스트링배열로 지정한다.
            //String attachs = StringUtil.getText(inputData.get("attachs"));
            /*if(!attachs.equals("")){
                if(attachs.indexOf(",") == -1){
                    mail.setHtmlAndFile(htmlMessage, attachs);
                }else{
                    mail.setHtmlAndFile(htmlMessage, attachs.split(","));
                }
            }else{
                mail.setHtml(htmlMessage);
            }*/



            if(!ObjUtil.isNull(inputData.get("attachs"))){
                //if(attachs.get("fileList").toString().indexOf(",") == -1){
            		Map <String, Object> attachs = (Map<String,Object>)inputData.get("attachs");
                    mail.setHtmlAndFile(htmlMessage, attachs);
                //}else{
                    //mail.setHtmlAndFile(htmlMessage, attachs.split(","));
                //}
            }else{
                mail.setHtml(htmlMessage);
            }

            mail.printCurrentConfigInfo();
            mail.send();
        }catch(Exception me){
            logger.debug("MailUtil.sendMail()" + "=>" + me.getMessage());
            throw new Exception("co.err.sendmail", me);
        }
    }

    public static void main(String[] args){

        String mailPop3Host = "tdms.daewooenc.com";
        String mailStoreType = "pop3";
        String mailUser = "test002";
        String mailPassword = "test002";

        receiveEmail(mailPop3Host, mailStoreType, mailUser, mailPassword);
    }

    /**
     * 메일을 수신한다.
     * @param pop3Host
     * @param storeType
     * @param user
     * @param password
     */
    public static void receiveEmail(String pop3Host, String storeType, String user, String password){
        Properties props = new Properties();

        try{

            // Connect to the POP3 server
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore(storeType);
            store.connect(pop3Host, user, password);

            // Open the folder
            Folder inbox = store.getFolder("INBOX");
            if(inbox == null){
                logger.debug("No INBOX");
                System.exit(1);
            }
            inbox.open(Folder.READ_ONLY);

            // Get the messages from the server
            Message[] messages = inbox.getMessages();
            for(int i = 0; i < messages.length; i++){
                logger.debug("------------ Message " + (i + 1) + " -------------");
                messages[i].writeTo(System.out);
            }

            // Close the connection
            // but don't remove the messages from the server
            inbox.close(false);
            store.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}
