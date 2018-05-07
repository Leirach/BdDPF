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

public class AirportAdapter extends ArrayAdapter<Airport> {
    public AirportAdapter(Context context, ArrayList<Airport> airports) {
        super(context, 0, airports);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Airport airport = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_airport, parent, false);
        TextView tvName = convertView.findViewById(R.id.text_name);
        TextView tvCode = convertView.findViewById(R.id.text_code);
        TextView tvCity = convertView.findViewById(R.id.text_city);
        TextView tvCountry = convertView.findViewById(R.id.text_country);

        tvName.setText(airport.getName());
        tvCode.setText(airport.getCode());
        tvCity.setText(airport.getCity());
        tvCountry.setText(airport.getCountry());

        return convertView;
    }
}
