package itesm.mx.bddpf;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan De León on 5/5/2018.
 */

public class PassengerAdapter extends ArrayAdapter<Passenger> {

    public PassengerAdapter(@NonNull Context context, ArrayList<Passenger> passengers) {
        super(context, 0, passengers);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Passenger passenger = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_passenger, parent, false);
        TextView tvPassName = (TextView) convertView.findViewById(R.id.text_pass_name);
        TextView tvPassCountry = (TextView) convertView.findViewById(R.id.text_pass_country);
        TextView tvPassCity = (TextView) convertView.findViewById(R.id.text_pass_city);
        TextView tvPassCell = (TextView) convertView.findViewById(R.id.text_pass_cel);
        TextView tvPassFixed = (TextView) convertView.findViewById(R.id.text_pass_fixed);
        TextView tvPassEmail = (TextView) convertView.findViewById(R.id.text_pass_email);

        tvPassName.setText(passenger.getFirstName() + " " + passenger.getLastName());
        tvPassCountry.setText(passenger.getCountry());
        tvPassCity.setText(passenger.getCity());
        tvPassCell.setText(passenger.getNumberCell());
        tvPassFixed.setText(passenger.getNumberFixed());
        tvPassEmail.setText(passenger.getEmail());

        return convertView;
    }
}
