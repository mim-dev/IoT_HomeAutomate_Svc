package com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway;

import com.mimdevelopment.iot.challenge.homeautomate.dto.BarometricPayloadDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.GatewayDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.LocationDTO;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.BarometricDataSubmission;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.BarometricDataSubmissionRequest;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.RegistrationRequest;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.request.validation.BarometricDataSubmissionRequestValidator;
import com.mimdevelopment.iot.challenge.homeautomate.endpoint.gateway.response.RegistrationResponse;
import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.service.RepositoryGatewayService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:04 AM
 */

@Path("/gateway")
public class GatewayEndpoint {
    @PUT
    @Path("/register/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@PathParam("identifier") String gatewayIdentifier, RegistrationRequest registrationRequest) {

        RepositoryGatewayService gatewayService = new RepositoryGatewayService();
        Response serviceInvocationResponse;

        try {

            LocationDTO reportedLocation = new LocationDTO();
            reportedLocation.setLatitude(registrationRequest.latitude);
            reportedLocation.setLongitude(registrationRequest.longitude);

            RegistrationResponse registrationResponse
                    = new RegistrationResponse(gatewayService.register(gatewayIdentifier, reportedLocation));
            serviceInvocationResponse = Response.status(200).entity(registrationResponse).build();
        } catch (HomeAutomatePropertyValidationException ex) {
            serviceInvocationResponse = Response.status(400).entity(ex.getMessage()).build();
        } catch (Exception e) {
            serviceInvocationResponse = Response.status(500).build();
        }

        return serviceInvocationResponse;

    }

    @POST
    @Path("/{identity}/barometricData")
    public Response processBarometricData(
            @PathParam("identity") Long gatewayIdentity,
            BarometricDataSubmissionRequest barometricDataSubmissionRequest) {

        RepositoryGatewayService gatewayService = new RepositoryGatewayService();
        Response serviceInvocationResponse;

        try {

            BarometricDataSubmissionRequestValidator barometricDataSubmissionRequestValidator
                    = new BarometricDataSubmissionRequestValidator();

            barometricDataSubmissionRequestValidator.validate(barometricDataSubmissionRequest);

            List<BarometricPayloadDTO> barometricPayload
                    = new ArrayList<BarometricPayloadDTO>(barometricDataSubmissionRequest.data.size());

            for (BarometricDataSubmission barometricDataSubmission : barometricDataSubmissionRequest.data) {
                barometricPayload.add(
                        new BarometricPayloadDTO(
                                barometricDataSubmission.submissionDate,
                                barometricDataSubmission.temperature,
                                barometricDataSubmission.pressure,
                                barometricDataSubmission.latitude,
                                barometricDataSubmission.longitude,
                                barometricDataSubmission.sensorManufacturer,
                                barometricDataSubmission.sensorIdentifier,
                                new GatewayDTO(gatewayIdentity)));
            }
            gatewayService.processBarometricPayloadSubmission(barometricPayload);
            serviceInvocationResponse = Response.status(204).build();

        } catch (HomeAutomateException ex) {

            if (ex.getReasonCode() == ExceptionReasonCode.UNKNOWN_GATEWAY
                    || ex.getReasonCode() == ExceptionReasonCode.NON_UNIQUE_GATEWAY) {
                serviceInvocationResponse = Response.status(404).entity(ex.getMessage()).build();
            } else {
                serviceInvocationResponse = Response.status(500).entity(ex.getMessage()).build();
            }

        } catch (HomeAutomatePropertyValidationException ex) {
            serviceInvocationResponse = Response.status(400).entity(ex.getMessage()).build();
        } catch (Exception e) {
            serviceInvocationResponse = Response.status(500).build();
        }

        return serviceInvocationResponse;
    }
}
