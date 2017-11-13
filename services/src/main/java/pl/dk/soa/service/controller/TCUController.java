package pl.dk.soa.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/tcu", produces = APPLICATION_JSON_VALUE)
@Api(description = "transmission control unit controller")
class TCUController {

    @GetMapping(path = "/current-gear")
    @ApiOperation(value = "current-gear")
    ResponseEntity<String> currentGear() {
        String gear =  "{\"current-gear\": \"4\"}";
        return new ResponseEntity<>(gear, OK);
    }

    @PostMapping(path = "/current-reads")
    @ApiOperation(value = "current-reads")
    ResponseEntity<String> currentReads(@RequestBody CurrentReads currentReads) {
        return new ResponseEntity<>(ACCEPTED);
    }

    @Data
    static class CurrentReads {

        String vehicleSpeed;

        String rpm;

        String throttleLevel;

        String transmissionFluidTemperature;
    }

}
