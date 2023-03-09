<%@ page import="com.nets.sso.agent.ErrorMessage"%>
<%@ page import="com.nets.sso.agent.SSOConfig" %>
<%@ page import="com.nets.sso.agent.ErrorCode" %>
<%@ page import="java.util.ResourceBundle" %>

<%@ page contentType="text/html; charset=euc-kr"%>
<%
    String responseBody = "";
    try
    {
        //�Է� �Ķ���� ����
        String providerDomain = request.getParameter("providerdomain");
        String notifyType = request.getParameter("notifyType");
        SSOConfig.request = request;
        ResourceBundle res = ResourceBundle.getBundle("sso");

        if(providerDomain ==  null || providerDomain.equals("") || !providerDomain.equals(res.getString("SSO_PROVIDER_DOMAIN")))
        {
            responseBody =ErrorMessage.FAILFLAG + ErrorMessage.MSGSEP + ErrorMessage.getMessage(ErrorCode.ERR_INVALID_SSOPROVIDER_DOMAIN);
        }
        else if(notifyType == null || notifyType.equals(""))
        {
            responseBody = ErrorMessage.FAILFLAG + ErrorMessage.MSGSEP + ErrorMessage.getMessage(ErrorCode.ERR_INVALID_NOTIFY_TYPE);
        }
        else
        {
            if (notifyType.equals("1")) // ȯ�� ���� ĳ�� �����û�� ���� ó���� ������(ReloadConfig)
            {
                //�����ÿ��� ������Ʈ �����ϰ� ����
                SSOConfig.setSSOAvailable(true);
                SSOConfig.initialized = false;
                SSOConfig.updateSSOConfig();
                SSOConfig.updateMessage();
            }
            else if (notifyType.equals("2")) // ȯ�� ���� ĳ�� ���� ��û�� ���� ó���� ������(Disabled)
            {
                SSOConfig.dsiabledSSOConfig();
            }
            responseBody = ErrorMessage.SUCCESSFLAG + ErrorMessage.MSGSEP + ErrorMessage.getMessage(ErrorCode.NO_ERR);
        }
    }
    catch (Exception ex) {
        String msg = ex.toString();
        msg = msg.replace('\n', ' ');
        msg = msg.replace('\r', ' ');
        msg = msg.replace('"', ' ');
        responseBody  = ErrorMessage.FAILFLAG + ErrorMessage.MSGSEP + msg;
    }
%>
<html>
<head><title></title></head>
<body>
<%=responseBody%>
</body>
</html>