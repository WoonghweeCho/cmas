<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<!-- Common Library 처리 -->
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="배차신청" name="title"/>
		<jsp:param value="proxy" name="popupType"/>
	</jsp:include>

	<!-- JavaScript Logic 처리 -->
	<ut:script src="${contextPath}/common/jsp/eaps/carApp/carAppForm.js"></ut:script>

	<!-- HTTP Request Parameter 처리 -->
	<script type="text/javascript">
	</script>

	<link href="${contextPath}/css/base.css" type="text/css" rel="stylesheet" />
</head>


<body>
<!--
	Class Name 	: carAppForm.jsp
	Description : 배차신청 입력화면
 -->
	<!--right-->
	<div id="popup_wrap">

		<!--CONTENTS-->
		<div id="content">
		<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="save"><span>저장</span></a>
			</div>

			<!--배차정보-->
			<div id="" class="box" style="height:365px">
				<div class="list_st1">
					<form id="editForm" >
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							  <col width="13%"/>
							  <col width="32%"/>
							  <col width="13%"/>
							  <col width="32%"/>
							  <tr>
								<td class="tit">배차기간</td>
								<td class="inpt"  colspan="3" >
									<span class="f_l">시작일&nbsp;:&nbsp;</span>
									<input type="text" name="rncrStDt" style="width:90px" class="int_s1 f_l">
								<select id="rncrStTm" name="rncrStTm" class="int_s1 f_l">
           								<option value=""></option>
           								<option value="08:00">08:00</option>
           								<option value="08:30">08:30</option>
           								<option value="09:00">09:00</option>
           								<option value="09:30">09:30</option>
           								<option value="10:00">10:00</option>
           								<option value="10:30">10:30</option>
           								<option value="11:00">11:00</option>
           								<option value="11:30">11:30</option>
           								<option value="12:00">12:00</option>
           								<option value="12:30">12:30</option>
           								<option value="13:00">13:00</option>
           								<option value="13:30">13:30</option>
           								<option value="14:00">14:00</option>
           								<option value="14:30">14:30</option>
           								<option value="15:00">15:00</option>
           								<option value="15:30">15:30</option>
           								<option value="16:00">16:00</option>
           								<option value="16:30">16:30</option>
           								<option value="17:00">17:00</option>
           								<option value="17:30">17:30</option>
        							</select>

									<span class="f_l">&nbsp;~&nbsp;</span>
									<span class="f_l">종료일&nbsp;:&nbsp;</span>
									<input type="text" name="rncrEdDt" style="width:90px" class="int_s1 f_l">
									<select id="rncrEdTm" name="rncrEdTm" class="int_s1 f_l">
           								<option value=""></option>
           								<option value="08:30">08:30</option>
           								<option value="09:00">09:00</option>
           								<option value="09:30">09:30</option>
           								<option value="10:00">10:00</option>
           								<option value="10:30">10:30</option>
           								<option value="11:00">11:00</option>
           								<option value="11:30">11:30</option>
           								<option value="12:00">12:00</option>
           								<option value="12:30">12:30</option>
           								<option value="13:00">13:00</option>
           								<option value="13:30">13:30</option>
           								<option value="14:00">14:00</option>
           								<option value="14:30">14:30</option>
           								<option value="15:00">15:00</option>
           								<option value="15:30">15:30</option>
           								<option value="16:00">16:00</option>
           								<option value="16:30">16:30</option>
           								<option value="17:00">17:00</option>
           								<option value="17:30">17:30</option>
        							</select>
								</td>
							  </tr>
							  <tr>
								<td class="tit">용도</td>
								<td class="inpt" ><select name="carAppnClscd" id="useCd" style="width:126px" class="sel_st1"></select></td>
								<td class="tit">사유</td>
								<td class="inpt" >
									<input name="appnResn" type="text" style="width:300px" class="int_s1"/>
								</td>
							  </tr>
							  <tr >
								<td class="tit">행선지</td>
								<td class="inpt" >
									<input name="destNm" type="text" style="width:300px" class="int_s1"/>
								</td>
								<td class="tit">탑승자</td>
								<td class="inpt" >
									<input name="userNm" type="text" style="width:300px" class="int_s1"/>
							  	</td>
							  </tr>
							  <tr >
								<td class="tit">운전구분</td>
								<td class="inpt" ><select name="drvClscd" id="drvClscd" style="width:126px" class="sel_st1"></select></td>
								<td class="tit">차종</td>
								<td class="inpt" >
								<input name="carKindExpl" type="text" style="width:300px" class="int_s1"/>
								</td>
							  </tr>
						</table>
					</form>
					<div><span>※ 배차신청 시간은 전일 08:00 ~ 16:30까지만 가능합니다.</span></div>
				</div>
			</div>
			<!--//배차정보-->


		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>