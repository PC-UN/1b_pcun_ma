package com.pcun.b1.a1b_pcun_ma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActvity";
    Context context = this;
    private TextView username, mail;

    String email;
    int userid;

//    Bundle datos;
    Intent intent3 = getIntent();

    ImageButton b_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        datos = getIntent().getExtras();
        intent3 = getIntent();
        email = intent3.getStringExtra("email");
        userid = intent3.getIntExtra("userid",0);


        b_menu = (ImageButton) findViewById(R.id.menu);
        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserActivity.this,LateralMenuActivity.class);
                myIntent.putExtra("userid",userid);
                myIntent.putExtra("email", email);
                startActivity(myIntent);
            }
        });
        username = (TextView) findViewById(R.id.txtUserName);






        getUserByUserName();
    }

    private void getUserByUserName(){
//        SharedPreferences sharedPrefes = getSharedPreferences("userData",context.MODE_PRIVATE);
//        String username = sharedPrefes.getString("username", "");
//        datos = getIntent().getExtras();
    intent3 = getIntent();
        email = intent3.getStringExtra("email");
;
        MyApolloClient.getMyApolloClient().query(
                UserByUserEmailQuery.builder().email(email).build()).enqueue(new ApolloCall.Callback<UserByUserEmailQuery.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<UserByUserEmailQuery.Data> response) {
                Log.d(TAG, "onResponse: " + response.data().userByUsername);
                UserActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        username.setText(response.data().userByEmail().username);

                        email.setText(response.data().userByEmail().email);

                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserActivity.this, FragmentMapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
