package com.videorental.db.config;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

/**
 * H2 in memory data source configuration
 *
 * @author oleciwoj
 */
public final class H2Config {

    private static final String DB_URL_IN_MEMORY = "jdbc:h2:mem:luvat;DB_CLOSE_DELAY=-1;";

    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private H2Config() {}

    public static DataSource inMemoryDataSource() {
        return dataSource(DB_URL_IN_MEMORY);
    }

    private static JdbcDataSource dataSource(String url) {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(url);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }
}
