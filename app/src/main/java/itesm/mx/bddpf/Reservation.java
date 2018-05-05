package itesm.mx.bddpf;

public class Reservation {
    private String code;
    private String passenger;
    private String payment;

    public Reservation(String code, String payment, String passenger) {
        this.code = code;
        this.payment = payment;
        this.passenger = passenger;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
