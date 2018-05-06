package itesm.mx.bddpf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Juan De León on 5/4/2018.
 */

public class PassengerDetailActivity extends AppCompatActivity {
    private Passenger passenger;
    public static final String PASSENGER_KEY = "passenger";
    private FlightOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_detail);
        dao = new FlightOperations(this);
        dao.open();

        Bundle bundle = getIntent().getExtras();
        String passengerID = bundle.getString(PASSENGER_KEY);
        passenger = dao.getPassenger(passengerID);

        TextView tvPassFName = (TextView) findViewById(R.id.pass_full_name);
        TextView tvPassID = (TextView) findViewById(R.id.pass_curp);
        TextView tvPassType = (TextView) findViewById(R.id.pass_type);
        TextView tvPassCell = (TextView) findViewById(R.id.pass_cel);
        TextView tvPassFixed = (TextView) findViewById(R.id.pass_fixed);
        TextView tvPassEmail = (TextView) findViewById(R.id.pass_email);
        TextView tvPassAddress = (TextView) findViewById(R.id.pass_address);
        TextView tvPassZipcode = (TextView) findViewById(R.id.pass_zipcode);
        TextView tvPassCountry = (TextView) findViewById(R.id.pass_country);
        TextView tvPassState = (TextView) findViewById(R.id.pass_state);
        TextView tvPassCity = (TextView) findViewById(R.id.pass_city);

        tvPassFName.setText(passenger.getFirstName() + " " + passenger.getLastName());
        tvPassID.setText(passenger.getPassengerID());
        tvPassType.setText(passenger.getPassengerType());
        tvPassCell.setText(passenger.getNumberCell());
        tvPassFixed.setText(passenger.getNumberFixed());
        tvPassEmail.setText(passenger.getEmail());
        tvPassAddress.setText(passenger.getStreetAddress());
        tvPassZipcode.setText(passenger.getPostalCode());
        tvPassCountry.setText(passenger.getCountry());
        tvPassState.setText(passenger.getState());
        tvPassCity.setText(passenger.getCity());
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
