package itesm.mx.bddpf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FlightOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPassengers = (Button) findViewById(R.id.btn_seePassengers);
        Button btnFlights = (Button) findViewById(R.id.btn_seeFlights);
        Button btnTickets = (Button) findViewById(R.id.btn_seeTickets);
        Button btnReservations = (Button) findViewById(R.id.btn_seeReservations);

        btnPassengers.setOnClickListener(this);
        btnFlights.setOnClickListener(this);
        btnReservations.setOnClickListener(this);
        btnTickets.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seeFlights:
                Intent flightsActivity = new Intent(this, FlightsActivity.class);
                startActivity(flightsActivity);
                break;
            case R.id.btn_seePassengers:
                Intent passengerActivity = new Intent(this, PassengerActivity.class);
                startActivity(passengerActivity);
                break;
            case R.id.btn_seeReservations:
                Intent reservationsActivity = new Intent(this, ReservationsActivity.class);
                startActivity(reservationsActivity);
                break;
            case R.id.btn_seeTickets:
                Intent ticketsActivity = new Intent(this, TicketsActivity.class);
                startActivity(ticketsActivity);
                break;
        }
    }
}

