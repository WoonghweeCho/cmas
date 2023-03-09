<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="특별ID 시스템 목록" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/id/idAppn/idAppnSysList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>특별ID</span> &nbsp;>&nbsp; <strong>특별ID 시스템 목록</strong>
			</div>
		</div>
		<!--//top_bar-->

<body>
	<!--right-->
	<div id="container">
		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="addBtn"><span>행추가</span></a>
				<a class="btn s1" id="delBtn"><span>행삭제</span></a>
				<a class="btn s1" id="btnSave"><span>저장</span></a>
				<a class="btn s1" id="search"><span>조회</span></a>
			</div>
			</div>
			<!--//검색설정-->

			<br>

			<!--특별ID 시스템 목록-->
			<div class="box">
				<!--리스트-->
				<div class="list_st2" style="min-height:470px;">
					<table id="idAppnSysList" style="width: 900px;"></table>
				</div>
				<!--//리스트-->
			</div>
			<!--//특별ID 시스템 목록-->

			<!--본문-->
			<div class="box">
				<br>
				<div class="list_st1" id="idAppnDtl">
				<form id='idAppnSysForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />
						<tr>
							<td class='tit'>* 시스템</td>
							<td class='inpt'><select name="sysCd" id="sysCd" style="width: 98%"
											class="sel_st1 f_l mar_r5"></select></td>
							<td class='tit'>* 모듈명</td>
							<td class='inpt'><input name='moduleNm' id='moduleNm'
								type='text' style='width: 98%' class='int_s1' /></td>
							<td class='tit'>담당자</td>
							<td class='inpt'><input name='perchrgId' id='perchrgId'
								type='text' style='width: 85%' class='int_s1' />
								<a href="#0" class="f_r" id="sysCdBtn"><img src="${contextPath}/common/images/common/btn_search.png" ></a></td>
						</tr>
						<tr>
							<td class='inpt' colspan=6>
							※ 저장된 시스템, 모듈명은 변경할 수 없습니다.<br>
							※ 담당자는 검색 버튼을 이용해 입력해 주십시오.</td>
						</tr>
					</table>
				</form>
				</div>
			</div>
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>