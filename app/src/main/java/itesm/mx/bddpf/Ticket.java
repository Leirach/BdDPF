package itesm.mx.bddpf;

public class Ticket {
    private String ticketNumber;
    private double price;
    private String seat;
    private String ticketClass;
    private String flight;
    private String reservation;
    private String extras;
    private String passenger;

    public Ticket(String ticketNumber, double price, String seat, String ticketClass, String flight, String reservation, String extras, String passenger) {
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.seat = seat;
        this.ticketClass = ticketClass;
        this.flight = flight;
        this.reservation = reservation;
        this.extras = extras;
        this.passenger = passenger;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }
}
