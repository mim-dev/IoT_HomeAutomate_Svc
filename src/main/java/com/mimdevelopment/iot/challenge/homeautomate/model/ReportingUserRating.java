package com.mimdevelopment.iot.challenge.homeautomate.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 4:09 PM
 */

@MappedSuperclass
public abstract class ReportingUserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identity;

    @Basic
    private Date date;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reportingUserId")
    private ReportingUser reportingUser;

    @Basic(optional = false)
    private Double reportedLatitude;

    @Basic(optional = false)
    private Double reportedLongitude;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "barometricPayloadId")
    private BarometricPayload bestMatchBarometricPayload;

    @Basic(optional = true)
    private Integer matchConfidence;

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

    public ReportingUser getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(ReportingUser reportingUser) {
        this.reportingUser = reportingUser;
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

    public BarometricPayload getBestMatchBarometricPayload() {
        return bestMatchBarometricPayload;
    }

    public void setBestMatchBarometricPayload(BarometricPayload bestMatchBarometricPayload) {
        this.bestMatchBarometricPayload = bestMatchBarometricPayload;
    }

    public Integer getMatchConfidence() {
        return matchConfidence;
    }

    public void setMatchConfidence(Integer matchConfidence) {
        this.matchConfidence = matchConfidence;
    }
}
