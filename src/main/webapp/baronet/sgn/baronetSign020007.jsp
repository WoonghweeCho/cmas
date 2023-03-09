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
		    			$("#c_Dept").text(result.c_Dept);
		    			$("#c_Name").text(result.c_Name);
		    			$("#c_Jikwi").text(result.c_Jikwi);
		    			$("#c_TelNo").text(result.c_TelNo);
		    			$("#c_CardNo").text(result.c_CardNo);
		    			$("#c_CardName").text(result.c_CardName);
		    			$("#c_Kind").text(result.c_Kind);
		    			$("#c_Reason").text(result.c_Reason);
		    			$("#c_LimitDate").text(result.c_LimitDate);
		    			$("#c_CloseYmd").text(result.c_CloseYmd);

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
		Description : 결재문서열람 - 지정형 법인카드-해지
	 -->
	<p class="pageTit">지정형 법인카드-해지</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>소속</th>
				<td id="c_Dept"></td>
			</tr>
			<tr>
				<th>성명</th>
				<td id="c_Name"></td>
			</tr>
			<tr>
				<th>호칭</th>
				<td id="c_Jikwi"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td id="c_TelNo"></td>
			</tr>
			<tr>
				<th>카드번호</th>
				<td id="c_CardNo"></td>
			</tr>
			<tr>
				<th>카드명의자</th>
				<td id="c_CardName"></td>
			</tr>
			<tr>
				<th>카드종류</th>
				<td id="c_Kind"></td>
			</tr>
			<tr>
				<th>해지사유</th>
				<td id="c_Reason"></td>
			</tr>
			<tr>
				<th>유효기간</th>
				<td id="c_LimitDate"></td>
			</tr>
			<tr>
				<th>해지신청일</th>
				<td id="c_CloseYmd"></td>
			</tr>
		</tbody>
	</table>
