package ru.spb.reshenie.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.reshenie.covid.entity.ExportStatusView;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vkondratiev on 09.11.2021
 */
@Repository
@Transactional
public interface ExportStatusViewRepository extends JpaRepository<ExportStatusView, Long> {

    default List<ExportStatusView> findByStatusDate(LocalDate date) {
        List<ExportStatusView> byStatusDateBetween;
        byStatusDateBetween = findByStatusDateBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        return byStatusDateBetween;
    }

    List<ExportStatusView> findByStatusDateBetween(LocalDateTime from, LocalDateTime to);
}