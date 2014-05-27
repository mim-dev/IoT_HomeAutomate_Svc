package com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 5/5/14
 * Time: 6:23 AM
 */

public class BarometricDataSubmission {

    public Double latitude;
    public Double longitude;

    public Float temperature;
    public Float pressure;

    public Date submissionDate;

    public Long sensorManufacturer;
    public Long sensorIdentifier;
}
