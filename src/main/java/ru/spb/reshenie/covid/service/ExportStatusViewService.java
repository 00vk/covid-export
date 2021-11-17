package ru.spb.reshenie.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.reshenie.covid.entity.ExportStatusView;
import ru.spb.reshenie.covid.repository.ExportStatusViewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vkondratiev on 09.11.2021
 */
@Service
public class ExportStatusViewService {

    private final ExportStatusViewRepository repository;

    @Autowired
    public ExportStatusViewService(ExportStatusViewRepository repository) {
        this.repository = repository;
    }

    public List<ExportStatusView> findLastTwoWeeks() {
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.plusDays(-14).atStartOfDay();
        LocalDateTime end = now.plusDays(1).atStartOfDay();
        return repository.findByStatusDateBetween(start, end);
    }

}
