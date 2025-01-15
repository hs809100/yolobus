package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class ElementUtils {
    private static final int DEFAULT_WAIT_TIME = 50; // seconds

    public static void openAgentStage(WebDriver driver){
        driver.get("https://agent-stage.yolobus.in/");
    }
    public static void openRiderStage(WebDriver driver){
        driver.get("https://stage.yolobus.in/");
    }
    public static void openInternalStage(WebDriver driver){
        driver.get("https://internal-stage.yolobus.in/");
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void click(WebDriver driver, By locator) {
        WebElement element = waitForElementToBeClickable(driver, locator);
        element.click();
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementToBeClickable(driver, locator);
        element.sendKeys(text);
    }
    public static void switch_to_frame(WebDriver driver,By frameID){
       WebElement frameElement = driver.findElement(frameID);
        driver.switchTo().frame(frameElement);
    }
    public static void actionClick(WebDriver driver,By locator){
        WebElement element = driver.findElement(locator);
        Actions a = new Actions(driver);
        a.moveToElement(element).click().build().perform();
    }
    public static void actionSendkeys(WebDriver driver,By locator,String string) {
        WebElement element = driver.findElement(locator);
        Actions a = new Actions(driver);
        a.moveToElement(element).sendKeys(string,Keys.ENTER).build().perform();
    }
    public static void switchToWindow(WebDriver driver,int windowIndex) {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        int i = 0;
        while (it.hasNext()) {
            String windowHandle = it.next();
            if (i == windowIndex) {
                driver.switchTo().window(windowHandle);
                break;
            }
            i++;
        }
    }
    public static void takeScreenshot(WebDriver driver) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "C:\\Users\\HARSHITA\\Pictures\\Screenshots\\screenshort_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(filePath));
    }

}

