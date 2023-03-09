package docfbaro.iam.handler;

import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jcf.iam.core.authentication.ria.RiaParameterProcessingHandler;
import jcf.iam.core.filter.request.ExternalAuthenticationToken;
import jcf.sua.ux.xplatform.dataset.XplatformDataSetReader;
import jcf.sua.ux.xplatform.mvc.XplatformRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : DoCFBaro
 * 프로그램 :
 * 설    명 : SpringSecurity의 기본적인 구현은 표준웹에서 FormAction 형태로 전송된 요청을 처리하는 흐름으로 구현되어 있다.
 *         AJAX 요청으로 인증을 시도하는 경우 사용자의 ID/PASSWORD를 추출하여 SpringSecurity {@link UsernamePasswordAuthenticationFilter} 에서
 *         인식가능한 형태로 변경해 주어야 하며, 본 클래스에서 이와 관련한 작업을 수행한다.
 * 작 성 자 : 고강민
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class IntegratedAuthenticationParameterExtHandler implements RiaParameterProcessingHandler {

    /**
     * <pre>
     * AJAX 요청과 함께 전달된 인증 정보를 이용하여 인증 토큰을 생성한다.
     * </pre>
     *
     * @param request HttpServletRequest
     *
     * @return 인증토큰
     */
    public ExternalAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        XplatformRequest req = new XplatformRequest(new XplatformDataSetReader(request, null), null);

        String username = req.getParam("j_username");
        String password = req.getParam("j_password");

        // 웹페이지 인증 처리
        if(username == null || username.equals("")){
            username = request.getParameter("j_username");
            password = request.getParameter("j_password");

            HttpSession session = request.getSession();
            PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
            session.removeAttribute("__rsaPrivateKey__"); // 키의 재사용 방지

            try{
                if(privateKey == null){
                    // throw new RuntimeException("암호화 비밀키 정보를 찾을 수 없습니다.");
                } else {
                    username = decryptRsa(privateKey, username);
                    password = decryptRsa(privateKey, password);
                }
            }catch(Exception ex){
                return new ExternalAuthenticationToken(null, null);
            }
        }

        return new ExternalAuthenticationToken(username, password);
    }

    private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception{
        // System.out.println("will decrypt : " + securedValue);
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] encryptedBytes = hexToByteArray(securedValue);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedValue = new String(decryptedBytes, "utf-8");
        return decryptedValue;
    }

    // 16진 문자열을 byte 배열로 변환한다.
    public static byte[] hexToByteArray(String hex){
        if(hex == null || hex.length() % 2 != 0){
            return new byte[] {};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < hex.length(); i += 2){
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int)Math.floor(i / 2)] = value;
        }
        return bytes;
    }

}
