package itesm.mx.bddpf;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AirplaneAdapter extends ArrayAdapter<Airplane> {
    public AirplaneAdapter(Context context, ArrayList<Airplane> airplanes) {
        super(context, 0, airplanes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Airplane airplane = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_airplane, parent, false);
        TextView tvID = convertView.findViewById(R.id.text_id);
        TextView tvModel = convertView.findViewById(R.id.text_model);

        tvID.setText(airplane.getID());
        tvModel.setText(airplane.getModel());

        return convertView;
    }
}
