package com.pcun.b1.a1b_pcun_ma;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CampaignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
