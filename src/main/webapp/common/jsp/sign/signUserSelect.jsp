<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="결재시스템" name="title"/>
		<jsp:param value="popup" name="type"/>
	</jsp:include>

	<!-- 공통 : 세션정보 -->
	<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

	<ut:script src="${contextPath}/common/jsp/sign/signUserSelect.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas};	// 파라메터로 인코딩 된 데이터
	var v_userId = gv_userId;
	var v_orgCd = gv_orgCd;
</script>
</head>


<body>

<div id="popup_wrap">

	<div class="top_bar">
    	<span class="title">결재선지정</span>
    </div>

	<div id="content">

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

				<table id="table_sign_search" width="100%" border="0" cellspacing="0" cellpadding="0">
					<col width="110px"/>
					<col width=""/>
					<tr>
						<td class="tit">
							부서/사원명
						</td>
						<td class="inpt"><input name="txtSrchTxt" id="txtSrchTxt" type="text" style="width:250px" class="int_s1"/></td>
					</tr>
				</table>

			</div>
			<div class="bg_B"><span class="left"></span><span class="right"></span></div>
		</div>
		<!--//검색설정-->

		<br>

		<div class="box">

			<div class="half f_l" style="height:200px;">
				<h3>부서목록</h3>
					<div class="btn-area f_r">
						<a id="treeReset" class="btn s4"><span>초기화</span></a>
					</div>
				<div class="area_line" style="overflow:hidden; height:170px;">
					<ul id="orgTree" class="ztree" style="overflow: auto; height: 150px; padding:10px;"></ul>
				</div>

			</div>



			<div class="half f_r" style="height:200px;">
				<h3>직원목록</h3>

				<!--리스트-->
				<div class="list_st2" style="min-height:175px;">
					<table id="signln" style="width: 300px;"></table>
					<div id="signlnPager"></div>
				</div>
				<!--//리스트-->

			</div>

			<div class="half f_r" style="height:200px;">
				<h3>결재선</h3>

				<div class="btn-area f_r">
				</div>

				<div class="btn-area" style="width:50%;text-align:center;">
					<a id="addUser1" class="btn s1"><span>결재▼</span>&nbsp;</a>
					<!-- <a id="addUser2" class="btn s1"><span>협의▼</span>&nbsp;</a>  -->
					<a id="delUser" class="btn s5"><span>삭제▲</span>&nbsp;</a>
				</div>

				<!--리스트-->
				<div class="list_st2" style="min-height:175px;">
					<table id="selSignln" style="width: 300px;"></table>
					<div id="selSignlnPager"></div>
				</div>
				<!--//리스트-->

			</div>

			<div class="clear"></div>
		</div> <!--//box-->

	</div><!--//contents-->
</div><!--//popup_wrap-->
</body>
</html>