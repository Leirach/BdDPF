package itesm.mx.bddpf;

public class Reservation {
    private String code;
    private String passenger;
    private String payment;

    public Reservation(String code, String passenger, String payment) {
        this.code = code;
        this.passenger = passenger;
        this.payment = payment;
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
