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
<head><title>SSO ȯ�漳��</title></head>
<body>
<form id="from1">
    <table align="center" border="0" cellpadding="=0" cellspacing="0" width="100%">
        <tr>
            <td valign="top">
                <table border="0" cellpadding="0" cellspacing="0" width="120">
                    <tr class="text-blackgul">
                        <td align="center" height="24" width="120">SSO ȯ�漳��</td>
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
                                                            <td class="td-property" nowrap>&nbsp;����� ID �Է� �±׸�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.iDTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;�����ȣ �Է� �±׸�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.pwdTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;�ڰ����� ���� �±׸�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.credentialTypeTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� URL �±׸�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.returnURLTagName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;�α׿� ��û URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.logonPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� �α׿� ��û URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.secureLogonPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;�α׿��� ��û URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.logoffPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� �˻� ��û URL</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.authCheckPage()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;�߾����� ������</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.providerDomain()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;������û IP �˻� ����</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isClientIPCheck()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� ��ȿ �Ⱓ</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.timeOut()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� ���� �ð� ��� ����</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.expireEnabled()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� ���� �ð�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.expireTime()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� ���� ����</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isSession()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;����ó�� �ɼ�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sessionProcessOption()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;���� Ÿ�Ӿƿ�</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sesstionTimeOut()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO ������</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomain()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO ������ ���� ��Ű��</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomainTokenName()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO ���� ����</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.isSSO()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO ������ ��Ű</td>
                                                            <td class="td-value">&nbsp;&nbsp;<%=SSOConfig.sSODomainAddCookie()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td-property" nowrap>&nbsp;SSO ������ ��Ű���</td>
                                                            <td class="td-value">
                                                                <table width="500px">
                                                                    <tr>
                                                                        <td>
                                                                            <table bgcolor="#E0E0E0" border="0" cellpadding="0" cellspacing="1" width="50%">
                                                                                <tr bgcolor="#C7DDF3" class="text-black">
                                                                                    <td align="center" height="20" nowrap width="40%">��Ű��</td>
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
                                                            <td class="td-property" nowrap>&nbsp;SSO ����Ʈ��</td>
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