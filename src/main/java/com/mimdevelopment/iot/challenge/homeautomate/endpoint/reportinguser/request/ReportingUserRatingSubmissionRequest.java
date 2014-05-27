package com.mimdevelopment.iot.challenge.homeautomate.endpoint.reportinguser.request;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/20/14
 * Time: 4:41 PM
 */

public class ReportingUserRatingSubmissionRequest {
    public ReportingUser reportingUser;

    public Double latitude;
    public Double longitude;

    public Date date;
    public Integer rating;
}

