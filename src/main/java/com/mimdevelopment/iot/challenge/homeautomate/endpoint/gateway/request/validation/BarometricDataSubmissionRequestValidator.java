package com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.validation;

import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.BarometricDataSubmission;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.BarometricDataSubmissionRequest;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;

/**
 * User: luther stanton
 * Date: 5/5/14
 * Time: 6:20 AM
 */

public class BarometricDataSubmissionRequestValidator {

    public void validate(BarometricDataSubmissionRequest target) throws HomeAutomatePropertyValidationException {

        if (target == null) {
            throw new HomeAutomatePropertyValidationException(
                    "The BarometricDataSubmissionRequest instance cannot be null.");
        }

        if (target.data == null || target.data.size() == 0) {
            throw new HomeAutomatePropertyValidationException(
                    "The BarometricDataSubmissionRequest.Data instance cannot be null or empty.");
        }

        for (BarometricDataSubmission item : target.data) {

            if (item.latitude == null) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a null latitude value.");
            }

            if (item.latitude > 85 || item.latitude < -85) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a latitude value outside of the expected range.");
            }

            if (item.longitude == null) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a null longitude value.");
            }

            if (item.longitude > 180 || item.longitude < -180) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a longitude value outside of the expected range.");
            }

            if (item.pressure == null) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a null pressure value.");
            }

            if (item.submissionDate == null) {
                throw new HomeAutomatePropertyValidationException(
                        "A BarometricDataSubmissionRequest.Data item has a null submissionDate value.");
            }

        }


    }
}
