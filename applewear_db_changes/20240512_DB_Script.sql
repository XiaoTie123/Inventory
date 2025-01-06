ALTER TABLE `tbl_rank`   
	CHANGE `rank_id` `rank_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	CHANGE `discount` `discount` DECIMAL(16,2) NOT NULL,
  CHARSET=utf8mb4, COLLATE=utf8mb4_general_ci;

ALTER TABLE `users`   
	CHANGE `id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	ADD COLUMN `sessionId` VARCHAR(255) NULL AFTER `role`;

ALTER TABLE `tbl_paymentmethod`   
	CHANGE `paymentmethod_id` `paymentmethod_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE `tbl_product`   
	CHANGE `product_id` `product_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	CHANGE `brand_id` `brand_id` BIGINT(20) NOT NULL,
	CHANGE `category_id` `category_id` BIGINT(20) NOT NULL,
	CHANGE `size_id` `size_id` BIGINT(20) NOT NULL,
	CHANGE `user_id` `user_id` BIGINT(20) NOT NULL,
	CHANGE `price` `price` DECIMAL(16,2) NOT NULL,
	CHANGE `buying_price` `buying_price` DECIMAL(16,2) NOT NULL,
	CHANGE `selling_price` `selling_price` DECIMAL(16,2) NOT NULL;
	
ALTER TABLE `tbl_customer`   
	CHANGE `customer_id` `customer_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	CHANGE `rank_id` `rank_id` BIGINT(20) NOT NULL;

ALTER TABLE `tbl_order`   
	CHANGE `order_id` `order_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	CHANGE `total` `total` DECIMAL(16,2) NOT NULL,
	CHANGE `totalpaid` `totalpaid` DECIMAL(16,2) DEFAULT 0 NOT NULL,
	CHANGE `discount` `discount` DECIMAL(16,2) NOT NULL,
	CHANGE `customer_id` `customer_id` BIGINT(20) NOT NULL,
	CHANGE `user_id` `user_id` BIGINT(20) NOT NULL;

ALTER TABLE `tbl_orderdetail`   
	CHANGE `orderdetail_id` `orderdetail_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	CHANGE `order_id` `order_id` BIGINT(20) NOT NULL,
	CHANGE `product_id` `product_id` BIGINT(20) NOT NULL,
	CHANGE `discount` `discount` DECIMAL(16,2) NOT NULL,
	CHANGE `selling_price` `selling_price` DECIMAL(16,2) NOT NULL,
	CHANGE `total` `total` DECIMAL(16,2) NOT NULL;

ALTER TABLE `payment`   
	CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	CHANGE `order_id` `order_id` BIGINT(20) NOT NULL,
	CHANGE `total_paid` `total_paid` DECIMAL(16,2) NOT NULL,
	CHANGE `customer_id` `customer_id` BIGINT(20) NOT NULL,
	ADD COLUMN `payment_method_id` BIGINT(20) NULL AFTER `updated_at`,
	ADD COLUMN `payment_slip` VARCHAR(255) NULL AFTER `payment_method_id`;


ALTER TABLE `payment`   
	CHANGE `order_date` `order_date` DATETIME NOT NULL;

ALTER TABLE `tbl_rank`  
  ENGINE=INNODB;


ALTER TABLE `tbl_customer`  
  ADD FOREIGN KEY (`rank_id`) REFERENCES `tbl_rank`(`rank_id`);


ALTER TABLE `tbl_rank`  
  CHARSET=utf8, COLLATE=utf8_unicode_ci;
