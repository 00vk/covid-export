package ru.spb.reshenie.covid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.spb.reshenie.covid.config.ServiceConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkondratiev on 04.11.2021
 * Сервис, осуществляющий авторизацию
 * Получаемый тестовый токен: 5D779458-E9BA-8B18-1A80-E92469EA62E9
 */

@Service
@EnableScheduling
public class AuthService {

    private final ServiceConfig serviceConfig;
    private final RestTemplate template = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String token;

    public AuthService(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // each 10 mins
    private void authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(authRequestBody(), headers);
        URI uri = getUri();
        ResponseEntity<Map> response;
        logger.info("Выполняется авторизация...");
        try {
            response = template.exchange(uri, HttpMethod.POST, entity, Map.class);
        } catch (RestClientException e) {
            logger.error("Не удалось получить токен", e);
            token = null;
            return;
        }
        String token = ((Map<String, Map<String, String>>) response.getBody()).get("body").get("token");
        logger.debug("Response: " + response);
        logger.info("Успешная авторизация. Получен токен: " + token);
        this.token = token;
    }

    public String token() {
        if (token == null)
            authenticate();
        return token;
    }

    private Map<String, String> authRequestBody() {
        Map<String, String> loginPassword = new HashMap<>();
        loginPassword.put("depart_number", serviceConfig.depart());
        loginPassword.put("token", serviceConfig.lpuToken());
        return loginPassword;
    }

    private URI getUri() {
        try {
            return new URI(serviceConfig.covidServerUrl() + "/api/v2/order/get-depart-token");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
