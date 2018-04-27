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
}

