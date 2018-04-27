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
