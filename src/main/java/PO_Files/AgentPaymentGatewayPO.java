package PO_Files;

import Utils.ElementUtils;
import Utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgentPaymentGatewayPO {
    private WebDriver driver;
    private static final Logger logger = LoggerUtils.getLogger(AgentPaymentGatewayPO.class);
    private payment_MethodsPO paymentMethodsPO;

    // Locators
    private By confirmBooking = By.xpath("/html/body/div[12]/div/div/div/div[2]/button");
    private By proceedToPay = By.xpath("//button[@class=\"secondryButton \"]");
    private By confirmButton = By.xpath("//button[@class=\"secondryButton proceedButton\"]");
    private By razorpayFrame = By.xpath("//iframe[@class='razorpay-checkout-frame']");
    private By successButton = By.xpath("//button[@class='success']");

    // Constructor
    public AgentPaymentGatewayPO(WebDriver driver) {
        this.driver = driver;
        this.paymentMethodsPO = new payment_MethodsPO(driver);
    }

    // Proceed to Payment Gateway
    public void proceedToPaymentGateway() {
        try {
            ElementUtils.actionClick(driver, confirmBooking);
        } catch (NoSuchElementException e) {
            logger.warn("Confirm Booking button not found. Proceeding to alternative payment flow.", e);
            proceedToPay();
        }
    }

    // Proceed to Pay Flow
    private void proceedToPay() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            Thread.sleep(3000);
            ElementUtils.actionClick(driver,proceedToPay);
            logger.info("Clicked on Proceed to Pay button.");

            Thread.sleep(3000);

            ElementUtils.actionClick(driver,confirmButton);

            /*WebElement ConfirmButton = driver.findElement(confirmButton);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", ConfirmButton);
            logger.info("Clicked on Confirm button.");*/



            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(razorpayFrame));
            logger.info("Switched to Razorpay iframe.");

            selectPaymentOptions("ICICI");

            wait.until(ExpectedConditions.elementToBeClickable(successButton)).click();
            logger.info("Payment completed successfully.");
        } catch (Exception ex) {
            logger.error("Error during payment process.", ex);
            throw new RuntimeException("Payment process failed", ex);
        }
    }

    // Select Payment Options
    private void selectPaymentOptions(String bankName) {
        try {
            paymentMethodsPO.Netbanking_Method(driver, bankName);
        } catch (TimeoutException e) {
            logger.error("Error selecting payment options.", e);
            throw e;
        }
    }

    public void validate_the_booking_confirmation() {

    }

}
