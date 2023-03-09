<%@ page import="com.nets.sso.agent.CryptEncType"%>
<%@ page import="com.nets.sso.agent.SSOConfig"%>
<%@ page import="com.nets.sso.agent.ErrorMessage"%>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.nets.sso.agent.CookieInfo" %>
<%@ page contentType="text/html; charset=euc-kr"%>
<%
SSOConfig.request = request;
%>
<html>
<head><title>SSO 환경설정</title></head>
<body>
<form id="from1">
    <table align="center" border="0" cellpadding="=0" cellspacing="0" width="100%">
        <tr>
            <td valign="top">
                <table border="0" cellpadding="0" cellspacing="0" width="120">
                    <tr class="text-blackgul">
                        <td align="center" height="24" width="120">SSO 환경설정</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <table bgcolor="#074C91" border="0" cellpadding="01" cellspacing="1" style="height: 678px; width: 99%;">
                    <tr>
                        <td bgcolor="#FFFFFF" valign="top" width="100%">
                            <table border="0" cellspacing="6" width="100%">
                                <tr>
                                    <td valign="top">
                                        <table align="left" bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="1" width="100%">
                                            <tr>
                                                <td valign="top">
                                                    <table align="left" bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="1" width="100%">
                                                        <tr>
                                                            <td colspan="2" style="height: 2px; background-color: #CCCCCC;">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="height:10px;"></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-black" colspan="2">&nbsp;&nbsp;&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;사용자 ID 입력 태그명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.iDTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;비빌번호 입력 태그명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.pwdTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;자격증명 종류 태그명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.credentialTypeTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;리턴 URL 태그명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.returnURLTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;로그온 요청 URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.logonPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;보안 로그온 요청 URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.secureLogonPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;로그오프 요청 URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.logoffPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;인증 검사 요청 URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.authCheckPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;중앙인증 도메인</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.providerDomain()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;인증요청 IP 검사 여부</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isClientIPCheck()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;인증 유효 기간</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.timeOut()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;인증 만료 시간 사용 여부</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.expireEnabled()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;인증 만료 시간</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.expireTime()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;세션 관리 여부</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isSession()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;세션처리 옵션</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sessionProcessOption()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;세션 타임아웃</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sesstionTimeOut()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 도메인</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomain()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 도메인 인증 쿠키명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomainTokenName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 참여 여부</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isSSO()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 도메인 쿠키</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomainAddCookie()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 도메인 쿠키목록</td>
                                                            <td class="td-value">
                                                                <table width="500px">
                                                                    <tr>
                                                                        <td>
                                                                            <table bgcolor="#E0E0E0" border="0" cellpadding="0" cellspacing="1" width="50%">
                                                                                <tr bgcolor="#C7DDF3" class="text-black">
                                                                                    <td align="center" height="20" nowrap width="40%">쿠키명</td>
                                                                                </tr>
                                                                                <%
                                                                                    if ( SSOConfig.sSODomainCookieInfos() != null && SSOConfig.sSODomainCookieInfos().length > 0){
                                                                                        for (CookieInfo cookie : SSOConfig.sSODomainCookieInfos()) {
                                                                                %>
                                                                                <tr>
                                                                                    <td><%=cookie.cookieName()%></td>
                                                                                </tr>
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                    %>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO 사이트명</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.siteDomain()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="height:40px"></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>