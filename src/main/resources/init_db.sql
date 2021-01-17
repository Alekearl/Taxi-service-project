CREATE SCHEMA `manufacturer` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `manufacturer`.`manufacturers` (
                                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(225) NOT NULL,
                                                `country` VARCHAR(225) NOT NULL,
                                                `delete` TINYINT(20) NOT NULL DEFAULT '0',
                                                PRIMARY KEY (`id`),
                                                UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

ALTER TABLE `manufacturer`.`manufacturers`
    CHANGE COLUMN `id` `manufacturer_id` BIGINT NOT NULL AUTO_INCREMENT ,
    CHANGE COLUMN `name` `manufacturer_name` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `country` `manufacturer_country` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `delete` `manufacturer_delete` TINYINT NOT NULL DEFAULT '0' ;

INSERT INTO `manufacturer`.`manufacturers` (`manufacturer_name`, `manufacturer_country`) VALUES ('Smart', 'Germany');
