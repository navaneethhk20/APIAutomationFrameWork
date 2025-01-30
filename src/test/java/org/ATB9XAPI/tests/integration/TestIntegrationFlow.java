package org.ATB9XAPI.tests.integration;


import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.ATB9XAPI.base.BaseTests;
import org.ATB9XAPI.endpoints.APIEndPoints;
import org.ATB9XAPI.pojos.Booking;
import org.ATB9XAPI.pojos.BookingResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class TestIntegrationFlow extends BaseTests {
    //  Test Integration Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups = "qa", priority = 1)
    @Owner("Navaneeth")
    @Description("TC-1: Verify Booking is created")
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIEndPoints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payLoadManager.createPayLoadBookingAsString()).post();


        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "James");
        System.out.println(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Navaneeth")
    @Description("TC-2: Verify Booking is created")
    public void testVerifyBooking(ITestContext iTestContext) {
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        // GET Request - to verify that the firstname after creation is James
        String basePathGET = APIEndPoints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);


        Booking booking = payLoadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("James");

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Navaneeth")
    @Description("TC-3: Verify Update Booking")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);

        String basePathPUTPATCH = APIEndPoints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payLoadManager.fullUpdatePayloadAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payLoadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Pramod");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Navaneeth")
    @Description("TC-4: Verify Delete booking")
    public void testDelete(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");


        String basePathDelete = APIEndPoints.CREATE_UPDATE_BOOKING_URL + "/" +bookingid;

        requestSpecification.basePath(basePathDelete).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }
}