package stepDefinitions;

import PO_Files.AgentPaymentGatewayPO;
import PO_Files.BookingStepsPO;
import PO_Files.LoginPO;
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

public class agentSteps {
    private static final WebDriver driver = DriverUtils.getDriver("firefox");
    private static final Logger logger = LoggerUtils.getLogger(agentSteps.class);
    private LoginPO loginPO = new LoginPO(driver);
    private BookingStepsPO bookingStepsPO = new BookingStepsPO(driver);
    private AgentPaymentGatewayPO agentPaymentGatewayPO = new AgentPaymentGatewayPO(driver);


    @Given("I open the agent-stage yolobus")
    public void i_open_the_agent_stage_yolobus() throws InterruptedException {
        ElementUtils.openAgentStage(driver);
        logger.info("Opened the agent-stage yolobus platform.");
        Thread.sleep(3000);


    }
    @Given("I log in with the phone number {string} and OTP {string}")
    public void i_log_in_with_the_phone_number_and_otp(String string, String string2) throws InterruptedException {
        loginPO.enterPhoneNumber(string);
        Thread.sleep(2000);
        loginPO.enterOTP(string2);
        Thread.sleep(2000);

    }
    @When("I select source city {string} with code {string}")
    public void i_select_source_city_with_code(String string, String string2) throws InterruptedException {
         bookingStepsPO.selectSourceCity(string,string2);
        Thread.sleep(2000);
    }
    @When("I select destination city {string} with code {string}")
    public void i_select_destination_city_with_code(String string, String string2) throws InterruptedException {
          bookingStepsPO.selectDestinationCity(string,string2);
          Thread.sleep(2000);
    }
    @When("I choose the travel date {string}")
    public void i_choose_the_travel_date(String string) throws InterruptedException {
           bookingStepsPO.chooseTravelDate(string);
           Thread.sleep(2000);
    }
    @When("I select {int} service")
    public void i_select_a_service(int i) throws InterruptedException {
           bookingStepsPO.chooseService(i);
           Thread.sleep(2000);
    }
    @When("I select a seat")
    public void i_select_a_seat() throws InterruptedException {
           bookingStepsPO.chooseSeat();
           Thread.sleep(2000);
    }
    @When("I confirm the seat selection")
    public void i_confirm_the_seat_selection() throws InterruptedException {
            bookingStepsPO.confirmSeatSelection();
            Thread.sleep(2000);
    }
    @Then("I add passenger details name {string}, age {string}, phonenumber {string} and email {string}")
    public void i_add_passenger_details_name_age_phonenumber_and_email(String string, String string2, String string3, String string4) throws InterruptedException {
             bookingStepsPO.addPassengerDetails(string,string2,string3,string4);
             Thread.sleep(2000);
    }
    @Then("I verify the total fare and seat tax")
    public void i_verify_the_total_fare_and_seat_tax() throws InterruptedException {
             bookingStepsPO.verifyTotalFareAndSeatTax();
             Thread.sleep(2000);
    }
    @Then("I confirm the booking")
    public void i_confirm_the_booking() throws InterruptedException {
              bookingStepsPO.confirmBooking();
              Thread.sleep(2000);
    }
    @Then("I verify the amount being paid")
    public void i_verify_the_amount_being_paid() throws InterruptedException {
               bookingStepsPO.verifyAmountBeingPaid();
               Thread.sleep(3000);
    }
    @Then("I proceed to the payment gateway")
    public void i_proceed_to_the_payment_gateway() throws InterruptedException {
               agentPaymentGatewayPO.proceedToPaymentGateway();
               Thread.sleep(3000);
    }
    @Then("I capture the ticket details")
    public void i_capture_the_ticket_details() throws InterruptedException, IOException, ParseException {
             Thread.sleep(10000);
             ElementUtils.takeScreenshot(driver);
             Thread.sleep(2000);
             bookingStepsPO.fetchTicketDetails();
             Thread.sleep(1000);
    }


}
