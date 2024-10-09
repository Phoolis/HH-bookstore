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

INSERT INTO books (title, author, publication_year, isbn, price, categoryid) 
VALUES 
('Taisteluni', 'Hullu Miäs', 1933, '897A-F98AWF-986A89F', 30.99, (SELECT id FROM categories WHERE name='Comic')),
('Roopen Elämä ja Teot', 'Don Rosa', 1998, '597A-F423AWF-986A89F', 53.80, (SELECT id FROM categories WHERE name='Comic')),
('Leijat', 'Bron Bronskoviski', 1833, '1237A-F98AWF-18669E', 19.50, (SELECT id FROM categories WHERE name='Romance'));

INSERT INTO users (username, password_hash, email, role) VALUES
('user', '$2a$10$nWnmISXRi3VQz.9n7w8VUeJxqnY3LuHj0EcpBzX5gz38JfTqORz0y', NULL, 'USER'),
('admin', '$2a$10$DuH5X4QlR5jYB9hwv162cONrLnZoUSs80Ze4J7L3Aj3bBLWey8v6S', NULL, 'ADMIN');
