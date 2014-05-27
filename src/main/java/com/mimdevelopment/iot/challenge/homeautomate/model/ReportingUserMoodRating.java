package com.mimdevelopment.iot.challenge.homeautomate.model;

import javax.persistence.*;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 3:59 PM
 */

@Entity
@AttributeOverride(name="identity", column=@Column(name="reportingUserMoodRatingId"))
public class ReportingUserMoodRating extends ReportingUserRating {

    @Basic(optional = false)
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}