package itesm.mx.bddpf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class that defines how we use the database,
 */

public class FlightOperations {
    private SQLiteDatabase db;
    private FlightDBHelper dbHelper;

    public FlightOperations(Context context) {
        dbHelper = new FlightDBHelper(context);
    }

    public void open() throws SQLException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e("SQLOPEN", e.toString());
        }
    }

    public void close() {
        db.close();
    }

    //Methods to convert the date to a number that's possible to save in the database...
    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    //...and back to a Date
    public static Date loadDate(Long time) {
        return new Date(time);
    }

    public long addFlight(String flightID, Date flightTime, int flightDuration, String airportOrigin, String terminalOrigin,
                          String gateOrigin, String airportDestination, String terminalDestination,
                          String gateDestination, String airplane) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID, flightID);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_TIME, persistDate(flightTime));
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_DURATION, flightDuration);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN, airportOrigin);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_TERMINAL_ORIGIN, terminalOrigin);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_GATE_ORIGIN, gateOrigin);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION, airportDestination);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_TERMINAL_DESTINATION, terminalDestination);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_GATE_DESTINATION, gateDestination);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_AIRPLANE, airplane);

            newRowId = db.insert(DataBaseSchema.FlightTable.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    //drop all tables
    public void dropAllTables() {
        dbHelper.onUpgrade(db, 0, 0);
    }

    public long addPassenger(String passengerID, String type, String cellNumber, String fixedNumber,
                             String firstName, String lastName, String email, String streetAddress,
                             String postalCode, String city, String state, String country) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_ID, passengerID);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_TYPE, type);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_NUMBER_CELL, cellNumber);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_NUMBER_FIXED, fixedNumber);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME, firstName);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_LAST_NAME, lastName);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_EMAIL, email);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_STREET_ADDRESS, streetAddress);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_POSTAL_CODE, postalCode);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_CITY, city);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_STATE, state);
            values.put(DataBaseSchema.Passenger.COLUMN_NAME_COUNTRY, country);

            newRowId = db.insert(DataBaseSchema.Passenger.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    public boolean addReservation(String reservationCode, String paymentInfo, String passengerID) {
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_RESERVATION_CODE, reservationCode);
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_PAYMENT_INFORMATION, paymentInfo);
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER, passengerID);

            db.insert(DataBaseSchema.Reservation.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
            return false;
        }
        return true;
    }

    public long addAirport(String airportCode, String city, String country, String name) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.AirportTable.COLUMN_NAME_AIRPORT_CODE, airportCode);
            values.put(DataBaseSchema.AirportTable.COLUMN_NAME_CITY, city);
            values.put(DataBaseSchema.AirportTable.COLUMN_NAME_COUNTRY, country);
            values.put(DataBaseSchema.AirportTable.COLUMN_NAME_NAME, name);

            newRowId = db.insert(DataBaseSchema.AirportTable.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    public long addTicket(String ticketNumber, double price, String seat, String flightClass, String flight,
                          String reservation, String extraServices, String passenger) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_TICKET_NUMBER, ticketNumber);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_PRICE, price);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_SEAT, seat);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_CLASS, flightClass);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_FLIGHT, flight);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_RESERVATION, reservation);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_EXTRA_SERVICES, extraServices);
            values.put(DataBaseSchema.Ticket.COLUMN_NAME_PASSENGER, passenger);

            newRowId = db.insert(DataBaseSchema.Ticket.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    public long addAirplane(String airplaneID, String model, int seatsEconomy, int seatsBusiness, int seatsFirstClass) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.Airplane.COLUMN_NAME_AIRPLANE_ID, airplaneID);
            values.put(DataBaseSchema.Airplane.COLUMN_NAME_MODEL, model);
            values.put(DataBaseSchema.Airplane.COLUMN_NAME_SEATS_ECONOMY, seatsEconomy);
            values.put(DataBaseSchema.Airplane.COLUMN_NAME_SEATS_BUSINESS, seatsBusiness);
            values.put(DataBaseSchema.Airplane.COLUMN_NAME_SEATS_FIRST_CLASS, seatsFirstClass);

            newRowId = db.insert(DataBaseSchema.Airplane.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    public ArrayList<String> getUniqueOrigins() {
        ArrayList<String> listOrigins = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN + " FROM " + DataBaseSchema.FlightTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listOrigins.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listOrigins;
    }

    public ArrayList<String> getUniqueDestinations() {
        ArrayList<String> listDestinations = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION + " FROM " + DataBaseSchema.FlightTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listDestinations.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listDestinations;
    }
    
    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> listFlights = new ArrayList<Flight>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME;
        Flight flight;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    flight = new Flight(cursor.getString(1), loadDate(cursor.getLong(2)),
                                        cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                                        cursor.getString(6), cursor.getString(7), cursor.getString(8),
                                        cursor.getString(9), cursor.getString(10));
                    listFlights.add(flight);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listFlights;
    }

    public ArrayList<Flight> getAllFlightsFromTo(String airportOrigin, String airportDestination) {
        ArrayList<Flight> listFlights = new ArrayList<Flight>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME + " WHERE " +
                DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN + " LIKE '%" + airportOrigin + "%' AND " +
                DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION + " LIKE '%" + airportDestination + "%'";
        Flight flight;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    flight = new Flight(cursor.getString(1), loadDate(cursor.getLong(2)),
                            cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8),
                            cursor.getString(9), cursor.getString(10));
                    listFlights.add(flight);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listFlights;
    }

    public Flight getFlight(String flightID) {
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME + " WHERE " + DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID + "=\"" + flightID + "\"";
        Flight flight;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                flight = new Flight(cursor.getString(1), loadDate(cursor.getLong(2)),
                            cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8),
                            cursor.getString(9), cursor.getString(10));
                return flight;
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return null;
    }

    public ArrayList<Reservation> getAllReservations() {
        ArrayList<Reservation> listReservations = new ArrayList<Reservation>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.Reservation.TABLE_NAME;
        Reservation reservation;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    reservation = new Reservation(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                    listReservations.add(reservation);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listReservations;
    }

    //return specific passenger
    public Passenger getPassenger(String passengerID) {
        Passenger passenger;
        String selectQuery = "SELECT * FROM " + DataBaseSchema.Passenger.TABLE_NAME +
                " WHERE " + DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_ID + "=\'" + passengerID + "\'";
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    passenger = new Passenger(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8),
                            cursor.getString(9), cursor.getString(10), cursor.getString(11),
                            cursor.getString(12));

                    return passenger;
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return null;
    }

    //return specific reservation
    public Reservation getReservation(String reservationCode) {
        Reservation reservation;
        String selectQuery = "SELECT * FROM " + DataBaseSchema.Reservation.TABLE_NAME +
                " WHERE " + DataBaseSchema.Reservation.COLUMN_NAME_RESERVATION_CODE + "=\'" + reservationCode + "\'";
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    reservation = new Reservation(cursor.getString(0), cursor.getString(1),
                            cursor.getString(2));

                    return reservation;
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return null;
    }

    //Get all passengers from the DB
    public ArrayList<Passenger> getAllPassengers() {
        ArrayList<Passenger> listPassengers = new ArrayList<>();
        Passenger passenger;
        String selectQuery = "SELECT * FROM " + DataBaseSchema.Passenger.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    passenger = new Passenger(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8),
                            cursor.getString(9), cursor.getString(10), cursor.getString(11),
                            cursor.getString(12));

                    listPassengers.add(passenger);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    //Filter passengers with name, country or city
    public ArrayList<Passenger> passengerQuery(String name, String lastname, String city, String country) {
        ArrayList<Passenger> listPassengers = new ArrayList<>();
        Passenger passenger;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM " + DataBaseSchema.Passenger.TABLE_NAME + " ");
        if (name.length() != 0){
            queryBuilder.append("WHERE " + DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME + " LIKE \'" + name + "%\'");
        }

        String selectQuery = "SELECT * FROM " + DataBaseSchema.Passenger.TABLE_NAME + " WHERE " +
                DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME + " LIKE '%" + name + "%' AND " +
                DataBaseSchema.Passenger.COLUMN_NAME_LAST_NAME + " LIKE '%" + lastname + "%' AND " +
                DataBaseSchema.Passenger.COLUMN_NAME_CITY + " LIKE '%" + city + "%' AND " +
                DataBaseSchema.Passenger.COLUMN_NAME_COUNTRY + " LIKE '%" + country + "%'";
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    passenger = new Passenger(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8),
                            cursor.getString(9), cursor.getString(10), cursor.getString(11),
                            cursor.getString(12));
                    listPassengers.add(passenger);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePassengers() {
        ArrayList<String> listPassengers = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER + " FROM " + DataBaseSchema.Reservation.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPassengers.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePassengerNames() {
        ArrayList<String> listPassengers = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME + " FROM " + DataBaseSchema.Passenger.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPassengers.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePassengerLastNames() {
        ArrayList<String> listPassengers = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Passenger.COLUMN_NAME_LAST_NAME + " FROM " + DataBaseSchema.Passenger.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPassengers.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePassengerCities() {
        ArrayList<String> listPassengers = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Passenger.COLUMN_NAME_CITY + " FROM " + DataBaseSchema.Passenger.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPassengers.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePassengerCountries() {
        ArrayList<String> listPassengers = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Passenger.COLUMN_NAME_COUNTRY + " FROM " + DataBaseSchema.Passenger.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPassengers.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPassengers;
    }

    public ArrayList<String> getUniquePayments() {
        ArrayList<String> listPayments = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.Reservation.COLUMN_NAME_PAYMENT_INFORMATION + " FROM " + DataBaseSchema.Reservation.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listPayments.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listPayments;
    }


    public ArrayList<Reservation> getAllReservationsWith(String passenger, String payment, String passengerName) {
        ArrayList<Reservation> listReservations = new ArrayList<Reservation>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.Reservation.TABLE_NAME + " WHERE " +
                DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER + " LIKE '%" + passenger + "%' AND " +
                DataBaseSchema.Reservation.COLUMN_NAME_PAYMENT_INFORMATION + " LIKE '%" + payment + "%'" +
                " AND " + DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER + " IN (SELECT " +
                DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_ID + " FROM " + DataBaseSchema.Passenger.TABLE_NAME +
                " WHERE " + DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME +
                " LIKE '%" +  passengerName + "%')";
        Reservation reservation;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    reservation = new Reservation(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                    listReservations.add(reservation);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listReservations;
    }

    public ArrayList<String> getUniqueAirports() {
        ArrayList<String> listAirports = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.AirportTable.COLUMN_NAME_NAME + " FROM " + DataBaseSchema.AirportTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listAirports.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }

    public ArrayList<String> getUniqueAirportCountries() {
        ArrayList<String> listAirports = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.AirportTable.COLUMN_NAME_COUNTRY + " FROM " + DataBaseSchema.AirportTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listAirports.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }

    public ArrayList<String> getUniqueAirportCities() {
        ArrayList<String> listAirports = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.AirportTable.COLUMN_NAME_CITY + " FROM " + DataBaseSchema.AirportTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listAirports.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }

    public ArrayList<String> getUniqueAirportCodes() {
        ArrayList<String> listAirports = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + DataBaseSchema.AirportTable.COLUMN_NAME_AIRPORT_CODE + " FROM " + DataBaseSchema.AirportTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listAirports.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }

    public ArrayList<Airport> getAllAirports() {
        ArrayList<Airport> listAirports = new ArrayList<Airport>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.AirportTable.TABLE_NAME;
        Airport airport;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    airport = new Airport(cursor.getString(4), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    listAirports.add(airport);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }

    public ArrayList<Airport> airportQuery(String name, String code, String city, String country){
        ArrayList<Airport> listAirports = new ArrayList<Airport>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.AirportTable.TABLE_NAME + " WHERE " +
                DataBaseSchema.AirportTable.COLUMN_NAME_NAME + " LIKE '%" + name + "%' AND " +
                DataBaseSchema.AirportTable.COLUMN_NAME_AIRPORT_CODE + " LIKE '%" + code + "%'" +
                " AND " + DataBaseSchema.AirportTable.COLUMN_NAME_CITY + " LIKE '%" + city + "%'" +
                " AND " + DataBaseSchema.AirportTable.COLUMN_NAME_COUNTRY + " LIKE '%" + country + "%'";
        Airport airport;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    airport = new Airport(cursor.getString(4), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    listAirports.add(airport);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listAirports;
    }
}

