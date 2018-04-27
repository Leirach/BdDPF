package itesm.mx.bddpf;

import android.provider.BaseColumns;

/**
 * Class to the describe the database that is used to save events
 */

public class DataBaseSchema {
    private DataBaseSchema() {}

    public static class FlightTable implements BaseColumns {
        public static final String TABLE_NAME = "Flight";
        public static final String COLUMN_NAME_FLIGHT_ID = "FlightID";
        public static final String COLUMN_NAME_FLIGHT_TIME = "FlightTime";
        public static final String COLUMN_NAME_AIRPORT_ORIGIN = "AirportOrigin";
        public static final String COLUMN_NAME_TERMINAL_ORIGIN = "TerminalOrigin";
        public static final String COLUMN_NAME_GATE_ORIGIN = "GateOrigin";
        public static final String COLUMN_NAME_AIRPORT_DESTINATION = "AirportDestination";
        public static final String COLUMN_NAME_TERMINAL_DESTINATION = "TerminalDestination";
        public static final String COLUMN_NAME_GATE_DESTINATION = "GateDestination";
        public static final String COLUMN_NAME_AIRPLANE = "Airplane";
    }

    public static class AirportTable implements BaseColumns {
        public static final String TABLE_NAME = "Airport";
        public static final String COLUMN_NAME_AIRPORT_CODE = "airportCode";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static class Airplane implements BaseColumns {
        public static final String TABLE_NAME = "Airplane";
        public static final String COLUMN_NAME_AIRPLANE_ID = "airplaneID";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_SEATS_ECONOMY = "seatsEconomy";
        public static final String COLUMN_NAME_SEATS_BUSINESS = "seatsBusiness";
        public static final String COLUMN_NAME_SEATS_FIRST_CLASS = "seatsFirstClass";
    }

    public static class Ticket implements BaseColumns {
        public static final String TABLE_NAME = "ticket";
        public static final String COLUMN_NAME_TICKET_NUMBER = "ticketNumber";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_SEAT = "seat";
        public static final String COLUMN_NAME_CLASS = "class";
        public static final String COLUMN_NAME_FLIGHT = "flight";
        public static final String COLUMN_NAME_RESERVATION = "reservation";
        public static final String COLUMN_NAME_EXTRA_SERVICES = "extraServices";
        public static final String COLUMN_NAME_PASSENGER = "passenger";
    }

    public static class Reservation implements BaseColumns {
        public static final String TABLE_NAME = "Reservation";
        public static final String COLUMN_NAME_RESERVATION_CODE = "reservationCode";
        public static final String COLUMN_NAME_PAYMENT_INFORMATION = "paymentInformation";
        public static final String COLUMN_NAME_PASSENGER = "passenger";
    }

    public static class Passenger implements BaseColumns {
        public static final String TABLE_NAME = "Passenger";
        public static final String COLUMN_NAME_PASSENGER_ID = "passengerID";
        public static final String COLUMN_NAME_PASSENGER_TYPE = "passengerType";
        public static final String COLUMN_NAME_NUMBER_CELL = "numberCell";
        public static final String COLUMN_NAME_NUMBER_FIXED = "numberFixed";
        public static final String COLUMN_NAME_FIRST_NAME = "firstName";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_STREET_ADDRESS = "streetAddress";
        public static final String COLUMN_NAME_POSTAL_CODE = "postalCode";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COUNTRY = "country";
    }
}

