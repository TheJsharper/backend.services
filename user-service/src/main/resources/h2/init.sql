CREATE TABLE USERS(
	id BIGINT auto_increment,
	name VARCHAR(250),
	balance INT,
	PRIMARY KEY (id)
);

CREATE TABLE USER_TRANSACTION(
	id BIGINT auto_increment,
	user_id BIGINT,
	amount INT,
	transaction_date TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE
	
);

INSERT INTO USERS(name, balance)
VALUES('sam', 1000), ('mike', 1200), ('jake', 800), ('marshal', 2000);