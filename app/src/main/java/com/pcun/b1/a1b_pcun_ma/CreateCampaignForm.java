package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateCampaignForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_form);
    }

    public void onCreateButtonClick(View view) {
        String name = ((EditText) findViewById(R.id.editText1_name)).getText().toString();
        String city = ((EditText) findViewById(R.id.editText2_city)).getText().toString();
        String address = ((EditText) findViewById(R.id.editText3_address)).getText().toString();
        String ubication = ((EditText) findViewById(R.id.editText4_ubication)).getText().toString();
        String create_date = ((EditText) findViewById(R.id.editText5_create_date)).getText().toString();
        String start_date = ((EditText) findViewById(R.id.editText6_star_date)).getText().toString();
        String end_date = ((EditText) findViewById(R.id.editText7_end_date)).getText().toString();
        String status = ((EditText) findViewById(R.id.editText8_status)).getText().toString();
        String program = ((EditText) findViewById(R.id.editText9_program)).getText().toString();

        CampaignConnection campaignConnection = new CampaignConnection();
        campaignConnection.createCampaign(name, city, address, ubication, create_date, start_date, end_date, status, program);


        Intent intent = new Intent(this, FragmentMapActivity.class);
        intent.putExtra("from", 2);
        startActivity(intent);
    }
}
