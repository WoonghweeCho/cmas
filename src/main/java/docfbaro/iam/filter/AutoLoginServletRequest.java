package docfbaro.iam.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : DoCFBaro
 * 프로그램 :
 * 설    명 : 자동 로그인 수행을 위한 위한 MockRequest, 클라이언트의 요청 객체와는 별도로 생성되어 세션범위의 인증객체를 생성한다.
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
public class AutoLoginServletRequest extends HttpServletRequestWrapper {

	private HttpServletRequest originRequest;

	/**
	 * <pre>
	 * 시스템 인증을 위한 MockRequest를 생성한다.
	 * </pre>
	 *
	 * @param filterProcessingUrl securityFilterChain에서 인증을 수행하는 virtual url
	 * @param usernameParameter
	 * @param username 사용자ID
	 * @param passwordParameter
	 * @param password password
	 */
	public AutoLoginServletRequest(HttpServletRequest oRequest, String filterProcessingUrl, String usernameParameter, String username, String passwordParameter, String password) {
		super(new MockHttpServletRequest());

		MockHttpServletRequest request = (MockHttpServletRequest) getRequest();

		request.setMethod(HttpMethod.POST.name());
		request.setRequestURI(filterProcessingUrl);
		request.setSession(new MockHttpSession());

		request.addParameter(usernameParameter, username);
		request.addParameter(passwordParameter, password);
		this.originRequest = oRequest;
	}

	public HttpServletRequest getOriginRequest() {
		return originRequest;
	}

	@Override
	public String getRemoteAddr() {
		return originRequest.getRemoteAddr();
	}

	@Override
	public HttpSession getSession(boolean create) {
		return originRequest.getSession(create);
	}

	@Override
	public HttpSession getSession() {
		return originRequest.getSession();
	}

}
