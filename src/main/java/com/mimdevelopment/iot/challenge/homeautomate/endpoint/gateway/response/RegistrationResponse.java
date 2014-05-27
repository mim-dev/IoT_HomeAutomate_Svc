package com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.response;

import com.mimdevelopment.iot.challenge.homeautomate.model.Gateway;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:29 AM
 */

public class RegistrationResponse {

    public Long gatewayId;

    public RegistrationResponse() {
    }

    public RegistrationResponse(Gateway result) {
        this.gatewayId = result.getIdentity();
    }
}
