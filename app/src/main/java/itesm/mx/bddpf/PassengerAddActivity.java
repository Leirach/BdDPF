package itesm.mx.bddpf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassengerAddActivity extends AppCompatActivity implements View.OnClickListener{

    private FlightOperations dao;
    private EditText[] form = new EditText[12];
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_add);

        dao = new FlightOperations(this);
        dao.open();

        addButton = (Button) findViewById(R.id.add_button); //button at the bottom of the form
        addButton.setOnClickListener(this);

        form[0] = (EditText) findViewById(R.id.curp_edit);      //curp
        form[1] = (EditText) findViewById(R.id.type_edit);      //tipo de pasajero
        form[2] = (EditText) findViewById(R.id.cel_edit);       //numero celular
        form[3] = (EditText) findViewById(R.id.fixed_edit);     //tipo de pasajero
        form[4] = (EditText) findViewById(R.id.name_edit);      //nombres
        form[5] = (EditText) findViewById(R.id.lastname_edit);  //apellido
        form[6] = (EditText) findViewById(R.id.email_edit);     //email
        form[7] = (EditText) findViewById(R.id.address_edit);   //address
        form[8] = (EditText) findViewById(R.id.zip_edit);       //zip code edit
        form[9] = (EditText) findViewById(R.id.city_edit);      //city
        form[10] = (EditText) findViewById(R.id.state_edit);    //state
        form[11] = (EditText) findViewById(R.id.country_edit);  //country
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            String[] formText = new String[form.length];
            for (int i=0; i<form.length; i++){
                formText[i] = form[i].getText().toString();
            }
            dao.addPassenger(formText[0], formText[1], formText[2], formText[3], formText[4], formText[5],
                    formText[6], formText[7], formText[8], formText[9], formText[10], formText[11]);

            Toast.makeText(getApplicationContext(), "Added passenger", Toast.LENGTH_SHORT).show();
        }
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
