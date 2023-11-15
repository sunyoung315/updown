-- -----------------------------------------------------
-- Schema updown
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `updown` ;
CREATE SCHEMA IF NOT EXISTS `updown` DEFAULT CHARACTER SET utf8 ;
USE `updown` ;

-- -----------------------------------------------------
-- Table `updown`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `updown`.`user` ;

CREATE TABLE IF NOT EXISTS `updown`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `target_weight` INT NOT NULL DEFAULT 0,
  `target_time` INT NOT NULL DEFAULT 0,
  `img` VARCHAR(100) NULL,
  `org_img` VARCHAR(100) NULL,
  `updown` VARCHAR(100) NOT NULL DEFAULT 'down',
  `is_secret` TINYINT NOT NULL DEFAULT 0,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `updown`.`weight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `updown`.`weight` ;

CREATE TABLE IF NOT EXISTS `updown`.`weight` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `now_weight` INT NOT NULL,
  `remain_weight` INT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_weight_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_weight_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `updown`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `updown`.`diet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `updown`.`diet` ;

CREATE TABLE IF NOT EXISTS `updown`.`diet` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(100) NOT NULL,
  `food` VARCHAR(300) NOT NULL,
  `calorie` INT NOT NULL DEFAULT 0,
  `reg_date` TIMESTAMP NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_diet_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_diet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `updown`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `updown`.`exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `updown`.`exercise` ;

CREATE TABLE IF NOT EXISTS `updown`.`exercise` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `time` INT NOT NULL DEFAULT 0,
  `calorie` INT NOT NULL DEFAULT 0,
  `reg_date` TIMESTAMP NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_exercise_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_exercise_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `updown`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `updown`.`follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `updown`.`follow` ;

CREATE TABLE IF NOT EXISTS `updown`.`follow` (
  `from_user_id` VARCHAR(100) NOT NULL,
  `to_user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`from_user_id`, `to_user_id`),
  INDEX `fk_follow_user2_idx` (`to_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`from_user_id`)
    REFERENCES `updown`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`to_user_id`)
    REFERENCES `updown`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;