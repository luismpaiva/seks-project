SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `svdb` ;
CREATE SCHEMA IF NOT EXISTS `svdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin ;
SHOW WARNINGS;
USE `svdb` ;

-- -----------------------------------------------------
-- Table `svdb`.`SemanticVector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`SemanticVector` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`SemanticVector` (
  `idSemanticVector` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idSemanticVector`) )
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
  `SemanticVector_idSemanticVector` INT NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `SemanticVector_idSemanticVector`) ,
  INDEX `fk_SemanticWeight_SemanticVector` (`SemanticVector_idSemanticVector` ASC) ,
  CONSTRAINT `fk_SemanticWeight_SemanticVector`
    FOREIGN KEY (`SemanticVector_idSemanticVector` )
    REFERENCES `svdb`.`SemanticVector` (`idSemanticVector` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;