package org.codejudge.sb.service;

import org.codejudge.sb.entities.AvailableDriver;
import org.codejudge.sb.entities.Driver;
import org.codejudge.sb.repositories.DriverRepository;
import org.codejudge.sb.request.Location;
import org.codejudge.sb.response.AvailableCabsResponse;
import org.codejudge.sb.response.ErrorResponse;
import org.codejudge.sb.response.NoCabsResponse;
import org.codejudge.sb.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private DriverRepository driverRepository;

    public Response getAvailableCabs(Location location) {
        List<AvailableDriver> availableDrivers = new ArrayList<>();
        if (location.getLongitude() != null && location.getLatitude() != null) {
            List<Driver> driverList = driverRepository.findAll();
            driverList.parallelStream().forEach( x-> {
                double dLat = Math.toRadians(x.getLatitude().subtract(location.getLatitude()).doubleValue());
                double dLon = Math.toRadians(x.getLongitude().subtract(location.getLongitude()).doubleValue());

                // convert to radians
                double lat1 = Math.toRadians(location.getLatitude().doubleValue());
                double lat2 = Math.toRadians(x.getLatitude().doubleValue());

                // apply formulae
                double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);

                double rad = 6371;
                double c = 2 * Math.asin(Math.sqrt(a));
                if (rad * c <= 4) {
                    availableDrivers.add(new AvailableDriver(x.getName(), x.getCarNumber(), new BigDecimal(x.getPhoneNumber())));
                }
            });
            if (availableDrivers.size() > 0)
                return new AvailableCabsResponse(availableDrivers);
            return new NoCabsResponse("No cabs available!");
        }
        return new ErrorResponse("failure", "Passenger Location is not valid.");
    }
}
