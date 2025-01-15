package PO_Files;

import Utils.DriverUtils;
import Utils.ElementUtils;
import Utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class PaymentGatewayPO {
    private static WebDriver driver = DriverUtils.getDriver("firefox");
    private static final Logger logger = LoggerUtils.getLogger(PaymentGatewayPO.class);
   //Locators
    private By confirmBooking = By.xpath("/html/body/div[12]/div/div/div/div[2]/button");
    private By ProceedToPay = By.xpath("//button[@class=\"secondryButton \"]");
    private By razorpayFrame = By.xpath("//iframe[@class=\"razorpay-checkout-frame\"]");
    private By netbanking = By.xpath("(//div[@data-value=\"netbanking\"])[1]");
    private By ICICIbank = By.xpath("(//div[@role='button'])[2]");
    private By successButton = By.xpath("//button[@class='success']");

    // Constructor
    public PaymentGatewayPO(WebDriver driver) {
        this.driver = driver;
    }

    public void proceed_to_PaymentGateway(WebDriver driver) throws InterruptedException {
        try {
            ElementUtils.click(driver, confirmBooking);
            Thread.sleep(3000);
        } catch (NoSuchElementException|TimeoutException e) {
            logger.warn("Confirm Booking button not found. Proceeding to alternative payment flow.", e);

            // If Confirm Booking not found, fall back to Proceed to Pay
            proceedToPay();
        }
    }
    private void proceedToPay() {
        try {
            // If 'confirmBooking' is not available, proceed with 'ProceedToPay'
            ElementUtils.click(driver,ProceedToPay);
            Thread.sleep(5000);

            //paymentgateway
            ElementUtils.switch_to_frame(driver,razorpayFrame);
            Thread.sleep(5000);

            selectPaymentOptions();
            Thread.sleep(1000);


            ElementUtils.switchToWindow(driver,1);
            Thread.sleep(3000);
            ElementUtils.actionClick(driver,successButton);
            Thread.sleep(2000);
        }catch (Exception ex){
            logger.error("Error during payment process.", ex);
            throw new RuntimeException("Payment process failed", ex);
        }

    }
    // Select Payment Options
    private void selectPaymentOptions() throws InterruptedException {
        try {
            ElementUtils.actionClick(driver, netbanking);
            Thread.sleep(5000);

            ElementUtils.actionClick(driver, ICICIbank);
            Thread.sleep(2000);

        } catch (TimeoutException e) {
            logger.error("Error selecting payment options.", e);
            throw e;
        }
    }

}
