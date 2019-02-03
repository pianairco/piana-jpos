DROP DATABASE IF EXISTS qp;
CREATE DATABASE qp;
ALTER DATABASE qp CHARACTER SET utf8 COLLATE utf8_general_ci;
use qp;

DROP TABLE IF EXISTS user;
create table user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(128),
  password VARCHAR(128),
  roles VARCHAR(1024)
);
ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO user (username, password, roles) VALUES
        ('admin', '123', 'admin,user'),
        ('user1', '123', 'user'),
        ('user2', '123', 'customer');

