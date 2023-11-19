-- -----------------------------------------------------
-- Schema updown
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `updown` ;

-- -----------------------------------------------------
-- Schema updown
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `updown` DEFAULT CHARACTER SET utf8 ;
USE `updown` ;

-- -----------------------------------------------------
-- Table `updown`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `target_weight` DECIMAL(5,1) NOT NULL DEFAULT '0.0',
  `target_time` INT NOT NULL DEFAULT '0',
  `img` VARCHAR(100) NULL DEFAULT NULL,
  `org_img` VARCHAR(100) NULL DEFAULT NULL,
  `updown` VARCHAR(100) NOT NULL DEFAULT 'down',
  `is_secret` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `updown`.`diet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diet` ;

CREATE TABLE IF NOT EXISTS `diet` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(100) NOT NULL,
  `food` VARCHAR(300) NOT NULL,
  `calorie` INT NOT NULL DEFAULT '0',
  `reg_date` DATE NOT NULL,
  `img` VARCHAR(100) NULL DEFAULT NULL,
  `org_img` VARCHAR(100) NULL DEFAULT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_diet_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_diet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `updown`.`exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise` ;

CREATE TABLE IF NOT EXISTS `exercise` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `time` INT NOT NULL DEFAULT '0',
  `calorie` DECIMAL(5,1) NOT NULL DEFAULT '0',
  `reg_date` DATE NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_exercise_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_exercise_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;

SELECT * FROM exercise;

-- -----------------------------------------------------
-- Table `updown`.`follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `follow` ;

CREATE TABLE IF NOT EXISTS `follow` (
  `from_user_id` VARCHAR(100) NOT NULL,
  `to_user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`from_user_id`, `to_user_id`),
  INDEX `fk_follow_user2_idx` (`to_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`from_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`to_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `updown`.`weight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `weight` ;

CREATE TABLE IF NOT EXISTS `weight` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `now_weight` DECIMAL(5,1) NOT NULL,
  `reg_date` DATE NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`no`, `user_id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC) VISIBLE,
  INDEX `fk_weight_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_weight_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `updown`.`exercise_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise_info` ;

CREATE TABLE IF NOT EXISTS `exercise_info` (
  `type` VARCHAR(100) NOT NULL,
  `met` DECIMAL(5,1) NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;

SELECT * FROM exercise_info;
