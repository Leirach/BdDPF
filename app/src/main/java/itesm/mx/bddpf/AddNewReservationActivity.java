package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewReservationActivity extends AppCompatActivity {
    private EditText etCode;
    private EditText etPassenger;
    private EditText etPayment;
    private FlightOperations dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reservation);

        dao = new FlightOperations(this);
        dao.open();

        etCode = findViewById(R.id.edit_code);
        etPassenger = findViewById(R.id.edit_passenger);
        etPayment = findViewById(R.id.edit_payment);

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dao.addReservation(etCode.getText().toString(), etPayment.getText().toString(), etPassenger.getText().toString());
                if (result){
                    Toast.makeText(getApplicationContext(), "success!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
