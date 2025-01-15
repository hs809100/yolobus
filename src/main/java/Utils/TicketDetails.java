package Utils;

import java.util.Map;

public class TicketDetails {
    private final String pnrNo;
    private final String bookingId;
    private final String sourceCityName;
    private final String destinationCityName;
    private final String boardingPoint;
    private final String droppingPoint;
    private final String name;
    private final String timing;
    private final String outputDate;
    private final String numberOfSeats;
    private final String commission;

    // Constructor accepting a Map
    public TicketDetails(Map<String, String> ticketDetailsMap) {
        this.pnrNo = ticketDetailsMap.getOrDefault("pnrno", "Not Available");
        this.bookingId = ticketDetailsMap.getOrDefault("bookingid", "Not Available");
        this.sourceCityName = ticketDetailsMap.getOrDefault("sourceCityName", "Not Available");
        this.destinationCityName = ticketDetailsMap.getOrDefault("destinationCityName", "Not Available");
        this.boardingPoint = ticketDetailsMap.getOrDefault("boardingPoint", "Not Available");
        this.droppingPoint = ticketDetailsMap.getOrDefault("droppingPoint", "Not Available");
        this.name = ticketDetailsMap.getOrDefault("name", "Not Available");
        this.timing = ticketDetailsMap.getOrDefault("timing", "Not Available");
        this.outputDate = ticketDetailsMap.getOrDefault("outputDate", "Not Available");
        this.numberOfSeats = ticketDetailsMap.getOrDefault("seats", "Not Available");
        this.commission = ticketDetailsMap.getOrDefault("commision", "0");
    }

    // Getters
    public String getPnrNo() {
        return pnrNo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getSourceCityName() {
        return sourceCityName;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public String getDroppingPoint() {
        return droppingPoint;
    }

    public String getName() {
        return name;
    }

    public String getTiming() {
        return timing;
    }

    public String getOutputDate() {
        return outputDate;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getCommission() {
        return commission;
    }

    // Override toString Method
    @Override
    public String toString() {
        return "TicketDetails{" +
                "pnrNo='" + pnrNo + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", sourceCityName='" + sourceCityName + '\'' +
                ", destinationCityName='" + destinationCityName + '\'' +
                ", boardingPoint='" + boardingPoint + '\'' +
                ", droppingPoint='" + droppingPoint + '\'' +
                ", name='" + name + '\'' +
                ", timing='" + timing + '\'' +
                ", outputDate='" + outputDate + '\'' +
                ", numberOfSeats='" + numberOfSeats + '\'' +
                ", commission='" + commission + '\'' +
                '}';
    }
}
