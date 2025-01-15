package PO_Files;

import Utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPO {
    private WebDriver driver ;

    // Locators
    private By phoneNumberField = By.cssSelector("input[type=tel]");
    private By getOTP = By.xpath("//button[@class=\"secondryButton \"]");
    private By otpField1 = By.xpath("(//input[@data-testid='input'])[1]");
    private By otpField2 = By.xpath("(//input[@data-testid='input'])[2]");
    private By otpField3 = By.xpath("(//input[@data-testid='input'])[3]");
    private By otpField4 = By.xpath("(//input[@data-testid='input'])[4]");

    // Constructor
    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterPhoneNumber(String phoneNumber) {
        ElementUtils.sendKeys(driver, phoneNumberField, phoneNumber);
        ElementUtils.click(driver,getOTP);
    }

    public void enterOTP(String otp) {
        ElementUtils.sendKeys(driver, otpField1, "1");
        ElementUtils.sendKeys(driver, otpField2, "2");
        ElementUtils.sendKeys(driver, otpField3, "3");
        ElementUtils.sendKeys(driver, otpField4, "4");

    }

}


