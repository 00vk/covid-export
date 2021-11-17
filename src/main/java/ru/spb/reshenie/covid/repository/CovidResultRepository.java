package ru.spb.reshenie.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.spb.reshenie.covid.entity.CovidResultDataView;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by vkondratiev on 07.11.2021
 */

@Repository
@Transactional
public interface CovidResultRepository extends JpaRepository<CovidResultDataView, Long> {

    CovidResultDataView findByOrderNumber(String orderNumber);

    @Query("SELECT v FROM CovidResultDataView v WHERE NOT EXISTS (SELECT es.id FROM ExportStatus es WHERE es.orderNumber = v.orderNumber) AND rownum <= 50")
    List<CovidResultDataView> findFirstFiftyRecords();
}