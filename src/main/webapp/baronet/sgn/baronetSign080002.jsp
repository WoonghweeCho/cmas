<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>

<!-- HTTP Request POST Data 처리 -->
<script type="text/javascript">
	var gContextPath = "${contextPath}";
	var datas = "${param.datas}";

	$(document).ready(function() {
		getData();
	});

	//http://dwdev.dwconst.co.kr/gw/app/bult/hkm/hbi.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/bult/hkm/hbi.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
		    	}];
		$.ajax({
			type: "post",
		    url: "<c:url value="/baronet/sgn/baronetDocViewer.baro" />",
		    data : JSON.stringify(parameter),
		    datatype: "json",
		    contentType: "application/json+sua; charset=utf-8",
		    success: function(data){
		    	//서버에서 data.result[0].* 로 넘어온다.(JSONArray 형태로 넘어옴.)
		    	if(data.result != undefined) {
		    		var result = data.result[0];

		    		if(result != undefined && result.result == "dataexist" ) {
		    			$("#c_AcceptDate").text(result.c_AcceptDate);
		    			$("#c_AcceptName").text(result.c_AcceptName);
		    			$("#c_Add").text(result.c_Add);
		    			$("#c_InforName").text(result.c_InforName);
		    			$("#c_DamdangName").text(result.c_DamdangName);
		    			$("#c_InforName_1").text(result.c_InforName_1);
		    			$("#c_BizGubun").text(result.c_BizGubun);
		    			$("#c_BizGubunType").text(result.c_BizGubunType);
		    			$("#c_Operation").text(result.c_Operation);
		    			$("#c_CityPlan").text(result.c_CityPlan);
		    			$("#c_Pyong").text(result.c_Pyong);
		    			$("#c_Pyong1").text(result.c_Pyong1);
		    			$("#c_Rate").text(result.c_Rate);
		    			$("#c_Rate1").text(result.c_Rate1);
		    			$("#c_Size").text(result.c_Size);
		    			$("#c_HouseCnt").text(result.c_HouseCnt);
		    			$("#c_BizDrive").text(result.c_BizDrive);
		    			$("#c_InforOpinion").text(result.c_InforOpinion);
		    			$("#c_Data").text(result.c_Data);
		    			$("#c_ViewCopyTo").text(result.c_ViewCopyTo);
		    			$("#c_Research").text(result.c_Research);
		    			$("#c_Report").text(result.c_Report);
		    			$("#c_Support").text(result.c_Support);
		    			$("#c_Price").text(result.c_Price);
		    			$("#c_Result_1").text(result.c_Result_1);
		    			$("#c_Result").text(result.c_Result);
		    			$("#c_Reply").text(result.c_ReplyDate + " " + result.c_Reply);

		    			//결재 URL 세팅
		    			$("#signURL").attr("data-url", result.signurl);
		    			$("#rejectURL").attr("data-url", result.rejecturl);
		    		}
		    		else {
		    			fn_alert(result.result);
		    		}
		    	}
		    	else {
		    		fn_alert("데이터가 없습니다.");
		    	}
		    }
		});
	};
</script>

	<!--
		Class Name 	: baronetDocViewer.jsp
		Description : 결재문서열람 - 수주정보관리(주택)
	 -->
	<p class="pageTit">수주정보관리(주택)</p>

	<table class="tb">
		<thead>
			<tr>
				<th colspan="2">기본 정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>접수일</th>
				<td id="c_AcceptDate"></td>
			</tr>
			<tr>
				<th>접수자</th>
				<td id="c_AcceptName"></td>
			</tr>
			<tr>
				<th>부지위치</th>
				<td id="c_Add"></td>
			</tr>
			<tr>
				<th rowspan="2">정보제공자</th>
				<td id="c_InforName"></td>
			</tr>
			<tr>
				<th rowspan="2">담당자</th>
				<td rowspan="2" id="c_DamdangName"></td>
			</tr>
			<tr>
				<td id="c_InforName_1"></td>
			</tr>
			<tr>
				<th>사업구분</th>
				<td id="c_BizGubun"></td>
			</tr>
			<tr>
				<th>사업유형</th>
				<td id="c_BizGubunType"></td>
			</tr>
			<tr>
				<th>시행사</th>
				<td id="c_Operation"></td>
			</tr>
			<tr>
				<th>도시계획</th>
				<td id="c_CityPlan"></td>
			</tr>
			<tr>
				<th>대지면적</th>
				<td id="c_Pyong"></td>
			</tr>
			<tr>
				<th>연면적</th>
				<td id="c_Pyong1"></td>
			</tr>
			<tr>
				<th>용적율</th>
				<td id="c_Rate"></td>
			</tr>
			<tr>
				<th>건폐율</th>
				<td id="c_Rate1"></td>
			</tr>
			<tr>
				<th>사업규모</th>
				<td id="c_Size"></td>
			</tr>
			<tr>
				<th>세대수</th>
				<td id="c_HouseCnt"></td>
			</tr>
			<tr>
				<th>사업추진사항</th>
				<td id="c_BizDrive"></td>
			</tr>
			<tr>
				<th>정보제공자의견</th>
				<td id="c_InforOpinion"></td>
			</tr>
			<tr>
				<th>접수자료</th>
				<td id="c_Data"></td>
			</tr>
			<tr>
				<th>참조인</th>
				<td id="c_ViewCopyTo"></td>
			</tr>
		</tbody>
	</table>

	<table class="tb">
		<thead>
			<tr>
				<th colspan="2">검토 결과</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>부지조사</th>
				<td id="c_Research"></td>
			</tr>
			<tr>
				<th>보고일</th>
				<td id="c_Report"></td>
			</tr>
			<tr>
				<th>유관팀협조</th>
				<td id="c_Support"></td>
			</tr>
			<tr>
				<th>사업성검토기준</th>
				<td id="c_Price"></td>
			</tr>
		</tbody>
	</table>

	<table class="tb">
		<thead>
			<tr>
				<th colspan="2">개략사업성 검토 여부(선택)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>1차심사내용</th>
				<td id="c_Result_1"></td>
			</tr>
			<tr>
				<th>검토결과</th>
				<td id="c_Result"></td>
			</tr>
			<tr>
				<th>검토결과 회신</th>
				<td id="c_Reply"></td>
			</tr>
		</tbody>
	</table>
