package ru.spb.reshenie.covid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spb.reshenie.covid.entity.ExportStatus;
import ru.spb.reshenie.covid.repository.ExportStatusRepository;

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
