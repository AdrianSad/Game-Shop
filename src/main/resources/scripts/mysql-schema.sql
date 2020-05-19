CREATE TABLE IF NOT EXISTS companies
(
id INTEGER NOT NULL auto_increment PRIMARY KEY,
name VARCHAR(64) NOT NULL,
nationality VARCHAR(20) NOT NULL,
logo LONGBLOB
);

CREATE TABLE IF NOT EXISTS games
(
id INTEGER NOT NULL auto_increment PRIMARY KEY,
name VARCHAR(64) NOT NULL,
description LONGBLOB NOT NULL,
platforms VARCHAR(128),
price FLOAT(2) NOT NULL,
company INTEGER NOT NULL,
image LONGBLOB,
FOREIGN KEY (company) REFERENCES companies(id)
);

CREATE TABLE IF NOT EXISTS categories
(
id INTEGER NOT NULL auto_increment PRIMARY KEY,
name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS game_category
(
game_id INTEGER NOT NULL,
category_id INTEGER NOT NULL,
FOREIGN KEY (game_id) REFERENCES games(id),
FOREIGN KEY (category_id) REFERENCES categories(id),
UNIQUE(game_id, category_id)
);