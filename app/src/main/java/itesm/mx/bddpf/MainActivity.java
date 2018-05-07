package itesm.mx.bddpf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FlightOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new FlightOperations(this);
        dao.open();

        Button btnPassengers = (Button) findViewById(R.id.btn_seePassengers);
        Button btnFlights = (Button) findViewById(R.id.btn_seeFlights);
        Button btnTickets = (Button) findViewById(R.id.btn_seeTickets);
        Button btnReservations = (Button) findViewById(R.id.btn_seeReservations);
        Button btnFillDB = (Button) findViewById(R.id.btn_fillDB);
        Button btnClearDB = (Button) findViewById(R.id.btn_empty);
        Button btnAirplanes = (Button) findViewById(R.id.btn_airplanes);
        Button btnAirports = (Button) findViewById(R.id.btn_airport);

        btnPassengers.setOnClickListener(this);
        btnFlights.setOnClickListener(this);
        btnReservations.setOnClickListener(this);
        btnTickets.setOnClickListener(this);
        btnFillDB.setOnClickListener(this);
        btnClearDB.setOnClickListener(this);
        btnAirplanes.setOnClickListener(this);
        btnAirports.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seeFlights:
                Intent flightsActivity = new Intent(this, FlightsActivity.class);
                startActivity(flightsActivity);
                break;
            case R.id.btn_seePassengers:
                Intent passengerActivity = new Intent(this, PassengerActivity.class);
                startActivity(passengerActivity);
                break;
            case R.id.btn_seeReservations:
                Intent reservationsActivity = new Intent(this, ReservationsActivity.class);
                startActivity(reservationsActivity);
                break;
            case R.id.btn_seeTickets:
                Intent ticketsActivity = new Intent(this, TicketsActivity.class);
                startActivity(ticketsActivity);
                break;
            case R.id.btn_airplanes:
                Intent airplanesActivity = new Intent(this, AirplanesActivity.class);
                startActivity(airplanesActivity);
                break;
            case R.id.btn_airport:
                Intent airportsActivity = new Intent(this, AirportsActivity.class);
                startActivity(airportsActivity);
                break;
            case R.id.btn_fillDB:
                fillDatabase();
                break;
            case R.id.btn_empty:
                dao.dropAllTables();
                break;
        }
    }

    public void fillDatabase() {

        //Add flights
        dao.addFlight("AA1021", toDate(2018,4,12,17,50), 70, "MTY", "A", "12A", "MEX", "B", "17", "A127");
        dao.addFlight("AA4081", toDate(2018,4,12,19,30), 200, "MTY", "A", "13", "LAX", "C", "12D", "D322");
        dao.addFlight("AA2012", toDate(2018,4,12,22,30), 260, "MEX", "C", "2B", "LAX", "A", "4", "D142");
        dao.addFlight("AA8531", toDate(2018,4,12,23,0), 250, "LAX", "D", "12", "MEX", "B", "22A", "D238");
        dao.addFlight("AA4165", toDate(2018,4,13,8,40), 60, "MEX", "A", "38C", "MTY", "A", "5", "A132");
        dao.addFlight("AA7651", toDate(2018,4,13,11,20), 250, "LAX", "A", "22", "MEX", "C", "1", "D322");
        dao.addFlight("AA8264", toDate(2018,4,13,15,0), 220, "LAX", "B", "48C", "MTY", "B", "8", "D142");
        dao.addFlight("AA1662", toDate(2018,4,13,17,50), 65, "MTY", "B", "7", "MEX", "A", "14C", "A127");
        dao.addFlight("IJ6522", toDate(2018,4,14,7,0), 210, "MTY", "C", "6", "LAX", "A", "28D", "D238");
        dao.addFlight("IJ1255", toDate(2018,4,14,17,20), 240, "MEX", "D", "38C", "LAX", "C", "44A", "D142");
        dao.addFlight("IJ1642", toDate(2018,4,15,12,30), 65, "MEX", "B", "12", "MTY", "B", "9", "A132");
        dao.addFlight("IJ6423", toDate(2018,4,16,22,10), 260, "LAX", "A", "16", "MEX", "B", "17", "D238");

        //Add passengers
        dao.addPassenger("MARC650607HNLRIR07", "Adult", "8112562389", "8113322523", "Carlos Ivan", "Martinez Ruiz", "carlos.martinez@gmail.com", "Hidalgo 2212", "64123", "Monterrey", "Nuevo Leon",  "Mexico");
        dao.addPassenger("LOKD250607HNLKJR04", "Adult", "8112565219", "8114432521", "Juan Pablo", "Elizondo Benavides", "juan.elizzz@gmail.com", "Guadalajara 132", "64654", "Monterrey", "Nuevo Leon",  "Mexico");
        dao.addPassenger("ASDJ638741HNLRIR12", "Adult", "812352134", "8143433333", "Carlos Pablo", "Castro Benavides", "carlos.castro@gmail.com", "Montevideo 1253", "64123", "Monterrey", "Nuevo Leon",  "Mexico");
        dao.addPassenger("GUEL710105MNLILI04", "Child", null, null, "Luisa Regina", "Elizondo Castro", null, null, null, null, null, null);


        //Add reservations
        dao.addReservation("KPM123", "Cash".toUpperCase(), "MARC650607HNLRIR07");
        dao.addReservation("AVD691", "CreditCard".toUpperCase(), "ASDJ638741HNLRIR12");
        dao.addReservation("LKD231", "DebitCard".toUpperCase(), "LOKD250607HNLKJR04");
        dao.addReservation("GJK986", "Invoice".toUpperCase(), "MARC650607HNLRIR07");
        dao.addReservation("KJD763", "CreditCard".toUpperCase(), "ASDJ638741HNLRIR12");
        dao.addReservation("SJF235", "DebitCard".toUpperCase(), "LOKD250607HNLKJR04");
        dao.addReservation("OIF236", "Cash".toUpperCase(), "MARC650607HNLRIR07");
        dao.addReservation("SKJ234", "CreditCard".toUpperCase(), "LOKD250607HNLKJR04");
        dao.addReservation("AQW862", "Invoice".toUpperCase(), "LOKD250607HNLKJR04");
        dao.addReservation("KFJ823", "CreditCard".toUpperCase(), "ASDJ638741HNLRIR12");
        dao.addReservation("NMS234", "CreditCard".toUpperCase(), "MARC650607HNLRIR07");

        //Insert airports
        dao.addAirport("MEX", "Mexico City", "Mexico", "Benito Juarez International Airport");
        dao.addAirport("MTY", "Monterrey", "Mexico", "Mariano Escobedo International Airport");
        dao.addAirport("LAX", "Los Angeles", "USA", "Los Angeles International Airport");

        //Insert tickets
        dao.addTicket("LKJ125432A", 40.12, "14C", "Economy", "AA1021", "KPM123", "Food", "MARC650607HNLRIR07");
        dao.addTicket("8T4EBYIAGO", 240.53, "28D", "First", "AA4081", "AVD691", "", "ASDJ638741HNLRIR12");
        dao.addTicket("PIL3S0EWIG", 18.54, "10C", "Economy", "AA4165", "LKD231", "", "LOKD250607HNLKJR04");
        dao.addTicket("6NQG4NC9GT", 77.76, "13A", "Business", "AA1021", "GJK986", "", "MARC650607HNLRIR07");
        dao.addTicket("6A195VQHHC", 76.00, "8E", "Business", "IJ6522", "KJD763", "", "ASDJ638741HNLRIR12");
        dao.addTicket("OQI58NADK9", 27.90, "2A", "Economy", "IJ6522", "SJF235", "", "LOKD250607HNLKJR04");
        dao.addTicket("12520O3DF9", 45.88, "22C", "Economy", "IJ6423", "OIF236", "Food", "MARC650607HNLRIR07");
        dao.addTicket("N3GP9W40SC", 77.27, "19A", "Business", "AA8264", "SKJ234", "", "LOKD250607HNLKJR04");
        dao.addTicket("GORFXRUWZT", 77.27, "19B", "Business", "AA8264", "SKJ234", "", "GUEL710105MNLILI04");
        dao.addTicket("J5TXB7TTY3", 80.54, "7C", "Business", "IJ1255", "KFJ823", "", "ASDJ638741HNLRIR12");
        dao.addTicket("RGSUPK6KF2", 55.59, "2C", "Economy", "IJ1255", "NMS234", "", "MARC650607HNLRIR07");
        dao.addTicket("2IO05EVREX", 23.44, "6A", "Economy", "AA1662", "AQW862", "Food", "LOKD250607HNLKJR04");
        dao.addTicket("Q6DWJT2148", 23.44, "6B", "Economy", "IJ6423", "AQW862", "Food", "GUEL710105MNLILI04");


        //add airplanes
        dao.addAirplane("A127", "BOEING737", 140, 20, 0);
        dao.addAirplane("A132", "BOEING737", 140, 20, 0);
        dao.addAirplane("D142", "ABUSA330", 180, 40, 15);
        dao.addAirplane("D238", "ABUSA330", 180, 30, 25);
        dao.addAirplane("D322", "ABUSA330", 165, 50, 20);

    }

    public Date toDate(int year, int month, int date, int hour, int minute){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hour, minute);
        return cal.getTime();
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


