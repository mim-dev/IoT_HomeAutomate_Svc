package com.mimdevelopment.iot.challenge.homeautomate.dto;

import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/28/14
 * Time: 9:36 AM
 */

public class BarometricPayloadDTO {

    private Long identity;

    private Date date;

    private Float temperature;
    private Float pressure;

    private Double reportedLatitude;
    private Double reportedLongitude;

    private Long sensorManufacturer;
    private Long sensorIdentifier;

    private GatewayDTO gateway;

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

    public GatewayDTO getGateway() {
        return gateway;
    }

    public void setGateway(GatewayDTO gateway) {
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

    public BarometricPayloadDTO() {
    }

    public BarometricPayloadDTO(
            Date date,
            Float temperature,
            Float pressure,
            Double reportedLatitude,
            Double reportedLongitude,
            Long sensorManufacturer,
            Long sensorIdentifier,
            GatewayDTO gateway) {
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.reportedLatitude = reportedLatitude;
        this.reportedLongitude = reportedLongitude;

        this.sensorManufacturer = sensorManufacturer;
        this.sensorIdentifier = sensorIdentifier;
        this.gateway = gateway;
    }

    public void validate() throws HomeAutomatePropertyValidationException {

        if(gateway == null){
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.Gateway property value cannot be null.");
        }  else if(gateway.getIdentity() == null) {
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.Gateway.Identity property value cannot be null.");
        }

        if(date == null){
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.Date property value cannot be null or empty.");
        }

        if(pressure == null){
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.Pressure property value cannot be null or empty.");
        }

        if(reportedLatitude == null){
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.ReportedLatitude property value cannot be null or empty.");
        }

        if (reportedLatitude > 85 || reportedLatitude < -85) {
            throw new HomeAutomatePropertyValidationException(
                    "A BarometricPayloadDTO.ReportedLatitude property value is outside of the expected range.");
        }

        if(reportedLongitude == null){
            throw new HomeAutomatePropertyValidationException("The BarometricPayloadDTO.ReportedLongitude property value cannot be null or empty.");
        }

        if (reportedLongitude > 180 || reportedLongitude < -180) {
            throw new HomeAutomatePropertyValidationException(
                    "A BarometricPayloadDTO.ReportedLongitude property value is outside of the expected range.");
        }
    }
}
