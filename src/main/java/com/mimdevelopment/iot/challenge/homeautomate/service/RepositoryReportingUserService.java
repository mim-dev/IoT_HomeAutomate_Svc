package com.mimdevelopment.iot.challenge.homeautomate.service;

import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserMoodRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserPhysicalRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.ReportingUser;
import com.mimdevelopment.iot.challenge.homeautomate.repository.ReportingUserRepository;

import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/27/14
 * Time: 10:07 AM
 */

public class RepositoryReportingUserService {

    public void processMoodRatingSubmission(
            ReportingUserMoodRatingDTO moodRatingTemplate)
            throws HomeAutomateException, HomeAutomatePropertyValidationException {

        if (moodRatingTemplate == null) {
            throw new IllegalArgumentException("moodRatingTemplate is required.");
        }

        moodRatingTemplate.validate();

        ReportingUserRepository reportingUserRepository = new ReportingUserRepository();
        ReportingUser reportingUser = reportingUserRepository.fetchByUserName(moodRatingTemplate.getReportingUser().getUserName());

        if (!authenticate(moodRatingTemplate.getReportingUser(), reportingUser)) {
            throw new HomeAutomateException(ExceptionReasonCode.BAD_CREDENTIALS);
        }

        moodRatingTemplate.getReportingUser().setIdentity(reportingUser.getIdentity());

        reportingUserRepository.addMoodRating(moodRatingTemplate);
    }

    public void processPhysicalRatingSubmission
            (ReportingUserPhysicalRatingDTO physicalRatingTemplate)
            throws HomeAutomateException, HomeAutomatePropertyValidationException {

        if (physicalRatingTemplate == null) {
            throw new IllegalArgumentException("physicalRatingTemplate is required.");
        }

        physicalRatingTemplate.validate();

        ReportingUserRepository reportingUserRepository = new ReportingUserRepository();
        ReportingUser reportingUser = reportingUserRepository.fetchByUserName(physicalRatingTemplate.getReportingUser().getUserName());

        if (!authenticate(physicalRatingTemplate.getReportingUser(), reportingUser)) {
            throw new HomeAutomateException(ExceptionReasonCode.BAD_CREDENTIALS);
        }

        physicalRatingTemplate.getReportingUser().setIdentity(reportingUser.getIdentity());
        reportingUserRepository.addPhysicalRating(physicalRatingTemplate);
    }

    private Boolean authenticate(ReportingUserDTO reportingUserTemplate, ReportingUser reportingUser) {

        String password;

        if (reportingUserTemplate == null) {
            throw new IllegalArgumentException("The reportingUserTemplate should not be null");
        } else {
            password = reportingUserTemplate.getPassword();
            if (password == null || password.length() == 0) {
                throw new IllegalArgumentException("The reportingUserTemplate's password should not be null or empty");
            }
        }

        return password.equals(reportingUser.getPassword());
    }

}
