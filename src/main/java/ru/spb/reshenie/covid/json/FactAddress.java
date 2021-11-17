package ru.spb.reshenie.covid.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vkondratiev on 12.08.2021
 */
public final class FactAddress {
    public final String town;
    public final String house;
    public final String region;
    public final String building;
    public final String district;
    public final String appartament;
    public final String streetName;

    @JsonCreator
    public FactAddress(@JsonProperty("town") String town,
                       @JsonProperty("house") String house,
                       @JsonProperty("region") String region,
                       @JsonProperty("building") String building,
                       @JsonProperty("district") String district,
                       @JsonProperty("appartament") String appartament,
                       @JsonProperty("streetName") String streetName){
        this.town = town;
        this.house = house;
        this.region = region;
        this.building = building;
        this.district = district;
        this.appartament = appartament;
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "FactAddress{" +
                "town='" + town + '\'' +
                ", house='" + house + '\'' +
                ", region='" + region + '\'' +
                ", building='" + building + '\'' +
                ", district='" + district + '\'' +
                ", appartament='" + appartament + '\'' +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}

