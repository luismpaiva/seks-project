SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `svdb` ;
CREATE SCHEMA IF NOT EXISTS `svdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin ;
SHOW WARNINGS;
SHOW WARNINGS;
USE `svdb` ;

-- -----------------------------------------------------
-- Table `svdb`.`Document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`Document` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`Document` (
  `idDocument` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `title` VARCHAR(45) NULL ,
  `extension` VARCHAR(45) NULL ,
  `isIndexed` TINYINT(1)  NOT NULL ,
  PRIMARY KEY (`idDocument`) ,
  UNIQUE INDEX `documentUri_UNIQUE` (`idDocument` ASC) )
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
  `weight` DOUBLE NULL ,
  `Document_idDocument` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `Document_idDocument`) ,
  INDEX `fk_SemanticWeight_Document` (`Document_idDocument` ASC) ,
  CONSTRAINT `fk_SemanticWeight_Document`
    FOREIGN KEY (`Document_idDocument` )
    REFERENCES `svdb`.`Document` (`idDocument` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `svdb`.`StatisticWeight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`StatisticWeight` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `svdb`.`StatisticWeight` (
  `idSemanticWeight` INT NOT NULL AUTO_INCREMENT ,
  `keyword` VARCHAR(45) NOT NULL ,
  `weight` DOUBLE NULL ,
  `Document_idDocument` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `Document_idDocument`) ,
  INDEX `fk_SemanticWeight_Document` (`Document_idDocument` ASC) ,
  CONSTRAINT `fk_SemanticWeight_Document0`
    FOREIGN KEY (`Document_idDocument` )
    REFERENCES `svdb`.`Document` (`idDocument` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getDocumentNumWithConcept
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getDocumentNumWithConcept`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getDocumentNumWithConcept` (concept varchar(45))
BEGIN
    select count(*) as nDocument from semanticWeight where (`concept` = concept);
END


$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getTotalDocumentNum
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getTotalDocumentNum`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getTotalDocumentNum` ()
BEGIN
    select count(*) as nDocument from Document ;
END

$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getStatisticWeightsWithDocURI
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getStatisticWeightsWithDocURI`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getStatisticWeightsWithDocURI` (docURI varchar(45))
BEGIN
    select * from `svdb`.`StatisticWeight` where (`Document_idDocument` = docURI) ;
END



$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getSemanticWeightsWithDocURI
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getSemanticWeightsWithDocURI`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getSemanticWeightsWithDocURI` (docURI varchar(45))
BEGIN
    select * from `svdb`.`SemanticWeight` where (`Document_idDocument` = docURI) ;
END



$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getNotIndexedDocURI
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getNotIndexedDocURI`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getNotIndexedDocURI` ()
BEGIN
    select * from `svdb`.`Document` where (`isIndexed` = false) ;
END

$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure insertSemanticWeight
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`insertSemanticWeight`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`insertSemanticWeight` (parentClass varchar(45), concept varchar(45), weight double, documentURI varchar(45))
BEGIN
    INSERT INTO `svdb`.`SemanticWeight` (`parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (parentClass, concept, weight, documentURI) ;
END
$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getListNonIndexed
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getListNonIndexed`;
SHOW WARNINGS;

DELIMITER $$
USE `svdb`$$
CREATE PROCEDURE `svdb`.`getListNonIndexed` ()
BEGIN
    SELECT idDocument, title, description, extension
    FROM Document
    WHERE isIndexed=false;
END
$$

DELIMITER ;
SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `svdb`.`Document`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`Document` (`idDocument`, `description`, `title`, `extension`, `isIndexed`) VALUES ('xpto1', 'QQ COISA', 'QQ COISA', 'QQ COISA', true);
INSERT INTO `svdb`.`Document` (`idDocument`, `description`, `title`, `extension`, `isIndexed`) VALUES ('xpto2', 'fegdnh', 'QQ COISA', 'QQ COISA', true);

COMMIT;

-- -----------------------------------------------------
-- Data for table `svdb`.`SemanticWeight`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (1, 'dsgjb', 'flkbtdbr', 12, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (2, 'fghstf', 'ghrdyt', 30, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (3, 'gfngtf', 'sghrt', 10, 'xpto2');

COMMIT;