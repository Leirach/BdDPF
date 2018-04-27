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
        Log.i("FlightDBHelper onCreate", CREATE_FLIGHTS_TABLE);
        db.execSQL(CREATE_FLIGHTS_TABLE);

        String CREATE_PASSENGERS_TABLE = "CREATE TABLE " +
                DataBaseSchema.Passenger.TABLE_NAME +
                "(" +
                DataBaseSchema.Passenger._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_ID + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_PASSENGER_TYPE + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_NUMBER_CELL + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_NUMBER_FIXED + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_FIRST_NAME + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_LAST_NAME + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_EMAIL + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_STREET_ADDRESS + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_POSTAL_CODE + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_CITY + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_STATE + " TEXT," +
                DataBaseSchema.Passenger.COLUMN_NAME_COUNTRY + " TEXT" +
                ")";
        Log.i("FlightDBHelper onCreate", CREATE_PASSENGERS_TABLE);
        db.execSQL(CREATE_PASSENGERS_TABLE);

        String CREATE_RESERVATIONS_TABLE = "CREATE TABLE " +
                DataBaseSchema.Reservation.TABLE_NAME +
                "(" +
                DataBaseSchema.Reservation.COLUMN_NAME_RESERVATION_CODE + " TEXT," +
                DataBaseSchema.Reservation.COLUMN_NAME_PAYMENT_INFORMATION + " TEXT," +
                DataBaseSchema.Reservation.COLUMN_NAME_PASSENGER + " TEXT" +
                ")";
        Log.i("FlightDBHelper onCreate", CREATE_RESERVATIONS_TABLE);
        db.execSQL(CREATE_RESERVATIONS_TABLE);
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
