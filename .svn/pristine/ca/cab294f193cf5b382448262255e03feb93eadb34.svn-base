package docfbaro.iam.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcf.iam.core.filter.handler.AbstractIamAccessDeniedHandler;
import jcf.sua.dataset.DataSetWriter;
import jcf.sua.mvc.MciDataSetAccessor;
import jcf.sua.mvc.MciResponse;
import jcf.sua.ux.json.dataset.JsonDataSetWriter;
import jcf.sua.ux.json.mvc.JsonResponse;
import jcf.sua.ux.xplatform.dataset.XplatformDataSetWriter;
import jcf.sua.ux.xplatform.mvc.XplatformResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;

import com.dwenc.cmas.common.metaData.service.support.DataSourcePool;
import com.dwenc.cmas.common.utils.ConfigUtil;

import docfbaro.iam.UserInfo;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : DoCFBaro - IAM
 * 프로그램 : IntegratedAccessDeniedHandler
 * 설    명 : 인증에 성공한 사용자(익명 사용자 포함)에 한해 인가 작업이 수행되며,
 *           인증된 사용자가 접근한 자원에 대한 인가가 거부된 경우에 수행되어야 하는
 *           후처리 작업들을 정의한다.
 *           기본구현(표준웹)은 정의된 예외페이지(ex.403.jsp)로의 redirection 이며,
 *           AJAX 요청인 경우 UI로 전달될 문자열을 생성하여 반환한다.
 * 작 성 자 : 고강민
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01    고강민    최초 작성
 * 2012-03-19    이재열    JSON/Xplatform 메세지 처리 추가
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
public class IntegratedAccessDeniedExtHandler extends AbstractIamAccessDeniedHandler {

	/**
	 * <pre>
	 * 표준웹과 AJAX 요청을 구분하여 처리하기 위해, 요청객체가 AJAX 요청인지 판단하여 반환한다.
	 * </pre>
	 *
	 * @param request HttpServletRequest
	 * @return AJAX 요청인 경우 true
	 */
	@Override
	protected boolean isMciRequest(HttpServletRequest request) {
		return isJSONRequest(request)||isXplatformRequest(request);//표준 웹 REQUEST인지 체크
	}

	/**
	 * <pre>
	 * 사용자가 허용되지 않는 자원에 접근한 경우, 발생된 예외를 처리하기 위한 별도의 로직을 작성하기 위한 메소드이며,
	 * 필요에 따라 재정의하여 사용한다.
	 * 표준웹을 처리하는 흐름은 상위 클래스{@link AbstractIamAccessDeniedHandler}에 정의되어 있으며,
	 * AJAX 요청인 경우만은 고려하여 로직을 기술한다.
	 * </pre>
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param accessDeniedException 인가가 거부되었을 경우 발생하는 예외
	 */
	@Override
	protected void handleMciRequest(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {

		MciResponse mciResponse = null;
		DataSetWriter writer = null;

		if (isJSONRequest(request)) {//JSON 통신
			mciResponse = new JsonResponse();
			writer = new JsonDataSetWriter(response, (MciDataSetAccessor) mciResponse);
		} else if (isXplatformRequest(request)) {//XPlatform 통신인 경우
			mciResponse = new XplatformResponse();
			writer = new XplatformDataSetWriter(request, response, null, (MciDataSetAccessor) mciResponse);
		}

		//String exceptionMessage = accessDeniedException.getLocalizedMessage();
		//TODO 메세지 처리 공통 적용 필요
		try {
		    insertAccess(request);
	        ((MciDataSetAccessor) mciResponse).setExceptionMessage("접근권한이 없습니다.");
	        writer.write();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
	}

    private Connection conn = null;
    private Properties appProperties;

    // 인가 정보 입력
    public void insertAccess(HttpServletRequest request) {
        String menuId = UserInfo.getUserInfo().getMenuId();
        if(menuId.equals("")) return;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getJDBCConnection("cmas");
            String sql = "INSERT INTO CO_MENU_CTG_DEM_URL";
            sql += "             (";
            sql += "                MENU_ID";
            sql += "              , DEM_URL";
            sql += "              , REM";
            sql += "              , FST_REG_DT";
            sql += "              , FST_REG_USER_ID";
            sql += "              , FNL_EDIT_DT";
            sql += "              , FNL_EDIT_USER_ID";
            sql += " , OGG_CD";
            sql += " , OGG_TIME";
            sql += "              )";
            sql += "      VALUES (";
            sql += "                '" + menuId + "'";
            sql += "              , '" + request.getRequestURI()+ "'";
            sql += "              , ''";
            sql += "              , sysdate";
            sql += "              , 'dweadm'";
            sql += "              , sysdate";
            sql += "              , 'dweadm'";
            sql += ", '100'";
            sql += ", TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS'), 'YYYYMMDDHH24MISSFF')";
            sql += ")";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {try {rs.close();} catch (Exception e) {}}
            if (ps != null) {try {ps.close();} catch (Exception e) {}}
            if (conn != null) {try {conn.close();} catch (Exception e) {}}
        }
    }

    // 컨넥션 정보 조회
    public Connection getJDBCConnection(String spec) throws Exception {
        if(appProperties == null) appProperties = ConfigUtil.getProperties(appProperties);
        appProperties.put("spec", spec);
        try {
            conn = DataSourcePool.getInstance().getConnection(appProperties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

	/**
	 * Request 가 JSON 통신 Request 인지 체크
	 * @param request
	 * @return JSON Request 여부
	 */
	protected boolean isJSONRequest(HttpServletRequest request) {
		String contentType = request.getHeader("Content-Type");
		String accept = request.getHeader("Accept");
		if ((StringUtils.hasText(contentType) && contentType.indexOf("application/json+sua") != -1) || (StringUtils.hasText(accept) && accept.indexOf("application/json+sua") != -1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Request 가 Xplatform에서 온 Request 인지 체크
	 * @param request
	 * @return Xplatform Request 여부
	 */
	protected boolean isXplatformRequest(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if (agent != null && (agent.indexOf("XPLATFORM") != -1)) {
			return true;
		} else {
			return false;
		}
	}
}