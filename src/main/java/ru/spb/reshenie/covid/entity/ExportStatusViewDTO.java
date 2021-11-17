package ru.spb.reshenie.covid.entity;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by vkondratiev on 09.11.2021
 */
public class ExportStatusViewDTO implements Comparable<ExportStatusViewDTO>{

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final Long id;
    private final String patientName;
    private final String ids;
    private final String readyDate;
    private final String orderNumber;
    private final String serviceStatus;
    private final String serviceMessage;
    private final String guStatus;
    private final String guMessage;
    private final String statusDate;

    public ExportStatusViewDTO(ExportStatusView esv) {
        id = esv.getId();
        patientName = esv.getPatientName();
        ids = esv.getIds();
        readyDate = DATE_FORMATTER.format(esv.getReadyDate());
        orderNumber = esv.getOrderNumber();
        serviceStatus = esv.getServiceStatus();
        serviceMessage = esv.getServiceMessage();
        guStatus = esv.getGuStatus();
        guMessage = esv.getGuMessage();
        statusDate = DATE_TIME_FORMATTER.format(esv.getStatusDate());
    }

    public String getPatientName() {
        return patientName;
    }

    public String getIds() {
        return ids;
    }

    public String getReadyDate() {
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

    public String getStatusDate() {
        return statusDate;
    }

    @Override
    public int compareTo(ExportStatusViewDTO o) {
        return patientName.compareTo(o.patientName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExportStatusViewDTO that = (ExportStatusViewDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
