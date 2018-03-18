package contract;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

public class EcuContractVerifierTest {

	static final String HOST = MOCK_HOST;

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
}
