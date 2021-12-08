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
import ru.spb.reshenie.covid.CovidResultMapper;
import ru.spb.reshenie.covid.config.ServiceConfig;
import ru.spb.reshenie.covid.entity.CovidResultDataView;
import ru.spb.reshenie.covid.json.CovidRequest;
import ru.spb.reshenie.covid.repository.CovidResultRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vkondratiev on 07.11.2021
 * Сервис, осуществляющий отправку результатов
 */
@Service
@EnableScheduling
public class ExportResultService {

    private final RestTemplate template = new RestTemplate();
    private final ServiceConfig config;
    private final CovidResultMapper formatter;
    private final CovidResultRepository repository;
    private final AuthService authService;
    private final SaveExportStatusService statusService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ExportResultService(ServiceConfig config,
                               CovidResultMapper formatter,
                               CovidResultRepository repository,
                               AuthService authService,
                               SaveExportStatusService statusService) {
        this.config = config;
        this.formatter = formatter;
        this.repository = repository;
        this.authService = authService;
        this.statusService = statusService;
    }

    @Scheduled(cron = "${export.result.schedule}") // 2min
    public void exportResult() {
        String token = authService.token();
        if (token == null)
            return;

        List<CovidResultDataView> covidResultData = repository.findFirstFiftyRecords();
        if (covidResultData.isEmpty())
            return;
        CovidRequest request = formatter.createRequest(covidResultData, token);
        sendCovidResultRequest(request, "ext-orders-package");
    }

    public void reexportResult(String orderNumber) {
        CovidResultDataView data = repository.findByOrderNumber(orderNumber);
        String token = authService.token();
        if (data == null || token == null) {
            logger.warn("V_COVID_RESULT_DATA не содержит записи с ORDER_NUMBER=" + orderNumber);
            return;
        }
        CovidRequest request = formatter.createRequest(data, token);
        sendCovidResultRequest(request, "change-ext-order");
    }

    private void sendCovidResultRequest(CovidRequest request, String uriPath) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        URI uri = getUri(uriPath);
        logger.info("Отправка результатов исследований " + request.orderNumbers() + "...");
        logger.debug("Sending request: " + request);
        ResponseEntity<Map> response = template.exchange(uri, HttpMethod.POST, entity, Map.class);
        logger.debug("Response: " + response);
        saveExportStatus(response);
    }

    private void saveExportStatus(ResponseEntity<Map> response) {
        Object responseBody = response.getBody().get("body");
        List<LinkedHashMap<String, Object>> orders = (ArrayList<LinkedHashMap<String, Object>>) responseBody;
        for (LinkedHashMap<String, Object> order : orders) {
            String orderNumber = (String) order.get("number");
            String status = (String) order.get("status");
            String message = (String) order.get("message");
            Integer extId = (Integer) order.get("id");
            statusService.saveExportStatus(orderNumber, extId, status, message);
        }
    }

    private URI getUri(String path) {
        try {
            return new URI(config.covidServerUrl() + "/api/v2/order/" + path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
