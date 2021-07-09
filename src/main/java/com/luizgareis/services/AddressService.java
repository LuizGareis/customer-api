package com.luizgareis.services;

import com.google.inject.Singleton;
import com.luizgareis.models.Address;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@Singleton
public class AddressService {

    Jdbi jdbi = Jdbi.create("jdbc:mysql://127.0.0.1/customer?characterEncoding=latin1&useConfigs=maxPerformance", "root", "Pacifico123*");

    public List<Address> findByCustomerId(Integer id) {
        List<Address> address =  jdbi.withHandle(handle -> handle.createQuery("SELECT a.* from ADDRESS a where a.id_customer = :id")
                .bind("id", id)
                .mapToBean(Address.class).list());
        return address;
    }

    public Address findByCustomerIdAndAddressId(Integer customerId, Integer id) {
        Address address =  jdbi.withHandle(handle -> handle.createQuery("SELECT a.* from ADDRESS a where a.id = :id and a.id_customer = :customerId")
                .bind(0, id)
                .bind(1, customerId)
                .mapToBean(Address.class).findFirst().get());
        return address;
    }

    public List<Address> findAll() {
        List<Address> adresses =  jdbi.withHandle(handle -> handle.createQuery("SELECT a.* FROM ADDRESS a")
                .mapToBean(Address.class).list());
        return adresses;
    }

    public void insert(Address address, Integer customer_id) {
        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO ADDRESS(id, state, city, neighborhood, zip_code, street, number, additional_information, main, customer_id) VALUES (:id, :state, :city, :neighborhood, :zip_code, :street, :number, :additional_information, :main, :customer_id)")
                .bind("customer_id",customer_id)
                .bindBean(address)
                .execute());
    }

    public void update(Address address) {
        jdbi.withHandle(handle -> handle.createUpdate("UPDATE ADDRESS set state = :state, city = :city, neighborhood = :neighborhood, zip_code = :zip_code, street = :street, number = :number, additional_information = :additional_information, main = :main where id = :id")
                .bindBean(address)
                .execute());
    }

    public void delete(Integer id) {
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM ADDRESS WHERE ID = :id")
                .bind(0, id)
                .execute());
    }

}
