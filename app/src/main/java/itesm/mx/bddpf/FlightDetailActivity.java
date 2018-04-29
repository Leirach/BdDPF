package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class FlightDetailActivity extends AppCompatActivity {
    private Flight flight;
    public static final String FLIGHT_KEY = "flight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        Bundle bundle = getIntent().getExtras();
        flight = bundle.getParcelable(FLIGHT_KEY);
        TextView tvFlightID = (TextView) findViewById(R.id.text_flightID);
        TextView tvFlightOrigin = (TextView) findViewById(R.id.text_from);
        TextView tvFlightOriginTerminal = (TextView) findViewById(R.id.text_from_terminal);
        TextView tvFlightOriginGate = (TextView) findViewById(R.id.text_from_gate);
        TextView tvFlightDestination = (TextView) findViewById(R.id.text_to);
        TextView tvFlightDestinationTerminal = (TextView) findViewById(R.id.text_to_terminal);
        TextView tvFlightDestinationGate = (TextView) findViewById(R.id.text_to_gate);
        TextView tvAirplane = (TextView) findViewById(R.id.text_airplane);
        TextView tvDate = (TextView) findViewById(R.id.text_date);

        tvFlightID.setText(flight.getFlightID());
        tvFlightOrigin.setText(flight.getAirportOrigin());
        tvFlightOriginTerminal.setText(flight.getTerminalOrigin());
        tvFlightDestination.setText(flight.getAirportDestination());
        tvFlightDestinationGate.setText(flight.getGateDestination());
        tvFlightDestinationTerminal.setText(flight.getTerminalDestination());
        tvFlightOriginGate.setText(flight.getGateOrigin());
        tvAirplane.setText(flight.getAirplane());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM-yyyy HH:mm");
        tvDate.setText(simpleDateFormat.format(flight.getFlightTime()));
    }
}
