package contract;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

public class ContractVerifierTest {

	static final String HOST = "http://localhost:8181";

	@Test
	public void validateEcuAccPedalPosition() throws Exception {
		// given:
			RequestSpecification request = given()
                    .contentType(JSON)
                    .body(new JSONObject()
                            .put("app",30)
                            .toString());
		// when:
			Response response = request.when()
					.post(HOST + "/ecu/acc-pedal-position");

		// then:
			assertThat(response.statusCode()).isEqualTo(202);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
	}

	@Test
	public void validateEcuEngineParams() throws Exception {
		// given:
            RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("map",90)
                        .put("cts", 60)
                        .toString());

		// when:
			Response response = request.when()
					.post(HOST +"/ecu/engine-params");

		// then:
			assertThat(response.statusCode()).isEqualTo(202);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
	}

	@Test
	public void validateEcuThrottle() {
		// when:
			Response response = when()
					.get(HOST + "/ecu/throttle");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['level']").isEqualTo("70%");
	}

	@Test
	public void validateSensorsApp() {
		// when:
			Response response = when()
					.get(HOST + "/sensors/app");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['pedal-position']").isEqualTo("40");
	}

	@Test
	public void validateSensorsCts() {
		// when:
			Response response = when()
					.get(HOST + "/sensors/cts");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['temperature']").isEqualTo("60");
	}

	@Test
	public void validateSensorsMap() {
		// when:
			Response response = when()
					.get(HOST + "/sensors/map");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['pressure']").isEqualTo("90");
	}

	@Test
	public void validateSensorsRpm() {
		// when:
			Response response = when()
					.get(HOST + "/sensors/rpm");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['rpm']").isEqualTo("1200");
	}

	@Test
	public void validateSensorsVss() {
		// when:
			Response response = when()
					.get(HOST + "/sensors/vss");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['speed']").isEqualTo("120");
	}

	@Test
	public void validateTcuCurrentGear() {
		// when:
			Response response = when()
					.get(HOST + "/tcu/current-gear");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['currentGear']").isEqualTo("4");
	}

	@Test
	public void validateTcuCurrentReads() throws Exception {
		// given:
            RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("vehicleSpeed",160)
                        .put("rpm",1400)
                        .put("throttleLevel",70)
                        .toString());

		// when:
			Response response = request.when()
					.post(HOST + "/tcu/current-reads");

		// then:
			assertThat(response.statusCode()).isEqualTo(202);
			assertThat(response.header("Content-Type")).isEqualTo("application/json");
	}

}
