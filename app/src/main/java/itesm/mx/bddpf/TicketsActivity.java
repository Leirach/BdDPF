package itesm.mx.bddpf;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TicketsActivity extends AppCompatActivity implements TextWatcher{
    FlightOperations dao;
    AutoCompleteTextView actv_reservation;
    AutoCompleteTextView actv_class;
    AutoCompleteTextView actv_ticket;
    ArrayList<Ticket> tickets;
    TicketAdapter ticketAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

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

        listView = findViewById(R.id.listview);
        tickets = dao.getAllTickets();
        ticketAdapter = new TicketAdapter(getApplicationContext(), tickets);
        listView.setAdapter(ticketAdapter);

        ArrayAdapter<String> adapterTicket = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueTickets());
        actv_ticket = (AutoCompleteTextView) findViewById(R.id.edit_ticket);
        actv_ticket.setThreshold(0);
        actv_ticket.setAdapter(adapterTicket);
        actv_ticket.addTextChangedListener(this);

        actv_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_ticket.showDropDown();
            }
        });

        ArrayAdapter<String> adapterClass = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueClasses());
        actv_class = (AutoCompleteTextView) findViewById(R.id.edit_class);
        actv_class.setThreshold(0);
        actv_class.setAdapter(adapterClass);
        actv_class.addTextChangedListener(this);

        actv_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_class.showDropDown();
            }
        });

        ArrayAdapter<String> adapterReservation = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dao.getUniqueReservations());
        actv_reservation = (AutoCompleteTextView) findViewById(R.id.edit_reservation);
        actv_reservation.setThreshold(0);
        actv_reservation.setAdapter(adapterReservation);
        actv_reservation.addTextChangedListener(this);

        actv_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv_reservation.showDropDown();
            }
        });


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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

    public void searchTickets(){
        String reservationSel = actv_reservation.getText().toString();
        String classSel = actv_class.getText().toString().toUpperCase();
        String ticketSel = actv_ticket.getText().toString().toUpperCase();
        tickets = dao.getAllTicketsWith(reservationSel, classSel, ticketSel);
        ticketAdapter = new TicketAdapter(getApplicationContext(), tickets);
        listView.setAdapter(ticketAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchTickets();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
