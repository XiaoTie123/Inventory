ALTER TABLE `tbl_orderdetail`   
	CHANGE `product_code` `product_code` VARCHAR(255) CHARSET utf8 COLLATE utf8_unicode_ci NULL;

ALTER TABLE `tbl_orderdetail`   
	CHANGE `trip_id` `trip_id` INT(15) NULL;
