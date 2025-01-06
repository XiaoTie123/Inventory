package com.applewear.crm.util.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.applewear.crm.util.common.CommonObject;
import com.applewear.crm.util.common.CommonUtil;

import lombok.Getter;

@Getter
public enum RecordStatus {

	ACTIVE(1, "Active"), INACTIVE(2, "Inactive");

	private int code;
	private String desc;

	private RecordStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescriptionByCode(Integer code) {

		if (!CommonUtil.validInteger(code)) {
			return "";
		}

		for (RecordStatus s : values()) {
			if (s.getCode() == code) {
				return s.getDesc();
			}
		}

		return "";

	}

	public static List<CommonObject> getAll() {
		return Stream.of(values()).map(s -> new CommonObject(s.getCode(), s.getDesc())).collect(Collectors.toList());
	}

}
