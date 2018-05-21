package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreatePointForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_point_form);
    }

    public void onCreateButtonClick(View view) {
        String disposal_point_name = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String disposal_point_address = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String residue_category = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String contact_person = ((EditText) findViewById(R.id.editText4)).getText().toString();
        String contact_email = ((EditText) findViewById(R.id.editText5)).getText().toString();
        String latitud = ((EditText) findViewById(R.id.editText6)).getText().toString();
        String longitud = ((EditText) findViewById(R.id.editText7)).getText().toString();
        String schedule = ((EditText) findViewById(R.id.editText8)).getText().toString();
        String residue_type = "";
        String residue_name = "";


        DisposalPointConnection disposalPointConnection = new DisposalPointConnection();
        disposalPointConnection.createDisposalPoint(disposal_point_name, disposal_point_address, residue_category, contact_person, contact_email, latitud, longitud, schedule, residue_type, residue_name);

        Intent intent = new Intent(this, FragmentMapActivity.class);
        intent.putExtra("from", 2);
        startActivity(intent);
    }
}
