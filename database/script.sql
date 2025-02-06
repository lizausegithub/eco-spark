--Database
CREATE SCHEMA ecospark;

USE ecospark;

--table
CREATE TABLE category (
    category_id VARCHAR(255) PRIMARY KEY,
    category_title VARCHAR(50) NOT NULL,
    category_description TEXT NULL,
    category_image_url TEXT NULL,
    category_status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255) , 
    updated_by VARCHAR(255)
);

CREATE TABLE product (
    product_id VARCHAR(255) PRIMARY KEY,
    category_id VARCHAR(255) NULL,
    product_title VARCHAR(50) NOT NULL,
    product_description TEXT NULL,
    product_image_url TEXT NULL,
    product_price DOUBLE NOT NULL,
    product_stock INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES category(id) 
);

CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_address TEXT,
    user_phone VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255)
);

CREATE TABLE order (
    order_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    order_total_price DOUBLE NOT NULL,
    order_status ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED' ,'CANCELLED','CART') DEFAULT 'PENDING',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id) 
);

CREATE TABLE order_item (
    order_item_id VARCHAR(255) PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL, 
    order_item_quantity INT NOT NULL,
    order_item_price DOUBLE NOT NULL,
    order_item_status ENUM('REMOVED', 'ADDED') DEFAULT 'ADDED',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES order(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE wishlist (
    wishlist_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE payment (
    payment_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    order_id INT NOT NULL,
    payment_amount DECIMAL(10,2) NOT NULL,
    payment_method ENUM('CREDIT_CARD', 'DEBIT_CARD', 'PAYPAL', 'NET_BANKING', 'COD') NOT NULL,
    payment_status ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING',
    payment_transaction_id VARCHAR(255) UNIQUE NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES order(id) ON DELETE CASCADE
);

CREATE TABLE review (
    id INT VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    review_rating INT CHECK (rating BETWEEN 1 AND 5),
    review_description TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255), 
    updated_by VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
