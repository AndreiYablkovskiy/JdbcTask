 CREATE SCHEMA `aircompany_db` ;

 CREATE TABLE `aircompany_db`.`role` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`));

   CREATE TABLE `aircompany_db`.`user` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255) NOT NULL,
   `role_id` INT NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES aircompany_db.role(id));

   CREATE TABLE `aircompany_db`.`order` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `number` INT NOT NULL,
   `order_date` DATE NOT NULL,
   `user_id` INT NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES aircompany_db.user(id));

   CREATE TABLE `aircompany_db`.`route` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `departure_id` INT NULL,
   `arrival_id` INT NULL,
   PRIMARY KEY (`id`));

   CREATE TABLE `aircompany_db`.`ticket` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `route_id` INT NOT NULL,
   `order_id` INT NOT NULL,
   `passport_data` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT fk_route FOREIGN KEY (route_id) REFERENCES aircompany_db.route(id),
   CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES aircompany_db.order(id));


   CREATE TABLE `aircompany_db`.`aircompany` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`));

   CREATE TABLE `aircompany_db`.`airplane` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255),
   `aircompany_id` INT NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT fk_aircompany FOREIGN KEY (aircompany_id) REFERENCES aircompany_db.aircompany(id));

   CREATE TABLE `aircompany_db`.`airplane_route` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `airplane_id` INT NOT NULL,
   `routes_id` INT NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT fk_airplane FOREIGN KEY (airplane_id) REFERENCES aircompany_db.airplane(id),
   CONSTRAINT fk_routes FOREIGN KEY (routes_id) REFERENCES aircompany_db.route(id));