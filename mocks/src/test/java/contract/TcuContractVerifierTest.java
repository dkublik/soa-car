package contract;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static pl.dk.soa.service.Hosts.MOCK_HOST;

public class TcuContractVerifierTest {

	static final String HOST = MOCK_HOST;

    @Test
    public void validateTcuCurrentGear() {
        // when:
        Response response = when()
                .get(HOST + "/tcu/current-gear");

        // then:
        response.then()
                .statusCode(OK.value())
                .contentType(JSON)
                .body("currentGear", allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(6)));
    }

    // won't pass - implemented by pulling
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

}
