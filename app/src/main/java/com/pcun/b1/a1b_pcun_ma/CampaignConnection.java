package com.pcun.b1.a1b_pcun_ma;


import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
//import com.pcun.b1.a1b_pcun_ma.type.CampaingInput;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;

public class CampaignConnection {

    /*

    private final String TAG = "debug_lines";
    private ApolloClient apolloClient;
    private OkHttpClient okHttpClient;
    private static final String URL = "http://104.196.29.186/graphiql";

    public CampaignConnection() {
        this.okHttpClient = new OkHttpClient.Builder().build();
        this.apolloClient = ApolloClient.builder()
                .serverUrl(URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public void allCampaigns(final AppCompatActivity context) {
        apolloClient.query(
                AllCampaigns.builder().build()
        ).enqueue(new ApolloCall.Callback<AllCampaigns.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllCampaigns.Data> response) {

                Log.d(TAG, "REQUEST SUCCEED!");

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                        txtResponse.setText(response.data().toString());
                    }
                });
            }



            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void campaignById(int id, final AppCompatActivity context) {
        CampaignById campaignById = CampaignById.builder().id(id).build();

        apolloClient.query(
                campaignById
        ).enqueue(new ApolloCall.Callback< CampaignById.Data>() {
            @Override
            public void onResponse(@Nonnull final Response< CampaignById.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView) context.findViewById(R.id.textView);
                        txtResponse.setText(response.data().toString());
                    }
                });

            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void createCampaign(String _id, String name, String city, String address, int ubication[], String created_date, String start_date, String end_date, String status, String program ) {
        CampaignInput campaignInput = CampaignInput.builder()
                .name(name)
                .city(city)
                .address(address)
                .ubication(ubication)
                .created_date(created_date)
                .start_date(start_date)
                .end_date(end_date)
                .status(status)
                .program(program)
                .build();

        CreateCampaign createCampaign = CreateCampaign.builder().campaignInput(campaignInput).build();

        apolloClient.mutate(
                createCampaign
        ).enqueue(new ApolloCall.Callback<CreateCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CreateCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void updateCampaign(String _id, String name, String city, String address, int ubication[], String created_date, String start_date, String end_date, String status, String program) {
        CampaignInput campaignInput = CampaignInput.builder()
                .name(name)
                .city(city)
                .address(address)
                .ubication(ubication)
                .created_date(created_date)
                .start_date(start_date)
                .end_date(end_date)
                .status(status)
                .program(program)
                .build();

        UpdateCampaign updateCampaign = UpdateCampaign.builder()
                .id(id).campaignInput(campaignInput).build();

        apolloClient.mutate(
                updateCampaign
        ).enqueue(new ApolloCall.Callback<UpdateCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<UpdateCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }

    public void deleteCampaign(String id) {
        DeleteCampaign deleteCampaign = DeleteCampaign.builder().id(id).build();
        apolloClient.mutate(
                deleteCampaign
        ).enqueue(new ApolloCall.Callback<DeleteCampaign.Data>() {
            @Override
            public void onResponse(@Nonnull Response<DeleteCampaign.Data> response) {
                Log.d(TAG, "REQUEST SUCCEED!");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.d(TAG, "REQUEST FAILED...");
                Log.d(TAG, e.toString());
            }
        });
    }*/
}
