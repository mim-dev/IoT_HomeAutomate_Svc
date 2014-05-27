package com.mimdevelopment.iot.challenge.homeautomate.dto;

import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/29/14
 * Time: 3:46 PM
 */

public class ReportingUserDTO {

    private Long identity;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Date firstSeen;

    private Date lastSeen;

    private Boolean active = false;

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

    public ReportingUserDTO() {
    }

    public ReportingUserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void validate() throws HomeAutomatePropertyValidationException {

        if(userName == null || userName.length() == 0){
            throw new HomeAutomatePropertyValidationException("The ReportingUserDTO.UserName property value cannot be null or empty.");
        } else if(password == null || password.length() == 0){
            throw new HomeAutomatePropertyValidationException("The ReportingUserDTO.Password property value cannot be null or empty.");
        }

    }
}
