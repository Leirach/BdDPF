package itesm.mx.bddpf;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;

public class AirplanesActivity extends AppCompatActivity implements ListView.OnItemClickListener, TextWatcher {
    private FlightOperations dao;
    private ArrayList<Airplane> airplanes;
    private AirplaneAdapter airplaneAdapter;
    private ListView listView;
    AutoCompleteTextView actv_Id;
    AutoCompleteTextView actv_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplanes);

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
        airplanes = dao.getAllAirplanes();
        setAirplaneList();

        ArrayAdapter<String> adapterId = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirplaneIds());
        actv_Id = (AutoCompleteTextView) findViewById(R.id.edit_id);
        actv_Id.setThreshold(0);
        actv_Id.setAdapter(adapterId);
        actv_Id.addTextChangedListener(this);
        actv_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Id.showDropDown();
            }
        });

        ArrayAdapter<String> adapterModel = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueAirplaneModels());
        actv_model = (AutoCompleteTextView) findViewById(R.id.edit_model);
        actv_model.setThreshold(0);
        actv_model.setAdapter(adapterModel);
        actv_model.addTextChangedListener(this);
        actv_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_model.showDropDown();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Airplane airplane = (Airplane) parent.getItemAtPosition(position);
        Intent airplaneDetailIntent = new Intent(this, AirplaneDetailActivity.class);
        airplaneDetailIntent.putExtra(AirplaneDetailActivity.ID_KEY, airplane.getID());
        startActivity(airplaneDetailIntent);
    }

    public void searchAirplanes() {
        String airplaneIDSelected = actv_Id.getText().toString().toUpperCase();
        String airplaneModelSelected = actv_model.getText().toString().toUpperCase();
        updateList(airplaneIDSelected, airplaneModelSelected);
    }

    private void updateList(String airplaneId, String airplandeModel) {
        airplanes = dao.airplaneQuery(airplaneId, airplandeModel);
        setAirplaneList();
    }

    public void setAirplaneList() {
        airplaneAdapter = new AirplaneAdapter(this, airplanes);
        listView.setAdapter(airplaneAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchAirplanes();
    }

    @Override
    public void afterTextChanged(Editable s) {

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
