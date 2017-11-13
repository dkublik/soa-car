package pl.dk.soa.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/ecu", produces = APPLICATION_JSON_VALUE)
@Api(description = "engine control unit controller")
class ECUController {

    @GetMapping(path = "/oxygen-level")
    @ApiOperation(value = "oxygen-level")
    ResponseEntity<String> oxygenLevel() {
        String level = "{\"level\": \"RUNNING_LEAN\"}";
        return new ResponseEntity<>(level, OK);
    }

    @GetMapping(path = "/rpm-idle")
    @ApiOperation(value = "oxygen-level")
    ResponseEntity<String> rpmIdle() {
        String level = "{\"rpm\": \"700\"}";
        return new ResponseEntity<>(level, OK);
    }

    @GetMapping(path = "/temperature")
    @ApiOperation(value = "temperature")
    ResponseEntity<String> temperature() {
        String level = "{\"temperature\": \"90C\"}";
        return new ResponseEntity<>(level, OK);
    }

    @GetMapping(path = "/throttle")
    @ApiOperation(value = "throttle")
    ResponseEntity<String> throttle() {
        String level = "{\"level\": \"70%\"}";
        return new ResponseEntity<>(level, OK);
    }

    @PostMapping(path = "/valve-timing")
    @ApiOperation(value = "valve-timing")
    ResponseEntity<String> valveTiming(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(ACCEPTED);
    }

}
