package org.ATB9XAPI.tests.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {
    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Description("TC-1: Creating a booking")
    @Owner("Navaneeth")
    public void test1(){
        Assert.assertTrue(true);
    }
    @Test(groups = "qa", priority = 2)
    @Description("TC-2: Get the booking")
    @Owner("Navaneeth")
    public void test2(){
        Assert.assertTrue(true);
    }
    @Test(groups = "qa", priority = 3)
    @Description("TC-3: Update the booking")
    @Owner("Navaneeth")
    public void test3(){
        Assert.assertTrue(true);
    }
    @Test(groups = "qa", priority = 4)
    @Description("TC-4: Delete the booking")
    @Owner("Navaneeth")
    public void test4(){
        Assert.assertTrue(true);
    }


}
