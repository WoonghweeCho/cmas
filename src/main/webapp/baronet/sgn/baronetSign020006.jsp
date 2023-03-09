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

	//http://dwdev.dwconst.co.kr/gw/app/aprv/daewoo/pubcard.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/aprv/daewoo/pubcard.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
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
		    			$("#c_wName").text(result.c_wName);
		    			$("#c_AppName").text(result.c_AppName);
		    			$("#c_CardCompany").text(result.c_CardCompany);
		    			$("#c_CardNo").text(result.c_CardNo);
		    			$("#c_IncreaseDate").text(result.c_IncreaseDate);
		    			$("#c_BeforeMoney").text(result.c_BeforeMoney);
		    			$("#c_AfterMoney").text(result.c_AfterMoney);
		    			$("#c_Sayu").text(result.c_Sayu);

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
		Description : 결재문서열람 - 지정형 법인카드-한도증액
	 -->
	<p class="pageTit">지정형 법인카드-한도증액</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>신청자</th>
				<td id="c_AppName"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="c_wName"></td>
			</tr>
			<tr>
				<th>카드사</th>
				<td id="c_CardCompany"></td>
			</tr>
			<tr>
				<th>카드번호</th>
				<td id="c_CardNo"></td>
			</tr>
			<tr>
				<th>증액기간</th>
				<td id="c_IncreaseDate"></td>
			</tr>
			<tr>
				<th>현재한도</th>
				<td id="c_BeforeMoney"></td>
			</tr>
			<tr>
				<th>요청한도</th>
				<td id="c_AfterMoney"></td>
			</tr>
			<tr>
				<th>증액사유</th>
				<td id="c_Sayu"></td>
			</tr>
		</tbody>
	</table>
