package com.mimdevelopment.iot.challenge.homeautomate.dto;

import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.BarometricPayload;
import com.mimdevelopment.iot.challenge.homeautomate.model.Gateway;
import com.mimdevelopment.iot.challenge.homeautomate.model.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:33 AM
 */
public class GatewayDTO {

    private Long identity = null;

    private String identifier;

    private Date firstSeen;

    private Date lastSeen;

    private Boolean active;

    private LocationDTO currentLocation;

    List<BarometricPayload> barometricPayloads;

    public Long getIdentity() {
        return identity;
    }

    public List<BarometricPayload> getBarometricPayloads() {
        return barometricPayloads;
    }

    public void setBarometricPayloads(List<BarometricPayload> barometricPayloads) {
        this.barometricPayloads = barometricPayloads;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Date firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocationDTO getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LocationDTO currentLocation) {
        this.currentLocation = currentLocation;
    }

    public GatewayDTO() {
    }

    public GatewayDTO(Long identity){
        this.identity = identity;
    }

    public GatewayDTO(String identifier) {
        this.identifier = identifier;
    }

    public GatewayDTO(Gateway src) {

        this.identity = src.getIdentity();

        this.identifier = src.getIdentifier();

        this.firstSeen = src.getFirstSeen();
        this.lastSeen = src.getLastSeen();

        this.active = src.isActive();

        this.currentLocation = src.getCurrentLocation() != null ? new LocationDTO(src.getCurrentLocation()) : null;
    }

    public void validate() throws HomeAutomatePropertyValidationException {

        if(identifier == null || identifier.length() == 0){
            throw new HomeAutomatePropertyValidationException("The GatewayDTO.Identifier property value cannot be null or empty.");
        }

        if(currentLocation == null){
            throw new HomeAutomatePropertyValidationException("The GatewayDTO.CurrentLocation property value cannot be null.");
        }   else{
            currentLocation.validate();
        }

        if(firstSeen == null){
            throw new HomeAutomatePropertyValidationException("The GatewayDTO.FirstSeen property value cannot be null.");
        }

        if(lastSeen == null){
            throw new HomeAutomatePropertyValidationException("The GatewayDTO.LastSeen property value cannot be null.");
        }
    }
}
