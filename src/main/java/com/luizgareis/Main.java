package com.luizgareis;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = getMySQLDataSource();
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        get("/hello", (req, res) -> "Hello World");
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
