create table Purchases(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nfe VARCHAR(255),
    price_total DECIMAL(10,2) NOT NULL,
    created_at DATETIME NOT NULL,
    customer_id INT NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

create table Purchase_book(
	purchase_id INT NOT NULL,
	book_id INT NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES Purchases(id),
    FOREIGN KEY (book_id) REFERENCES Books(id),
    PRIMARY KEY (purchase_id, book_id)
);