package ru.spb.reshenie.covid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spb.reshenie.covid.entity.ExportStatus;
import ru.spb.reshenie.covid.repository.ExportStatusRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vkondratiev on 08.11.2021
 */
@Service
public class SaveExportStatusService {

    private final ExportStatusRepository repository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SaveExportStatusService(ExportStatusRepository repository) {
        this.repository = repository;
    }

    public List<String[]> queryOrdersToCheckStatus() {
        LocalDateTime dateFrom = LocalDateTime.now().minusDays(2);
        LocalDateTime dateTo = LocalDateTime.now().minusHours(6);
        List<ExportStatus> records = repository.findRecordsToCheckStatus(dateFrom, dateTo);
        int size = records.size();
        logger.info("Найдено заказов для проверки статуса: " + size);
        if (size == 0)
            return new ArrayList<>();

        String[] orderNum = new String[size];
        for (int i = 0; i < size; i++) {
            ExportStatus record = records.get(i);
            record.updateDate();
            repository.save(record);
            orderNum[i] = record.getOrderNumber();
        }

        List<String[]> groupedByFifty = new ArrayList<>();
        int idx = 0;
        while (idx < orderNum.length) {
            int end = Math.min(idx + 50, orderNum.length);
            groupedByFifty.add(Arrays.copyOfRange(orderNum, idx, end));
            idx += end;
        }
        return groupedByFifty;
    }

    public void saveExportStatus(String orderNumber,
                                 Integer serviceId,
                                 String serviceStatus,
                                 String serviceMessage) {
        ExportStatus statusEntity = repository.findByOrderNumber(orderNumber);
        if (statusEntity == null) {
            statusEntity = new ExportStatus(orderNumber);
        }
        statusEntity.setServiceId(serviceId);
        statusEntity.setServiceStatus(serviceStatus);
        statusEntity.setServiceMessage(serviceMessage);
        statusEntity.updateDate();
        logger.info("Сохранение записи в БД: " + statusEntity);
        repository.save(statusEntity);
    }

    public void updateGuStatus(String orderNumber,
                               Integer guId,
                               String guStatus,
                               String guMessage) {
        ExportStatus statusEntity = repository.findByOrderNumber(orderNumber);
        if (statusEntity == null) {
            statusEntity = new ExportStatus(orderNumber);
        }
        statusEntity.setGuId(guId);
        statusEntity.setGuStatus(guStatus);
        statusEntity.setGuMessage(guMessage);
        statusEntity.updateDate();
        logger.info("Сохранение записи в БД: " + statusEntity);
        repository.save(statusEntity);
    }
}
