DROP TABLE IF EXISTS game_category;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS companies;

CREATE TABLE companies
(
id INTEGER IDENTITY PRIMARY KEY,
name VARCHAR(64) NOT NULL,
nationality VARCHAR(20) NOT NULL,
logo LONGBLOB
);

CREATE TABLE games
(
id INTEGER IDENTITY PRIMARY KEY,
name VARCHAR(64) NOT NULL,
description TEXT NOT NULL,
platforms VARCHAR(128),
price FLOAT(2) NOT NULL,
company_id INTEGER NOT NULL,
image LONGBLOB
);

ALTER TABLE games ADD CONSTRAINT company_cst FOREIGN KEY (company_id) REFERENCES companies(id);

CREATE TABLE categories
(
id INTEGER IDENTITY PRIMARY KEY,
name VARCHAR(64) NOT NULL
);

CREATE TABLE game_category
(
game_id INTEGER NOT NULL,
category_id INTEGER NOT NULL
);

ALTER TABLE game_category ADD CONSTRAINT game_cst FOREIGN KEY (game_id) REFERENCES games(id);
ALTER TABLE game_category ADD CONSTRAINT category_cst FOREIGN KEY (category_id) REFERENCES categories(id);
