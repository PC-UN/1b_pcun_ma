package com.pcun.b1.a1b_pcun_ma;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.pcun.b1.a1b_pcun_ma.type.AuthInput;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class AuthConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://35.185.71.134/graphql";         // new

    public AuthConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public void authenticate(String email, String password) {
        AuthInput authInput = AuthInput.builder()
                .email(email)
                .password(password)
                .build();

        Auth auth = Auth.builder().auth(authInput).build();

        apolloClient.mutate(
                auth
        ).enqueue(new ApolloCall.Callback<Auth.Data>() {
            @Override
            public void onResponse(@Nonnull Response<Auth.Data> response) {
                if (new Boolean(response.data().auth().answer()).booleanValue())
                    Log.d(TAG, "Authentication Successful!");
                else
                    Log.d(TAG, "Authentication Failed...");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "Unable to connect to GraphQL server...");
            }
        });
    }
}
