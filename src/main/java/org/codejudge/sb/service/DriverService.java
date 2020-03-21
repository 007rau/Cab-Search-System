package org.codejudge.sb.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.codejudge.sb.entities.Driver;
import org.codejudge.sb.repositories.DriverRepository;
import org.codejudge.sb.request.DriverRequest;
import org.codejudge.sb.request.Location;
import org.codejudge.sb.response.DriverResponse;
import org.codejudge.sb.response.ErrorResponse;
import org.codejudge.sb.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    /**
     * @param driverRequest - driver details
     * @return response - driver response or error
     */
    public Response createDriverAccount(DriverRequest driverRequest) {
        if (driverRequest.getName() != null && driverRequest.getPhone_number() != null
                && driverRequest.getCar_number() != null && driverRequest.getEmail() != null
                && driverRequest.getLicense_number() != null && driverRequest.getPhone_number().length() == 10 ) {
            Driver byCarNumber = driverRepository.findByCarNumber(driverRequest.getCar_number());
            Driver byEmail = driverRepository.findByEmail(driverRequest.getEmail());
            Driver byLicenseNumber = driverRepository.findByLicenseNumber(driverRequest.getLicense_number());
            Driver byPhoneNumber = driverRepository.findByPhoneNumber(driverRequest.getPhone_number());

            if(byCarNumber == null && byEmail == null && byLicenseNumber == null && byPhoneNumber == null) {
                Driver driverBuilder = new Driver(driverRequest.getName(), driverRequest.getEmail(),driverRequest.getPhone_number(), driverRequest.getLicense_number(), driverRequest.getCar_number());
                Driver driver = driverRepository.save(driverBuilder);
                return new DriverResponse(driver.getId(), driver.getName(), driver.getEmail(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getCarNumber());
            }
            return new ErrorResponse("failure", "Driver Details are not unique.");
        }
        return new ErrorResponse("failure", "Driver Phone Number is not valid.");
    }

    /**
     * @param location - latitude and longitude
     * @param id - driver id
     * @return response - 200 or error
     */
    public Response setDriverLocation(Location location, int id) {
        Driver byId = driverRepository.findById(id);
        if (byId != null && location.getLatitude() != null && location.getLongitude() != null) {
            byId.setLongitude(location.getLongitude());
            byId.setLatitude(location.getLatitude());
            driverRepository.save(byId);
            return new Response();
        }
        return new ErrorResponse("failure", "Driver Id is not valid.");
    }

}
