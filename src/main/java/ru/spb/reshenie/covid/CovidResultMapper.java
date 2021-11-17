package ru.spb.reshenie.covid;

import org.springframework.stereotype.Component;
import ru.spb.reshenie.covid.config.ServiceConfig;
import ru.spb.reshenie.covid.entity.CovidResultDataView;
import ru.spb.reshenie.covid.json.*;

import java.util.*;

/**
 * Created by vkondratiev on 07.11.2021
 */
@Component
public class CovidResultMapper {

    private final ServiceConfig config;

    public CovidResultMapper(ServiceConfig config) {
        this.config = config;
    }

    public CovidRequest createRequest(List<CovidResultDataView> covidResultDataViews, String token) {
        Json[] json = getJson(covidResultDataViews);
        return new CovidRequest(token, config.depart(), json);
    }

    public CovidRequest createRequest(CovidResultDataView covidResultDataView, String token) {
        return createRequest(Collections.singletonList(covidResultDataView), token);
    }

    public CovidRequest createRequest(String token) {
        return createRequest(mock(), token);
    }

    private Json[] getJson(List<CovidResultDataView> covidResultDataViewList) {
        List<Json> jsons = new ArrayList<>();
        for (CovidResultDataView covidResultDataView : covidResultDataViewList) {
            Serv serv = getServ(covidResultDataView);
            Order order = getOrder(serv, covidResultDataView);
            jsons.add(new Json(order));
        }
        return jsons.toArray(new Json[0]);
    }

    private Order getOrder(Serv serv, CovidResultDataView covidResultDataView) {
        return new Order(
                covidResultDataView.getOrderNumber(),
                config.depart(),
                config.labName(),
                config.labOgrn(),
                covidResultDataView.getCustName(),
                covidResultDataView.getCustOgrn(),
                covidResultDataView.getOrderDate(),
                new Serv[]{serv},
                getPatient(covidResultDataView)
        );
    }

    private Serv getServ(CovidResultDataView covidResultDataView) {
        Integer type = covidResultDataView.getResultType();
        if (type == null) {
            type = 1;
        }
        Serv serv = new Serv(
                covidResultDataView.getServCode(),
                covidResultDataView.getServName(),
                covidResultDataView.getTestSystem(),
                covidResultDataView.getBiomaterDate(),
                covidResultDataView.getReadyDate(),
                covidResultDataView.getResult(),
                type
        );
        String valueStr = covidResultDataView.getResultValue();
        if (valueStr != null && !valueStr.isEmpty()) {
            serv.setValue(Double.parseDouble(valueStr));
        }
        return serv;
    }

    private Patient getPatient(CovidResultDataView covidResultDataView) {
        return new Patient(
                covidResultDataView.getLastName(),
                covidResultDataView.getFirstName(),
                covidResultDataView.getSecondName(),
                covidResultDataView.getSex(),
                covidResultDataView.getBirthday(),
                covidResultDataView.getPhone(),
                covidResultDataView.getEmail(),
                covidResultDataView.getDocType(),
                covidResultDataView.getDocNum(),
                covidResultDataView.getDocSerNum(),
                covidResultDataView.getSnils(),
                covidResultDataView.getOms(),
                getAddress(covidResultDataView)
        );
    }

    private Address getAddress(CovidResultDataView covidResultDataView) {
        return new Address(getRegAddress(covidResultDataView), getFactAddress(covidResultDataView));
    }

    private FactAddress getFactAddress(CovidResultDataView covidResultDataView) {
        String region = covidResultDataView.getFactRegion();
        if (region == null || region.isEmpty())
            region = config.defaultRegion();
        return new FactAddress(
                covidResultDataView.getFactTown(),
                covidResultDataView.getFactHouse(),
                region,
                covidResultDataView.getFactBuilding(),
                covidResultDataView.getFactDistrict(),
                covidResultDataView.getFactApt(),
                covidResultDataView.getFactStreet()
        );
    }

    private RegAddress getRegAddress(CovidResultDataView covidResultDataView) {
        return new RegAddress(
                covidResultDataView.getRegTown(),
                covidResultDataView.getRegHouse(),
                covidResultDataView.getRegRegion(),
                covidResultDataView.getRegBuilding(),
                covidResultDataView.getRegDistrict(),
                covidResultDataView.getRegApt(),
                covidResultDataView.getRegStreet()
        );
    }

