package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class CovidRequest {
    public final String token;
    public final String depart_number;
    public final String json;
    private final List<String> orderNumbers;

    @JsonCreator
    public CovidRequest(@JsonProperty("token") String token,
                        @JsonProperty("depart_number") String depart_number,
                        @JsonProperty("json") Json[] json){
        this.token = token;
        this.depart_number = depart_number;
        orderNumbers = Arrays.stream(json)
                             .map(jsonItem -> jsonItem.order.number)
                             .collect(Collectors.toList());
        try {
            this.json = new ObjectMapper().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "CovidRequest{" +
                "token='" + token + '\'' +
                ", depart_number='" + depart_number + '\'' +
                ", json='" + json + '\'' +
                '}';
    }

    public List<String> orderNumbers() {
        return orderNumbers;
    }
}

