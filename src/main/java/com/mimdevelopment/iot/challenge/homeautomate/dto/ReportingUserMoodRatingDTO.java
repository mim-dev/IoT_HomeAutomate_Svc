package com.mimdevelopment.iot.challenge.homeautomate.dto;


import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/27/14
 * Time: 10:50 AM
 */

public class ReportingUserMoodRatingDTO extends ReportingUserRatingDTO {

    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReportingUserMoodRatingDTO(Integer rating) {
        this.rating = rating;
    }

    public ReportingUserMoodRatingDTO(
            Long identity,
            ReportingUserDTO reportingUser,
            Date date,
            Integer rating,
            Double reportedLatitude,
            Double reportedLongitude) {
        super(identity, reportingUser, date, reportedLatitude, reportedLongitude);
        this.rating = rating;
    }

    public ReportingUserMoodRatingDTO(
            ReportingUserDTO reportingUser,
            Date date,
            Integer rating,
            Double reportedLatitude,
            Double reportedLongitude) {
        super(reportingUser, date, reportedLatitude, reportedLongitude);
        this.rating = rating;
    }

    public void validate() throws HomeAutomatePropertyValidationException {
        super.validate();
        if (rating == null) {
            throw new HomeAutomatePropertyValidationException("The ReportingUserMoodRatingDTO.Rating cannot be null or empty.");
        }
    }
}
