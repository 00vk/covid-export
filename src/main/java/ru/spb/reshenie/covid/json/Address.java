package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class Address {
    public final RegAddress regAddress;
    public final FactAddress factAddress;

    @JsonCreator
    public Address(@JsonProperty("regAddress") RegAddress regAddress,
                   @JsonProperty("factAddress") FactAddress factAddress){
        this.regAddress = regAddress;
        this.factAddress = factAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "regAddress=" + regAddress +
                ", factAddress=" + factAddress +
                '}';
    }
}

