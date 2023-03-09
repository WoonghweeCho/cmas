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
		    			$("#c_Ename").text(result.c_Ename);
		    			$("#c_JuNo").text(result.c_JuNo);
		    			$("#c_CardCompany").text(result.c_CardCompany);
		    			$("#c_Limit").text(result.c_Limit);
		    			$("#c_addr").text(result.c_addr);
		    			$("#c_Sayu").text(result.c_Sayu);
		    			$("#c_Gubun1").text(result.c_Gubun1);
		    			$("#c_Money").text(result.c_Money);
		    			$("#c_Gubun2").text(result.c_Gubun2);
		    			$("#c_Gubun3").text(result.c_Gubun3);
		    			$("#c_MadeDate").text(result.c_MadeDate);
		    			$("#c_AgreeName").text(result.c_AgreeName);
		    			$("#c_JuNo2").text(result.c_JuNo);

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
		Description : 결재문서열람 - 지정형 법인카드-신규발급
	 -->
	<p class="pageTit">지정형 법인카드-신규발급</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>신청구분</th>
				<td id="acceptDate">신규발급신청서</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="c_wName"></td>
			</tr>
			<tr>
				<th>신청자</th>
				<td id="c_AppName"></td>
			</tr>
			<tr>
				<th>영문명</th>
				<td id="c_Ename"></td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td id="c_JuNo"></td>
			</tr>
			<tr>
				<th>카드사</th>
				<td id="c_CardCompany"></td>
			</tr>
			<tr>
				<th>한도</th>
				<td id="c_Limit"></td>
			</tr>
			<tr>
				<th>카드 수령 주소</th>
				<td id="c_addr"></td>
			</tr>
			<tr>
				<th>발급사유</th>
				<td id="c_Sayu"></td>
			</tr>
			<tr>
				<th>카드종류</th>
				<td id="c_Gubun1"></td>
			</tr>
			<tr>
				<th>이용한도</th>
				<td id="c_Money"></td>
			</tr>
			<tr>
				<th>브랜드구분</th>
				<td id="c_Gubun2"></td>
			</tr>
			<tr>
				<th>제휴카드명</th>
				<td id="c_Gubun3"></td>
			</tr>
		</tbody>
	</table>

	<table class="tb">
		<thead>
			<tr>
				<th>동의서</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					법인카드 사용 후 미정산, 결제계좌 잔액부족등의 개인부주의로
					연체가 발생하여 회사가 대위변제 할 경우에는 본인의 급여,
					퇴직금 등에서 변제하여도 이의제기치 않을 것임을 동의합니다.<br />

					<div id="c_MadeDate" class="mdate">&nbsp;</div>

					<p class="info">
						<span style="position:absolute; top:0; right:120px;">성&nbsp;&nbsp;&nbsp;&nbsp;명 : </span>
						<span id="c_AgreeName">&nbsp;</span>
					</p>
					<p class="info">
						<span style="position:absolute; top:0; right:120px;">주민번호 : </span>
						<span id="c_JuNo2">&nbsp;</span>
					</p>
				</td>
			</tr>
		</tbody>
	</table>
