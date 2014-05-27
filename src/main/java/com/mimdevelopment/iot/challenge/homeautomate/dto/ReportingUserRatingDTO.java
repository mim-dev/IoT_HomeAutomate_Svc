package com.mimdevelopment.iot.challenge.homeautomate.dto;

import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/27/14
 * Time: 10:13 AM
 */

public abstract class ReportingUserRatingDTO {

    private Long identity;

    private Double reportedLatitude;
    private Double reportedLongitude;

    private Date date;

    private ReportingUserDTO reportingUser;

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public ReportingUserDTO getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(ReportingUserDTO reportingUser) {
        this.reportingUser = reportingUser;
    }

    public ReportingUserRatingDTO() {
    }

    public ReportingUserRatingDTO(Long identity, ReportingUserDTO reportingUser,Date date, Double reportedLatitude, Double reportedLongitude) {
        this.identity = identity;
        this.reportingUser = reportingUser;
        this.date = date;
        this.reportedLatitude = reportedLatitude;
        this.reportedLongitude = reportedLongitude;
    }

    public ReportingUserRatingDTO(
            ReportingUserDTO reportingUser, Date date, Double reportedLatitude, Double reportedLongitude) {
        identity = null;
        this.reportingUser = reportingUser;
        this.date = date;
        this.reportedLatitude = reportedLatitude;
        this.reportedLongitude = reportedLongitude;
    }

    protected void validate() throws HomeAutomatePropertyValidationException{

        if(reportingUser == null){
            throw new HomeAutomatePropertyValidationException("The ReportingUserRatingDTO.ReportingUser cannot be null or empty.");
        }  else{
            reportingUser.validate();
        }

        if(date == null){
            throw new HomeAutomatePropertyValidationException("The ReportingUserRatingDTO.Date cannot be null or empty.");
        }

        if(reportedLatitude == null){
            throw new HomeAutomatePropertyValidationException("The ReportingUserRatingDTO.ReportedLatitude cannot be null or empty.");
        }

        if (reportedLatitude > 85 || reportedLatitude < -85) {
            throw new HomeAutomatePropertyValidationException(
                    "THe ReportingUserRatingDTO.ReportedLatitude property value is outside of the expected range.");
        }

        if(reportedLongitude == null){
            throw new HomeAutomatePropertyValidationException("The ReportingUserRatingDTO.ReportedLongitude cannot be null or empty.");
        }

        if (reportedLongitude > 180 || reportedLongitude < -180) {
            throw new HomeAutomatePropertyValidationException(
                    "The ReportingUserRatingDTO.ReportedLongitude property value is outside of the expected range.");
        }


    }
}
