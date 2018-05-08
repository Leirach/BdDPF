package itesm.mx.bddpf;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PassengerActivity extends AppCompatActivity implements ListView.OnItemClickListener, TextWatcher {

    private FlightOperations dao;
    private ArrayList<Passenger> passengers;
    private PassengerAdapter passengerAdapter;
    private ListView listView;
    AutoCompleteTextView actv_Name;
    AutoCompleteTextView actv_lastName;
    AutoCompleteTextView actv_city;
    AutoCompleteTextView actv_Country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        //add new passenger?
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPassenger = new Intent(getApplicationContext(), PassengerAddActivity.class);
                startActivity(addPassenger);
            }
        });

        dao = new FlightOperations(this);
        dao.open();

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        passengers = dao.getAllPassengers();
        setPassengerList();

        ArrayAdapter<String> adapterFirstName = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengerNames());
        actv_Name = (AutoCompleteTextView) findViewById(R.id.edit_nombre);
        actv_Name.setThreshold(0);
        actv_Name.setAdapter(adapterFirstName);
        actv_Name.addTextChangedListener(this);

        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengerCountries());
        actv_Country = (AutoCompleteTextView) findViewById(R.id.edit_pais);
        actv_Country.setThreshold(0);
        actv_Country.setAdapter(adapterCountry);
        actv_Country.addTextChangedListener(this);

        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengerCities());
        actv_city = (AutoCompleteTextView) findViewById(R.id.edit_ciudad);
        actv_city.setThreshold(0);
        actv_city.setAdapter(adapterCity);
        actv_city.addTextChangedListener(this);

        ArrayAdapter<String> adapterLastname = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengerLastNames());
        actv_lastName = (AutoCompleteTextView) findViewById(R.id.edit_apellido);
        actv_lastName.setThreshold(0);
        actv_lastName.setAdapter(adapterLastname);
        actv_lastName.addTextChangedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent passengerDetail = new Intent(this, PassengerDetailActivity.class);
        Passenger passenger = (Passenger) parent.getItemAtPosition(position);
        passengerDetail.putExtra(PassengerDetailActivity.PASSENGER_KEY, passenger.getPassengerID());
        startActivity(passengerDetail);
    }

    public void searchPassengers() {
        String passengerNameSelected = actv_Name.getText().toString().toUpperCase();
        String passengerCountrySelected = actv_Country.getText().toString().toUpperCase();
        String passengerLastNameSelected = actv_lastName.getText().toString().toUpperCase();
        String passengerCitySelected = actv_city.getText().toString().toUpperCase();
        updateList(passengerNameSelected, passengerLastNameSelected, passengerCitySelected, passengerCountrySelected);
    }

    private void updateList(String passengerFName, String passengerLName, String passengerCity, String passengerCountry) {
        passengers = dao.passengerQuery(passengerFName, passengerLName, passengerCity, passengerCountry);
        setPassengerList();
    }

    public void setPassengerList() {
        passengerAdapter = new PassengerAdapter(this, passengers);
        listView.setAdapter(passengerAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchPassengers();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
