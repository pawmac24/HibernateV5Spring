DROP TABLE IF EXISTS `phone`;
DROP TABLE IF EXISTS `employee`;
-- CREATE TABLE `employee` (
--   `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `first_name` varchar(50) NOT NULL,
--   `last_name` varchar(40) NOT NULL,
--   `salary` decimal(10, 2) NOT NULL,
--   PRIMARY KEY (`employee_id`)
-- );

-- CREATE TABLE `phone` (
--   `owner_id` bigint(20) NOT NULL,
--   `type` varchar(5) NOT NULL,
--   `area_code` varchar(3) NOT NULL,
--   `phone_number` varchar(8) NOT NULL,
--   KEY `k_phone_owner_id` (`owner_id`),
--   CONSTRAINT `fk_phone_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `employee` (`employee_id`)
-- );

-- ALTER TABLE employee ADD COLUMN birth_date DATE NOT NULL;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(10) NOT NULL,
  `order_date` date NOT NULL,
  PRIMARY KEY (`order_id`)
);

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `number` bigint(20) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `k_item_to_order_id` (`order_id`),
  CONSTRAINT `item_to_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
);
