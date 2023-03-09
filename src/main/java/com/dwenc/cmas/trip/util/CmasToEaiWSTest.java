package com.dwenc.cmas.trip.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jcf.data.GridData;
import jcf.integration.webservice.WebServiceExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.query.callback.AbstractRowStatusCallback;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:junit-context.xml"})
public class CmasToEaiWSTest {

    private static Logger logger = LoggerFactory.getLogger(CmasToEaiWSTest.class);

	private WebServiceExecutor executor = new WebServiceExecutor();

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	/**
	 * I/F 번호 : SAP-DS02-006
	 * I/F 명 : 매입거래처 등록여부 확인
	 * @throws Exception
	 */
	@Test
	public void getBdgtNo() throws Exception {
		String functionName = "getBdgtNo";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("picgubun", ""); // 예산구분
		param.put("piexecteam", ""); // 집행팀구분
		param.put("piteamnm", ""); // 팀명
		param.put("piexenm", ""); // 임원명
		param.put("piiokey", ""); // 예산내역
		param.put("piaufnr", ""); // 예산번호

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 logger.debug("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }
	}

	/**
	 * I/F 번호 : SAP-DS02-132
	 * I/F 명 : 외화 세무 검토코드 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getBdgtNoList(String Picgubun, String Piexecteam, String Piteamnm, String Piexenm, String Piiokey, String Piaufnr) throws Exception {
		String functionName = "getBdgtNo";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Picgubun", Picgubun); // 예산구분
		param.put("Piexecteam", Piexecteam); // 집행팀구분
		param.put("Piteamnm", Piteamnm); // 팀명
		param.put("Piexenm", Piexenm); // 임원명
		param.put("Piiokey", Piiokey); // 예산내역
		param.put("Piaufnr", Piaufnr); // 예산번호

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("");
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;
	}

	/**
	 * I/F 번호 : SAP-DS02-132
	 * I/F 명 : 외화 세무 검토코드 조회
	 * @throws Exception
	 */
	@Test
	public void getSendBudgetDoc(String Gubun, String Measure) throws Exception {
		String functionName = "getSendBudgetDoc";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Gubun", Gubun); // 예산구분
		param.put("Measure", Measure); // 집행팀구분

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		// resultMap 내부의 조회 결과 Object
		Object obj = resultMap.get("SapTData");

		// 단일 행이 Return되는 경우는 HashMap Type이며, 복수의 행이 Return되는 경우는 List Type 임
		if(obj instanceof HashMap){
			Map<String, Object> resMap = (Map<String, Object>) obj;
			set = resMap.keySet();
			iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				System.out.println("iterator==" + key);
				 System.out.println("RFC Name=="+functionName);
				System.out.println("iteratorVal==" + resMap.get(key));

//				logger.debug("iterator==" + key);
//				logger.debug("iteratorVal==" + resMap.get(key));
			}
		}else if(obj instanceof List){
			List<Map<String, Object>> resultList = (List<Map<String, Object>>) obj;
			if(resultList != null){
				for (Map<String, Object> map : resultList) {
					set = map.keySet();
					iterator = set.iterator();
					while (iterator.hasNext()) {
						String key = iterator.next();
						System.out.println("iterator==" + key);
						 System.out.println("RFC Name=="+functionName);
						System.out.println("iteratorVal==" + map.get(key));

//						logger.debug("iterator==" + key);
//						logger.debug("iteratorVal==" + map.get(key));
					}
				}
			}
		}else{
			System.out.println("Return SapTData is null");
//			logger.debug("Return SapTData is null");
		}
	}



