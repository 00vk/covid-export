package ru.spb.reshenie.covid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vkondratiev on 07.11.2021
 */

@Configuration
public class ServiceConfig {

    @Value("${depart}")
    private String depart;
    @Value("${lpu-token}")
    private String lpuToken;
    @Value("${covid-server-url}")
    private String covidServerUrl;
    @Value("${lab-ogrn}")
    private String labOgrn;
    @Value("${lab-name}")
    private String labName;
    @Value("${default-region}")
    private String defaultRegion;
    @Value("${covid-results-view-name}")
    private String covidResultsViewName;

    public String depart() {
        return depart;
    }

    public String lpuToken() {
        return lpuToken;
    }

    public String covidServerUrl() {
        return covidServerUrl;
    }

    public String labOgrn() {
        return labOgrn;
    }

    public String labName() {
        return labName;
    }

    public String defaultRegion() {
        return defaultRegion;
    }

    public String covidResultsViewName() {
        return covidResultsViewName;
    }
}
