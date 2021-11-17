package ru.spb.reshenie.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.reshenie.covid.entity.ExportStatus;

import javax.transaction.Transactional;

/**
 * Created by vkondratiev on 08.11.2021
 */
@Transactional
@Repository
public interface ExportStatusRepository extends JpaRepository<ExportStatus, Long> {

    ExportStatus findByOrderNumber(String orderNumber);
}
