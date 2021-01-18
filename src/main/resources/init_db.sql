CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `taxi_service` (
                                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(225) NOT NULL,
                                                `country` VARCHAR(225) NOT NULL,
                                                `deleted` TINYINT(20) NOT NULL DEFAULT '0',
                                                PRIMARY KEY (`id`),
                                                UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

INSERT INTO `taxi_service` (`name`, `country`) VALUES ('Smart', 'Germany');
