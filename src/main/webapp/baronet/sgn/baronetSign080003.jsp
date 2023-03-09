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

	//http://dwdev.dwconst.co.kr/gw/app/aprv/daewoo/fund.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/aprv/daewoo/fund.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
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
		    			$("#c_ReqDate").text(result.c_ReqDate);
		    			$("#c_Damdang").text(result.c_Damdang);
		    			$("#c_PjName").text(result.c_PjName);
		    			$("#c_Sum").text(addComma(result.c_Sum));

	    				for(var i = 0; i <= 4; i++) {
	    					$("#c_PDate" + i).text(result["c_PDate" + i]);
		    				$("#c_PBank" + i).text(result["c_PBank" + i]);
		    				$("#c_PNum" + i).text(result["c_PNum" + i]);
		    				$("#c_PName" + i).text(result["c_PName" + i]);
		    				$("#c_PAccount" + i).text(addComma(result["c_PAccount" + i]));
	    				}

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

	function addComma(number) {
		var regExp1 = /(^[+-]?\d+)(\d{3})/;	// 소수점 없는거 용도
		var regExp2 = /(^[+-]?\d+)(\d{3}[.]\d{0,})/;	// 소수점 있는거 용도
		var regExp = regExp1;
		var string = String(number);

		if(string.indexOf('.') != -1) regExp = regExp2;

		while (regExp.test(string)) {
			string = string.replace(regExp, '$1,$2');
		}
		return string;
	}
</script>

	<!--
		Class Name 	: baronetDocViewer.jsp
		Description : 결재문서열람 - 수금통보(주택)
	 -->
	<p class="pageTit">수금통보(주택)</p>

	<table class="tb">
		<thead>
			<tr>
				<th colspan="2">개요</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>요청일자</th>
				<td id="c_ReqDate"></td>
			</tr>
			<tr>
				<th>담당</th>
				<td id="c_Damdang"></td>
			</tr>
			<tr>
				<th>CODE/PJ명</th>
				<td id="c_PjName"></td>
			</tr>
		</tbody>
	</table>

	<table class="tb">
		<thead>
			<tr>
				<th colspan="2">입금정보(합계 : <span id="c_Sum"></span>)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>일자</th>
				<td id="c_PDate1"></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td id="c_PBank1"></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td id="c_PNum1"></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td id="c_PName1"></td>
			</tr>
			<tr>
				<th>금액</th>
				<td id="c_PAccount1"></td>
			</tr>
			<tr>
				<th>일자</th>
				<td id="c_PDate2"></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td id="c_PBank2"></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td id="c_PNum2"></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td id="c_PName2"></td>
			</tr>
			<tr>
				<th>금액</th>
				<td id="c_PAccount2"></td>
			</tr>
			<tr>
				<th>일자</th>
				<td id="c_PDate3"></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td id="c_PBank3"></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td id="c_PNum3"></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td id="c_PName3"></td>
			</tr>
			<tr>
				<th>금액</th>
				<td id="c_PAccount3"></td>
			</tr>
			<tr>
				<th>일자</th>
				<td id="c_PDate4"></td>
			</tr>
			<tr>
				<th>은행명</th>
				<td id="c_PBank4"></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td id="c_PNum4"></td>
			</tr>
			<tr>
				<th>예금주</th>
				<td id="c_PName4"></td>
			</tr>
			<tr>
				<th>금액</th>
				<td id="c_PAccount4"></td>
			</tr>
		</tbody>
	</table>

	<p>*) 상세데이터는 바로넷을 참조 바랍니다.</p>
