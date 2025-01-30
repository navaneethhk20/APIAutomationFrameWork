package org.ATB9XAPI.tests.crud;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.ATB9XAPI.base.BaseTests;
import org.ATB9XAPI.endpoints.APIEndPoints;
import org.ATB9XAPI.pojos.BookingResponse;
import org.testng.annotations.Test;

public class testCreateBooking extends BaseTests {
    @Owner("Navaneeth")
    @TmsLink("https://google.com")
    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @Description("Verify that POST request is working fine.")
    @Test(groups = "qa")
    public void testVerifyCreateBookingTestPOST01(){
        requestSpecification.basePath(APIEndPoints.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payLoadManager.createPayLoadBookingAsString()).post();


        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);


        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"James");
    }
}
