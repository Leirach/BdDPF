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

    public long addReservation(String reservationCode, String paymentInfo, String passengerID) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_RESERVATION_CODE, reservationCode);
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_PAYMENT_INFORMATION, paymentInfo);
            values.put(DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER, passengerID);

            newRowId = db.insert(DataBaseSchema.Reservation.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
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

    public ArrayList<String> getAllFlightIDs() {
        ArrayList<String> listFlightIDs = new ArrayList<String>();
        String selectQuery = "SELECT " + DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID + " FROM " + DataBaseSchema.FlightTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listFlightIDs.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listFlightIDs;
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

    public ArrayList<Flight> getAllFlightsFrom(String airportOrigin) {
        ArrayList<Flight> listFlights = new ArrayList<Flight>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME + " WHERE " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN + "=\"" + airportOrigin + "\"";
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

    public ArrayList<Flight> getAllFlightsTo(String airportDestination) {
        ArrayList<Flight> listFlights = new ArrayList<Flight>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME + " WHERE " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION + "=\"" + airportDestination + "\"";
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
        String selectQuery = "SELECT * FROM " + DataBaseSchema.FlightTable.TABLE_NAME + " WHERE " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN + "=\"" + airportOrigin + "\" AND " + DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION + "=\"" + airportDestination + "\"";
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
}

