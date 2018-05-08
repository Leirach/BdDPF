package itesm.mx.bddpf;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReservationsActivity extends AppCompatActivity implements TextWatcher, ListView.OnItemClickListener {
    private FlightOperations dao;
    private AutoCompleteTextView actv_Passenger;
    private AutoCompleteTextView actv_Payment;
    private AutoCompleteTextView actv_Name;
    private ArrayList<Reservation> reservations;
    private ReservationAdapter reservationAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        dao = new FlightOperations(this);
        dao.open();

        listView = (ListView) findViewById(R.id.listview);
        reservations = dao.getAllReservations();
        reservationAdapter = new ReservationAdapter(this, reservations);
        listView.setAdapter(reservationAdapter);
        listView.setOnItemClickListener(this);

        ArrayAdapter<String> adapterPassenger = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengers());
        actv_Passenger = (AutoCompleteTextView) findViewById(R.id.edit_passenger);
        actv_Passenger.setThreshold(0);
        actv_Passenger.setAdapter(adapterPassenger);
        actv_Passenger.addTextChangedListener(this);

        actv_Passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Passenger.showDropDown();
            }
        });

        ArrayAdapter<String> adapterPayment = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePayments());
        actv_Payment = (AutoCompleteTextView) findViewById(R.id.edit_payment);
        actv_Payment.setThreshold(0);
        actv_Payment.setAdapter(adapterPayment);
        actv_Payment.addTextChangedListener(this);

        actv_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Payment.showDropDown();
            }
        });

        ArrayAdapter<String> adapterPassengerNames = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniquePassengerNames());
        actv_Name = (AutoCompleteTextView) findViewById(R.id.edit_name);
        actv_Name.setThreshold(0);
        actv_Name.setAdapter(adapterPassengerNames);
        actv_Name.addTextChangedListener(this);

        actv_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_Name.showDropDown();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation = new Intent(getApplicationContext(), AddNewReservationActivity.class);
                startActivityForResult(addReservation, 0);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            onResume();
            reservations = dao.getAllReservations();
            reservationAdapter = new ReservationAdapter(this, reservations);
            listView.setAdapter(reservationAdapter);
        }
    }

    public void searchReservations(){
        String passengerNameSelected = actv_Name.getText().toString();
        String passengerSelected = actv_Passenger.getText().toString().toUpperCase();
        String paymentSelected = actv_Payment.getText().toString().toUpperCase();
        reservations = dao.getAllReservationsWith(passengerSelected, paymentSelected, passengerNameSelected);
        reservationAdapter = new ReservationAdapter(getApplicationContext(), reservations);
        listView.setAdapter(reservationAdapter);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchReservations();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Reservation reservation = (Reservation) parent.getItemAtPosition(position);
        Intent openDetailReservation = new Intent(this, ReservationDetailActivity.class);
        openDetailReservation.putExtra(ReservationDetailActivity.CODE_KEY, reservation.getCode());
        startActivity(openDetailReservation);
    }
}
