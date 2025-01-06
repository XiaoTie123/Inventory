package com.applewear.crm.dto.admin;

import java.util.List;
import com.applewear.crm.dto.search.SearchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminSearchDTO extends SearchDTO {

	private Long adminId;
	private String adminName;
	private String email;
	private Long roleId;
	private List<AdminDTO> data;


}
