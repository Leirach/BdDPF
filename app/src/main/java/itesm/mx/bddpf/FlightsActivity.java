package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FlightsActivity extends AppCompatActivity {
    private FlightOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        dao = new FlightOperations(this);
        dao.open();

        ArrayList<String> flightIDs = dao.getAllFlightIDs();

        ListView listView = (ListView) findViewById(R.id.listview);
        FlightIDAdapter flightIDAdapter = new FlightIDAdapter(this, flightIDs);
        listView.setAdapter(flightIDAdapter);
    }
}
