ALTER TABLE `tbl_customer`   
	CHANGE `rank` `rank` INT(11) NULL;

ALTER TABLE `tbl_customer`   
	CHANGE `email` `email` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `mobile` `mobile` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL;

ALTER TABLE `tbl_order`   
	CHANGE `discount` `discount` DECIMAL(16,2) NULL,
	CHANGE `customer_name` `customer_name` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `customer_rank` `customer_rank` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `customer_address` `customer_address` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `customer_mobile` `customer_mobile` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `customer_email` `customer_email` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL,
	CHANGE `user_id` `user_id` BIGINT(20) NULL;

ALTER TABLE `payment`   
	CHANGE `customer_name` `customer_name` VARCHAR(255) CHARSET latin1 COLLATE latin1_swedish_ci NULL;
