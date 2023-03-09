package com.dwenc.cmas.common.security.definition;

import jcf.iam.core.jdbc.authentication.AuthorityMapping;
import jcf.query.core.evaluator.definition.SelectStatement;

public class AuthorityDefinition extends AuthorityMapping implements
		SelectStatement {
	private String username;
	private String authority;

	public String getUsername() {
		return this.username;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getQuery() {
		StringBuilder builder = new StringBuilder();
		builder.append("/* docfbaro.AuthorityDefinition */          \n");
		builder.append("SELECT  USER_ID username                    \n");
		builder.append("     ,  PRIV_CD authority                   \n");
		builder.append("  FROM  V_CO_PRIV_USER                      \n");
		builder.append(" WHERE  SYS_CD  = 'icms' \n");
		builder.append("   AND  USER_ID = '" + getUsername() + "'");
		return builder.toString();
	}
}
