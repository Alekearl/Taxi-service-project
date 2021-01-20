CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `taxi_service`.`manufacturers` (
                                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(225) NOT NULL,
                                                `country` VARCHAR(225) NOT NULL,
                                                `deleted` TINYINT(20) NOT NULL DEFAULT '0',
                                                PRIMARY KEY (`id`),
                                                UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

INSERT INTO `taxi_service`.`manufacturers` (`name`, `country`) VALUES ('Smart', 'Germany');

CREATE TABLE `taxi_service`.`drivers` (
                                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(225) NOT NULL,
                                          `license_number` VARCHAR(225) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT '0',
                                          PRIMARY KEY (`id`),
                                          UNIQUE INDEX `iddrivers_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxi_service`.`cars` (
                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                       `model` VARCHAR(45) NOT NULL,
                                       `manufacturer_id` BIGINT NOT NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT '0',
                                       PRIMARY KEY (`id`),
                                       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                       INDEX `manufacturer_id_fk_id_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `manufacturer_id_fk_id`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturers` (`id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxi_service`.`cars_drivers` (
                                               `car_id` BIGINT NOT NULL,
                                               `driver_id` BIGINT NOT NULL,
                                               INDEX `cars_drivers_car_if_fk_idx` (`car_id` ASC) VISIBLE,
                                               INDEX `cars_drivers_driver_id_fk_idx` (`driver_id` ASC) VISIBLE,
                                               CONSTRAINT `cars_drivers_car_id_fk`
                                                   FOREIGN KEY (`car_id`)
                                                       REFERENCES `taxi_service`.`cars` (`id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `cars_drivers_driver_id_fk`
                                                   FOREIGN KEY (`driver_id`)
                                                   REFERENCES `taxi_service`.`drivers` (`id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
