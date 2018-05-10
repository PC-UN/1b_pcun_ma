package com.pcun.b1.a1b_pcun_ma;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.pcun.b1.a1b_pcun_ma.type.AuthInput;
import com.pcun.b1.a1b_pcun_ma.type.AuthenticationInput;
import com.pcun.b1.a1b_pcun_ma.type.SessionInput;
import com.pcun.b1.a1b_pcun_ma.type.TokenInput;

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

    public void authenticate(final String email, final String password, final Activity context) {
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
                if (new Boolean(response.data().auth().answer()).booleanValue()) {
                    Log.d(TAG, "Authentication Successful!");
                    Intent intent = new Intent(context, FragmentMapActivity.class);
                    intent.putExtra("from", 4);
                    intent.putExtra("email", response.data().auth().email());

                    Log.d(TAG, "Generating session token...");
                    createSession(email, password, context);
                    context.startActivity(intent);
                } else {
                    Log.d(TAG, "Authentication Failed...");
                    Snackbar.make(context.findViewById(R.id.auth_canvas), "Usuario o contraseña incorrectos.", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "Unable to connect to GraphQL server...");
                Snackbar.make(context.findViewById(R.id.auth_canvas), "No se puede conectar con el servidor.", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    public void createSession(String email, String password, final Activity context) {
        AuthenticationInput authenticationInput = AuthenticationInput.builder()
                .email(email)
                .password(password)
                .build();

        SessionInput sessionInput = SessionInput.builder()
                .auth(authenticationInput)
                .build();

        CreateSession createSession = CreateSession.builder()
                .auth(sessionInput)
                .build();

        apolloClient.mutate(
                createSession
        ).enqueue(new ApolloCall.Callback<CreateSession.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CreateSession.Data> response) {
                String token = response.data().createSession().jwt();
                ((GlobalData) context.getApplication()).setSessionToken(token); // stores token in an global object
                Log.d(TAG, "Session created := " + token);
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "Session creation failed...");
                Snackbar.make(context.findViewById(R.id.auth_canvas), "No se puede conectar con el servidor.", Snackbar.LENGTH_LONG).show();
            }

        });
    }

    public void checkSession(String token, final Activity context) {

        TokenInput tokenInput = TokenInput.builder()
                .token(token)
                .build();

        CheckSession checkSession = CheckSession.builder()
                .token(tokenInput)
                .build();

        apolloClient.query(
                checkSession
        ).enqueue(new ApolloCall.Callback<CheckSession.Data>() {
            GlobalData globalData = (GlobalData) context.getApplication();
            @Override
            public void onResponse(@Nonnull Response<CheckSession.Data> response) {
                if(response.data() != null) {
                    Log.d(TAG, response.data().checkSession().username() + " has validated session. ");
                    globalData.setSessionVerified(true);
                    globalData.setCurrentUser(response.data().checkSession().id());
                } else {
                    Log.d(TAG, "The session does not exist...");
                    globalData.setSessionVerified(true);
                    globalData.setCurrentUser(-1);

                }
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "Connection to server refused.");
            }
        });
    }
}
