-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema VirtualMuseum
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema VirtualMuseum
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VirtualMuseum` DEFAULT CHARACTER SET utf8 ;
USE `VirtualMuseum` ;

-- -----------------------------------------------------
-- Table `VirtualMuseum`.`MUSEUM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`MUSEUM` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(25) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `lat` DECIMAL(10,7) NOT NULL,
  `lng` DECIMAL(10,7) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`TOUR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`TOUR` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `start` DATETIME NOT NULL,
  `duration` DECIMAL(3,1) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `museumId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TOUR_MUSEUM1_idx` (`museumId` ASC) VISIBLE,
  CONSTRAINT `fk_TOUR_MUSEUM1`
    FOREIGN KEY (`museumId`)
    REFERENCES `VirtualMuseum`.`MUSEUM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`USER` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `isAdmin` TINYINT NOT NULL,
  `adminToken` VARCHAR(256) NULL,
  `isApproved` TINYINT NOT NULL DEFAULT 0,
  `isBlocked` TINYINT NOT NULL DEFAULT 0,
  `isPasswordReset` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`ARTIFACT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`ARTIFACT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uri` VARCHAR(100) NOT NULL,
  `type` VARCHAR(50) NULL,
  `tourId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ARTIFCAT_TOUR_idx` (`tourId` ASC) VISIBLE,
  CONSTRAINT `fk_ARTIFCAT_TOUR`
    FOREIGN KEY (`tourId`)
    REFERENCES `VirtualMuseum`.`TOUR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`CARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`CARD` (
  `cardNumber` CHAR(16) NOT NULL,
  `firstName` VARCHAR(200) NOT NULL,
  `lastName` VARCHAR(200) NOT NULL,
  `cardType` VARCHAR(25) NOT NULL,
  `expirationDate` TEXT(4) NOT NULL,
  `cvv` TEXT(3) NOT NULL,
  `balance` DECIMAL(10,2) NOT NULL,
  `isEnabled` TINYINT NOT NULL,
  PRIMARY KEY (`cardNumber`))
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`CARD_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`CARD_USER` (
  `userId` INT NOT NULL,
  `cardNumber` CHAR(16) NOT NULL,
  PRIMARY KEY (`userId`, `cardNumber`),
  INDEX `fk_CARD_has_USER_USER1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_CARD_USER_CARD1_idx` (`cardNumber` ASC) VISIBLE,
  CONSTRAINT `fk_CARD_has_USER_USER1`
    FOREIGN KEY (`userId`)
    REFERENCES `VirtualMuseum`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CARD_USER_CARD1`
    FOREIGN KEY (`cardNumber`)
    REFERENCES `VirtualMuseum`.`CARD` (`cardNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualMuseum`.`TRANSACTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`TRANSACTION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `cardNumber` CHAR(16) NOT NULL,
  `tourId` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `isSuccess` TINYINT NOT NULL,
  `ticketNumber` VARCHAR(256) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TRANSACTION_TOUR1_idx` (`tourId` ASC) VISIBLE,
  INDEX `fk_TRANSACTION_CARD_USER1_idx` (`userId` ASC, `cardNumber` ASC) VISIBLE,
  CONSTRAINT `fk_TRANSACTION_TOUR1`
    FOREIGN KEY (`tourId`)
    REFERENCES `VirtualMuseum`.`TOUR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TRANSACTION_CARD_USER1`
    FOREIGN KEY (`userId` , `cardNumber`)
    REFERENCES `VirtualMuseum`.`CARD_USER` (`userId` , `cardNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
