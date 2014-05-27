package com.mimdevelopment.iot.challenge.homeautomate.dto;

import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.Location;

import javax.persistence.*;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 8:17 PM
 */

public class LocationDTO {

    private Long identity;

    private Double latitude;

    private Double longitude;

    private Boolean dirty = true;

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocationDTO() {
    }

    public LocationDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Boolean getDirty() {
        return dirty;
    }

    public LocationDTO(Location src) {

        if (src != null) {
            this.identity = src.getIdentity();
            this.latitude = src.getLatitude();
            this.longitude = src.getLongitude();
        }
    }

    public Location toLocation(){
        return new Location(this.identity,  this.latitude, this.longitude);
    }

    public void validate() throws HomeAutomatePropertyValidationException {
        if(latitude == null){
            throw new HomeAutomatePropertyValidationException("The LocationDTO.Latitude property value cannot be null.");
        }

        if(longitude == null){
            throw new HomeAutomatePropertyValidationException("The LocationDTO.Longitude property value cannot be null.");
        }
    }

}
