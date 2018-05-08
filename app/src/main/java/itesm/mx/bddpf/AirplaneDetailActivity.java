package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AirplaneDetailActivity extends AppCompatActivity {
    FlightOperations dao;
    public static final String ID_KEY = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_detail);

        Bundle bundle = getIntent().getExtras();
        String reservationCode = bundle.getString(ID_KEY);
        dao = new FlightOperations(this);
        dao.open();
        Airplane airplane = dao.getAirplane(reservationCode);


        TextView tvId = findViewById(R.id.text_id);
        TextView tvModel = findViewById(R.id.text_model);
        TextView tvEcoSeats = findViewById(R.id.text_ecoseats);
        TextView tvBusiSeats = findViewById(R.id.text_busiseats);
        TextView tvFirstSeats = findViewById(R.id.text_firstseats);

        tvId.setText(airplane.getID());
        tvModel.setText(airplane.getModel());
        tvEcoSeats.setText(Integer.toString(airplane.getEconomySeats()));
        tvBusiSeats.setText(Integer.toString(airplane.getBusinessSeats()));
        tvFirstSeats.setText(Integer.toString(airplane.getFirstSeats()));

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

}
