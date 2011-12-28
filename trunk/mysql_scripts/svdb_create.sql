SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `svdb` ;
CREATE SCHEMA IF NOT EXISTS `svdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin ;
SHOW WARNINGS;
SHOW WARNINGS;
USE `svdb` ;

-- -----------------------------------------------------
-- Table `svdb`.`SemanticVector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`SemanticVector` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`SemanticVector` (
  `documentUri` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `isIndexed` TINYINT(1)  NOT NULL ,
  PRIMARY KEY (`documentUri`) ,
  UNIQUE INDEX `documentUri_UNIQUE` (`documentUri` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `svdb`.`SemanticWeight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`SemanticWeight` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`SemanticWeight` (
  `idSemanticWeight` INT NOT NULL AUTO_INCREMENT ,
  `parentClass` VARCHAR(45) NULL ,
  `concept` VARCHAR(45) NOT NULL ,
  `weight` INT(11)  NULL ,
  `SemanticVector_documentUri` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `SemanticVector_documentUri`) ,
  INDEX `fk_SemanticWeight_SemanticVector` (`SemanticVector_documentUri` ASC) ,
  CONSTRAINT `fk_SemanticWeight_SemanticVector`
    FOREIGN KEY (`SemanticVector_documentUri` )
    REFERENCES `svdb`.`SemanticVector` (`documentUri` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `svdb`.`StatVector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`StatVector` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`StatVector` (
  `documentUri` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`documentUri`) ,
  UNIQUE INDEX `documentUri_UNIQUE` (`documentUri` ASC) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `svdb`.`StatWeight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`StatWeight` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`StatWeight` (
  `idSemanticWeight` INT NOT NULL AUTO_INCREMENT ,
  `keyword` VARCHAR(45) NOT NULL ,
  `weight` INT(11)  NULL ,
  `StatVector_documentUri` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `StatVector_documentUri`) ,
  INDEX `fk_StatWeight_StatVector1` (`StatVector_documentUri` ASC) ,
  CONSTRAINT `fk_StatWeight_StatVector1`
    FOREIGN KEY (`StatVector_documentUri` )
    REFERENCES `svdb`.`StatVector` (`documentUri` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `svdb`.`SemanticVector`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`SemanticVector` (`documentUri`, `description`, `isIndexed`) VALUES ('xpto1', 'QQ COISA', NULL);
INSERT INTO `svdb`.`SemanticVector` (`documentUri`, `description`, `isIndexed`) VALUES ('xpto2', 'fegdnh', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `svdb`.`SemanticWeight`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `SemanticVector_documentUri`) VALUES (1, 'dsgjb', 'flkbtdbr', 12, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `SemanticVector_documentUri`) VALUES (2, 'fghstf', 'ghrdyt', 30, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `SemanticVector_documentUri`) VALUES (3, 'gfngtf', 'sghrt', 10, 'xpto2');

COMMIT;
