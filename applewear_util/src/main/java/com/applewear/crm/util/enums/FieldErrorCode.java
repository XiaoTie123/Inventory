package com.applewear.crm.util.enums;

import com.applewear.crm.util.common.FieldError;

public enum FieldErrorCode {

	SUCCESS(1, ""), GENERAL(-1, "general.application.exception"),

	MULTIPLE_ERROR(1000, "invalid.input.values"), INVALID_SESSION(1001, "invalid.session"),
	USER_ID_REQUIRED(1002, "msg.user.id.required"), PASSWORD_REQUIRED(1003, "msg.password.required"),
	SESSION_ID_REQUIRED(1004, "msg.session.id.required"), USER_ID_INVALID_NUMBER(1005, "msg.user.id.invalid.number"),
	VEHICLE_PREFIX_REQUIRED(1006, "vehicle.prefix.is.required"), VEHICLE_NO_REQUIRED(1087, "vehicle.no.required"),

	;

	private int code;
	private String desc;

	FieldErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public FieldError toFieldError() {
		FieldError error = new FieldError();
		error.setCode(code);
		error.setMessage(desc);
		return error;
	}
}
