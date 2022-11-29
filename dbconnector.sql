-- MySQL Script generated by MySQL Workbench
-- Tue Nov 29 02:11:34 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dbconnector
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbconnector
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbconnector` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dbconnector` ;

-- -----------------------------------------------------
-- Table `dbconnector`.`ingredients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbconnector`.`ingredients` (
  `ingredient_id` INT NOT NULL,
  `bell_pepper` INT NOT NULL,
  `ground_beef` INT NOT NULL,
  `spaghetti_noodles` INT NOT NULL,
  `sauce` INT NOT NULL,
  `cheese` INT NOT NULL,
  PRIMARY KEY (`ingredient_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbconnector`.`cookbook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbconnector`.`cookbook` (
  `recipe_id` INT NOT NULL,
  `recipe_desc` VARCHAR(225) NOT NULL,
  `recipe_steps` VARCHAR(600) NOT NULL,
  `ingredient_list_id` INT NOT NULL,
  PRIMARY KEY (`recipe_id`),
  INDEX `ingredient_list_id to ingredient_id _idx` (`ingredient_list_id` ASC) VISIBLE,
  CONSTRAINT `ingredient_list_id to ingredient_id `
    FOREIGN KEY (`ingredient_list_id`)
    REFERENCES `dbconnector`.`ingredients` (`ingredient_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dbconnector`.`registration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbconnector`.`registration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(225) NOT NULL,
  `email` VARCHAR(55) NOT NULL,
  `password` VARCHAR(55) NOT NULL,
  `pantry_id` INT NOT NULL,
  `admin` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `pantry_id to ing_id_idx` (`pantry_id` ASC) VISIBLE,
  CONSTRAINT `pantry_id to ing_id`
    FOREIGN KEY (`pantry_id`)
    REFERENCES `dbconnector`.`ingredients` (`ingredient_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `dbconnector`.`ingredients`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbconnector`;
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (0, 1, 1, 1, 1, 1);
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (1, 0, 0, 0, 0, 0);
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (2, 0, 0, 0, 0, 0);
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (10, 0, 1, 1, 1, 0);
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (11, 1, 1, 0, 0, 1);
INSERT INTO `dbconnector`.`ingredients` (`ingredient_id`, `bell_pepper`, `ground_beef`, `spaghetti_noodles`, `sauce`, `cheese`) VALUES (101, 1, 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dbconnector`.`cookbook`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbconnector`;
INSERT INTO `dbconnector`.`cookbook` (`recipe_id`, `recipe_desc`, `recipe_steps`, `ingredient_list_id`) VALUES (0, 'Admin Spaghetti', 'admin recipe', 101);
INSERT INTO `dbconnector`.`cookbook` (`recipe_id`, `recipe_desc`, `recipe_steps`, `ingredient_list_id`) VALUES (1, 'Spaghetti', 'spaghetti recipe', 10);
INSERT INTO `dbconnector`.`cookbook` (`recipe_id`, `recipe_desc`, `recipe_steps`, `ingredient_list_id`) VALUES (2, 'Stuffed Pepper', 'pepper recipe', 11);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dbconnector`.`registration`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbconnector`;
INSERT INTO `dbconnector`.`registration` (`id`, `full_name`, `email`, `password`, `pantry_id`, `admin`) VALUES (1, 'grant ', 'grant@gmail.com', 'pass1', 1, 0);
INSERT INTO `dbconnector`.`registration` (`id`, `full_name`, `email`, `password`, `pantry_id`, `admin`) VALUES (2, 'matt', 'matt@gmail.com', 'pass2', 2, 0);
INSERT INTO `dbconnector`.`registration` (`id`, `full_name`, `email`, `password`, `pantry_id`, `admin`) VALUES (3, 'admin', 'admin', 'admin', 0, 1);

COMMIT;

