CREATE DEFINER=`root`@`localhost` TRIGGER `games_BEFORE_UPDATE` BEFORE UPDATE ON `games` FOR EACH ROW BEGIN
           IF NEW.price < 0 THEN
               SET NEW.price = 0;
           ELSEIF NEW.price > 200 THEN
               SET NEW.price = 200;
           END IF;
END