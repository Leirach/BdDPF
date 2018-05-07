package itesm.mx.bddpf;

public class Airplane {
    private String ID;
    private String model;
    private int economySeats;
    private int businessSeats;
    private int firstSeats;

    public Airplane(String ID, String model, int economySeats, int businessSeats, int firstSeats) {
        this.ID = ID;
        this.model = model;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.firstSeats = firstSeats;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public void setEconomySeats(int economySeats) {
        this.economySeats = economySeats;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(int businessSeats) {
        this.businessSeats = businessSeats;
    }

    public int getFirstSeats() {
        return firstSeats;
    }

    public void setFirstSeats(int firstSeats) {
        this.firstSeats = firstSeats;
    }
}
