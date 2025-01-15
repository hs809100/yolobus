package Utils;

import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

import static Utils.ElementUtils.click;
import static Utils.ElementUtils.sendKeys;

public class BookingUtils {
    private WebDriver driver ;


    public static void selectCity(WebDriver driver, By cityLocator, String cityCode, String cityName) throws InterruptedException {
        click(driver, cityLocator);
        Thread.sleep(2000);
        sendKeys(driver, cityLocator, cityCode);
        Thread.sleep(2000);
        for (WebElement element : driver.findElements(By.xpath("//div[@class='pr8 text-capitalize']"))) {
            if (element.getText().contains(cityName)) {
                element.click();
                break;
            }
        }
    }

    public static void selectTravelDate(WebDriver driver, String day, By date) throws InterruptedException {
        for (WebElement element : driver.findElements(date)) {
            String[] split = element.getText().split("\n");
            if (split[0].trim().equals(day)) {
                element.click();
                break;
            }
        }
        Thread.sleep(2000);
    }
    public static void Calendar(WebDriver driver,String targetDate) throws InterruptedException {
        WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='" + targetDate + "' and @aria-disabled='false']"));

        if (dateElement != null) {
            dateElement.click();
            System.out.println("Selected date: " + targetDate);
        } else {
            System.out.println("The target date is not available or is disabled.");
        }
        Thread.sleep(3000);

    }

    public static void selectService(WebDriver driver, By locator,int index) throws InterruptedException {
        List<WebElement> services = driver.findElements(locator);
        // Check if the index is valid
        if (services.isEmpty()) {
            System.out.println("No services found on the page.");
        } else if (index < 0 || index >= services.size()) {
            System.out.println("Invalid index. Please provide an index between 0 and " + (services.size() - 1));
        } else {
            // Click on the service at the specified index
            WebElement selectedService = services.get(index);
            selectedService.click();
        }
        Thread.sleep(3000);

    }

    public static void selectSeat(WebDriver driver, By seatL, By seatS) throws InterruptedException {
        try {
            WebElement Seat = driver.findElement(seatL);
            Seat.click();
        } catch (NoSuchElementException | TimeoutException e) {
            try {
                WebElement Seat = driver.findElement(seatS);
                Seat.click();
            } catch (NoSuchElementException | TimeoutException ex) {
                System.out.println("Both seats could not be clicked: " + ex.getMessage());
            }
        }
        Thread.sleep(2000);
    }

    public static void enterPassengerDetails(WebDriver driver, By Name, By Age, By gender_Options, By PhoneNumber, By Email, String name, String age, String phone, String email) throws InterruptedException {
        sendKeys(driver, Name, name);
        Thread.sleep(1000);
        sendKeys(driver, Age, age);
        Thread.sleep(1000);
        List<WebElement> genderOptions = driver.findElements(gender_Options);
        // Select 'Female' option
        genderOptions.get(1).click();
        Thread.sleep(3000);
        sendKeys(driver, PhoneNumber, phone);
        Thread.sleep(1000);
        sendKeys(driver, Email, email);
        Thread.sleep(1000);
    }

    public static FareDetails verify_totalFare_and_seatTax(WebDriver driver, By baseFareLocator, By seatTaxLocator, By totalFareLocator) throws InterruptedException {
        // CheckPoint 1: Extracting values for Seat_Base_Fare, Seat_Tax, and Total_Fare
        String seatBaseFareText = driver.findElement(baseFareLocator).getText();
        String seatTaxText = driver.findElement(seatTaxLocator).getText();
        String totalFareText = driver.findElement(totalFareLocator).getText();


        // Parsing the extracted text by removing any currency symbols, commas, or spaces
        int Seat_Base_Fare = (int) Double.parseDouble(seatBaseFareText.replaceAll("[^0-9.]", "").trim());
        int Seat_Tax = (int) Double.parseDouble(seatTaxText.replaceAll("[^0-9.]", "").trim());
        int Total_Fare = (int) Double.parseDouble(totalFareText.replaceAll("[^0-9.]", "").trim());


        // Calculating the expected tax and total fare
        int Expected_Tax = (int) Math.round((0.05 * Seat_Base_Fare));  // Assuming 5% tax
        int Expected_Total_Fare = Seat_Base_Fare + Expected_Tax;


        // Assertions to validate the Seat_Tax and Total_Fare
        Assert.assertEquals(Expected_Tax, Seat_Tax, 0.1);
        Assert.assertEquals(Expected_Total_Fare, Total_Fare, 0.1);
        return new FareDetails(Expected_Total_Fare, Seat_Base_Fare, Seat_Tax, Expected_Tax);


    }

    public static void verify_Amount_Being_Paid(WebDriver driver, By WalletBalance, By AvailableCreditLine, By AmountPayable, By AmountToPay, FareDetails fareDetails) throws InterruptedException {
        // Retrieve and parse wallet balance and available credit line from the UI
        String yoloWalletBalanceText = driver.findElement(WalletBalance).getText();
        String availableCreditLineText = driver.findElement(AvailableCreditLine).getText();
        int yoloWalletBalance = (int) Double.parseDouble(yoloWalletBalanceText.replaceAll("[^0-9.]", "").trim());
        int availableCreditLine = (int) Double.parseDouble(availableCreditLineText.replaceAll("[^0-9.]", "").trim());


        int calculatedTotalFare;


        // Determine the total fare based on wallet balance and conditions
        if (fareDetails.expectedTotalFare < 1000) {
            if (yoloWalletBalance == 0) {
                calculatedTotalFare = availableCreditLine - (fareDetails.seatBaseFare + fareDetails.expectedTax);
            } else if (yoloWalletBalance > 0) {
                calculatedTotalFare = availableCreditLine - (fareDetails.seatBaseFare + fareDetails.seatTax);
            } else { // yoloWalletBalance < 0
                calculatedTotalFare = availableCreditLine + yoloWalletBalance - (fareDetails.seatBaseFare + fareDetails.seatTax);
            }
            // Ensure that the fare is not negative
            // fareDetails.expectedTotalFare = Math.max(calculatedTotalFare, 0);
        }


        // Fetch the Amount Payable from the UI and convert it to an integer for comparison
        String amountPayableText = driver.findElement(AmountPayable).getText();
        int amountPayable = (int) Double.parseDouble(amountPayableText.replaceAll("[^0-9.]", "").trim());


        String amountToPayText = driver.findElement(AmountToPay).getText();
        int amountToPay = (int) Double.parseDouble(amountToPayText.replaceAll("[^0-9.]", "").trim());


        // Assertion to validate the Amount Payable with the Expected Total Fare
        Assert.assertEquals(fareDetails.expectedTotalFare, amountToPay, "The calculated fare does not match the amount payable.");
    }

    public static void   validateBookingConfirmation() {

    }


}


