package com.applewear.crm.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Column(name = "created_at")
	private Date createdTime;

	@Column(name = "updated_at")
	private Date updatedTime;
}
