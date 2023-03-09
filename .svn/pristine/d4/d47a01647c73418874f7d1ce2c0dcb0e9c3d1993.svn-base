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
		    			$("#c_AppName").text(result.c_AppName);
		    			$("#c_wName").text(result.c_wName);
		    			$("#c_CardCompany").text(result.c_CardCompany);
		    			$("#c_CardNo").text(result.c_CardNo);
		    			$("#c_Gubun").text(result.c_Gubun);
		    			$("#c_BeforeBankName").text(result.c_BeforeBankName);
		    			$("#c_BeforeAccountName").text(result.c_BeforeAccountName);
		    			$("#c_BeforeBankAccount").text(result.c_BeforeBankAccount);
		    			$("#c_AfterBankName").text(result.c_AfterBankName);
		    			$("#c_AfterAccountName").text(result.c_AfterAccountName);
		    			$("#c_AfterBankAccount").text(result.c_AfterBankAccount);
		    			$("#c_BeforePwd").text(result.c_BeforePwd);
		    			$("#c_AfterPwd").text(result.c_AfterPwd);
		    			$("#c_BeforeAddr").text(result.c_BeforeAddr);
		    			$("#c_AfterAddr").text(result.c_AfterAddr);
		    			$("#c_BeforeEmpno").text(result.c_BeforeEmpno);
		    			$("#c_AfterEmpno").text(result.c_AfterEmpno);
		    			$("#c_Reason").text(result.c_Reason);
		    			$("#c_RequestContent").text(result.c_RequestContent);

		    			//결재 URL 세팅
		    			$("#signURL").attr("data-url", result.signurl);
		    			$("#rejectURL").attr("data-url", result.rejecturl);

		    			//변경항목에 따라 항목 감추기
						if(result.c_Gubun.indexOf("[결제계좌]") > -1) {
							$("#bank").css("display", "");
						}
						if(result.c_Gubun.indexOf("[비밀번호]") > -1) {
							$("#passwd").css("display", "");
						}
						if(result.c_Gubun.indexOf("[대금청구지]") > -1) {
							$("#addr").css("display", "");
						}
						if(result.c_Gubun.indexOf("[사번변경]") > -1) {
							$("#empNo").css("display", "");
						}
						if(result.c_Gubun.indexOf("[기타]") > -1) {
							$("#etc").css("display", "");
						}
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
		Description : 결재문서열람 - 지정형 법인카드-정보변경
	 -->
	<p class="pageTit">지정형 법인카드-정보변경</p>

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
				<th>변경항목</th>
				<td id="c_Gubun"></td>
			</tr>
		</tbody>
	</table>

	<table id="bank" class="tb" style="display:none;">
		<thead>
			<tr>
				<th colspan="2">결제계좌 변경</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>기존 은행명</th>
				<td id="c_BeforeBankName"></td>
			</tr>
			<tr>
				<th>기존 예금주</th>
				<td id="c_BeforeAccountName"></td>
			</tr>
			<tr>
				<th>기존 계좌번호</th>
				<td id="c_BeforeBankAccount"></td>
			</tr>
			<tr>
				<th>변경 은행명</th>
				<td id="c_AfterBankName"></td>
			</tr>
			<tr>
				<th>변경 예금주</th>
				<td id="c_AfterAccountName"></td>
			</tr>
			<tr>
				<th>변경 계좌번호</th>
				<td id="c_AfterBankAccount"></td>
			</tr>
		</tbody>
	</table>

	<table id="passwd" class="tb" style="display:none;">
		<thead>
			<tr>
				<th colspan="2">비밀번호 변경</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>기존 비밀번호</th>
				<td id="c_BeforePwd"></td>
			</tr>
			<tr>
				<th>변경 비밀번호</th>
				<td id="c_AfterPwd"></td>
			</tr>
		</tbody>
	</table>

	<table id="addr" class="tb" style="display:none;">
		<thead>
			<tr>
				<th colspan="2">대금청구지 변경</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>기존 계좌번호</th>
				<td id="c_BeforeAddr"></td>
			</tr>
			<tr>
				<th>변경 계좌번호</th>
				<td id="c_AfterAddr"></td>
			</tr>
		</tbody>
	</table>

	<table id="empNo" class="tb" style="display:none;">
		<thead>
			<tr>
				<th colspan="2">사번 변경</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>기존 사번</th>
				<td id="c_BeforeEmpno"></td>
			</tr>
			<tr>
				<th>변경 사번</th>
				<td id="c_AfterEmpno"></td>
			</tr>
		</tbody>
	</table>

	<table id="etc" class="tb" style="display:none;">
		<thead>
			<tr>
				<th colspan="2">기타</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>사유</th>
				<td id="c_Reason"></td>
			</tr>
			<tr>
				<th>요청사항</th>
				<td id="c_RequestContent"></td>
			</tr>
		</tbody>
	</table>
