package com.pcun.b1.a1b_pcun_ma;

import android.app.Activity;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class CommentConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.196.104.239/graphql";         // new

    public CommentConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public void commentByPoint(int pointId, Activity context) {
        CommentByPoint commentByPoint = CommentByPoint.builder().id(pointId).build();
        apolloClient.query(
                commentByPoint
        ).enqueue(new ApolloCall.Callback<CommentByPoint.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CommentByPoint.Data> response) {
                ArrayList<CommentByPoint.CommentByPoint1> comments = new ArrayList<>(response.data().commentByPoint());
                Log.d("debug_lines", comments.toString());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d("debug_lines", "bu");
            }
        });
    }
}
