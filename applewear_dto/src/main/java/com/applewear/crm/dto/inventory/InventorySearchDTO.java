package com.applewear.crm.dto.inventory;

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
public class InventorySearchDTO extends SearchDTO {

	private String productCode;

	private String productName;

	private List<InventoryDTO> data;

}
