package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class Json {
    public final Order order;

    @JsonCreator
    public Json(@JsonProperty("order") Order order){
        this.order = order;
    }

    @Override
    public String toString() {
        return "Json{" +
                "order=" + order +
                '}';
    }
}
