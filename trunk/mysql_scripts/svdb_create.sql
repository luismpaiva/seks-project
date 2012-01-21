SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `svdb` ;
CREATE SCHEMA IF NOT EXISTS `svdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin ;
USE `svdb` ;

-- -----------------------------------------------------
-- Table `svdb`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`document` ;

CREATE  TABLE IF NOT EXISTS `svdb`.`document` (
  `idDocument` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `description` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL ,
  `title` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL ,
  `extension` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL ,
  `isIndexed` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idDocument`) ,
  UNIQUE INDEX `documentUri_UNIQUE` (`idDocument` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `svdb`.`semanticweight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`semanticweight` ;

CREATE  TABLE IF NOT EXISTS `svdb`.`semanticweight` (
  `idSemanticWeight` INT(11) NOT NULL AUTO_INCREMENT ,
  `parentClass` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL ,
  `concept` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `weight` DOUBLE NULL DEFAULT NULL ,
  `Document_idDocument` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`idSemanticWeight`, `Document_idDocument`) ,
  INDEX `fk_SemanticWeight_Document` (`Document_idDocument` ASC) ,
  CONSTRAINT `fk_SemanticWeight_Document`
    FOREIGN KEY (`Document_idDocument` )
    REFERENCES `svdb`.`document` (`idDocument` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `svdb`.`statisticweight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `svdb`.`statisticweight` ;

CREATE  TABLE IF NOT EXISTS `svdb`.`statisticweight` (
  `idStatisticWeight` INT(11) NOT NULL AUTO_INCREMENT ,
  `keyword` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `weight` DOUBLE NULL DEFAULT NULL ,
  `Document_idDocument` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`idStatisticWeight`, `Document_idDocument`) ,
  INDEX `fk_StatisticWeight_Document` (`Document_idDocument` ASC) ,
  CONSTRAINT `fk_StatisticWeight_Document0`
    FOREIGN KEY (`Document_idDocument` )
    REFERENCES `svdb`.`document` (`idDocument` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- procedure getDocumentIDs
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getDocumentIDs`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDocumentIDs`()
BEGIN
    select (idDocument) from Document ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getDocumentNumWithConcept
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getDocumentNumWithConcept`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDocumentNumWithConcept`(con varchar(45))
BEGIN
    select count(idSemanticWeight) as nDocument from semanticWeight where (concept = con);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getListNonIndexed
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getListNonIndexed`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getListNonIndexed`()
BEGIN
    SELECT idDocument, title, description, extension
    FROM Document
    WHERE isIndexed=false;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getSemanticWeightsWithDocID
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getSemanticWeightsWithDocID`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSemanticWeightsWithDocID`(documentURI varchar(45))
BEGIN
    select idSemanticWeight, parentClass, concept, weight from semanticWeight where (Document_idDocument = documentURI) ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getStatisticWeightsWithDocID
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getStatisticWeightsWithDocID`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStatisticWeightsWithDocID`(docURI varchar(45))
BEGIN
    select * from `svdb`.`StatisticWeight` where (`Document_idDocument` = docURI) ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getTotalDocumentNum
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`getTotalDocumentNum`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalDocumentNum`()
BEGIN
    select count(idDocument) as nDocument from Document ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertSemanticWeight
-- -----------------------------------------------------

USE `svdb`;
DROP procedure IF EXISTS `svdb`.`insertSemanticWeight`;

DELIMITER $$
USE `svdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertSemanticWeight`(parentClass varchar(45), concept varchar(45), weight double, documentURI varchar(45))
BEGIN
    INSERT INTO `svdb`.`SemanticWeight` (`parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (parentClass, concept, weight, documentURI) ;
END$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `svdb`.`Document`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`Document` (`idDocument`, `description`, `title`, `extension`, `isIndexed`) VALUES ('xpto1', 'QQ COISA', 'QQ COISA', '.txt', false);
INSERT INTO `svdb`.`Document` (`idDocument`, `description`, `title`, `extension`, `isIndexed`) VALUES ('xpto2', 'fegdnh', 'QQ COISA', '.pdf', false);

COMMIT;

-- -----------------------------------------------------
-- Data for table `svdb`.`SemanticWeight`
-- -----------------------------------------------------
START TRANSACTION;
USE `svdb`;
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (1, 'Design_Actor', 'Architect', 0.6, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (2, 'Project_Phase', 'Design_Phase', 0.4, 'xpto1');
INSERT INTO `svdb`.`SemanticWeight` (`idSemanticWeight`, `parentClass`, `concept`, `weight`, `Document_idDocument`) VALUES (3, 'gfngtf', 'sghrt', 1, 'xpto2');

COMMIT;
