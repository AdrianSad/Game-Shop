INSERT INTO companies(id, name, nationality) VALUES (1, 'CD Projekt RED', 'Polish');
INSERT INTO companies(id, name, nationality) VALUES (2, 'Electronic Arts', 'American');
INSERT INTO companies(id, name, nationality) VALUES (3, 'Ubisoft', 'French');
INSERT INTO companies(id, name, nationality) VALUES (4, 'Activision Blizzard', 'American');

INSERT INTO users(id, first_name, last_name, email, password) VALUES (1, 'Adrian', 'Sadurski', 'admin@example.com', '$2y$12$29I3hHm6hAV0GrPYU8NHxOwAmNFjOvLb7V2bNCLQCq3eZHRFlp4Ge');
INSERT INTO users(id, first_name, last_name, email, password) VALUES (2, 'Jan', 'Kowalski', 'jan@example.com', '$2y$12$29I3hHm6hAV0GrPYU8NHxOwAmNFjOvLb7V2bNCLQCq3eZHRFlp4Ge');

INSERT INTO games(id, name, description, platforms, price, company_id, user_id) VALUES (1, 'Witcher 3', 'The Witcher 3: Wild Hunt is a story-driven, next-generation open world role-playing game, set in a visually stunning fantasy universe, full of meaningful choices and impactful consequences. You play as Geralt of Rivia, a monster hunter tasked with finding a child from an ancient prophecy.', 'PlayStation 4, Nintendo Switch, Xbox One, Microsoft Windows', 114.99, 1, 1);
INSERT INTO games(id, name, description, platforms, price, company_id, user_id) VALUES (2, 'Assassin’s Creed Origins', 'Assassin''s Creed Origins is an action-adventure stealth game played from a third-person perspective. Players complete quests—linear scenarios with set objectives—to progress through the story, earn experience points, and acquire new skills.', 'PlayStation 4, Xbox One, Microsoft Windows', 65.99, 3, 1);
INSERT INTO games(id, name, description, platforms, price, company_id, user_id) VALUES (3, 'Far Cry 3', 'Far Cry 3 is a 2012 first-person shooter developed by Ubisoft Montreal and published by Ubisoft. It is the third main installment in the Far Cry series. The game takes place on the fictional Rook Islands, a tropical archipelago which can be freely explored by players. Gameplay focuses on combat and exploration.', 'PlayStation 4, PlayStation 3, Xbox One, Xbox 360, Microsoft Windows', 91.32, 3, 2);

INSERT INTO categories(id, name) VALUES (1, 'RPG'), (2, 'FPP'), (3, 'FPS'), (4,'MMORPG'), (5, 'Tower Defense'), (6, 'Fighting'), (7, 'TPP'), (8, 'Sandbox');

INSERT INTO game_category(game_id, category_id) VALUES (1, 1), (1,7), (1,8);
INSERT INTO game_category(game_id, category_id) VALUES (2, 7), (2,8);
INSERT INTO game_category(game_id, category_id) VALUES (3, 2), (3,3), (3,8);

INSERT INTO user_roles(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles(id, name) VALUES (2, 'ROLE_CUSTOMER');

INSERT INTO users_roles(userID, roleID) VALUES (1,1);
INSERT INTO users_roles(userID, roleID) VALUES (2,2);
INSERT INTO users_roles(userID, roleID) VALUES (1,2);