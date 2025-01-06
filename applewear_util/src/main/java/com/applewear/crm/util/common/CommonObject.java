package com.applewear.crm.util.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonObject {

	private int code;
	private String desc;

	@Override
	public String toString() {
		return desc;
	}

}
