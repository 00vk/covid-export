package ru.spb.reshenie.covid.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by vkondratiev on 08.11.2021
 */
@Entity
@Table(schema = "SOLUTION_MED", name = "COVID_RESULT_EXPORT_STATUS")
public class ExportStatus {

    @Id
    @GeneratedValue(generator = "S_COVID_RESULT_EXPORT_STATUS")
    @SequenceGenerator(name = "S_COVID_RESULT_EXPORT_STATUS", sequenceName = "S_COVID_RESULT_EXPORT_STATUS", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column
    private Integer serviceId;

    @Column
    private String serviceStatus;

    @Column
    private String serviceMessage;

    @Column
    private Integer guId;

    @Column
    private String guStatus;

    @Column
    private String guMessage;

    @Column(name = "dat")
    private LocalDateTime date;

    public ExportStatus() {
    }

    public ExportStatus(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void updateDate() {
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public String getServiceMessage() {
        return serviceMessage;
    }

    public Integer getGuId() {
        return guId;
    }

    public String getGuStatus() {
        return guStatus;
    }

    public String getGuMessage() {
        return guMessage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public void setServiceMessage(String serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    public void setGuId(Integer guId) {
        this.guId = guId;
    }

    public void setGuStatus(String guStatus) {
        this.guStatus = guStatus;
    }

    public void setGuMessage(String guMessage) {
        this.guMessage = guMessage;
    }

    @Override
    public String toString() {
        return "ExportStatus{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", serviceId=" + serviceId +
                ", serviceStatus='" + serviceStatus + '\'' +
                ", serviceMessage='" + serviceMessage + '\'' +
                ", guId=" + guId +
                ", guStatus='" + guStatus + '\'' +
                ", guMessage='" + guMessage + '\'' +
                ", date=" + date +
                '}';
    }
}
