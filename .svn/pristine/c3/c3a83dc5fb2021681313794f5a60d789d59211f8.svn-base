package docfbaro.sua.exception;

import jcf.sua.mvc.MciExceptionMessageWriter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.StringUtils;

import com.dwenc.cmas.common.sysMng.service.support.SysMngUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : DoCFBaro
 * 프로그램 :
 * 설    명 : 발생한 예외 메세지를 적절한 형태로 가공하여 클라이언트로 전송한다.
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
 * @see docfbaro.sua.exception.BusinessException
 */
public class BusinessExceptionExtMessageWriter implements MciExceptionMessageWriter {

	/**
	 * <pre>
	 * ExceptionMessageWriter에서 처리 가능한 예외를 정의한다.
	 * </pre>
	 *
	 * @param exception 예외
	 */
	public boolean accept(Exception exception) {
		return exception instanceof BusinessException;
	}

	/**
	 * <pre>
	 * 예외메세지를 생성한다.
	 * </pre>
	 *
	 * @param messageSourceAccessor message resource bundle을 사용하기 위한 편의 클래스
	 * @param exception 예외
	 */
	public String buildExceptionMessage(MessageSourceAccessor messageSourceAccessor, Exception exception) {
		String code = ((BusinessException) exception).getCode();
		String defaultMessage = ((BusinessException) exception).getDefaultMessage();
		Object[] arguments = ((BusinessException) exception).getArguments();
		String message = code;

		// dwe.sysMng.exception.useYn = true
        SysMngUtil.sendErrorMsg(message, exception);

		if(messageSourceAccessor != null){
			try	{
				message = messageSourceAccessor.getMessage(code, arguments);
			}catch(Exception mex){
				message = defaultMessage;
			}

			if (!StringUtils.hasText(message)) {
				message = exception.getMessage();
			}
		}

		return String.format("%s//DETAIL//%s", message, ExceptionUtils.getFullStackTrace(exception));
	}

}
