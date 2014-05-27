package com.mimdevelopment.iot.challenge.homeautomate.model;


import javax.persistence.*;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 4:23 PM
 */

@Entity
@AttributeOverride(name="identity", column=@Column(name="reportingUserPhysicalRatingId"))
public class ReportingUserPhysicalRating extends ReportingUserRating {

    @Basic(optional = false)
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
