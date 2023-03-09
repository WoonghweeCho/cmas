package com.dwenc.cmas.common.tlds;

import net.sf.json.JSONObject;

import org.apache.taglibs.standard.tag.common.core.SetSupport;

public class SetJsonTag extends SetSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setValue(Object value) {
		this.value = JSONObject.fromObject(value);
		this.valueSpecified = true;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
