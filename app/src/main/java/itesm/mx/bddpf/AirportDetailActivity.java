package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AirportDetailActivity extends AppCompatActivity {
    FlightOperations dao;
    public static final String CODE_KEY = "code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_detail);

        Bundle bundle = getIntent().getExtras();
        String airportCode = bundle.getString(CODE_KEY);
        dao = new FlightOperations(this);
        dao.open();
        Airport airport = dao.getAirport(airportCode);

        TextView tvCode = findViewById(R.id.text_code);
        TextView tvName = findViewById(R.id.text_name);
        TextView tvCity = findViewById(R.id.text_city);
        TextView tvCountry = findViewById(R.id.text_country);

        tvCode.setText(airport.getCode());
        tvName.setText(airport.getName());
        tvCity.setText(airport.getCity());
        tvCountry.setText(airport.getCountry());
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
