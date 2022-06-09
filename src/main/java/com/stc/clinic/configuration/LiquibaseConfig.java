package com.stc.clinic.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public SpringLiquibase configure(){

        String changelogFile = "classpath:/db/master.xml";

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogFile);
        liquibase.setContexts("default");
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema("stc");
        liquibase.setDropFirst(false);
        liquibase.setShouldRun(true);

        return liquibase;
    }
}