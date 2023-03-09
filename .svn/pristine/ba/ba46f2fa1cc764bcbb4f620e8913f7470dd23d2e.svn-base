<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType" />
	<jsp:param value="비자 발급 신청" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>

<!-- 파일 업로드 -->
<jsp:include page="/common/jsp/comp/fileupload/upload4RexComp.jsp">
	<jsp:param value="hiduploaddiv" name="objId" />
	<jsp:param value="f_upCallback" name="callbackFunc" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/visaAppn/visaAppnDraft.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
	var v_userId = gv_userId;	//작성자
	var v_userNm = gv_userNm;	//작성자명
	var v_orgCd = gv_orgCd;		//작성자 소속코드
	var v_orgNm = gv_orgNm;		//작성자 소속명
	var v_userPositCd = gv_userPositCd;	//직급
	var v_userRpswrkCd = gv_userRpswrkCd; //직책
</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>

<body>
	<!--
	Class Name 	: visaAppnDraft.jsp
	 -->
	<!--right-->
	<!--CONTENTS-->
	<div id="contents">
		<div class="btn-area">
			<a class="btn s1" id="btnDelete" style="display: none"><span>삭제</span></a>
			<a class="btn s1" id="btnSignln" style="display: none"><span>결재선지정</span></a>
			<a class="btn s1" id="btnSave"><span>저장</span></a>

			<%-- 결재 이전 --%>
			<a class="btn s1" id="btnRequestForHelp"><span>협조의뢰</span></a>
			<a class="btn s1" id="btnVisaNotConfirm" style="display:none"><span>반려</span></a>
			<a class="btn s1" id="btnVisaConfirm" style="display:none"><span>검토완료</span></a>

			<%-- 결재 이후 --%>
			<a class="btn s1" id="btnSubmit" style="display: none"><span>비자접수</span></a>
			<a class="btn s1" id="btnIssue" style="display: none"><span>비자발급완료</span></a>
			<a class="btn s1" id="btnClose"><span>닫기</span></a>
		</div>
		<div class="clear"></div>
		<br>
		<!-- 양식명칭 -->
		<div id="viewTitle" class="form_title" align="center">
			<h6>비자 발급 신청</h6>
		</div>
		<!-- //양식명칭 -->
		<br> <br>
		<!--결재선-->
		<div id="appr_line2">
			<table id="right_signln" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr class="signln_pos">
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
					</tr>
					<tr class="signln_dt">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--//결재선-->
		<div class="clear"></div>
		<br>
		<br>

		<div class="list_st1">
			<h3>문서정보</h3>
			<table id="docInfo">
				<tr id="errMsgTr" style="display:none">
					<td class="tit" style="width:10%">에러내역</td>
					<td class="inpt" style="width:90%" colspan="3">
						<span id="errMsg"></span>
					</td>
				</tr>
				<tr id="prtrMsgTr" style="display:none">
					<td class="tit" style="width:10%">반려사유</td>
					<td class="inpt" style="width:90%" colspan="3">
						<span id="prtrMsg" style="color:red; font-weight:bold;"></span>
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">문서 번호</td>
					<td class="inpt" style="width: 90%" colspan="3" id="docNo"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">작성자</td>
					<td class="inpt" style="width: 40%" id="drafter"></td>
					<td class="tit" style="width: 10%">소속팀</td>
					<td class="inpt" style="width: 40%" id="drafterOrgNm"></td>
				</tr>

				<!-- IF 협의자 존재 -->
				<tr id="signAssistor">
					<td class="tit">협의자</td>
					<td class="inpt" id="signAssistorVal" colspan="3"></td>
				</tr>
				<!-- //IF 협의자 존재 -->
			</table>
			<BR>
			<h3>
				<span>SAP 예산집행</span>
			</h3>
			<table id="docBugtInfo">
				<tr>
					<td class="tit" style="width: 10%">경비구분</td>
					<td class="inpt" style="width: 30%"><span id="bdgtType"></span>
						<a href="javascript:;" class="button-light" id="btnBdgtSelect">예산번호
							선택</a><span id="VTCodeSpan" style="display: none;"> / 경비이체코드 : <input
							type="text" id="vTcode" style="width:20%;"></span>
					<td class="tit" style="width: 10%">예산번호</td>
					<td class="inpt" style="width: 40%" id="Aufnr"></td>
				</tr>
			</table>
			<div id="dSignDiv"
				style="color: red; font-weight: bold; display: none;">※ 예산담당팀과
				사전협의 요망 (사진협의 미진행시 결재/자금집행 지연)</div>
			<div class="clear">&nbsp;</div>
			<h3>비자 </h3>
			<div id="visitNatRiskComm" style="display:none;">
				<div>
				<BR>&nbsp;
				</div>
				<div style="color:red; font-weight:bold;">
					※ 예외적여권사용허가 신청 서류
						첨부 필요( <a href="${contextPath}/common/document/cmas/EtcVisaInfo.zip">
							<img src="${contextPath}/common/images/zip.png"><font style="color:red; font-weight:bold;">예외적여권 사용허가 안내 및 신청서류)</font></a>
				</div>
			</div>
			<table id="visaAppnInfo">
				<tr>
					<td class="tit">신청구분</td>
					<td class="inpt"><select name="appnCls" style="width: 70%">
							<option value="A" selected="selected">출장</option>
							<option value="B">파견</option>
							<option value="C">부임</option>
							<option value="D">번역/공증</option>
					</select></td>
					<td class="tit">국가 </td>
					<td class="inpt"><input type="text" name="visitNat1"
						style="text-align: center"></td>
					<td class="inpt"><input type="text" name="visitNat2"
						style="text-align: center"></td>
					<td class="inpt"><input type="text" name="visitNat3"
						style="text-align: center"></td>
				</tr>

				<tr>
					<td class="tit" style="width: 20%">출국예정일</td>
					<td class="inpt" style="width: 30%"><input type="text"
						name="departScdDd"></td>
					<td class="tit" style="width: 20%">도시/현장</td>
					<td class="inpt"><input type="text" name="visitArea1"
						style="text-align: center"></td>
					<td class="inpt"><input type="text" name="visitArea2"
						style="text-align: center"></td>
					<td class="inpt"><input type="text" name="visitArea3"
						style="text-align: center"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">방문 목적</td>
					<td class="inpt" style="width: 40%" colspan="5"><input name="tPurp" type="text" style="width: 95%" maxlength="40"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">비고</td>
					<td class="inpt" style="width: 90%" colspan="5"><input name="tRemark" type="text" style="width: 95%" maxlength="40"></td>
				</tr>
				<tr>
						<td class="tit">비자접수일</td>
						<td class="inpt" id="submitYmd"></td>
						<td class="tit">발급예정일</td>
						<td class="inpt" id="preissueYmd"></td>
						<td class="tit">비자발급일</td>
						<td class="inpt" id="issueYmd"></td>
				</tr>

			</table>
			<BR>
			<table id="gInfo">
				<tr>
					<td>
						<h3>일반정보.</h3>
					</td>
				</tr>
				<tr id="gInfoComm" style="display:none;">
					<td colspan="5">
						<div>
							<div style="color:#457090; font-weight:bold;">
							※ 협력업체의 경우 개인정보 활용 동의서 서명본 첨부 필요( <a href="${contextPath}/common/document/cmas/PersonGuide.doc">
							<img src="${contextPath}/common/images/doc.png" ><font style="color:#457090; font-weight:bold;">개인정보활용동의서</font></a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">출장자</td>
					<td class="inpt" style="width: 35%"><span id="tripUserId2"
						style="display: inline-block; width: 150px;height: 22px"></span></td>
					<td class="tit" style="width: 45%;text-align: left;" colspan="3">&nbsp;&nbsp;<input type="checkbox"
						id="extnlPerYn2" onclick=chkExtSt(this);>&nbsp;외부인여부</td>
				</tr>
				<tr>
					<!-- td class="tit" style="width: 10%">주민등록번호</td>
					<td class="inpt" style="width: 40%"><input name="userSsno2"
						type="text" style="width: 95%" maxlength="20"></td -->
					<td class="tit" style="width: 10%">출장자 소속</td>
					<td class="inpt" style="width: 25%"><input
						name="tripUserOrgNm2" type="text" style="width: 95%"
						maxlength="20"></td>
					<td class="tit" style="width: 10%">휴대전화번호</td>
					<td class="inpt" style="width: 40%" colspan="2"><input
						name="mphoneNo2" type="text" style="width: 95%" maxlength="20">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">결혼여부</td>
					<td class="inpt" style="width: 40%">
					<select name="marriType2" style="width:70%" onchange="marrSt();" >
						<option value="" selected="selected"></option>
						<option value="Y">기혼</option>
						<option value="N">미혼</option>
					</select>
					</td>
					<td class="tit" style="width: 10%">이메일 주소</td>
					<td class="inpt" style="width: 40%" colspan="2">
					<input name="email2" type="text" style="width: 95%" maxlength="50">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">본적</td>
					<td class="inpt" style="width: 90%" colspan="4"><input
						name="domi2" type="text" style="width: 98%" maxlength="50">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">현주소</td>
					<td class="inpt" style="width: 90%" colspan="4"><input
						name="curAddr2" type="text" style="width: 98%" maxlength="50">
					</td>
				</tr>
			</table>
			<table id="generalInfo">
				<tr>
					<td><h3>일반정보</h3></td>
				</tr>
				<tr id="gInfoComm1" style="display:none;">
					<td colspan="5">
						<div>
							<div style="color:#0000ff; font-weight:bold;">
							※ 협력업체 및 e-hr 미등록 직원의 경우 개인정보 활용 동의서 서명본 첨부 필요( <a href="${contextPath}/common/document/cmas/PersonGuide.doc">
							<img src="${contextPath}/common/images/doc.png" ><font style="color:#0000ff; font-weight:bold;">개인정보활용동의서  )</font></a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">출장자</td>
					<td class="inpt" style="width: 35%"><span id="tripUserId"
						style="display: inline-block; width: 150px; height: 22px"></span></td>
					<td class="tit" style="width: 45%; text-align: left;" colspan="3">&nbsp;&nbsp;<input type="checkbox"
						id="extnlPerYn" onclick="chkExtSt(this);">&nbsp;외부인여부</td>
				</tr>
				<tr>
					<!--td class="tit" style="width: 10%">주민등록번호</td>
					<td class="inpt" style="width: 40%"><input name="userSsno"
						type="text" style="width: 95%" maxlength="20"></td -->
					<td class="tit" style="width: 10%">출장자 소속</td>
					<td class="inpt" style="width: 25%"><input
						name="tripUserOrgNm" type="text" style="width: 95%" maxlength="20">
					<td class="tit" style="width: 10%">휴대전화번호</td>
					<td class="inpt" style="width: 40%" colspan="2"><input
						name="mphoneNo" type="text" style="width: 95%" maxlength="20">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">결혼여부</td>
					<td class="inpt" style="width: 40%"><select id="marriType" name="marriType"
						style="width: 70%">
							<option value="" selected="selected"></option>
							<option value="Y">기혼</option>
							<option value="N">미혼</option>
					</select></td>
					<td class="tit" style="width: 10%">이메일 주소</td>
					<td class="inpt" style="width: 40%" colspan="2"><input
						name="email" type="text" style="width: 95%" maxlength="50">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">본적</td>
					<td class="inpt" style="width: 90%" colspan="4"><input
						name="domi" type="text" style="width: 98%" maxlength="50">
					</td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%">현주소</td>
					<td class="inpt" style="width: 90%" colspan="4"><input
						name="curAddr" type="text" style="width: 98%" maxlength="50">
					</td>
				</tr>
			</table>
			<table id="gInfoComm2" style="display:none;">
				<tr>
					<td>
						<div style="font-weight:bold;">※ 협력업체의 경우 "출장자 소속"란에 회사명 기재</div>
					</td>
				</tr>
			</table>

			<table id="fInfo">
				<tr>
					<td><h3>가족 정보.</h3></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">No.</td>
					<td class="tit" style="width: 25%; text-align: center">관계</td>
					<td class="tit" style="width: 25%; text-align: center">성명</td>
					<td class="tit" style="width: 25%; text-align: center">생년월일</td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">1</td>
					<td class="tit" style="width: 25%; text-align: center">부</td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="fatherNm2" type="text"
						style="width: 98%; text-align: center" maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="fatherBirth2" type="text"
						style="width: 98%; text-align: center" maxlength="8"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">2</td>

					<td class="tit" style="width: 25%; text-align: center">모</td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="motherNm2" type="text"
						style="width: 98%; text-align: center" maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="motherBirth2" type="text"
						style="width: 98%; text-align: center" maxlength="8"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">3</td>
					<td class="tit" style="width: 25%; text-align: center">배우자</td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="spouseNm2" type="text"
						style="width: 98%; text-align: center" maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="spouseBirth2" type="text"
						style="width: 98%; text-align: center;" maxlength="8"></td>
				</tr>
			</table>
			<table id="familyInfo">
				<tr>
					<td><h3>가족 정보</h3></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">No.</td>
					<td class="tit" style="width: 25%; text-align: center">관계</td>
					<td class="tit" style="width: 25%; text-align: center">성명</td>
					<td class="tit" style="width: 25%; text-align: center">생년월일</td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">1</td>
					<td class="tit" style="width: 25%; text-align: center">부</td>
					<td class="inpt" style="width: 25%; text-align: center"><input
						name="fatherNm" type="text" style="width: 98%; text-align: center"
						maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"
						id="fatherBirth"><input name="fatherBirth" type="text"
						style="width: 98%; text-align: center" maxlength="8"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">2</td>

					<td class="tit" style="width: 25%; text-align: center">모</td>
					<td class="inpt" style="width: 25%; text-align: center"
						id="motherNm"><input name="motherNm" type="text"
						style="width: 98%; text-align: center" maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"
						id="motherBirth"><input name="motherBirth" type="text"
						style="width: 98%; text-align: center" maxlength="8"></td>
				</tr>
				<tr>
					<td class="tit" style="width: 25%; text-align: center">3</td>
					<td class="tit" style="width: 25%; text-align: center">배우자</td>
					<td class="inpt" style="width: 25%; text-align: center"
						id="spouseNm"><input name="spouseNm" type="text"
						style="width: 98%; text-align: center" maxlength="20"></td>
					<td class="inpt" style="width: 25%; text-align: center"
						id="spouseBirth"><input name="spouseBirth" type="text"
						style="width: 98%; text-align: center" maxlength="8"></td>
				</tr>
			</table>

			<table id="mInfo">
				<tr>
					<td><h3>병역정보.</h3></td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%; text-align: center">군별</td>
					<td class="inpt" style="width: 15%"><input name="armyType2"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">계급</td>
					<td class="inpt" style="width: 15%"><input name="grade2"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">입대일</td>
					<td class="inpt" style="width: 15%"><input name="enlistYmd2"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">전역일</td>
					<td class="inpt" style="width: 15%"><input name="demobYmd2"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
				</tr>
			</table>
			<table id="militaryInfo">
				<tr>
					<td><h3>병역정보</h3></td>
				</tr>
				<tr>
					<td class="tit" style="width: 10%; text-align: center">군별</td>
					<td class="inpt" style="width: 15%"><input name="armyType"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">계급</td>
					<td class="inpt" style="width: 15%"><input name="grade"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">입대일</td>
					<td class="inpt" style="width: 15%"><input name="enlistYmd"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
					<td class="tit" style="width: 10%; text-align: center">전역일</td>
					<td class="inpt" style="width: 15%"><input name="demobYmd"
						type="text" style="width: 98%; text-align: center" maxlength="8">
					</td>
				</tr>
			</table>
			<BR>
			<h3>참고/요청사항</h3>
			<table id="fyi">
				<tr>
					<td class="tit" style="text-align:left;"><font color="#000000">※ 협조의뢰 후 총무팀 장준용 매니저(T.1884)앞 여권 및 구비 서류 제출 요망 <br>
					※ 여행금지국가 비자 신청 시 예외적여권사용허가 신청 서류 첨부 필수(보유시 예외적여권사용허가서 스캔본 첨부)</font><br>
						<font color="#0000ff">※ 첨부파일 추가시 저장(필수) → 협조의뢰</font> <br>
						<font color="#000000">※ 비자담당자 : 총무팀 장준용 매니저(T.1884)</font><br>
					</td>
				</tr>
			</table>

			<table>
				<tr id="ghrTr" style="display:none">
					<td class="tit" style="width:10%">담당자 의견</td>
					<td class="inpt" style="width:90%" colspan="4">
						<textarea id="ghrComment" style="width:95%; height:80px" readonly="readonly"></textarea>
					</td>
				</tr>
			</table>

		</div>
		<BR>
		<div class="clear"></div>
		<!--첨부파일/문서첨부-->
		<div class="box">
			<h3>
				<span>첨부 파일</span>
			</h3>
			<div id="atchFile">
				<div id="table" name="fileComponent" class="upload"></div>
			</div>
		</div>
	</div>
	<!--//CONTENTS-->
	<div class="bottom_bg">
		<span class="left"></span><span class="right"></span>
	</div>
</body>
</html>