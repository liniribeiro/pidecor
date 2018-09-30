CREATE TABLE PROVIDER (
    id UUID NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	cnpj VARCHAR(255) NOT NULL,
	status VARCHAR(255),
	providers_api_id VARCHAR(255) NOT NULL,
	qtd_items integer,
    category VARCHAR(255),
    reputation VARCHAR(255),
    social_name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    home_page VARCHAR(255),
    email VARCHAR(255),
    companyType VARCHAR(255),
    annual_billing VARCHAR(255),
    foundation_year integer,
    qtd_client integer,
    percentage DECIMAL(12,4),
    delivery_days integer,
    rejection_rate DECIMAL(12,4),
    qtd_items_sold integer
);

CREATE TABLE customer (
	id UUID NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL,
	useraccount UUID,
	CPF VARCHAR(255) NOT NULL,
	CRIATIONDATE DATE,
	address VARCHAR(255) NOT NULL,
	CONSTRAINT customer_useraccount_FKEY FOREIGN KEY (useraccount) REFERENCES useraccount (ID)
);

CREATE TABLE customer_order (
	id UUID NOT NULL PRIMARY KEY,
	customer UUID,
	status VARCHAR(255) NOT NULL,
	CONSTRAINT customer_order_client_FKEY FOREIGN KEY (customer) REFERENCES customer (ID)
);

CREATE TABLE item (
    id UUID NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	photoLink VARCHAR(255) NOT NULL,
	price DECIMAL(12,4) NOT NULL,
	provider UUID,
    CREATIONDATE DATE,
    status VARCHAR(255) NOT NULL,
    customer_order UUID,
	CONSTRAINT item_provider_FKEY FOREIGN KEY (provider) REFERENCES provider (ID),
	CONSTRAINT item_customer_order_FKEY FOREIGN KEY (customer_order) REFERENCES customer_order (ID)
);

