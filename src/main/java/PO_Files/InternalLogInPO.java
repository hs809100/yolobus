package PO_Files;

import Utils.DriverUtils;
import Utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InternalLogInPO {
    private static WebDriver driver = DriverUtils.getDriver("firefox");
    // Locators
    private By loginInternal = By.id("standard-adornment-mobile");
    private By getOTPinternal = By.xpath("//button[@type=\"submit\"]");
    private By otpField = By.xpath("//input[@id=\"standard-otp-xyz\"]");
    private By verify = By.xpath("//button[@type=\"submit\"]");


    public InternalLogInPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterPhoneNumber(String phonenumber) throws InterruptedException {

        ElementUtils.actionClick(driver, loginInternal);
        Thread.sleep(500);

        ElementUtils.sendKeys(driver, loginInternal, phonenumber);//9761325797
        Thread.sleep(2000);

        ElementUtils.actionClick(driver,getOTPinternal);
        Thread.sleep(2000);
    }
    public void enterOTP(String otp) throws InterruptedException {
        ElementUtils.sendKeys(driver, otpField, otp);
        Thread.sleep(500);

        ElementUtils.actionClick(driver, verify);
        Thread.sleep(2000);
    }
}
