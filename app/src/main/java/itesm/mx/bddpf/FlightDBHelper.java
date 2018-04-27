package itesm.mx.bddpf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class that manages the database for events. It can create and upgrade/downgrade the database
 */

public class FlightDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FlightsDB.db";
    private static final int DATABASE_VERSION = 1;

    public FlightDBHelper(Context context) {
        //Create database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FLIGHTS_TABLE = "CREATE TABLE " +
                DataBaseSchema.FlightTable.TABLE_NAME +
                "(" +
                DataBaseSchema.FlightTable._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_TIME + " INTEGER," +
                DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_ORIGIN + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_TERMINAL_ORIGIN + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_GATE_ORIGIN + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_AIRPORT_DESTINATION + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_TERMINAL_DESTINATION + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_GATE_DESTINATION + " TEXT," +
                DataBaseSchema.FlightTable.COLUMN_NAME_AIRPLANE + " TEXT" +
                ")";
        Log.i("Eventhelper onCreate", CREATE_FLIGHTS_TABLE);
        db.execSQL(CREATE_FLIGHTS_TABLE);

        String CREATE_AIRPORT_TABLE = "CREATE TABLE " +
                DataBaseSchema.AirportTable.TABLE_NAME +
                "(" +
                DataBaseSchema.AirportTable._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.AirportTable.COLUMN_NAME_AIRPORT_CODE + " TEXT," +
                DataBaseSchema.AirportTable.COLUMN_NAME_CITY + " TEXT," +
                DataBaseSchema.AirportTable.COLUMN_NAME_COUNTRY + " TEXT," +
                DataBaseSchema.AirportTable.COLUMN_NAME_NAME + " TEXT," +
                ")";
        Log.i("Eventhelper onCreate", CREATE_AIRPORT_TABLE);
        db.execSQL(CREATE_AIRPORT_TABLE);

        String CREATE_AIRPLANE_TABLE = "CREATE TABLE " +
                DataBaseSchema.Airplane.TABLE_NAME +
                "(" +
                DataBaseSchema.Airplane._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.Airplane.COLUMN_NAME_AIRPLANE_ID + " TEXT," +
                DataBaseSchema.Airplane.COLUMN_NAME_MODEL + " TEXT," +
                DataBaseSchema.Airplane.COLUMN_NAME_SEATS_ECONOMY + " INTEGER," +
                DataBaseSchema.Airplane.COLUMN_NAME_SEATS_BUSINESS + " INTEGER," +
                DataBaseSchema.Airplane.COLUMN_NAME_SEATS_FIRST_CLASS + " INTEGER," +
                ")";
        Log.i("Eventhelper onCreate", CREATE_AIRPLANE_TABLE);
        db.execSQL(CREATE_AIRPLANE_TABLE);

        String CREATE_TICKET_TABLE = "CREATE TABLE " +
                DataBaseSchema.Ticket.TABLE_NAME +
                "(" +
                DataBaseSchema.Ticket._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.Ticket.COLUMN_NAME_TICKET_NUMBER + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_PRICE + " DOUBLE," +
                DataBaseSchema.Ticket.COLUMN_NAME_SEAT + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_CLASS + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_FLIGHT + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_RESERVATION + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_EXTRA_SERVICES + " TEXT," +
                DataBaseSchema.Ticket.COLUMN_NAME_PASSENGER + " TEXT," +
                ")";
        Log.i("Eventhelper onCreate", CREATE_TICKET_TABLE);
        db.execSQL(CREATE_TICKET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_FLIGHTS_TABLE = "DROP TABLE IF EXISTS  " +
                DataBaseSchema.FlightTable.TABLE_NAME;
        db.execSQL(DELETE_FLIGHTS_TABLE);
        onCreate(db);
    }

    public  void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualize database version
    }
}
