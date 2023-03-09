<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>

<html>
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="결재시스템" name="title"/>
		<jsp:param value="popup" name="type"/>
	</jsp:include>

	<!-- 공통 : 세션정보 -->
	<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

	<ut:script src="${contextPath}/id/idAppn/userIdSelect.js"></ut:script>

<script type="text/javascript">
	var datas = ${param.datas};
	var v_userId = gv_userId;
	var v_userNm = gv_userNm;
	var v_orgCd = gv_orgCd;
	var v_orgNm = gv_orgNm;
	var v_userPositCd = gv_userPositCd;
	var v_userRpswrkCd = gv_userRpswrkCd;
</script>

<style>
html {overflow:hidden;} </style>
</head>


<body>

<div id="">
	<div id="content">
		<br>
		<!--BTN-->
		<div class="btn-area">
			<a id="btnSearch" class="btn s1"><span>조회</span></a>
			<a id="btnConf" class="btn s1"><span>확인</span></a>
			<a id="btnClose" class="btn s5"><span>닫기</span></a>
		</div>
		<!--//BTN-->

		<!--검색설정-->
		<div class="int_box">
			<div class="bg_T"><span class="left"></span><span class="right"></span></div>
			<div class="content">

				<table id="table_user_search" width="100%" border="0" cellspacing="0" cellpadding="0">
					<col width="110px"/>
					<col width=""/>
					<tr>
						<td class="tit">
							부서/사용자명
						</td>
						<td class="inpt"><input name="txtSrchTxt" id="txtSrchTxt" type="text" style="width:150px" class="int_s1"/></td>
					</tr>
				</table>

			</div>
			<div class="bg_B"><span class="left"></span><span class="right"></span></div>
		</div>
		<!--//검색설정-->

		<br>

		<div class="box">
			<div style="min-width:100px;">
				<h3>사용자목록</h3>

				<!--리스트-->
				<div class="list_st2">
					<table id="users" ></table>
					<div id="usersPager"></div>
				</div>
				<!--//리스트-->

			</div>
		</div> <!--//box-->

	</div><!--//contents-->
</div><!--//popup_wrap-->
</body>
</html>