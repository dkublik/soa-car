package contract;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.OK;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

public class TCUContractVerifierTest {

	static final String HOST = MOCK_HOST;

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
