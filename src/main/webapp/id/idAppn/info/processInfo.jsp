<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file='/common/jsp/comm/include/baseSetting.jsp'%>
<html>
<head>
<title>발급절차안내</title>
<style type="text/css">
.font{
	font-family:돋움;
	font-size:12px;
	text-align:left;
	color:#327399;
	padding-top:8px;
	padding-bottom:8px;
}
.table_title{
	font-family:돋움;
	font-size:13px;
	text-align:center;
	color:#303030;
	height:28px;
	font-weight:bold;
	background-color:#f0f0f0;
}
.table_title1{
	font-family:돋움;
	font-size:13px;
	text-align:center;
	color:#303030;
	background-color:#f0f0f0;
}
.table_text{
	font-family:돋움;
	font-size:13px;
	text-align:center;
	color:#303030;
	height:28px;
	letter-spacing:-1pt;
	background-color:#ffffff;
}
.body_text{
	font-family:돋움;
	font-size:13px;
	text-align:left;
	color:#303030;
	letter-spacing:-1pt;
	line-height:20px;
	padding-top:15px;
	padding-bottom:15px;
}
.body_text1{
	font-family:돋움;
	font-size:13px;
	text-align:left;
	color:#303030;
	letter-spacing:-1pt;
	line-height:20px;
}
</style>

<!-- 디자인 적용 CSS -->
<link href='${contextPath}/common/css/base.css' type='text/css'	rel='stylesheet' />

</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<table width="800" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td align="center" style="padding-top:32px;">

        <table border="0" cellpadding="0" cellspacing="0" width="600">
    	<tr>
        <td>			<div style="text-align: center;"><span style="font-size:24px"><strong>특별ID 발급절차 안내</strong></span></div>
</td>
        </tr>
        <tr>
        <td height="2" bgcolor="#507896"></td>
        </tr>

<!--
        <tr>
        <td class="font">특별ID 담당자 : IT운영팀 과장 나상문 (4184), 인사팀 대리 박상현(1781)<br>
        <br>[ID 관련 담당자]<br>
         바로넷 : IT운영팀 과장 제창현(4063)&nbsp;&nbsp;&nbsp;메일 : IT운영팀 과장 송진규 (5055)&nbsp;&nbsp;&nbsp;바로콘 : IT운영팀 대리 신동길 (2068)<br>
         모바일(One Touch HSE-Q) : 국내HSE팀 대리 김정규 (2182)&nbsp;&nbsp;&nbsp;&nbsp;SVPN 담당자 : IT기획팀 차장 장승호
		</td>
        </tr>


        <tr>
        <td class="body_text"><img src="processInfo.png"/>
		</td>
        </tr>
  -->


        <tr>
        <td>


		<br><br>

        					<!--리스트-->
					<div class="list_st1">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<col width="15%" />
							<col width="50%" />
							<col width="35%" />
							<tr>
								<td class="inpt" style="text-align:center;background-color:#FFE400;" colspan="3"><b>신규</b></td>
							</tr>
							<tr>
								<td class="tit" style="text-align:center">순서</td>
								<td class="tit" style="text-align:center">절차</td>
								<td class="tit" style="text-align:center">담당자</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">1</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;<b>신규 신청서 상신</b></td>
								<td class="inpt" style="text-align:center">임직원</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">2</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;협의요청</td>
								<td class="inpt" style="text-align:center">팀장/소장</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">3</td>
								<td class="inpt" style="text-align:left; font-weight:bold;">
								&nbsp;<font color="red">협의결재</font> <br>
								&nbsp;&nbsp;&nbsp;- 인사팀 외 협의 필요 시스템 담당부서<br>
								</td>
								<td class="inpt" style="text-align:center">
								협의결재자 <br>
								(신청권한에 따라 자동으로 지정됨)
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">4</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;결재완료</td>
								<td class="inpt" style="text-align:center">팀장/소장</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">5</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">
								&nbsp;익일 자동발급 및 신청권한 자동부여
								</td>
								<td class="inpt" style="text-align:center">시스템</td>
							</tr>

							<tr>
								<td> </td>
							</tr>
							<tr>
								<td> </td>
							</tr>

							<tr>
								<td class="inpt" style="text-align:center;background-color:#FFE400;" colspan="3"><b>연장</b></td>
							</tr>
							<tr>
								<td class="tit" style="text-align:center">순서</td>
								<td class="tit" style="text-align:center">절차</td>
								<td class="tit" style="text-align:center">담당자</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">1</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;<b>연장 신청서 상신</b></td>
								<td class="inpt" style="text-align:center">임직원</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">2</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;협의요청</td>
								<td class="inpt" style="text-align:center">팀장/소장</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">3</td>
								<td class="inpt" style="text-align:left; font-weight:bold;">
								&nbsp;<font color="red">협의결재</font> <br>
								&nbsp;&nbsp;&nbsp;- 인사팀 <br>
								</td>
								<td class="inpt" style="text-align:center">
								인사팀
								</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">4</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;결재완료</td>
								<td class="inpt" style="text-align:center">팀장/소장</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">5</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">
								&nbsp;익일 자동 연장
								</td>
								<td class="inpt" style="text-align:center">시스템</td>
							</tr>

							<tr>
								<td> </td>
							</tr>
							<tr>
								<td> </td>
							</tr>

							<tr>
								<td class="inpt" style="text-align:center;background-color:#FFE400;" colspan="3"><b>해지</b></td>
							</tr>
							<tr>
								<td class="tit" style="text-align:center">순서</td>
								<td class="tit" style="text-align:center">절차</td>
								<td class="tit" style="text-align:center">담당자</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">1</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;<b>해지 신청서 상신</b></td>
								<td class="inpt" style="text-align:center">임직원</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">2</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">&nbsp;결재완료</td>
								<td class="inpt" style="text-align:center">팀장/소장</td>
							</tr>
							<tr>
								<td class="inpt" style="text-align:center">3</td>
								<td class="inpt" style="text-align:left; font-weight:bold; color:red;">
								&nbsp;익일 자동 해지
								</td>
								<td class="inpt" style="text-align:center">시스템</td>
							</tr>

						</table>
					</div>

		<br><br>

			        <div id='viewTitle' class='form_title' align='left'>
			        <br>[ID 관련 담당자]<br>
			        &nbsp; 바로넷 : 디지털개발팀 차장 제창현(4063)<br>
			        &nbsp; 메일 : 디지털개발팀 대리 이진혁 (3464)<br>
			        &nbsp; 바로콘 : 디지털개발팀 과장 신동길 (2068)<br>
			        &nbsp; 모바일(One Touch HSE-Q) : 안전교육팀 과장 박진석 (4435)<br>
			        &nbsp; 바로미 : 디지털개발팀 차장 제창현(4063)<br>
			        &nbsp; SVPN :디지털전략팀 차장 장승호 (4357)<br>
   			       <!-- &nbsp; 스마트세이프티 : 안전보건팀 과장 김정규 (2182)<br> -->
   			        &nbsp; 분양관리 : 주택건축분양팀 과장 이혜용 (4461)<br>
   			        &nbsp; 프로젝트 외주인력 보안 : 정보보호팀 부장 김명호 (4379)<br>
					</div>

		<br><br>

		</td>
        </tr>


    </table>
</body>
</html>