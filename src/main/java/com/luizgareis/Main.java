package com.luizgareis;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.luizgareis.injector.AppInjector;
import com.luizgareis.models.Address;
import com.luizgareis.models.Customer;
import com.luizgareis.services.AddressService;
import com.luizgareis.services.CustomerService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = getMySQLDataSource();
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        Injector injector   =   Guice.createInjector(new AppInjector());
        AddressService addressService = injector.getInstance(AddressService.class);
        CustomerService customerService = injector.getInstance(CustomerService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        get("/customer/:id/address", (req, res) -> {
            String id = req.params(":id");
            List<Address> address = addressService.findByCustomerId(Integer.valueOf(id));

            String addressJson = objectMapper.writeValueAsString(address);
            return addressJson;
        });

        get("/customer/:id_cust/address/:id", (req, res) -> {
            String id = req.params(":id");
            String customer = req.params(":id_cust");
            Address address = addressService.findByCustomerIdAndAddressId(Integer.valueOf(id), Integer.valueOf(customer));

            String addressJson = objectMapper.writeValueAsString(address);
            return addressJson;
        });

        post("/customer/:id/address", (req, res) -> {
            String id = req.params(":id");
            Address address = objectMapper.readValue(req.body(), Address.class);
            addressService.insert(address, Integer.valueOf(id));

            return "Address created";
        });

        put("/customer/:id_cust/address/:id", (req, res) -> {
            String id = req.params(":id");
            String customer = req.params(":id_cust");
            Address address = addressService.findByCustomerIdAndAddressId(Integer.valueOf(id), Integer.valueOf(customer));
            addressService.update(address);

            return "Address updated";
        });

        delete("/customer/:id_cust/address/:id", (req, res) -> {
            String id = req.params(":id");
            String customer = req.params(":id_cust");
            addressService.delete(Integer.valueOf(id));

            return "Address deleted";
        });

        get("/customer/:id", (req, res) -> {
            String id = req.params(":id");
            Customer customer = customerService.findById(Integer.valueOf(id));

            String addressJson = objectMapper.writeValueAsString(customer);
            return addressJson;
        });

        get("/customer", (req, res) -> {
            List<Customer> customers = customerService.findAll();

            String addressJson = objectMapper.writeValueAsString(customers);
            return addressJson;
        });

        post("/customer", (req, res) -> {
            Customer customer = objectMapper.readValue(req.body(), Customer.class);
            customerService.insert(customer);

            return "Customer created";
        });

        put("/customer/:id", (req, res) -> {
            String id = req.params(":id");
            Customer customer = customerService.findById(Integer.valueOf(id));
            customerService.update(customer);

            return "Customer updated";
        });

        delete("/customer/:id", (req, res) -> {
            String id = req.params(":id");
            customerService.delete(Integer.valueOf(id));

            return "Customer deleted";
        });

    }

    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS = null;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://127.0.0.1/customer?characterEncoding=latin1&useConfigs=maxPerformance");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("Pacifico123*");
        return mysqlDS;
    }
}
