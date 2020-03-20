package org.codejudge.sb.controller;

import org.codejudge.sb.request.Location;
import org.codejudge.sb.response.AvailableCabsResponse;
import org.codejudge.sb.response.ErrorResponse;
import org.codejudge.sb.response.Response;
import org.codejudge.sb.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping(value = "/available_cabs")
    public ResponseEntity<Response> getAvaiableCabs(@RequestBody Location location) {
        try {
            Response response = passengerService.getAvailableCabs(location);
            if (response instanceof ErrorResponse)
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("failure", "Interval Exception");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
