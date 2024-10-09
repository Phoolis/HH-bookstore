DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY
    , name VARCHAR(50) NOT NULL
);

CREATE TABLE books 
(id BIGSERIAL PRIMARY KEY
, title VARCHAR(150) NOT NULL
, author VARCHAR(150)
, publication_year INT
, isbn VARCHAR(50) NOT NULL
, price DECIMAL(10,2)
, categoryid BIGINT,
FOREIGN KEY (categoryid) REFERENCES categories(id)
 ON DELETE SET NULL
);

CREATE TABLE users
(id BIGSERIAL PRIMARY KEY
, username VARCHAR(100) NOT NULL UNIQUE
, password_hash VARCHAR(255) NOT NULL
, email VARCHAR(150)
, role VARCHAR(50) NOT NULL
);
 
INSERT INTO categories (name) VALUES ('Non-fiction');
INSERT INTO categories (name) VALUES ('Sci-fi');
INSERT INTO categories (name) VALUES ('Romance');
INSERT INTO categories (name) VALUES ('Comic');