-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema goree
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `goree` ;

-- -----------------------------------------------------
-- Schema goree
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `goree` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `goree` ;

-- -----------------------------------------------------
-- Table `goree`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`member` ;

CREATE TABLE IF NOT EXISTS `goree`.`member` (
  `member_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(60) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `full_name` VARCHAR(15) NOT NULL COMMENT '',
  `nickname` VARCHAR(45) NOT NULL COMMENT '',
  `facebook` VARCHAR(60) NULL COMMENT '',
  `twitter` VARCHAR(60) NULL COMMENT '',
  `kakaotalk` VARCHAR(60) NULL COMMENT '',
  `job` VARCHAR(45) NULL COMMENT '',
  `age` INT NOT NULL COMMENT '',
  `gender` ENUM('M', 'F') NOT NULL COMMENT '',
  `phone` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`member_id`)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '')
ENGINE = InnoDB
COMMENT = '     ';


-- -----------------------------------------------------
-- Table `goree`.`groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`groups` ;

CREATE TABLE IF NOT EXISTS `goree`.`groups` (
  `group_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `leader_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `group_name` VARCHAR(30) NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `cover_img_url` TEXT NULL COMMENT '',
  PRIMARY KEY (`group_id`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`group_name` ASC)  COMMENT '',
  INDEX `fk_group_member1_idx` (`leader_id` ASC)  COMMENT '',
  CONSTRAINT `fk_group_member1`
    FOREIGN KEY (`leader_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`place` ;

CREATE TABLE IF NOT EXISTS `goree`.`place` (
  `place_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `place_name` VARCHAR(45) NOT NULL COMMENT '',
  `address` VARCHAR(100) NOT NULL COMMENT '',
  `x_coordinate` DECIMAL NOT NULL COMMENT '',
  `y_coordinate` DECIMAL NOT NULL COMMENT '',
  PRIMARY KEY (`place_id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`meeting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`meeting` ;

CREATE TABLE IF NOT EXISTS `goree`.`meeting` (
  `meeting_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `group_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `promoter_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `place_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`meeting_id`)  COMMENT '',
  INDEX `fk_meeting_group1_idx` (`group_id` ASC)  COMMENT '',
  INDEX `fk_meeting_place1_idx` (`place_id` ASC)  COMMENT '',
  INDEX `fk_meeting_member1_idx` (`promoter_id` ASC)  COMMENT '',
  CONSTRAINT `fk_meeting_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `goree`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meeting_place1`
    FOREIGN KEY (`place_id`)
    REFERENCES `goree`.`place` (`place_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meeting_member1`
    FOREIGN KEY (`promoter_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`tag` ;

CREATE TABLE IF NOT EXISTS `goree`.`tag` (
  `tag_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `tag_name` VARCHAR(15) NOT NULL COMMENT '',
  `provided` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '',
  PRIMARY KEY (`tag_id`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`tag_name` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`meeting_has_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`meeting_has_member` ;

CREATE TABLE IF NOT EXISTS `goree`.`meeting_has_member` (
  `meeting_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `member_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `attendance` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '',
  PRIMARY KEY (`meeting_id`, `member_id`, `attendance`)  COMMENT '',
  INDEX `fk_participant_member1_idx` (`member_id` ASC)  COMMENT '',
  CONSTRAINT `fk_participant_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participant_meeting1`
    FOREIGN KEY (`meeting_id`)
    REFERENCES `goree`.`meeting` (`meeting_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`group_has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`group_has_tag` ;

CREATE TABLE IF NOT EXISTS `goree`.`group_has_tag` (
  `group_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `tag_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`group_id`, `tag_id`)  COMMENT '',
  INDEX `fk_group_tag_tag1_idx` (`tag_id` ASC)  COMMENT '',
  CONSTRAINT `fk_group_tag_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `goree`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_tag_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `goree`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`member_has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`member_has_tag` ;

CREATE TABLE IF NOT EXISTS `goree`.`member_has_tag` (
  `tag_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `member_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`tag_id`, `member_id`)  COMMENT '',
  INDEX `fk_tag_has_member_member1_idx` (`member_id` ASC)  COMMENT '',
  INDEX `fk_tag_has_member_tag1_idx` (`tag_id` ASC)  COMMENT '',
  CONSTRAINT `fk_tag_has_member_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `goree`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_has_member_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`group_has_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`group_has_member` ;

CREATE TABLE IF NOT EXISTS `goree`.`group_has_member` (
  `group_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `member_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`group_id`, `member_id`)  COMMENT '',
  INDEX `fk_group_has_member_member1_idx` (`member_id` ASC)  COMMENT '',
  INDEX `fk_group_has_member_group1_idx` (`group_id` ASC)  COMMENT '',
  CONSTRAINT `fk_group_has_member_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `goree`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_member_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`note` ;

CREATE TABLE IF NOT EXISTS `goree`.`note` (
  `note_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `group_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `note_writer_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `note_content` TEXT NULL COMMENT '',
  `note_create_date` TIMESTAMP NOT NULL DEFAULT now() COMMENT '',
  `notecol` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`note_id`)  COMMENT '',
  INDEX `fk_note_group1_idx` (`group_id` ASC)  COMMENT '',
  INDEX `fk_note_member1_idx` (`note_writer_id` ASC)  COMMENT '',
  CONSTRAINT `fk_note_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `goree`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_member1`
    FOREIGN KEY (`note_writer_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `goree`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goree`.`comment` ;

CREATE TABLE IF NOT EXISTS `goree`.`comment` (
  `comment_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `note_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `comment_writer_id` BIGINT UNSIGNED NOT NULL COMMENT '',
  `comment_content` TEXT NULL COMMENT '',
  `comment_create_date` TIMESTAMP NOT NULL DEFAULT now() COMMENT '',
  PRIMARY KEY (`comment_id`)  COMMENT '',
  INDEX `fk_comment_note1_idx` (`note_id` ASC)  COMMENT '',
  INDEX `fk_comment_member1_idx` (`comment_writer_id` ASC)  COMMENT '',
  CONSTRAINT `fk_comment_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `goree`.`note` (`note_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_member1`
    FOREIGN KEY (`comment_writer_id`)
    REFERENCES `goree`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
