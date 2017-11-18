package pl.dk.soa.service.device;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class Car {

    public static final int MAX_SPEED = 220;

    public static final int MAX_GEAR = 6;

    public static final int MAX_THROTTLE_LEVEL = 100;

    public static final double WHEEL_DIAMETER = 0.6;

    private int coolantTemperature;

    private int manifoldPressure;

    private int throttleLevel;

    private int accPedalPosition;

    private int rpm;

    private int speed;

    private int currentGear;

    public int getSpeed() {
        speed = throttleLevel / 100 * 220;
        return speed;
    }

}
