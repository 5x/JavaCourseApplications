SET GLOBAL time_zone = '+2:00';

DROP DATABASE IF EXISTS brewery;

CREATE DATABASE brewery
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_bin;

USE brewery;

-- For MySQL < 5.6
-- SET storage_engine = InnoDB;


CREATE TABLE city (
  city_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name    VARCHAR(60)      NOT NULL,

  PRIMARY KEY (city_id)
);


CREATE TABLE factory (
  factory_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  city       INTEGER UNSIGNED NOT NULL,
  title      VARCHAR(60)      NOT NULL UNIQUE,

  PRIMARY KEY (factory_id),
  FOREIGN KEY (city) REFERENCES city (city_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);


CREATE TABLE factory_unit (
  unit_id     INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  factory     INTEGER UNSIGNED NOT NULL,
  description VARCHAR(160)     NULL,

  PRIMARY KEY (unit_id),
  FOREIGN KEY (factory) REFERENCES factory (factory_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE beer_styles (
  beer_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name    VARCHAR(160)     NOT NULL UNIQUE,

  PRIMARY KEY (beer_id)
);


CREATE TABLE beer_factory_unit (
  beer_id      INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  beer_type    INTEGER UNSIGNED NOT NULL,
  factory_unit INTEGER UNSIGNED NOT NULL,

  PRIMARY KEY (beer_id),
  UNIQUE KEY (beer_type, factory_unit),
  FOREIGN KEY (beer_type) REFERENCES beer_styles (beer_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (factory_unit) REFERENCES factory_unit (unit_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE trader (
  trader_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name      VARCHAR(80)      NOT NULL UNIQUE,

  PRIMARY KEY (trader_id)
);


CREATE TABLE warehouse (
  warehouse_id INTEGER UNSIGNED NOT NULL  AUTO_INCREMENT,
  factory_unit INTEGER UNSIGNED NOT NULL,
  capacity     INTEGER UNSIGNED NOT NULL  DEFAULT 0,
  title        VARCHAR(60)      NOT NULL  UNIQUE,
  description  VARCHAR(160)     NULL,

  PRIMARY KEY (warehouse_id),
  FOREIGN KEY (factory_unit) REFERENCES factory_unit (unit_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE batch (
  batch_id  INTEGER UNSIGNED NOT NULL  AUTO_INCREMENT,
  warehouse INTEGER UNSIGNED NOT NULL,
  trader    INTEGER UNSIGNED NULL,
  created   TIMESTAMP        NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  shipment  TIMESTAMP        NULL      DEFAULT NULL,
  note      VARCHAR(160)     NULL,

  PRIMARY KEY (batch_id),
  FOREIGN KEY (warehouse) REFERENCES warehouse (warehouse_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (trader) REFERENCES trader (trader_id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);


CREATE TABLE batch_beer (
  batch  INTEGER UNSIGNED NOT NULL,
  beer   INTEGER UNSIGNED NOT NULL,
  amount INTEGER          NOT NULL DEFAULT 0,

  PRIMARY KEY (batch, beer),
  FOREIGN KEY (batch) REFERENCES batch (batch_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (beer) REFERENCES beer_factory_unit (beer_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- --------------------------------------------
-- Test data
-- --------------------------------------------


-- Dump data of "city" ----------------------------
INSERT INTO city (`city_id`, `name`) VALUES
  ('1', 'Kyiv'),
  ('2', 'Odesa'),
  ('3', 'Krasnopavlika');
-- ---------------------------------------------------------


-- Dump data of "factory" ----------------------------
INSERT INTO factory (`factory_id`, `city`, `title`) VALUES
  ('1', '1', 'Kiev First Brewery'),
  ('2', '1', 'Kiev Second Brewery'),
  ('3', '2', 'Odesa Brewery');
-- ---------------------------------------------------------


-- Dump data of "factory_unit" ----------------------------
INSERT INTO factory_unit (`unit_id`, `factory`, `description`) VALUES
  ('1', '1', 'factory#1, unit#1'),
  ('2', '1', 'factory#1, unit#2'),
  ('3', '1', 'factory#1, unit#3'),
  ('4', '2', 'factory#2, unit#1'),
  ('5', '2', 'factory#2, unit#2'),
  ('6', '3', 'factory#3, unit#1'),
  ('7', '3', 'factory#3, unit#2');
-- ---------------------------------------------------------


-- Dump data of "beer_styles" ----------------------------
INSERT INTO beer_styles (`beer_id`, `name`) VALUES
  ('1', 'Witbier'),
  ('2', 'Red Ale'),
  ('3', 'Dunkelweizen'),
  ('4', 'Porter'),
  ('5', 'Pilsner'),
  ('6', 'Dortmunder'),
  ('7', 'Wiener Lager'),
  ('8', 'Doppelbock'),
  ('9', 'Bock'),
  ('10', 'Weizenbock'),
  ('11', 'Eisbock'),
  ('12', 'Schwarzbier'),
  ('13', 'Lambic'),
  ('14', 'Rauchbier');
-- ---------------------------------------------------------


-- Dump data of "beer_factory_unit" ----------------------------
INSERT INTO beer_factory_unit (`beer_id`, `beer_type`, `factory_unit`) VALUES
  ('1', '1', '1'),
  ('2', '2', '1'),
  ('3', '3', '1'),
  ('4', '4', '1'),
  ('5', '5', '1'),
  ('6', '6', '1'),
  ('7', '7', '1'),
  ('8', '1', '2'),
  ('9', '2', '2'),
  ('10', '3', '2'),
  ('11', '10', '2'),
  ('12', '11', '2'),
  ('13', '12', '2'),
  ('14', '13', '2'),
  ('15', '5', '3'),
  ('16', '6', '3'),
  ('17', '7', '3'),
  ('18', '8', '3'),
  ('19', '9', '3'),
  ('20', '10', '3'),
  ('30', '10', '7'),
  ('31', '10', '4'),
  ('32', '11', '4'),
  ('33', '12', '5'),
  ('34', '13', '5'),
  ('35', '5', '5'),
  ('36', '6', '6'),
  ('37', '7', '7'),
  ('38', '8', '7'),
  ('39', '9', '7'),
  ('40', '1', '3'),
  ('41', '1', '7'),
  ('42', '1', '5');
-- ---------------------------------------------------------


-- Dump data of "trader" ----------------------------
INSERT INTO trader (`trader_id`, `name`) VALUES
  ('1', 'Metro'),
  ('2', 'EXSO UA'),
  ('3', 'FETraider');
-- ---------------------------------------------------------


-- Dump data of "trader" ----------------------------
INSERT INTO warehouse (`warehouse_id`, `factory_unit`, `capacity`, `title`) VALUES
  ('1', '1', '0', 'Warehouse#1'),
  ('2', '1', '0', 'Warehouse#2'),
  ('3', '1', '0', 'Warehouse#3'),
  ('4', '2', '0', 'Warehouse#4'),
  ('5', '3', '0', 'Warehouse#5'),
  ('6', '3', '0', 'Warehouse#6'),
  ('7', '4', '0', 'Warehouse#7'),
  ('8', '4', '0', 'Warehouse#8'),
  ('9', '4', '0', 'Warehouse#9'),
  ('10', '4', '0', 'Warehouse#10'),
  ('11', '4', '0', 'Warehouse#11'),
  ('12', '5', '0', 'Warehouse#12'),
  ('13', '5', '0', 'Warehouse#13'),
  ('14', '6', '0', 'Warehouse#14'),
  ('15', '6', '0', 'Warehouse#15'),
  ('16', '6', '0', 'Warehouse#16'),
  ('17', '7', '0', 'Warehouse#17'),
  ('18', '7', '0', 'Warehouse#18'),
  ('19', '7', '0', 'Warehouse#19'),
  ('20', '7', '0', 'Warehouse#20');
-- ---------------------------------------------------------


-- Dump data of "batch" ----------------------------
INSERT INTO batch (`batch_id`, `warehouse`, `trader`) VALUES
  ('1', '1', '1'),
  ('2', '2', '1'),
  ('3', '3', '1'),
  ('4', '4', '1'),
  ('5', '5', '1'),
  ('6', '6', '1'),
  ('7', '7', '1'),
  ('8', '8', '1'),
  ('9', '9', '1'),
  ('10', '10', '1'),
  ('11', '11', '1'),
  ('12', '12', '1'),
  ('13', '13', '1'),
  ('14', '14', '1'),
  ('15', '15', '1'),
  ('16', '16', '1'),
  ('17', '17', '1'),
  ('18', '18', '1'),
  ('19', '19', '1'),
  ('20', '20', '1'),
  ('21', '1', '1'),
  ('22', '2', '1'),
  ('23', '3', '1'),
  ('24', '4', '1'),
  ('25', '5', '1'),
  ('26', '6', '1'),
  ('27', '7', '1'),
  ('28', '8', '1'),
  ('29', '9', '1'),
  ('30', '10', '1'),
  ('31', '11', '1'),
  ('32', '12', '1'),
  ('33', '13', '1'),
  ('34', '14', '1'),
  ('35', '15', '1'),
  ('36', '16', '1'),
  ('37', '17', '1'),
  ('38', '18', '1'),
  ('39', '19', '1'),
  ('40', '20', '1'),
  ('41', '1', '2'),
  ('42', '1', '2'),
  ('43', '1', '2'),
  ('44', '1', '2'),
  ('45', '4', '2'),
  ('46', '7', '2'),
  ('47', '6', '2'),
  ('48', '6', '1'),
  ('49', '6', '1'),
  ('50', '6', '1'),
  ('51', '6', '1'),
  ('52', '5', '1'),
  ('53', '5', '2'),
  ('54', '5', '2'),
  ('55', '5', '2'),
  ('56', '5', '2'),
  ('57', '5', '2'),
  ('58', '11', '1'),
  ('59', '11', '1'),
  ('60', '12', '1'),
  ('61', '13', '1'),
  ('62', '14', '1'),
  ('63', '14', '1'),
  ('64', '14', '2'),
  ('65', '14', '2'),
  ('66', '14', '2'),
  ('67', '4', '1'),
  ('68', '6', '1'),
  ('69', '7', '2'),
  ('70', '9', '2'),
  ('71', '10', '2'),
  ('72', '18', '1'),
  ('73', '18', '1'),
  ('74', '18', '3'),
  ('75', '18', '3'),
  ('76', '3', '3'),
  ('77', '17', '3'),
  ('78', '17', '3'),
  ('79', '17', '3'),
  ('80', '17', '3');
-- ---------------------------------------------------------


-- Dump data of "batch_beer" ----------------------------
INSERT INTO batch_beer (`batch`, `beer`, `amount`) VALUES
  ('1', '1', '10'),
  ('2', '2', '10'),
  ('3', '3', '10'),
  ('4', '4', '10'),
  ('5', '5', '10'),
  ('6', '6', '10'),
  ('7', '7', '10'),
  ('8', '8', '10'),
  ('9', '9', '10'),
  ('10', '10', '18'),
  ('11', '11', '18'),
  ('12', '12', '18'),
  ('13', '13', '18'),
  ('14', '14', '18'),
  ('15', '15', '18'),
  ('16', '16', '18'),
  ('17', '17', '18'),
  ('18', '18', '18'),
  ('19', '19', '18'),
  ('20', '20', '18'),
  ('21', '1', '12'),
  ('22', '2', '12'),
  ('23', '3', '12'),
  ('24', '4', '12'),
  ('25', '5', '12'),
  ('26', '6', '12'),
  ('27', '7', '12'),
  ('28', '8', '12'),
  ('29', '9', '12'),
  ('30', '10', '100'),
  ('31', '11', '100'),
  ('32', '12', '100'),
  ('33', '13', '100'),
  ('34', '14', '51'),
  ('35', '15', '51'),
  ('36', '16', '51'),
  ('37', '17', '51'),
  ('38', '18', '51'),
  ('39', '19', '51'),
  ('40', '20', '51'),
  ('41', '1', '2'),
  ('42', '1', '2'),
  ('43', '1', '2'),
  ('44', '1', '2'),
  ('45', '4', '2'),
  ('46', '7', '2'),
  ('47', '6', '2'),
  ('48', '6', '1'),
  ('49', '6', '1'),
  ('50', '6', '1'),
  ('51', '6', '1'),
  ('52', '5', '1'),
  ('53', '5', '2'),
  ('54', '5', '2'),
  ('55', '5', '2'),
  ('56', '5', '2'),
  ('57', '5', '2'),
  ('58', '11', '1'),
  ('59', '11', '1'),
  ('60', '12', '1'),
  ('61', '13', '1'),
  ('62', '14', '1'),
  ('63', '14', '1'),
  ('64', '14', '2'),
  ('65', '14', '2'),
  ('66', '14', '2'),
  ('67', '4', '1'),
  ('68', '6', '1'),
  ('69', '7', '2'),
  ('70', '9', '2'),
  ('71', '10', '2'),
  ('72', '18', '1'),
  ('73', '18', '1'),
  ('74', '18', '3'),
  ('75', '18', '3'),
  ('76', '3', '3'),
  ('77', '17', '3'),
  ('78', '17', '3'),
  ('79', '17', '3'),
  ('80', '17', '3');
-- ---------------------------------------------------------
