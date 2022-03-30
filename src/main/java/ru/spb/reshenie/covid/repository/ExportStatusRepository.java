package ru.spb.reshenie.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spb.reshenie.covid.entity.ExportStatus;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vkondratiev on 08.11.2021
 */
@Transactional
@Repository
public interface ExportStatusRepository extends JpaRepository<ExportStatus, Long> {

    ExportStatus findByOrderNumber(String orderNumber);

    @Query(""
           + "SELECT es "
           + "  FROM ExportStatus es"
           + " WHERE (es.guStatus = 'received' "
           + "        OR es.guStatus IS NULL "
           + "        OR es.guMessage IN ('ESIA_ERROR', 'Не удалось получить статус', 'Ответ не получен'))"
           + "   AND (es.serviceStatus = 'ok' OR es.serviceStatus IS NULL)"
           + "   AND es.date >= :dateFrom"
           + "   AND es.date <= :dateTo"
           + "   AND rownum <= 500")
    List<ExportStatus> findRecordsToCheckStatus(
            @Param("dateFrom") LocalDateTime dateFrom,
            @Param("dateTo") LocalDateTime dateTo
    );
}
