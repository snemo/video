package com.videorental.db.generator;

import org.h2.jdbcx.JdbcDataSource;

/**
 * H2 in memory data source configuration for JOOQ generator
 *
 * @author oleciwoj
 */
class H2InMemoryConfig {

    private static final String DB_URL_IN_MEMORY = "jdbc:h2:mem:library;DB_CLOSE_DELAY=-1;";

    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private H2InMemoryConfig() {}

    public static JdbcDataSource inMemoryDataSource() {
        return dataSource(DB_URL_IN_MEMORY);
    }

    private static JdbcDataSource dataSource(String url) {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setURL(url);
        return dataSource;
    }
}
