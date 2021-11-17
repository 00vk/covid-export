package ru.spb.reshenie.covid.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by vkondratiev on 07.11.2021
 */
@Entity
@Immutable
@Table(name = "covidResultDataViewName") // флаг идентификатора используется в CovidViewNamingStrategy
public class CovidResultDataView implements Serializable {

    @Id
    private Long id;
    private String orderNumber;
    private String custName;
    private String custOgrn;
    private String orderDate;
    private String servCode;
    private String servName;
    private String testSystem;
    private String biomaterDate;
    private String readyDate;
    private Integer result;
    private Integer resultType;
    private String resultValue;
    private String lastName;
    private String firstName;
    private String secondName;
    private Integer sex;
    private String birthday;
    private String phone;
    private String email;
    private String docType;
    private String docNum;
    private String docSerNum;
    private String snils;
    private String oms;
    private String factTown;
    private String factHouse;
    private String factRegion;
    private String factBuilding;
    private String factDistrict;
    private String factApt;
    private String factStreet;
    private String regTown;
    private String regHouse;
    private String regRegion;
    private String regBuilding;
    private String regDistrict;
    private String regApt;
    private String regStreet;

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustName() {
        return custName == null ? null : custName.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getCustOgrn() {
        return custOgrn == null ? null : custOgrn.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getServCode() {
        return servCode == null ? null : servCode.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getServName() {
        return servName == null ? null : servName.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getTestSystem() {
        return testSystem == null ? null : testSystem.replaceAll("(?<=\\w)\"", "»")
                                                     .replaceAll("\"", "«");
    }

    public String getBiomaterDate() {
        return biomaterDate == null ? null : biomaterDate.replaceAll("(?<=\\w)\"", "»")
                                                         .replaceAll("\"", "«");
    }

    public String getReadyDate() {
        return readyDate == null ? null : readyDate.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public Integer getResult() {
        return result;
    }

    public Integer getResultType() {
        return resultType;
    }

    public String getResultValue() {
        return resultValue == null ? null : resultValue.replaceAll("(?<=\\w)\"", "»")
                                                       .replaceAll("\"", "«");
    }

    public String getLastName() {
        return lastName == null ? null : lastName.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getFirstName() {
        return firstName == null ? null : firstName.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public String getSecondName() {
        return secondName == null ? null : secondName.replaceAll("(?<=\\w)\"", "»")
                                                     .replaceAll("\"", "«");
    }

    public Integer getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday == null ? null : birthday.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getPhone() {
        return phone == null ? null : phone.replaceAll("(?<=\\w)\"", "»")
                                           .replaceAll("\"", "«");
    }

    public String getEmail() {
        return email == null ? null : email.replaceAll("(?<=\\w)\"", "»")
                                           .replaceAll("\"", "«");
    }

    public String getDocType() {
        return docType == null ? null : docType.replaceAll("(?<=\\w)\"", "»")
                                               .replaceAll("\"", "«");
    }

    public String getDocNum() {
        return docNum == null ? null : docNum.replaceAll("(?<=\\w)\"", "»")
                                             .replaceAll("\"", "«");
    }

    public String getDocSerNum() {
        return docSerNum == null ? null : docSerNum.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public String getSnils() {
        return snils == null ? null : snils.replaceAll("(?<=\\w)\"", "»")
                                           .replaceAll("\"", "«");
    }

    public String getOms() {
        return oms == null ? null : oms.replaceAll("(?<=\\w)\"", "»")
                                       .replaceAll("\"", "«");
    }

    public String getFactTown() {
        return factTown == null ? null : factTown.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getFactHouse() {
        return factHouse == null ? null : factHouse.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public String getFactRegion() {
        return factRegion == null ? null : factRegion.replaceAll("(?<=\\w)\"", "»")
                                                     .replaceAll("\"", "«");
    }

    public String getFactBuilding() {
        return factBuilding == null ? null : factBuilding.replaceAll("(?<=\\w)\"", "»")
                                                         .replaceAll("\"", "«");
    }

    public String getFactDistrict() {
        return factDistrict == null ? null : factDistrict.replaceAll("(?<=\\w)\"", "»")
                                                         .replaceAll("\"", "«");
    }

    public String getFactApt() {
        return factApt == null ? null : factApt.replaceAll("(?<=\\w)\"", "»")
                                               .replaceAll("\"", "«");
    }

    public String getFactStreet() {
        return factStreet == null ? null : factStreet.replaceAll("(?<=\\w)\"", "»")
                                                     .replaceAll("\"", "«");
    }

    public String getRegTown() {
        return regTown == null ? null : regTown.replaceAll("(?<=\\w)\"", "»")
                                               .replaceAll("\"", "«");
    }

    public String getRegHouse() {
        return regHouse == null ? null : regHouse.replaceAll("(?<=\\w)\"", "»")
                                                 .replaceAll("\"", "«");
    }

    public String getRegRegion() {
        return regRegion == null ? null : regRegion.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public String getRegBuilding() {
        return regBuilding == null ? null : regBuilding.replaceAll("(?<=\\w)\"", "»")
                                                       .replaceAll("\"", "«");
    }

    public String getRegDistrict() {
        return regDistrict == null ? null : regDistrict.replaceAll("(?<=\\w)\"", "»")
                                                       .replaceAll("\"", "«");
    }

    public String getRegApt() {
        return regApt == null ? null : regApt.replaceAll("(?<=\\w)\"", "»")
                                             .replaceAll("\"", "«");
    }

    public String getRegStreet() {
        return regStreet == null ? null : regStreet.replaceAll("(?<=\\w)\"", "»")
                                                   .replaceAll("\"", "«");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCustOgrn(String custOgrn) {
        this.custOgrn = custOgrn;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setServCode(String servCode) {
        this.servCode = servCode;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public void setTestSystem(String testSystem) {
        this.testSystem = testSystem;
    }

    public void setBiomaterDate(String biomaterDate) {
        this.biomaterDate = biomaterDate;
    }

    public void setReadyDate(String readyDate) {
        this.readyDate = readyDate;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public void setDocSerNum(String docSerNum) {
        this.docSerNum = docSerNum;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public void setOms(String oms) {
        this.oms = oms;
    }

    public void setFactTown(String factTown) {
        this.factTown = factTown;
    }

    public void setFactHouse(String factHouse) {
        this.factHouse = factHouse;
    }

    public void setFactRegion(String factRegion) {
        this.factRegion = factRegion;
    }

    public void setFactBuilding(String factBuilding) {
        this.factBuilding = factBuilding;
    }

    public void setFactDistrict(String factDistrict) {
        this.factDistrict = factDistrict;
    }

    public void setFactApt(String factApt) {
        this.factApt = factApt;
    }

    public void setFactStreet(String factStreet) {
        this.factStreet = factStreet;
    }

    public void setRegTown(String regTown) {
        this.regTown = regTown;
    }

    public void setRegHouse(String regHouse) {
        this.regHouse = regHouse;
    }

    public void setRegRegion(String regRegion) {
        this.regRegion = regRegion;
    }

    public void setRegBuilding(String regBuilding) {
        this.regBuilding = regBuilding;
    }

    public void setRegDistrict(String regDistrict) {
        this.regDistrict = regDistrict;
    }

    public void setRegApt(String regApt) {
        this.regApt = regApt;
    }

    public void setRegStreet(String regStreet) {
        this.regStreet = regStreet;
    }
}
