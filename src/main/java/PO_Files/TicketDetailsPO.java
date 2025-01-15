package PO_Files;

import Utils.TicketDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDetailsPO {
    private WebDriver driver;
    private final Logger logger = Logger.getLogger(TicketDetailsPO.class.getName());

    // Locators
    private final By pnrLocator = By.xpath("//h6[@class='pfc7 f18 fw7']");
    private final By bookingIdLocator = By.xpath("//h5[@class='f16 fw5 mb18']");
    private final By serviceNameLocator = By.xpath("//p[@class='sfc3 fw7 f21']");
    private final By timingsLocator = By.xpath("//span[@class='block f18 mt5']");
    private final By nameLocator = By.xpath("//span[@class='block f18 passengerList']");
    private final By boardingPointLocator = By.xpath("(//span[@class='f18'])[1]");
    private final By droppingPointLocator = By.xpath("(//span[@class='f18'])[2]");
    //private final By commissionLocator = By.xpath("(//span[@class='f18'])[3]");

    // Constructor
    public TicketDetailsPO(WebDriver driver) {
        this.driver = driver;
    }

    // Helper Method to Get Text by Locator
    private String getText(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (NoSuchElementException e) {
            logger.log(Level.SEVERE, "Element not found: " + locator, e);
            return null;
        }
    }

    // Method to Extract Ticket Details
    public TicketDetails getTicketDetails() throws ParseException {
        Map<String, String> ticketDetails = new HashMap<>();

        // Extract PNR and Booking ID
        String pnr = getText(pnrLocator);
        if (pnr != null && pnr.split(" ").length > 2) {
            ticketDetails.put("pnrno", pnr.split(" ")[2].trim());
        }

        String bookingId = getText(bookingIdLocator);
        if (bookingId != null && bookingId.split(" ").length > 2) {
            ticketDetails.put("bookingid", bookingId.split(" ")[2].trim());
        }

        // Extract Service Names
        String serviceName = getText(serviceNameLocator);
        if (serviceName != null) {
            String[] serviceNameArray = serviceName.split("\n");
            ticketDetails.put("sourceCityName", serviceNameArray[0].trim());
            ticketDetails.put("destinationCityName", serviceNameArray[1].trim());
        }

        // Extract Timing Details
        String timings = getText(timingsLocator);
        String[] timingsArray = timings.split(",");
        if (timingsArray.length > 2) {
            // Extract and parse journey date
            String journeyDate = timingsArray[0].trim();
            String journeyDateWithYear = journeyDate + " " + Year.now().getValue();


            // Parse the date with the year
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
            LocalDate journey = LocalDate.parse(journeyDateWithYear, inputFormatter);

            // Use the current date
            LocalDate currentDate = LocalDate.now();

            // Format the current date
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
            String formattedDate = currentDate.format(outputFormatter);


            //Boarding Point
            String boarding_point_string = getText(boardingPointLocator);
            String[] boarding_point_array = boarding_point_string.split(",");
            String boarding_point = boarding_point_array[0].trim();
            ticketDetails.put("boardingPoint",boarding_point );

            //Dropping point
            String dropping_point_string = getText(droppingPointLocator);
            String[] dropping_point_array = dropping_point_string.split(",");
            String dropping_point = dropping_point_array[0].trim();
            ticketDetails.put("droppingPoint", dropping_point);



            // Store details
                ticketDetails.put("outputDate", formattedDate);
                ticketDetails.put("timing", timingsArray[1].trim());
                ticketDetails.put("seats", timingsArray[2].trim());


        }


            // Extract Remaining Details
            ticketDetails.put("name", Optional.ofNullable(getText(nameLocator)).orElse("Not Available"));
         // ticketDetails.put("commision", Optional.ofNullable(getText(commissionLocator)).orElse("0"));

            System.out.println(ticketDetails);

            // Return the TicketDetails object
            return new TicketDetails(ticketDetails);
        }
    }

