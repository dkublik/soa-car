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
@RequestMapping(path = "/dcu", produces = APPLICATION_JSON_VALUE)
@Api(description = "door control unit controller")
class DCUController {

    @GetMapping(path = "/belts")
    @ApiOperation(value = "belts")
    ResponseEntity<String> belts() {
        String belts = "{\"belts\": \"ON\"}";
        return new ResponseEntity<>(belts, OK);
    }

    @GetMapping(path = "/child-lock")
    @ApiOperation(value = "child-lock")
    ResponseEntity<String> childLock() {
        String childLock = "{\"childLock\": \"ON\"}";
        return new ResponseEntity<>(childLock, OK);
    }


    @PostMapping(path = "/child-lock")
    @ApiOperation(value = "child-lock")
    ResponseEntity<String> childLock(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostMapping(path = "/mirror-position")
    @ApiOperation(value = "mirror-position")
    ResponseEntity<String> mirrorPosition(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostMapping(path = "/window-position")
    @ApiOperation(value = "window-position")
    ResponseEntity<String> windowPosition(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(ACCEPTED);
    }

}
