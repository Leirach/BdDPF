package itesm.mx.bddpf;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class FlightsActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    private FlightOperations dao;
    private ArrayList<Flight> flights;
    private FlightAdapter flightAdapter;
    private ListView listView;
    AutoCompleteTextView actv_Origins;
    AutoCompleteTextView actv_Destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);


        dao = new FlightOperations(this);
        dao.open();


        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        flights = dao.getAllFlights();
        setFlightList();

        ArrayAdapter<String> adapterOrigin = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueOrigins());
        actv_Origins = (AutoCompleteTextView) findViewById(R.id.edit_from);
        actv_Origins.setThreshold(0);
        actv_Origins.setAdapter(adapterOrigin);
        actv_Origins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFlights();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actv_Origins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Origins.showDropDown();
            }
        });

        ArrayAdapter<String> adapterDestination = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueDestinations());
        actv_Destinations = (AutoCompleteTextView) findViewById(R.id.edit_to);
        actv_Destinations.setThreshold(0);
        actv_Destinations.setAdapter(adapterDestination);
        actv_Destinations.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFlights();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actv_Destinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Destinations.showDropDown();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start add activity
                Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchFlights(){
        String airportOriginSelected = actv_Origins.getText().toString().toUpperCase();
        String airportDestinationSelected = actv_Destinations.getText().toString().toUpperCase();
        airportsToFrom(airportOriginSelected, airportDestinationSelected);
    }

    public void airportsToFrom(String airportOriginSelected, String airportDestinationSelected) {
        flights = dao.getAllFlightsFromTo(airportOriginSelected, airportDestinationSelected);
        flightAdapter = new FlightAdapter(getApplicationContext(), flights);
        setFlightList();
    }

    public void setFlightList() {
        flightAdapter = new FlightAdapter(this, flights);
        listView.setAdapter(flightAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent flightDetail = new Intent(this, FlightDetailActivity.class);
        Flight flight = (Flight) parent.getItemAtPosition(position);
        flightDetail.putExtra(FlightDetailActivity.FLIGHT_KEY, flight.getFlightID());
        startActivity(flightDetail);
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
    }
}
