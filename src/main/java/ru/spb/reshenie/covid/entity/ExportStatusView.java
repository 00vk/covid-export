package ru.spb.reshenie.covid.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by vkondratiev on 09.11.2021
 */
@Entity
@Immutable
@Table(name = "V_COVID_RESULT_EXPORTED")
public class ExportStatusView {

    @Id
    private Long id;
    private String patientName;
    private String ids;
    private LocalDate readyDate;
    private String orderNumber;
    private String serviceStatus;
    private String serviceMessage;
    private String guStatus;
    private String guMessage;
    private LocalDateTime statusDate;

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getIds() {
        return ids;
    }

    public LocalDate getReadyDate() {
        return readyDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public String getServiceMessage() {
        return serviceMessage;
    }

    public String getGuStatus() {
        return guStatus;
    }

    public String getGuMessage() {
        return guMessage;
    }

    public LocalDateTime getStatusDate() {
        return statusDate;
    }

}
