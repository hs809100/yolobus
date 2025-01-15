package PO_Files;

import Utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class BookingStepsPO {
    private WebDriver driver;
    private FareDetails fareDetails;
    private TicketDetails ticketDetails;
    private TicketDetailsPO ticketDetailsPO;




    // Locators
    private By sourceCityInput = By.xpath("//input[@placeholder=\"Departure\"]");
    private By destinationCityInput = By.xpath("//input[@placeholder=\"Destination\"]");
    private By destinationCityInputRider = By.xpath("//input[@placeholder=\"Return\"]");
    private By date = By.xpath("//div[@class='date']");
    private By searchBus = By.xpath("//button[@class=\"searchBusButton  f16\"]");
    private By selectService = By.xpath("//div[@class='suggestions-card']");
    private By selectSeatL = By.xpath("//div[@class='card doubleHeight']");
    private By selectSeatS = By.xpath("//div[@class='card']");
    private By confirmSeatButton = By.xpath("//button[@class=\"seat-button \"]");
    private By Name = By.xpath("//input[@placeholder=\"Enter Name\"]");
    private By Age = By.cssSelector("input[type='number']");
    private By Gender = By.xpath("//button[@class='gender']");
    private By PhoneNumber = By.cssSelector("input[type='tel']");
    private By Email = By.cssSelector("input[type='email']");
    private By seatBaseFare = By.xpath("(//div[@class=\"flex alignCenter spaceBetween mb15\"])[1]");
    private By seatTax = By.xpath("(//div[@class=\"flex alignCenter spaceBetween mb15\"])[2]");
    private By totalFare = By.xpath("//div[@class=\"flex alignCenter spaceBetween\"]");
    private By ConfirmPay = By.xpath("//*[@id=\"content\"]/div/div/div[2]/div[2]/div/div[2]/div[3]/button");
    private By yoloWalletBalance = By.xpath("//div[@class=\"flex radioButton p15 alignCenter spaceBetween userboxShadow\"]");
    private By availableCreditLine = By.xpath("//div[@class=\"flex radioButton p15 alignCenter spaceBetween userboxShadow\"]");
    private By amountPayable = By.xpath("//div[@class='p15 flex spaceBetween fw5 userboxShadow f16 fwb']");
    private By amountToPay = By.xpath("//p[@class='f20 fw7']");



    // Constructor
    public BookingStepsPO(WebDriver driver) {
        this.driver = driver;
        this.ticketDetailsPO = new TicketDetailsPO(driver);  // Initialize TicketDetailsPO here
    }

    // Actions
    public void selectSourceCity(String cityName, String cityCode) throws InterruptedException {
        BookingUtils.selectCity(driver, sourceCityInput, cityCode, cityName);
    }

    public void selectDestinationCity(String cityName, String cityCode) throws InterruptedException {
        BookingUtils.selectCity(driver, destinationCityInput, cityCode, cityName);
    }
    public void selectDestinationCityRider(String cityName, String cityCode) throws InterruptedException {
        BookingUtils.selectCity(driver, destinationCityInputRider, cityCode, cityName);
    }

    public void chooseTravelDate(String day) throws InterruptedException {
        BookingUtils.selectTravelDate(driver, day, date);
    }
    public void chooseTravelDateRider(String day) throws InterruptedException {
        BookingUtils.selectTravelDate(driver, day, date);
        ElementUtils.click(driver,searchBus);
    }
    public void chooseService(int index) throws InterruptedException {
        BookingUtils.selectService(driver, selectService,index);
    }

    public void chooseSeat() throws InterruptedException {
        BookingUtils.selectSeat(driver, selectSeatL, selectSeatS);
    }

    public void confirmSeatSelection() {
        ElementUtils.click(driver, confirmSeatButton);
    }


    public void addPassengerDetails(String name, String age, String phoneNumber, String email) throws InterruptedException {
        BookingUtils.enterPassengerDetails(driver, Name, Age, Gender, PhoneNumber, Email, name, age, phoneNumber, email);
    }

    public void verifyTotalFareAndSeatTax() throws InterruptedException {
        fareDetails = BookingUtils.verify_totalFare_and_seatTax(driver, seatBaseFare, seatTax, totalFare);
    }

    public void confirmBooking() {
        ElementUtils.click(driver, ConfirmPay);
    }

    public void verifyAmountBeingPaid() throws InterruptedException {
        BookingUtils.verify_Amount_Being_Paid(driver, yoloWalletBalance, availableCreditLine, amountPayable, amountToPay, fareDetails);
    }

    public void fetchTicketDetails() throws ParseException {
        // Get ticket details from the PO file
        ticketDetails = ticketDetailsPO.getTicketDetails(); // This fetches the ticket details from TicketDetailsPO
        System.out.println(ticketDetails);

    }

}
