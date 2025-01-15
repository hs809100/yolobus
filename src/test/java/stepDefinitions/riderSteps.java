package stepDefinitions;

import PO_Files.BookingStepsPO;
import PO_Files.LoginPO;
import PO_Files.PaymentGatewayPO;
import Utils.DriverUtils;
import Utils.ElementUtils;
import Utils.LoggerUtils;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;

public class riderSteps {
    private static final WebDriver driver = DriverUtils.getDriver("firefox");
    private static final Logger logger = LoggerUtils.getLogger(riderSteps.class);
    private LoginPO loginPO = new LoginPO(driver);
    private BookingStepsPO bookingStepsPO = new BookingStepsPO(driver);
    private PaymentGatewayPO paymentGatewayPO = new PaymentGatewayPO(driver);

    @Given("I open the stage yolobus")
    public void i_open_the_stage_yolobus() {
        ElementUtils.openRiderStage(driver);
        logger.info("Opened the rider-stage yolobus platform.");
    }
    @Given("I select source city {string} with code {string} rider")
    public void i_select_source_city_with_code_rider(String string, String string2) throws InterruptedException {
         bookingStepsPO.selectSourceCity(string,string2);
         Thread.sleep(2000);
    }
    @Given("I select destination city {string} with code {string} rider")
    public void i_select_destination_city_with_code_rider(String string, String string2) throws InterruptedException {
         bookingStepsPO.selectDestinationCityRider(string,string2);
         Thread.sleep(2000);
    }
    @Given("I choose the travel date {string} rider")
    public void i_choose_the_travel_date_rider(String string) throws InterruptedException {
         bookingStepsPO.chooseTravelDateRider(string);
         Thread.sleep(2000);
    }
    @Given("I select {int} service rider")
    public void i_select_a_service_rider(int i) throws InterruptedException {
        bookingStepsPO.chooseService(i);
        Thread.sleep(2000);
    }
    @Given("I select a seat rider")
    public void i_select_a_seat_rider() throws InterruptedException {
        bookingStepsPO.chooseSeat();
        Thread.sleep(2000);
        bookingStepsPO.confirmSeatSelection();
        Thread.sleep(1000);
    }
    @When("I log in with the phone number {string} and OTP {string} on rider")
    public void i_log_in_with_the_phone_number_and_otp_on_rider(String string, String string2) throws InterruptedException {
        loginPO.enterPhoneNumber(string);
        Thread.sleep(2000);
        loginPO.enterOTP(string2);
        Thread.sleep(2000);
    }
    @Then("I add passenger details name {string}, age {string}, phonenumber {string} and email {string} rider")
    public void i_add_passenger_details_name_age_phonenumber_and_email_rider(String string, String string2, String string3, String string4) throws InterruptedException {
        bookingStepsPO.addPassengerDetails(string,string2,string3,string4);
        Thread.sleep(2000);
    }
    @Then("I verify the total fare and seat tax rider")
    public void i_verify_the_total_fare_and_seat_tax_rider() throws InterruptedException {
        bookingStepsPO.verifyTotalFareAndSeatTax();
        Thread.sleep(2000);
    }
    @Then("I confirm the booking rider")
    public void i_confirm_the_booking_rider() throws InterruptedException {
        bookingStepsPO.confirmBooking();
        Thread.sleep(2000);
    }
    @Then("I verify the amount being paid rider")
    public void i_verify_the_amount_being_paid_rider() throws InterruptedException {
        bookingStepsPO.verifyAmountBeingPaid();
        Thread.sleep(2000);
    }
    @Then("I proceed to the payment gateway rider")
    public void i_proceed_to_the_payment_gateway_rider() throws InterruptedException {
        paymentGatewayPO.proceed_to_PaymentGateway(driver);
        Thread.sleep(6000);
    }
    @Then("I capture the ticket details rider")
    public void i_capture_the_ticket_details_rider() throws InterruptedException, IOException, ParseException {
         ElementUtils.takeScreenshot(driver);
         Thread.sleep(2000);
         bookingStepsPO.fetchTicketDetails();
         Thread.sleep(2000);
    }
    // Close the driver after the tests
    @After
    public void tearDown() {
        driver.quit();
    }

}
