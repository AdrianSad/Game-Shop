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
company_id INTEGER NOT NULL,
image LONGBLOB,
FOREIGN KEY (company_id) REFERENCES companies(id)
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

CREATE TABLE IF NOT EXISTS users
(
    id INTEGER auto_increment PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id INTEGER auto_increment PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    userID INTEGER NOT NULL,
    roleID INTEGER NOT NULL,
    PRIMARY KEY (userID, roleID),
    FOREIGN KEY (userID) REFERENCES users(id),
    FOREIGN KEY (roleID) REFERENCES user_roles(id)
);