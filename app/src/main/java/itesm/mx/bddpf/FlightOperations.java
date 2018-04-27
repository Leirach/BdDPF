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

    public long addFlight(String flightID, Date flightTime, String airportOrigin, String terminalOrigin,
                          String gateOrigin, String airportDestination, String terminalDestination,
                          String gateDestination, String airplane) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID, flightID);
            values.put(DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_TIME, persistDate(flightTime));
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

    public ArrayList<String> getAllFlightIDs() {
        ArrayList<String> listEvents = new ArrayList<String>();
        String selectQuery = "SELECT " + DataBaseSchema.FlightTable.COLUMN_NAME_FLIGHT_ID + " FROM " + DataBaseSchema.FlightTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listEvents.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listEvents;
    }
}

