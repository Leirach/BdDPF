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

public class TicketAdapter extends ArrayAdapter<Ticket> {
    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        super(context, 0, tickets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ticket ticket = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_tickets, parent, false);
        TextView tvTicket = convertView.findViewById(R.id.text_ticket);
        TextView tvPassenger = convertView.findViewById(R.id.text_passenger);
        TextView tvReservation = convertView.findViewById(R.id.text_reservation);
        TextView tvPrice = convertView.findViewById(R.id.text_price);

        tvPassenger.setText(ticket.getPassenger());
        tvPrice.setText(Double.toString(ticket.getPrice()));
        tvTicket.setText(ticket.getTicketNumber());
        tvReservation.setText(ticket.getReservation());
        return convertView;
    }
}
