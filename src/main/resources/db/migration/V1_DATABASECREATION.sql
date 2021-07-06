create table customer.ADDRESS (
                                  id integer NOT NULL AUTO_INCREMENT,
                                  state varchar(2) not null,
                                  city varchar(255) not null,
                                  neighborhood varchar(255) not null,
                                  zip_code varchar(20) not null,
                                  street varchar(255) not null,
                                  number varchar(10),
                                  additionalInformation varchar(255),
                                  main boolean default true,
                                  id_customer int not null,
                                  CONSTRAINT PRIMARY KEY (ID));


create table customer.CUSTOMER (
                                   id integer(64) NOT NULL AUTO_INCREMENT,
                                   name varchar(255),
                                   email varchar(255),
                                   birth_date varchar(20),
                                   cpf varchar(20),
                                   gender varchar(10),
                                   created_at datetime,
                                   update_at datetime,
                                   main_address int,
                                   address int,
                                   CONSTRAINT PRIMARY KEY (ID));

ALTER TABLE customer.ADDRESS
    ADD FOREIGN KEY (id_customer) REFERENCES CUSTOMER(ID);

ALTER TABLE customer.CUSTOMER
    ADD FOREIGN KEY (main_address) REFERENCES ADDRESS(ID);