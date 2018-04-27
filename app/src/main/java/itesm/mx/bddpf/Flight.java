package itesm.mx.bddpf;

import java.util.Date;

public class Flight {
    private String flightID;
    private Date flightTime;
    private String airportOrigin;
    private String terminalOrigin;
    private String gateOrigin;
    private String airportDestination;
    private String terminalDestination;
    private String gateDestination;
    private String airplane;

    public Flight(String flightID, Date flightTime, String airportOrigin, String terminalOrigin, String gateOrigin, String airportDestination, String terminalDestination, String gateDestination, String airplane) {
        this.flightID = flightID;
        this.flightTime = flightTime;
        this.airportOrigin = airportOrigin;
        this.terminalOrigin = terminalOrigin;
        this.gateOrigin = gateOrigin;
        this.airportDestination = airportDestination;
        this.terminalDestination = terminalDestination;
        this.gateDestination = gateDestination;
        this.airplane = airplane;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public String getAirportOrigin() {
        return airportOrigin;
    }

    public void setAirportOrigin(String airportOrigin) {
        this.airportOrigin = airportOrigin;
    }

    public String getTerminalOrigin() {
        return terminalOrigin;
    }

    public void setTerminalOrigin(String terminalOrigin) {
        this.terminalOrigin = terminalOrigin;
    }

    public String getGateOrigin() {
        return gateOrigin;
    }

    public void setGateOrigin(String gateOrigin) {
        this.gateOrigin = gateOrigin;
    }

    public String getAirportDestination() {
        return airportDestination;
    }

    public void setAirportDestination(String airportDestination) {
        this.airportDestination = airportDestination;
    }

    public String getTerminalDestination() {
        return terminalDestination;
    }

    public void setTerminalDestination(String terminalDestination) {
        this.terminalDestination = terminalDestination;
    }

    public String getGateDestination() {
        return gateDestination;
    }

    public void setGateDestination(String gateDestination) {
        this.gateDestination = gateDestination;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }
}