    public List<CovidResultDataView> mock() {
        CovidResultDataView data1 = new CovidResultDataView();
        data1.setId(968L);
        data1.setOrderNumber(UUID.randomUUID().toString().substring(0, 27));
        data1.setCustName("Поликлиника №7");
        data1.setOrderDate("2021-10-22");
        data1.setServCode("500000");
        data1.setServName("ОПРЕДЕЛЕНИЕ РНК КОРОНАВИРУСА SARS-COV-2 МЕТОДОМ ОТ-ПЦР");
        data1.setBiomaterDate("2021-10-22");
        data1.setReadyDate("2021-10-23");
        data1.setResult(0);
        data1.setResultType(1);
        data1.setLastName("Абдулаев");
        data1.setFirstName("Абду");
        data1.setSecondName("Дибирович");
        data1.setSex(1);
        data1.setBirthday("1972-06-14");
        data1.setPhone("9064486740");
        data1.setDocType("Паспорт гражданина РФ");
        data1.setDocNum("999999");
        data1.setDocSerNum("9999");
        data1.setOms("0553720835000195");
        data1.setRegTown("махачкала");
        data1.setRegHouse("204а");
        data1.setRegRegion("Дагестан Респ");
        data1.setRegApt("23");
        data1.setRegStreet("гаджиева");


        CovidResultDataView data2 = new CovidResultDataView();
        data2.setId(78L);
        data2.setOrderNumber(UUID.randomUUID().toString().substring(0, 27));
        data2.setCustName("Хоздоговорная организация");
        data2.setOrderDate("2021-09-08");
        data2.setServCode("500000");
        data2.setServName("ОПРЕДЕЛЕНИЕ РНК КОРОНАВИРУСА SARS-COV-2 МЕТОДОМ ОТ-ПЦР");
        data2.setBiomaterDate("2021-09-08");
        data2.setReadyDate("2021-09-09");
        data2.setResult(1);
        data2.setResultType(1);
        data2.setLastName("Бахмудов");
        data2.setFirstName("Магомед");
        data2.setSecondName("Ахмедович");
        data2.setSex(1);
        data2.setBirthday("1991-02-08");
        data2.setDocType("Паспорт гражданина РФ");
        data2.setDocNum("888888");
        data2.setDocSerNum("8888");
        data2.setOms("0551900881000875");
        data2.setRegTown("asd");
        data2.setRegHouse("161");
        data2.setRegRegion("Дагестан Респ");
        data2.setRegBuilding("asd");
        data2.setRegDistrict("asd");
        data2.setRegApt("25");
        data2.setRegStreet("Орджоникидзе ул");

        return Arrays.asList(data1, data2);
    }

    public CovidResultDataView mockEdit() {
        CovidResultDataView data1 = new CovidResultDataView();
        data1.setId(968L);
        data1.setOrderNumber(UUID.randomUUID().toString().substring(0, 27));
        data1.setCustName("Поликлиника №777");
        data1.setOrderDate("2021-10-22");
        data1.setServCode("500000");
        data1.setServName("ОПРЕДЕЛЕНИЕ РНК КОРОНАВИРУСА SARS-COV-2 МЕТОДОМ ОТ-ПЦР");
        data1.setBiomaterDate("2021-10-22");
        data1.setReadyDate("2021-10-23");
        data1.setResult(0);
        data1.setResultType(1);
        data1.setLastName("Абдулаев");
        data1.setFirstName("Абду");
        data1.setSecondName("Дибирович");
        data1.setSex(1);
        data1.setBirthday("1972-06-14");
        data1.setPhone("9064486740");
        data1.setDocType("Паспорт гражданина РФ");
        data1.setDocNum("923860");
        data1.setDocSerNum("8217");
        data1.setOms("0553720835000195");
        data1.setRegTown("махачкала");
        data1.setRegHouse("204аff");
        data1.setRegRegion("Дагестан Респ");
        data1.setRegApt("23");
        data1.setRegStreet("гаджиева");

        return data1;
    }
}
