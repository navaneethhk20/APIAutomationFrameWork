package org.ATB9XAPI.modules;

import com.google.gson.Gson;
import org.ATB9XAPI.pojos.Booking;
import org.ATB9XAPI.pojos.BookingResponse;
import org.ATB9XAPI.pojos.Bookingdates;

public class PayLoadManager {

    //Converting java object to string
    Gson gson;

    public String createPayLoadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        gson = new Gson();
        String jsonStringPayLoad = gson.toJson(booking);
        System.out.println(jsonStringPayLoad);
        return jsonStringPayLoad;

    }

    //Converting String to java object
    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public Booking getResponseFromJSON(String getResponse) {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;

    }
}
