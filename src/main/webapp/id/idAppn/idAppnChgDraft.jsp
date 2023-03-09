<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="특별ID SVPN권한 추가 신청" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리  -->
<ut:script src="${contextPath}/id/idAppn/idAppnChgDraft.js"></ut:script>

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<!-- 디자인 적용 CSS -->
<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />

<script type="text/javascript">
	var datas = ${param.datas};
	var v_userId = gv_userId;
	var v_userNm = gv_userNm;
	var v_orgCd = gv_orgCd;
	var v_orgNm = gv_orgNm;
	var v_userPositCd = gv_userPositCd;
	var v_userRpswrkCd = gv_userRpswrkCd;
</script>
</head>


<body id="popup_body">
	<!-- POPUP_WRAP -->
	<div id="popup_wrap">

		<!-- CONTENT -->
		<div id="content">

			<!--BTN-->
			<div class="btn-area">
				<!-- a class="btn s1" id="btnReadtn"><span>사전검토/회람지정</span></a-->
				<!-- a class="btn s1" id="btnSignln"><span>결재선지정</span></a-->
				<a class="btn s1" id="btnSave"><span>저장</span></a>
				<a class="btn s1" id="btnDraft"><span>상신</span></a>
				<a class="btn s1" id="btnSignln" style="display:none"><span>결재선지정</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">특별ID SVPN 권한 신청</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

			<br> <br>

			<!--결재선-->
			<div id="appr_line1">
				<table id="left_signln" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr class="signln_pos">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
						<tr class="signln_nm">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="signln_dt">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//결재선-->


			<!--결재선-->
			<div id="appr_line2">
				<table id="right_signln" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr class="signln_pos">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
						<tr class="signln_nm">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="signln_dt">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//결재선-->

			<!--문서정보-->
			<div id="infoDiv" class="box">
				<h3>문서정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<col width="13%" />
							<col width="87%" />
							<tr>
								<td class="tit">문서번호</td>
								<td class="inpt" name="docNo" id="docNo"></td>
							</tr>

							<!-- IF 기안자 존재 -->
							<tr id="signDrafter">
								<td class="tit">기안자</td>
								<!-- td class="inpt"><select name="authCd" id="authCd" class="txts1"></select>
								<span id="signDrafterVal"></span>
								</td-->
								<td class="inpt" style="padding:0;">
									<span id="drftUserNm"></span>
									<select name="authCd" id="authCd" style="width:120px;height:15px;font:normal 12px Dotum,'돋움',arial,helvetica,sans-serif;border:0px solid;background-color:#eee;padding:0;"></select>
								</td>
							</tr>
							<!-- //IF 협의자 존재 -->

							<!-- IF 협의자 존재 -->
							<tr id="signAssistor">
								<td class="tit">협의자</td>
								<td class="inpt" id="signAssistorVal"></td>
							</tr>
							<!-- //IF 협의자 존재 -->
							<tr>
								<td></td>
								<td></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!--//문서정보-->

			<br>

			<div class="box">
				<h3>대상자 리스트</h3>
				<div class="btn-area">
					<a class="btn s1" id="addBtn"><span>추가</span></a>
					<a class="btn s1" id="delBtn"><span>삭제</span></a>
				</div>
				<div class="list_st2" style="min-height: 70px;">
					<table id="idAppnDtlList" style="width: 750px;"></table>
				</div>
			</div>

			<!--본문-->
			<div class="box">
				<br>
				<div class="list_st1" id="idAppnDtl">

				<form id='idAppnDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='10%' />
						<col width='15%' />
						<col width='75%' />
						<tr>
							<td class='tit'>* 종료일자</td>
							<td class='inpt'><input type="text" name="edYmd" id="edYmd" style="width: 98%" class="int_s1 f_l"></td>
						 	<td class='inpt'><span class="s4" style="color:red;">
								※ ID별로 종료일자를 지정하시기 바랍니다.<br>
								※ 오늘부터 최대 1년까지 신청가능
								</span>
						 	</td>
						</tr>

						<tr>
							<td class='tit'>* 직급</td>
							<td class='inpt'><input type="text" name="grd" id="grd" style="width: 98%" class="int_s1"></td>
						 	<td class='inpt'></td>
						</tr>

						<tr>
							<td class='tit'>휴대폰번호</td>
							<td class='inpt'><input type="text" name="mphoneNo" id="mphoneNo" style="width: 98%" class="int_s1"></td>
						 	<td class='inpt'></td>
						</tr>

						<tr>
							<td class='tit'>회사전화번호</td>
							<td class='inpt'><input type="text" name="compTelno" id="compTelno" style="width: 98%" class="int_s1"></td>
						 	<td class='inpt'></td>
						</tr>

						<tr>
							<td class='tit'>팩스번호</td>
							<td class='inpt'><input type="text" name="faxno" id="faxno" style="width: 98%" class="int_s1"></td>
						 	<td class='inpt'></td>
						</tr>

						</table>
					</form>



					<form id='idAppnSysSVPN'>
						<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='10%' />
						<col width='40%' />
						<col width='50%' />
						<tr>
							<td class='tit'>* SVPN</td>
						 	<td class='inpt' id='SVPN' name='SVPN' disabled></td>
						 	<td class='inpt'>
						 					※ 사용 선택 시, 협의자로 SVPN 담당자 추가됨<br>
						 					※ 외부 사무실 근무자는 반드시 SVPN 신청 필요<br>
						 					※ SVPN 웹페이지 로그인 후 업무시스템 사용 가능<br>
						 					※ 접속경로 : https://svpn.dwconst.co.kr<br>
						 					※ ID : za사번, PW : za사번(최초 로그인 후 패스워드 변경)<br>
						 					※ 담당자 : IT기획팀 장승호 차장 (02-2288-4357)<br>
						 	</td>
						</tr>
					</table>
					</form>


				</div>
			</div>
			<!--//본문-->
		</div>
		<!-- CONTENT -->
	</div>
	<!-- //POPUP_WRAP -->
</body>
</html>