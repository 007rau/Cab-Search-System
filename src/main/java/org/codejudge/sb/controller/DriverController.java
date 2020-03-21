package org.codejudge.sb.controller;

import org.codejudge.sb.request.DriverRequest;
import org.codejudge.sb.request.Location;
import org.codejudge.sb.response.DriverResponse;
import org.codejudge.sb.response.ErrorResponse;
import org.codejudge.sb.response.Response;
import org.codejudge.sb.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * @param driverRequest - input request to the server
     * @return Http response of the request
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Response> registerDriver(@RequestBody DriverRequest driverRequest) {
        try {
            Response response = driverService.createDriverAccount(driverRequest);
            if (response instanceof DriverResponse)
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("failure", "Interval Exception");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id - driver id
     * @param location - longitude and latitude
     * @return Http response of the request
     */
    @PostMapping(value = "/{id}/sendLocation")
    public ResponseEntity<Response> setLocation(@PathVariable int id, @RequestBody Location location) {
        try {
            Response response = driverService.setDriverLocation(location, id);
            if (response instanceof ErrorResponse)
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("failure", "Interval Exception");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
