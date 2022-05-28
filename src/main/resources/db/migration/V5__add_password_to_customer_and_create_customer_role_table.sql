ALTER TABLE Customers ADD COLUMN password VARCHAR(255) NOT NULL;

create table Customer_role(
	customer_id int not null,
    role varchar(255) not null,
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);