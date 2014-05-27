package com.mimdevelopment.iot.challenge.homeautomate.endpoint.reportinguser;

import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserMoodRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserPhysicalRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.reportinguser.request.ReportingUser;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.reportinguser.request.ReportingUserRatingSubmissionRequest;
import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.service.RepositoryReportingUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/20/14
 * Time: 4:40 PM
 */


@Path("/reportinguser")
public class ReportingUserEndpoint {

    @POST
    @Path("/mood")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitMoodRating(ReportingUserRatingSubmissionRequest moodRatingSubmissionRequest) {

        // validate the inputs
        if (moodRatingSubmissionRequest == null) {
            return Response.status(400).entity("Payload missing.").build();
        }

        ReportingUser reportingUserRequest = moodRatingSubmissionRequest.reportingUser;
        if (reportingUserRequest == null) {
            return Response.status(400).entity("ReportingUser parameter missing from payload.").build();
        }

        String reportingUserUserName = reportingUserRequest.username;
        if (reportingUserUserName == null || reportingUserUserName.length() == 0) {
            return Response.status(400).entity("ReportingUser.UserName parameter missing from payload.").build();
        }

        String reportingUserPassword = reportingUserRequest.password;
        if (reportingUserPassword == null || reportingUserPassword.length() == 0) {
            return Response.status(400).entity("ReportingUser.Password parameter missing from payload.").build();
        }

        Date submissionDate = moodRatingSubmissionRequest.date;
        if (submissionDate == null) {
            return Response.status(400).entity("Date parameter missing from payload.").build();
        }

        Integer rating = moodRatingSubmissionRequest.rating;
        if (rating == null) {
            return Response.status(400).entity("Rating parameter missing from payload.").build();
        }

        Double reportedLatitude = moodRatingSubmissionRequest.latitude;
        if (reportedLatitude == null) {
            return Response.status(400).entity("Latitude parameter missing from payload.").build();
        } else if (reportedLatitude < -85 || reportedLatitude > 85) {
            return Response.status(400).entity("Latitude parameter value outside of acceptable range -85 <= latitude <= 85.").build();
        }

        Double reportedLongitude = moodRatingSubmissionRequest.longitude;
        if (reportedLongitude == null) {
            return Response.status(400).entity("Longitude parameter missing from payload.").build();
        } else if (reportedLongitude < -180 || reportedLongitude > 180) {
            return Response.status(400).entity(
                    "Longitude parameter value outside of acceptable range -180 <= longitude <= 180.").build();
        }

        try {
            RepositoryReportingUserService reportingUserService = new RepositoryReportingUserService();
            reportingUserService.processMoodRatingSubmission(
                    new ReportingUserMoodRatingDTO(
                            new ReportingUserDTO(reportingUserUserName, reportingUserPassword),
                            submissionDate,
                            rating,
                            reportedLatitude,
                            reportedLongitude));

            return Response.status(204).build();

        } catch (IllegalArgumentException e) {
            return Response.status(400).entity("Unknown payload content error / missing parameter.  Server says '"
                    + e.getMessage() + "'").build();
        } catch (HomeAutomateException ex) {
            if (ex.getReasonCode() == ExceptionReasonCode.BAD_CREDENTIALS
                    || ex.getReasonCode() == ExceptionReasonCode.UNKNOWN_GATEWAY_USER) {
                return Response.status(401).entity("Unauthorized").build();
            } else {
                return Response.status(500).entity(ex.getMessage()).build();
            }
        } catch (HomeAutomatePropertyValidationException ex) {
            return Response.status(400).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/physical")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitPhysicalRating(ReportingUserRatingSubmissionRequest physicalRatingSubmissionRequest) {

        // validate the inputs
        if (physicalRatingSubmissionRequest == null) {
            return Response.status(400).entity("Payload missing.").build();
        }

        ReportingUser reportingUserRequest = physicalRatingSubmissionRequest.reportingUser;
        if (reportingUserRequest == null) {
            return Response.status(400).entity("ReportingUser parameter missing from payload.").build();
        }

        String reportingUserUserName = reportingUserRequest.username;
        if (reportingUserUserName == null || reportingUserUserName.length() == 0) {
            return Response.status(400).entity("ReportingUser.UserName parameter missing from payload.").build();
        }

        String reportingUserPassword = reportingUserRequest.password;
        if (reportingUserPassword == null || reportingUserPassword.length() == 0) {
            return Response.status(400).entity("ReportingUser.Password parameter missing from payload.").build();
        }

        Date submissionDate = physicalRatingSubmissionRequest.date;
        if (submissionDate == null) {
            return Response.status(400).entity("Date parameter missing from payload.").build();
        }

        Integer rating = physicalRatingSubmissionRequest.rating;
        if (rating == null) {
            return Response.status(400).entity("Rating parameter missing from payload.").build();
        }

        Double reportedLatitude = physicalRatingSubmissionRequest.latitude;
        if (reportedLatitude == null) {
            return Response.status(400).entity("Latitude parameter missing from payload.").build();
        } else if (reportedLatitude < -85 || reportedLatitude > 85) {
            return Response.status(400).entity("Latitude parameter value outside of acceptable range -85 <= latitude <= 85.").build();
        }

        Double reportedLongitude = physicalRatingSubmissionRequest.longitude;
        if (reportedLongitude == null) {
            return Response.status(400).entity("Longitude parameter missing from payload.").build();
        } else if (reportedLongitude < -180 || reportedLongitude > 180) {
            return Response.status(400).entity("Longitude parameter value outside of acceptable range -180 <= longitude <= 180.").build();
        }

        try {
            RepositoryReportingUserService reportingUserService = new RepositoryReportingUserService();
            reportingUserService.processPhysicalRatingSubmission(
                    new ReportingUserPhysicalRatingDTO(
                            new ReportingUserDTO(reportingUserUserName, reportingUserPassword),
                            submissionDate,
                            rating,
                            reportedLatitude,
                            reportedLongitude));

            return Response.status(204).build();

        } catch (IllegalArgumentException e) {
            return Response.status(400).entity("Unknown payload content error / missing parameter.  Server says '"
                    + e.getMessage() + "'").build();
        } catch (HomeAutomateException ex) {
            if (ex.getReasonCode() == ExceptionReasonCode.BAD_CREDENTIALS || ex.getReasonCode() == ExceptionReasonCode.UNKNOWN_GATEWAY_USER) {
                return Response.status(401).entity("Unauthorized").build();
            } else {
                return Response.status(500).entity(ex.getMessage()).build();
            }
        } catch (HomeAutomatePropertyValidationException ex) {
            return Response.status(400).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }
}
