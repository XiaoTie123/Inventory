package com.applewear.crm.dto.payment_method;

import com.applewear.crm.entity.payment_method.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {

	private Long paymentMethodId;

	private String name;

	public PaymentMethodDTO(PaymentMethod entity) {

		if (entity != null) {

			this.paymentMethodId = entity.getId();

			this.name = entity.getName();

		}

	}

}
