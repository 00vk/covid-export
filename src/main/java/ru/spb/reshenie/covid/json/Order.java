package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class Order {
    public final String number;
    public final String depart;
    public final String laboratoryName;
    public final String laboratoryOgrn;
    public final String nameMo;
    public final String ogrn;
    public final String orderDate;
    public final Serv[] serv;
    public final Patient patient;

    @JsonCreator
    public Order(@JsonProperty("number") String number,
                 @JsonProperty("depart") String depart,
                 @JsonProperty("laboratoryName") String laboratoryName,
                 @JsonProperty("laboratoryOgrn") String laboratoryOgrn,
                 @JsonProperty("nameMo") String nameMo,
                 @JsonProperty("ogrn") String ogrn,
                 @JsonProperty("orderDate") String orderDate,
                 @JsonProperty("serv") Serv[] serv,
                 @JsonProperty("patient") Patient patient){
        this.number = number;
        this.depart = depart;
        this.laboratoryName = laboratoryName;
        this.laboratoryOgrn = laboratoryOgrn;
        this.nameMo = nameMo;
        this.ogrn = ogrn;
        this.orderDate = orderDate;
        this.serv = serv;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number='" + number + '\'' +
                ", depart='" + depart + '\'' +
                ", laboratoryName='" + laboratoryName + '\'' +
                ", laboratoryOgrn='" + laboratoryOgrn + '\'' +
                ", nameMo='" + nameMo + '\'' +
                ", ogrn='" + ogrn + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", serv=" + Arrays.toString(serv) +
                ", patient=" + patient +
                '}';
    }
}

