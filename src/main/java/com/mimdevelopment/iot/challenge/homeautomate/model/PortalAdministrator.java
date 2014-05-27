package com.mimdevelopment.iot.challenge.homeautomate.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/19/14
 * Time: 12:25 PM
 */

@Entity
public class PortalAdministrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portalAdministratorId")
    private Long identity;

    @Basic(optional = true)
    private String firstName;

    @Basic(optional = true)
    private String lastName;

    @Basic(optional = false)
    private String userName;

    @Basic(optional = false)
    private String password;

    @Basic(optional = false)
    private Date firstSeen;

    @Basic(optional = false)
    private Date lastSeen;

    @Basic(optional = false)
    private Boolean active;

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Date firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
