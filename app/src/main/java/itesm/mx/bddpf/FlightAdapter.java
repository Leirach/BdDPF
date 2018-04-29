package itesm.mx.bddpf;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Flight flight = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_flight, parent, false);
        TextView tvFlightID = (TextView) convertView.findViewById(R.id.text_flightID);
        TextView tvFlightOrigin = (TextView) convertView.findViewById(R.id.text_from);
        TextView tvFlightOriginTerminal = (TextView) convertView.findViewById(R.id.text_from_terminal);
        TextView tvFlightOriginGate = (TextView) convertView.findViewById(R.id.text_from_gate);
        TextView tvFlightDestination = (TextView) convertView.findViewById(R.id.text_to);
        TextView tvFlightDestinationTerminal = (TextView) convertView.findViewById(R.id.text_to_terminal);
        TextView tvFlightDestinationGate = (TextView) convertView.findViewById(R.id.text_to_gate);
        TextView tvDate = (TextView) convertView.findViewById(R.id.text_date);

        tvFlightID.setText(flight.getFlightID());
        tvFlightOrigin.setText(flight.getAirportOrigin());
        tvFlightOriginTerminal.setText(flight.getTerminalOrigin());
        tvFlightDestination.setText(flight.getAirportDestination());
        tvFlightDestinationGate.setText(flight.getGateDestination());
        tvFlightDestinationTerminal.setText(flight.getTerminalDestination());
        tvFlightOriginGate.setText(flight.getGateOrigin());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM-yyyy HH:mm");
        tvDate.setText(simpleDateFormat.format(flight.getFlightTime()));
        return convertView;
    }
}
