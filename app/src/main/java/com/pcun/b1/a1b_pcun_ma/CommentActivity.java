package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        int idPunto = intent.getIntExtra("idPunto", -1);

        CommentConnection commentConnection = new CommentConnection();
        commentConnection.commentByPoint(idPunto, this);
    }
}
