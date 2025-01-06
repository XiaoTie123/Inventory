package com.applewear.crm.util.enums;

import com.applewear.crm.util.common.CommonUtil;

public enum LangCode {

	EN("EN"), MM("MM");

	private String code;

	private LangCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static LangCode get(String code) {
		if (!CommonUtil.validString(code)) {
			return LangCode.EN;
		}
		for (LangCode s : values()) {
			if (s.getCode().trim().equalsIgnoreCase(code.trim())) {
				return s;
			}
		}
		return LangCode.EN;
	}
	
	public static boolean isMM(String code) {
		if(!CommonUtil.validString(code)) {
			return false;
		}
		return MM.getCode().trim().equalsIgnoreCase(code.trim());
	}

}
