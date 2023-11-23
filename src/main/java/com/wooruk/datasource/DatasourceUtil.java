package com.wooruk.datasource;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatasourceUtil {
    private final HikariDataSource ds;
    private static DatasourceUtil instance;
    private DatasourceUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/throughko");
        config.setUsername("root");
        config.setPassword("123123");

        ds = new HikariDataSource(config);
    }

    public static DatasourceUtil getInstance() {
        if (instance == null) {
            instance = new DatasourceUtil();
        }
        return instance;
    }

    public HikariDataSource getDs () {
        return ds;
    }
}
