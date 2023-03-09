package com.dwenc.cmas.common.login.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Companion Class
 *
 * @author PASCAL
 *
 */
public class AuthenticationListenerUtils {

	public final static String UNKNOWN = "unknown";

	public static String getRemoteAddress(UsernamePasswordAuthenticationToken token) {
		if (token.getDetails() instanceof WebAuthenticationDetails) {
			return ((WebAuthenticationDetails) token.getDetails()).getRemoteAddress();
		}
		return AuthenticationListenerUtils.UNKNOWN;
	}

	public enum Status {
		SUCCESS, NOAUTH, FAILURE
	}

	public static String getSessionId(UsernamePasswordAuthenticationToken token) {
		if (token.getDetails() instanceof WebAuthenticationDetails) {
			return ((WebAuthenticationDetails) token.getDetails()).getSessionId();
		}
		return AuthenticationListenerUtils.UNKNOWN;
	}
}
