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

	//http://dwdev.dwconst.co.kr/gw/app/bult/nidreq.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/bult/nidreq.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
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
		    			$("#c_Name").text(result.c_Name);
		    			$("#c_RequestDate").text(result.c_RequestDate);
		    			$("#c_useApp").text(result.c_useApp);
		    			$("#c_TeamCode").text(result.c_TeamCode + " " + result.c_Team);
		    			$("#c_appCnt").text(result.c_appCnt);
		    			$("#c_startDate").text(result.c_startDate);
		    			$("#c_Note").text(result.c_Note);

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
		Description : 결재문서열람 - 업무시스템 가상화 신청
	 -->
	<p class="pageTit">업무시스템 가상화 신청</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>신청자</th>
				<td id="c_Name"></td>
			</tr>
			<tr>
				<th>신청일</th>
				<td id="c_RequestDate"></td>
			</tr>
			<tr>
				<th>사용요청 시스템</th>
				<td id="c_useApp"></td>
			</tr>
			<tr>
				<th>팀명</th>
				<td id="c_TeamCode"></td>
			</tr>
			<tr>
				<th>사용인원</th>
				<td id="c_appCnt"></td>
			</tr>
			<tr>
				<th>사용종료일</th>
				<td id="c_startDate"></td>
			</tr>
			<tr>
				<th>비고</th>
				<td id="c_Note"></td>
			</tr>
		</tbody>
	</table>
