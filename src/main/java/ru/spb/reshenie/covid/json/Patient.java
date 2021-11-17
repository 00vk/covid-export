package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class Patient {
    public final String surname;
    public final String name;
    public final String patronymic;
    public final int gender;
    public final String birthday;
    public final String phone;
    public final String email;
    public final String documentType;
    public final String documentNumber;
    public final String documentSerNumber;
    public final String snils;
    public final String oms;
    public final Address address;

    @JsonCreator
    public Patient(@JsonProperty("surname") String surname,
                   @JsonProperty("name") String name,
                   @JsonProperty("patronymic") String patronymic,
                   @JsonProperty("gender") int gender,
                   @JsonProperty("birthday") String birthday,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("email") String email,
                   @JsonProperty("documentType") String documentType,
                   @JsonProperty("documentNumber") String documentNumber,
                   @JsonProperty("documentSerNumber") String documentSerNumber,
                   @JsonProperty("snils") String snils,
                   @JsonProperty("oms") String oms,
                   @JsonProperty("address") Address address){
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.documentSerNumber = documentSerNumber;
        this.snils = snils;
        this.oms = oms;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentSerNumber='" + documentSerNumber + '\'' +
                ", snils='" + snils + '\'' +
                ", oms='" + oms + '\'' +
                ", address=" + address +
                '}';
    }
}

