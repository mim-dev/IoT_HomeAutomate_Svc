package com.mimdevelopment.iot.challenge.homeautomate.model;


import javax.persistence.*;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 11:34 AM
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationId")
    private Long identity;

    @Basic(optional = false)
    private Double latitude;

    @Basic(optional = false)
    private Double longitude;

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

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(Long identity, Double latitude, Double longitude) {
        this.identity = identity;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
