package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class FlightsActivity extends AppCompatActivity {
    private FlightOperations dao;
    private ArrayList<Flight> flights;
    private FlightAdapter flightAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);


        dao = new FlightOperations(this);
        dao.open();

        //comment this after running first time since it will keep on adding new flights to the database every time
        addFlightsToDatabase();

        listView = (ListView) findViewById(R.id.listview);
        flights = dao.getAllFlights();
        setFlightList();

        ArrayAdapter<String> adapterOrigin = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueOrigins());
        final AutoCompleteTextView actv_Origins = (AutoCompleteTextView) findViewById(R.id.edit_from);
        actv_Origins.setThreshold(0);
        actv_Origins.setAdapter(adapterOrigin);
        actv_Origins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String airportOriginSelected = actv_Origins.getText().toString().toUpperCase();
                flights = dao.getAllFlightsFrom(airportOriginSelected);
                flightAdapter = new FlightAdapter(getApplicationContext(), flights);
                setFlightList();
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
    }

    public void setFlightList() {
        flightAdapter = new FlightAdapter(this, flights);
        listView.setAdapter(flightAdapter);
    }

    private void addFlightsToDatabase() {
        dao.addFlight("KSDJ", new Date(), "LAX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("ASDL", new Date(), "LAX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("FDAA", new Date(), "MEX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("HGSD", new Date(), "GOT", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("JFGF", new Date(), "LAX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("TYRB", new Date(), "GOT", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("FDSFF", new Date(), "LAX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("FDSF", new Date(), "MTY", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("LHGG", new Date(), "MEX", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("KJHG", new Date(), "GOT", "2", "1", "MEX", "4", "12", "ASKDJJ");
        dao.addFlight("IUTR", new Date(), "MTY", "2", "1", "MEX", "4", "12", "ASKDJJ");


    }
}
