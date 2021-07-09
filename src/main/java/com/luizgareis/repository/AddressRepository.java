package com.luizgareis.repository;

import com.luizgareis.models.Address;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AddressRepository {

    @SqlUpdate("INSERT INTO ADDRESS(id, state, city, neighborhood, zip_code, street, number, additional_information, main) VALUES (:id, :state, :city, :neighborhood, :zip_code, :street, :number, :additional_information, :main)")
    Address insertBean(@BindBean Address address);

    @SqlUpdate("UPDATE ADDRESS set state = :state, city = :city, neighborhood = :neighborhood, zip_code = :zip_code, street = :street, number = :number, additional_information = :additional_information, main = :main where id = :id")
    Address updateBean(@Bind("id") Integer id);

    @SqlQuery("SELECT a.* FROM ADDRESS a")
    List<Address> findAll();

    @SqlQuery("SELECT a.* from ADDRESS a where a.id = :id")
    Address findById(@Bind("id") Integer id);

    @SqlQuery("DELETE FROM ADDRESS WHERE ID = :id")
    void delete(@Bind("id") Integer id);

}