	/**
	 * I/F 번호 : ZN_RFC_GET_PRCTR_CODE
	 * I/F 명 : 외화 세무 검토코드 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getPrctrList(String Bukrs, String Prctrnm) throws Exception {
		String functionName = "getPrctrCode";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Bukrs", Bukrs); // 예산구분
		param.put("Prctrnm", Prctrnm); // 집행팀구분


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_SEND_BIZ_TRIP_APPLICATION
	 * I/F 명 : 국내출장상신
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getSendBizTripApplication(Map<String, Object> map) throws Exception {
		String functionName = "getSendBizTripApplication";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		Set<String> setP = param.keySet();
		 Iterator<String> iteratorP = setP.iterator();
		 while (iteratorP.hasNext()) {
			 String key = iteratorP.next();
			 System.out.println("param Map==" + key);
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + param.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		// param init
		param.put("IBdgtType", (String)map.get("iBdgtType")); // 경비구분 P사업, I일반, C특정
		param.put("ITripType", (String)map.get("iTripType")); // 출장구분 국출 D
		param.put("IClearType", (String)map.get("iClearType")); // 정산구분(default : *)
		param.put("IDocType", (String)map.get("iDocType")); // 전표구분 H본사, G지역
		param.put("INotesdoc", (String)map.get("iNotesdoc")); // 바로넷 문서번호 생략가능
		param.put("IBdgtNo", (String)map.get("iBdgtNo")); // 예산번호 (특정경비일 경우 C)
		param.put("IBdgtTeam", (String)map.get("iBdgtTeam")); // 집행팀
		param.put("ISenderId", (String)map.get("iSenderId")); // 작성자사번

		param.put("ISendDate", (String)map.get("iSendDate")); // 증빙일

		param.put("ITravId", (String)map.get("iTravId")); // 출장자사번
		param.put("ITravTeam", (String)map.get("iTravTeam")); // 출장자팀

		param.put("ITravFdate", (String)map.get("iTravFdate")); // 출장사적예정일
		param.put("ITravTdate", (String)map.get("iTravTdate")); // 출장종료예정일

		param.put("ITravPurp", (String)map.get("iTravPurp")); // 주석-출장목적
		param.put("ITravArea", (String)map.get("iTravArea")); // 주석-출장지역
		param.put("ICompCar", (String)map.get("iCompCar")); // 회사차량여부

		param.put("IPaymDate", (String)map.get("iPaymDate")); // 생성일 - 송금예정일

		param.put("ITran1Amt", (String)map.get("iTran1Amt")); // 금액(현지통화) - 항공 합계
		param.put("ITran2Amt", (String)map.get("iTran2Amt")); // 금액(현지통화) - 기차/선박/고속버스/고속철도 합계
		param.put("ITran3Amt", (String)map.get("iTran3Amt")); // 금액(현지통화) - 대중교통 합계 => 0 (현재 사용안함)
		param.put("ITran4Amt", (String)map.get("iTran4Amt")); // 금액(식비)
		param.put("ITran5Amt", (String)map.get("iTran5Amt")); // 금액(잡비)

		param.put("IGitaAmt", (String)map.get("iGitaAmt")); // 기타경비 - 대중교통 합계 => 0 (현재 사용안함)
		param.put("INoDaym", (String)map.get("iNoDaym")); // 숙박비제외 (임원 실비정산 필수)
		param.put("INoWage", (String)map.get("iNoWage")); // 일당비제외
		param.put("IRutLifnr", (String)map.get("iRutLifnr")); // 임원대체수취 거래처
		param.put("IRutValue", (String)map.get("iRutValue")); // 임원대체수취 거래처 지급액
		param.put("IPosid", (String)map.get("iPosid")); // 기간내 관련요일
		param.put("IBukrs", (String)map.get("iBukrs")); // 회사코드
		param.put("IEduchk", (String)map.get("iEduchk")); // 교육 출장 여부
		param.put("IEduAmt", (String)map.get("iEduAmt")); // 교육출장시 일당비

		param.put("IAccomoDcnt", (String)map.get("iAccomoDcnt")); // 숙박시설 일수
		param.put("ITravRem", (String)map.get("iTravRem")); // 주석-출장비고

System.out.println("-------------------getSendBizTripApplication (strat)--------------------------");
System.out.println("IBdgtType==" + (String)map.get("IBdgtType"));
System.out.println("ITripType==" + (String)map.get("ITripType"));
System.out.println("IClearType==" + (String)map.get("IClearType"));
System.out.println("IDocType==" + (String)map.get("IDocType"));
System.out.println("INotesdoc==" + (String)map.get("INotesdoc"));
System.out.println("IBdgtNo==" + (String)map.get("IBdgtNo"));
System.out.println("IBdgtTeam==" + (String)map.get("IBdgtTeam"));
System.out.println("ISenderId==" + (String)map.get("ISenderId"));
System.out.println("ISendDate==" + (String)map.get("ISendDate"));
System.out.println("ITravId==" + (String)map.get("ITravId"));
System.out.println("ITravTeam==" + (String)map.get("ITravTeam"));
System.out.println("ITravFdate==" + (String)map.get("ITravFdate"));
System.out.println("ITravTdate==" + (String)map.get("ITravTdate"));
System.out.println("ITravPurp==" + (String)map.get("ITravPurp"));
System.out.println("ITravArea==" + (String)map.get("ITravArea"));
System.out.println("ICompCar==" + (String)map.get("ICompCar"));
System.out.println("IPaymDate==" + (String)map.get("IPaymDate"));
System.out.println("ITran1Amt==" + (String)map.get("ITran1Amt"));
System.out.println("ITran2Amt==" + (String)map.get("ITran2Amt"));
System.out.println("ITran3Amt==" + (String)map.get("ITran3Amt"));
System.out.println("ITran4Amt==" + (String)map.get("ITran4Amt"));
System.out.println("IGitaAmt==" + (String)map.get("IGitaAmt"));
System.out.println("INoDaym==" + (String)map.get("INoDaym"));
System.out.println("INoWage==" + (String)map.get("INoWage"));
System.out.println("IRutLifnr==" + (String)map.get("IRutLifnr"));
System.out.println("IRutValue==" + (String)map.get("IRutValue"));
System.out.println("IPosid==" + (String)map.get("IPosid"));
System.out.println("IBukrs==" + (String)map.get("IBukrs"));
System.out.println("IEduchk==" + (String)map.get("IEduchk"));
System.out.println("IEduAmt==" + (String)map.get("IEduAmt"));
System.out.println("-------------------getSendBizTripApplication (end)--------------------------");

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}


	/**
	 * I/F 명 : 국출 해출 REFNO 로 내용 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getTripExpenseSearch(Map<String, Object> map) throws Exception {
		String functionName = "getTripExpenseSearch";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Gubun", (String)map.get("Gubun")); // 예산구분
		param.put("Refkey", (String)map.get("Measure")); // 예산구분


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_SEND_TRIP_EXPENSE_STANDARD
	 * I/F 명 : 직급별 출장기준액 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getSendTripExpenseStandard(Map<String, Object> map) throws Exception {
		String functionName = "getSendTripExpenseStandard";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Sabun", (String)map.get("Sabun")); // 예산구분


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_SEND_BIZ_TRIP_APPLICATION
	 * I/F 명 : 국내출장상신
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getSendTrafficCost(Map<String, Object> map, GridData<HashMap> gridData) throws Exception {
		String functionName = "getSendTrafficCost";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		// param init
		param.put("IBukrs", (String)map.get("IBukrs"));


		final List<Map<String, Object>> sapDocTab = new ArrayList<Map<String, Object>>();

		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {

				Map<String, Object> sapDocTabParam = new HashMap<String, Object>();

				sapDocTabParam.put("Bukrs", (String)record.get("Bukrs"));
				sapDocTabParam.put("Refkey", (String)record.get("Refkey"));
				sapDocTabParam.put("Bdocno", (String)record.get("Bdocno"));
				sapDocTabParam.put("Seqno", (Integer)record.get("Seqno"));


				sapDocTabParam.put("Kostl1", (String)record.get("Kostl1"));
				sapDocTabParam.put("Kostxt", (String)record.get("Kostxt"));
				sapDocTabParam.put("Sabun1", (String)record.get("Sabun1"));
				sapDocTabParam.put("Date1", (String)record.get("Date1"));
				sapDocTabParam.put("Sabun2", (String)record.get("Sabun2"));
				sapDocTabParam.put("Lfatxt", (String)record.get("Lfatxt"));
				sapDocTabParam.put("Kostl2", (String)record.get("Kostl2"));
				sapDocTabParam.put("Bankno", (String)record.get("Bankno"));
				sapDocTabParam.put("Paydate", (String)record.get("Paydate"));
				sapDocTabParam.put("Waers", (String)record.get("Waers"));
				sapDocTabParam.put("Amount", (String)record.get("Amount"));

				sapDocTabParam.put("Area", (String)record.get("Area"));
				sapDocTabParam.put("Descript", (String)record.get("Descript"));

				sapDocTabParam.put("Chkflag", (String)record.get("Chkflag"));
				sapDocTabParam.put("Sdate", (String)record.get("Sdate"));
				sapDocTabParam.put("Sdflag", (String)record.get("Sdflag"));
				sapDocTabParam.put("BdgtType", (String)record.get("BdgtType"));
				sapDocTabParam.put("DocType", (String)record.get("DocType"));
				sapDocTabParam.put("Aufnr", (String)record.get("Aufnr"));
				sapDocTabParam.put("Posid", (String)record.get("Posid"));
				sapDocTabParam.put("Kblnr", (String)record.get("Kblnr"));
				sapDocTabParam.put("Kblpos", (String)record.get("Kblpos"));

				sapDocTabParam.put("Usedt", (String)record.get("Usedt"));
				sapDocTabParam.put("Rfund", (String)record.get("Rfund"));


				// 출장자를 ADD 한다. 개개인 데이터
				sapDocTab.add(sapDocTabParam);

			}

			@Override
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {

			}

			@Override
			public void delete(HashMap record, int index) {

			}
		});


		System.out.println("Array List size : " + sapDocTab.size());
		for(int i = 0; i < sapDocTab.size(); i ++){
			Map<String, Object> tempData = sapDocTab.get(i);
			System.out.println("Array index : " + i);
			System.out.println("Sabun1 : " + (String)tempData.get("Sabun1"));
			System.out.println("Sabun2 : " + (String)tempData.get("Sabun2"));
			System.out.println("Seqno : " + (Integer)tempData.get("Seqno"));

		}


		param.put("SapDoctab", sapDocTab);

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}


	/**
	 * I/F 번호 : ZN_RFC_TRAFFIC_EXPENSE_SEARCH
	 * I/F 명 : 시내교통비 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getTrafficExpenseSearch(Map<String, Object> map) throws Exception {
		String functionName = "getTrafficExpenseSearch";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Refkey", (String)map.get("Refkey")); // 예산구분


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_RFC_SEND_EXRATE
	 * I/F 명 : 환율 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getSendExrate(Map<String, Object> map) throws Exception {
		String functionName = "getSendExrate";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Ymd", (String)map.get("Ymd")); // 환율기준 일자

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_CONFIRM_TRAFFIC_COST
	 * I/F 명 : 시내교통비 회계승인
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> confirmTrafficCost(Map<String, Object> map) throws Exception {
		String functionName = "confirmTrafficCost";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

//		신청번호
//		CMAS 문서번호
//		결재모드 ( Y: 결재, N: 반려 )
//		결재자사번
//		회사코드 (무조건 1000)

		param.put("IRefkey", (String)map.get("IRefkey"));
		param.put("INotesdoc", (String)map.get("INotesdoc"));
		param.put("IMode", (String)map.get("IMode"));
		param.put("ISenderId", (String)map.get("ISenderId"));
		param.put("IBukrs", (String)map.get("IBukrs"));


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_CONFIRM_BIZ_TRIP
	 * I/F 명 : 국내출장 회계승인
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> confirmBizTrip(Map<String, Object> map) throws Exception {
		String functionName = "confirmBizTrip";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

//		I_REFKEY	CHAR	 15 	신청번호
//		I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//		I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//		I_SENDER_ID	CHAR	7	결재자사번


		param.put("IRefkey", (String)map.get("IRefkey"));
		param.put("INotesdoc", (String)map.get("INotesdoc"));
		param.put("IConfirm", (String)map.get("IConfirm"));
		param.put("ISenderId", (String)map.get("ISenderId"));

		System.out.println((String)map.get("IRefkey"));
		System.out.println((String)map.get("INotesdoc"));
		System.out.println((String)map.get("IConfirm"));
		System.out.println((String)map.get("ISenderId"));


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_CONFIRM_OS_BIZ_TRIP
	 * I/F 명 : 해외출장 회계승인
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> confirmOsBizTrip(Map<String, Object> map) throws Exception {
		String functionName = "confirmOsBizTrip";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

//		I_REFKEY	CHAR	 15 	신청번호
//		I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//		I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//		I_SENDER_ID	CHAR	7	결재자사번
//		I_BUKRS	CHAR	4	회사코드 (무조건 1000)

		param.put("IRefkey", (String)map.get("IRefkey"));
		param.put("INotesdoc", (String)map.get("INotesdoc"));
		param.put("IConfirm", (String)map.get("IConfirm"));
		param.put("ISenderId", (String)map.get("ISenderId"));
		param.put("IBukrs", (String)map.get("IBukrs"));


		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}


	/**
	 * I/F 번호 : ZN_UPDATE_AIR_FARE_N
	 * I/F 명 : 항공료 입력완료
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> updateAirFareN(Map<String, Object> map) throws Exception {
		String functionName = "updateAirFareN";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("IMode", (String)map.get("IMode"));
		param.put("IBdocno", (String)map.get("IBdocno"));
		param.put("IRefkey", (String)map.get("IRefkey"));
		param.put("IEmpnm", (String)map.get("IEmpnm"));
		param.put("ISerno", (String)map.get("ISerno"));
		param.put("ITicno", (String)map.get("ITicno"));
		param.put("ITicdt", (String)map.get("ITicdt"));
		param.put("IAirfare", (String)map.get("IAirfare"));
		param.put("IInsure", (String)map.get("IInsure"));
		param.put("IAirptFee1", (String)map.get("IAirptFee1"));
		param.put("IAirptFee2", (String)map.get("IAirptFee2"));
		param.put("IDcamt", (String)map.get("IDcamt"));
		param.put("IRoute", (String)map.get("IRoute"));
		param.put("IPayMethod", (String)map.get("IPayMethod"));
		param.put("ICancelDate", (String)map.get("ICancelDate"));
		param.put("IUsnam", (String)map.get("IUsnam"));
		param.put("ITicketFee", (String)map.get("ITicketFee"));
		param.put("ICash", (String)map.get("ICash"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : Z_AIRFARE_INVOICE_DAY
	 * I/F 명 : 항공료 입력 날짜별 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getAirfareInvoiceDay(Map<String, Object> map) throws Exception {
		String functionName = "getAirfareInvoiceDay";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("Refkey", (String)map.get("Refkey"));
		param.put("Fdate", (String)map.get("Fdate"));
		param.put("Tdate", (String)map.get("Tdate"));
		param.put("Notesdoc", (String)map.get("Notesdoc"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : Z_AIRFARE_INVOICE_LIST
	 * I/F 명 : 전표 리스트 조회
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getAirfareInvoiceList(Map<String, Object> map) throws Exception {
		String functionName = "getAirfareInvoiceList";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

//		IGjahr
//		IMonat
//		IUpnam

		param.put("IGjahr", (String)map.get("IGjahr"));
		param.put("IMonat", (String)map.get("IMonat"));
		param.put("IUpnam", (String)map.get("IUpnam"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : Z_AIRFARE_INVOICE
	 * I/F 명 : 항공료 입력
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> getAirfareInvoice(Map<String, Object> map) throws Exception {
		String functionName = "getAirfareInvoice";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("IGjahr", (String)map.get("IGjahr"));
		param.put("IMonat", (String)map.get("IMonat"));
		param.put("IBelnr", (String)map.get("IBelnr"));
		param.put("IUpnam", (String)map.get("IUpnam"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_CHECK_OS_BIZ_TRIP_N
	 * I/F 명 : 해외 출장 예산 체크
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> checkOsBizTrip(Map<String, Object> map) throws Exception {
		String functionName = "checkOsBizTrip";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("IBdgtType", (String)map.get("IBdgtType"));
		param.put("IBdgtNo", (String)map.get("IBdgtNo"));
		param.put("IBdgtTeam", (String)map.get("IBdgtTeam"));
		param.put("ISendDate", (String)map.get("ISendDateC"));
		param.put("AirAmt", (String)map.get("AirAmt"));
		param.put("StayAmt", (String)map.get("StayAmt"));
		param.put("EtcLairAmt", (String)map.get("EtcLairAmt"));
		param.put("EtcLtrAmt", (String)map.get("EtcLtrAmt"));
		param.put("EtcVisaAmt", (String)map.get("EtcVisaAmt"));
		param.put("EtcOverAmt", (String)map.get("EtcOverAmt"));
		param.put("EtcWelAmt", (String)map.get("EtcWelAmt"));
		param.put("EtcRcAmt", (String)map.get("EtcRcAmt"));
		param.put("IRefkey", (String)map.get("IRefkey"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}


	/**
	 * I/F 번호 : ZN_SUBMIT_OS_BIZ_TRIP_N
	 * I/F 명 : 해외 출장 예산 체크
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> submitOsBizTrip(Map<String, Object> map) throws Exception {
		String functionName = "submitOsBizTrip";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("IBdgtType", (String)map.get("IBdgtType"));
		param.put("IDocType", (String)map.get("IDocType"));
		param.put("INotesdoc", (String)map.get("INotesdoc"));
		param.put("IBdgtNo", (String)map.get("IBdgtNo"));
		param.put("IBdgtTeam", (String)map.get("IBdgtTeam"));
		param.put("IPostTeam", (String)map.get("IPostTeam"));
		param.put("ISenderId", (String)map.get("ISenderId"));
		param.put("ISendDate", (String)map.get("ISendDate"));
		param.put("ITravId", (String)map.get("ITravId"));
		param.put("ITravTeam", (String)map.get("ITravTeam"));
		param.put("ITravFdate", (String)map.get("ITravFdate"));
		param.put("ITravTdate", (String)map.get("ITravTdate"));
		param.put("ITravArea", (String)map.get("ITravArea"));
		param.put("ITravPurp", (String)map.get("ITravPurp"));
		param.put("IPaymDate", (String)map.get("IPaymDate"));
		param.put("IEmpNm", (String)map.get("IEmpNm"));
		param.put("ISeatClass", (String)map.get("ISeatClass"));
		param.put("IAirFare", (String)map.get("IAirFare"));
		param.put("IDayAmt", (String)map.get("IDayAmt"));
		param.put("IGitaAmt", (String)map.get("IGitaAmt"));
		param.put("IGitaAmt2", (String)map.get("IGitaAmt2"));
		param.put("IGitaAmt3", (String)map.get("IGitaAmt3"));
		param.put("IGitaSettle", (String)map.get("IGitaSettle"));
		param.put("IGuestNm1", (String)map.get("IGuestNm1"));
		param.put("ISeatClass1", (String)map.get("ISeatClass1"));
		param.put("IAirFare1", (String)map.get("IAirFare1"));
		param.put("IGuestNm2", (String)map.get("IGuestNm2"));
		param.put("ISeatClass2", (String)map.get("ISeatClass2"));
		param.put("IAirFare2", (String)map.get("IAirFare2"));
		param.put("IGuestNm3", (String)map.get("IGuestNm3"));
		param.put("ISeatClass3", (String)map.get("ISeatClass3"));
		param.put("IAirFare3", (String)map.get("IAirFare3"));
		param.put("IRutLifnr", (String)map.get("IRutLifnr"));
		param.put("IRutValue", (String)map.get("IRutValue"));
		param.put("IPosid", (String)map.get("IPosid"));
		param.put("IBukrs", (String)map.get("IBukrs"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}

	/**
	 * I/F 번호 : ZN_SETTLE_OS_BIZ_TRIP_N
	 * I/F 명 : 해외 출장 정산 전표 생성
	 * @throws Exception
	 */
	@Test
	public Map<String, Object> settleOsBizTrip(Map<String, Object> map) throws Exception {
		String functionName = "settleOsBizTrip";

		String wsdl = StringUtil.getText(appProperties.get("dwe.eai.url")) + StringUtil.getText(appProperties.get("dwe.eai.etctosap.wsdl"));
		executor.setPrefix(StringUtil.getText(appProperties.get("dwe.eai.etctosap.prefix")));
		executor.setNamespaceURI(StringUtil.getText(appProperties.get("dwe.eai.etctosap.namespace")));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("IBdgtType", (String)map.get("IBdgtType"));
		param.put("IClearType", (String)map.get("IClearType"));
		param.put("IDocType", (String)map.get("IDocType"));
		param.put("INotesdoc", (String)map.get("INotesdoc"));
		param.put("IRefkey", (String)map.get("IRefkey"));
		param.put("IGitaRefkey", (String)map.get("IGitaRefkey"));
		param.put("IBdgtNo", (String)map.get("IBdgtNo"));
		param.put("IBdgtTeam", (String)map.get("IBdgtTeam"));
		param.put("IPostTeam", (String)map.get("IPostTeam"));
		param.put("ISenderId", (String)map.get("ISenderId"));
		param.put("ISendDate", (String)map.get("ISendDate"));
		param.put("ITravId", (String)map.get("ITravId"));
		param.put("ITravTeam", (String)map.get("ITravTeam"));
		param.put("ITravFdate", (String)map.get("ITravFdate"));
		param.put("ITravTdate", (String)map.get("ITravTdate"));
		param.put("ITravArea", (String)map.get("ITravArea"));
		param.put("ITravPurp", (String)map.get("ITravPurp"));
		param.put("IPaymDate", (String)map.get("IPaymDate"));
		param.put("IRetUsnam", (String)map.get("IRetUsnam"));
		param.put("IRetDatum", (String)map.get("IRetDatum"));
		param.put("IRetMethod", (String)map.get("IRetMethod"));
		param.put("IRetBank", (String)map.get("IRetBank"));
		param.put("IAirFare", (String)map.get("IAirFare"));
		param.put("IDayAmt", (String)map.get("IDayAmt"));
		param.put("IPosid", (String)map.get("IPosid"));
		param.put("IBukrs", (String)map.get("IBukrs"));
		param.put("IGitaAmt", (String)map.get("IGitaAmt"));
		param.put("IGitaAmt2", (String)map.get("IGitaAmt2"));
		param.put("IGitaAmt3", (String)map.get("IGitaAmt3"));

		Map<String, Object> resultMap = executor.execute(wsdl, functionName, param);

		 Set<String> set = resultMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("iterator==" + key);
			 System.out.println("RFC Name=="+functionName);
			 System.out.println("iteratorVal==" + resultMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }

		 return resultMap;

	}




}
