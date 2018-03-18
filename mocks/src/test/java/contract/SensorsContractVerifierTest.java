package contract;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.http.HttpStatus.OK;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

public class SensorsContractVerifierTest {

	static final String HOST = MOCK_HOST;

    @Test
    public void validateSensorsWss() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/wss");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("rpm", allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(2000)));
    }

    @Test
    public void validateSensorsMap() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/map");

        // then
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("pressure", allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(120)));
    }

    @Test
    public void validateSensorsCts() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/cts");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("temperature", allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(100)));
    }

    @Test
    public void validateSensorsApp() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/app");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("pedalPosition",allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(100)));
    }

}
