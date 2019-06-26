package com.videorental.db.config;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

/**
 * @author oleciwoj
 */
public class LiquibaseDataSourceHolder {

    private static DSLContext dslContext;

    private static DataSource getDataSource() throws Exception {
        DataSource ds = H2Config.inMemoryDataSource();

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                        new JdbcConnection(ds.getConnection()));

        Liquibase liquibase = new Liquibase("liquibase/master.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts());

        return ds;
    }

    public static synchronized DSLContext getDSLContext() throws Exception {
        if (dslContext != null)
            return dslContext;

        return dslContext = DSL.using(getDataSource(), SQLDialect.H2);
    }
}
