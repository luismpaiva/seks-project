SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


DROP SCHEMA IF EXISTS `lportal` ;
CREATE SCHEMA IF NOT EXISTS `lportal` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `lportal` ;

-- -----------------------------------------------------
-- Table `lportal`.`account_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`account_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`account_` (
  `accountId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentAccountId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `legalName` VARCHAR(75) NULL DEFAULT NULL ,
  `legalId` VARCHAR(75) NULL DEFAULT NULL ,
  `legalType` VARCHAR(75) NULL DEFAULT NULL ,
  `sicCode` VARCHAR(75) NULL DEFAULT NULL ,
  `tickerSymbol` VARCHAR(75) NULL DEFAULT NULL ,
  `industry` VARCHAR(75) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `size_` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`accountId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`address` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`address` (
  `addressId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `street1` VARCHAR(75) NULL DEFAULT NULL ,
  `street2` VARCHAR(75) NULL DEFAULT NULL ,
  `street3` VARCHAR(75) NULL DEFAULT NULL ,
  `city` VARCHAR(75) NULL DEFAULT NULL ,
  `zip` VARCHAR(75) NULL DEFAULT NULL ,
  `regionId` BIGINT(20) NULL DEFAULT NULL ,
  `countryId` BIGINT(20) NULL DEFAULT NULL ,
  `typeId` INT(11) NULL DEFAULT NULL ,
  `mailing` TINYINT(4) NULL DEFAULT NULL ,
  `primary_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`addressId`) ,
  INDEX `IX_93D5AD4E` (`companyId` ASC) ,
  INDEX `IX_ABD7DAC0` (`companyId` ASC, `classNameId` ASC) ,
  INDEX `IX_71CB1123` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_923BD178` (`companyId` ASC, `classNameId` ASC, `classPK` ASC, `mailing` ASC) ,
  INDEX `IX_9226DBB4` (`companyId` ASC, `classNameId` ASC, `classPK` ASC, `primary_` ASC) ,
  INDEX `IX_5BC8B0D4` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`announcementsdelivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`announcementsdelivery` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`announcementsdelivery` (
  `deliveryId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `email` TINYINT(4) NULL DEFAULT NULL ,
  `sms` TINYINT(4) NULL DEFAULT NULL ,
  `website` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`deliveryId`) ,
  UNIQUE INDEX `IX_BA4413D5` (`userId` ASC, `type_` ASC) ,
  INDEX `IX_6EDB9600` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`announcementsentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`announcementsentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`announcementsentry` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `entryId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `title` VARCHAR(75) NULL DEFAULT NULL ,
  `content` LONGTEXT NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `displayDate` DATETIME NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  `alert` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  INDEX `IX_A6EF0B81` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_14F06A6B` (`classNameId` ASC, `classPK` ASC, `alert` ASC) ,
  INDEX `IX_D49C2E66` (`userId` ASC) ,
  INDEX `IX_1AFBDE08` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`announcementsflag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`announcementsflag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`announcementsflag` (
  `flagId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `entryId` BIGINT(20) NULL DEFAULT NULL ,
  `value` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`flagId`) ,
  UNIQUE INDEX `IX_4539A99C` (`userId` ASC, `entryId` ASC, `value` ASC) ,
  INDEX `IX_9C7EB9F` (`entryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetcategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetcategory` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetcategory` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentCategoryId` BIGINT(20) NULL DEFAULT NULL ,
  `leftCategoryId` BIGINT(20) NULL DEFAULT NULL ,
  `rightCategoryId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `vocabularyId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`categoryId`) ,
  UNIQUE INDEX `IX_BE4DF2BF` (`parentCategoryId` ASC, `name` ASC, `vocabularyId` ASC) ,
  UNIQUE INDEX `IX_E8D019AA` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_E639E2F6` (`groupId` ASC) ,
  INDEX `IX_D61ABE08` (`name` ASC, `vocabularyId` ASC) ,
  INDEX `IX_7BB1826B` (`parentCategoryId` ASC) ,
  INDEX `IX_9DDD15EA` (`parentCategoryId` ASC, `name` ASC) ,
  INDEX `IX_B185E980` (`parentCategoryId` ASC, `vocabularyId` ASC) ,
  INDEX `IX_4D37BB00` (`uuid_` ASC) ,
  INDEX `IX_287B1F89` (`vocabularyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetcategoryproperty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetcategoryproperty` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetcategoryproperty` (
  `categoryPropertyId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NULL DEFAULT NULL ,
  `key_` VARCHAR(75) NULL DEFAULT NULL ,
  `value` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`categoryPropertyId`) ,
  UNIQUE INDEX `IX_DBD111AA` (`categoryId` ASC, `key_` ASC) ,
  INDEX `IX_99DA856` (`categoryId` ASC) ,
  INDEX `IX_8654719F` (`companyId` ASC) ,
  INDEX `IX_52340033` (`companyId` ASC, `key_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetentries_assetcategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetentries_assetcategories` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetentries_assetcategories` (
  `entryId` BIGINT(20) NOT NULL ,
  `categoryId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`entryId`, `categoryId`) ,
  INDEX `IX_A188F560` (`categoryId` ASC) ,
  INDEX `IX_E119938A` (`entryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetentries_assettags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetentries_assettags` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetentries_assettags` (
  `entryId` BIGINT(20) NOT NULL ,
  `tagId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`entryId`, `tagId`) ,
  INDEX `IX_2ED82CAD` (`entryId` ASC) ,
  INDEX `IX_B2A61B55` (`tagId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetentry` (
  `entryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `classUuid` VARCHAR(75) NULL DEFAULT NULL ,
  `visible` TINYINT(4) NULL DEFAULT NULL ,
  `startDate` DATETIME NULL DEFAULT NULL ,
  `endDate` DATETIME NULL DEFAULT NULL ,
  `publishDate` DATETIME NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  `mimeType` VARCHAR(75) NULL DEFAULT NULL ,
  `title` VARCHAR(255) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `summary` LONGTEXT NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `height` INT(11) NULL DEFAULT NULL ,
  `width` INT(11) NULL DEFAULT NULL ,
  `priority` DOUBLE NULL DEFAULT NULL ,
  `viewCount` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  UNIQUE INDEX `IX_1E9D371D` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_FC1F9C7B` (`classUuid` ASC) ,
  INDEX `IX_7306C60` (`companyId` ASC) ,
  INDEX `IX_1EBA6821` (`groupId` ASC, `classUuid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetlink`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetlink` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetlink` (
  `linkId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `entryId1` BIGINT(20) NULL DEFAULT NULL ,
  `entryId2` BIGINT(20) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`linkId`) ,
  INDEX `IX_128516C8` (`entryId1` ASC) ,
  INDEX `IX_56E0AB21` (`entryId1` ASC, `entryId2` ASC) ,
  INDEX `IX_8F542794` (`entryId1` ASC, `entryId2` ASC, `type_` ASC) ,
  INDEX `IX_14D5A20D` (`entryId1` ASC, `type_` ASC) ,
  INDEX `IX_12851A89` (`entryId2` ASC) ,
  INDEX `IX_91F132C` (`entryId2` ASC, `type_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assettag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assettag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assettag` (
  `tagId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `assetCount` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`tagId`) ,
  INDEX `IX_7C9E46BA` (`groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assettagproperty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assettagproperty` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assettagproperty` (
  `tagPropertyId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `tagId` BIGINT(20) NULL DEFAULT NULL ,
  `key_` VARCHAR(75) NULL DEFAULT NULL ,
  `value` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`tagPropertyId`) ,
  UNIQUE INDEX `IX_2C944354` (`tagId` ASC, `key_` ASC) ,
  INDEX `IX_DFF1F063` (`companyId` ASC) ,
  INDEX `IX_13805BF7` (`companyId` ASC, `key_` ASC) ,
  INDEX `IX_3269E180` (`tagId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assettagstats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assettagstats` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assettagstats` (
  `tagStatsId` BIGINT(20) NOT NULL ,
  `tagId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `assetCount` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`tagStatsId`) ,
  UNIQUE INDEX `IX_56682CC4` (`tagId` ASC, `classNameId` ASC) ,
  INDEX `IX_50702693` (`classNameId` ASC) ,
  INDEX `IX_9464CA` (`tagId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`assetvocabulary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`assetvocabulary` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`assetvocabulary` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `vocabularyId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `settings_` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`vocabularyId`) ,
  UNIQUE INDEX `IX_C0AAD74D` (`groupId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_1B2B8792` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_B22D908C` (`companyId` ASC) ,
  INDEX `IX_B6B8CA0E` (`groupId` ASC) ,
  INDEX `IX_55F58818` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`blogsentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`blogsentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`blogsentry` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `entryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `title` VARCHAR(150) NULL DEFAULT NULL ,
  `urlTitle` VARCHAR(150) NULL DEFAULT NULL ,
  `content` LONGTEXT NULL DEFAULT NULL ,
  `displayDate` DATETIME NULL DEFAULT NULL ,
  `allowPingbacks` TINYINT(4) NULL DEFAULT NULL ,
  `allowTrackbacks` TINYINT(4) NULL DEFAULT NULL ,
  `trackbacks` LONGTEXT NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  UNIQUE INDEX `IX_DB780A20` (`groupId` ASC, `urlTitle` ASC) ,
  UNIQUE INDEX `IX_1B1040FD` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_72EF6041` (`companyId` ASC) ,
  INDEX `IX_430D791F` (`companyId` ASC, `displayDate` ASC) ,
  INDEX `IX_BB0C2905` (`companyId` ASC, `displayDate` ASC, `status` ASC) ,
  INDEX `IX_EB2DCE27` (`companyId` ASC, `status` ASC) ,
  INDEX `IX_8CACE77B` (`companyId` ASC, `userId` ASC) ,
  INDEX `IX_A5F57B61` (`companyId` ASC, `userId` ASC, `status` ASC) ,
  INDEX `IX_81A50303` (`groupId` ASC) ,
  INDEX `IX_621E19D` (`groupId` ASC, `displayDate` ASC) ,
  INDEX `IX_F0E73383` (`groupId` ASC, `displayDate` ASC, `status` ASC) ,
  INDEX `IX_1EFD8EE9` (`groupId` ASC, `status` ASC) ,
  INDEX `IX_FBDE0AA3` (`groupId` ASC, `userId` ASC, `displayDate` ASC) ,
  INDEX `IX_DA04F689` (`groupId` ASC, `userId` ASC, `displayDate` ASC, `status` ASC) ,
  INDEX `IX_49E15A23` (`groupId` ASC, `userId` ASC, `status` ASC) ,
  INDEX `IX_69157A4D` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`blogsstatsuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`blogsstatsuser` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`blogsstatsuser` (
  `statsUserId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `entryCount` INT(11) NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  `ratingsTotalEntries` INT(11) NULL DEFAULT NULL ,
  `ratingsTotalScore` DOUBLE NULL DEFAULT NULL ,
  `ratingsAverageScore` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`statsUserId`) ,
  UNIQUE INDEX `IX_82254C25` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_90CDA39A` (`companyId` ASC, `entryCount` ASC) ,
  INDEX `IX_43840EEB` (`groupId` ASC) ,
  INDEX `IX_28C78D5C` (`groupId` ASC, `entryCount` ASC) ,
  INDEX `IX_BB51F1D9` (`userId` ASC) ,
  INDEX `IX_507BA031` (`userId` ASC, `lastPostDate` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`bookmarksentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`bookmarksentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`bookmarksentry` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `entryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  `visits` INT(11) NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  UNIQUE INDEX `IX_EAA02A91` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_E52FF7EF` (`groupId` ASC) ,
  INDEX `IX_5200100C` (`groupId` ASC, `folderId` ASC) ,
  INDEX `IX_E2E9F129` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_B670BA39` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`bookmarksfolder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`bookmarksfolder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`bookmarksfolder` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`folderId`) ,
  UNIQUE INDEX `IX_DC2F8927` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_2ABA25D7` (`companyId` ASC) ,
  INDEX `IX_7F703619` (`groupId` ASC) ,
  INDEX `IX_967799C0` (`groupId` ASC, `parentFolderId` ASC) ,
  INDEX `IX_451E7AE3` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`browsertracker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`browsertracker` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`browsertracker` (
  `browserTrackerId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `browserKey` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`browserTrackerId`) ,
  UNIQUE INDEX `IX_E7B95510` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`calevent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`calevent` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`calevent` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `eventId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `title` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `startDate` DATETIME NULL DEFAULT NULL ,
  `endDate` DATETIME NULL DEFAULT NULL ,
  `durationHour` INT(11) NULL DEFAULT NULL ,
  `durationMinute` INT(11) NULL DEFAULT NULL ,
  `allDay` TINYINT(4) NULL DEFAULT NULL ,
  `timeZoneSensitive` TINYINT(4) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `repeating` TINYINT(4) NULL DEFAULT NULL ,
  `recurrence` LONGTEXT NULL DEFAULT NULL ,
  `remindBy` INT(11) NULL DEFAULT NULL ,
  `firstReminder` INT(11) NULL DEFAULT NULL ,
  `secondReminder` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`eventId`) ,
  UNIQUE INDEX `IX_5CCE79C8` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_D6FD9496` (`companyId` ASC) ,
  INDEX `IX_12EE4898` (`groupId` ASC) ,
  INDEX `IX_4FDDD2BF` (`groupId` ASC, `repeating` ASC) ,
  INDEX `IX_FCD7C63D` (`groupId` ASC, `type_` ASC) ,
  INDEX `IX_F6006202` (`remindBy` ASC) ,
  INDEX `IX_C1AD2122` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`chat_entry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`chat_entry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`chat_entry` (
  `entryId` BIGINT(20) NOT NULL ,
  `createDate` BIGINT(20) NULL DEFAULT NULL ,
  `fromUserId` BIGINT(20) NULL DEFAULT NULL ,
  `toUserId` BIGINT(20) NULL DEFAULT NULL ,
  `content` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  INDEX `IX_DAEF230F` (`createDate` ASC) ,
  INDEX `IX_AD559D93` (`createDate` ASC, `fromUserId` ASC) ,
  INDEX `IX_D9E49928` (`createDate` ASC, `fromUserId` ASC, `toUserId` ASC) ,
  INDEX `IX_8BE273A4` (`createDate` ASC, `toUserId` ASC) ,
  INDEX `IX_F9966D55` (`fromUserId` ASC) ,
  INDEX `IX_2A17A23F` (`fromUserId` ASC, `toUserId` ASC, `content`(767) ASC) ,
  INDEX `IX_16384BE6` (`toUserId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`chat_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`chat_status` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`chat_status` (
  `statusId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `modifiedDate` BIGINT(20) NULL DEFAULT NULL ,
  `online_` TINYINT(4) NULL DEFAULT NULL ,
  `awake` TINYINT(4) NULL DEFAULT NULL ,
  `activePanelId` VARCHAR(75) NULL DEFAULT NULL ,
  `message` LONGTEXT NULL DEFAULT NULL ,
  `playSound` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`statusId`) ,
  UNIQUE INDEX `IX_E17EBD79` (`userId` ASC) ,
  INDEX `IX_15BD544A` (`modifiedDate` ASC) ,
  INDEX `IX_B723B792` (`modifiedDate` ASC, `online_` ASC) ,
  INDEX `IX_32531B3D` (`online_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`classname_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`classname_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`classname_` (
  `classNameId` BIGINT(20) NOT NULL ,
  `value` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`classNameId`) ,
  UNIQUE INDEX `IX_B27A301F` (`value` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`clustergroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`clustergroup` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`clustergroup` (
  `clusterGroupId` BIGINT(20) NOT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `clusterNodeIds` VARCHAR(75) NULL DEFAULT NULL ,
  `wholeCluster` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`clusterGroupId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`company` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`company` (
  `companyId` BIGINT(20) NOT NULL ,
  `accountId` BIGINT(20) NULL DEFAULT NULL ,
  `webId` VARCHAR(75) NULL DEFAULT NULL ,
  `key_` LONGTEXT NULL DEFAULT NULL ,
  `virtualHost` VARCHAR(75) NULL DEFAULT NULL ,
  `mx` VARCHAR(75) NULL DEFAULT NULL ,
  `homeURL` LONGTEXT NULL DEFAULT NULL ,
  `logoId` BIGINT(20) NULL DEFAULT NULL ,
  `system` TINYINT(4) NULL DEFAULT NULL ,
  `maxUsers` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`companyId`) ,
  UNIQUE INDEX `IX_975996C0` (`virtualHost` ASC) ,
  UNIQUE INDEX `IX_EC00543C` (`webId` ASC) ,
  INDEX `IX_38EFE3FD` (`logoId` ASC) ,
  INDEX `IX_12566EC2` (`mx` ASC) ,
  INDEX `IX_35E3E7C6` (`system` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`contact_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`contact_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`contact_` (
  `contactId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `accountId` BIGINT(20) NULL DEFAULT NULL ,
  `parentContactId` BIGINT(20) NULL DEFAULT NULL ,
  `firstName` VARCHAR(75) NULL DEFAULT NULL ,
  `middleName` VARCHAR(75) NULL DEFAULT NULL ,
  `lastName` VARCHAR(75) NULL DEFAULT NULL ,
  `prefixId` INT(11) NULL DEFAULT NULL ,
  `suffixId` INT(11) NULL DEFAULT NULL ,
  `male` TINYINT(4) NULL DEFAULT NULL ,
  `birthday` DATETIME NULL DEFAULT NULL ,
  `smsSn` VARCHAR(75) NULL DEFAULT NULL ,
  `aimSn` VARCHAR(75) NULL DEFAULT NULL ,
  `facebookSn` VARCHAR(75) NULL DEFAULT NULL ,
  `icqSn` VARCHAR(75) NULL DEFAULT NULL ,
  `jabberSn` VARCHAR(75) NULL DEFAULT NULL ,
  `msnSn` VARCHAR(75) NULL DEFAULT NULL ,
  `mySpaceSn` VARCHAR(75) NULL DEFAULT NULL ,
  `skypeSn` VARCHAR(75) NULL DEFAULT NULL ,
  `twitterSn` VARCHAR(75) NULL DEFAULT NULL ,
  `ymSn` VARCHAR(75) NULL DEFAULT NULL ,
  `employeeStatusId` VARCHAR(75) NULL DEFAULT NULL ,
  `employeeNumber` VARCHAR(75) NULL DEFAULT NULL ,
  `jobTitle` VARCHAR(100) NULL DEFAULT NULL ,
  `jobClass` VARCHAR(75) NULL DEFAULT NULL ,
  `hoursOfOperation` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`contactId`) ,
  INDEX `IX_66D496A3` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`counter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`counter` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`counter` (
  `name` VARCHAR(75) NOT NULL ,
  `currentId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`country` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`country` (
  `countryId` BIGINT(20) NOT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `a2` VARCHAR(75) NULL DEFAULT NULL ,
  `a3` VARCHAR(75) NULL DEFAULT NULL ,
  `number_` VARCHAR(75) NULL DEFAULT NULL ,
  `idd_` VARCHAR(75) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`countryId`) ,
  UNIQUE INDEX `IX_717B97E1` (`a2` ASC) ,
  UNIQUE INDEX `IX_717B9BA2` (`a3` ASC) ,
  UNIQUE INDEX `IX_19DA007B` (`name` ASC) ,
  INDEX `IX_25D734CD` (`active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`cyrususer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`cyrususer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`cyrususer` (
  `userId` VARCHAR(75) NOT NULL ,
  `password_` VARCHAR(75) NOT NULL ,
  PRIMARY KEY (`userId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`cyrusvirtual`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`cyrusvirtual` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`cyrusvirtual` (
  `emailAddress` VARCHAR(75) NOT NULL ,
  `userId` VARCHAR(75) NOT NULL ,
  PRIMARY KEY (`emailAddress`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`dlfileentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`dlfileentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`dlfileentry` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `fileEntryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `versionUserId` BIGINT(20) NULL DEFAULT NULL ,
  `versionUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `extension` VARCHAR(75) NULL DEFAULT NULL ,
  `title` VARCHAR(255) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `extraSettings` LONGTEXT NULL DEFAULT NULL ,
  `version` VARCHAR(75) NULL DEFAULT NULL ,
  `size_` BIGINT(20) NULL DEFAULT NULL ,
  `readCount` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`fileEntryId`) ,
  UNIQUE INDEX `IX_5391712` (`groupId` ASC, `folderId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_ED5CA615` (`groupId` ASC, `folderId` ASC, `title` ASC) ,
  UNIQUE INDEX `IX_BC2E7E6A` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_4CB1B2B4` (`companyId` ASC) ,
  INDEX `IX_F4AF5636` (`groupId` ASC) ,
  INDEX `IX_93CF8193` (`groupId` ASC, `folderId` ASC) ,
  INDEX `IX_43261870` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_D20C434D` (`groupId` ASC, `userId` ASC, `folderId` ASC) ,
  INDEX `IX_64F0FE40` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`dlfilerank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`dlfilerank` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`dlfilerank` (
  `fileRankId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`fileRankId`) ,
  UNIQUE INDEX `IX_CE705D48` (`companyId` ASC, `userId` ASC, `folderId` ASC, `name` ASC) ,
  INDEX `IX_40B56512` (`folderId` ASC, `name` ASC) ,
  INDEX `IX_BAFB116E` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_EED06670` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`dlfileshortcut`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`dlfileshortcut` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`dlfileshortcut` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `fileShortcutId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `toFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `toName` VARCHAR(255) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`fileShortcutId`) ,
  UNIQUE INDEX `IX_FDB4A946` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_B0051937` (`groupId` ASC, `folderId` ASC) ,
  INDEX `IX_ECCE311D` (`groupId` ASC, `folderId` ASC, `status` ASC) ,
  INDEX `IX_55C736AC` (`groupId` ASC, `toFolderId` ASC, `toName` ASC) ,
  INDEX `IX_346A0992` (`groupId` ASC, `toFolderId` ASC, `toName` ASC, `status` ASC) ,
  INDEX `IX_4831EBE4` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`dlfileversion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`dlfileversion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`dlfileversion` (
  `fileVersionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `extension` VARCHAR(75) NULL DEFAULT NULL ,
  `title` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `changeLog` VARCHAR(75) NULL DEFAULT NULL ,
  `extraSettings` VARCHAR(75) NULL DEFAULT NULL ,
  `version` VARCHAR(75) NULL DEFAULT NULL ,
  `size_` BIGINT(20) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`fileVersionId`) ,
  UNIQUE INDEX `IX_2F8FED9C` (`groupId` ASC, `folderId` ASC, `name` ASC, `version` ASC) ,
  INDEX `IX_B413F1EC` (`groupId` ASC, `folderId` ASC, `name` ASC) ,
  INDEX `IX_94E784D2` (`groupId` ASC, `folderId` ASC, `name` ASC, `status` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`dlfolder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`dlfolder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`dlfolder` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`folderId`) ,
  UNIQUE INDEX `IX_902FD874` (`groupId` ASC, `parentFolderId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_3CC1DED2` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_A74DB14C` (`companyId` ASC) ,
  INDEX `IX_F2EA1ACE` (`groupId` ASC) ,
  INDEX `IX_49C37475` (`groupId` ASC, `parentFolderId` ASC) ,
  INDEX `IX_51556082` (`parentFolderId` ASC, `name` ASC) ,
  INDEX `IX_CBC408D8` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`emailaddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`emailaddress` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`emailaddress` (
  `emailAddressId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `address` VARCHAR(75) NULL DEFAULT NULL ,
  `typeId` INT(11) NULL DEFAULT NULL ,
  `primary_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`emailAddressId`) ,
  INDEX `IX_1BB072CA` (`companyId` ASC) ,
  INDEX `IX_49D2DEC4` (`companyId` ASC, `classNameId` ASC) ,
  INDEX `IX_551A519F` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_2A2CB130` (`companyId` ASC, `classNameId` ASC, `classPK` ASC, `primary_` ASC) ,
  INDEX `IX_7B43CD8` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`expandocolumn`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`expandocolumn` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`expandocolumn` (
  `columnId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `tableId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `defaultData` LONGTEXT NULL DEFAULT NULL ,
  `typeSettings` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`columnId`) ,
  UNIQUE INDEX `IX_FEFC8DA7` (`tableId` ASC, `name` ASC) ,
  INDEX `IX_A8C0CBE8` (`tableId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`expandorow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`expandorow` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`expandorow` (
  `rowId_` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `tableId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`rowId_`) ,
  UNIQUE INDEX `IX_81EFBFF5` (`tableId` ASC, `classPK` ASC) ,
  INDEX `IX_D3F5D7AE` (`tableId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`expandotable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`expandotable` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`expandotable` (
  `tableId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`tableId`) ,
  UNIQUE INDEX `IX_37562284` (`companyId` ASC, `classNameId` ASC, `name` ASC) ,
  INDEX `IX_B5AE8A85` (`companyId` ASC, `classNameId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`expandovalue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`expandovalue` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`expandovalue` (
  `valueId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `tableId` BIGINT(20) NULL DEFAULT NULL ,
  `columnId` BIGINT(20) NULL DEFAULT NULL ,
  `rowId_` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `data_` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`valueId`) ,
  UNIQUE INDEX `IX_9DDD21E5` (`columnId` ASC, `rowId_` ASC) ,
  UNIQUE INDEX `IX_D27B03E7` (`tableId` ASC, `columnId` ASC, `classPK` ASC) ,
  INDEX `IX_B29FEF17` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_F7DD0987` (`columnId` ASC) ,
  INDEX `IX_9112A7A0` (`rowId_` ASC) ,
  INDEX `IX_F0566A77` (`tableId` ASC) ,
  INDEX `IX_1BD3F4C` (`tableId` ASC, `classPK` ASC) ,
  INDEX `IX_CA9AFB7C` (`tableId` ASC, `columnId` ASC) ,
  INDEX `IX_B71E92D5` (`tableId` ASC, `rowId_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`group_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`group_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`group_` (
  `groupId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `creatorUserId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `parentGroupId` BIGINT(20) NULL DEFAULT NULL ,
  `liveGroupId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `typeSettings` LONGTEXT NULL DEFAULT NULL ,
  `friendlyURL` VARCHAR(100) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`groupId`) ,
  UNIQUE INDEX `IX_D0D5E397` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  UNIQUE INDEX `IX_5DE0BE11` (`companyId` ASC, `classNameId` ASC, `liveGroupId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_5BDDB872` (`companyId` ASC, `friendlyURL` ASC) ,
  UNIQUE INDEX `IX_BBCA55B` (`companyId` ASC, `liveGroupId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_5AA68501` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_ABA5CEC2` (`companyId` ASC) ,
  INDEX `IX_16218A38` (`liveGroupId` ASC) ,
  INDEX `IX_7B590A7A` (`type_` ASC, `active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`groups_orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`groups_orgs` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`groups_orgs` (
  `groupId` BIGINT(20) NOT NULL ,
  `organizationId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`groupId`, `organizationId`) ,
  INDEX `IX_75267DCA` (`groupId` ASC) ,
  INDEX `IX_6BBB7682` (`organizationId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`groups_permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`groups_permissions` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`groups_permissions` (
  `groupId` BIGINT(20) NOT NULL ,
  `permissionId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`groupId`, `permissionId`) ,
  INDEX `IX_C48736B` (`groupId` ASC) ,
  INDEX `IX_EC97689D` (`permissionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`groups_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`groups_roles` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`groups_roles` (
  `groupId` BIGINT(20) NOT NULL ,
  `roleId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`groupId`, `roleId`) ,
  INDEX `IX_84471FD2` (`groupId` ASC) ,
  INDEX `IX_3103EF3D` (`roleId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`groups_usergroups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`groups_usergroups` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`groups_usergroups` (
  `groupId` BIGINT(20) NOT NULL ,
  `userGroupId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`groupId`, `userGroupId`) ,
  INDEX `IX_31FB749A` (`groupId` ASC) ,
  INDEX `IX_3B69160F` (`userGroupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`igfolder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`igfolder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`igfolder` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`folderId`) ,
  UNIQUE INDEX `IX_9BBAFB1E` (`groupId` ASC, `parentFolderId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_B10EFD68` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_60214CF6` (`companyId` ASC) ,
  INDEX `IX_206498F8` (`groupId` ASC) ,
  INDEX `IX_1A605E9F` (`groupId` ASC, `parentFolderId` ASC) ,
  INDEX `IX_F73C0982` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`igimage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`igimage` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`igimage` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `imageId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `smallImageId` BIGINT(20) NULL DEFAULT NULL ,
  `largeImageId` BIGINT(20) NULL DEFAULT NULL ,
  `custom1ImageId` BIGINT(20) NULL DEFAULT NULL ,
  `custom2ImageId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`imageId`) ,
  UNIQUE INDEX `IX_E97342D9` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_E597322D` (`custom1ImageId` ASC) ,
  INDEX `IX_D9E0A34C` (`custom2ImageId` ASC) ,
  INDEX `IX_63820A7` (`groupId` ASC) ,
  INDEX `IX_8956B2C4` (`groupId` ASC, `folderId` ASC) ,
  INDEX `IX_AAE8DF83` (`groupId` ASC, `folderId` ASC, `name` ASC) ,
  INDEX `IX_BE79E1E1` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_64F0B572` (`largeImageId` ASC) ,
  INDEX `IX_D3D32126` (`smallImageId` ASC) ,
  INDEX `IX_265BB0F1` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`image` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`image` (
  `imageId` BIGINT(20) NOT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `text_` LONGTEXT NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `height` INT(11) NULL DEFAULT NULL ,
  `width` INT(11) NULL DEFAULT NULL ,
  `size_` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`imageId`) ,
  INDEX `IX_6A925A4D` (`size_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalarticle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalarticle` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalarticle` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `id_` BIGINT(20) NOT NULL ,
  `resourcePrimKey` BIGINT(20) NULL DEFAULT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `articleId` VARCHAR(75) NULL DEFAULT NULL ,
  `version` DOUBLE NULL DEFAULT NULL ,
  `title` VARCHAR(300) NULL DEFAULT NULL ,
  `urlTitle` VARCHAR(150) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `content` LONGTEXT NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `structureId` VARCHAR(75) NULL DEFAULT NULL ,
  `templateId` VARCHAR(75) NULL DEFAULT NULL ,
  `displayDate` DATETIME NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  `reviewDate` DATETIME NULL DEFAULT NULL ,
  `indexable` TINYINT(4) NULL DEFAULT NULL ,
  `smallImage` TINYINT(4) NULL DEFAULT NULL ,
  `smallImageId` BIGINT(20) NULL DEFAULT NULL ,
  `smallImageURL` LONGTEXT NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id_`) ,
  UNIQUE INDEX `IX_85C52EEC` (`groupId` ASC, `articleId` ASC, `version` ASC) ,
  UNIQUE INDEX `IX_3463D95B` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_DFF98523` (`companyId` ASC) ,
  INDEX `IX_323DF109` (`companyId` ASC, `status` ASC) ,
  INDEX `IX_9356F865` (`groupId` ASC) ,
  INDEX `IX_68C0F69C` (`groupId` ASC, `articleId` ASC) ,
  INDEX `IX_4D5CD982` (`groupId` ASC, `articleId` ASC, `status` ASC) ,
  INDEX `IX_301D024B` (`groupId` ASC, `status` ASC) ,
  INDEX `IX_2E207659` (`groupId` ASC, `structureId` ASC) ,
  INDEX `IX_8DEAE14E` (`groupId` ASC, `templateId` ASC) ,
  INDEX `IX_22882D02` (`groupId` ASC, `urlTitle` ASC) ,
  INDEX `IX_D2D249E8` (`groupId` ASC, `urlTitle` ASC, `status` ASC) ,
  INDEX `IX_33F49D16` (`resourcePrimKey` ASC) ,
  INDEX `IX_3E2765FC` (`resourcePrimKey` ASC, `status` ASC) ,
  INDEX `IX_EF9B7028` (`smallImageId` ASC) ,
  INDEX `IX_F029602F` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalarticleimage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalarticleimage` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalarticleimage` (
  `articleImageId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `articleId` VARCHAR(75) NULL DEFAULT NULL ,
  `version` DOUBLE NULL DEFAULT NULL ,
  `elInstanceId` VARCHAR(75) NULL DEFAULT NULL ,
  `elName` VARCHAR(75) NULL DEFAULT NULL ,
  `languageId` VARCHAR(75) NULL DEFAULT NULL ,
  `tempImage` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`articleImageId`) ,
  UNIQUE INDEX `IX_103D6207` (`groupId` ASC, `articleId` ASC, `version` ASC, `elInstanceId` ASC, `elName` ASC, `languageId` ASC) ,
  INDEX `IX_3B51BB68` (`groupId` ASC) ,
  INDEX `IX_158B526F` (`groupId` ASC, `articleId` ASC, `version` ASC) ,
  INDEX `IX_D4121315` (`tempImage` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalarticleresource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalarticleresource` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalarticleresource` (
  `resourcePrimKey` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `articleId` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`resourcePrimKey`) ,
  UNIQUE INDEX `IX_88DF994A` (`groupId` ASC, `articleId` ASC) ,
  INDEX `IX_F8433677` (`groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalcontentsearch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalcontentsearch` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalcontentsearch` (
  `contentSearchId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `privateLayout` TINYINT(4) NULL DEFAULT NULL ,
  `layoutId` BIGINT(20) NULL DEFAULT NULL ,
  `portletId` VARCHAR(200) NULL DEFAULT NULL ,
  `articleId` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`contentSearchId`) ,
  UNIQUE INDEX `IX_C3AA93B8` (`groupId` ASC, `privateLayout` ASC, `layoutId` ASC, `portletId` ASC, `articleId` ASC) ,
  INDEX `IX_9207CB31` (`articleId` ASC) ,
  INDEX `IX_6838E427` (`groupId` ASC, `articleId` ASC) ,
  INDEX `IX_20962903` (`groupId` ASC, `privateLayout` ASC) ,
  INDEX `IX_7CC7D73E` (`groupId` ASC, `privateLayout` ASC, `articleId` ASC) ,
  INDEX `IX_B3B318DC` (`groupId` ASC, `privateLayout` ASC, `layoutId` ASC) ,
  INDEX `IX_7ACC74C9` (`groupId` ASC, `privateLayout` ASC, `layoutId` ASC, `portletId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalfeed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalfeed` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalfeed` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `id_` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `feedId` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `structureId` VARCHAR(75) NULL DEFAULT NULL ,
  `templateId` VARCHAR(75) NULL DEFAULT NULL ,
  `rendererTemplateId` VARCHAR(75) NULL DEFAULT NULL ,
  `delta` INT(11) NULL DEFAULT NULL ,
  `orderByCol` VARCHAR(75) NULL DEFAULT NULL ,
  `orderByType` VARCHAR(75) NULL DEFAULT NULL ,
  `targetLayoutFriendlyUrl` VARCHAR(255) NULL DEFAULT NULL ,
  `targetPortletId` VARCHAR(75) NULL DEFAULT NULL ,
  `contentField` VARCHAR(75) NULL DEFAULT NULL ,
  `feedType` VARCHAR(75) NULL DEFAULT NULL ,
  `feedVersion` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`id_`) ,
  UNIQUE INDEX `IX_65576CBC` (`groupId` ASC, `feedId` ASC) ,
  UNIQUE INDEX `IX_39031F51` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_35A2DB2F` (`groupId` ASC) ,
  INDEX `IX_50C36D79` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journalstructure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journalstructure` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journalstructure` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `id_` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `structureId` VARCHAR(75) NULL DEFAULT NULL ,
  `parentStructureId` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `xsd` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`id_`) ,
  UNIQUE INDEX `IX_AB6E9996` (`groupId` ASC, `structureId` ASC) ,
  UNIQUE INDEX `IX_42E86E58` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_B97F5608` (`groupId` ASC) ,
  INDEX `IX_CA0BD48C` (`groupId` ASC, `parentStructureId` ASC) ,
  INDEX `IX_8831E4FC` (`structureId` ASC) ,
  INDEX `IX_6702CA92` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`journaltemplate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`journaltemplate` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`journaltemplate` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `id_` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `templateId` VARCHAR(75) NULL DEFAULT NULL ,
  `structureId` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `xsl` LONGTEXT NULL DEFAULT NULL ,
  `langType` VARCHAR(75) NULL DEFAULT NULL ,
  `cacheable` TINYINT(4) NULL DEFAULT NULL ,
  `smallImage` TINYINT(4) NULL DEFAULT NULL ,
  `smallImageId` BIGINT(20) NULL DEFAULT NULL ,
  `smallImageURL` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`id_`) ,
  UNIQUE INDEX `IX_E802AA3C` (`groupId` ASC, `templateId` ASC) ,
  UNIQUE INDEX `IX_62D1B3AD` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_77923653` (`groupId` ASC) ,
  INDEX `IX_1701CB2B` (`groupId` ASC, `structureId` ASC) ,
  INDEX `IX_25FFB6FA` (`smallImageId` ASC) ,
  INDEX `IX_1B12CA20` (`templateId` ASC) ,
  INDEX `IX_2857419D` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleoaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleoaction` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleoaction` (
  `kaleoActionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` VARCHAR(2000) NULL DEFAULT NULL ,
  `executionType` VARCHAR(20) NULL DEFAULT NULL ,
  `script` LONGTEXT NULL DEFAULT NULL ,
  `scriptLanguage` VARCHAR(75) NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoActionId`) ,
  INDEX `IX_50E9112C` (`companyId` ASC) ,
  INDEX `IX_F95A622` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_B88DF9B1` (`kaleoNodeId` ASC, `executionType` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleodefinition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleodefinition` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleodefinition` (
  `kaleoDefinitionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `description` VARCHAR(2000) NULL DEFAULT NULL ,
  `version` INT(11) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  `startKaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoDefinitionId`) ,
  INDEX `IX_40B9112F` (`companyId` ASC) ,
  INDEX `IX_408542BA` (`companyId` ASC, `active_` ASC) ,
  INDEX `IX_76C781AE` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_4C23F11B` (`companyId` ASC, `name` ASC, `active_` ASC) ,
  INDEX `IX_EC14F81A` (`companyId` ASC, `name` ASC, `version` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleoinstance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleoinstance` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleoinstance` (
  `kaleoInstanceId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoDefinitionName` VARCHAR(200) NULL DEFAULT NULL ,
  `kaleoDefinitionVersion` INT(11) NULL DEFAULT NULL ,
  `rootKaleoInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `className` VARCHAR(200) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `completed` TINYINT(4) NULL DEFAULT NULL ,
  `completionDate` DATETIME NULL DEFAULT NULL ,
  `workflowContext` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoInstanceId`) ,
  INDEX `IX_5F2FCD2D` (`companyId` ASC) ,
  INDEX `IX_BF5839F8` (`companyId` ASC, `kaleoDefinitionName` ASC, `kaleoDefinitionVersion` ASC, `completionDate` ASC) ,
  INDEX `IX_4DA4D123` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_ACF16238` (`kaleoDefinitionId` ASC, `completed` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleoinstancetoken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleoinstancetoken` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleoinstancetoken` (
  `kaleoInstanceTokenId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceId` BIGINT(20) NULL DEFAULT NULL ,
  `parentKaleoInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `currentKaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `currentKaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `className` VARCHAR(75) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `completed` TINYINT(4) NULL DEFAULT NULL ,
  `completionDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoInstanceTokenId`) ,
  INDEX `IX_153721BE` (`companyId` ASC) ,
  INDEX `IX_4A86923B` (`companyId` ASC, `parentKaleoInstanceTokenId` ASC) ,
  INDEX `IX_360D34D9` (`companyId` ASC, `parentKaleoInstanceTokenId` ASC, `completionDate` ASC) ,
  INDEX `IX_7BDB04B4` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_F42AAFF6` (`kaleoInstanceId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleolog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleolog` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleolog` (
  `kaleoLogId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `terminalKaleoNode` TINYINT(4) NULL DEFAULT NULL ,
  `kaleoActionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoActionName` VARCHAR(200) NULL DEFAULT NULL ,
  `kaleoActionDescription` VARCHAR(2000) NULL DEFAULT NULL ,
  `previousKaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `previousKaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `previousAssigneeClassName` VARCHAR(200) NULL DEFAULT NULL ,
  `previousAssigneeClassPK` BIGINT(20) NULL DEFAULT NULL ,
  `currentAssigneeClassName` VARCHAR(200) NULL DEFAULT NULL ,
  `currentAssigneeClassPK` BIGINT(20) NULL DEFAULT NULL ,
  `type_` VARCHAR(50) NULL DEFAULT NULL ,
  `comment_` VARCHAR(2000) NULL DEFAULT NULL ,
  `startDate` DATETIME NULL DEFAULT NULL ,
  `endDate` DATETIME NULL DEFAULT NULL ,
  `duration` BIGINT(20) NULL DEFAULT NULL ,
  `workflowContext` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoLogId`) ,
  INDEX `IX_73B5F4DE` (`companyId` ASC) ,
  INDEX `IX_6C64B7D4` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_5BC6AB16` (`kaleoInstanceId` ASC) ,
  INDEX `IX_25157F25` (`kaleoInstanceTokenId` ASC, `kaleoNodeId` ASC, `type_` ASC) ,
  INDEX `IX_470B9FF8` (`kaleoInstanceTokenId` ASC, `type_` ASC) ,
  INDEX `IX_B0CDCA38` (`kaleoTaskInstanceTokenId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleonode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleonode` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleonode` (
  `kaleoNodeId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` VARCHAR(2000) NULL DEFAULT NULL ,
  `type_` VARCHAR(20) NULL DEFAULT NULL ,
  `initial_` TINYINT(4) NULL DEFAULT NULL ,
  `terminal` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoNodeId`) ,
  INDEX `IX_C4E9ACE0` (`companyId` ASC) ,
  INDEX `IX_F28C443E` (`companyId` ASC, `kaleoDefinitionId` ASC) ,
  INDEX `IX_32E94DD6` (`kaleoDefinitionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleonotification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleonotification` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleonotification` (
  `kaleoNotificationId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` VARCHAR(2000) NULL DEFAULT NULL ,
  `executionType` VARCHAR(20) NULL DEFAULT NULL ,
  `template` LONGTEXT NULL DEFAULT NULL ,
  `templateLanguage` VARCHAR(75) NULL DEFAULT NULL ,
  `notificationTypes` VARCHAR(25) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoNotificationId`) ,
  INDEX `IX_38829497` (`companyId` ASC) ,
  INDEX `IX_4B968E8D` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_A5C459A6` (`kaleoNodeId` ASC, `executionType` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleonotificationrecipient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleonotificationrecipient` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleonotificationrecipient` (
  `kaleoNotificationRecipientId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNotificationId` BIGINT(20) NULL DEFAULT NULL ,
  `recipientClassName` VARCHAR(75) NULL DEFAULT NULL ,
  `recipientClassPK` BIGINT(20) NULL DEFAULT NULL ,
  `recipientRoleType` INT(11) NULL DEFAULT NULL ,
  `address` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoNotificationRecipientId`) ,
  INDEX `IX_2C8C4AF4` (`companyId` ASC) ,
  INDEX `IX_AA6697EA` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_7F4FED02` (`kaleoNotificationId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleotask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleotask` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleotask` (
  `kaleoTaskId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` VARCHAR(75) NULL DEFAULT NULL ,
  `dueDateDuration` DOUBLE NULL DEFAULT NULL ,
  `dueDateScale` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoTaskId`) ,
  INDEX `IX_E1F8B23D` (`companyId` ASC) ,
  INDEX `IX_3FFA633` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_77B3F1A2` (`kaleoNodeId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleotaskassignment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleotaskassignment` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleotaskassignment` (
  `kaleoTaskAssignmentId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskId` BIGINT(20) NULL DEFAULT NULL ,
  `assigneeClassName` VARCHAR(200) NULL DEFAULT NULL ,
  `assigneeClassPK` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoTaskAssignmentId`) ,
  INDEX `IX_81225C04` (`assigneeClassName` ASC, `kaleoTaskId` ASC) ,
  INDEX `IX_611732B0` (`companyId` ASC) ,
  INDEX `IX_575C03A6` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_4DD12F58` (`kaleoTaskId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleotaskassignmentinstance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleotaskassignmentinstance` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleotaskassignmentinstance` (
  `kaleoTaskAssignmentInstanceId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskName` VARCHAR(75) NULL DEFAULT NULL ,
  `assigneeClassName` VARCHAR(75) NULL DEFAULT NULL ,
  `assigneeClassPK` BIGINT(20) NULL DEFAULT NULL ,
  `completed` TINYINT(4) NULL DEFAULT NULL ,
  `completionDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoTaskAssignmentInstanceId`) ,
  INDEX `IX_6E3CDA1B` (`companyId` ASC) ,
  INDEX `IX_C851011` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_67A9EE93` (`kaleoInstanceId` ASC) ,
  INDEX `IX_D4C2235B` (`kaleoTaskInstanceTokenId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleotaskinstancetoken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleotaskinstancetoken` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleotaskinstancetoken` (
  `kaleoTaskInstanceTokenId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoInstanceTokenId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoTaskName` VARCHAR(200) NULL DEFAULT NULL ,
  `className` VARCHAR(75) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `completionUserId` BIGINT(20) NULL DEFAULT NULL ,
  `completed` TINYINT(4) NULL DEFAULT NULL ,
  `completionDate` DATETIME NULL DEFAULT NULL ,
  `dueDate` DATETIME NULL DEFAULT NULL ,
  `workflowContext` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoTaskInstanceTokenId`) ,
  INDEX `IX_997FE723` (`companyId` ASC) ,
  INDEX `IX_608E9519` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_2CE1159B` (`kaleoInstanceId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`kaleotransition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`kaleotransition` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`kaleotransition` (
  `kaleoTransitionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(200) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `kaleoDefinitionId` BIGINT(20) NULL DEFAULT NULL ,
  `kaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` VARCHAR(2000) NULL DEFAULT NULL ,
  `sourceKaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `sourceKaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `targetKaleoNodeId` BIGINT(20) NULL DEFAULT NULL ,
  `targetKaleoNodeName` VARCHAR(200) NULL DEFAULT NULL ,
  `defaultTransition` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`kaleoTransitionId`) ,
  INDEX `IX_41D6C6D` (`companyId` ASC) ,
  INDEX `IX_479F3063` (`kaleoDefinitionId` ASC) ,
  INDEX `IX_A392DFD2` (`kaleoNodeId` ASC) ,
  INDEX `IX_A38E2194` (`kaleoNodeId` ASC, `defaultTransition` ASC) ,
  INDEX `IX_85268A11` (`kaleoNodeId` ASC, `name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`layout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`layout` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`layout` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `plid` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `privateLayout` TINYINT(4) NULL DEFAULT NULL ,
  `layoutId` BIGINT(20) NULL DEFAULT NULL ,
  `parentLayoutId` BIGINT(20) NULL DEFAULT NULL ,
  `name` LONGTEXT NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `typeSettings` LONGTEXT NULL DEFAULT NULL ,
  `hidden_` TINYINT(4) NULL DEFAULT NULL ,
  `friendlyURL` VARCHAR(255) NULL DEFAULT NULL ,
  `iconImage` TINYINT(4) NULL DEFAULT NULL ,
  `iconImageId` BIGINT(20) NULL DEFAULT NULL ,
  `themeId` VARCHAR(75) NULL DEFAULT NULL ,
  `colorSchemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `wapThemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `wapColorSchemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `css` LONGTEXT NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  `layoutPrototypeId` BIGINT(20) NULL DEFAULT NULL ,
  `dlFolderId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`plid`) ,
  UNIQUE INDEX `IX_BC2C4231` (`groupId` ASC, `privateLayout` ASC, `friendlyURL` ASC) ,
  UNIQUE INDEX `IX_7162C27C` (`groupId` ASC, `privateLayout` ASC, `layoutId` ASC) ,
  UNIQUE INDEX `IX_CED31606` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_C7FBC998` (`companyId` ASC) ,
  INDEX `IX_FAD05595` (`dlFolderId` ASC) ,
  INDEX `IX_C099D61A` (`groupId` ASC) ,
  INDEX `IX_705F5AA3` (`groupId` ASC, `privateLayout` ASC) ,
  INDEX `IX_6DE88B06` (`groupId` ASC, `privateLayout` ASC, `parentLayoutId` ASC) ,
  INDEX `IX_1A1B61D2` (`groupId` ASC, `privateLayout` ASC, `type_` ASC) ,
  INDEX `IX_23922F7D` (`iconImageId` ASC) ,
  INDEX `IX_D0822724` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`layoutprototype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`layoutprototype` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`layoutprototype` (
  `layoutPrototypeId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `name` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `settings_` LONGTEXT NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`layoutPrototypeId`) ,
  INDEX `IX_30616AAA` (`companyId` ASC) ,
  INDEX `IX_557A639F` (`companyId` ASC, `active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`layoutset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`layoutset` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`layoutset` (
  `layoutSetId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `privateLayout` TINYINT(4) NULL DEFAULT NULL ,
  `logo` TINYINT(4) NULL DEFAULT NULL ,
  `logoId` BIGINT(20) NULL DEFAULT NULL ,
  `themeId` VARCHAR(75) NULL DEFAULT NULL ,
  `colorSchemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `wapThemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `wapColorSchemeId` VARCHAR(75) NULL DEFAULT NULL ,
  `css` LONGTEXT NULL DEFAULT NULL ,
  `pageCount` INT(11) NULL DEFAULT NULL ,
  `virtualHost` VARCHAR(75) NULL DEFAULT NULL ,
  `settings_` LONGTEXT NULL DEFAULT NULL ,
  `layoutSetPrototypeId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`layoutSetId`) ,
  UNIQUE INDEX `IX_48550691` (`groupId` ASC, `privateLayout` ASC) ,
  INDEX `IX_A40B8BEC` (`groupId` ASC) ,
  INDEX `IX_5ABC2905` (`virtualHost` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`layoutsetprototype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`layoutsetprototype` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`layoutsetprototype` (
  `layoutSetPrototypeId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `name` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `settings_` LONGTEXT NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`layoutSetPrototypeId`) ,
  INDEX `IX_55F63D98` (`companyId` ASC) ,
  INDEX `IX_9178FC71` (`companyId` ASC, `active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`listtype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`listtype` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`listtype` (
  `listTypeId` INT(11) NOT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`listTypeId`) ,
  INDEX `IX_2932DD37` (`type_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`lock_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`lock_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`lock_` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `lockId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `className` VARCHAR(75) NULL DEFAULT NULL ,
  `key_` VARCHAR(200) NULL DEFAULT NULL ,
  `owner` VARCHAR(75) NULL DEFAULT NULL ,
  `inheritable` TINYINT(4) NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`lockId`) ,
  INDEX `IX_228562AD` (`className` ASC, `key_` ASC) ,
  INDEX `IX_E3F1286B` (`expirationDate` ASC) ,
  INDEX `IX_13C5CD3A` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mail_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mail_account` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mail_account` (
  `accountId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `address` VARCHAR(75) NULL DEFAULT NULL ,
  `personalName` VARCHAR(75) NULL DEFAULT NULL ,
  `protocol` VARCHAR(75) NULL DEFAULT NULL ,
  `incomingHostName` VARCHAR(75) NULL DEFAULT NULL ,
  `incomingPort` INT(11) NULL DEFAULT NULL ,
  `incomingSecure` TINYINT(4) NULL DEFAULT NULL ,
  `outgoingHostName` VARCHAR(75) NULL DEFAULT NULL ,
  `outgoingPort` INT(11) NULL DEFAULT NULL ,
  `outgoingSecure` TINYINT(4) NULL DEFAULT NULL ,
  `login` VARCHAR(75) NULL DEFAULT NULL ,
  `password_` VARCHAR(75) NULL DEFAULT NULL ,
  `savePassword` TINYINT(4) NULL DEFAULT NULL ,
  `signature` VARCHAR(75) NULL DEFAULT NULL ,
  `useSignature` TINYINT(4) NULL DEFAULT NULL ,
  `folderPrefix` VARCHAR(75) NULL DEFAULT NULL ,
  `inboxFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `draftFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `sentFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `trashFolderId` BIGINT(20) NULL DEFAULT NULL ,
  `defaultSender` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`accountId`) ,
  INDEX `IX_C4F22765` (`userId` ASC) ,
  INDEX `IX_6B92F85F` (`userId` ASC, `address` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mail_attachment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mail_attachment` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mail_attachment` (
  `attachmentId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `accountId` BIGINT(20) NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `messageId` BIGINT(20) NULL DEFAULT NULL ,
  `contentPath` VARCHAR(75) NULL DEFAULT NULL ,
  `fileName` VARCHAR(75) NULL DEFAULT NULL ,
  `size_` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`attachmentId`) ,
  INDEX `IX_F661D061` (`messageId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mail_folder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mail_folder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mail_folder` (
  `folderId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `accountId` BIGINT(20) NULL DEFAULT NULL ,
  `fullName` VARCHAR(75) NULL DEFAULT NULL ,
  `displayName` VARCHAR(75) NULL DEFAULT NULL ,
  `remoteMessageCount` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`folderId`) ,
  INDEX `IX_3841821C` (`accountId` ASC) ,
  INDEX `IX_310E554A` (`accountId` ASC, `fullName` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mail_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mail_message` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mail_message` (
  `messageId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `accountId` BIGINT(20) NULL DEFAULT NULL ,
  `folderId` BIGINT(20) NULL DEFAULT NULL ,
  `sender` LONGTEXT NULL DEFAULT NULL ,
  `to_` LONGTEXT NULL DEFAULT NULL ,
  `cc` LONGTEXT NULL DEFAULT NULL ,
  `bcc` LONGTEXT NULL DEFAULT NULL ,
  `sentDate` DATETIME NULL DEFAULT NULL ,
  `subject` LONGTEXT NULL DEFAULT NULL ,
  `preview` VARCHAR(75) NULL DEFAULT NULL ,
  `body` LONGTEXT NULL DEFAULT NULL ,
  `flags` VARCHAR(75) NULL DEFAULT NULL ,
  `size_` BIGINT(20) NULL DEFAULT NULL ,
  `remoteMessageId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`messageId`) ,
  INDEX `IX_163EBD83` (`companyId` ASC) ,
  INDEX `IX_64F72622` (`folderId` ASC) ,
  INDEX `IX_200D262A` (`folderId` ASC, `remoteMessageId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbban`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbban` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbban` (
  `banId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `banUserId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`banId`) ,
  UNIQUE INDEX `IX_8ABC4E3B` (`groupId` ASC, `banUserId` ASC) ,
  INDEX `IX_69951A25` (`banUserId` ASC) ,
  INDEX `IX_5C3FF12A` (`groupId` ASC) ,
  INDEX `IX_48814BBA` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbcategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbcategory` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbcategory` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentCategoryId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `threadCount` INT(11) NULL DEFAULT NULL ,
  `messageCount` INT(11) NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`categoryId`) ,
  UNIQUE INDEX `IX_F7D28C2F` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_BC735DCF` (`companyId` ASC) ,
  INDEX `IX_BB870C11` (`groupId` ASC) ,
  INDEX `IX_ED292508` (`groupId` ASC, `parentCategoryId` ASC) ,
  INDEX `IX_C2626EDB` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbdiscussion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbdiscussion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbdiscussion` (
  `discussionId` BIGINT(20) NOT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `threadId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`discussionId`) ,
  UNIQUE INDEX `IX_33A4DE38` (`classNameId` ASC, `classPK` ASC) ,
  UNIQUE INDEX `IX_B5CA2DC` (`threadId` ASC) ,
  INDEX `IX_79D0120B` (`classNameId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbmailinglist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbmailinglist` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbmailinglist` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `mailingListId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NULL DEFAULT NULL ,
  `emailAddress` VARCHAR(75) NULL DEFAULT NULL ,
  `inProtocol` VARCHAR(75) NULL DEFAULT NULL ,
  `inServerName` VARCHAR(75) NULL DEFAULT NULL ,
  `inServerPort` INT(11) NULL DEFAULT NULL ,
  `inUseSSL` TINYINT(4) NULL DEFAULT NULL ,
  `inUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `inPassword` VARCHAR(75) NULL DEFAULT NULL ,
  `inReadInterval` INT(11) NULL DEFAULT NULL ,
  `outEmailAddress` VARCHAR(75) NULL DEFAULT NULL ,
  `outCustom` TINYINT(4) NULL DEFAULT NULL ,
  `outServerName` VARCHAR(75) NULL DEFAULT NULL ,
  `outServerPort` INT(11) NULL DEFAULT NULL ,
  `outUseSSL` TINYINT(4) NULL DEFAULT NULL ,
  `outUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `outPassword` VARCHAR(75) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`mailingListId`) ,
  UNIQUE INDEX `IX_76CE9CDD` (`groupId` ASC, `categoryId` ASC) ,
  UNIQUE INDEX `IX_E858F170` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_BFEB984F` (`active_` ASC) ,
  INDEX `IX_4115EC7A` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbmessage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbmessage` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbmessage` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `messageId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NULL DEFAULT NULL ,
  `threadId` BIGINT(20) NULL DEFAULT NULL ,
  `rootMessageId` BIGINT(20) NULL DEFAULT NULL ,
  `parentMessageId` BIGINT(20) NULL DEFAULT NULL ,
  `subject` VARCHAR(75) NULL DEFAULT NULL ,
  `body` LONGTEXT NULL DEFAULT NULL ,
  `attachments` TINYINT(4) NULL DEFAULT NULL ,
  `anonymous` TINYINT(4) NULL DEFAULT NULL ,
  `priority` DOUBLE NULL DEFAULT NULL ,
  `allowPingbacks` TINYINT(4) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`messageId`) ,
  UNIQUE INDEX `IX_8D12316E` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_51A8D44D` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_F6687633` (`classNameId` ASC, `classPK` ASC, `status` ASC) ,
  INDEX `IX_B1432D30` (`companyId` ASC) ,
  INDEX `IX_1AD93C16` (`companyId` ASC, `status` ASC) ,
  INDEX `IX_5B153FB2` (`groupId` ASC) ,
  INDEX `IX_1073AB9F` (`groupId` ASC, `categoryId` ASC) ,
  INDEX `IX_4257DB85` (`groupId` ASC, `categoryId` ASC, `status` ASC) ,
  INDEX `IX_B674AB58` (`groupId` ASC, `categoryId` ASC, `threadId` ASC) ,
  INDEX `IX_385E123E` (`groupId` ASC, `categoryId` ASC, `threadId` ASC, `status` ASC) ,
  INDEX `IX_ED39AC98` (`groupId` ASC, `status` ASC) ,
  INDEX `IX_8EB8C5EC` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_377858D2` (`groupId` ASC, `userId` ASC, `status` ASC) ,
  INDEX `IX_75B95071` (`threadId` ASC) ,
  INDEX `IX_A7038CD7` (`threadId` ASC, `parentMessageId` ASC) ,
  INDEX `IX_9DC8E57` (`threadId` ASC, `status` ASC) ,
  INDEX `IX_7A040C32` (`userId` ASC) ,
  INDEX `IX_C57B16BC` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbmessageflag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbmessageflag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbmessageflag` (
  `messageFlagId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `threadId` BIGINT(20) NULL DEFAULT NULL ,
  `messageId` BIGINT(20) NULL DEFAULT NULL ,
  `flag` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`messageFlagId`) ,
  UNIQUE INDEX `IX_E9EB6194` (`userId` ASC, `messageId` ASC, `flag` ASC) ,
  INDEX `IX_D180D4AE` (`messageId` ASC) ,
  INDEX `IX_A6973A8E` (`messageId` ASC, `flag` ASC) ,
  INDEX `IX_C1C9A8FD` (`threadId` ASC) ,
  INDEX `IX_3CFD579D` (`threadId` ASC, `flag` ASC) ,
  INDEX `IX_7B2917BE` (`userId` ASC) ,
  INDEX `IX_2EA537D7` (`userId` ASC, `threadId` ASC, `flag` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbstatsuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbstatsuser` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbstatsuser` (
  `statsUserId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `messageCount` INT(11) NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`statsUserId`) ,
  UNIQUE INDEX `IX_9168E2C9` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_A00A898F` (`groupId` ASC) ,
  INDEX `IX_FAB5A88B` (`groupId` ASC, `messageCount` ASC) ,
  INDEX `IX_847F92B5` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`mbthread`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`mbthread` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`mbthread` (
  `threadId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NULL DEFAULT NULL ,
  `rootMessageId` BIGINT(20) NULL DEFAULT NULL ,
  `messageCount` INT(11) NULL DEFAULT NULL ,
  `viewCount` INT(11) NULL DEFAULT NULL ,
  `lastPostByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  `priority` DOUBLE NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`threadId`) ,
  INDEX `IX_41F6DC8A` (`categoryId` ASC, `priority` ASC) ,
  INDEX `IX_95C0EA45` (`groupId` ASC) ,
  INDEX `IX_9A2D11B2` (`groupId` ASC, `categoryId` ASC) ,
  INDEX `IX_50F1904A` (`groupId` ASC, `categoryId` ASC, `lastPostDate` ASC) ,
  INDEX `IX_485F7E98` (`groupId` ASC, `categoryId` ASC, `status` ASC) ,
  INDEX `IX_E1E7142B` (`groupId` ASC, `status` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`membershiprequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`membershiprequest` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`membershiprequest` (
  `membershipRequestId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  `replyComments` LONGTEXT NULL DEFAULT NULL ,
  `replyDate` DATETIME NULL DEFAULT NULL ,
  `replierUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusId` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`membershipRequestId`) ,
  INDEX `IX_8A1CC4B` (`groupId` ASC) ,
  INDEX `IX_C28C72EC` (`groupId` ASC, `statusId` ASC) ,
  INDEX `IX_66D70879` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`opensocial_gadget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`opensocial_gadget` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`opensocial_gadget` (
  `gadgetId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`gadgetId`) ,
  INDEX `IX_729869EE` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`organization_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`organization_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`organization_` (
  `organizationId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `parentOrganizationId` BIGINT(20) NULL DEFAULT NULL ,
  `leftOrganizationId` BIGINT(20) NULL DEFAULT NULL ,
  `rightOrganizationId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `recursable` TINYINT(4) NULL DEFAULT NULL ,
  `regionId` BIGINT(20) NULL DEFAULT NULL ,
  `countryId` BIGINT(20) NULL DEFAULT NULL ,
  `statusId` INT(11) NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`organizationId`) ,
  UNIQUE INDEX `IX_E301BDF5` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_834BCEB6` (`companyId` ASC) ,
  INDEX `IX_418E4522` (`companyId` ASC, `parentOrganizationId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`orggrouppermission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`orggrouppermission` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`orggrouppermission` (
  `organizationId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NOT NULL ,
  `permissionId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`organizationId`, `groupId`, `permissionId`) ,
  INDEX `IX_A425F71A` (`groupId` ASC) ,
  INDEX `IX_6C53DA4E` (`permissionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`orggrouprole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`orggrouprole` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`orggrouprole` (
  `organizationId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NOT NULL ,
  `roleId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`organizationId`, `groupId`, `roleId`) ,
  INDEX `IX_4A527DD3` (`groupId` ASC) ,
  INDEX `IX_AB044D1C` (`roleId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`orglabor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`orglabor` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`orglabor` (
  `orgLaborId` BIGINT(20) NOT NULL ,
  `organizationId` BIGINT(20) NULL DEFAULT NULL ,
  `typeId` INT(11) NULL DEFAULT NULL ,
  `sunOpen` INT(11) NULL DEFAULT NULL ,
  `sunClose` INT(11) NULL DEFAULT NULL ,
  `monOpen` INT(11) NULL DEFAULT NULL ,
  `monClose` INT(11) NULL DEFAULT NULL ,
  `tueOpen` INT(11) NULL DEFAULT NULL ,
  `tueClose` INT(11) NULL DEFAULT NULL ,
  `wedOpen` INT(11) NULL DEFAULT NULL ,
  `wedClose` INT(11) NULL DEFAULT NULL ,
  `thuOpen` INT(11) NULL DEFAULT NULL ,
  `thuClose` INT(11) NULL DEFAULT NULL ,
  `friOpen` INT(11) NULL DEFAULT NULL ,
  `friClose` INT(11) NULL DEFAULT NULL ,
  `satOpen` INT(11) NULL DEFAULT NULL ,
  `satClose` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`orgLaborId`) ,
  INDEX `IX_6AF0D434` (`organizationId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`passwordpolicy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`passwordpolicy` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`passwordpolicy` (
  `passwordPolicyId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `defaultPolicy` TINYINT(4) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `changeable` TINYINT(4) NULL DEFAULT NULL ,
  `changeRequired` TINYINT(4) NULL DEFAULT NULL ,
  `minAge` BIGINT(20) NULL DEFAULT NULL ,
  `checkSyntax` TINYINT(4) NULL DEFAULT NULL ,
  `allowDictionaryWords` TINYINT(4) NULL DEFAULT NULL ,
  `minAlphanumeric` INT(11) NULL DEFAULT NULL ,
  `minLength` INT(11) NULL DEFAULT NULL ,
  `minLowerCase` INT(11) NULL DEFAULT NULL ,
  `minNumbers` INT(11) NULL DEFAULT NULL ,
  `minSymbols` INT(11) NULL DEFAULT NULL ,
  `minUpperCase` INT(11) NULL DEFAULT NULL ,
  `history` TINYINT(4) NULL DEFAULT NULL ,
  `historyCount` INT(11) NULL DEFAULT NULL ,
  `expireable` TINYINT(4) NULL DEFAULT NULL ,
  `maxAge` BIGINT(20) NULL DEFAULT NULL ,
  `warningTime` BIGINT(20) NULL DEFAULT NULL ,
  `graceLimit` INT(11) NULL DEFAULT NULL ,
  `lockout` TINYINT(4) NULL DEFAULT NULL ,
  `maxFailure` INT(11) NULL DEFAULT NULL ,
  `lockoutDuration` BIGINT(20) NULL DEFAULT NULL ,
  `requireUnlock` TINYINT(4) NULL DEFAULT NULL ,
  `resetFailureCount` BIGINT(20) NULL DEFAULT NULL ,
  `resetTicketMaxAge` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`passwordPolicyId`) ,
  UNIQUE INDEX `IX_3FBFA9F4` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_2C1142E` (`companyId` ASC, `defaultPolicy` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`passwordpolicyrel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`passwordpolicyrel` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`passwordpolicyrel` (
  `passwordPolicyRelId` BIGINT(20) NOT NULL ,
  `passwordPolicyId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`passwordPolicyRelId`) ,
  INDEX `IX_C3A17327` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_CD25266E` (`passwordPolicyId` ASC) ,
  INDEX `IX_ED7CF243` (`passwordPolicyId` ASC, `classNameId` ASC, `classPK` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`passwordtracker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`passwordtracker` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`passwordtracker` (
  `passwordTrackerId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `password_` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`passwordTrackerId`) ,
  INDEX `IX_326F75BD` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`permission_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`permission_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`permission_` (
  `permissionId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `actionId` VARCHAR(75) NULL DEFAULT NULL ,
  `resourceId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`permissionId`) ,
  UNIQUE INDEX `IX_4D19C2B8` (`actionId` ASC, `resourceId` ASC) ,
  INDEX `IX_F090C113` (`resourceId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`phone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`phone` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`phone` (
  `phoneId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `number_` VARCHAR(75) NULL DEFAULT NULL ,
  `extension` VARCHAR(75) NULL DEFAULT NULL ,
  `typeId` INT(11) NULL DEFAULT NULL ,
  `primary_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`phoneId`) ,
  INDEX `IX_9F704A14` (`companyId` ASC) ,
  INDEX `IX_A2E4AFBA` (`companyId` ASC, `classNameId` ASC) ,
  INDEX `IX_9A53569` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_812CE07A` (`companyId` ASC, `classNameId` ASC, `classPK` ASC, `primary_` ASC) ,
  INDEX `IX_F202B9CE` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`pluginsetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`pluginsetting` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`pluginsetting` (
  `pluginSettingId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `pluginId` VARCHAR(75) NULL DEFAULT NULL ,
  `pluginType` VARCHAR(75) NULL DEFAULT NULL ,
  `roles` LONGTEXT NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`pluginSettingId`) ,
  UNIQUE INDEX `IX_7171B2E8` (`companyId` ASC, `pluginId` ASC, `pluginType` ASC) ,
  INDEX `IX_B9746445` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`pollschoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`pollschoice` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`pollschoice` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `choiceId` BIGINT(20) NOT NULL ,
  `questionId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`choiceId`) ,
  UNIQUE INDEX `IX_D76DD2CF` (`questionId` ASC, `name` ASC) ,
  INDEX `IX_EC370F10` (`questionId` ASC) ,
  INDEX `IX_6660B399` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`pollsquestion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`pollsquestion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`pollsquestion` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `questionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  `lastVoteDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`questionId`) ,
  UNIQUE INDEX `IX_F3C9F36` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_9FF342EA` (`groupId` ASC) ,
  INDEX `IX_51F087F4` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`pollsvote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`pollsvote` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`pollsvote` (
  `voteId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `questionId` BIGINT(20) NULL DEFAULT NULL ,
  `choiceId` BIGINT(20) NULL DEFAULT NULL ,
  `voteDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`voteId`) ,
  UNIQUE INDEX `IX_1BBFD4D3` (`questionId` ASC, `userId` ASC) ,
  INDEX `IX_D5DF7B54` (`choiceId` ASC) ,
  INDEX `IX_12112599` (`questionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`portlet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`portlet` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`portlet` (
  `id_` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `portletId` VARCHAR(200) NULL DEFAULT NULL ,
  `roles` LONGTEXT NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_`) ,
  UNIQUE INDEX `IX_12B5E51D` (`companyId` ASC, `portletId` ASC) ,
  INDEX `IX_80CC9508` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`portletitem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`portletitem` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`portletitem` (
  `portletItemId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `portletId` VARCHAR(75) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`portletItemId`) ,
  INDEX `IX_96BDD537` (`groupId` ASC, `classNameId` ASC) ,
  INDEX `IX_D699243F` (`groupId` ASC, `name` ASC, `portletId` ASC, `classNameId` ASC) ,
  INDEX `IX_2C61314E` (`groupId` ASC, `portletId` ASC) ,
  INDEX `IX_E922D6C0` (`groupId` ASC, `portletId` ASC, `classNameId` ASC) ,
  INDEX `IX_8E71167F` (`groupId` ASC, `portletId` ASC, `classNameId` ASC, `name` ASC) ,
  INDEX `IX_33B8CE8D` (`groupId` ASC, `portletId` ASC, `name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`portletpreferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`portletpreferences` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`portletpreferences` (
  `portletPreferencesId` BIGINT(20) NOT NULL ,
  `ownerId` BIGINT(20) NULL DEFAULT NULL ,
  `ownerType` INT(11) NULL DEFAULT NULL ,
  `plid` BIGINT(20) NULL DEFAULT NULL ,
  `portletId` VARCHAR(200) NULL DEFAULT NULL ,
  `preferences` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`portletPreferencesId`) ,
  UNIQUE INDEX `IX_C7057FF7` (`ownerId` ASC, `ownerType` ASC, `plid` ASC, `portletId` ASC) ,
  INDEX `IX_E4F13E6E` (`ownerId` ASC, `ownerType` ASC, `plid` ASC) ,
  INDEX `IX_F15C1C4F` (`plid` ASC) ,
  INDEX `IX_D340DB76` (`plid` ASC, `portletId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_blob_triggers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_blob_triggers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_blob_triggers` (
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `BLOB_DATA` BLOB NULL DEFAULT NULL ,
  PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_calendars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_calendars` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_calendars` (
  `CALENDAR_NAME` VARCHAR(80) NOT NULL ,
  `CALENDAR` BLOB NOT NULL ,
  PRIMARY KEY (`CALENDAR_NAME`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_cron_triggers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_cron_triggers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_cron_triggers` (
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `CRON_EXPRESSION` VARCHAR(80) NOT NULL ,
  `TIME_ZONE_ID` VARCHAR(80) NULL DEFAULT NULL ,
  PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_fired_triggers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_fired_triggers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_fired_triggers` (
  `ENTRY_ID` VARCHAR(95) NOT NULL ,
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `IS_VOLATILE` TINYINT(4) NOT NULL ,
  `INSTANCE_NAME` VARCHAR(80) NOT NULL ,
  `FIRED_TIME` BIGINT(20) NOT NULL ,
  `PRIORITY` INT(11) NOT NULL ,
  `STATE` VARCHAR(16) NOT NULL ,
  `JOB_NAME` VARCHAR(80) NULL DEFAULT NULL ,
  `JOB_GROUP` VARCHAR(80) NULL DEFAULT NULL ,
  `IS_STATEFUL` TINYINT(4) NULL DEFAULT NULL ,
  `REQUESTS_RECOVERY` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`ENTRY_ID`) ,
  INDEX `IX_804154AF` (`INSTANCE_NAME` ASC) ,
  INDEX `IX_BAB9A1F7` (`JOB_GROUP` ASC) ,
  INDEX `IX_ADEE6A17` (`JOB_NAME` ASC) ,
  INDEX `IX_64B194F2` (`TRIGGER_GROUP` ASC) ,
  INDEX `IX_5FEABBC` (`TRIGGER_NAME` ASC) ,
  INDEX `IX_20D8706C` (`TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_job_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_job_details` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_job_details` (
  `JOB_NAME` VARCHAR(80) NOT NULL ,
  `JOB_GROUP` VARCHAR(80) NOT NULL ,
  `DESCRIPTION` VARCHAR(120) NULL DEFAULT NULL ,
  `JOB_CLASS_NAME` VARCHAR(128) NOT NULL ,
  `IS_DURABLE` TINYINT(4) NOT NULL ,
  `IS_VOLATILE` TINYINT(4) NOT NULL ,
  `IS_STATEFUL` TINYINT(4) NOT NULL ,
  `REQUESTS_RECOVERY` TINYINT(4) NOT NULL ,
  `JOB_DATA` BLOB NULL DEFAULT NULL ,
  PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_job_listeners`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_job_listeners` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_job_listeners` (
  `JOB_NAME` VARCHAR(80) NOT NULL ,
  `JOB_GROUP` VARCHAR(80) NOT NULL ,
  `JOB_LISTENER` VARCHAR(80) NOT NULL ,
  PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`, `JOB_LISTENER`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_locks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_locks` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_locks` (
  `LOCK_NAME` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`LOCK_NAME`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_paused_trigger_grps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_paused_trigger_grps` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_paused_trigger_grps` (
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  PRIMARY KEY (`TRIGGER_GROUP`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_scheduler_state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_scheduler_state` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_scheduler_state` (
  `INSTANCE_NAME` VARCHAR(80) NOT NULL ,
  `LAST_CHECKIN_TIME` BIGINT(20) NOT NULL ,
  `CHECKIN_INTERVAL` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`INSTANCE_NAME`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_simple_triggers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_simple_triggers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_simple_triggers` (
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `REPEAT_COUNT` BIGINT(20) NOT NULL ,
  `REPEAT_INTERVAL` BIGINT(20) NOT NULL ,
  `TIMES_TRIGGERED` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_trigger_listeners`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_trigger_listeners` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_trigger_listeners` (
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `TRIGGER_LISTENER` VARCHAR(80) NOT NULL ,
  PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_LISTENER`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`quartz_triggers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`quartz_triggers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`quartz_triggers` (
  `TRIGGER_NAME` VARCHAR(80) NOT NULL ,
  `TRIGGER_GROUP` VARCHAR(80) NOT NULL ,
  `JOB_NAME` VARCHAR(80) NOT NULL ,
  `JOB_GROUP` VARCHAR(80) NOT NULL ,
  `IS_VOLATILE` TINYINT(4) NOT NULL ,
  `DESCRIPTION` VARCHAR(120) NULL DEFAULT NULL ,
  `NEXT_FIRE_TIME` BIGINT(20) NULL DEFAULT NULL ,
  `PREV_FIRE_TIME` BIGINT(20) NULL DEFAULT NULL ,
  `PRIORITY` INT(11) NULL DEFAULT NULL ,
  `TRIGGER_STATE` VARCHAR(16) NOT NULL ,
  `TRIGGER_TYPE` VARCHAR(8) NOT NULL ,
  `START_TIME` BIGINT(20) NOT NULL ,
  `END_TIME` BIGINT(20) NULL DEFAULT NULL ,
  `CALENDAR_NAME` VARCHAR(80) NULL DEFAULT NULL ,
  `MISFIRE_INSTR` INT(11) NULL DEFAULT NULL ,
  `JOB_DATA` BLOB NULL DEFAULT NULL ,
  PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) ,
  INDEX `IX_F7655CC3` (`NEXT_FIRE_TIME` ASC) ,
  INDEX `IX_9955EFB5` (`TRIGGER_STATE` ASC) ,
  INDEX `IX_8040C593` (`TRIGGER_STATE` ASC, `NEXT_FIRE_TIME` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`ratingsentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`ratingsentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`ratingsentry` (
  `entryId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `score` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`entryId`) ,
  UNIQUE INDEX `IX_B47E3C11` (`userId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_16184D57` (`classNameId` ASC, `classPK` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`ratingsstats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`ratingsstats` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`ratingsstats` (
  `statsId` BIGINT(20) NOT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `totalEntries` INT(11) NULL DEFAULT NULL ,
  `totalScore` DOUBLE NULL DEFAULT NULL ,
  `averageScore` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`statsId`) ,
  UNIQUE INDEX `IX_A6E99284` (`classNameId` ASC, `classPK` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`region` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`region` (
  `regionId` BIGINT(20) NOT NULL ,
  `countryId` BIGINT(20) NULL DEFAULT NULL ,
  `regionCode` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`regionId`) ,
  INDEX `IX_2D9A426F` (`active_` ASC) ,
  INDEX `IX_16D87CA7` (`countryId` ASC) ,
  INDEX `IX_11FB3E42` (`countryId` ASC, `active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`release_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`release_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`release_` (
  `releaseId` BIGINT(20) NOT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `servletContextName` VARCHAR(75) NULL DEFAULT NULL ,
  `buildNumber` INT(11) NULL DEFAULT NULL ,
  `buildDate` DATETIME NULL DEFAULT NULL ,
  `verified` TINYINT(4) NULL DEFAULT NULL ,
  `testString` VARCHAR(1024) NULL DEFAULT NULL ,
  PRIMARY KEY (`releaseId`) ,
  INDEX `IX_8BD6BCA7` (`servletContextName` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`resource_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`resource_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`resource_` (
  `resourceId` BIGINT(20) NOT NULL ,
  `codeId` BIGINT(20) NULL DEFAULT NULL ,
  `primKey` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`resourceId`) ,
  UNIQUE INDEX `IX_67DE7856` (`codeId` ASC, `primKey` ASC) ,
  INDEX `IX_2578FBD3` (`codeId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`resourceaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`resourceaction` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`resourceaction` (
  `resourceActionId` BIGINT(20) NOT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `actionId` VARCHAR(75) NULL DEFAULT NULL ,
  `bitwiseValue` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`resourceActionId`) ,
  UNIQUE INDEX `IX_EDB9986E` (`name` ASC, `actionId` ASC) ,
  INDEX `IX_81F2DB09` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`resourcecode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`resourcecode` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`resourcecode` (
  `codeId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `scope` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`codeId`) ,
  UNIQUE INDEX `IX_A32C097E` (`companyId` ASC, `name` ASC, `scope` ASC) ,
  INDEX `IX_717FDD47` (`companyId` ASC) ,
  INDEX `IX_AACAFF40` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`resourcepermission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`resourcepermission` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`resourcepermission` (
  `resourcePermissionId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `scope` INT(11) NULL DEFAULT NULL ,
  `primKey` VARCHAR(255) NULL DEFAULT NULL ,
  `roleId` BIGINT(20) NULL DEFAULT NULL ,
  `actionIds` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`resourcePermissionId`) ,
  UNIQUE INDEX `IX_8D83D0CE` (`companyId` ASC, `name` ASC, `scope` ASC, `primKey` ASC, `roleId` ASC) ,
  INDEX `IX_60B99860` (`companyId` ASC, `name` ASC, `scope` ASC) ,
  INDEX `IX_2200AA69` (`companyId` ASC, `name` ASC, `scope` ASC, `primKey` ASC) ,
  INDEX `IX_A37A0588` (`roleId` ASC) ,
  INDEX `IX_2F80C17C` (`roleId` ASC, `scope` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`role_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`role_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`role_` (
  `roleId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `title` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `subtype` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`roleId`) ,
  UNIQUE INDEX `IX_A88E424E` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  UNIQUE INDEX `IX_EBC931B8` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_449A10B9` (`companyId` ASC) ,
  INDEX `IX_5EB4E2FB` (`subtype` ASC) ,
  INDEX `IX_CBE204` (`type_` ASC, `subtype` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`roles_permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`roles_permissions` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`roles_permissions` (
  `roleId` BIGINT(20) NOT NULL ,
  `permissionId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`roleId`, `permissionId`) ,
  INDEX `IX_7A3619C6` (`permissionId` ASC) ,
  INDEX `IX_E04E486D` (`roleId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`scframeworkversi_scproductvers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`scframeworkversi_scproductvers` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`scframeworkversi_scproductvers` (
  `frameworkVersionId` BIGINT(20) NOT NULL ,
  `productVersionId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`frameworkVersionId`, `productVersionId`) ,
  INDEX `IX_3BB93ECA` (`frameworkVersionId` ASC) ,
  INDEX `IX_E8D33FF9` (`productVersionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`scframeworkversion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`scframeworkversion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`scframeworkversion` (
  `frameworkVersionId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`frameworkVersionId`) ,
  INDEX `IX_C98C0D78` (`companyId` ASC) ,
  INDEX `IX_272991FA` (`groupId` ASC) ,
  INDEX `IX_6E1764F` (`groupId` ASC, `active_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`sclicense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`sclicense` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`sclicense` (
  `licenseId` BIGINT(20) NOT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `openSource` TINYINT(4) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  `recommended` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`licenseId`) ,
  INDEX `IX_1C841592` (`active_` ASC) ,
  INDEX `IX_5327BB79` (`active_` ASC, `recommended` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`sclicenses_scproductentries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`sclicenses_scproductentries` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`sclicenses_scproductentries` (
  `licenseId` BIGINT(20) NOT NULL ,
  `productEntryId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`licenseId`, `productEntryId`) ,
  INDEX `IX_27006638` (`licenseId` ASC) ,
  INDEX `IX_D7710A66` (`productEntryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`scproductentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`scproductentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`scproductentry` (
  `productEntryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `tags` VARCHAR(255) NULL DEFAULT NULL ,
  `shortDescription` LONGTEXT NULL DEFAULT NULL ,
  `longDescription` LONGTEXT NULL DEFAULT NULL ,
  `pageURL` LONGTEXT NULL DEFAULT NULL ,
  `author` VARCHAR(75) NULL DEFAULT NULL ,
  `repoGroupId` VARCHAR(75) NULL DEFAULT NULL ,
  `repoArtifactId` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`productEntryId`) ,
  INDEX `IX_5D25244F` (`companyId` ASC) ,
  INDEX `IX_72F87291` (`groupId` ASC) ,
  INDEX `IX_98E6A9CB` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_7311E812` (`repoGroupId` ASC, `repoArtifactId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`scproductscreenshot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`scproductscreenshot` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`scproductscreenshot` (
  `productScreenshotId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `productEntryId` BIGINT(20) NULL DEFAULT NULL ,
  `thumbnailId` BIGINT(20) NULL DEFAULT NULL ,
  `fullImageId` BIGINT(20) NULL DEFAULT NULL ,
  `priority` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`productScreenshotId`) ,
  INDEX `IX_AE8224CC` (`fullImageId` ASC) ,
  INDEX `IX_467956FD` (`productEntryId` ASC) ,
  INDEX `IX_DA913A55` (`productEntryId` ASC, `priority` ASC) ,
  INDEX `IX_6C572DAC` (`thumbnailId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`scproductversion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`scproductversion` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`scproductversion` (
  `productVersionId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `productEntryId` BIGINT(20) NULL DEFAULT NULL ,
  `version` VARCHAR(75) NULL DEFAULT NULL ,
  `changeLog` LONGTEXT NULL DEFAULT NULL ,
  `downloadPageURL` LONGTEXT NULL DEFAULT NULL ,
  `directDownloadURL` VARCHAR(2000) NULL DEFAULT NULL ,
  `repoStoreArtifact` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`productVersionId`) ,
  INDEX `IX_7020130F` (`directDownloadURL`(767) ASC) ,
  INDEX `IX_8377A211` (`productEntryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`servicecomponent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`servicecomponent` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`servicecomponent` (
  `serviceComponentId` BIGINT(20) NOT NULL ,
  `buildNamespace` VARCHAR(75) NULL DEFAULT NULL ,
  `buildNumber` BIGINT(20) NULL DEFAULT NULL ,
  `buildDate` BIGINT(20) NULL DEFAULT NULL ,
  `data_` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`serviceComponentId`) ,
  UNIQUE INDEX `IX_4F0315B8` (`buildNamespace` ASC, `buildNumber` ASC) ,
  INDEX `IX_7338606F` (`buildNamespace` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shard` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shard` (
  `shardId` BIGINT(20) NOT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`shardId`) ,
  INDEX `IX_DA5F4359` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_941BA8C3` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingcart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingcart` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingcart` (
  `cartId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `itemIds` LONGTEXT NULL DEFAULT NULL ,
  `couponCodes` VARCHAR(75) NULL DEFAULT NULL ,
  `altShipping` INT(11) NULL DEFAULT NULL ,
  `insure` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`cartId`) ,
  UNIQUE INDEX `IX_FC46FE16` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_C28B41DC` (`groupId` ASC) ,
  INDEX `IX_54101CC8` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingcategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingcategory` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingcategory` (
  `categoryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `parentCategoryId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`categoryId`) ,
  INDEX `IX_5F615D3E` (`groupId` ASC) ,
  INDEX `IX_1E6464F5` (`groupId` ASC, `parentCategoryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingcoupon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingcoupon` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingcoupon` (
  `couponId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `code_` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `startDate` DATETIME NULL DEFAULT NULL ,
  `endDate` DATETIME NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  `limitCategories` LONGTEXT NULL DEFAULT NULL ,
  `limitSkus` LONGTEXT NULL DEFAULT NULL ,
  `minOrder` DOUBLE NULL DEFAULT NULL ,
  `discount` DOUBLE NULL DEFAULT NULL ,
  `discountType` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`couponId`) ,
  UNIQUE INDEX `IX_DC60CFAE` (`code_` ASC) ,
  INDEX `IX_3251AF16` (`groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingitem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingitem` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingitem` (
  `itemId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `categoryId` BIGINT(20) NULL DEFAULT NULL ,
  `sku` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `properties` LONGTEXT NULL DEFAULT NULL ,
  `fields_` TINYINT(4) NULL DEFAULT NULL ,
  `fieldsQuantities` LONGTEXT NULL DEFAULT NULL ,
  `minQuantity` INT(11) NULL DEFAULT NULL ,
  `maxQuantity` INT(11) NULL DEFAULT NULL ,
  `price` DOUBLE NULL DEFAULT NULL ,
  `discount` DOUBLE NULL DEFAULT NULL ,
  `taxable` TINYINT(4) NULL DEFAULT NULL ,
  `shipping` DOUBLE NULL DEFAULT NULL ,
  `useShippingFormula` TINYINT(4) NULL DEFAULT NULL ,
  `requiresShipping` TINYINT(4) NULL DEFAULT NULL ,
  `stockQuantity` INT(11) NULL DEFAULT NULL ,
  `featured_` TINYINT(4) NULL DEFAULT NULL ,
  `sale_` TINYINT(4) NULL DEFAULT NULL ,
  `smallImage` TINYINT(4) NULL DEFAULT NULL ,
  `smallImageId` BIGINT(20) NULL DEFAULT NULL ,
  `smallImageURL` LONGTEXT NULL DEFAULT NULL ,
  `mediumImage` TINYINT(4) NULL DEFAULT NULL ,
  `mediumImageId` BIGINT(20) NULL DEFAULT NULL ,
  `mediumImageURL` LONGTEXT NULL DEFAULT NULL ,
  `largeImage` TINYINT(4) NULL DEFAULT NULL ,
  `largeImageId` BIGINT(20) NULL DEFAULT NULL ,
  `largeImageURL` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`itemId`) ,
  UNIQUE INDEX `IX_1C717CA6` (`companyId` ASC, `sku` ASC) ,
  INDEX `IX_FEFE7D76` (`groupId` ASC, `categoryId` ASC) ,
  INDEX `IX_903DC750` (`largeImageId` ASC) ,
  INDEX `IX_D217AB30` (`mediumImageId` ASC) ,
  INDEX `IX_FF203304` (`smallImageId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingitemfield`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingitemfield` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingitemfield` (
  `itemFieldId` BIGINT(20) NOT NULL ,
  `itemId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `values_` LONGTEXT NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`itemFieldId`) ,
  INDEX `IX_6D5F9B87` (`itemId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingitemprice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingitemprice` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingitemprice` (
  `itemPriceId` BIGINT(20) NOT NULL ,
  `itemId` BIGINT(20) NULL DEFAULT NULL ,
  `minQuantity` INT(11) NULL DEFAULT NULL ,
  `maxQuantity` INT(11) NULL DEFAULT NULL ,
  `price` DOUBLE NULL DEFAULT NULL ,
  `discount` DOUBLE NULL DEFAULT NULL ,
  `taxable` TINYINT(4) NULL DEFAULT NULL ,
  `shipping` DOUBLE NULL DEFAULT NULL ,
  `useShippingFormula` TINYINT(4) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`itemPriceId`) ,
  INDEX `IX_EA6FD516` (`itemId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingorder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingorder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingorder` (
  `orderId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `number_` VARCHAR(75) NULL DEFAULT NULL ,
  `tax` DOUBLE NULL DEFAULT NULL ,
  `shipping` DOUBLE NULL DEFAULT NULL ,
  `altShipping` VARCHAR(75) NULL DEFAULT NULL ,
  `requiresShipping` TINYINT(4) NULL DEFAULT NULL ,
  `insure` TINYINT(4) NULL DEFAULT NULL ,
  `insurance` DOUBLE NULL DEFAULT NULL ,
  `couponCodes` VARCHAR(75) NULL DEFAULT NULL ,
  `couponDiscount` DOUBLE NULL DEFAULT NULL ,
  `billingFirstName` VARCHAR(75) NULL DEFAULT NULL ,
  `billingLastName` VARCHAR(75) NULL DEFAULT NULL ,
  `billingEmailAddress` VARCHAR(75) NULL DEFAULT NULL ,
  `billingCompany` VARCHAR(75) NULL DEFAULT NULL ,
  `billingStreet` VARCHAR(75) NULL DEFAULT NULL ,
  `billingCity` VARCHAR(75) NULL DEFAULT NULL ,
  `billingState` VARCHAR(75) NULL DEFAULT NULL ,
  `billingZip` VARCHAR(75) NULL DEFAULT NULL ,
  `billingCountry` VARCHAR(75) NULL DEFAULT NULL ,
  `billingPhone` VARCHAR(75) NULL DEFAULT NULL ,
  `shipToBilling` TINYINT(4) NULL DEFAULT NULL ,
  `shippingFirstName` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingLastName` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingEmailAddress` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingCompany` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingStreet` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingCity` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingState` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingZip` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingCountry` VARCHAR(75) NULL DEFAULT NULL ,
  `shippingPhone` VARCHAR(75) NULL DEFAULT NULL ,
  `ccName` VARCHAR(75) NULL DEFAULT NULL ,
  `ccType` VARCHAR(75) NULL DEFAULT NULL ,
  `ccNumber` VARCHAR(75) NULL DEFAULT NULL ,
  `ccExpMonth` INT(11) NULL DEFAULT NULL ,
  `ccExpYear` INT(11) NULL DEFAULT NULL ,
  `ccVerNumber` VARCHAR(75) NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  `ppTxnId` VARCHAR(75) NULL DEFAULT NULL ,
  `ppPaymentStatus` VARCHAR(75) NULL DEFAULT NULL ,
  `ppPaymentGross` DOUBLE NULL DEFAULT NULL ,
  `ppReceiverEmail` VARCHAR(75) NULL DEFAULT NULL ,
  `ppPayerEmail` VARCHAR(75) NULL DEFAULT NULL ,
  `sendOrderEmail` TINYINT(4) NULL DEFAULT NULL ,
  `sendShippingEmail` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`orderId`) ,
  UNIQUE INDEX `IX_D7D6E87A` (`number_` ASC) ,
  INDEX `IX_1D15553E` (`groupId` ASC) ,
  INDEX `IX_119B5630` (`groupId` ASC, `userId` ASC, `ppPaymentStatus` ASC) ,
  INDEX `IX_F474FD89` (`ppTxnId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`shoppingorderitem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`shoppingorderitem` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`shoppingorderitem` (
  `orderItemId` BIGINT(20) NOT NULL ,
  `orderId` BIGINT(20) NULL DEFAULT NULL ,
  `itemId` VARCHAR(75) NULL DEFAULT NULL ,
  `sku` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(200) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `properties` LONGTEXT NULL DEFAULT NULL ,
  `price` DOUBLE NULL DEFAULT NULL ,
  `quantity` INT(11) NULL DEFAULT NULL ,
  `shippedDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`orderItemId`) ,
  INDEX `IX_B5F82C7A` (`orderId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`sn_meetupsentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`sn_meetupsentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`sn_meetupsentry` (
  `meetupsEntryId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `title` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `startDate` DATETIME NULL DEFAULT NULL ,
  `endDate` DATETIME NULL DEFAULT NULL ,
  `totalAttendees` INT(11) NULL DEFAULT NULL ,
  `maxAttendees` INT(11) NULL DEFAULT NULL ,
  `price` DOUBLE NULL DEFAULT NULL ,
  `thumbnailId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`meetupsEntryId`) ,
  INDEX `IX_A56E51DD` (`companyId` ASC) ,
  INDEX `IX_6EA9EEA5` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`sn_meetupsregistration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`sn_meetupsregistration` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`sn_meetupsregistration` (
  `meetupsRegistrationId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `meetupsEntryId` BIGINT(20) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`meetupsRegistrationId`) ,
  INDEX `IX_A79293FC` (`meetupsEntryId` ASC) ,
  INDEX `IX_BCEB16E2` (`meetupsEntryId` ASC, `status` ASC) ,
  INDEX `IX_3CBE4C36` (`userId` ASC, `meetupsEntryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`sn_wallentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`sn_wallentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`sn_wallentry` (
  `wallEntryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`wallEntryId`) ,
  INDEX `IX_5C68C960` (`groupId` ASC) ,
  INDEX `IX_F2F6C19A` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_C46194C4` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialactivity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialactivity` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialactivity` (
  `activityId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` BIGINT(20) NULL DEFAULT NULL ,
  `mirrorActivityId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `extraData` LONGTEXT NULL DEFAULT NULL ,
  `receiverUserId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`activityId`) ,
  UNIQUE INDEX `IX_8F32DEC9` (`groupId` ASC, `userId` ASC, `createDate` ASC, `classNameId` ASC, `classPK` ASC, `type_` ASC, `receiverUserId` ASC) ,
  INDEX `IX_82E39A0C` (`classNameId` ASC) ,
  INDEX `IX_A853C757` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_64B1BC66` (`companyId` ASC) ,
  INDEX `IX_2A2468` (`groupId` ASC) ,
  INDEX `IX_1271F25F` (`mirrorActivityId` ASC) ,
  INDEX `IX_1F00C374` (`mirrorActivityId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_121CA3CB` (`receiverUserId` ASC) ,
  INDEX `IX_3504B8BC` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialequityassetentry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialequityassetentry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialequityassetentry` (
  `equityAssetEntryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `assetEntryId` BIGINT(20) NULL DEFAULT NULL ,
  `informationK` DOUBLE NULL DEFAULT NULL ,
  `informationB` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`equityAssetEntryId`) ,
  UNIQUE INDEX `IX_22F6B5CB` (`assetEntryId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialequityhistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialequityhistory` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialequityhistory` (
  `equityHistoryId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `personalEquity` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`equityHistoryId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialequitylog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialequitylog` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialequitylog` (
  `equityLogId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `assetEntryId` BIGINT(20) NULL DEFAULT NULL ,
  `actionId` VARCHAR(75) NULL DEFAULT NULL ,
  `actionDate` INT(11) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  `expiration` INT(11) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `value` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`equityLogId`) ,
  UNIQUE INDEX `IX_55B2F00C` (`userId` ASC, `assetEntryId` ASC, `actionId` ASC, `actionDate` ASC, `active_` ASC, `type_` ASC) ,
  INDEX `IX_DB6958D2` (`assetEntryId` ASC, `actionId` ASC, `actionDate` ASC, `active_` ASC, `type_` ASC) ,
  INDEX `IX_FEB4055A` (`assetEntryId` ASC, `actionId` ASC, `active_` ASC, `type_` ASC) ,
  INDEX `IX_E8DA181D` (`assetEntryId` ASC, `type_` ASC, `active_` ASC) ,
  INDEX `IX_15A017B` (`userId` ASC, `actionId` ASC, `actionDate` ASC, `active_` ASC, `type_` ASC) ,
  INDEX `IX_3525A383` (`userId` ASC, `actionId` ASC, `active_` ASC, `type_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialequitysetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialequitysetting` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialequitysetting` (
  `equitySettingId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `actionId` VARCHAR(75) NULL DEFAULT NULL ,
  `dailyLimit` INT(11) NULL DEFAULT NULL ,
  `lifespan` INT(11) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `uniqueEntry` TINYINT(4) NULL DEFAULT NULL ,
  `value` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`equitySettingId`) ,
  UNIQUE INDEX `IX_903C1B28` (`groupId` ASC, `classNameId` ASC, `actionId` ASC, `type_` ASC) ,
  INDEX `IX_F3AAD60D` (`groupId` ASC, `classNameId` ASC, `actionId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialequityuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialequityuser` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialequityuser` (
  `equityUserId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `contributionK` DOUBLE NULL DEFAULT NULL ,
  `contributionB` DOUBLE NULL DEFAULT NULL ,
  `participationK` DOUBLE NULL DEFAULT NULL ,
  `participationB` DOUBLE NULL DEFAULT NULL ,
  `rank` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`equityUserId`) ,
  UNIQUE INDEX `IX_D65D3521` (`groupId` ASC, `userId` ASC) ,
  INDEX `IX_6B42B3E7` (`groupId` ASC) ,
  INDEX `IX_945E27C7` (`groupId` ASC, `rank` ASC) ,
  INDEX `IX_166A8F03` (`rank` ASC) ,
  INDEX `IX_6ECBD5D` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialrelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialrelation` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialrelation` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `relationId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` BIGINT(20) NULL DEFAULT NULL ,
  `userId1` BIGINT(20) NULL DEFAULT NULL ,
  `userId2` BIGINT(20) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`relationId`) ,
  UNIQUE INDEX `IX_12A92145` (`userId1` ASC, `userId2` ASC, `type_` ASC) ,
  INDEX `IX_61171E99` (`companyId` ASC) ,
  INDEX `IX_95135D1C` (`companyId` ASC, `type_` ASC) ,
  INDEX `IX_C31A64C6` (`type_` ASC) ,
  INDEX `IX_5A40CDCC` (`userId1` ASC) ,
  INDEX `IX_4B52BE89` (`userId1` ASC, `type_` ASC) ,
  INDEX `IX_5A40D18D` (`userId2` ASC) ,
  INDEX `IX_3F9C2FA8` (`userId2` ASC, `type_` ASC) ,
  INDEX `IX_F0CA24A5` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`socialrequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`socialrequest` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`socialrequest` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `requestId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` BIGINT(20) NULL DEFAULT NULL ,
  `modifiedDate` BIGINT(20) NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `type_` INT(11) NULL DEFAULT NULL ,
  `extraData` LONGTEXT NULL DEFAULT NULL ,
  `receiverUserId` BIGINT(20) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`requestId`) ,
  UNIQUE INDEX `IX_36A90CA7` (`userId` ASC, `classNameId` ASC, `classPK` ASC, `type_` ASC, `receiverUserId` ASC) ,
  UNIQUE INDEX `IX_4F973EFE` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_D3425487` (`classNameId` ASC, `classPK` ASC, `type_` ASC, `receiverUserId` ASC, `status` ASC) ,
  INDEX `IX_A90FE5A0` (`companyId` ASC) ,
  INDEX `IX_32292ED1` (`receiverUserId` ASC) ,
  INDEX `IX_D9380CB7` (`receiverUserId` ASC, `status` ASC) ,
  INDEX `IX_80F7A9C2` (`userId` ASC) ,
  INDEX `IX_CC86A444` (`userId` ASC, `classNameId` ASC, `classPK` ASC, `type_` ASC, `status` ASC) ,
  INDEX `IX_AB5906A8` (`userId` ASC, `status` ASC) ,
  INDEX `IX_49D5872C` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`subscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`subscription` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`subscription` (
  `subscriptionId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `frequency` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`subscriptionId`) ,
  UNIQUE INDEX `IX_2E1A92D4` (`companyId` ASC, `userId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_786D171A` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_54243AFD` (`userId` ASC) ,
  INDEX `IX_E8F34171` (`userId` ASC, `classNameId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`tasksproposal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`tasksproposal` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`tasksproposal` (
  `proposalId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` VARCHAR(75) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `publishDate` DATETIME NULL DEFAULT NULL ,
  `dueDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`proposalId`) ,
  UNIQUE INDEX `IX_181A4A1B` (`classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_7FB27324` (`groupId` ASC) ,
  INDEX `IX_6EEC675E` (`groupId` ASC, `userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`tasksreview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`tasksreview` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`tasksreview` (
  `reviewId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `proposalId` BIGINT(20) NULL DEFAULT NULL ,
  `assignedByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `assignedByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `stage` INT(11) NULL DEFAULT NULL ,
  `completed` TINYINT(4) NULL DEFAULT NULL ,
  `rejected` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`reviewId`) ,
  UNIQUE INDEX `IX_5C6BE4C7` (`userId` ASC, `proposalId` ASC) ,
  INDEX `IX_4D0C7F8D` (`proposalId` ASC) ,
  INDEX `IX_70AFEA01` (`proposalId` ASC, `stage` ASC) ,
  INDEX `IX_1894B29A` (`proposalId` ASC, `stage` ASC, `completed` ASC) ,
  INDEX `IX_41AFC20C` (`proposalId` ASC, `stage` ASC, `completed` ASC, `rejected` ASC) ,
  INDEX `IX_36F512E6` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`team` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`team` (
  `teamId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`teamId`) ,
  UNIQUE INDEX `IX_143DC786` (`groupId` ASC, `name` ASC) ,
  INDEX `IX_AE6E9907` (`groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`ticket` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`ticket` (
  `ticketId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `key_` VARCHAR(75) NULL DEFAULT NULL ,
  `expirationDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`ticketId`) ,
  INDEX `IX_B2468446` (`key_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`user_`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`user_` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`user_` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `defaultUser` TINYINT(4) NULL DEFAULT NULL ,
  `contactId` BIGINT(20) NULL DEFAULT NULL ,
  `password_` VARCHAR(75) NULL DEFAULT NULL ,
  `passwordEncrypted` TINYINT(4) NULL DEFAULT NULL ,
  `passwordReset` TINYINT(4) NULL DEFAULT NULL ,
  `passwordModifiedDate` DATETIME NULL DEFAULT NULL ,
  `digest` VARCHAR(255) NULL DEFAULT NULL ,
  `reminderQueryQuestion` VARCHAR(75) NULL DEFAULT NULL ,
  `reminderQueryAnswer` VARCHAR(75) NULL DEFAULT NULL ,
  `graceLoginCount` INT(11) NULL DEFAULT NULL ,
  `screenName` VARCHAR(75) NULL DEFAULT NULL ,
  `emailAddress` VARCHAR(75) NULL DEFAULT NULL ,
  `facebookId` BIGINT(20) NULL DEFAULT NULL ,
  `openId` VARCHAR(1024) NULL DEFAULT NULL ,
  `portraitId` BIGINT(20) NULL DEFAULT NULL ,
  `languageId` VARCHAR(75) NULL DEFAULT NULL ,
  `timeZoneId` VARCHAR(75) NULL DEFAULT NULL ,
  `greeting` VARCHAR(255) NULL DEFAULT NULL ,
  `comments` LONGTEXT NULL DEFAULT NULL ,
  `firstName` VARCHAR(75) NULL DEFAULT NULL ,
  `middleName` VARCHAR(75) NULL DEFAULT NULL ,
  `lastName` VARCHAR(75) NULL DEFAULT NULL ,
  `jobTitle` VARCHAR(100) NULL DEFAULT NULL ,
  `loginDate` DATETIME NULL DEFAULT NULL ,
  `loginIP` VARCHAR(75) NULL DEFAULT NULL ,
  `lastLoginDate` DATETIME NULL DEFAULT NULL ,
  `lastLoginIP` VARCHAR(75) NULL DEFAULT NULL ,
  `lastFailedLoginDate` DATETIME NULL DEFAULT NULL ,
  `failedLoginAttempts` INT(11) NULL DEFAULT NULL ,
  `lockout` TINYINT(4) NULL DEFAULT NULL ,
  `lockoutDate` DATETIME NULL DEFAULT NULL ,
  `agreedToTermsOfUse` TINYINT(4) NULL DEFAULT NULL ,
  `active_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`userId`) ,
  UNIQUE INDEX `IX_615E9F7A` (`companyId` ASC, `emailAddress` ASC) ,
  UNIQUE INDEX `IX_C5806019` (`companyId` ASC, `screenName` ASC) ,
  UNIQUE INDEX `IX_9782AD88` (`companyId` ASC, `userId` ASC) ,
  UNIQUE INDEX `IX_5ADBE171` (`contactId` ASC) ,
  INDEX `IX_3A1E834E` (`companyId` ASC) ,
  INDEX `IX_5204C37B` (`companyId` ASC, `active_` ASC) ,
  INDEX `IX_6EF03E4E` (`companyId` ASC, `defaultUser` ASC) ,
  INDEX `IX_1D731F03` (`companyId` ASC, `facebookId` ASC) ,
  INDEX `IX_89509087` (`companyId` ASC, `openId`(767) ASC) ,
  INDEX `IX_762F63C6` (`emailAddress` ASC) ,
  INDEX `IX_A18034A4` (`portraitId` ASC) ,
  INDEX `IX_E0422BDA` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`usergroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`usergroup` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`usergroup` (
  `userGroupId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `parentUserGroupId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`userGroupId`) ,
  UNIQUE INDEX `IX_23EAD0D` (`companyId` ASC, `name` ASC) ,
  INDEX `IX_524FEFCE` (`companyId` ASC) ,
  INDEX `IX_69771487` (`companyId` ASC, `parentUserGroupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`usergroupgrouprole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`usergroupgrouprole` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`usergroupgrouprole` (
  `userGroupId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NOT NULL ,
  `roleId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userGroupId`, `groupId`, `roleId`) ,
  INDEX `IX_CCBE4063` (`groupId` ASC) ,
  INDEX `IX_CAB0CCC8` (`groupId` ASC, `roleId` ASC) ,
  INDEX `IX_1CDF88C` (`roleId` ASC) ,
  INDEX `IX_DCDED558` (`userGroupId` ASC) ,
  INDEX `IX_73C52252` (`userGroupId` ASC, `groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`usergrouprole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`usergrouprole` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`usergrouprole` (
  `userId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NOT NULL ,
  `roleId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `groupId`, `roleId`) ,
  INDEX `IX_1B988D7A` (`groupId` ASC) ,
  INDEX `IX_871412DF` (`groupId` ASC, `roleId` ASC) ,
  INDEX `IX_887A2C95` (`roleId` ASC) ,
  INDEX `IX_887BE56A` (`userId` ASC) ,
  INDEX `IX_4D040680` (`userId` ASC, `groupId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`useridmapper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`useridmapper` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`useridmapper` (
  `userIdMapperId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `type_` VARCHAR(75) NULL DEFAULT NULL ,
  `description` VARCHAR(75) NULL DEFAULT NULL ,
  `externalUserId` VARCHAR(75) NULL DEFAULT NULL ,
  PRIMARY KEY (`userIdMapperId`) ,
  UNIQUE INDEX `IX_41A32E0D` (`type_` ASC, `externalUserId` ASC) ,
  UNIQUE INDEX `IX_D1C44A6E` (`userId` ASC, `type_` ASC) ,
  INDEX `IX_E60EA987` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_groups` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_groups` (
  `userId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `groupId`) ,
  INDEX `IX_C4F9E699` (`groupId` ASC) ,
  INDEX `IX_F10B6C6B` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_orgs` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_orgs` (
  `userId` BIGINT(20) NOT NULL ,
  `organizationId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `organizationId`) ,
  INDEX `IX_7EF4EC0E` (`organizationId` ASC) ,
  INDEX `IX_FB646CA6` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_permissions` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_permissions` (
  `userId` BIGINT(20) NOT NULL ,
  `permissionId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `permissionId`) ,
  INDEX `IX_8AE58A91` (`permissionId` ASC) ,
  INDEX `IX_C26AA64D` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_roles` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_roles` (
  `userId` BIGINT(20) NOT NULL ,
  `roleId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `roleId`) ,
  INDEX `IX_C19E5F31` (`roleId` ASC) ,
  INDEX `IX_C1A01806` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_teams` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_teams` (
  `userId` BIGINT(20) NOT NULL ,
  `teamId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userId`, `teamId`) ,
  INDEX `IX_4D06AD51` (`teamId` ASC) ,
  INDEX `IX_A098EFBF` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`users_usergroups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`users_usergroups` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`users_usergroups` (
  `userGroupId` BIGINT(20) NOT NULL ,
  `userId` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`userGroupId`, `userId`) ,
  INDEX `IX_66FF2503` (`userGroupId` ASC) ,
  INDEX `IX_BE8102D6` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`usertracker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`usertracker` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`usertracker` (
  `userTrackerId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `sessionId` VARCHAR(200) NULL DEFAULT NULL ,
  `remoteAddr` VARCHAR(75) NULL DEFAULT NULL ,
  `remoteHost` VARCHAR(75) NULL DEFAULT NULL ,
  `userAgent` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`userTrackerId`) ,
  INDEX `IX_29BA1CF5` (`companyId` ASC) ,
  INDEX `IX_46B0AE8E` (`sessionId` ASC) ,
  INDEX `IX_E4EFBA8D` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`usertrackerpath`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`usertrackerpath` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`usertrackerpath` (
  `userTrackerPathId` BIGINT(20) NOT NULL ,
  `userTrackerId` BIGINT(20) NULL DEFAULT NULL ,
  `path_` LONGTEXT NULL DEFAULT NULL ,
  `pathDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`userTrackerPathId`) ,
  INDEX `IX_14D8BCC0` (`userTrackerId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`vocabulary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`vocabulary` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`vocabulary` (
  `vocabularyId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` VARCHAR(75) NULL DEFAULT NULL ,
  `folksonomy` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`vocabularyId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`webdavprops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`webdavprops` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`webdavprops` (
  `webDavPropsId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `props` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`webDavPropsId`) ,
  UNIQUE INDEX `IX_97DFA146` (`classNameId` ASC, `classPK` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`website`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`website` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`website` (
  `websiteId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `typeId` INT(11) NULL DEFAULT NULL ,
  `primary_` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`websiteId`) ,
  INDEX `IX_96F07007` (`companyId` ASC) ,
  INDEX `IX_4F0F0CA7` (`companyId` ASC, `classNameId` ASC) ,
  INDEX `IX_F960131C` (`companyId` ASC, `classNameId` ASC, `classPK` ASC) ,
  INDEX `IX_1AA07A6D` (`companyId` ASC, `classNameId` ASC, `classPK` ASC, `primary_` ASC) ,
  INDEX `IX_F75690BB` (`userId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wikinode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wikinode` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wikinode` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `nodeId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `lastPostDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`nodeId`) ,
  UNIQUE INDEX `IX_920CD8B1` (`groupId` ASC, `name` ASC) ,
  UNIQUE INDEX `IX_7609B2AE` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_5D6FE3F0` (`companyId` ASC) ,
  INDEX `IX_B480A672` (`groupId` ASC) ,
  INDEX `IX_6C112D7C` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wikipage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wikipage` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wikipage` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `pageId` BIGINT(20) NOT NULL ,
  `resourcePrimKey` BIGINT(20) NULL DEFAULT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `nodeId` BIGINT(20) NULL DEFAULT NULL ,
  `title` VARCHAR(255) NULL DEFAULT NULL ,
  `version` DOUBLE NULL DEFAULT NULL ,
  `minorEdit` TINYINT(4) NULL DEFAULT NULL ,
  `content` LONGTEXT NULL DEFAULT NULL ,
  `summary` LONGTEXT NULL DEFAULT NULL ,
  `format` VARCHAR(75) NULL DEFAULT NULL ,
  `head` TINYINT(4) NULL DEFAULT NULL ,
  `parentTitle` VARCHAR(255) NULL DEFAULT NULL ,
  `redirectTitle` VARCHAR(255) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL ,
  `statusByUserId` BIGINT(20) NULL DEFAULT NULL ,
  `statusByUserName` VARCHAR(75) NULL DEFAULT NULL ,
  `statusDate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`pageId`) ,
  UNIQUE INDEX `IX_3D4AF476` (`nodeId` ASC, `title` ASC, `version` ASC) ,
  UNIQUE INDEX `IX_2CD67C81` (`resourcePrimKey` ASC, `nodeId` ASC, `version` ASC) ,
  UNIQUE INDEX `IX_899D3DFB` (`uuid_` ASC, `groupId` ASC) ,
  INDEX `IX_A2001730` (`format` ASC) ,
  INDEX `IX_C8A9C476` (`nodeId` ASC) ,
  INDEX `IX_E7F635CA` (`nodeId` ASC, `head` ASC) ,
  INDEX `IX_65E84AF4` (`nodeId` ASC, `head` ASC, `parentTitle` ASC) ,
  INDEX `IX_9F7655DA` (`nodeId` ASC, `head` ASC, `parentTitle` ASC, `status` ASC) ,
  INDEX `IX_432F0AB0` (`nodeId` ASC, `head` ASC, `status` ASC) ,
  INDEX `IX_46EEF3C8` (`nodeId` ASC, `parentTitle` ASC) ,
  INDEX `IX_1ECC7656` (`nodeId` ASC, `redirectTitle` ASC) ,
  INDEX `IX_546F2D5C` (`nodeId` ASC, `status` ASC) ,
  INDEX `IX_997EEDD2` (`nodeId` ASC, `title` ASC) ,
  INDEX `IX_E745EA26` (`nodeId` ASC, `title` ASC, `head` ASC) ,
  INDEX `IX_BEA33AB8` (`nodeId` ASC, `title` ASC, `status` ASC) ,
  INDEX `IX_B771D67` (`resourcePrimKey` ASC, `nodeId` ASC) ,
  INDEX `IX_94D1054D` (`resourcePrimKey` ASC, `nodeId` ASC, `status` ASC) ,
  INDEX `IX_FBBE7C96` (`userId` ASC, `nodeId` ASC, `status` ASC) ,
  INDEX `IX_9C0E478F` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wikipageresource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wikipageresource` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wikipageresource` (
  `uuid_` VARCHAR(75) NULL DEFAULT NULL ,
  `resourcePrimKey` BIGINT(20) NOT NULL ,
  `nodeId` BIGINT(20) NULL DEFAULT NULL ,
  `title` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`resourcePrimKey`) ,
  UNIQUE INDEX `IX_21277664` (`nodeId` ASC, `title` ASC) ,
  INDEX `IX_BE898221` (`uuid_` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`workflowdefinitionlink`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`workflowdefinitionlink` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`workflowdefinitionlink` (
  `workflowDefinitionLinkId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `workflowDefinitionName` VARCHAR(75) NULL DEFAULT NULL ,
  `workflowDefinitionVersion` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`workflowDefinitionLinkId`) ,
  INDEX `IX_A8B0D276` (`companyId` ASC) ,
  INDEX `IX_A4DB1F0F` (`companyId` ASC, `workflowDefinitionName` ASC, `workflowDefinitionVersion` ASC) ,
  INDEX `IX_B6EE8C9E` (`groupId` ASC, `companyId` ASC, `classNameId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`workflowinstancelink`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`workflowinstancelink` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`workflowinstancelink` (
  `workflowInstanceLinkId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `userId` BIGINT(20) NULL DEFAULT NULL ,
  `userName` VARCHAR(75) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `classNameId` BIGINT(20) NULL DEFAULT NULL ,
  `classPK` BIGINT(20) NULL DEFAULT NULL ,
  `workflowInstanceId` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`workflowInstanceLinkId`) ,
  INDEX `IX_415A7007` (`groupId` ASC, `companyId` ASC, `classNameId` ASC, `classPK` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wsrp_wsrpconsumer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wsrp_wsrpconsumer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wsrp_wsrpconsumer` (
  `wsrpConsumerId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `url` LONGTEXT NULL DEFAULT NULL ,
  `wsdl` LONGTEXT NULL DEFAULT NULL ,
  `registrationContextString` LONGTEXT NULL DEFAULT NULL ,
  `registrationPropertiesString` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`wsrpConsumerId`) ,
  INDEX `IX_8F3348D` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wsrp_wsrpconsumerportlet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wsrp_wsrpconsumerportlet` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wsrp_wsrpconsumerportlet` (
  `wsrpConsumerPortletId` BIGINT(20) NOT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `wsrpConsumerId` BIGINT(20) NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `portletHandle` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`wsrpConsumerPortletId`) ,
  INDEX `IX_1CEEF2AA` (`wsrpConsumerId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lportal`.`wsrp_wsrpproducer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lportal`.`wsrp_wsrpproducer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `lportal`.`wsrp_wsrpproducer` (
  `wsrpProducerId` BIGINT(20) NOT NULL ,
  `groupId` BIGINT(20) NULL DEFAULT NULL ,
  `companyId` BIGINT(20) NULL DEFAULT NULL ,
  `createDate` DATETIME NULL DEFAULT NULL ,
  `modifiedDate` DATETIME NULL DEFAULT NULL ,
  `name` VARCHAR(75) NULL DEFAULT NULL ,
  `portletIds` LONGTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`wsrpProducerId`) ,
  INDEX `IX_DD08A671` (`companyId` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure list_files
-- -----------------------------------------------------

USE `lportal`;
DROP procedure IF EXISTS `lportal`.`list_files`;
SHOW WARNINGS;

DELIMITER $$
USE `lportal`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `list_files`(idFolder int)
BEGIN

Select extension, title, description, version, fileEntryId
From  lportal.dlfileentry
Where folderId = idFolder;

END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure list_folders
-- -----------------------------------------------------

USE `lportal`;
DROP procedure IF EXISTS `lportal`.`list_folders`;
SHOW WARNINGS;

DELIMITER $$
USE `lportal`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `list_folders`()
BEGIN

Select folderId, parentFolderId, name, description from  lportal.dlfolder;

END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure list_tags
-- -----------------------------------------------------

USE `lportal`;
DROP procedure IF EXISTS `lportal`.`list_tags`;
SHOW WARNINGS;

DELIMITER $$
USE `lportal`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `list_tags`(fileId int)
BEGIN

select assettag.name
from lportal.assettag, lportal.assetentries_assettags, lportal.assetentry, lportal.dlfileentry
where assetentries_assettags.tagId=assettag.tagId
    and assetentries_assettags.entryId=assetentry.entryId
    and assetentry.classPK=dlfileentry.fileEntryId
    and dlfileentry.fileEntryId=fileId;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure teste_apagar
-- -----------------------------------------------------

USE `lportal`;
DROP procedure IF EXISTS `lportal`.`teste_apagar`;
SHOW WARNINGS;

DELIMITER $$
USE `lportal`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `teste_apagar`()
BEGIN

END$$

DELIMITER ;
SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;