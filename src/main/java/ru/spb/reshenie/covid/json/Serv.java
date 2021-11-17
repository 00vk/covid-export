package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class Serv {
    public final String code;
    public final String name;
    public final String testSystem;
    public final String biomaterDate;
    public final String readyDate;
    public final int result;
    public final int type;
    public double value;

    @JsonCreator
    public Serv(@JsonProperty("code") String code,
                @JsonProperty("name") String name,
                @JsonProperty("testSystem") String testSystem,
                @JsonProperty("biomaterDate") String biomaterDate,
                @JsonProperty("readyDate") String readyDate,
                @JsonProperty("result") int result,
                @JsonProperty("type") int type){
        this.code = code;
        this.name = name;
        this.testSystem = testSystem;
        this.biomaterDate = biomaterDate;
        this.readyDate = readyDate;
        this.result = result;
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Serv{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", testSystem='" + testSystem + '\'' +
                ", biomaterDate='" + biomaterDate + '\'' +
                ", readyDate='" + readyDate + '\'' +
                ", result=" + result +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}

