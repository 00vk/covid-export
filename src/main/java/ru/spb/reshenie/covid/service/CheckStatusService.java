package ru.spb.reshenie.covid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.spb.reshenie.covid.config.ServiceConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vkondratiev on 08.11.2021
 */
@Service
@EnableScheduling
public class CheckStatusService {

    private final ServiceConfig config;
    private final AuthService authService;
    private final SaveExportStatusService statusService;
    private final RestTemplate template = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public CheckStatusService(ServiceConfig config,
                              AuthService authService,
                              SaveExportStatusService statusService) {
        this.config = config;
        this.authService = authService;
        this.statusService = statusService;
    }

    @Scheduled(cron = "${import.status.schedule}") // 10min
    public void checkStatuses() {
        List<String[]> ordersGroupedByFifty = statusService.queryOrdersToCheckStatus();
        for (String[] orders : ordersGroupedByFifty) {
            checkStatusesByOrderNumber(orders);
        }
    }

    public void checkStatusesByOrderNumber(String... orderNumbers) {
        if (orderNumbers.length == 0)
            return;
        List<String> orders = Arrays.asList(orderNumbers);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Map<String, Object> requestBody = checkStatusRequest();
        requestBody.put("orders", orders);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
        URI uri = getUri("status-by-orders");
        logger.info("?????????????????? ?????????????? ???? ??????????????: " + orders + "...");
        ResponseEntity<Map> response = template.exchange(uri, HttpMethod.POST, entity, Map.class);
        logger.debug("Response: " + response);
        saveGosuslugiStatus(response);
    }

    private void saveGosuslugiStatus(ResponseEntity<Map> response) {
        if (response.getBody() == null)
            return;
        Object body = response.getBody().get("body");
        if (!(body instanceof Map) || !((Map<?, ?>) body).containsKey("data")) {
            logger.warn("?????????????? ???? ??????????????!");
            return;
        }
        List<Map<String, Object>> statuses = ((Map<String, List<Map<String, Object>>>) body).get("data");
        for (Map<String, Object> status : statuses) {
            String orderNumber = (String) status.get("number");
            String guStatus = (String) status.get("status");
            String guMessage = (String) status.get("error");
            Integer guId = (Integer) status.get("id");
            statusService.updateGuStatus(orderNumber, guId, guStatus, guMessage);
        }
    }

    private Map<String, Object> checkStatusRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("depart_number", config.depart());
        requestBody.put("token", authService.token());
        return requestBody;
    }

    private URI getUri(String path) {
        try {
            return new URI(config.covidServerUrl() + "/api/v2/order/" + path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
