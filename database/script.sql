--Database
CREATE SCHEMA ecospark;

USE ecospark;

CREATE TABLE category (
    category_id VARCHAR(255) PRIMARY KEY,
    category_title VARCHAR(50) NOT NULL,
    category_description TEXT NULL,
    category_image_url VARCHAR NULL,
    category_status ENUM DEFAULT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT, 
    updated_by INT
);

CREATE TABLE product (
    product_id VARCHAR(255) PRIMARY KEY,
    product_title VARCHAR(50) NOT NULL,
    product_description TEXT NULL,
    product_image_url VARCHAR NULL,
    product_price DECIMAL(10,2) NOT NULL,
    product_stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT, 
    updated_by INT
);
