package com.luizgareis.services;

import com.google.inject.Singleton;
import com.luizgareis.models.Customer;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@Singleton
public class CustomerService {

    Jdbi jdbi = Jdbi.create("jdbc:mysql://127.0.0.1/customer?characterEncoding=latin1&useConfigs=maxPerformance", "root", "Pacifico123*");

    public Customer findById(Integer id) {
        Customer customer =  jdbi.withHandle(handle -> handle.createQuery("SELECT c.* from CUSTOMER c where c.id = :id")
                .bind("id", id)
                .mapToBean(Customer.class).findFirst().get());
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> customers =  jdbi.withHandle(handle -> handle.createQuery("SELECT c.* FROM CUSTOMER c")
                .mapToBean(Customer.class).list());
        return customers;
    }

    public void insert(Customer customer) {
        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO CUSTOMER(id, uuid, name, email, birth_date, cpf, gender, main_address, createdAt, updateAt) VALUES (:id, :uuid, :name, :email, :birth_date, :cpf, :gender, :main_address, :create_at, :update_at)")
                .bindBean(customer)
                .execute());
    }

    public void update(Customer customer) {
        jdbi.withHandle(handle -> handle.createUpdate("UPDATE CUSTOMER set :uuid, :name, :email, :birth_date, :cpf, :gender, :main_address, :create_at, :update_at where id = :id")
                .bindBean(customer)
                .execute());
    }

    public void delete(Integer id) {
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM CUSTOMER WHERE ID = :id")
                .bind(0, id)
                .execute());
    }
}
