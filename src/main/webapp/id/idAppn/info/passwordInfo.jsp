<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file='/common/jsp/comm/include/baseSetting.jsp'%>
<html>
<head>
<title>비밀번호안내</title>
<!-- 디자인 적용 CSS -->
<link href='${contextPath}/common/css/base.css' type='text/css'	rel='stylesheet' />
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<table width="800" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td align="center" style="padding-top:32px;">

        <table border="0" cellpadding="0" cellspacing="0" width="600">
    	<tr>
        <td>			<div style="text-align: center;"><span style="font-size:24px"><strong>특별ID 최초비밀번호 및 비밀번호 초기화 안내</strong></span></div>
</td>
        </tr>
        <tr>
        <td height="2" bgcolor="#507896"></td>
        </tr>
        <tr>
        <td>

		<br><br>
        <div id='viewTitle' class='form_title' align='left'>
		최초 비밀번호는 발급받은 특별ID와 동일(대문자, SVPN은 소문자)	<br>
		[예] 발급받은 특별ID : ZA99999 <br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color=red>아이디 : ZA99999 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (SVPN은 za99999)</font> <br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color=red>비밀번호 : ZA99999 &nbsp;&nbsp;&nbsp; (SVPN은 za99999)</font>
		</div>

		<br><br>

					<!--리스트-->
					<div class="list_st1">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<col width="12%" />
							<col width="45%" />
							<col width="43&" />
							<tr>
								<td class="tit" style="text-align:center">시스템</td>
								<td class="tit" style="text-align:center">초기화</td>
								<td class="tit" style="text-align:center">변경</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">바로넷</td>
								<td class="inpt" style="text-align:left" rowspan="3">
								&nbsp; * 바로넷 사용자 : <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 바로넷-업무지원-비밀번호 찾기 <br><br>

								&nbsp; * 바로넷 미 사용자 : <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 바로콘/메일 담당자에게 문의 <br>
								</td>
								<td class="inpt" style="text-align:left" rowspan="3">
								&nbsp; * 바로넷 사용자 : <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 바로넷-My Work-환경설정 <br>
								&nbsp;&nbsp;&nbsp;&nbsp; (바로넷/바로콘/메일 일괄반영) <br>
								&nbsp; * 바로콘 : 바로콘-화면 좌측의 <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 톱니바퀴 <br>
								&nbsp; * 메일 : 메일-비밀번호 변경 <br>
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">바로콘</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">메일</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">바로미</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 바로미만 사용하는 특별ID는 <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 바로미 담당자에게 문의 <br>
								</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 바로미만 사용하는 특별ID는 <br>
								&nbsp;&nbsp;&nbsp;&nbsp; 바로미-환경설정에서 변경 가능 <br>
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">SVPN</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * SVPN 담당자에게 문의
								</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * SVPN 담당자에게 문의
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">분양관리</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 분양관리 담당자에게 문의
								</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 분양관리 담당자에게 문의
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">프로젝트<br>외주인력<br>보안</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 프로젝트 외주인력 보안 담당자에게 문의
								</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 프로젝트 외주인력 보안 담당자에게 문의
								</td>
							</tr>
<!--  						<tr>
								<td class="inpt" style="text-align:center">스마트세이프티</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 스마트세이프티 담당자에게 문의
								</td>
								<td class="inpt" style="text-align:left">
								&nbsp; * 스마트세이프티 담당자에게 문의
								</td>
							</tr>
-->
						</table>
					</div>

        <br><br>

        <div id='viewTitle' class='form_title' align='left'>
        [ID 관련 담당자]<br>
        &nbsp; 바로넷 : 디지털개발팀 차장 제창현(4063)<br>
        &nbsp; 메일 : 디지털개발팀 대리 이진혁 (3464)<br>
        &nbsp; 바로콘 : 디지털개발팀 대리 김재갑 (4127)<br>
        &nbsp; 모바일(One Touch HSE-Q) : 안전보건팀 과장 김정규 (2182)<br>
        &nbsp; 바로미 : 디지털개발팀 차장 제창현 (4063)<br>
        &nbsp; SVPN : 디지털전략팀 차장 장승호 (4357)<br>
			        <!-- &nbsp; 스마트세이프티 : 안전보건팀 과장 김정규  (2182)<br>  -->
			        &nbsp; 분양관리 : 주택건축분양팀 과장 이혜용 (4461)<br>
			        &nbsp; 프로젝트 외주인력 보안 : 정보보호팀 부장 김명호 (4379)<br>
		</div>

		<br><br>

		</td>
        </tr>
    </table>
</body>
</html>