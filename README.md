# PhoneBook

###SQL
####Table users:
CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, login VARCHAR(50) UNIQUE NOT NULL, password VARCHAR(50) NOT NULL,
fullname VARCHAR(50) NOT NULL, PRIMARY KEY (id))
####Table phones:
CREATE TABLE phones (id INT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL DEFAULT '0', lastname VARCHAR(50) NOT NULL DEFAULT '0',
firstname VARCHAR(50) NOT NULL DEFAULT '0', patronymic VARCHAR(50) NOT NULL DEFAULT '0', `mobilephone VARCHAR(50) NOT NULL DEFAULT '0', homephone VARCHAR(50) DEFAULT '0',
address VARCHAR(50) DEFAULT '0',email VARCHAR(50) DEFAULT '0', PRIMARY KEY (id))

####Properties file sample:
\####Select type of data storage `db` or `file` <br>
spring.profiles.active=db<br>
\####File property<br>
file.path=C:/wp/test.json<br>
\####DB proproperties<br>
db.url=jdbc:mysql://localhost:3306/phonebook<br>
db.user=admin<br>
db.pass=pass<br>

