package com.videorental.db.generator;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.h2.jdbcx.JdbcDataSource;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.h2.H2Database;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Target;

/**
 * JOOQ class generator
 * Generator will start H2 in memory database, then it will execute Liquibase.
 * When Liquibase generates whole schema in H2, JOOQ generator will create
 * classes into db/build/generated-src
 *
 * @author oleciwoj
 */
public class JooqClassGenerator {

    public static void main(String args[]) throws Exception {
        JdbcDataSource dataSource = H2InMemoryConfig.inMemoryDataSource();

        liquibase(dataSource);
        jooq(dataSource);
    }

    private static void liquibase(JdbcDataSource dataSource) throws Exception {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                        new JdbcConnection(dataSource.getConnection()));

        Liquibase liquibase = new Liquibase("liquibase/master.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts());
    }

    private static void jooq(JdbcDataSource dataSource) throws Exception {
        Configuration configuration = new Configuration()
                .withGenerator(new Generator()
                        .withName("org.jooq.codegen.DefaultGenerator")
                        .withDatabase(new org.jooq.meta.jaxb.Database()
                                .withName(H2Database.class.getCanonicalName())
                                .withInputSchema("PUBLIC")
                                .withExcludes("databasechangelog | databasechangeloglock")
                                .withRecordVersionFields("version"))
                        .withTarget(new Target()
                                .withDirectory("build/generated-src")
                                .withPackageName("com.videorental.db.generated"))
                        .withGenerate(new Generate().withJavaTimeTypes(true))
                );

        GenerationTool generationTool = new GenerationTool();
        generationTool.setConnection(dataSource.getConnection());
        generationTool.run(configuration);
    }
}
