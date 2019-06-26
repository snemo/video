package com.videorental.db.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = prepareLiquibaseConfig(dataSource);
        liquibase.setContexts("prod");
        return liquibase;
    }

    @Bean
    @Profile("dev")
    public SpringLiquibase liquibaseDev(DataSource dataSource) {
        SpringLiquibase liquibase = prepareLiquibaseConfig(dataSource);
        liquibase.setContexts("dev");
        return liquibase;
    }

    private SpringLiquibase prepareLiquibaseConfig(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/master.xml");

        return liquibase;
    }
}
