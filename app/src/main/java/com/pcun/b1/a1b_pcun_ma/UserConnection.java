package com.pcun.b1.a1b_pcun_ma;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.pcun.b1.a1b_pcun_ma.type.UserInput;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class UserConnection {
    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://104.196.29.186/graphiql";
    //private static final String URL = "http://35.185.71.134/graphql";         // new

    public UserConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public void createUser(String username, String email, String password, final Activity context) {
        UserInput userInput = UserInput.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        CreateUser createUser = CreateUser.builder().user(userInput).build();

        apolloClient.mutate(
                createUser
        ).enqueue(new ApolloCall.Callback<CreateUser.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CreateUser.Data> response) {
                String username = response.data().createUser().username();
                int id = new Integer(response.data().createUser().id()).intValue();
                Log.d(TAG, "User " + username + " created... id = " + id);

                Intent intent = new Intent(context, FragmentMapActivity.class);
                intent.putExtra("from", 3);
                intent.putExtra("username", username);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "User creation failed...");
                Snackbar.make(context.findViewById(R.id.auth_canvas), "No se puede conectar con el servidor.", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static void closeSession(Activity context) {
        ((GlobalData) context.getApplication()).setSessionToken("");
        ((GlobalData) context.getApplication()).setCurrentUser(-1);
        ((GlobalData) context.getApplication()).setSessionVerified(false);
    }


    /*
        If user is logged, return its ID, else returns -1.
     */
    public static int checkSession(Activity context) {
        /*
            The true branch of following sentence validates that
            user be logged in app.
        */
        int userId = -1;
        GlobalData globalData = (GlobalData) context.getApplication();
        if(globalData.getSessionToken() != "") {
            AuthConnection authConnection = new AuthConnection();
            authConnection.checkSession(globalData.getSessionToken(), context);
            while (!globalData.isSessionVerified()) ;
            if(globalData.getCurrentUser() != -1)
                userId = globalData.getCurrentUser();
            else
                Toast.makeText(context, "Debes iniciar sesión.", Toast.LENGTH_SHORT).show();
            globalData.setSessionVerified(false);
        } else {
            Toast.makeText(context, "Debes iniciar sesión.", Toast.LENGTH_SHORT).show();
        }
        return userId;
    }
}
