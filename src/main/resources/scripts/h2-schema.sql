DROP TABLE IF EXISTS game_category;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS users_roles;

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

CREATE TABLE users
(
    id INTEGER IDENTITY PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
UNIQUE (email)
);

CREATE TABLE user_roles
(
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE users_roles
(
    userID INTEGER NOT NULL,
    roleID INTEGER NOT NULL
);

ALTER TABLE users_roles ADD CONSTRAINT user_cst FOREIGN KEY (userID) REFERENCES users(id);
ALTER TABLE users_roles ADD CONSTRAINT role_cst FOREIGN KEY (roleID) REFERENCES user_roles(id);
ALTER TABLE game_category ADD CONSTRAINT game_cst FOREIGN KEY (game_id) REFERENCES games(id);
ALTER TABLE game_category ADD CONSTRAINT category_cst FOREIGN KEY (category_id) REFERENCES categories(id);
