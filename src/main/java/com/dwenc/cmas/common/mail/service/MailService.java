package com.dwenc.cmas.common.mail.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : MailService
 * 설    명 : 메일을 처리하는 서비스
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.common.MapUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;

@Service("MailService")
public class MailService {

	@Autowired
	private FileService service;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 단건 메일 발송
     */
	public void sendMail(Map<String, Object> data) throws Exception {
		try {
			data =  MapUtil.setNullString(data) ;
			if ( !ObjUtil.isNull(data.get("fileAtchId")) && ObjUtil.isNull(data.get("attachs")) ) {
				//data.put("attachs", service.retrieveEmailFileList(data));
			} else if ( !ObjUtil.isNull(data.get("attachs")) ) {
				Map<String, Object> attachs = new HashMap<String, Object>();
				String[] attachList = StringUtil.split(data.get("attachs").toString(), '|');
				attachs.put("fileList", attachList[0]);
				attachs.put("ecmList" , attachList[1]);
				data.put("attachs", attachs);
			}
			Thread.sleep(1);
			mailUtil.sendMail(data);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

    /**
     * 여러건 메일 발송
     */
	public void sendMail(List<Map<String, Object>> mData) throws Exception {
		for (int i = 0; i < mData.size(); i++) {
			Map inputData = mData.get(i);
			inputData = MapUtil.setNullString(inputData);
			sendMail(inputData);
		}
	}

	/**
     * 여러건 메일 발송
     */
	public void sendMail(GridData<HashMap> mData) throws Exception {
		for (int i = 0; i < mData.size(); i++) {
			Map inputData = mData.get(i);
			sendMail(inputData);
		}
	}

    /**
     * 단건 메일 발송 테스트
     */
    public void sendMail2(Map<String, Object> data) throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("spec", "default");
        input.put("bodyTemplate", "sysMng"); // 사용할 메일 템플릿
        input.put("mailSubject", data.get("mailSubject")); // Mail Title
        input.put("mailId", data.get("mailId"));
        input.put("fromMailId", data.get("fromMailId"));

        // 템플릿에서 치환해야 할 변수들
        input.put("reportType", "aaa"); // ${reportType}
        input.put("createDate", "bbb"); // ${createDate}
        mailUtil.sendMail(input);
    }

    /**
     * 여러건 GridData 형태 처리 테스트
     */
    public void sendMail2(GridData<HashMap> mData) throws Exception {
        for (int i = 0; i < mData.size(); i++) {
            Map inputData = mData.get(i);
            sendMail2(inputData);
        }
    }
}
