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

public class FlightIDAdapter extends ArrayAdapter<String> {
    public FlightIDAdapter(Context context, ArrayList<String> flightIDs) {
        super(context, 0, flightIDs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        TextView tvFlightID = (TextView) convertView.findViewById(R.id.text_flightID);
        tvFlightID.setText(getItem(position));
        return convertView;
    }
}
