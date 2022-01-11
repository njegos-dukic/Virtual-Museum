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
  `maps` VARCHAR(300) NOT NULL,
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
-- Table `VirtualMuseum`.`TICKET`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualMuseum`.`TICKET` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tourId` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TICKET_TOUR1_idx` (`tourId` ASC) VISIBLE,
  INDEX `fk_TICKET_USER1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_TICKET_TOUR1`
    FOREIGN KEY (`tourId`)
    REFERENCES `VirtualMuseum`.`TOUR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TICKET_USER1`
    FOREIGN KEY (`idUser`)
    REFERENCES `VirtualMuseum`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
