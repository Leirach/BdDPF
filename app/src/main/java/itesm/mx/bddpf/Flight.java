package itesm.mx.bddpf;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Flight implements Parcelable{
    private String flightID;
    private Date flightTime;
    private int flightDuration; //In minutes
    private String airportOrigin;
    private String terminalOrigin;
    private String gateOrigin;
    private String airportDestination;
    private String terminalDestination;
    private String gateDestination;
    private String airplane;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        public Flight[] newArray(int size) { return new Flight[size]; }
    };

    public Flight(String flightID, Date flightTime, int flightDuration, String airportOrigin, String terminalOrigin, String gateOrigin, String airportDestination, String terminalDestination, String gateDestination, String airplane) {
        this.flightID = flightID;
        this.flightTime = flightTime;
        this.flightDuration = flightDuration;
        this.airportOrigin = airportOrigin;
        this.terminalOrigin = terminalOrigin;
        this.gateOrigin = gateOrigin;
        this.airportDestination = airportDestination;
        this.terminalDestination = terminalDestination;
        this.gateDestination = gateDestination;
        this.airplane = airplane;
    }

    public Flight (Parcel in) {
        this.flightID = in.readString();
        this.flightTime = new Date(in.readLong());
        this.flightDuration = in.readInt();
        this.airportOrigin = in.readString();
        this.terminalOrigin = in.readString();
        this.gateOrigin = in.readString();
        this.airportDestination = in.readString();
        this.terminalDestination = in.readString();
        this.gateDestination = in.readString();
        this.airplane = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flightID);
        dest.writeLong(flightTime.getTime());
        dest.writeInt(flightDuration);
        dest.writeString(airportOrigin);
        dest.writeString(terminalOrigin);
        dest.writeString(gateOrigin);
        dest.writeString(airportDestination);
        dest.writeString(terminalDestination);
        dest.writeString(gateDestination);
        dest.writeString(airplane);
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

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
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
