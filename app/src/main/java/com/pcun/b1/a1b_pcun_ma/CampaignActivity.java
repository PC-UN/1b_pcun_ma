package com.pcun.b1.a1b_pcun_ma;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CampaignActivity extends AppCompatActivity {
    public final String TAG = "debug_lines";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate campaign activity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_campaign);
        EditText filter_text = (EditText) findViewById(R.id.search_filter);

        final DisposalPointConnection disposalPointConnection =
                new DisposalPointConnection();
        disposalPointConnection.allPointsBasic(this);

        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                disposalPointConnection.getDisposalPointAdapter().getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, CreatePointForm.class);
        startActivity(intent);


    }
}
