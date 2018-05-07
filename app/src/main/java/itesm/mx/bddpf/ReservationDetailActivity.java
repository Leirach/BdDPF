package itesm.mx.bddpf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationDetailActivity extends AppCompatActivity {
    FlightOperations dao;
    public static final String CODE_KEY = "code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);

        Bundle bundle = getIntent().getExtras();
        String reservationCode = bundle.getString(CODE_KEY);
        dao = new FlightOperations(this);
        dao.open();
        Reservation reservation = dao.getReservation(reservationCode);


        TextView tvCode = findViewById(R.id.text_reservationCode);
        TextView tvPassenger = findViewById(R.id.text_passenger);
        TextView tvPayment = findViewById(R.id.text_payment);

        tvCode.setText(reservation.getCode());
        tvPassenger.setText(reservation.getPassenger());
        tvPayment.setText(reservation.getPayment());
    }
}
