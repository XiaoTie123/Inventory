package com.applewear.crm.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

	private Integer draw; // Nothing special. just simply response back to client (for DataTable security reason)
	private Integer start;
	private Integer length;
	private Long recordsTotal;
	private Long recordsFiltered;

}
