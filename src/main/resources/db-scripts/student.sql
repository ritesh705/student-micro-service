DROP TABLE IF EXISTS Student;

CREATE TABLE Student (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) DEFAULT NULL,
  email varchar(30) DEFAULT NULL,
  address_id int DEFAULT NULL,
  PRIMARY KEY (id)
)

INSERT INTO `student` VALUES (1,'Raj','Dave','raj_dave@yahoo.com',1),(2,'John','Smith','john_smith@gmail.com',2);