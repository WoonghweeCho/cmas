package com.dwenc.cmas.common.utils;

import java.net.URLEncoder;
import java.sql.Date;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.user.domain.Sign;
import com.dwenc.cmas.common.user.domain.Signln;


import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * @author 1204594
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
@Service
@RequestMapping("/co/common/utils/*")
public class SignProcess {
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	public Map<String, Object> draft(Map<String, Object> map) throws Exception{
		Map<String, Object> rsl = new HashMap<String, Object>();
		rsl = new HttpSend().invokeURL((String)map.get("invokeURL"), map);
		return rsl;
	}

	// 결재콜백에 대해 CMAA_SIGN 테이블에만 업데이트할 경우 사용
    @RequestMapping("SignCallBack.*")
	public void CallBackServlet(MciRequest request, MciResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> data = request.get("json", HashMap.class);
    	Map<String, Object> result = new HashMap<String, Object>();
		try {
			String docStsCd = data.get("docStsCd").toString();
			map.put("signId", data.get("signId").toString());
			map.put("signUserId", data.get("signUserId").toString());
			map.put("signSeq", data.get("signSeq").toString());
			map.put("docNo", data.get("docNo").toString());
			map.put("docSts", docStsCd); 		// 업무의 결재문서상태 업데이트

			if(docStsCd.equals("D02"))			// 결재중
				map.put("signStsCd", "S04");
			else if(docStsCd.equals("D03"))		// 결재완료
				map.put("signStsCd", "S03");
			else if (docStsCd.equals("D04")) 	// 반려
				map.put("signStsCd", "S05");
			else if (docStsCd.equals("D11"))	// 협의요청 (협의요청 콜백은 없음)
				map.put("signStsCd", "S11");

			System.out.println("map : " + map);
			System.out.println("data : " + data);
			commonDao.update("SignUser.updateSign", map);
		} catch (Exception e){
			System.out.println("실패메세지 전송");
			result.put("TYPE", "FAIL");
			result.put("MSG", "CMAS : 결재선정보 업데이트 실패");
			response.setMap("RESULT", result);
			e.printStackTrace();
			throw new BusinessException(null, "test", null);
		}
		System.out.println("성공메세지 전송");
		result.put("TYPE", "SUCCESS");
		result.put("MSG", "CMAS : 성공");
		response.setMap("RESULT", result);
	}
}
