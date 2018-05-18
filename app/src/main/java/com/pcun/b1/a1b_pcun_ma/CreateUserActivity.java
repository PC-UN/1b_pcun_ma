package com.pcun.b1.a1b_pcun_ma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void onRegisterPressed(View view) {
        UserConnection userConnection = new UserConnection();

        String username = ((EditText) findViewById(R.id.register_username)).getText().toString();
        String email = ((EditText) findViewById(R.id.register_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.register_password)).getText().toString();

        userConnection.createUser(username, email, password, this);
    }
}
