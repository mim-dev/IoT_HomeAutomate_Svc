package com.mimdevelopment.iot.challenge.homeautomate.service;

import com.mimdevelopment.iot.challenge.homeautomate.dto.BarometricPayloadDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.GatewayDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.LocationDTO;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.Gateway;
import com.mimdevelopment.iot.challenge.homeautomate.repository.GatewayRepository;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;

import java.util.Date;
import java.util.List;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:36 AM
 */
public class RepositoryGatewayService {

    public Gateway register(String gatewayIdentifier, LocationDTO reportedLocation)
            throws HomeAutomateException, HomeAutomatePropertyValidationException {

        GatewayRepository gatewayRepository = new GatewayRepository();
        Gateway result = gatewayRepository.fetchByIdentifier(gatewayIdentifier);

        reportedLocation.validate();

        if (result == null) {
            GatewayDTO gatewayDTO = new GatewayDTO();

            gatewayDTO.setIdentifier(gatewayIdentifier);
            gatewayDTO.setCurrentLocation(reportedLocation);
            gatewayDTO.setFirstSeen(new Date());
            gatewayDTO.setLastSeen(gatewayDTO.getFirstSeen());
            gatewayDTO.setActive(true);

            result = gatewayRepository.save(gatewayDTO);
        } else {
            // update the last seen and current location elements
            GatewayDTO gatewayDTO = new GatewayDTO(result);
            gatewayDTO.setLastSeen(new Date());
            gatewayDTO.setCurrentLocation(reportedLocation);
            result = gatewayRepository.save(gatewayDTO);
        }

        return result;
    }

    public void processBarometricPayloadSubmission
            (List<BarometricPayloadDTO> barometricPayloadTemplates)
            throws HomeAutomateException, HomeAutomatePropertyValidationException {

        if (barometricPayloadTemplates == null) {
            throw new IllegalArgumentException("The barometricPayloadTemplate argument is required.");
        }

        new GatewayRepository().processBarometricPayloadSubmission(barometricPayloadTemplates);
    }
}
