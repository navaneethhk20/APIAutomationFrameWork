package org.ATB9XAPI.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.ATB9XAPI.asserts.AssertActions;
import org.ATB9XAPI.endpoints.APIEndPoints;
import org.ATB9XAPI.modules.PayLoadManager;
import org.testng.annotations.BeforeTest;

public class BaseTests {
    public RequestSpecification requestSpecification;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public Response response;
    public PayLoadManager payLoadManager;

    @BeforeTest
    public void setup() {
        payLoadManager = new PayLoadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured
//                .given()
//                .baseUri(APIEndPoints.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIEndPoints.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

    }

    public String getToken() {
        requestSpecification = RestAssured
                .given()
                .baseUri(APIEndPoints.BASE_URL)
                .basePath(APIEndPoints.AUTH_URL);
        //setting the payload
        String payload = payLoadManager.setAuthPayLoad();

        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        // String Extraction
        String token = payLoadManager.getTokenFromJSON(response.asString());

        return token;

    }
    }

