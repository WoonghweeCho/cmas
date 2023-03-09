package docfbaro.sua.exception;

import java.sql.SQLException;
import java.util.Locale;

import jcf.iam.core.common.util.UserInfoHolder;
import jcf.sua.mvc.MciExceptionMessageWriter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;

import com.dwenc.cmas.common.sysMng.service.support.SysMngUtil;

import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : DoCFBaro
 * 프로그램 : OracleSQLExceptionMessageWriter
 * 설    명 : ORACLE SQL 관련 발생한 예외 메세지를 적절한 형태로 가공하여
 *           클라이언트로 전송한다.
 * 작 성 자 : 이재열
 * 작성일자 : 2012-03-15
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-03-15    이재열   최초 작성
 * ---------------------------------------------------------------
 * </pre>
 */
public class OracleSQLExceptionExtMessageWriter implements MciExceptionMessageWriter {

	@Override
	public boolean accept(Exception exception) {
		return (exception instanceof DataIntegrityViolationException) ||
			   (exception instanceof BadSqlGrammarException) ||
			   (exception instanceof UncategorizedSQLException);
	}

	@Override
	public String buildExceptionMessage(MessageSourceAccessor messageSourceAccessor, Exception exception) {
	    Locale locale = new Locale("ko", "KR");
	    if(UserInfoHolder.getUserInfo(UserDefinition.class) != null) {
	        UserDefinition userData = UserInfo.getUserInfo();
	        if(userData.getLoclCd().equals("en_US")) {
	            locale = new Locale("en", "US");
	        }
	    }
		String msg = "";
		if(locale.toString().equals("en_US")) {
		    msg = "Database-related error occurred. Please contact the help desk.";
		} else if(locale.equals("")) {
            msg = "데이타베이스 관련오류입니다. 전산담당자에게 문의바랍니다.";
		}

        // dwe.sysMng.exception.useYn = true
        SysMngUtil.sendErrorMsg(msg, exception);

		if (exception instanceof DataIntegrityViolationException) {
			DataIntegrityViolationException violationException = (DataIntegrityViolationException) exception;
			if (violationException.getRootCause() instanceof SQLException) {
				SQLException sqlException = (SQLException) violationException.getRootCause();

				int code = sqlException.getErrorCode();
				if(locale.toString().equals("en_US")) {
	                msg = "Database restrictions error occurred. Please contact the help desk.";
				} else {
	                msg = "데이타베이스 제약사항오류입니다. 전산담당자에게 문의바랍니다.";
				}
				switch (code) {
				case 1:
	                if(locale.toString().equals("en_US")) {
	                    msg = "Input data has been duplicated.";
	                } else {
	                    msg = "입력한 데이타가 중복되었습니다.";
	                }
					break;
				case 1400:
                    if(locale.toString().equals("en_US")) {
                        msg = "There is no value entered in the column defined as the required input. Please contact the help desk.";
                    } else {
                        msg = "필수입력으로 정의된 칼럼에 입력된값이 없습니다. 전산담당자에게 문의바랍니다.";
                    }
					break;
				case 1438:
                    if(locale.toString().equals("en_US")) {
                        msg = "The maximum value of the field size has been exceeded. Please contact the help desk.";
                    } else {
                        msg = "입력된값이 필드의 최대값 크기를 초과하였습니다. 전산담당자에게 문의바랍니다.";
                    }
					break;
				case 1861:
                    if(locale.toString().equals("en_US")) {
                        msg = "Does not match the data format of the input value. Please contact the help desk.";
                    } else {
                        msg = "입력된값의 자료형식이 맞지않습니다. 전산담당자에게 문의바랍니다.";
                    }
					break;
				case 2291:
                    if(locale.toString().equals("en_US")) {
                        msg = "Missing the parental data, this data can not be processed. Please contact the help desk.";
                    } else {
                        msg = "데이타무결성을 위배하였습니다(참조데이타없음). 전산담당자에게 문의바랍니다.";
                    }
					break;
				case 2292:
                    if(locale.toString().equals("en_US")) {
                        msg = "The referred data can not be deleted. 전산담당자에게 문의바랍니다.";
                    } else {
                        msg = "참조하는 데이타가 있어서 삭제처리할수 없습니다. 전산담당자에게 문의바랍니다.";
                    }
					break;
				default:
					break;
				}
				//return String.format("%s//DETAIL//%s", msg, sqlException.getLocalizedMessage());
			}
			return String.format("%s//DETAIL//%s", msg, ExceptionUtils.getFullStackTrace(exception));
		} else if (exception instanceof BadSqlGrammarException) {
            if(locale.toString().equals("en_US")) {
                msg = "Database grammatical error occurred. Please contact the help desk.";
            } else {
                msg = "데이타베이스 관련 문법오류입니다. 전산담당자에게 문의바랍니다.";
            }
			return String.format("%s//DETAIL//%s", msg, ExceptionUtils.getFullStackTrace(exception));
		} else if (exception instanceof UncategorizedSQLException) {
			UncategorizedSQLException uncategorizedSQLException = (UncategorizedSQLException) exception;
			if (uncategorizedSQLException.getRootCause() instanceof SQLException) {
				SQLException sqlException = (SQLException) uncategorizedSQLException.getRootCause();
				int code = sqlException.getErrorCode();
	            if(locale.toString().equals("en_US")) {
	                msg = "Database other error occurred. Please contact the help desk.";
	            } else {
	                msg = "데이타베이스 관련 기타오류입니다. 전산담당자에게 문의바랍니다.";
	            }
				switch (code) {
				case 12899:
	                if(locale.toString().equals("en_US")) {
	                    msg = "The entered value is the maximum value of the field size has been exceeded. Please contact the help desk.";
	                } else {
	                    msg = "입력된값이 필드의 최대값 크기를 초과하였습니다. 전산담당자에게 문의바랍니다.";
	                }
					break;
				default:
					break;
				}
				//return String.format("%s//DETAIL//%s", msg, sqlException.getLocalizedMessage());
			}
			return String.format("%s//DETAIL//%s", msg, ExceptionUtils.getFullStackTrace(exception));
		}
		return String.format("%s//DETAIL//%s", ((SQLException) exception).getLocalizedMessage(), ExceptionUtils.getFullStackTrace(exception));
	}

}