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

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    public ReservationAdapter(Context context, ArrayList<Reservation> reservations) {
        super(context, 0, reservations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Reservation reservation = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_reservation, parent, false);
        TextView tvCode = convertView.findViewById(R.id.text_reservationCode);
        TextView tvPassenger = convertView.findViewById(R.id.text_passenger);
        TextView tvPayment = convertView.findViewById(R.id.text_payment);

        tvCode.setText(reservation.getCode());
        tvPassenger.setText(reservation.getPassenger());
        tvPayment.setText(reservation.getPayment());
        return convertView;
    }
}
