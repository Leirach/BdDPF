package itesm.mx.bddpf;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;

public class AirportsActivity extends AppCompatActivity implements ListView.OnItemClickListener, TextWatcher {
    private FlightOperations dao;
    private ArrayList<Airport> airports;
    private AirportAdapter airportAdapter;
    private ListView listView;
    AutoCompleteTextView actv_Name;
    AutoCompleteTextView actv_code;
    AutoCompleteTextView actv_city;
    AutoCompleteTextView actv_Country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports);

        //add new passenger?
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start add activity
                Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
            }
        });

        dao = new FlightOperations(this);
        dao.open();

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        airports = dao.getAllAirports();
        setAirportList();

        ArrayAdapter<String> adapterFirstName = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirports());
        actv_Name = (AutoCompleteTextView) findViewById(R.id.edit_nombre);
        actv_Name.setThreshold(0);
        actv_Name.setAdapter(adapterFirstName);
        actv_Name.addTextChangedListener(this);

        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirportCountries());
        actv_Country = (AutoCompleteTextView) findViewById(R.id.edit_pais);
        actv_Country.setThreshold(0);
        actv_Country.setAdapter(adapterCountry);
        actv_Country.addTextChangedListener(this);

        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirportCities());
        actv_city = (AutoCompleteTextView) findViewById(R.id.edit_ciudad);
        actv_city.setThreshold(0);
        actv_city.setAdapter(adapterCity);
        actv_city.addTextChangedListener(this);

        ArrayAdapter<String> adapterCodigo = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirportCodes());
        actv_code = (AutoCompleteTextView) findViewById(R.id.edit_codigo);
        actv_code.setThreshold(0);
        actv_code.setAdapter(adapterCodigo);
        actv_code.addTextChangedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Intent airportDetail = new Intent(this, AirportDetailActivity.class);
        Airport airport = (Airport) parent.getItemAtPosition(position);
        airportDetail.putExtra(AirportDetailActivity.AIRPORT_KEY, airport.getCode());
        startActivity(airportDetail);*/
    }

    public void searchAirports() {
        String airportNameSelected = actv_Name.getText().toString().toUpperCase();
        String airportCountrySelected = actv_Country.getText().toString().toUpperCase();
        String airportCodeSelected = actv_code.getText().toString().toUpperCase();
        String airportCitySelected = actv_city.getText().toString().toUpperCase();
        updateList(airportNameSelected, airportCodeSelected, airportCitySelected, airportCountrySelected);
    }

    private void updateList(String passengerFName, String passengerLName, String passengerCity, String passengerCountry) {
        airports = dao.airportQuery(passengerFName, passengerLName, passengerCity, passengerCountry);
        setAirportList();
    }

    public void setAirportList() {
        airportAdapter = new AirportAdapter(this, airports);
        listView.setAdapter(airportAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchAirports();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
