package contract;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

public class ContractVerifierTest {

	static final String HOST = "http://localhost:8181";

	// --------------------- ECU

	@Test
	public void validateEcuThrottle() {
		// when:
		Response response = when()
				.get(HOST + "/ecu/throttle");

		// then:
		response.then()
				.statusCode(OK.value())
				.contentType(JSON)
				.body("level", is(50));
	}

    @Test
    public void validateEcuEngineParams() {
        // given:
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("map", 90)
                        .put("cts", 60)
                        .toString()
                );

        // when:
        Response response = request.when()
                .post(HOST +"/ecu/engine-params");

        // then:
        response.then()
                .statusCode(ACCEPTED.value())
                .contentType(JSON);
    }

	@Test
	public void validateEcuAccPedalPosition() {
		// given:
			RequestSpecification request = given()
                    .contentType(JSON)
                    .body(new JSONObject()
                            .put("pedalPosition",30)
                            .toString()
                    );
		// when:
			Response response = request.when()
					.post(HOST + "/ecu/acc-pedal-position");

		// then:
			response.then()
					.statusCode(ACCEPTED.value())
					.contentType(JSON);
	}

    // --------------------- TCU

    @Test
    public void validateTcuCurrentGear() {
        // when:
        Response response = when()
                .get(HOST + "/tcu/current-gear");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("currentGear", is(4));
    }

    @Test
    public void validateTcuCurrentReads() {
        // given:
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("vehicleSpeed", 160)
                        .put("rpm", 1400)
                        .put("throttleLevel", 70)
                        .toString());

        // when:
        Response response = request.when()
                .post(HOST + "/tcu/current-reads");

        // then:
        response.then()
                .statusCode(ACCEPTED.value())
                .contentType(JSON);
    }

    // --------------------- Sensors

    @Test
    public void validateSensorsVss() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/vss");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("speed", is(120));
    }

    @Test
    public void validateSensorsWss() {
        // when:
        Response response = when()
                .get(HOST + "/sensors/wss");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("rpm", is(1200));
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
                .body("pressure", is(90));
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
                     .body("temperature", is(60));
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
                .body("pedalPosition", is(70));
    }

}
