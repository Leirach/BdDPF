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

public class PassengerActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private FlightOperations dao;
    private ArrayList<Passenger> passengers;
    private PassengerAdapter passengerAdapter;
    private ListView listView;
    AutoCompleteTextView actv_Name;
    AutoCompleteTextView actv_Country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        //Merged with my branch, what is this code?
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
        passengers = dao.getAllPassengers();
        setPassengerList();

        ArrayAdapter<String> adapterOrigin = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueOrigins());
        actv_Name = (AutoCompleteTextView) findViewById(R.id.edit_nombre);
        actv_Name.setThreshold(0);
        actv_Name.setAdapter(adapterOrigin);
        actv_Name.addTextChangedListener(new TextWatcher() {
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
        });
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
        //String passengerCountrySelected = actv_Country.getText().toString().toUpperCase();
        if (passengerNameSelected.length() != 0) {
            updateList(passengerNameSelected, "", "", "");
        }
    }

    private void updateList(String passengerFName, String passengerLName, String passengerCity, String passengerCountry) {
        passengers = dao.passengerQuery(passengerFName, passengerLName, passengerCity, passengerCountry);
        passengerAdapter = new PassengerAdapter(getApplicationContext(), passengers);
        setPassengerList();
    }

    public void setPassengerList() {
        passengerAdapter = new PassengerAdapter(this, passengers);
        listView.setAdapter(passengerAdapter);
    }
}
