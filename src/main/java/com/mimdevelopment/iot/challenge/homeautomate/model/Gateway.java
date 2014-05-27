package com.mimdevelopment.iot.challenge.homeautomate.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: luther stanton
 * Date: 4/18/14
 * Time: 11:30 AM
 */

@Entity
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gatewayId")
    private Long identity;

    @Basic(optional = false)
    private String identifier;

    @Basic(optional = false)
    private Date firstSeen;

    @Basic(optional = false)
    private Date lastSeen;

    @Basic(optional = false)
    private Boolean active;

    @OneToOne(orphanRemoval = true, optional = false, cascade = CascadeType.PERSIST)
    private Location currentLocation;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Location> locationHistory;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gatewayId")
    List<BarometricPayload> barometricPayloads;

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
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

    public List<Location> getLocationHistory() {
        return locationHistory;
    }

    public void setLocationHistory(List<Location> locations) {
        this.locationHistory = locations;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Gateway() {
    }

    public Gateway(
            Long idenity,
            String identifier,
            Date firstSeen,
            Date lastSeen,
            Boolean active,
            Location currentLocation,
            List<Location> locationHistory,
            List<BarometricPayload> barometricPayloads) {
        this.identity = idenity;
        this.identifier = identifier;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
        this.active = active;
        this.currentLocation = currentLocation;
        this.locationHistory = locationHistory;
        this.barometricPayloads = barometricPayloads;
    }
}
