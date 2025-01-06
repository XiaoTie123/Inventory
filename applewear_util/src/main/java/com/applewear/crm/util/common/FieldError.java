package com.applewear.crm.util.common;

import com.applewear.crm.util.enums.FieldErrorCode;
import com.applewear.crm.util.enums.LangCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldError {

	private int code;
	private String message;

	public FieldError(FieldErrorCode errorCode) {
		if (errorCode != null) {
			this.code = errorCode.getCode();
			if(CommonUtil.validString(errorCode.getDesc()) && errorCode.getDesc().contains(".")) {
				this.message = PropUtil.readMessageValue(errorCode.getDesc(), LangCode.EN);
			} else {
				this.message = errorCode.getDesc();
			}
		}
	}

	public FieldError(FieldErrorCode errorCode, LangCode lang) {
		if (errorCode != null) {
			this.code = errorCode.getCode();
			if(CommonUtil.validString(errorCode.getDesc()) && errorCode.getDesc().contains(".")) {
				this.message = PropUtil.readMessageValue(errorCode.getDesc(), lang);
			} else {
				this.message = errorCode.getDesc();
			}
		}
	}
}
