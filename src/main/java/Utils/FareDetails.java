package Utils;

public class FareDetails {
    public int expectedTotalFare;
    public int seatBaseFare;
    public int seatTax;
    public int expectedTax;

    public FareDetails(int expectedTotalFare, int seatBaseFare, int seatTax, int expectedTax) {
        this.expectedTotalFare = expectedTotalFare;
        this.seatBaseFare = seatBaseFare;
        this.seatTax = seatTax;
        this.expectedTax = expectedTax;
    }
}
