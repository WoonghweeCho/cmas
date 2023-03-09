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

	//http://dwdev.dwconst.co.kr/gw/app/bult/sapid.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/bult/sapid.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
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
		    			$("#c_Date").text(result.c_Date);
		    			$("#c_Team").text(result.c_Team);
		    			$("#c_TeamCode").text(result.c_TeamCode);
		    			$("#c_Gubun").text(result.c_Gubun);
		    			$("#c_Gubun_1").text(result.c_Gubun_1);
		    			$("#c_SapID").text(result.c_SapID);
		    			$("#c_Receive_Nation").text(result.c_Receive_Nation);
		    			$("#c_Receive_Team").text(result.c_Receive_Team + " " + result.c_Receive_Teamcode);
		    			$("#c_AuthorityList").text(result.c_AuthorityList);
		    			$("#c_Etc").text(result.c_Etc);

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
		Description : 결재문서열람 - SAP ID 신청/변경
	 -->
	<p class="pageTit">SAP ID 신청/변경</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>신청자</th>
				<td id="c_Name"></td>
			</tr>
			<tr>
				<th>신청일</th>
				<td id="c_Date"></td>
			</tr>
			<tr>
				<th>팀명</th>
				<td id="c_Team"></td>
			</tr>
			<tr>
				<th>팀코드</th>
				<td id="c_TeamCode"></td>
			</tr>
			<tr>
				<th>소속구분</th>
				<td id="c_Gubun"></td>
			</tr>
			<tr>
				<th>신청구분</th>
				<td id="c_Gubun_1"></td>
			</tr>
			<tr>
				<th>SAP ID</th>
				<td id="c_SapID"></td>
			</tr>
			<tr>
				<th>국가</th>
				<td id="c_Receive_Nation"></td>
			</tr>
			<tr>
				<th>현장명</th>
				<td id="c_Receive_Team"></td>
			</tr>
			<tr>
				<th>권한</th>
				<td id="c_AuthorityList"></td>
			</tr>
			<tr>
				<th>기타요청</th>
				<td id="c_Etc"></td>
			</tr>
		</tbody>
	</table>
