package com.mimdevelopment.iot.challenge.homeautomate.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 2:27 PM
 */

@Entity
public class BarometricPayload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "barometricPayloadId")
    private Long identity;

    @Basic(optional = true)
    private Long sensorManufacturer;

    @Basic(optional = true)
    private Long sensorIdentifier;

    @Basic(optional = false)
    private Date date;

    @Basic(optional = true)
    private Float temperature;

    @Basic(optional = false)
    private Float pressure;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "gatewayId")
    private Gateway gateway;

    @Basic(optional = false)
    private Double reportedLatitude;

    @Basic(optional = false)
    private Double reportedLongitude;

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date receivedDate) {
        this.date = receivedDate;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Double getReportedLatitude() {
        return reportedLatitude;
    }

    public void setReportedLatitude(Double reportedLatitude) {
        this.reportedLatitude = reportedLatitude;
    }

    public Double getReportedLongitude() {
        return reportedLongitude;
    }

    public void setReportedLongitude(Double reportedLongitude) {
        this.reportedLongitude = reportedLongitude;
    }

    public Long getSensorManufacturer() {
        return sensorManufacturer;
    }

    public void setSensorManufacturer(Long sensorManufacturer) {
        this.sensorManufacturer = sensorManufacturer;
    }

    public Long getSensorIdentifier() {
        return sensorIdentifier;
    }

    public void setSensorIdentifier(Long sensorIdentifier) {
        this.sensorIdentifier = sensorIdentifier;
    }
}
