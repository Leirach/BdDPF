package itesm.mx.bddpf;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juan De León on 5/4/2018.
 */

public class Passenger{
    private String passengerID;
    private String passengerType;
    private String numberCell;
    private String numberFixed;
    private String firstName;
    private String lastName;
    private String email;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String state;
    private String country;


    public Passenger(String passengerID, String passengerType, String numberCell, String numberFixed,
            String firstName, String lastName, String email, String streetAddress, String postalCode,
            String city, String state, String country) {

        this.passengerID = passengerID;
        this.passengerType = passengerType;
        this.numberCell = numberCell;
        this.numberFixed = numberFixed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this. country = country;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getNumberCell() {
        return numberCell;
    }

    public void setNumberCell(String numberCell) {
        this.numberCell = numberCell;
    }

    public String getNumberFixed() {
        return numberFixed;
    }

    public void setNumberFixed(String numberFixed) {
        this.numberFixed = numberFixed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
