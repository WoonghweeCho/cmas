<%@ page import="com.nets.sso.agent.ErrorMessage"%>
<%@ page import="com.nets.sso.agent.SSOConfig" %>
<%@ page import="com.nets.sso.agent.ErrorCode" %>
<%@ page import="java.util.ResourceBundle" %>

<%@ page contentType="text/html; charset=euc-kr"%>
<%
    String responseBody = "";
    try
    {
        //입력 파라메터 검증
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
            if (notifyType.equals("1")) // 환경 설정 캐시 변경요청에 대한 처리를 수행한(ReloadConfig)
            {
                //성공시에만 업데이트 가능하게 구성
                SSOConfig.setSSOAvailable(true);
                SSOConfig.initialized = false;
                SSOConfig.updateSSOConfig();
                SSOConfig.updateMessage();
            }
            else if (notifyType.equals("2")) // 환경 설정 캐시 삭제 요청에 대한 처리를 수행함(Disabled)
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