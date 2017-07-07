DROP database isproj3;
CREATE database isproj3;
use isproj3;

CREATE TABLE Subscribers (
	email VARCHAR(20),
	
	course INT,
	confirmed BOOLEAN DEFAULT 0,
	fordeletion BOOLEAN DEFAULT 0,
	
	PRIMARY KEY (email,course)

);

CREATE TABLE EmailCount (
	id INT AUTO_INCREMENT,
	total INT,
	
	today INT,
	PRIMARY KEY(id)

);




CREATE TABLE Materials (
	id INT ,
	courseid INT,

	PRIMARY KEY(id)

);


INSERT INTO EmailCount (total,today) VALUES(0,0);
