package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private FlightOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new FlightOperations(this);
        dao.open();
        dao.addFlight("AA1200", new Date(), "MTY", "3", "13A"
                , "MEX", "2", "12", "BOEING737");

        ArrayList<String> flightIDs = dao.getAllFlightIDs();
        TextView tvFlightIDs = (TextView) findViewById(R.id.text_flightID);
        tvFlightIDs.setText(flightIDs.get(0));
    }
}
