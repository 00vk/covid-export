package ru.spb.reshenie.covid.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vkondratiev on 11.11.2021
 */

@Configuration
public class CovidViewNamingStrategy extends SpringPhysicalNamingStrategy {

    private final ServiceConfig serviceConfig;

    public CovidViewNamingStrategy(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        if ("covidResultDataViewName".equals(identifier.getText()))
            return Identifier.toIdentifier(serviceConfig.covidResultsViewName());
        return identifier;
    }
}