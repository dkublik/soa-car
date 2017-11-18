package pl.dk.soa.service.ecu;

import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.service.device.Car;
import pl.dk.soa.service.ecu.response.ThrottleResponse;
import pl.dk.soa.service.sensors.response.*;

import static java.lang.Math.*;
import static pl.dk.soa.service.device.Car.*;

@Service
@Getter
class EcuModule {

    static final String SENSORS_HOST = "http://localhost:8182";

    private final RestTemplate restTemplate;

    private final Car car;

    EcuModule(RestTemplate restTemplate, Car car) {
        this.restTemplate = restTemplate;
        this.car = car;
    }

    @Scheduled(fixedDelay=1000)
    public void adjustThrottle() {
        try {
            int pressure = restTemplate.getForObject(SENSORS_HOST + "/sensors/map", MapResponse.class).getPressure();
            int temperature = restTemplate.getForObject(SENSORS_HOST + "/sensors/cts", CtsResponse.class).getTemperature();
            int pedalPosition = restTemplate.getForObject(SENSORS_HOST + "/sensors/app", AppResponse.class).getPedalPosition();
            int throttleLevel = calculateThrottleLevel(pressure, temperature, pedalPosition);
            car.setThrottleLevel(throttleLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int getThrottleLevel() {
        return car.getThrottleLevel();
    }

    private int calculateThrottleLevel(int pressure, int temperature, int pedalPosition) {
        return 90;
    }

    private double expectedRpm(int speed) {
        double metersPerMinute = speed * 1000 / 60;
        double wheelCircumference = PI * WHEEL_DIAMETER;
        return metersPerMinute / wheelCircumference;
    }

    private boolean goingUp(int speed, int rpm) {
        return rpm - expectedRpm(speed) > 100;
    }

    private boolean goingDown(int speed, int rpm) {
        return expectedRpm(speed) - rpm > 100;
    }

}
